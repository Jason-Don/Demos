package com.bigdata.datadown.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: zhangf
 * @description:通过URL抓取数据
 * @date: 2018/12/1
 * <br>
 * <a href="http://www.baidu.com">Demo</a>
 */
public class DataDownByUrl {

    public static String getHtmlByUrl(String urlAddress,String encoding) {
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = null;
        try{
            // 一、通过网址获取 影评
            // 1、建立网络链接
            URL url = new URL(urlAddress);
            // 2、打开链接
            URLConnection urlConnection = url.openConnection();
            // 3、将源码下载到本地 IO流 输入流 和 输出流 相对的（喝雪碧）

            //建立管道
            //建立输入流
            inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),encoding);
            //缓存
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //创建临时文件
            String temp = null;
            stringBuffer = new StringBuffer();
            //读取
            while((temp = bufferedReader.readLine())!=null){
                stringBuffer.append(temp+"\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                    //JVM 优先回收null
                    inputStreamReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws IOException {
        String urlAddress = "https://movie.douban.com/subject/27110296/?from=showing";
        String encoding = "utf8";
        String htmlByUrl = getHtmlByUrl(urlAddress, encoding);
//        System.out.printf(stringBuffer.toString());报错

        System.out.println(htmlByUrl);
    }

}
