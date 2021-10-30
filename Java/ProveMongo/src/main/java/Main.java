import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String connectionString = "mongodb://127.0.0.1:27017/admin";//dove mi sto connetendo per ora localhost

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)).build(); //costruzione dei setting
        /*System.out.println("sono i settings"+settings);
        System.out.println(MongoClients.create(settings));*/
        MongoClient mongoClient = MongoClients.create(settings);

        MongoDatabase DB =mongoClient.getDatabase("prova");
        DB.getCollection("PROVA"); //creo la collezione
        /*
        person = {
              _id: "jo",
              name: "Jo Bloggs",
              age: 34,
              address: {
                street: "123 Fake St",
                city: "Faketon",
                state: "MA",
                zip: &#x201C;12345&#x201D;
              }
              books: [ 27464, 747854, ...]
            }
         */
        ArrayList<Integer> books = new ArrayList<>();
        books.add(3);
        books.add(4);
        books.add(7);
        books.add(9);
        DBObject oggetto = new BasicDBObject("_id","jo")
                .append("name","ciccio").append("age",34)
                .append("address", new BasicDBObject("street","123 pippo")
                        .append("city","Livorno").append("state","LI")
                        .append("zip", "fdsfewrgewregew432432")
                ).append("books",books);

        //DB.getCollection("PROVA").insertOne()
        Document oggetto2 = new Document("_id","jo")
                .append("name","ciccio").append("age",34)
                .append("address", new BasicDBObject("street","123 pippo")
                        .append("city","Livorno").append("state","LI")
                        .append("zip", "fdsfewrgewregew432432")
                ).append("books",books);
        DB.getCollection("PROVA").insertOne(oggetto2);

    }
}
