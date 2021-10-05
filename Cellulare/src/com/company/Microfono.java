package com.company;

public class Microfono {
    private String lastVocalMessage;

    public Microfono() {
        this.lastVocalMessage = null;
    }

    public String getLastVocalMessage() {
        if(this.lastVocalMessage != null)
            return lastVocalMessage;
        else
            return "Vocale Assente";
    }

    public void setLastVocalMessage(String lastVocalMessage) {
        this.lastVocalMessage = lastVocalMessage;
    }
}
