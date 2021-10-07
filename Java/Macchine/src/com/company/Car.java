package com.company;


public class Car {
    private int maxVelocity;
    private int nPort;
    private String color;
    private int yearsProduction;

    public Car(int maxVelocity, int nPort, String color, int yearsProduction) {
        this.maxVelocity = maxVelocity;
        this.nPort = nPort;
        this.color = color;
        this.yearsProduction = yearsProduction;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public int getnPort() {
        return nPort;
    }

    public String getColor() {
        return color;
    }

    public int getYearsProduction() {
        return yearsProduction;
    }

    @Override
    public String toString() {
        return "Car{" +
                "maxVelocity=" + maxVelocity +
                ", nPort=" + nPort +
                ", color=" + color +
                ", yearsProduction=" + yearsProduction +
                '}';
    }
}
