package com.company;

public class Main {

    public static void main(String[] args) {

        Singoletto singoletto = Singoletto.getSingoletto();
        System.out.println(singoletto.getMessage());
    }
}
