package com.company;

public class Main {

    public static void main(String[] args) {
        int a = 0;
        int b = 3;

        try
        {
            int c = b/a;
            System.out.println(c);
        }catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
    }
}
