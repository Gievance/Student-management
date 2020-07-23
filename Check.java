package com.version1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Check implements Scanners, Clear {
    //实现查看学生信息
    public Check() throws IOException, ClassNotFoundException, InterruptedException {

    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        sturead();
//    }
    //返回所有学生对象的列表
    public  LinkedList<Student> sturead() throws IOException, ClassNotFoundException {
        Path path = Paths.get("S:\\PROJECT");
        LinkedList<File> arr = new LinkedList<>();//存放学生路径
        String glob = "glob:**/*.txt";
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(file))
                    arr.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }
        });
//        System.out.println(arr);
//        System.out.println(arr.size());

        LinkedList<Student> students = new LinkedList<>();
        for (File p:arr){
//            System.out.println(arr.get(i));
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(p));
//            System.out.println(arr.get(i).toFile());
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
    public void show10(LinkedList<Student> arr) throws InterruptedException, IOException, ClassNotFoundException {
        for (Student s : arr) {
            System.out.println(s);
        }
        check(scanner());
    }


    public void check(String c) throws IOException, ClassNotFoundException, InterruptedException {
        switch (c) {
            case "Y": {
                clear();
                show10(sturead());
            }
            ;
            break;
            case "N": {
                clear();
                new Display();
            }
            ;
            break;
            default: {
                clear();
                System.out.println("请重新输入");
                check(scanner());
            }
            ;
            break;
        }
    }

    @Override
    public String scanner() {
        System.out.println("是否继续查看？ 是=Y,不是=N");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return next;
    }

    @Override
    public void clear() {
        System.out.println("\n\n\n\n\n\n\n");
    }
}
