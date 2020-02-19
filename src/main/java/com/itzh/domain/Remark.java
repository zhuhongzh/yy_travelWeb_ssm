package com.itzh.domain;

import java.util.List;

public class Remark {
    private int remarkId;
    private String remarkTitle;
    private String remarkContent;
    private String remarkTime;
    private int sceneryId;
    private int userId;
    private User user;
    private String sceneryName;
    private List<RemarkImg> remarkImgList;

    public List<RemarkImg> getRemarkImgList() {
        return remarkImgList;
    }

    public void setRemarkImgList(List<RemarkImg> remarkImgList) {
        this.remarkImgList = remarkImgList;
    }

    public int getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(int remarkId) {
        this.remarkId = remarkId;
    }

    public String getRemarkTitle() {
        return remarkTitle;
    }

    public void setRemarkTitle(String remarkTitle) {
        this.remarkTitle = remarkTitle;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getRemarkTime() {
        return remarkTime;
    }

    public void setRemarkTime(String remarkTime) {
        this.remarkTime = remarkTime;
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }
}
