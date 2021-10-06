package com.company;

public class Main {

    public static void main(String[] args) {
	    Impiegato a = new Impiegato(3,1000,"Gino");
        System.out.println("early");
        a.printInfo();
        a.upgradeSalary(10);
        System.out.println("post");
        a.printInfo();
        System.out.println(" ");
        Manager b = new Manager(2,1500,"Pino",new Impiegato(1,500,"Vino"));
        System.out.println("early");
        b.printInfo();
        System.out.println("post");
        b.upgradeSalary(10);
        b.printInfo();

        b.segretario.printInfo();
    }
}
