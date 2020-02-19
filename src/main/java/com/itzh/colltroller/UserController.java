package com.itzh.colltroller;

import com.alibaba.fastjson.JSONObject;
import com.itzh.domain.User;
import com.itzh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAllUsers")
    public void findAll(HttpServletResponse response) {
        try {
            List<User> userList = userService.findAllUsers();
            response.setCharacterEncoding("UTF-8"); //设置服务器的编码，默认是ISO-8859-1
            response.setContentType("text/html;character=utf-8"); //告诉浏览器服务器的编码格式
            Writer out = response.getWriter();
            Map<String, Object> dataMap = new HashMap<>();
            if (userList == null || userList.size() == 0 || userList.isEmpty()) {
                dataMap.put("status", "failure");
                dataMap.put("reason", "当前用户数量为0");
                dataMap.put("data", userList);
            } else {
                dataMap.put("status", "success");
                dataMap.put("reason", "成功");
                dataMap.put("data", userList);
            }
            JSONObject jsonObject = new JSONObject(dataMap);
            out.write(jsonObject.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/saveUser")
    public void saveUser(HttpServletResponse response, @RequestParam Map<String, String> map) {
        User user = new User();
        user.setUserName(map.get("userName"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setAvatar("http://49.232.144.185:8080/picture/avaterimgs/20200115_22523827.jpg");
        user.setSex(map.get("sex"));
        user.setBirthday(map.get("birthday"));
        user.setPhone(map.get("phone"));
        try {
            User data = userService.saveUser(user);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;character=utf-8");
            Writer out = response.getWriter();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("status", "failure");
            dataMap.put("reason", "电话或者邮箱已经被注册");
            dataMap.put("data", data);
            if (data != null) {
                dataMap.put("status", "success");
                dataMap.put("reason", "成功");
            }
            JSONObject json = new JSONObject(dataMap);
            out.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateUser")
    public void updateUser(HttpServletResponse response, @RequestParam Map<String, String> map) {
        User user = new User();
        user.setAvatar(map.get("avatar"));
        user.setUserName(map.get("userName"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setSex(map.get("sex"));
        user.setBirthday(map.get("birthday"));
        user.setPhone(map.get("phone"));
        try {
            User data = userService.updateUser(user);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;character=utf-8");
            Writer out = response.getWriter();

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("data", data);
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");

            JSONObject json = new JSONObject(dataMap);
            out.write(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findUserByPhone")
    public void findUserByPhone(HttpServletResponse response, String phone) throws IOException {
        User user = userService.findUserByPhone(phone);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "未查找到该用户");
        dataMap.put("data", user);
        if (user != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }

    @RequestMapping("/findUserById")
    public void findUserById(HttpServletResponse response, int id) throws IOException {
        User user = userService.findUserById(id);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;character=utf-8");
        Writer out = response.getWriter();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "未查找到该用户");
        dataMap.put("data", user);
        if (user != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject jsonObject = new JSONObject(dataMap);
        out.write(jsonObject.toJSONString());
    }
}
