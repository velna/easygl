package com.vanix.easygl.application.task;

import com.vanix.easygl.application.Ticket;

public class TimeoutTask extends RunnableTask {

    private long timeout;
    private long ticket;

    public TimeoutTask(String id, long timeout, Runnable runnable) {
        super(id, runnable);
        this.timeout = timeout;
    }

    @Override
    public void run() {
        long incr = ticket + timeout - Ticket.local().get();
        if (incr <= 0 && timeout >= 0) {
            super.run();
            ticket = -1;
        } else {
            timeout = incr;
        }
    }

    @Override
    public long next() {
        if (ticket == -1) {
            return -1;
        } else {
            ticket = Ticket.local().get();
            return timeout;
        }
    }

    public long remains() {
        return ticket > 0 ? Ticket.local().get() - ticket : -1;
    }

    public void incr(long incr) {
        if (ticket >= 0 && timeout >= 0) {
            timeout += incr;
        }
    }

    public void cancel() {
        timeout = -1;
    }
}
