import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCommandException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private int nMenbers;

    public Client() {
        this.nMenbers = -1;
    }
    //{name:"ciao5",surname:"ciao5",birthDate:"5-4-13",cF:"a5",address:{street:"mio la vendetta",number:5},freeDay:0})
    public Document createDocument(String name,String surname,String birthDate,String street, int number,String cF)
    {
         return new Document("name",name).append("surname",surname).append("birthDate",birthDate).append("cF",cF)
                .append("address", new BasicDBObject("street",street)
                        .append("number",number)
                ).append("freeDay",0);
    }
    public MongoClient CreateConnection()
    {
        String connectionString = "mongodb://127.0.0.1:27017/admin";//dove mi sto connetendo per ora localhost

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)).build(); //costruzione dei setting
        /*System.out.println("sono i settings"+settings);
        System.out.println(MongoClients.create(settings));*/
        return MongoClients.create(settings);
    }
    public boolean InsertDatabase(Scanner keyboard, MongoDatabase mongoDatabase,String collectionName)
    {
        System.out.println("insert y if u want insert user with json otherwise n");
        String response = keyboard.nextLine();
        System.out.println(response);
        if(nMenbers == -1)// è il primo elemento che sto inserendo
        {
            nMenbers = 0;
            try{
                mongoDatabase.getCollection(collectionName).createIndex(BsonDocument.parse("{ \"cF\": 1 }, { unique: true }"));//creo un indice univoco sul CF
            }
            catch (MongoCommandException e)
            {
                System.out.println("index exist");
            }
            //String g ="{name:\"ciao2\",surname:\"ciao2\",birthDate:\"13-13-13\",cF:\"aaaa\",address:\"mio due la vendetta\",freeDay:0}";
            //servirebbe isolare il ritorno carello e l'accapo
        }
        if(response.contains("y"))
        {
            System.out.println("insert your json");
            String json = keyboard.nextLine();
            try {
                json = json.replace("\r"," ").replace("\n"," ");
                //System.out.println(json);
                mongoDatabase.getCollection(collectionName).insertOne(Document.parse(json));
            }catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            /*
            name
            surname
            Birthdate
            cf
            indirizzo
            numeroGiorniFerie
             */
        }
        else
        {
            String name,surname,birthDate,cF,street;
            int number;
            try {
                System.out.println("insert name");
                name = keyboard.nextLine();
                System.out.println("insert surname");
                surname = keyboard.nextLine();
                System.out.println("insert birthDate dd-mm-yy");
                birthDate = keyboard.nextLine();
                System.out.println("insert CF");
                cF = keyboard.nextLine();
                System.out.println("insert street");
                street = keyboard.nextLine();
                System.out.println("insert number of street");
                number = Integer.parseInt(keyboard.nextLine());
            }
            catch (InputMismatchException e)
            {
                e.printStackTrace();
                return false;
            }

            /*mongoDatabase.getCollection(collectionName).insertOne(Document.parse(
                    "{name:\""+name+ "\","
                    +"surname:\""+surname+"\","
                    +"birthDate:\""+birthDate+"\","
                    +"cF:\""+cF+"\","
                    +"address:{street:\"" +street+ "\",number:" +"\""+number + "\""+  "},"
                    +"freeDay:\""+0+"\","
                    + "}"));*/
            mongoDatabase.getCollection(collectionName).insertOne(createDocument(name,surname,birthDate,street,number,cF));
        }
        nMenbers++;
        return true;
    }

    public void printCollection(String collectionName, MongoDatabase mongoDatabase)
    {
        FindIterable<Document> resultFind = mongoDatabase.getCollection(collectionName).find();
        for(Document i: resultFind)
        {
            System.out.println(i.toJson());
        }
    }

    public boolean updateCollection(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)//ricerco gli utenti sempre per codice fiscale
    {           //posso aggiornare il nome oppure il cognome
        System.out.println("insert cf");
        String cF = keyboard.nextLine();
        String name,surname;
        System.out.println("insert which attribute do u want update");
        System.out.println("0-name");
        System.out.println("1-surname");
        System.out.println("2-name and surname");
        int request;
        try {
            request = Integer.parseInt(keyboard.nextLine());
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            request = 0;
        }
        switch (request)//bisogna usare la nextLine anche per gli interi e poi si usa Integer.parser
        {
            case 0:
            {
                System.out.println("write name");
                name = keyboard.nextLine();//"{\"name\":\""+name+"\"}"
                mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),BsonDocument.parse("{$set:{\"name\":\""+name+"\"}}"));
                break;
            }
            case 1:
            {
                System.out.println("write surname");
                surname = keyboard.nextLine();
                mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),BsonDocument.parse("{$set:{\"surname\":\""+surname+"\"}}3"));
                break;
            }
            case 2:
            {
                System.out.println("write name");
                name = keyboard.nextLine();
                System.out.println("write surname");
                surname = keyboard.nextLine();
                mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),//filtro
                        BsonDocument.parse("{$set:{\"name\":\""+name+"\",\"surname\":\""+surname+"\"}}"));
                break;
            }//BsonDocument.parse("{\"cF\":\""+ cF +"\"}"),BsonDocument.parse("{\"name\":\""+name+"\",\"surname\":\""+surname+"\"}"
        }
        return true;
    }
    public boolean searchNameSurname(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)
    {
        System.out.println("insert y if u want search user with json otherwise n");
        String response = keyboard.nextLine();
        //System.out.println(response);
        FindIterable<Document> findResult;
        if(response.contains("y"))
        {
            System.out.println("insert your filter json");
            String filterJson = keyboard.nextLine();
            try {
                filterJson = filterJson.replace("\r"," ").replace("\n"," ");
                findResult = mongoDatabase.getCollection(collectionName).find(BsonDocument.parse(filterJson));
            }catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        /*else
        {
            String name,surname;
            System.out.println("write name");
            name = keyboard.nextLine();
            System.out.println("write surname");
            surname = keyboard.nextLine();
            findResult = mongoDatabase.getCollection(collectionName).find(Filters.or(BsonDocument.parse("{name:\"" + name + "\"}"), BsonDocument.parse("{surname:\"" + surname + "\"}")));
        }*///{db.USER.deleteOne({"cF":"a5"})}
        else//trova l'utente anche per iniziale e non è case sensitive
        {
            String name,surname;
            System.out.println("write name");
            name = keyboard.nextLine();
            System.out.println("write surname");
            surname = keyboard.nextLine();
            findResult = mongoDatabase.getCollection(collectionName).find(Filters.or(BsonDocument.parse("{name:{$regex:/^" + name + "/,$options:\"im\"}}"), BsonDocument.parse("{surname:\"" + surname + "\"}")));
        }
        for(Document i:findResult)
        {
            System.out.println(i.toJson());
        }
        return true;
    }

    public boolean DeleteUser(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)
    {
        String cF;
        try {
            System.out.println("write cF");
            cF = keyboard.nextLine();
        }catch (InputMismatchException e)
        {
            System.out.println("[ERROR DELETE]");
            return false;
        }

        mongoDatabase.getCollection(collectionName).deleteOne(Filters.eq("cF",cF));
        nMenbers--;
        return true;
    }

    public boolean IncreaseNDay(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)
    {
        String cF;
        try {
            System.out.println("write cF");
            cF = keyboard.nextLine();
        }catch (InputMismatchException e)
        {
            System.out.println("[ERROR INCREASE]");
            return false;
        }
        mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),BsonDocument.parse("{$inc:{freeDay:"+ 1 +"}}"));

        return true;
    }
    public boolean DecreseNDay(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)
    {
        String cF;
        try {
            System.out.println("write cF");
            cF = keyboard.nextLine();
        }catch (InputMismatchException e)
        {
            System.out.println("[ERROR INCREASE]");
            return false;
        }
        mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),BsonDocument.parse("{$inc:{freeDay:"+ -1 +"}}"));

        return true;
    }
}
