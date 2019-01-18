import java.io.File;

class Main 
{
    public static void main(String[] args)
    {
        CSVReader reader = new CSVReader(new File("./lol.csv"));
        CSVReader.CSVMatrix matrix = reader.readMatrix();

        System.out.println(matrix);


        System.out.println("Hello World!");
    }
}