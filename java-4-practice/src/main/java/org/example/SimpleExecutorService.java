package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleExecutorService {
    private final int numThreads;
    private final List<WorkerThread> threads;
    private final BlockingQueue<Runnable> taskQueue;

    public SimpleExecutorService(int numThreads) {
        this.numThreads = numThreads;
        this.threads = new ArrayList<>(numThreads);
        this.taskQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < numThreads; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threads.add(thread);
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        for (WorkerThread thread : threads) {
            thread.interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    public static void main(String[] args) {
        SimpleExecutorService executorService = new SimpleExecutorService(2); // Создаем пул из 2 потоков

        // Отправляем задачи на выполнение
        executorService.submit(() -> System.out.println("Task 1 is running"));
        executorService.submit(() -> System.out.println("Task 2 is running"));
        executorService.submit(() -> System.out.println("Task 3 is running"));

        // Небольшая задержка, чтобы дать потокам время на выполнение задач
        try {
            Thread.sleep(1000); // Подождать 1 секунду
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Завершаем работу пула потоков
        executorService.shutdown();
    }

}
