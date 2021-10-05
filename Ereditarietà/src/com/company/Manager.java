package com.company;

public class Manager extends Impiegato{
    Impiegato segretario = null;

    public Manager(int yearsService, double salary, String name, Impiegato segretario) {
        super(yearsService, salary, name);
        this.segretario = segretario;
    }

    @Override
    public void upgradeSalary(double percent) {
        percent += 0.5*getYearsService();
        //System.out.println(percent);
        super.upgradeSalary(percent);

    }
}
