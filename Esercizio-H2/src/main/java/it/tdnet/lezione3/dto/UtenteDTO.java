package it.tdnet.lezione3.dto;


import lombok.Data;

@Data
public class UtenteDTO {
    private Integer id;
    private String name;
    private String email;
    private String country;
    private String password;

    public UtenteDTO(Integer id, String name, String email, String country, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.password = password;
    }
}
