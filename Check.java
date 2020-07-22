package com.version1;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner;

public class Check {
    //实现查看学生信息

    public Check() throws IOException, ClassNotFoundException, InterruptedException {
        show10(sturead());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Student> sturead = sturead();
        System.out.println(sturead);
    }
    //返回所有学生对象的列表
    public static ArrayList<Student> sturead() throws IOException, ClassNotFoundException {
        Path path = Paths.get("S:\\PROJECT");
        ArrayList<Path> arr = new ArrayList<>();//存放学生路径
        String glob = "glob:**/s*.txt";
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file))
                    arr.add(file);
                return FileVisitResult.CONTINUE;
            }
        });
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {

            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(String.valueOf(arr.get(i))));
            Object o = obj.readObject();
            Student s = (Student) o;
            students.add(s);
            System.out.println(students);
        }

        return students;
    }

    //展示
    public void show10(ArrayList<Student> arr) throws InterruptedException, IOException, ClassNotFoundException {
        int count = arr.size() >= 10 ? 10 : arr.size() - 1;
        for (int i = 0; i < count; i++) {
            System.out.println(arr.get(i).toString());
            count--;
        }
        if (count != 0) {
            check(scanner());
        } else {
            System.out.println("无学生数据");
            wait(5000);
            new Display();
        }
    }

    public void check(String c) throws IOException, ClassNotFoundException, InterruptedException {
        switch (c) {
            case "Y":
                show10(sturead());
                break;
            case "N":
                new Display();
                break;
            default: {
                System.out.println("请重新输入");
                check(scanner());
            };
            break;
        }
    }

    public String scanner() {
        System.out.println("是否继续查看？ 是=Y,不是=N");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return next;
    }
}
