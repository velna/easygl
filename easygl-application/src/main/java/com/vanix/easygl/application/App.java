package com.vanix.easygl.application;


import com.vanix.easygl.application.task.Task;

public class App {

    protected static final ThreadLocal<App> CONTEXT = new ThreadLocal<>();

    protected final Ticket ticket;

    public App(String id) {
        ticket = new Ticket(id);
        ticket.submit(Task.once("RegisterGame", () -> CONTEXT.set(this)));
    }

    public Ticket ticket() {
        return ticket;
    }

    @SuppressWarnings("unchecked")
    public static <T extends App> T get() {
        return (T) CONTEXT.get();
    }

    public static void submit(Task task) {
        get().ticket().submit(task);
    }

    public static long getTicket() {
        return get().ticket().get();
    }

}
