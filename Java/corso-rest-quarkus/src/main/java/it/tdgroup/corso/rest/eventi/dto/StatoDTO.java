package it.tdgroup.corso.rest.eventi.dto;

public enum StatoDTO {
    PUBBLICATO("Pubblicato"),
    IN_LAVORAZIONE("In lavorazione");

    public String getDisplayName() {
        return displayName;
    }

    private String displayName;
    StatoDTO(String name) {
        this.displayName = name;
    }
}
