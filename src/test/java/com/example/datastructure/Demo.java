package com.example.datastructure;

public class Demo {

    Holder holder;

    public static void main(String[] args) {
        new Demo().test();
    }

    public void initHolder() {
        holder = new Holder(42);
    }

    public void test() {

        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                initHolder();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                if (holder != null) {
                    holder.assertSanity();
                }
            }
        }).start();


    }

    static class Holder {
        private int n;

        public Holder(int n) {
            this.n = n;
        }

        public void assertSanity() {
            if (n != n) {
                throw new AssertionError("This statement is false");
            }
        }
    }

}
