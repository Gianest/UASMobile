package com.example.uasmobile.ui.favorite;

public class Favorite {
    String tim;
    String desc;
    String imagev;

    // contrustor(empty)
    public Favorite() {
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagev() {
        return imagev;
    }

    public void setImagev(String imagev) {
        this.imagev = imagev;
    }

    // constructor
    public Favorite(String name, String desk, String imagev) {
        this.tim = name;
        this.desc = desk;
        this.imagev = imagev;
    }
}
