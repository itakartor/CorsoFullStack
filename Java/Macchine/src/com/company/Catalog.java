package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Catalog{

    private Set<Car> list;
    private int nCar;
    public Catalog()
    {
        this.list = new HashSet<>();
        this.nCar = 0;
    }
    public void Add(Car car)
    {
        this.list.add(car);
        this.nCar++;
        System.out.println("Car is insert");
    }
    public Car Search(int maxVelocity, int nPort, String color, int yearsProduction)
    {
        if(nCar>0) // in caso che il catalogo sia vuoto
        {
            boolean trovato = false;
            int i = 0;
            Iterator<Car> it = list.iterator();
            Car result = null;
            while(!trovato && it.hasNext())
            {
                Car car;
                car = it.next();
               if(car.getColor().equals(color) && car.getMaxVelocity() == maxVelocity && car.getnPort()==nPort && car.getYearsProduction() == yearsProduction)
               {
                   trovato = true;
                   result = car;
               }
            }
            System.out.println("Car found");
            return result;
        }
        System.out.println("car isn't found");
        return null;
    }
    public void Remove(int maxVelocity, int nPort, String color, int yearsProduction)
    {
        Car car = Search(maxVelocity, nPort, color, yearsProduction);
        if(car != null) //ho trovato la macchina
        {
            this.list.remove(car);
            this.nCar--;
            System.out.println("Car is remove");
        }
        else
            System.out.println("Car isn't remove");

    }
    public void PrintCatalog()
    {
        list.forEach(car -> {
            System.out.println(car.toString());
        });
        System.out.println("End");
    }
}
