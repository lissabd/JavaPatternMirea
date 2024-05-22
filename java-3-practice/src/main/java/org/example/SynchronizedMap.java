package org.example;

import java.util.HashMap;
import java.util.Map;

public class SynchronizedMap <K, V> {
    private Map <K,V> map;
    public SynchronizedMap() {
        map = new HashMap<>();
    }
    public SynchronizedMap(int size) {
        map = new HashMap<>(size);
    }
    public synchronized void put(K key, V value) {
        map.put(key, value);
    }
    public synchronized V get(K key) {
        return map.get(key);
    }
    public synchronized V remove(K key) {
        return map.remove(key);
    }

    public synchronized boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public synchronized boolean containsValue(V value) {
        return map.containsValue(value);
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }
    public static void main(String[] args) {
        SynchronizedMap<String, Integer> synchronizedMap = new SynchronizedMap<>();


        Thread thread1 = new Thread(() -> {
            synchronizedMap.put("key", 1);
            System.out.println("Thread 1: Element  1 added");
            synchronizedMap.put("key", 1);
            System.out.println("Thread 1: Element 2 added");
        });


        Thread thread2 = new Thread(() -> {
            synchronizedMap.remove("key");
            System.out.println("Thread 2: Element removed");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final size of the map: " + synchronizedMap.size());
    }

}
