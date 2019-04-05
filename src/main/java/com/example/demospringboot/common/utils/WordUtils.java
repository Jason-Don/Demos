package com.example.demospringboot.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author: zhangf
 * @description:
 * @date: 2018/9/28
 */
public class WordUtils {
    private static Configuration configuration = null;
    //这里注意的是利用WordUtils的类加载器动态获得模板文件的位置
    private static final String templateFolder = "/wordTemplate";
    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        try {
            //configuration.setDirectoryForTemplateLoading(new File(templateFolder));
            configuration.setClassForTemplateLoading(WordUtils.class,templateFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map,String title,String ftlFile) throws IOException {
        Template freemarkerTemplate = configuration.getTemplate(ftlFile);
        File file = null;
        InputStream inputStream = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类的createDoc方法生成Word文档
            file = createDoc(map,freemarkerTemplate);
            inputStream = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            String fileName = title+DateUtils.formatDATE_TIME_PATTERN2(new Date()) + ".doc";
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

            out = response.getOutputStream();
            // 缓冲区
            byte[] buffer = new byte[1024];
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while((bytesToRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(out != null){
                out.close();
            }
            if(file != null){
                file.delete(); // 删除临时文件
            }
        }
    }

    private static File createDoc(Map<?, ?> dataMap, Template template) {
        String name =  "sellPlan.doc";
        File f = new File(name);
        Template t = template;
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }
}