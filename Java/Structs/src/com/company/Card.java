package com.company;

import java.util.Objects;

public class Card {
    private int number;
    private Sign sign;
    private boolean value;

    public Card(int number, Sign sign) {
        this.number = number;
        this.sign = sign;
        this.value = true;
    }

    public int getNumber() {
        return number;
    }

    public Sign getSign() {
        return sign;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber() && getSign() == card.getSign();
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", sign=" + sign +
                '}';
    }
}
