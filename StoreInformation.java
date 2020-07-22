package com.version1;

import java.io.*;
import java.util.LinkedList;
import java.util.TreeSet;

public class StoreInformation {
    public static void main(String[] args) throws IOException {
        Student s1 = new Student(1001, "可爱多", 18);
        Student s2 = new Student(1002, "张东升", 19);
        Student s3 = new Student(1003, "秦昊", 20);
        Student s4 = new Student(1004, "奈奈", 21);
        Student s5 = new Student(1005, "雨果", 21);
        Student s6 = new Student(1006, "边城", 23);
        Student s7 = new Student(1007, "秦始皇", 22);
        Student s8 = new Student(1008, "兰陵王", 17);
        Student s9 = new Student(1009, "伽罗", 23);
        Student s10 = new Student(1010, "张朝阳", 24);
        Student s11 = new Student(1011, "浴兰", 29);
        LinkedList<Student> tree = new LinkedList<>();
        tree.add(s1);
        tree.add(s2);
        tree.add(s3);
        tree.add(s4);
        tree.add(s5);
        tree.add(s6);
        tree.add(s7);
        tree.add(s8);
        tree.add(s9);
        tree.add(s10);
        tree.add(s11);
        for (int i=0;i<11;i++) {
            File file = new File("s" + (i+1) + ".txt");
            if (!file.exists())
                file.createNewFile();
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
            obj.writeObject(tree.get(i));
        }
    }
}
