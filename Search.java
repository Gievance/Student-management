package com.version1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Search {
    //查询学生信息
    public Search() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("----读取学生信息中----");
        Thread.sleep(1500);
        Check check = new Check();
        for (Student s : check.sturead())
            System.out.println(s);
        search();
    }

    public void search() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("请输入学生学号：");
        Scanner scanner = new Scanner(System.in);
        int id = check(scanner);
        LinkedList<Student> sturead = new Check().sturead();
        boolean flag = false;
        for (Student s : sturead)
            if (s.id == id) {
                flag = true;
                Student stu = new Student(s.id, s.name, s.age) {
                    @Override
                    public String toString() {
                        return "学生信息:" + "[" + "学号：" + id + "  姓名：" + name + "  年龄：" + age + "]";
                    }

                };
                System.out.println(stu);
            }
        if (!flag) {
            System.out.println("未查到此人，请重新查询");
            search();
        }
        System.out.println("是否继续查询？ 是Y,否N");
        check(scanner);
    }

    public int check(Scanner scan) throws InterruptedException, IOException, ClassNotFoundException {
        Pattern y = Pattern.compile("Y");
        Pattern n = Pattern.compile("N");
        if (scan.hasNextInt()) {
            int i = scan.nextInt();
            if (i < 0) {
                System.out.println("输入错误");
                check(scan);
            } else return i;
        }
        if (scan.hasNext(y)) {
            String next = scan.next();
            search();
        }
        if (scan.hasNext(n)) {
            String next = scan.next();
            new Display();
        }
        return 0;
    }
}
