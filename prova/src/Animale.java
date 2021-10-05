
public class Animale {

    private String coloreMantello;
    private int Anni;
    private String Nome;

    private Animale()//metto private in modo tale da inibire la possibilità di definire un animale senza parametri
    {}               //posso estendere la cosa a tutti i cotruttori che non voglio che vengano usati
    public Animale(String coloreMantello, String Nome)
    {
        this.coloreMantello = coloreMantello;
        this.Nome = Nome;
        this.Anni = 0;
    }
    public Animale(String coloreMantello, int Anni, String Nome)
    {
        this.Anni = Anni;
        this.coloreMantello = coloreMantello;
        this.Nome = Nome;
    }
    public void versi()
    {
        System.out.printf("[%s]:Bau,bau!!\n",Nome);
    }
    public void mangiano()
    {
        System.out.printf("[%s]:Sto mangiando.\n",Nome);
    }
    public void muovono()
    {
        System.out.printf("[%s]:mi muovo.\n",Nome);
    }

    public String getNome() {
        return Nome;
    }

    public String getColoreMantello() {
        return coloreMantello;
    }

    public int getAnni() {
        return Anni;
    }

    public void setColoreMantello(String coloreMantello) {
        this.coloreMantello = coloreMantello;
    }

    public void setAnni(int anni) {
        Anni = anni;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
