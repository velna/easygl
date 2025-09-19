package com.vanix.easygl.application.g3d;

import com.vanix.easygl.commons.primitives.Positiondc;
import com.vanix.easygl.commons.value.FloatValue;
import com.vanix.easygl.commons.value.Value;
import com.vanix.easygl.core.Direction;
import com.vanix.easygl.window.input.Keyboard;
import com.vanix.easygl.window.input.Mouse;
import com.vanix.easygl.window.input.event.MouseScrollEvent;
import lombok.Setter;


public class ControllableCamera extends Camera {
    @Setter
    private Keyboard.Key forwardKey = Keyboard.PrintableKey.W;
    @Setter
    private Keyboard.Key backwardKey = Keyboard.PrintableKey.S;
    @Setter
    private Keyboard.Key leftKey = Keyboard.PrintableKey.A;
    @Setter
    private Keyboard.Key rightKey = Keyboard.PrintableKey.D;
    @Setter
    private Keyboard.Key upKey = Keyboard.PrintableKey.SPACE;
    @Setter
    private Keyboard.Key downKey = Keyboard.FunctionKey.LEFT_SHIFT;
    @Setter
    private float mouseSensitivity = 0.1f;
    private final long startTime = System.currentTimeMillis();
    private float lastFrame;
    private double x;
    private double y;
    private final FloatValue yaw = Value.of(-120.0f);
    private final FloatValue pitch = Value.limited(-20f, -89.0f, 89.0f);
    private final Keyboard keyboard;
    private final Mouse mouse;

    public ControllableCamera(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        yaw.addInterceptor((oldValue, setValue) -> {
            update(pitch.get(), setValue);
            return setValue;
        });
        pitch.addInterceptor((oldValue, setValue) -> {
            update(setValue, yaw.get());
            return setValue;
        });
    }

    public ControllableCamera update() {
        float currentFrame = (System.currentTimeMillis() - startTime) / 1000.0f;
        float deltaTime = currentFrame - lastFrame;
        lastFrame = currentFrame;

        if (keyboard != null) {
            if (keyboard.isPressed(forwardKey)) {
                move(Direction.Forward, deltaTime);
            }
            if (keyboard.isPressed(backwardKey)) {
                move(Direction.Backward, deltaTime);
            }
            if (keyboard.isPressed(leftKey)) {
                move(Direction.Left, deltaTime);
            }
            if (keyboard.isPressed(rightKey)) {
                move(Direction.Right, deltaTime);
            }
            if (keyboard.isPressed(upKey)) {
                move(Direction.Up, deltaTime);
            }
            if (keyboard.isPressed(downKey)) {
                move(Direction.Down, deltaTime);
            }
        }
        if (mouse != null) {
            Positiondc pos = mouse.getPosition();
            if (x == 0 && y == 0) {
                x = pos.getX();
                y = pos.getY();
            }
            double lastX = x;
            double lastY = y;
            this.x = pos.getX();
            this.y = pos.getY();
            double xoffset = x - lastX;
            double yoffset = y - lastY;

            float sens = mouseSensitivity;
            xoffset *= sens;
            yoffset *= sens;

            yaw.incr((float) xoffset);
            pitch.incr(-(float) yoffset);
            update(pitch.get(), yaw.get());
        }

        return this;
    }

    private void mouseOnScroll(MouseScrollEvent event) {
        fov().incr(-(float) event.yOffset());
    }

    public void setZoomOnMouseScroll(boolean enable) {
        if (enable && mouse != null) {
            mouse.onScroll().subscribe(this::mouseOnScroll);
        } else if (!enable && mouse != null) {
            mouse.onScroll().unsubscribe(this::mouseOnScroll);
        }
    }

    public FloatValue yaw() {
        return yaw;
    }

    public FloatValue pitch() {
        return pitch;
    }
}
