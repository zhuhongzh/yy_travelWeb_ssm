package com.itzh.colltroller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itzh.domain.Note;
import com.itzh.domain.Remark;
import com.itzh.domain.RemarkImg;
import com.itzh.service.IRemarkImgService;
import com.itzh.service.IRemarkService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/remark")
public class RemarkController {

    @Resource(name = "remarkService")
    private IRemarkService remarkService;
    @Resource(name = "remarkImgService")
    private IRemarkImgService remarkImgService;

    @RequestMapping("/findRemarkByRand")
    public void findRemarkByRand(HttpServletResponse response) throws IOException {
        List<Remark> remarkList = remarkService.findRemarkByRand();
        print(remarkList, response);
    }

    @RequestMapping("/saveRemark")
    public void saveRemark(HttpServletResponse response, HttpServletRequest request,Remark remark,
                           @RequestParam("file") MultipartFile[] files) throws IOException {
        Remark data = remarkService.saveRemark(remark);

        List<RemarkImg> remarkImgList = new ArrayList<>();
        String path = request.getSession().getServletContext().getRealPath("\\").
                substring(0, request.getSession().getServletContext().getRealPath("\\").lastIndexOf("\\"))
                + "\\picture" + "\\remarkimgs\\";
        if(files != null && files.length != 0){
            int size = files.length;
            for (int i = 0; i < size; i++) {
                MultipartFile file = files[i];
                if(file != null){
                    String type = file.getOriginalFilename().substring(file.getOriginalFilename()
                            .lastIndexOf(".") + 1, file.getOriginalFilename().length());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    Random r = new Random();
                    String imgName = "";
                    imgName = getString(type, sdf, r, imgName);
                    //将文件流写入到磁盘中
//                    FileUtils.writeByteArrayToFile(new File(path + imgName), file.getBytes());
                    file.transferTo(new File(path+ imgName));


                    remarkImgService.saveRemarkImg("http://49.232.144.185:8080/picture/remarkimgs/"
                            + imgName,data.getRemarkId());
                }
            }
        }
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "失败");
        dataMap.put("data", data);
        dataMap.put("paths",remarkImgList);
        if (data != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject json = new JSONObject(dataMap);
        out.write(json.toJSONString());
    }

    static String getString(String type, SimpleDateFormat sdf, Random r, String imgName) {
        if ("jpg".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpg";
        } else if ("png".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".png";
        } else if ("jpeg".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".jpeg";
        } else if ("gif".equals(type)) {
            imgName = sdf.format(new Date()) + r.nextInt(100) + ".gif";
        }
        return imgName;
    }

    //将notelist在页面输出为json格式
    public void print(List<Remark> remarkList, HttpServletResponse response) throws IOException {
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        if (remarkList == null || remarkList.size() == 0) {
            dataMap.put("status", "failure");
            dataMap.put("reason", "当前热点数量为0");
            dataMap.put("data", remarkList);
        } else {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
            dataMap.put("data", remarkList);
        }
        String data = JSONObject.toJSONString(dataMap, SerializerFeature.DisableCircularReferenceDetect);
        out.write(data);
    }

    //设置response编码
    public Writer setResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        return out;
    }
}
