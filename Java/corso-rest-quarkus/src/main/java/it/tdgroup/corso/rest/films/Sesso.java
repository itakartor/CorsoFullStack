package it.tdgroup.corso.rest.films;

import it.tdgroup.corso.rest.api.exception.MapperException;

public enum Sesso {
    F("femmina"),
    M("maschio");

    private String genere;

    Sesso(String genere){
            this.genere = genere;
    }

    public String getGenere() {
        return genere;
    }

    public static Sesso controllo(String daControllare) throws MapperException {
        for(Sesso i: Sesso.values())
        {
            if(daControllare.equals(i.getGenere()))
                return i;//ho trovato il valore
        }
        throw new MapperException("Genere errato");//non trovo il valore
    }
}
