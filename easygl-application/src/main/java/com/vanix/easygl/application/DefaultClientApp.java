package com.vanix.easygl.application;

import com.vanix.easygl.application.util.Counters;
import com.vanix.easygl.commons.value.DoubleValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.graphics.GraphicsException;
import com.vanix.easygl.core.meta.MetaSystem;
import com.vanix.easygl.window.Window;
import com.vanix.easygl.graphics.Graphics;
import com.vanix.easygl.graphics.GraphicsErrorCode;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultClientApp implements ClientApp {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final Counters counters = new Counters();
    protected Graphics graphics;
    protected Window window;
    private final Map<String, Renderer<DefaultClientApp, RenderContext>> renderers = new ListOrderedMap<>();
    protected final Ticket ticket;

    private boolean shutdown;

    public DefaultClientApp(String id, Window window) {
        this.window = window;
        ticket = new Ticket(id, 100);
        new Thread(this::mainLoop, "Loop").start();
        Thread counterThread = new Thread(this::counterLoop, "Counters");
        counterThread.setDaemon(true);
        counterThread.start();
    }

    private void mainLoop() {
        while (!shutdown) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        Ticket.shutdown();
    }

    private void counterLoop() {
        while (true) {
            counters.snapshot();
            counters.print(System.out);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    protected void initGraphics() throws GraphicsException {
        log.info("Client application startup.");
        graphics = Graphics.of();
        graphics.viewport(0, 0, window.frameBufferWidth(), window.frameBufferHeight());
    }

    public void run() throws GraphicsException {
        initGraphics();
        initRenderers();
        DoubleValue tickDelta = Value.of(0.0);
        RenderContext context = new DefaultRenderContext(window, graphics, tickDelta);
        Counters.Counter fps = counters.create("client.core", "fps", 0);
        long lastFrame = System.currentTimeMillis();
        while (!window.shouldClose()) {
            window.pollEvents();
            long frameStart = System.currentTimeMillis();
            updateAndRender(context);
            fps.incr();
            if (ticket.tick() < 0) {
                log.info("ticket shutdown");
                break;
            }
            tickDelta.set((System.currentTimeMillis() - lastFrame) / 1000.0);
            lastFrame = frameStart;
        }
    }

    private void updateAndRender(RenderContext context) {
        var error = GraphicsErrorCode.get();
        if (error.isError()) {
            log.error(String.format("GL error: %s", error));
        }
        renderers.forEach((id, renderer) -> {
            try {
                if (renderer.isActive()) {
                    renderer.render(context);
                }
            } catch (GraphicsException e) {
                log.error("Render error: ", e);
            }
        });
        window.swapBuffers();
    }

    public void close() {
        if (!shutdown) {
            log.info("Client application shutdown.");
            closeRenderers();
            shutdown = true;
            if (window != null) {
                window.close();
            }
            MetaSystem.closeAll();
        }
    }

    protected void initRenderers() {
        renderers.forEach((id, renderer) -> {
            try {
                renderer.init(this);
            } catch (GraphicsException e) {
                log.error("Renderer init error: ", e);
            }
        });
    }

    protected void closeRenderers() {
        renderers.forEach((id, renderer) -> {
            try {
                renderer.close();
            } catch (Exception e) {
                log.error("Error close renderer: ", e);
            }
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void registerRenderer(Renderer<? extends ClientApp, ? extends RenderContext> renderer) {
        this.renderers.put(renderer.id(), (Renderer) renderer);
    }

    @Override
    public Window window() {
        return window;
    }

    @Override
    public Graphics graphics() {
        return graphics;
    }

    @Override
    public Ticket ticket() {
        return ticket;
    }

    @Override
    public Counters counters() {
        return counters;
    }
}
