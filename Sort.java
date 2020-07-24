package com.version1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Sort {
    public Sort() throws InterruptedException, IOException, ClassNotFoundException {
        Check check = new Check();
        for (Student s : check.sturead())
            System.out.println(s);
        sort();
    }

    public void sort() throws InterruptedException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("按学号还是年龄排序？ 学号：0，年龄: 1");
        check(scanner);

    }

    public void check(Scanner scan) throws InterruptedException, IOException, ClassNotFoundException {
        Pattern y = Pattern.compile("Y");
        Pattern n = Pattern.compile("N");
        if (scan.hasNext(y))
            sort();
        if (scan.hasNext(n))
            new Display();
        if (scan.hasNextInt())
            switch (scan.nextInt())
            {
                case 0:sortId(scan);break;
                case 1:sortAge(scan);break;
                default:
                {System.out.println("格式错误！！请重新输入");check(scan);}break;
            }
    }

    private void sortAge(Scanner s) throws InterruptedException, IOException, ClassNotFoundException {
        LinkedList<Student> sturead = new Check() {
            @Override
            public LinkedList<Student> sort(LinkedList<Student> t) {
               t.sort((o1, o2) -> {
                       int res = o1.age - o2.age;
                       int res2 = res == 0 ? o1.id - o2.id : res;
                       return res2 == 0 ? o1.name.compareTo(o2.name) : res2;});
                return t;
            }
        }.sturead();
        for (Student stu:sturead)
            System.out.println(stu);
        System.out.println("是否继续？ 是Y,否N");
        check(s);
    }

    private void sortId(Scanner s) throws InterruptedException, IOException, ClassNotFoundException {
        LinkedList<Student> sturead = new Check().sturead();
        for (Student stu:sturead)
            System.out.println(stu);
        System.out.println("是否继续？ 是Y,否N");
        check(s);
    }
}
