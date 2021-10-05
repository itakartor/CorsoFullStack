public class Main {

    public static void main(String[] args)
    {
        Animale a = new Animale("rosso",3,"Red");
        Animale b = new Animale("grigio",4,"Toby");

        a.versi();
        b.mangiano();
        b.muovono();
        System.out.println(a);//posso stampare l'oggetto per identificare delle aree di memoria
        System.out.println(b);

        a = null; //così lo distruggo
        a = b;
        a.versi();//questa chiamata è uguale a scrivere b.versi(), visto che a punta all'oggetto b

        System.out.println(a);//a e b hanno la stessa area di memoria visto che l'oggetto di a è stato distrutto e ora punta a b
        System.out.println(b);

        System.out.println("il mio nome è:" + b.getNome());
    }
}
