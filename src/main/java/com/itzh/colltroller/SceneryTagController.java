package com.itzh.colltroller;


import com.alibaba.fastjson.JSONObject;
import com.itzh.domain.SceneryTag;
import com.itzh.service.ISceneryTagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tag")
public class SceneryTagController {

    @Resource(name = "sceneryTagService")
    private ISceneryTagService sceneryTagService;

    @RequestMapping("/alltags")
    public void findAllSceneryTag(HttpServletResponse response) throws IOException {
        List<SceneryTag> sceneryTags = sceneryTagService.findAllSceneryTags();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        Map<String, Object> dataMap = new HashMap<>();
        if(sceneryTags == null || sceneryTags.size() == 0){
            dataMap.put("status","failure");
            dataMap.put("reason","当前种类数量为0");
            dataMap.put("data",sceneryTags);
        }else {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
            dataMap.put("data", sceneryTags);
        }
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }
}
