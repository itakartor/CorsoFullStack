import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean end = false;
        int request;
        int x,y;
        Scanner keyboard = new Scanner(System.in);


        Random random = new Random();
        Grid grid= new Grid();//costruisco la griglia
        grid.chooseDimension(keyboard);//scelgo la dimensione della griglia
        grid.choiceNNavy(keyboard);//scelgo quante navi usare
        grid.generateNavy();//genero in automatico le navi

        while (!end)
        {
            System.out.println("====================");
            System.out.println("0-exit");
            System.out.println("1-punch navy");
            System.out.println("2-stamp greed");
            System.out.println("3-new game");
            System.out.println("====================");
            System.out.println("enter an integer for decision");

            request = keyboard.nextInt();

            switch (request)
            {
                case 0:
                {
                    System.out.println("GameOver!");
                    end=true;
                    break;
                }
                case 1:
                {
                    System.out.println("insert x of bullet");
                    x = keyboard.nextInt();
                    System.out.println("insert y of bullet");
                    y = keyboard.nextInt();
                    if(grid.getValueNavy(x,y))//se trovo la nave
                    {
                        System.out.println("Hit!");
                        grid.setValueNavy(x,y,0);
                        //System.out.println("numNavy:"+grid.getNNavyOnGrid());
                        if(grid.getNNavyOnGrid()<=0)
                        {
                            end = true;
                            System.out.println("you WON!!");
                        }
                    }
                    else
                    {
                        System.out.println("MISsSsS!");
                    }
                    break;
                }
                case 2:
                {
                    grid.printGrid();
                    break;
                }
                case 3:
                {
                    System.out.println("====================");
                    System.out.println("refresh of system");
                    System.out.println("====================");
                    grid = new Grid();
                    grid.chooseDimension(keyboard);//scelgo la dimensione della griglia
                    grid.choiceNNavy(keyboard);//scelgo quante navi usare
                    grid.generateNavy();//genero in automatico le navi
                    break;
                }
                default:
                {
                    System.out.println("invalid number");
                    break;
                }
            }
        }
    }
}
