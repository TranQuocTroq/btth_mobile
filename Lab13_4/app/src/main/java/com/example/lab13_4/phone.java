package com.example.lab13_4;

public class phone {
    private String namephone;
    private int imagephone;

    public phone(String namephone, int imagephone) {
        this.namephone = namephone;
        this.imagephone = imagephone;
    }

    public String getNamephone() {
        return namephone;
    }

    public void setNamephone(String namephone) {
        this.namephone = namephone;
    }

    public int getImagephone() {
        return imagephone;
    }

    public void setImagephone(int imagephone) {
        this.imagephone = imagephone;
    }
}
