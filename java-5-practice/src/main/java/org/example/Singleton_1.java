package org.example;

public class Singleton_1 {
    private static final Singleton instance;

    static {
        instance = new Singleton();
    }

    private Singleton_1() {}

    public static Singleton getInstance() {
        return instance;
    }
}
