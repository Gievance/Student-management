package com.version1;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Check {
    //实现查看学生信息
    private int page=0;
    public Check() throws IOException, ClassNotFoundException, InterruptedException {
        show10(sturead(),page);
    }

    //返回所有学生对象的列表
    public static LinkedList<Student> sturead() throws IOException, ClassNotFoundException {
        Path path = Paths.get("S:\\PROJECT");
        LinkedList<Path> arr = new LinkedList<>();//存放学生路径
        String glob = "glob:**/*.txt";
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file))
                    arr.add(file);
                return FileVisitResult.CONTINUE;
            }
        });
        LinkedList<Student> students = new LinkedList<>();
        for (Path p : arr) {

            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(p.toFile()));
            Object o = obj.readObject();
            Student s = (Student) o;
            students.add(s);
            obj.close();
        }
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int res = o1.id - o2.id;
                int res2 = res == 0 ? o1.age - o2.age : res;
                int res3 = res2 == 0 ? o1.name.compareTo(o2.name) : res2;
                return res3;
            }
        });
        return students;
    }

    //展示
    public void show10(LinkedList<Student> arr, int page) throws InterruptedException, IOException, ClassNotFoundException {
        Object[] array = arr.toArray();
        int pages=array.length/10;
        int con=array.length%10;
        int mul=page*10;
        for (int i=mul;i<(array.length-(mul)<10?con+(mul):i+10);i++)
            System.out.println((Student)array[i]);
        if (page==(pages+(con>0?1:0)))
            page=0;
    }


    public void check(String c) throws IOException, ClassNotFoundException, InterruptedException {
        switch (c) {
            case "Y": {
                show10(sturead(),page++);
            };
            break;
            case "N":
                new Display();
                break;
            default: {
                System.out.println("请重新输入");
                check(scanner());
            }
            ;
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
