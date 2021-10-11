package com.company;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Pallottoliere2 {
    private Set<Integer> numeri;
    private int dim;
    private int nEstratti;
    private Random a;
    public Pallottoliere2(int dim)
    {
        a = new Random();
        nEstratti = 0;
        a.setSeed(System.currentTimeMillis());
        numeri = new HashSet<>();
        this.dim = dim;
    }
    public int getExstract()
    {
        int i;

        if(nEstratti<dim)
        {
            i = a.nextInt(dim);
            if(numeri.contains(i))
            {
                System.out.println("Numero gia estratto");
            }
            else
            {
                numeri.add(i);
                i++;
                System.out.println("ho estratto il numero " + i);
                nEstratti++;
                return i;
            }
        }
        else
            return -1;

        return -2;
    }
}
