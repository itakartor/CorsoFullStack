import lombok.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Data

public class Grid {
    private int dimension;
    private ArrayList<Line> grid;
    private int nNavyOnGrid;

    public Grid()
    {
        grid = new ArrayList<>();
        this.nNavyOnGrid=0;
        this.dimension = 0;
    }
    public void printGrid()
    {
        System.out.println("----------");
        for (Line i: grid)
        {
            for(int j: i.getLineList())
            {
                System.out.printf("|"+ j +"|");
            }
            System.out.println(" ");
            System.out.println("----------");
        }
    }
    public boolean getValueNavy(int x, int y)
    {
        if(isValidCordinate(x,y,dimension))
            return grid.get(y).getLineList().get(x) == 1;//vuol dire che c'è una nave
        else
        {
            System.out.println("invalid coordinates");
            return false;
        }
    }
    public void setValueNavy(int x, int y,int a)
    {
        if(isValidCordinate(x,y,dimension))
        {
            grid.get(y).getLineList().set(x,a);
            if(a==0)
                nNavyOnGrid--;
        }
        else
            System.out.println("invalide coordinates");
    }
    public boolean isValidCordinate(int x,int y, int bound)
    {
        return y>=0 && x>=0 && x<bound && y<bound;
    }
    public void generateNavy()//calcolo delle navi e posizionamento
    {
        Random random = new Random();
        int x,y;
        int nNavy = nNavyOnGrid;
        while(nNavy>0)
        {
            x = random.nextInt(dimension);
            y = random.nextInt(dimension);
            if(!getValueNavy(x,y)) {
                //System.out.println(grid.getValueNavy(x,y));
                setValueNavy(x, y, 1);
                nNavy--;
            }
        }
    }
    public void choiceNNavy(Scanner keyboard)
    {
        int MaxNNavy= (dimension*dimension)/3;
        int nNavy;
        int nTry = 3;
        do
        {
            System.out.println("enter an integer for Navy number, Max number is:" + MaxNNavy);
            nNavy = keyboard.nextInt();
            if(nNavy>MaxNNavy)
            {
                System.out.println("invalid number");
            }
            nTry--;
        }while (nNavy>MaxNNavy && nTry>0);
        if(nTry==0)
            nNavy = 1;
        nNavyOnGrid = nNavy;
    }
    public void chooseDimension(Scanner keyboard)
    {
        int nTry = 3;
        do
        {
            System.out.println("enter an integer for dimension");
            dimension = keyboard.nextInt();
            nTry--;
        }while(dimension<=0 && nTry>0);
        if(nTry == 0)
        {
            dimension = 3;
        }
        for(int i=0;i<dimension;i++)
            this.grid.add(new Line(dimension));
    }
}
