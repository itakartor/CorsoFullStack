package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Pallottoliere {
    private ArrayList<Integer> numeri;
    private int dim;
    private int nEstratti;
    private Random a;
    private boolean vuoto;
    public Pallottoliere(int dim)
    {
        a = new Random();
        a.setSeed(System.currentTimeMillis());
        numeri = new ArrayList<>();
        this.dim = dim;
        this.nEstratti = 0;
        this.vuoto = true;
        for(int i=0;i<dim;i++)
        {
            numeri.add(i,0);//inizializzo tutto l'array con tutti i numeri ancora nel sacchetto
        }                       //0 nel sacchetto, 1 è stato estratto
    }
    public void setExstrac(int index)//levo il numero dal sacchetto
    {
        if(!getStatus(index))
        {
            numeri.set(index,1);
            nEstratti++;
        }
    }
    public void setInsert(int index)//metto il numero nel saccheto
    {
        if (getStatus(index)) {
            numeri.set(index, 0);
            nEstratti--;
        }
    }
    public boolean getStatus(int index)
    {
        if(numeri.get(index) == 1)//se il numero è stato estratto ritorna true, altrimenti false
            return true;
        else
            return false;
    }
    public void riempiSacchetto()
    {
        for(int i : numeri)
        {
            if(getStatus(i))
            {
                System.out.println("ho inserito il numero " + i);
                setInsert(i);
            }
        }
    }
    public int getExstract()
    {
        if(nEstratti < dim)
        {
            //System.out.println("nestratti: " + nEstratti + "dim:" + dim);
            int i;
            do
            {
                i = a.nextInt(dim);
            }
            while(getStatus(i));//ho gia estratto il numero
                {setExstrac(i);}
            i++;
            System.out.println("ho estratto il numero " + i);
            return i;
        }
        else
        {
            if(vuoto)
            {
                vuoto = false;
                System.out.println("il sacchetto è vuoto");
            }
            return -1;
        }
    }
}
