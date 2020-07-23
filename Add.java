package com.version1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.CookieHandler;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Add implements Clear{
    private LinkedList<Student> stu;//
    private ArrayList<Integer> ida=new ArrayList<>();
    private ArrayList<String> namea=new ArrayList<>();
    private ArrayList<Integer> agea=new ArrayList<>();
    //从Check中读取学生列表
    public Add() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("正在读取中.....");
        Check check = new Check();
        Check check1 = new Check();
        this.stu = check1.sturead();
//        System.out.println(stu);  Stu没有问题
        //初始化学生列表-用于检验
        for (Student s:stu)
        {
            ida.add(s.id);
            namea.add(s.name);
            agea.add(s.age);
        }
        System.out.println("读取完成!!!");

    }
    //检查id
    int checkId(Scanner scan) throws InterruptedException, IOException, ClassNotFoundException {
        int i = scan.nextInt();
        if (i<0)
        {System.out.println("学生id错误");new Add().addstu();}
        else
        {
            if (ida.contains(i))
            {System.out.println("学生id重复");new Add().addstu();}
            else
                return i;
        }
        return -1;

    }
//    检查姓名
    String checkName(Scanner scan) throws InterruptedException, IOException, ClassNotFoundException {
        String s = scan.next();
        if (s.contains("*")||s.contains("#")||s.contains("$")||s.contains("&")||s.contains("%"))
        {System.out.println("输入姓名有未知字符");new Add().addstu();}
        else
        {
            if (namea.contains(s))
            {System.out.println("输入姓名有重复"); new Add().addstu();}
            else
                return s;
        }
        return "unknown";
    }
//    检查年龄
    int checkAge(Scanner scan) throws InterruptedException, IOException, ClassNotFoundException {
        int i = scan.nextInt();
        if (i<0)
        {System.out.println("学生age错误");new Add().addstu();}
        else
        {
                return i;
        }
        return 0;
    }
    //添加学生

    public void addstu() throws IOException, ClassNotFoundException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学号： ");
        int id = checkId(scan);
        System.out.println("请输入姓名: ");
        String name = checkName(scan);
        System.out.println("请输入年龄: ");
        int age = checkAge(scan);
        System.out.println("已存储");
        File file = createFile();
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));
        Student stu = new Student(id, name, age);
        obj.writeObject(stu);
        obj.close();
        System.out.println("学生添加成功,自动返回主菜单");
        clear();
        new Display();
    }
//createFile由addstu调用，无需调用
    public File createFile() throws IOException {
        String glob = "glob:**/*.txt";
        LinkedList<Path> arr = new LinkedList<>();//存放文件路径
        File dir = new File("s://PROJECT");
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                if (pathMatcher.matches(file)) {
                    arr.add(file);//存放到自定义文件路径。用来获取文件名数字
                }
                return FileVisitResult.CONTINUE;
            }
        });
        ArrayList<Integer> iarr = new ArrayList<>();
        for (Path path:arr)
        {
            Integer integer = Integer.valueOf(path.getFileName().toString().split(".txt")[0]);
            iarr.add(integer);
        }
        Integer max = Collections.max(iarr);
        //创建一个新文件 ，按数字max+1命名
        File file = new File((++max) + ".txt");
        if (!file.exists()) {
            boolean res = file.createNewFile();
        }
        return file;
    }
    @Override
    public void clear() {
        System.out.println("\n\n\n\n\n\n\n");
    }
}
