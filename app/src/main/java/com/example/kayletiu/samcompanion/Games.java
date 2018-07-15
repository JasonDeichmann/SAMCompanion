package com.example.kayletiu.samcompanion;
/**
 * Created by Kayle Tiu on 08/07/2018.
 */

public class Games {
    private String name;
    private int imgurl;
    private int bgurl;

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public int getBgurl() {
        return bgurl;
    }

    public void setBgurl(int bgurl) {
        this.bgurl = bgurl;
    }

    public Games(String name, int imgurl, int bgurl) {
        this.name = name;
        this.imgurl = imgurl;
        this.bgurl = bgurl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
