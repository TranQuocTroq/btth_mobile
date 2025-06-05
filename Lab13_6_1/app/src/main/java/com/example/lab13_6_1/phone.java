package com.example.lab13_6_1;
public class phone {
    private String namephone;
    private int imagephone;
    private String phoneprice;
    public phone(String namephone, int imagephone, String phoneprice) {
        this.namephone = namephone;
        this.imagephone = imagephone;
        this.phoneprice = phoneprice;
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

    public String getPhoneprice() {
        return phoneprice;
    }

    public void setPhoneprice(String phoneprice) {
        this.phoneprice = phoneprice;
    }
}

