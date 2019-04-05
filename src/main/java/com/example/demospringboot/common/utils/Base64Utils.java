package com.example.demospringboot.common.utils;

import sun.misc.BASE64Encoder;

import java.io.*;
/**
 * @author: zhangf
 * @description:
 * @date: 2018/9/28
 */
public class Base64Utils {
    /**
    
     *@描述 获得图片的base64码
    
     *@参数  [src] 图片的绝对路径
    
     *@返回值  java.lang.String 图片的base64码
    
     *@创建人  zhangf
    
     *@创建时间  2018/9/29 11:40
    
     *@修改人和其它信息
    
     */
    public static String getImageBase(String src) {
        if(src==null||src==""){
            return "";
        }
        File file = new File(src);
        if(!file.exists()) {
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
