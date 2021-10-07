package com.company;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Set<Card> deck = new HashSet<>();
        int nCardsForSign = 5;
        for(int i=0;i<nCardsForSign;i++)//aggiungo tutte le carte al mazzo iniziale
        {
            deck.add(new Card(i+1,Sign.Cuori));
            deck.add(new Card(i+1,Sign.Fiori));
            deck.add(new Card(i+1,Sign.Quadri));
            deck.add(new Card(i+1,Sign.Picche));
        }

        //istanzio i subDecks
        Set <Card> C2 = new HashSet<>();
        Set <Card> C3C5 = new HashSet<>();
        Set <Card> Q = new HashSet<>();
        Set <Card> P2F5 = new HashSet<>();
        Set <Card> F2F4 = new HashSet<>();

        for( Card a: deck)
        {
            if(a.getValue())//se la carta non è stata ancora assegnata
            {
                if(a.getNumber()>2 && a.getSign()==Sign.Cuori)
                {
                    C3C5.add(a);
                    a.setValue(false); //segno che la carta è stata messa in un altro mazzo
                }
                if(a.getNumber()<3 && a.getSign()==Sign.Cuori)
                {
                    C2.add(a);
                    a.setValue(false); //segno che la carta è stata messa in un altro mazzo
                }
                if(a.getSign()==Sign.Quadri)
                {
                    Q.add(a);
                    a.setValue(false); //segno che la carta è stata messa in un altro mazzo
                }
                if((a.getNumber()<3 && a.getSign()==Sign.Picche) || (a.getNumber()>4 && a.getSign()==Sign.Fiori))
                {
                    P2F5.add(a);
                    a.setValue(false); //segno che la carta è stata messa in un altro mazzo
                }
                if(a.getSign() == Sign.Fiori && a.getNumber()>1 && a.getNumber()<5)
                {
                    F2F4.add(a);
                    a.setValue(false); //segno che la carta è stata messa in un altro mazzo
                }
            }
        }
        System.out.println("Stampo il primo subDeck");
        for(Card card: C3C5)
        {
            System.out.println(card.toString());
        }
        System.out.println("Stampo il secondo subDeck");
        for(Card card: C2)
        {
            System.out.println(card.toString());
        }
        System.out.println("Stampo il terzo subDeck");
        for(Card card: Q)
        {
            System.out.println(card.toString());
        }
        System.out.println("Stampo il quarto subDeck");
        for(Card card: P2F5)
        {
            System.out.println(card.toString());
        }
        System.out.println("Stampo il quinto subDeck");
        for(Card card: F2F4)
        {
            System.out.println(card.toString());
        }
        System.out.println("Stampo le carte che non sono state smistate");
        for(Card card: deck)
        {
            if(card.getValue())//se la carta non è in un altro mazzo
            {
                System.out.println(card.toString());
            }
        }
    }
}
