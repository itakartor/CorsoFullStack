package com.company;

public class Telefono {
    Autoparlante autoparlante;
    Microfono microfono;
    Tastiera tastiera;

    public Telefono() {
        this.autoparlante = new Autoparlante();
        this.microfono = new Microfono();
        this.tastiera = new Tastiera();
    }

    public boolean Send(String message,Telefono telefono)//1 for text and 2 for vocal
    {
        if(message == null)
        {
            System.out.println("I need message for call");
            return false;
        }
        if(telefono == null)
        {
            System.out.println("telephone isn't reachable");
            return false;
        }

        telefono.autoparlante.setMessage(message);//sono riuscito a scrivere il messaggio
        return true;
    }

    public String Recive(Telefono telefono)
    {
        if(telefono == null)
        {
            System.out.println("telephone isn't reachable");
            return "Vuoto";
        }
        return telefono.autoparlante.getMessage();
    }
}
