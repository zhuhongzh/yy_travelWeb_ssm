package com.itzh.colltroller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itzh.domain.Note;
import com.itzh.domain.Scenery;
import com.itzh.service.ISceneryService;
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
@RequestMapping("/scenery")
public class SceneryController {
    @Resource(name = "sceneryService")
    private ISceneryService sceneryService;

    @RequestMapping("/findSceneryById")
    public void findSceneryById(HttpServletResponse response,int sceneryId) throws IOException {
        Scenery scenery = sceneryService.findSceneryById(sceneryId);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "未查找到该景点");
        dataMap.put("data", scenery);
        if (scenery != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }

    @RequestMapping("/findSceneryByRand")
    public void findSceneryByRand(HttpServletResponse response) throws IOException {
        List<Scenery> sceneryList = sceneryService.findSceneryByRand();
        print(sceneryList,response);
    }

    @RequestMapping("/findScenery")
    public void findScenery(HttpServletResponse response,String content) throws IOException {
        List<Scenery> sceneryList = sceneryService.findScenery(content);
        print(sceneryList,response);
    }

    @RequestMapping("/findSceneryByTag")
    public void findSceneryByTag(HttpServletResponse response,int tagId) throws IOException {
        List<Scenery> sceneryList = sceneryService.findSceneryByTagId(tagId);
        print(sceneryList,response);
    }

    @RequestMapping("/findCollectedScenery")
    public void findCollectedScenery(HttpServletResponse response,int id) throws IOException {
        List<Scenery> sceneryList = sceneryService.findCollectedSceneryById(id);
        print(sceneryList,response);
    }

    @RequestMapping("/findAllScenery")
    public void findAllScenery(HttpServletResponse response) throws IOException {
        List<Scenery> sceneryList = sceneryService.findAllScenery();
        print(sceneryList,response);
    }

    //设置response编码
    public Writer setResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        return out;
    }
    //将notelist在页面输出为json格式
    public void print(List<Scenery> sceneryList, HttpServletResponse response) throws IOException {
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        if(sceneryList == null || sceneryList.size() == 0){
            dataMap.put("status","failure");
            dataMap.put("reason","当前景区数量为0");
            dataMap.put("data",sceneryList);
        }else {
            dataMap.put("size",sceneryList.size());
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
            dataMap.put("data", sceneryList);
        }
//        JSONObject jsonObject = new JSONObject(dataMap);
        String data = JSONObject.toJSONString(dataMap, SerializerFeature.DisableCircularReferenceDetect);
        out.write(data);
//        out.write(jsonObject.toJSONString());
    }
}
