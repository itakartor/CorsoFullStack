package com.company;

public class Singoletto {
    private String message = null;

    private static Singoletto instance = null;
    private Singoletto() {
        message = "I'm alive";
    }

    public static Singoletto getSingoletto()
    {
        if(instance == null)
        {
            instance = new Singoletto();
        }
        return instance;
    }
    public String getMessage() {
        if(message != null)
            return message;
        else
            return "Void";
    }
}
