package com.example.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Liu Zhendong
 * @Description 正则表达式的匹配与切割
 * @createTime 2019年07月31日 14:38:00
 */
public class RegexDemo {

    public static void main(String[] args) {
        RegexDemo regexDemo = new RegexDemo();
        regexDemo.test_4();
    }


    private void test_1(){
        String str = "<span>这些需要替换掉，请替换成：哈哈哈</span>这些不要替换<span>这些需要替换掉</span>";
        String regex1 = "<span>.*?</span>";
        String regex2 = "<span>.*</span>";

        String newStr_1 = str.replaceAll(regex1,"hello");
        String newStr_2 = str.replaceFirst(regex1,"hello");
        String newStr_3 = str.replaceAll(regex2,"good");

        System.out.println(newStr_1);
        System.out.println(newStr_2);

    }

    private void test_3(){
        String str = "我们的网址是：http://blog.csdn.net/w496272885，快来看看吧";
        /*
         * .表示任意字符，*表示零个或多个，默认使用了贪婪模式。
         * \d表示任意数字，也表示截取的最后一位是数字
         * 由于\在正则表达式中为特殊字符，所有需要转义，所以使用了\\d
         */
        String regex = "http.*\\d";
        //这里将正则表达式编译并赋给Pattern类
        Pattern pattern = Pattern.compile(regex);
        //使用Matcher去保存匹配到的内容，matcher()方法去匹配字符串
        Matcher matcher = pattern.matcher(str);
        //matcher.find()判断是否有对象,可能匹配到多个，所以使用while循环
        while(matcher.find()){
            System.out.println(matcher.group());
        }

    }

    private void test_2(){
        String raw = "7天";

        String regex = "(\\d)";

        Pattern compile = Pattern.compile(regex);

        Matcher matcher = compile.matcher(raw);

        while (matcher.find()){
            String group = matcher.group();
            int dayInt = Integer.parseInt(group);
            System.out.println(dayInt);
        }

        String replaceAll = matcher.replaceAll("-");

        System.out.println(replaceAll);

    }

    private void test_4(){
        String regex = "a*b";

        String input = "aabfooaabfooabfoobkkk";

        String replace = "-";

        Pattern compile = Pattern.compile(regex);

        Matcher m = compile.matcher(input);

        StringBuffer buffer = new StringBuffer();
        int count = 0;
        while(m.find()){

            String group = m.group();
            int start = m.start();
            System.out.println("asssert 3 ,result is :" + start);
            m.appendReplacement(buffer,replace);
            count++;
            System.out.println(" Match number "+count);
            System.out.println(" start() "+m.start());
            System.out.println(" end() "+m.end());
        }
        m.appendTail(buffer);
        System.out.println(buffer.toString());
    }

}
