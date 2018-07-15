package com.example.kayletiu.samcompanion;

/**
 * Created by Kayle Tiu on 08/07/2018.
 */

public class Partner {
    public int imgurl;
    public String name;
    public String description;
    public String announcements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(String announcements) {
        this.announcements = announcements;
    }

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

    public Partner(int imgurl, String name, String description, String announcements) {
        this.imgurl = imgurl;
        this.name = name;
        this.description = description;
        this.announcements = announcements;
    }
}
