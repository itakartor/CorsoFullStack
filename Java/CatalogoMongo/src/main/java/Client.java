import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCommandException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private int nMenbers;

    public Client() {
        this.nMenbers = -1;
    }

    public MongoClient CreateConnection()
    {
        String connectionString = "mongodb://127.0.0.1:27017/admin";//dove mi sto connetendo per ora localhost

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)).build(); //costruzione dei setting

        return MongoClients.create(settings);
    }
    public boolean ExistDatabase(MongoClient client,String nameDB)
    {
        for (String i :client.listDatabaseNames())
        {
            if(i.equals(nameDB.toString()))
            {
                return true; // il database esiste gia
            }
        }
        return false;//il database non esiste
    }
    public boolean InsertDatabase(Scanner keyboard, MongoDatabase mongoDatabase,String collectionName)
    {
        System.out.println("insert y if u want insert user with json otherwise n");
        String response = keyboard.next();
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
            String json = keyboard.next();
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
                number = keyboard.nextInt();
            }
            catch (InputMismatchException e)
            {
                e.printStackTrace();
                return false;
            }

            mongoDatabase.getCollection(collectionName).insertOne(Document.parse(
                    "{name:\""+name+ "\","
                    +"surname:\""+surname+"\","
                    +"birthDate:\""+birthDate+"\","
                    +"cF:\""+cF+"\","
                    +"address:{street:\"" +street+ "\",number:" +"\""+number + "\""+  "},"
                    +"freeDay:\""+0+"\","
                    + "}"));
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
        String cF = keyboard.next();
        String name,surname;
        System.out.println("insert which attribute do u want update");
        System.out.println("0-name");
        System.out.println("1-surname");
        System.out.println("2-name and surname");
        int request;
        try {
            request = keyboard.nextInt();
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            request = 0;
        }
        switch (request)
        {
            case 0:
            {
                System.out.println("write name");
                name = keyboard.next();//"{\"name\":\""+name+"\"}"
                mongoDatabase.getCollection(collectionName).updateMany(Filters.eq("cF",cF),BsonDocument.parse("{\"name\":\""+name+"\"}"));
                break;
            }
            case 1:
            {
                System.out.println("write surname");
                surname = keyboard.next();
                mongoDatabase.getCollection(collectionName).updateMany(Filters.eq("cF",cF),BsonDocument.parse("{\"name\":\""+surname+"\"}"));
                break;
            }
            case 2:
            {
                System.out.println("write name");
                name = keyboard.next();
                System.out.println("write surname");
                surname = keyboard.next();
                mongoDatabase.getCollection(collectionName).updateMany(Filters.eq("cF",cF),//filtro
                        BsonDocument.parse("{$set:{\"name\":\""+name+"\",\"surname\":\""+surname+"\"}}"));
                break;
            }//BsonDocument.parse("{\"cF\":\""+ cF +"\"}"),BsonDocument.parse("{\"name\":\""+name+"\",\"surname\":\""+surname+"\"}"
        }
        return true;
    }
    public boolean searchNameSurname(String collectionName, MongoDatabase mongoDatabase,Scanner keyboard)
    {
        System.out.println("insert y if u want search user with json otherwise n");
        String response = keyboard.next();
        //System.out.println(response);
        FindIterable<Document> findResult;
        if(response.contains("y"))
        {
            System.out.println("insert your filter json");
            String filterJson = keyboard.next();
            try {
                filterJson = filterJson.replace("\r"," ").replace("\n"," ");
                findResult = mongoDatabase.getCollection(collectionName).find(BsonDocument.parse(filterJson));
            }catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            String name,surname;
            System.out.println("write name");
            name = keyboard.next();
            System.out.println("write surname");
            surname = keyboard.next();
            findResult = mongoDatabase.getCollection(collectionName).find(Filters.or(BsonDocument.parse("{name:\"" + name + "\"}"), BsonDocument.parse("{surname:\"" + surname + "\"}")));
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
            cF = keyboard.next();
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
            cF = keyboard.next();
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
            cF = keyboard.next();
        }catch (InputMismatchException e)
        {
            System.out.println("[ERROR INCREASE]");
            return false;
        }
        mongoDatabase.getCollection(collectionName).updateOne(Filters.eq("cF",cF),BsonDocument.parse("{$inc:{freeDay:"+ -1 +"}}"));

        return true;
    }
}
