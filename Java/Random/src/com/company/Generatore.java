package com.company;

import java.util.Random;

public class Generatore {

    public static int Random()
    {
        return (int)(Math.random()*99 +  1);//genera numeri da 1 a 100
    }
    public static int Random2(Random a)
    {
        int i;

        i = a.nextInt(100);
        if(i == 0)
        {return 1;}

        return i;
    }

}
