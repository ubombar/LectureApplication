import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVReader
{
    public static interface CSVMatrixQuery
    {
        /**
         * The action for each row
         * @param row
         * @return if true row will be added to the new matrix
         */
        public boolean query(String[] row);
    };

    public static interface CSVMatrixOperation
    {
        /**
         * The action for each row
         * @param row
         * @return if true row will be added to the new matrix
         */
        public void operation(String[] row);
    };

    public static class CSVMatrix 
    {
        private int __column;
        private int __row;
        public String[][] data;

        /**
         * Constructor is not intended to be in use outside class
         * @param row
         * @param col
         */
        protected CSVMatrix(int row, int col)
        {
            __column = col;
            __row = row;
            data = new String[__row][__column];
        }

        /**
         * Changes the contents of a row, If a corrupts the morphism
         * of the matrix then returns true
         * @param row row index
         * @param a row
         * @return true if everything is alright
         */
        protected boolean alterRow(int row, String[] a)
        {
            if (__column != a.length)
                return false;

            data[row] = a;
            return true;
        }

        /**
         * Gets the rowth row
         * @param row starts from 0
         * @return String[] represents the row
         */
        public String[] getRow(int row)
        {
            return data[row];
        }

        /**
         * Gets the specified row and col,
         * Throws {@link NullPointerException} if given parameters are out of bounds
         * @param row
         * @param col
         * @return
         */
        public String get(int row, int col)
        {
            return data[row][col];
        }

        /**
         * Complexity: O(N)
         * Precondition: Tales a not null querry interface (mostly lambda body)
         * PostCondition: Creates a new CSVMatrix
         * 
         * Function evaluates every row, if the querry function returns true the row will
         * be added to the new matrix.
         * 
         * @param q querry function
         * @return new CSVMatrix object
         */
        public CSVMatrix query(CSVMatrixQuery q)
        {
            ArrayList<String[]> tmpArray = new ArrayList<String[]>();

            for (String[] row : data) 
                if (q.query(row))
                    tmpArray.add(row);

            CSVMatrix matrix = new CSVMatrix(tmpArray.size(), __column);
            
            for (int i = 0; i < tmpArray.size(); i++)
                matrix.alterRow(i, tmpArray.get(i));

            return matrix;
        }

        /**
         * Evaluates each row
         * @param o operation
         * @return itself
         */
        public CSVMatrix foreach(CSVMatrixOperation o)
        {
            for (String[] row : data)
                o.operation(row);
            return this;
        }

        @Override
        public String toString()
        {
            StringBuffer buffer = new StringBuffer("CSVMatrix: " + System.lineSeparator());

            this.foreach((String[] a) -> 
            {
                for (int i = 0; i < a.length; i++)
                {
                    buffer.append(a[i]);
                    buffer.append("\t");
                }
                buffer.append(System.lineSeparator());
            });

            return buffer.toString();
        }

        /**
         * @return column
         */
        public int getColumn()
        {
            return __column;
        }

        /**
         * @return row
         */
        public int getRow()
        {
            return __row;
        }
    };

    private File __file;
    private BufferedReader __reader;

    /**
     * Constructor of CSVReader class
     * @param file
     */
    public CSVReader(File file)
    {
        try
        {
            __file = file;
            __reader = new BufferedReader(new FileReader(__file));
        } 
        catch (Exception e)
        {
            System.err.println("Could not open file" + __file.toString());
            e.printStackTrace();
        }
    }

    /**
     * Reads a row, if end of file reached then returns null
     * @return Array of String represents the row
     */
    public String[] readRow()
    {
        if (__reader == null)
            return null;

        String[] array = null;

        try
        {
            String rawLine = __reader.readLine();

            if (rawLine == null || rawLine == "")
                return null;

            if (rawLine.contains(","))
                array = rawLine.split(",");
            else
                array = new String[] {rawLine};
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return array;
    }

    /**
     * Reads the whole file into double CSVMatrix
     * @return
     */
    public CSVReader.CSVMatrix readMatrix()
    {
        CSVReader.CSVMatrix matrix = null;

        try
        {
            ArrayList<String[]> list = new  ArrayList<String[]>();
            String rawLine = new String();
            int column = 0;

            while ((rawLine = __reader.readLine()) != null && rawLine != "")
                if (rawLine.contains(","))
                {
                    String[] tmp = rawLine.split(",");
                    list.add(tmp);
                    column = Math.max(column, tmp.length);
                }
                else
                {
                    list.add(new String[] {rawLine});
                    column = Math.max(column, 1);
                }
            

            matrix = new CSVMatrix(list.size(), column);

            for (int i = 0; i < list.size(); i++)
                matrix.alterRow(i, list.get(i));

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return matrix;
    }

    /**
     * Closes the object, do not use the reader after that.
     */
    public void close()
    {
        try
        {
            __reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

}