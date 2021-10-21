package it.tdnet.lezione3;

import it.tdnet.lezione3.h2.H2Create;

import java.sql.SQLException;

/**
 * Crud UTENTE
 */
public class Esercizio {
    public static void main(String[] argv) throws SQLException {
        H2Create h2Create = new H2Create();
        h2Create.createTable();

//        H2Insert insert = new H2Insert();
//        insert.insertRecord();
//
//        UtenteDTO dto = new UtenteDTO(2, "Ermenegildo", "ermenegildo@gmail.com", "IT", "pwd1");
//        insert.insertRecord(dto);
//        dto = new UtenteDTO(3, "Gertrude", "gertrude@gmail.com", "IT", "pwd1");
//        insert.insertRecord(dto);
//
//        H2Select h2Select = new H2Select();
//        h2Select.selectRecord(1);
//
//        h2Select.selectAll();
//
//        H2Update h2Update = new H2Update();
//        h2Update.updateCountry();
//        h2Select.selectRecord(3);
//
//        H2Delete h2Delete = new H2Delete();
//        h2Delete.deleteRecord();
//        h2Select.selectAll();
    }
}
