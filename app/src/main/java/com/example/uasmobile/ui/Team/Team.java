package com.example.uasmobile.ui.Team;

public class Team {
    private final String tim;
    private final String desc;
    private final String imagev;

    public Team(String name, String desk, String imagev) {
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
