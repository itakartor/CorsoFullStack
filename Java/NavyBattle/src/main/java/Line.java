import java.util.ArrayList;
import lombok.*;

@Data
public class Line {
    private int dimension;
    private ArrayList<Integer> lineList;

    public Line(int dimension)
    {
        this.dimension = dimension;
        lineList = new ArrayList<>();
        for(int i=0;i<dimension;i++)
        {
            this.lineList.add(i,0);
        }
    }
}
