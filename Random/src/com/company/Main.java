package com.company;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random a = new Random();
        //Date date = new Date();
        //Timestamp timestamp = new Timestamp(date.getTime());
        for(int i=0;i<5;i++)
        {
            a.setSeed(System.currentTimeMillis());
            System.out.println("cambio seed");
            for(int j=0; j<10;j++)
            {
                System.out.println(Generatore.Random2(a));
            }
        }
    }
}
