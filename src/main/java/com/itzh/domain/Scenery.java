package com.itzh.domain;

import java.util.List;

public class Scenery {
    private int sceneryId;
    private String sceneryName;
    private String sceneryDescription;
    private double sceneryPrice;
    private String sceneryLocation;
    private int sceneryTagId;
    private SceneryTag sceneryTag;
    private List<SceneryImg> sceneryImgs;
    private List<Goods> goodsList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getSceneryTagId() {
        return sceneryTagId;
    }

    public void setSceneryTagId(int sceneryTagId) {
        this.sceneryTagId = sceneryTagId;
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }

    public String getSceneryName() {
        return sceneryName;
    }

    public void setSceneryName(String sceneryName) {
        this.sceneryName = sceneryName;
    }

    public String getSceneryDescription() {
        return sceneryDescription;
    }

    public void setSceneryDescription(String sceneryDescription) {
        this.sceneryDescription = sceneryDescription;
    }

    public double getSceneryPrice() {
        return sceneryPrice;
    }

    public void setSceneryPrice(double sceneryPrice) {
        this.sceneryPrice = sceneryPrice;
    }

    public String getSceneryLocation() {
        return sceneryLocation;
    }

    public void setSceneryLocation(String sceneryLocation) {
        this.sceneryLocation = sceneryLocation;
    }

    public SceneryTag getSceneryTag() {
        return sceneryTag;
    }

    public void setSceneryTag(SceneryTag sceneryTag) {
        this.sceneryTag = sceneryTag;
    }

    public List<SceneryImg> getSceneryImgs() {
        return sceneryImgs;
    }

    public void setSceneryImgs(List<SceneryImg> sceneryImgs) {
        this.sceneryImgs = sceneryImgs;
    }

    @Override
    public String toString() {
        return "Scenery{" +
                "sceneryId=" + sceneryId +
                ", sceneryName='" + sceneryName + '\'' +
                ", sceneryDescription='" + sceneryDescription + '\'' +
                ", sceneryPrice=" + sceneryPrice +
                ", sceneryLocation='" + sceneryLocation + '\'' +
                ", sceneryTag='" + sceneryTag + '\'' +
                ", sceneryImgs=" + sceneryImgs +
                '}';
    }
}
