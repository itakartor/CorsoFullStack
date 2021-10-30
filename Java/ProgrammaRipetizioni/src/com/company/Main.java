package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("scrivi il prezzo");
        Scanner a = new Scanner(System.in);
        int prezzo = a.nextInt();
        if(prezzo<10)
        {
            //System.out.println("prezzo " + prezzo);
        }
        else
        {
            if(prezzo>100)
            {
                prezzo = prezzo + prezzo*22/100;
                //System.out.println("prezzo " + prezzo + " 22% di tasse");
            }
            else
            {} //System.out.println("prezzo " + prezzo);
        }

    }
}
