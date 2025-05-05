package thread;

import java.util.LinkedList;

public class ThreadPool {
    int ThreadPoolSize;

    LinkedList<Runnable> tasks = new LinkedList<Runnable>();

    public ThreadPool(int size) {
        ThreadPoolSize = size;

        for (int i = 0; i < size; i++) {
            synchronized (tasks) {
                new TaskConsumeThread("线程" + i).start();
            }
        }
    }

    public void addTask(Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }
        Runnable task;
        public void run() {
            System.out.println("启动： " + this.getName());
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeFirst();
                    //tasks.notifyAll();
                }
                System.out.println(this.getName() + " 获取到任务，并执行");
                task.run();
            }
        }
    }
}
