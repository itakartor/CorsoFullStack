package com.company;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {

    public static int calculateAge(
            LocalDate birthDate,
            LocalDate currentDate) {
        // validate inputs ...
        return Period.between(birthDate, currentDate).getYears();
    }

    public static void main(String[] args) {
	// write your code here
        List<String> amici = new ArrayList<>();
        amici.add("ciccio");
        amici.add("mago");
        amici.add("papo");
        amici.add("pepe");
        amici.add("lalo");
        amici.add("mano");
        amici.stream().filter(a->a.startsWith("p")).forEach(System.out::println);
        amici.stream().filter(a->a.startsWith("p")).forEach(a-> System.out.println("questo amico inizia per p?"+a));
        //vado a filtrare le persone che hanno un nome che inizia per p
        System.out.println(" ");
        amici.stream().filter(a->a.endsWith("o")).forEach(System.out::println);

        List<Integer> numeri = Arrays.asList(1,2,3,4,5);
        System.out.println(numeri.stream().mapToInt(Integer::valueOf).sum());
        System.out.println(numeri.stream().reduce(0,(a,b) ->(a+b)));

        List<Element> elementList = new ArrayList<>();
        for(int i=0;i<10;i++)
            elementList.add(new Element(i,"ciao" + i));
        elementList.stream().forEach(element -> {
            System.out.println(element.toString());
        });

        System.out.println(elementList.stream().mapToInt(Element::getValue).sum());
        //la map int mi restitisce il valore indicato come intero
        //posso di solito ritornare anche altri oggetti che sono contenuti nell'oggetto contenitore
        //se volessi complicarmi la vita potevo ritornare con la mappa il valore e poi chiamare la reduce
        //con la somma

        Factor factor = new Factor();
        System.out.println(factor.apply(3));

        //collect serve a creare un nuovo stream oppure in una nuova struttura che gli dico io
        //collect(Collector.toList())-> quello che ho ottenuto in precedenza nello stream lo posso mettere in una nuova Lista

        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1999,10,11,"a"));
        personList.add(new Person(2015,3,19,"b"));
        personList.add(new Person(2003,6,5,"c"));
        personList.add(new Person(2019,7,25,"d"));
        personList.add(new Person(1990,6,3,"e"));

        System.out.println("La persona piu grande " + personList.stream()
                .mapToInt(Person::getYears)
                .max()
                .getAsInt());
        System.out.println("La persona piu grande dei minorenni " + personList.stream()
                .mapToInt(Person::getYears)
                .filter(age -> age < 18)
                .max()
                .getAsInt());
        if(personList.stream()
                .allMatch(person -> person.getYears() < 18))
        {
            System.out.println("Sono tutti minorenni");
        }
        else
            System.out.println("C'è qualche maggiorenne");

        personList.stream()//stampo lo stream delle età delle persone
                .mapToInt(Person::getYears)
                .forEach(System.out::println);

        //calcolo dell'età con funzione in alto
        LocalDate localDate = LocalDate.of(1999,10,12);
        LocalDate localDate1 = LocalDate.now();
        System.out.println(calculateAge(localDate,localDate1));

        //Consumer<T> è un interfaccia che consuma un'informazione e poi non restituisce niente
        //mi potrebbe servire per nascondere un metodo con una logica particolare per evitare di spiatterlarla in una parte precisa
        //mi serve per riutilizzarla e per la manutenzione
        //Predicate<T> altra interfaccia
        //Function<Input,Output> altra interfaccia
        //java.util.Function
        //servlet -> "funzioni" per invocare le funzioni Crud:{Post,Put,Delete,Get}
        //la servlet istanzia un thread che farà la richiesta

        Optional<Integer> a = Optional.empty();
        Optional<Integer> b = Optional.of(2);
        if(a.isEmpty())
            System.out.println("vuoto");
        else
            System.out.println(a);
    }
}
