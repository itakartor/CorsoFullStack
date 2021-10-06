package com.company;

public class Main {

    public static void main(String[] args) {
	    Solido a = new Solido();//le dimensioni che vengono generate casualmente vanno da 1 a 10
        a.setAltezza((int)(Math.random()*10 + 1));
        a.setLarghezza((int)(Math.random()*10 + 1));
        a.setLunghezza((int)(Math.random()*10 + 1));
        System.out.printf("Altezza:%d\n",a.getAltezza());
        System.out.printf("Larghezza:%d\n",a.getLarghezza());
        System.out.printf("Lunghezza:%d\n",a.getLunghezza());

        System.out.printf("Volume del %s:%d\n",a.getNome(),a.calcolaVolumeSolido());

        Cubo b = new Cubo();
        b.setLunghezza((int)(Math.random()*10 + 1));
        System.out.printf("Lunghezza:%d\n",b.getLunghezza());
        System.out.printf("Volume del %s:%d\n",b.getNome(),b.calcolaVolumeSolido());

    }
}
