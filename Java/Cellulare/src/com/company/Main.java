package com.company;

public class Main {

    public static void main(String[] args) {
	    Telefono telefono1 = new Telefono();
	    Telefono telefono2 = new Telefono();

        if(telefono1.Send("hi, how u re?",telefono2))
            System.out.println("[Telephon1]: Message sended");
        System.out.println("[Telephon2]: Message recive ->"+telefono2.Recive(telefono2));

        if(telefono2.Send("hello, i am fine and u?",telefono1))
            System.out.println("[Telephon2]: Message sended");
        System.out.println("[Telephon1]: Message recive ->"+telefono1.Recive(telefono1));
    }
}
