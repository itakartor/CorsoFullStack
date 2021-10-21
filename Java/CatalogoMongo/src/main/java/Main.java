import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String NAMEDB = "Catalogo";
        String NAMECOLLECTION = "USER";
        boolean end = false;
        Scanner keyboard = new Scanner(System.in);
        int request;

        Client client = new Client();
        //vado a richiedere la connessione al server mongo
        MongoClient mongoClient = client.CreateConnection();
        //vado a creare il database Catalogo o a prenderlo in caso che esista gia
        MongoDatabase CatalogoDB =mongoClient.getDatabase(NAMEDB);
        CatalogoDB.getCollection("USERS"); //creo la collezione
        while (!end) {
            System.out.println("====================");
            System.out.println("0-exit");
            System.out.println("1-insert User");
            System.out.println("2-print collection");
            System.out.println("3-update name or surname of User collection");
            System.out.println("4-delete users");
            System.out.println("5-find users for name or surname");
            System.out.println("6-increase free day");
            System.out.println("7-decrease free day");
            System.out.println("====================");
            System.out.println("enter an integer for decision");
            try {
                request = keyboard.nextInt();
            }catch (InputMismatchException e)
            {
                System.out.println("invalid input");
                request = 0;
            }


            switch (request) {
                case 0: {
                    System.out.println("bye!");
                    end = true;
                    break;
                }
                case 1: {
                    if(client.InsertDatabase(keyboard,CatalogoDB,NAMECOLLECTION))
                        System.out.println("it is insert");
                    break;
                }
                case 2: {
                    client.printCollection(NAMECOLLECTION,CatalogoDB);
                    break;
                }
                case 3: {
                    if(client.updateCollection(NAMECOLLECTION,CatalogoDB,keyboard))
                    {
                        System.out.println("update done");
                    }
                    break;
                }
                case 4: {
                    if(client.DeleteUser(NAMECOLLECTION,CatalogoDB,keyboard))
                    {
                        System.out.println("User delete");
                    }
                    break;
                }
                case 5: {
                    client.searchNameSurname(NAMECOLLECTION,CatalogoDB,keyboard);
                    break;
                }
                case 6: {
                    if(client.IncreaseNDay(NAMECOLLECTION,CatalogoDB,keyboard))
                        System.out.println("increase free day done");
                    break;
                }
                case 7: {
                    if(client.DecreseNDay(NAMECOLLECTION,CatalogoDB,keyboard))
                        System.out.println("decrease free day done");
                    break;
                }
                default: {
                    System.out.println("invalid number");
                    break;
                }
            }
        }
    }
}

/*
{name:"ciao1",surname:"ciao1",birthDate:"1-1-1",cF:"a1",address:{street:"mio la vendetta",number:1},freeDay:0}
        db.USER.insertMany({name:"ciao2",surname:"ciao2",birthDate:"2-13-13",cF:"a2",address:{street:"mio la vendetta",number:2},freeDay:0},
        {name:"ciao3",surname:"ciao3",birthDate:"3-8-13",cF:"a3",address:{street:"mio la vendetta",number:3},freeDay:0},
        {name:"ciao4",surname:"ciao4",birthDate:"4-13-13",cF:"a4",address:{street:"mio la vendetta",number:4},freeDay:0},
        {name:"ciao5",surname:"ciao5",birthDate:"5-4-13",cF:"a5",address:{street:"mio la vendetta",number:5},freeDay:0})
        */
