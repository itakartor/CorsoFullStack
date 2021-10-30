
package it.tdgroup.corso.rest.risorse.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "RISORSA")
public class Risorsa{

    // Utilizzato da MongoDB per il campo _id
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String state;
}


