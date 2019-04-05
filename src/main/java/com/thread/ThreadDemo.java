package com.thread;

/**
 * @author: zhangf
 * @description:
 * @date: 2019/3/9
 *
 * 通过直接继承Thread类的方式实现多线程，所造成的问题是单继承的局限。
 *
 */
public class ThreadDemo extends Thread{

    private String title = null;

    public ThreadDemo(String title){
        this.title = title;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10;x++) {
            System.out.println(title+",X="+x);
        }

    }

    public static void main(String[] args) {
        ThreadDemo th1 = new ThreadDemo("A");
        ThreadDemo th2 = new ThreadDemo("B");
        ThreadDemo th3 = new ThreadDemo("C");
        th1.start();
        th2.start();
        th3.start();
    }
}
