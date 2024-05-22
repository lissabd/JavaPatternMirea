package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreList<T> {
    private List<T> list;
    private Semaphore semaphore;
    public SemaphoreList() {
        list = new ArrayList<>();
        // Используем семафор с одним разрешением для обеспечения доступа к списку
        semaphore = new Semaphore(1);
    }
    public void add(T item) throws InterruptedException {
        semaphore.acquire(); // Пытаемся захватить разрешение семафора
        try {
            list.add(item); // Добавляем элемент в список
        } finally {
            semaphore.release(); // Освобождаем разрешение семафора
        }
    }
    public void remove(int index) throws InterruptedException {
        semaphore.acquire(); // Пытаемся захватить разрешение семафора
        try {
            list.remove(index); // Удаляем элемент из списка по индексу
        } finally {
            semaphore.release(); // Освобождаем разрешение семафора
        }
    }

    public T get(int index) throws InterruptedException {
        semaphore.acquire(); // Пытаемся захватить разрешение семафора
        try {
            return list.get(index); // Получаем элемент списка по индексу
        } finally {
            semaphore.release(); // Освобождаем разрешение семафора
        }
    }

    public int size() throws InterruptedException {
        semaphore.acquire(); // Пытаемся захватить разрешение семафора
        try {
            return list.size(); // Получаем размер списка
        } finally {
            semaphore.release(); // Освобождаем разрешение семафора
        }
    }
    public static void main(String[] args) {
        // Создаем экземпляр списка
        SemaphoreList<Integer> synchronizedList = new SemaphoreList<>();

        // Создаем и запускаем первый поток, который добавляет элементы в список
        Thread addThread = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronizedList.add(i);
                    System.out.println("Thread 1: Added " + i);
                    Thread.sleep(100); // Даем немного времени для визуализации
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Создаем и запускаем второй поток, который удаляет элементы из списка
        Thread removeThread = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    synchronizedList.remove(0); // Удаляем элемент с индексом 0
                    System.out.println("Thread 2: Removed element at index 0");
                    Thread.sleep(150); // Даем немного времени для визуализации
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        addThread.start();
        removeThread.start();

        // Ждем завершения работы обоих потоков
        try {
            addThread.join();
            removeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Печатаем содержимое списка после выполнения операций
        System.out.println("Final list: " + synchronizedList);
    }
}
