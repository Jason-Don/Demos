package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: zhangf
 * @description:
 * @date: 2019/3/9
 *
 * Callable实现带有返回值的线程。
 * FutureTask
 * 
 */
public class CallableDemo implements Callable<String> {

    private String title = null;

    public CallableDemo(String title){
        this.title = title;
    }

    @Override
    public String call() {
        for (int x = 0; x < 10;x++) {
            System.out.println(title+",X="+x);
        }
        return "票卖完了！";
    }

    public static void main(String[] args) {
        CallableDemo th1 = new CallableDemo("A");
        FutureTask task = new FutureTask(th1);
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
