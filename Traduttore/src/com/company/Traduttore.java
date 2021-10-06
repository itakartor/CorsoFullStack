package com.company;

public class Traduttore {
    public static String allUp(String a)
    {
        if(a==null)
        {
            System.out.println("invalid String");
            return null;
        }
        a = a.toUpperCase();
        return a;
    }
    public static String allDown(String a)
    {
        if(a==null)
        {
            System.out.println("invalid String");
            return null;
        }
        a = a.toLowerCase();
        return a;
    }
    public static String allCapital(String a)
    {
        if(a==null)
        {
            System.out.println("invalid String");
            return null;
        }
        /*StringTokenizer st = new StringTokenizer(a,divisor);
        while(st.hasMoreElements())
        */
        String[] words = a.split("\\s");
        a="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            a+=first.toUpperCase()+afterfirst+" ";
        }

        return a;
    }
}
