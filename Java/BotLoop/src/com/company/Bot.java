package com.company;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class Bot {

    public static void start()
    {
        //Timestamp ts;

        int request;
        boolean ok = true;
        while (ok)
        {
            Scanner keyboard = new Scanner(System.in);

            System.out.println("enter an integer");
            request = keyboard.nextInt();
            switch (request)
            {
                case 0://help
                {
                    System.out.println("0-help");
                    System.out.println("1-come stai?");
                    System.out.println("2-che tempo fa?");
                    System.out.println("3-che ore sono?");
                    System.out.println("4-Termina");
                    break;
                }
                case 1:
                {
                    System.out.println("thank u, i am fine!");
                    break;
                }
                case 2:
                {
                    System.out.println("it's raining");
                    break;
                }
                case 3:
                {
                    System.out.println("it's "+Timestamp.from(Instant.now()));
                    break;
                }
                case 4:
                {
                    System.out.println("Ciao ciao");
                    ok=false;
                    break;
                }
                default:
                {
                    System.out.println("invalid number, press 0 for help");
                    break;
                }
            }
        }
    }
}
