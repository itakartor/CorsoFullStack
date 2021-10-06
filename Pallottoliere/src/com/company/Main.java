package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Pallottoliere pallottoliere = new Pallottoliere(5);
        int j;
        for(int i=0;i<9;i++)
        {
            j = pallottoliere.getExstract();
            if(j != -1)
                {
                    Thread.sleep(1000);
                }
        }


    }
}
