package com.company;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double Max =Double.NEGATIVE_INFINITY;
        double Min = Double.POSITIVE_INFINITY;
        double[] a = new double[10];
        System.out.println(Max);
        System.out.println(Min);
        for(int i=0;i<10;i++)
        {
            a[i]=Math.random()*10;
        }
        for(int i=0;i<10;i++)
        {
            if(Max<a[i])
            {
                Max = a[i];
                System.out.println(Max);
            }
            if(Min>a[i])
            {
                Min = a[i];
                System.out.println(Min);
            }
        }
        System.out.println("valori array:");
        for(int i=0;i<10;i++)
        {
            System.out.println(a[i]);
        }
        System.out.println("Questo è il massimo: "+Max);
        System.out.println("Questo è il minimo: "+Min);
    }
}
