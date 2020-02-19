package com.itzh.colltroller;

import com.alibaba.fastjson.JSONObject;
import com.itzh.domain.UserToScenery;
import com.itzh.service.IUserToSceneryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/collcet")
public class UserToSceneryController {

    @Resource(name = "userToSceneryService")
    private IUserToSceneryService userToSceneryService;

    @RequestMapping("/save")
    public void saveUserToScenery(HttpServletResponse response, int userId, int sceneryId) throws IOException {
        UserToScenery userToScenery = userToSceneryService.saveUserToScenery(userId,sceneryId);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        if(userToScenery != null){
            dataMap.put("status", "success");
            dataMap.put("reason", "保存成功");
            dataMap.put("data", userToScenery);
        }else {
            dataMap.put("status", "success");
            dataMap.put("reason", "删除成功");
            dataMap.put("data", userToScenery);
        }
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }


    @RequestMapping("/delete")
    public void deleteUserToScenery(HttpServletResponse response, int userId, int sceneryId) throws IOException {
        userToSceneryService.deleteUserToScenery(userId,sceneryId);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "success");
        dataMap.put("reason", "成功");
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }

    //设置response编码
    public Writer setResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        return out;
    }
}
