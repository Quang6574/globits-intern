package com.globits.demo.model;

public class Channel {
    private String name;
    private String title;
    private String content;


    public Channel() {
        this.name = "Default Name";
        this.title = "Default Title";
        this.content = "Default Content";
    }

    public Channel(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

}
