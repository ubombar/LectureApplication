
import java.io.File;
import java.util.*;

class Main 
{

    public static void main(String[] args)
    {

        CSVReader.CSVMatrix matrix = new CSVReader(new File("./res/lol.csv")).readMatrix();
        System.out.println(matrix.query((String[] row) -> Integer.valueOf(row[0]) >= 3));
    }
}