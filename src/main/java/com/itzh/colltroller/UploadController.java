package com.itzh.colltroller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/avater")
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           HttpServletResponse response,
                           HttpServletRequest request) throws Exception {
        response.setCharacterEncoding("UTF-8"); //设置服务器的编码，默认是ISO-8859-1
        response.setContentType("text/html;character=utf-8"); //告诉浏览器服务器的编码格式
        Writer out = response.getWriter();
        Map<String,Object> dataMap = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("\\").
                substring(0,request.getSession().getServletContext().getRealPath("\\").lastIndexOf("\\"))
                +"\\picture" + "\\avaterimgs\\";
        System.out.println(path);
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String imgName = "";
        if (file != null) {
            imgName = RemarkController.getString(type, sdf, r, imgName);
            dataMap.put("status", "success");
            dataMap.put("reason", "上传成功");
            dataMap.put("path","http://49.232.144.185:8080/picture/avaterimgs/" + imgName);
        }else {
            dataMap.put("status", "failure");
            dataMap.put("reason", "上传失败，没有上传图片文件");
        }
        //将文件流写入到磁盘中
        FileUtils.writeByteArrayToFile(new File(path + imgName), file.getBytes());
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }
}
