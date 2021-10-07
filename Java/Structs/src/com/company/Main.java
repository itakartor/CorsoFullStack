package com.company;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Set deck = new HashSet();
        int nCardsForSign = 6;
        for(int i=0;i<nCardsForSign;i++)//aggiungo tutte le carte al mazzo iniziale
        {
            deck.add(new Card(i+1,Sign.Cuori));
            deck.add(new Card(i+1,Sign.Fiori));
            deck.add(new Card(i+1,Sign.Quadri));
            deck.add(new Card(i+1,Sign.Picche));
        }

        //istanzio i subDecks
        Set C1 = new HashSet();
        Set C2C5 = new HashSet();
        Set Q = new HashSet();
        Set P2F5 = new HashSet();
        Set F2F4 = new HashSet();


    }
}
