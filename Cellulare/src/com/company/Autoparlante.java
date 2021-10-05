package com.company;

public class Autoparlante {
    private String message;

    public Autoparlante() {
        this.message = null;
    }

    public String getMessage() {
        if(this.message != null)
            return message;
        else
            return "Nessuna risposta";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
