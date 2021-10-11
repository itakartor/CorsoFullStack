package com.company;

public class Element {
    private int value;
    private String label;

    public Element(int value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                ", label='" + label + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
