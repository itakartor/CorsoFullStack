package com.company;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private LocalDate dateBirth;//vorrei trasformarla in una data per calcolare ogni volta l'età in automatico
    private String name;
    private int years;

    public Person(int year,int month,int day, String name) {//memorizzo la data della persona e ogni volta che necessito la sua età
        this.dateBirth = LocalDate.of(year,month,day);      //la calcolo attraverso l'apposità funzione
        this.name = name;
    }

    public int calculateAge(
            LocalDate birthDate,
            LocalDate currentDate) {
        // validate inputs ...
        return Period.between(birthDate, currentDate).getYears();
    }

    public int getYears() {
        return calculateAge(dateBirth, LocalDate.now());
    }

    public void setDateBirth(int year,int month,int day) {
        this.dateBirth = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

