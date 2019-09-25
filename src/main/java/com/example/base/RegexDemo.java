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
        regexDemo.test_6();
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

    private void test_5(){

        String regex = "^[{].*";

        String input = "{\\\\rtf1\\\\ansi\\\\ansicpg936\\\\deff0\\\\deflang1033\\\\deflangfe2052{" +
                "\\\\fonttbl{\\\\f0\\\\fmodern\\\\fprq6\\\\fcharset134 \\\\'cb\\\\'ce\\\\'cc\\\\'e5;}" +
                "{\\\\f1\\\\fnil\\\\fcharset0 Arial;}}\\r\\n\\\\viewkind4\\\\uc1\\\\pard\\\\lang2052\\\\f0\\\\fs18 1" +
                " \\\\'c4\\\\'e3\\\\'ba\\\\'c3\\\\par\\r\\n2 \\\\f1 hello\\\\fs20\\\\par\\r\\n}";


        Pattern compile = Pattern.compile(regex);

        Matcher m = compile.matcher(input);
        while(m.find()){
            String group = m.group();
//            System.out.println(group);
            String[] strings = group.split("[{]");
            for(int index = 0 ;index < strings.length; index++){
                System.out.println(strings[index]);
            }

        }

    }

    private void test_6(){
//        String msg = "\r\n下面的样品检测项目被\r\n用户名:service\r\n检测项目:有机氯农药\r\n质控批次:Qb19052504\r\n" +
//                "样品代码:LETC1905011.01, LETC1905011.02, LETC1905011.03, LETC1905011.04, LETC1905011.05, LETC1905011.06," +
//                " LETC1905011.07, LETC1905011.08, LETC1905011.09, LETC1905011.10, LETC1905011.11\r\nQCType:DUP, MB," +
//                " MS\r\n项目名称:上海方展消防科技有限公司水土监测调查\r\n退回原因:0已发送邮件\",\n";
        String msg = "SR19071101 细节被变更。详情请查看监测方案变更报告。已发送邮件";
        String filter_1 = msg.replaceAll("\\r\\n", " ");
        String regex = "样品代码.*?项目名称";
        String replace = "样品代码：见邮件详情 项目名称";
        String res = filter_1.replaceAll(regex, replace);

        String regex_1 = "退回原因.*";
        String replace_1 = "";

        String regex_3 = "质控批次.*" ;



        Pattern compile = Pattern.compile(regex_3);

        Matcher m = compile.matcher(msg);
        if (m.find()){
            System.out.println(m.group());
        }else {
            compile = Pattern.compile(".*细节被变更");
            m = compile.matcher(msg);
            if (m.find()){
                System.out.println(m.group());
            }
        }
        while(m.find()) {
            String group = m.group();
//            group.replaceAll("样品代码","");
            System.out.println(group==null);
            System.out.println(group.equals(""));
            System.out.println(group);
        }
//        String res_1 = res.replaceAll(regex_1,replace_1);
//        System.out.println(res_1);

    }


}
