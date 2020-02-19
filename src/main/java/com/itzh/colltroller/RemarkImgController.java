package com.itzh.colltroller;


import com.alibaba.fastjson.JSONObject;
import com.itzh.domain.Scenery;
import com.itzh.service.IRemarkImgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/remarkImg")
public class RemarkImgController {
    @Resource(name = "remarkImgService")
    private IRemarkImgService remarkImgService;


    @RequestMapping("/save")
    public void saveRemarkImg(HttpServletResponse response,
                              @RequestParam("imgUrl")List<String> imgUrls,
                              @RequestParam("remarkId")List<String> remarkIds) throws IOException {
            int size = imgUrls.size();
            for (int i = 0; i < size; i++) {
                remarkImgService.saveRemarkImg(imgUrls.get(i), Integer.parseInt(remarkIds.get(i)));
            }
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
            Writer out = setResponse(response);
            JSONObject json = new JSONObject(dataMap);
            out.write(json.toJSONString());
    }

    //设置response编码
    public Writer setResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        return out;
    }
}
