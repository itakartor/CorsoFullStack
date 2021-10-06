package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String a = new String("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit");
        System.out.println(a);
        a = Traduttore.allUp(a);
        System.out.println(a);

        a = Traduttore.allDown(a);
        System.out.println(a);
        a = Traduttore.allCapital(a);
        System.out.println(a);

    }
}
