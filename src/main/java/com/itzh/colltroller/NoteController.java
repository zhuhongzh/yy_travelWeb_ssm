package com.itzh.colltroller;


import com.alibaba.fastjson.JSONObject;
import com.itzh.domain.Note;
import com.itzh.service.INoteService;
import org.apache.ibatis.annotations.ResultMap;
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

import static java.lang.System.out;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Resource(name = "noteService")
    INoteService noteService;

    @RequestMapping("/findNoteByUserId")
    public void findNoteByUserId(HttpServletResponse response, int id) throws IOException {
        List<Note> noteList = noteService.findNoteByUserId(id);
        print(noteList, response);
    }

    @RequestMapping("/findNoteByState")
    public void findNoteByState(HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
        int id = Integer.valueOf(map.get("id"));
        int state = Integer.valueOf(map.get("state"));
        List<Note> noteList = noteService.findNoteByState(id, state);
        print(noteList, response);
    }

    @RequestMapping("/findNoteLimit")
    public void findNoteLimit(HttpServletResponse response, int num) throws IOException {
        List<Note> noteList = noteService.findNoteLimit(num);
        print(noteList, response);
    }

    @RequestMapping("/saveNote")
    public void saveNote(HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
        Note note = new Note();
        note.setNoteTitle(map.get("noteTitle"));
        note.setNoteContent(map.get("noteContent"));
        note.setNoteTime(map.get("noteTime"));
        note.setSceneryId(Integer.valueOf(map.get("sceneryId")));
        note.setState(Integer.valueOf(map.get("state")));
        note.setUserId(Integer.valueOf(map.get("userId")));

        Note data = noteService.saveNote(note);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "失败");
        dataMap.put("data", data);
        if (data != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject json = new JSONObject(dataMap);
        out.write(json.toJSONString());
    }

    @RequestMapping("/deleteNote")
    public void saveNote(HttpServletResponse response, int id) throws IOException {
        noteService.deleteNote(id);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("status", "success");
        dataMap.put("reason", "成功");

        JSONObject json = new JSONObject(dataMap);
        out.write(json.toJSONString());
    }

    @RequestMapping("/updateNote")
    public void updateNote(HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
        Note note = new Note();
        note.setNoteId(Integer.parseInt(map.get("noteId")));
        note.setNoteTitle(map.get("noteTitle"));
        note.setNoteContent(map.get("noteContent"));
        note.setNoteTime(map.get("noteTime"));
        note.setSceneryId(Integer.valueOf(map.get("sceneryId")));
        note.setState(Integer.valueOf(map.get("state")));
        note.setUserId(Integer.valueOf(map.get("userId")));

        Note data = noteService.updateNote(note);
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", "failure");
        dataMap.put("reason", "失败");
        dataMap.put("data", data);
        if (data != null) {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
        }
        JSONObject json = new JSONObject(dataMap);
        out.write(json.toJSONString());
    }

    //将notelist在页面输出为json格式
    public void print(List<Note> noteList, HttpServletResponse response) throws IOException {
        Writer out = setResponse(response);
        Map<String, Object> dataMap = new HashMap<>();
        if (noteList == null || noteList.size() == 0) {
            dataMap.put("status", "failure");
            dataMap.put("reason", "当前用户记事数量为0");
            dataMap.put("data", noteList);
        } else {
            dataMap.put("status", "success");
            dataMap.put("reason", "成功");
            dataMap.put("data", noteList);
        }
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
