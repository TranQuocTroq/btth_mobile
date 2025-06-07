package com.example.lab14_2;

public class Item {
    private String code, title;
    private Integer like;

    public Item(String code, String title, Integer like) {
        this.code = code;
        this.title = title;
        this.like = like;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
