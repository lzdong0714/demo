package com.example.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月21日 16:25:00
 */
public class MainShow {


    private void getClass_test() throws ClassNotFoundException {
            Class<?> studentClass_1 = Class.forName("com.example.reflection.Student");

//      方法区：类名.class
        Class<Student> studentClass_2 = Student.class;


//       内存堆：
        Student student = new Student();
        Class<? extends Student> studentClass_3 = student.getClass();

        boolean b = (studentClass_1==studentClass_2) && (studentClass_2 == studentClass_3);

        System.out.println(b);
    }

    private void field_test() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> studentClass = Student.class;

        //获取所有的变量名称
        Field[] declaredFields = studentClass.getDeclaredFields();

        //获取public的变量名称
        Field[] fields = studentClass.getFields();

        Field name = studentClass.getDeclaredField("name");

        Student obj = new Student();
        obj.setAge(11);
        obj.setName("lolo");

//        忽略安全检查
        name.setAccessible(true);
        String o = (String)name.get(obj);
        System.out.println(o);

        name.set(obj, "lol");
        System.out.println(obj.getName());

    }

    public void test_construct() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Student> studentClass = Student.class;
        Constructor<?>[] constructors = studentClass.getConstructors();

        Constructor<?>[] declaredConstructors = studentClass.getDeclaredConstructors();

        Constructor<Student> cons2 = studentClass.getConstructor();

        Constructor<Student> con4 = studentClass.getDeclaredConstructor(String.class, int.class);

        Student lzd = cons2.newInstance();
        System.out.println(lzd);

        con4.setAccessible(true);
        Student student = con4.newInstance("lzd", 30);
        System.out.println(student);
    }


    public void test_method() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        /**获取类对象*/
        Class studentClass = Student.class;

// 1. 获取public修饰的所有方法对象列表【public 包含继承的】
        Method[] meLis1 = studentClass.getMethods();

// 2. 获取public修饰的指定方法签名的方法对象
        Method me1 = studentClass.getMethod("getName",String.class);
        Student stu1 = (Student) studentClass.getConstructor(String.class, int.class)
                .newInstance("杰克", 18);
        System.out.println(me1.invoke(stu1, "这是一个公共方法！"));

// 3. 获取该对象表示的类的所有类型的方法对象，但是不包括继承的！
        Method[] meList2 = studentClass.getDeclaredMethods();

// 4. 获取指定方法签名的方法对象
        Method me2 = studentClass.getDeclaredMethod("getAge",int.class);

// 5.暴力反射
        me2.setAccessible(true);

// 6.执行方法
        me2.invoke(stu1, 666);

    }

    public void anno_test(){
        Class<Student> studentClass = Student.class;
        Annotation[] annotations = studentClass.getAnnotations();
        for (int index = 0; index < annotations.length; index++){
            Annotation annotation = annotations[index];

        }
    }

    public static void main(String[] args) {
        MainShow mainShow = new MainShow();
        try {
            System.out.println("---------test 1 ---------");
            mainShow.field_test();
            System.out.println("---------END test 1 ---------");
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        try {
            System.out.println("---------test 2 ---------");
            mainShow.getClass_test();
            System.out.println("---------end test 2 ---------");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("---------test 3 ---------");
            mainShow.test_construct();
            System.out.println("---------end test 3 ---------");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        ;
    }
}
