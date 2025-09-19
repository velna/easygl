package com.vanix.easygl.commons;

public interface Chained<C> {
    C then();

    class Simple<C> implements Chained<C> {
        protected final C owner;

        public Simple(C owner) {
            this.owner = owner;
        }

        @Override
        public C then() {
            return owner;
        }
    }
}
