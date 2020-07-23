package com.version1;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class change implements Clear{
    public change() throws InterruptedException, IOException, ClassNotFoundException {
        set();
    }
    public void show() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("----读取学生信息中----");
        Check check = new Check();
        for (Student s : check.sturead())
            System.out.println(s);
    }
    public void set() throws IOException, ClassNotFoundException, InterruptedException {
        clear();
        show();
        System.out.println("请输入要修改的id");
        Scanner scanner = new Scanner(System.in);
        int id = getId(scanner);
        HashMap<Integer, String> map = new Delete().getClas(new Delete().getObj());
        Set<Integer> key = map.keySet();
        for (Integer i:key)
        {
            if (i==id)
            {
                ObjectInputStream obj = new ObjectInputStream(new FileInputStream(map.get(i)));
                Object o = obj.readObject();
                Student s=(Student)o;
                Student cs=changeInfo(s);
                obj.close();
                ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(map.get(i)));
                oo.writeObject(cs);
                oo.close();
            }
        }
        System.out.println("正在修改...");
        Thread.sleep(1000);
        System.out.println("修改完成");
        new Display();
    }
    public Student changeInfo(Student student)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String next = scanner.next();
        System.out.print("请输入年龄");
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            return new Student(student.id, next, i);
        }
        return student;
    }
    public int getId(Scanner scan){
        if (scan.hasNextInt()) {
            return  scan.nextInt();
        }
        return 0;
    }
    @Override
    public void clear() {
        System.out.println("\n\n\n\n\n\n\n\n");
    }
}
