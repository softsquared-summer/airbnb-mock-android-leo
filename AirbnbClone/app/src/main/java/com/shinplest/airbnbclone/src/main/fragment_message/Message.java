package com.shinplest.airbnbclone.src.main.fragment_message;

public class Message {
    private String profileUrl;
    private String userName;
    private String date;
    private String content;
    private String room;
    private String available;

    public Message(String profileUrl, String userName, String date, String content, String room, String available) {
        this.profileUrl = profileUrl;
        this.userName = userName;
        this.date = date;
        this.content = content;
        this.room = room;
        this.available = available;
    }

    public String getProfileUrl() {
        return profileUrl;
    }


    public String getUserName() {
        return userName;
    }


    public String getDate() {
        return date;
    }


    public String getContent() {
        return content;
    }


    public String getRoom() {
        return room;
    }


    public String getAvailable() {
        return available;
    }
}
