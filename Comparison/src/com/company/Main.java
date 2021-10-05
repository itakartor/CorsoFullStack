package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Utente a = new Utente(19,"Mimmo");
        Utente b = new Utente(9,"Lino");
        Utente c = new Utente(19,"Mimmo");

        System.out.println(a.isAdult());
        System.out.println(b.isAdult());
        System.out.println(a.equals(c));//i due utenti hanno nome e età uguale ma sono due persone differenti.
        System.out.println(a==c);
    }
}
