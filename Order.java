package com.version1;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Order {
    public Order() throws InterruptedException, IOException, ClassNotFoundException {
        order();
    }

    public void order() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("正在随机抽选....");
        Thread.sleep(1200);
        random();
    }
    public void random() throws InterruptedException, IOException, ClassNotFoundException {
        LinkedList<Student> sturead = new Check().sturead();
        Collections.shuffle(sturead);
        Collections.shuffle(sturead);
        Random random = new Random();
        int i = random.nextInt(sturead.size());
        System.out.println("幸运儿："+sturead.get(i));
        System.out.println("是否继续抽选？ 是1，否0");
        check();
    }
    public void check() throws InterruptedException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt())
            switch (scanner.nextInt())
            {
                case 0:new Display();break;
                case 1:random();break;
                default:{
                    System.out.println("输入有误!!!!");check();}break;
            }
    }
}
