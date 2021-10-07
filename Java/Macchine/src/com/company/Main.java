package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Catalog catalog = new Catalog();
        int maxVel;
        int nPorts;
        String color;
        int yearsProduction;
        int request;
        boolean termina = false;
        while (!termina)
        {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("enter an integer");
            request = keyboard.nextInt();
            switch (request)
            {
                case 0://help
                {
                    System.out.println("0-help");
                    System.out.println("1-inserisci Auto");
                    System.out.println("2-rimuovi Auto");
                    System.out.println("3-ricerca Auto");
                    System.out.println("4-stampa Catalogo");
                    System.out.println("5-Termina");
                    break;
                }
                case 1:
                {
                    System.out.println("enter a max Velocity");
                    maxVel = keyboard.nextInt();
                    System.out.println("enter a number of Ports");
                    nPorts = keyboard.nextInt();
                    System.out.println("enter a color");
                    color = keyboard.next();
                    System.out.println("enter an year");
                    yearsProduction = keyboard.nextInt();

                    catalog.Add(new Car(maxVel,nPorts,color,yearsProduction));

                    break;
                }
                case 2:
                {
                    System.out.println("enter a max Velocity");
                    maxVel = keyboard.nextInt();
                    System.out.println("enter a number of Ports");
                    nPorts = keyboard.nextInt();
                    System.out.println("enter a color");
                    color = keyboard.next();
                    System.out.println("enter an year");
                    yearsProduction = keyboard.nextInt();

                    catalog.Remove(maxVel,nPorts,color,yearsProduction);
                    break;
                }
                case 3:
                {
                    System.out.println("enter a max Velocity");
                    maxVel = keyboard.nextInt();
                    System.out.println("enter a number of Ports");
                    nPorts = keyboard.nextInt();
                    System.out.println("enter a color");
                    color = keyboard.next();
                    System.out.println("enter an year");
                    yearsProduction = keyboard.nextInt();

                    catalog.Search(maxVel,nPorts,color,yearsProduction);

                    break;
                }
                case 4:
                {
                    catalog.PrintCatalog();
                    break;
                }
                case 5:
                {
                    System.out.println("Ciao ciao");
                    termina=true;
                    break;
                }
                default:
                {
                    System.out.println("invalid number, press 0 for help");
                    break;
                }
            }
        }
    }
}
