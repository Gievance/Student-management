package com.version1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Delete implements Clear {

    public void delete() throws IOException, ClassNotFoundException, InterruptedException {
        //通过HashMap将学号和文件联系起来
        System.out.println("----读取学生信息中----");
        Check check = new Check();
        for (Student s : check.sturead())
            System.out.println(s);
        LinkedList<Path> obj = getObj();//获取文件路径
        HashMap<Integer, String> map = getClas(obj);//学号和文件映射
        System.out.println("学号映射中.....");
        Thread.sleep(2000);
        System.out.println("_____学号映射完成_____");
        System.out.print("请输入要删除学生的学号： ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int sc = scanner.nextInt();
            Set<Integer> integers = map.keySet();
            for (Integer i : integers) {
                if (i == sc) {
                    String s = map.get(i);
                    if (deleteFile(s)) {
                        System.out.println("删除成功");
                        clear();
                        new Display();
                    } else {
                        System.out.println("删除失败");
                        new Delete().delete();
                    }
                }
            }
        } else {
            System.out.println("输入格式错误！！！！！");
            Thread.sleep(2000);
            clear();
            System.out.println("是否继续？ 是Y :否N");
            Scanner sc = new Scanner(System.in);
            check(sc.next());
        }

    }

    public void check(String code) throws InterruptedException, IOException, ClassNotFoundException {
        switch (code) {
            case "Y": {
                clear();
                new Delete().delete();
            }
            break;
            case "N": {
                clear();
                new Display();
            }
            break;
            default: {
                System.out.println("输入格式有误");
                new Display();
            }
        }
    }

    public HashMap<Integer, String> getClas(LinkedList<Path> arr) throws IOException, ClassNotFoundException {
        HashMap<Integer, String> map = new HashMap<>();
        HashMap<Integer, String> maps = new HashMap<>();
        for (Path p : arr) {
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(p.toFile()));
            Student o = (Student) obj.readObject();
//            System.out.println(o);//有效
            maps.put(o.id, p.getFileName().toString());
            obj.close();
        }
        return maps;
    }

    public LinkedList<Path> getObj() throws IOException {
        Path path = Paths.get("s:\\PROJECT");
        LinkedList<Path> arr = new LinkedList<>();
        String glob = "glob:**/*.txt";
        PathMatcher pm = FileSystems.getDefault().getPathMatcher(glob);
        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (pm.matches(file)) {
                    arr.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
//        for (Path p:arr)
//        System.out.println(p);//检查是否获取路径
        return arr;
    }

    //测试用的
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        LinkedList<Path> obj = getObj();
//        System.out.println(obj);
//
//        HashMap<Integer, String> clas = getClas(obj);
//        Set<Integer> integers = clas.keySet();
//        deleteFile("12.txt");
//    }
    public boolean deleteFile(String filename) {
        File file = new File(filename);
        return file.delete();


    }

    @Override
    public void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n");
    }
}
