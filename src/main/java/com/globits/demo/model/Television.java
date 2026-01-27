package com.globits.demo.model;

public class Television {
    private Channel channel;


    public Television() {
        this.channel = new Channel();
    }
    public Television(Channel channel) {
        this.channel = channel;
    }

    private void currentChannel() {
        System.out.println("Current channel: " + channel);
    }

    public void turnOn() {
        System.out.println("Television is turned on.");
    }
}
