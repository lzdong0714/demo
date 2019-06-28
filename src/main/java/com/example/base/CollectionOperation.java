package com.example.base;



import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 集合运算的演示,以及深拷贝，浅拷贝的方式
 */
public class CollectionOperation {

    public static void main(String[] args) {
        CollectionOperation collectionOperation = new CollectionOperation();
        collectionOperation.test_1();
        collectionOperation.test_2();

    }

    List<String> list=new LinkedList<>();

    public void test_1(){
        String[] strArr ={"a","b","c"};
        for (String str:strArr){
            list.add(str);
        }

        List<String> stringList = new LinkedList<>();
        stringList.addAll(list);

        List<String> tmpList = new ArrayList<>();
        tmpList =depCopy(stringList);
        stringList.add("d");

        System.out.println(tmpList);

        tmpList.retainAll(list);
        System.out.println(tmpList);

        tmpList.removeAll(list);

        System.out.println(stringList);

        list.removeAll(stringList);
        System.out.println(list);

        System.out.println("++++++++++++++++++++++++++++++++++");
    }


    public void test_2(){
        List<Pointer> pointerList = new LinkedList<>();
        String[] names= {"a","b","c"};
        Random random = new Random();
        for (String name:names) {
            double random_x = random.nextDouble()*10-5;
            double random_y = random.nextDouble()*10-5;
            pointerList.add(new Pointer(name, random_x,random_y));
        }

        List<Pointer> copyOfPointerList = depCopy(pointerList);

        pointerList.add(new Pointer("X",100.0,100.0));

        copyOfPointerList.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++");

        pointerList.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++");

        List<Pointer> cloneByStream = cloneByStream(pointerList);
        cloneByStream.removeAll(copyOfPointerList);
        cloneByStream.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++");

        pointerList.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++");

    }

    public static <T> List<T> depCopy(List<T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>) inStream.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T cloneByStream(T obj) {
        if (null == obj || false == (obj instanceof Serializable)) {
            return null;
        }
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            out.flush();
            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
            return (T) in.readObject();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            close(out);
        }
        return null;
    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }

}
