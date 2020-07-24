package com.version1;

import java.io.IOException;
import java.util.Scanner;

public class Display implements Scanners<Integer>, Clear {
    public Display() throws InterruptedException, IOException, ClassNotFoundException {
        show();
        order(code());
    }

    public void show() {
        System.out.println("|1、查看学生信息");
        System.out.println("|2、添加学生信息");
        System.out.println("|3、删除学生信息");
        System.out.println("|4、修改学生信息");
        System.out.println("|5、查询学生信息");
        System.out.println("|6、排序学生信息");
        System.out.println("|7、随机点名器");
    }

    public void order(int sequence) throws InterruptedException, IOException, ClassNotFoundException {//根据序号进行进行
        switch (sequence) {
            case 1: {
                Check check = new Check();
                check.show10(check.sturead());
            }

            break;
            case 2: {
                Add add = new Add();
                add.addstu();
            }

            break;
            case 3:{new Delete().delete();}break;
            case 4:{new change();}break;
            case 5:new Search();break;
            case 6:new Sort();break;
            case 7:new Order();break;

        }

    }


    public int code() throws InterruptedException, IOException, ClassNotFoundException {//序号输入
        int codes = 1;
        boolean flag = true;

        while (flag) {
            codes = scanner();
            flag = check(codes);
        }
        return codes;
    }

    @Override
    public Integer scanner() throws InterruptedException, IOException, ClassNotFoundException {//键盘输入
        System.out.print("请输入序号：");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            return scanner.nextInt();

        } else {
            System.out.println("您输入有误！！请重新输入");
            clear();
            new Display();
        }
        return 0;
    }

    public boolean check(int code) {//序号检测
        if (code > 8 || code < 1) {
            System.out.println("请输入数字在0 -  8之间");
            return true;
        } else return false;
    }

    @Override
    public void clear() {
        System.out.println("\n\n\n\n\n\n\n");
    }
}
