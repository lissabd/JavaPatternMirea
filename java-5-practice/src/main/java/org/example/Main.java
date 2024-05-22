package org.example;

public class Main {
    public static void main(String[] args) {
        // Создаем первый экземпляр Singleton
        Singleton singleton1 = Singleton.getInstance();

        // Создаем второй экземпляр Singleton
        Singleton singleton2 = Singleton.getInstance();

        // Сравниваем ссылки на объекты
        System.out.println("Ссылки на объекты одинаковы: " + (singleton1 == singleton2));
    }
}
