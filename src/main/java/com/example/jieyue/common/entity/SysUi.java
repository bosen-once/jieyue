package com.example.jieyue.common.entity;

public class SysUi {
    private int id;
    private String url;
    private int width;
    private int height;

    @Override
    public String toString() {
        return "SysUi{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
