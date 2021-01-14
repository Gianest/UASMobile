package com.example.uasmobile.ui.home;

public class Home {
    private final String tim;
    private final String desc;
    private final String imagev;

    public Home(String name, String desk, String imagev) {
        this.tim = name;
        this.desc = desk;
        this.imagev = imagev;
    }

    public String getImagev() {
        return imagev;
    }
    public String getTim() {
        return tim;
    }
    public String getDesc() {
        return desc;
    }
}
