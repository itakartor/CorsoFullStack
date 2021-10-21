import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.Document;

import javax.print.Doc;

public class Main {
    public static void main(String[] args) {
        String connectionString = "mongodb://127.0.0.1:27017/admin";//dove mi sto connetendo per ora localhost

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)).build(); //costruzione dei setting

        MongoClient client = MongoClients.create(settings);

        MongoDatabase provaDb = client.getDatabase("prova");//use database test
        FindIterable<Document> findResult = provaDb.getCollection("inventory").find();
        MongoCursor<Document> cursor = findResult.iterator();
        do {
            Document i = cursor.next();
            System.out.println(i.toJson());
        }while (cursor.hasNext());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        //find
        FindIterable<Document> findCollection = provaDb.getCollection("inventory").find(BsonDocument.parse("{ status:\"A\" }"));
        for (Document i : findCollection) {
            System.out.println(i.toJson());
        }
        //classe updateriesult con controllo sull'ACK
        //deleteResult classe
        //creazione di indici univoci?? da vedere meglio
        // /.* mi.*/i -> filtro per cercare le parole che contengono mi



        /*
        eseguibile da riga di comando, dove si possono fare più le varie operazioni relative ad un db
        di Utenti come se fosse un catalogo
        Utente -> { cF(unico, not null),nome,cognome,indirizzo,dataNascita,numeroGiorniFerie }
        operazioni di inserimento con campi oppure json
        varie find con filtro oppure senza
        1)filtro con OR nome cognome
        2)filtro con json di ricerca -> provare injection
        delete per id o CF
        update
        incremento e decremento giorni ferie
        robomongo
         */
    }
}
