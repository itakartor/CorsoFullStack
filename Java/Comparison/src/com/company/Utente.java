package com.company;

import java.util.Objects;

public class Utente {
    private int age;
    private String name;

    public Utente(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public boolean isAdult()
    {
        if(age >= 18)
            return true;
        else
            return false;
    }
    public boolean equals(Utente utente2)
    {
        if(utente2 != null)
        {
            if(utente2.age == age && utente2.name.toString().equals(name.toString()))
                return true;
        }

        return false;
    }

}
