package com.itzh.domain;

import java.util.List;

public class SceneryTag {
    private int tagId;
    private String tagName;
    private List<Scenery> sceneryList;

    public List<Scenery> getSceneryList() {
        return sceneryList;
    }

    public void setSceneryList(List<Scenery> sceneryList) {
        this.sceneryList = sceneryList;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "SceneryTag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", sceneryList=" + sceneryList +
                '}';
    }
}
