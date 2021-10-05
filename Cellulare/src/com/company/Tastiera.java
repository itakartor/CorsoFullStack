package com.company;

public class Tastiera {
    private String lastMessage;

    public Tastiera() {
        this.lastMessage = null;
    }

    public String getLastMessage() {
        if(this.lastMessage != null)
            return lastMessage;
        else
            return "Vuoto";
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
