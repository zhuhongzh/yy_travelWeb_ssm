package com.itzh.domain;

public class UserToScenery {
    private int id;
    private int userId;
    private int sceneryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserToScenery{" +
                "id=" + id +
                ", userId=" + userId +
                ", sceneryId=" + sceneryId +
                '}';
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }
}
