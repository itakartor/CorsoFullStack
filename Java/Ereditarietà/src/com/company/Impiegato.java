package com.company;

public class Impiegato {
    private int yearsService;
    private double salary;
    private String name;

    public Impiegato(int yearsService, double salary, String name) {
        this.yearsService = yearsService;
        this.salary = salary;
        this.name = name;
    }

    public void printInfo()
    {
        System.out.println("Name worker: "+ name);
        System.out.println("Years of Service: "+ yearsService);
        System.out.println("Salary: "+ salary);
    }

    public void upgradeSalary(double percent)
    {
        salary += salary*percent/100;
    }

    public int getYearsService() {
        return yearsService;
    }
}
