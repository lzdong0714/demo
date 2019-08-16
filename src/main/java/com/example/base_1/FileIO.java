package com.example.base_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2019年08月15日 11:53:00
 */
public class FileIO {
    Logger logger = LoggerFactory.getLogger(getClass());

    File file = getFile();
    String filePath = getFilePath();
    String outPath = "D:\\workproject\\idea\\demo\\src\\main\\resources\\data_test\\mail_uid_out.txt";


    private void BytesIODemo(){

        try {
            //读取文件(字节流)
            InputStream in = new FileInputStream(file);
            //写入相应的文件
            OutputStream out = new FileOutputStream(outPath);
            //读取数据
            //一次性取多少字节
            byte[] bytes = new byte[2048];
            //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
            int n = -1;
            //循环取出数据
            while ((n = in.read(bytes, 0, bytes.length)) != -1) {
                //转换成字符串
                String str = new String(bytes, 0, n, "GBK");//#这里可以实现字节到字符串的转换，比较实用
                System.out.println(str);
                //写入相关文件
                out.write(bytes, 0, n);
            }

            //关闭流
            in.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void bufferByteIODemo(){
        try {
//            File file = ResourceUtils.getFile("classpath:sqlserver/alarm/mail_uid.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath)));
            String s = reader.readLine();
            int record_uid = Integer.parseInt(s);
            System.out.println(record_uid);
            reader.close();
            PrintWriter out = new PrintWriter(new FileWriter(outPath));
            out.print(record_uid + 1);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            logger.info("读取邮件缓存ID记录的临时文件不存在");
            e.printStackTrace();
        }catch (IOException e){
            logger.info("读取邮件缓存ID记录的读取错误");
        }finally {
        }

    }


    private void printerDemo(){
        try {
            //读取文件(字节流)
            Reader in = new InputStreamReader(new FileInputStream(filePath), "utf-8");
            //写入相应的文件
            PrintWriter out = new PrintWriter(new FileWriter(outPath));
            //读取数据
            //循环取出数据

            //清楚缓存
            out.flush();
            //关闭流
            in.close();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private File getFile(){

        String filePath = "D:\\workproject\\idea\\demo\\src\\main\\resources\\data_test\\mail_uid.txt";
        File file = new File(filePath);
        return file;

    }

    private String getFilePath(){
        String filePath = "D:\\workproject\\idea\\demo\\src\\main\\resources\\data_test\\mail_uid.txt";
        return filePath;

    }

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
//        fileIO.BytesIODemo();
        fileIO.bufferByteIODemo();
//        fileIO.printerDemo();
    }
}
