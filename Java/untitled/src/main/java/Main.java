public class Main {
    public static void main(String []Args)
    {
        Casa casa = new Casa(1,2);
        Casa casa1 =Casa.builder()
                .altezza(2)
                .piano(2)
                .build();
        System.out.println(casa.getAltezza());
    }
}
