package it.tdgroup.corso.rest.eventi.entity;

public enum Stato {
    FINITO("Finito"),
    BOZZA("Bozza");

    private String displayName;
    Stato(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
