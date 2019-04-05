package com.thread;

/**
 * @author: zhangf
 * @description:
 * @date: 2019/3/9
 *
 */
public class RunnableDemo implements Runnable{

    private String title = null;

    public RunnableDemo(String title){
        this.title = title;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10;x++) {
            System.out.println(title+",X="+x);
        }

    }

    public static void main(String[] args) {
        RunnableDemo th1 = new RunnableDemo("A");
        RunnableDemo th2 = new RunnableDemo("B");
        RunnableDemo th3 = new RunnableDemo("C");
//        th1.start();
        new Thread(th1).start();
//        th2.start();
        new Thread(th2).start();
//        th3.start();
        new Thread(th3).start();


        //匿名内部类的写法
        new Thread(new Runnable() {
            private String title = "D";
            @Override
            public void run() {
                for (int x = 0; x < 10;x++) {
                    System.out.println(title+",X="+x);
                }
            }
        }).start();

        //Lambda表达式
        new Thread(() ->{
            for (int x = 0; x < 10;x++) {
                System.out.println("E,X="+x);
            }
        }).start();

//        实际开发中后两种较为推荐

    }
}
