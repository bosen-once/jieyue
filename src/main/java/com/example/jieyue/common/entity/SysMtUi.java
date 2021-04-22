package com.example.jieyue.common.entity;

public class SysMtUi {
    private int id;
    private String url;
    private int width;
    private int height;
    private int merchant;

    @Override
    public String toString() {
        return "SysMtUi{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", merchant=" + merchant +
                '}';
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMerchant() {
        return merchant;
    }

    public void setMerchant(int merchant) {
        this.merchant = merchant;
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
