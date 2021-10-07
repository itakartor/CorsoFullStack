package com.company;

public class Cubo extends Solido{
    public Cubo()
    {
        super();
        super.setNome("Cubo");
    }
    @Override

    public void setLunghezza(int lunghezza) {
        super.setAltezza(lunghezza);
        super.setLarghezza(lunghezza);
        super.setLunghezza(lunghezza);
    }
}
