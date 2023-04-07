package com.berat.creational;

/**
 * Singleton desgin pattern çalışma zamanında yalnızca 1 object yaratılmasını garanti eden tasarım desenidir.
 * Eager ve Lazy yöntemleri vardır.
 */
public class Singleton {
    private static Singleton INSTANCE;
    private String info = "Initial value";

    private Singleton() { }

    public static Singleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

class SingletonMain {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1.getInfo());

        Singleton singleton2 = Singleton.getInstance();
        singleton2.setInfo("Changed value");

        System.out.println(singleton2.getInfo());
        System.out.println(singleton1.getInfo());
    }
}