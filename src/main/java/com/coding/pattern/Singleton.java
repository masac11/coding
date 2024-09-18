package com.coding.pattern;

public class Singleton {

    private static volatile Singleton singleton = null;
    private Singleton() {}

    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
