package com.version1;

import java.io.IOException;
import java.util.Scanner;

public class Display {
    public Display() throws InterruptedException, IOException, ClassNotFoundException {
        show();
        order(code());
    }

    public  void show(){
        System.out.println("|1、查看学生信息");
        System.out.println("|2、添加学生信息");
        System.out.println("|3、删除学生信息");
        System.out.println("|4、修改学生信息");
        System.out.println("|5、查询学生信息");
        System.out.println("|6、排序学生信息");
        System.out.println("|7、随机点名器");
    }

    public void order(int sequence) throws InterruptedException, IOException, ClassNotFoundException {//根据序号进行进行
        switch (sequence)
        {
            case 1:new Check();break;
//            case 2:checkStudent();break;
//            case 3:checkStudent();break;
//            case 4:checkStudent();break;
//            case 5:checkStudent();break;
//            case 6:checkStudent();break;
//            case 7:checkStudent();break;

        }

    }
    public  int code(){//序号输入
        int codes=1;
        boolean flag=true;

        while(flag){
            codes= scanner();
            flag=check(codes);
        }
        return codes;
    }
    public int scanner(){//键盘输入
        System.out.print("请输入序号：");
        Scanner scanner = new Scanner(System.in);
        int code=scanner.nextInt();
        return code;
    }
    public boolean check(int code){//序号检测
        if (code>8||code<1)
            return true;
        else return false;
    }

}