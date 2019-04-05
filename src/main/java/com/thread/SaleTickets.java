package com.thread;

import javax.annotation.security.RunAs;

/**
 * @author: zhangf
 * @description:
 * @date: 2019/3/9
 */
public class SaleTickets implements Runnable {
    private Integer ticket = 10;
    private String machineName = null;
    public SaleTickets(String machineName){
        this.machineName = machineName;
    }
    @Override
    public void run() {
//        synchronized(this){
//        }
        for (int x = 0; x < 10;x++) {
            if (ticket>0){
                System.out.println(machineName+" sale ticket:"+ ticket--);
            }
        }
    }

    public static void main(String[] args) {
        SaleTickets saleTicketsMachine1 = new SaleTickets("A");
//        SaleTickets saleTicketsMachine2 = new SaleTickets("B");
//        SaleTickets saleTicketsMachine3 = new SaleTickets("C");
        new Thread(saleTicketsMachine1).start();
        new Thread(saleTicketsMachine1).start();
        new Thread(saleTicketsMachine1).start();
    }
}
