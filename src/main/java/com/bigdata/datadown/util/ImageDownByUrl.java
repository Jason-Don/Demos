package com.bigdata.datadown.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangf
 * @description:通过URL地址下载页面上的图片
 * @date: 2018/12/3
 */
public class ImageDownByUrl {
    static final String[] PICTURE = {".jpg",".jpeg",".png",".gif",".bmp"};
    static final String PREFIX = "=\"";
    static final String OUTPUT_PATH = "D:/temp/";

    public static String getHTML(String urlAddress,String encoding){
        InputStreamReader inputStreamReader = null;
        StringBuilder sb = null;
        try {
            //创建URL链接
            URL url = new URL(urlAddress);
            //打开链接
            URLConnection urlConnection = url.openConnection();
            //建立输入流
            InputStream inputStream = urlConnection.getInputStream();
            //将字节流转换为字符流的桥梁。若不指定字符集编码，该解码过程将使用平台默认的字符编码，如：GBK。
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            //缓存
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //读取
            String temp = null;
            sb = new StringBuilder();
            while((temp = bufferedReader.readLine()) != null){
                sb.append(temp+"\n");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally{
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                    inputStreamReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return sb.toString();
    }

    public static List<String> getImageAddress(String htmlString){
        List<String> urlList = new ArrayList<String>(16);
        for(String suffix : PICTURE){
            String html = new String(htmlString);
            while(html.length()>0){
                //后缀字符串索引位置
                int index = html.indexOf(suffix);
                //若(等于-1)小于0,则没有该字符串
                if(index < 0){
                    break;
                }
                //后缀字符串 第一次出现的 末尾索引
                int suffix_end_index = index + suffix.length();
                //后缀字符串且包含后缀字符串前的字符串
                String str= html.substring(0, suffix_end_index);
                //前缀字符串 末尾索引
                int suffix_start_index = str.lastIndexOf(PREFIX) + PREFIX.length();
                //截取出 以后缀字符串suffix结尾的字符串（图片url地址）
                String imageAddress = str.substring(suffix_start_index);
                //存放
                urlList.add(imageAddress);
                //后缀字符串第一次出现位置的后面字符串
                html = html.substring(suffix_end_index);
            }
        }
        return urlList;
    }

    public static void downImage(List<String> urlList){
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
              try {
                for(String urlAddress : urlList){
                    URL url = new URL("http:"+urlAddress);
                    InputStream inputStream = url.openStream();
                    dataInputStream = new DataInputStream(inputStream);
                    int index = urlAddress.lastIndexOf("/");
                    String fileName = urlAddress.substring(index);
                    String filePath = OUTPUT_PATH.concat(fileName);

                    fileOutputStream = new FileOutputStream(new File(filePath));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    byte[] b = new byte[1024];
                    int length = 0;
                    while ( (length = dataInputStream.read(b)) > 0){
                        byteArrayOutputStream.write(b,0,length);
                    }
                    byte[] context = byteArrayOutputStream.toByteArray();
                    fileOutputStream.write(context);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                  if(dataInputStream != null){
                      try {
                          dataInputStream.close();
                          dataInputStream = null;
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }

                  if(fileOutputStream != null){
                      try {
                          fileOutputStream.close();
                          fileOutputStream = null;
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }

              }
    }
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        String url = "https://www.qq.com/";
        String encoding = "GBK";

        String htmlString = getHTML(url, encoding);
        List<String> stringList = getImageAddress(htmlString);

        long endTime = System.currentTimeMillis();

        System.out.println("爬取图片地址耗时："+String.valueOf( endTime - startTime)+"ms");

//        System.out.println(stringList);

        downImage(stringList);
        long downEndTime = System.currentTimeMillis();
        System.out.println("下载耗时："+String.valueOf( downEndTime - endTime)+"ms");
    }
}
