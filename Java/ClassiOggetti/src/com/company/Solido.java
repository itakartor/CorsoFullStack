package com.company;

public class Solido {
    private int lunghezza;
    private int altezza;
    private int larghezza;
    private String Nome;

    public Solido()
    {
        this.Nome = "Solido";
        this.altezza = 0;
        this.lunghezza = 0;
        this.larghezza = 0;
    }

    public void setLunghezza(int lunghezza) {
        this.lunghezza = lunghezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int calcolaVolumeSolido()
    {
        return this.altezza*this.larghezza*this.lunghezza;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getAltezza() {
        return altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public String getNome() {
        return Nome;
    }
}
