package com.example.lab18;

public class Item {
    private String id, title;
    private Integer like;

    public Item(String id, String title, Integer like) {
        this.id = id;
        this.title = title;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
