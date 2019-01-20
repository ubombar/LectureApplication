import java.io.File;
import java.util.Arrays;

class Main 
{

    public static class A 
    {
        public int a;
    }

    public static void faa(A a, A b)
    {
        a = b;
    }

    public static void foo(int[] a)
    {
        final int size = a.length;

        for (int i = 0; i < size / 2; i++)
        {
            int tmp = a[i];
            a[i] = a[size - 1 - i];
            a[size - 1 - i] = tmp;   
        }
    }

    public static int binarySearch(int[] a, int v)
    {
        int l = 0, r = a.length - 1, m = 0;

        while (l < r)
        {
            m = (l + r) / 2;
            if (a[m] < v)
                r = m - 1;
            else if (a[m] > v)
                l = m + 1;
            else
                return m;
        }

        return -1;
    }


    public static void main(String[] args)
    {
        int[] array = new int[] {1, 2, 3, 3, 5, 6, 7};

        System.out.println(binarySearch(array, 4));


        /*
        CSVReader.CSVMatrix matrix = new CSVReader(new File("./res/lol.csv")).readMatrix();
        System.out.println(matrix.query((String[] row) -> Integer.valueOf(row[0]) >= 3));

        System.out.println(contains(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{3, 6, 5}));
        System.out.println(containerON(new int[]{1,8,6,2,5,4,8,3,7}));


        System.out.println("Hello World!");
        */
    }
}