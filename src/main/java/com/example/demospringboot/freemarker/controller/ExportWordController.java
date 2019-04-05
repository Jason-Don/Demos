package com.example.demospringboot.freemarker.controller;

import com.example.demospringboot.common.utils.Base64Utils;
import com.example.demospringboot.common.utils.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangf
 * @description:
 * @date: 2018/9/28
 */
@Controller
@RequestMapping("/freemarker/export_word")
public class ExportWordController {
    @GetMapping("/index")
    public String index(){
        return "freemarker/export_word";
    }

    @RequestMapping("/getWord")
    public void getWord(HttpServletRequest request, HttpServletResponse response, Model model){
        String hzName = request.getParameter("hzName");
        String hzSex = request.getParameter("hzSex");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hzName",hzName);
        map.put("hzSex",hzSex);

        String imageBase = Base64Utils.getImageBase("C:\\Users\\Administrator\\Desktop\\信息核查1.png");
        map.put("image",imageBase);
        try {
            WordUtils.exportMillCertificateWord(request,response,map,"患者_"+hzName+"_肇事肇祸等严重精神障碍患者信息采集表",
                    "肇事肇祸等严重精神障碍患者信息采集表.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
