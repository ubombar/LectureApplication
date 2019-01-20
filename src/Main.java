import java.io.File;

class Main 
{
    /**
     * Challange is to write an algorithm that has a complexity of O(N)
     * @param big not null not empty int array
     * @param small not null not empty int array
     * @return if small array is contained in small array true else false
     */
    public static boolean contains(int[] big, int[] small)
    {
        if (big.length < small.length)
            return false;
        
        int bp1 = 0;
        int bp2 = 0;
        int sp = 0;
        boolean inside = false;

        while (true)
        {
            if (sp == small.length)
                return true;
            
            if (bp1 == big.length)
                return false;

            if (big[bp1] == small[sp])
            {
                if (inside)
                {
                    bp1++;
                    sp++;
                }
                else 
                {
                    bp1++;
                    bp2 = bp1;
                    inside = true;
                    sp++;
                }
            }
            else 
            {
                if (inside)
                {
                    inside = false;
                    sp = 0;
                    bp1 = bp2;
                }
                else 
                {
                    bp1++;
                    bp2++;
                }
            }
        }
    }

    /**
     * Complexity: O(N^2)
     * @param con
     * @return
     */
    public static int containerONN(int[] con)
    {
        int p1 = 0;
        int p2 = 0;
        int area = 0;

        while (p1 < con.length)
        {
            p2 = p1 + 1;

            while (p2 < con.length)
            {                
                int tempArea = (p2 - p1) * (con[p1] < con[p2] ? con[p1] : con[p2]);

                if (area < tempArea)
                    area = tempArea;
                p2++;
            }
            p1++;
        }

        return area;
    }

    public static int containerON(int[] height)
    {
        int left = 0;
        int right = height.length - 1;
        int area = 0;

        while (left < right)
        {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right])
                left++;
            else    
                right--;
        }

        return area;
    }

    public static void main(String[] args)
    {
        CSVReader.CSVMatrix matrix = new CSVReader(new File("./res/lol.csv")).readMatrix();
        System.out.println(matrix.query((String[] row) -> Integer.valueOf(row[0]) >= 3));

        System.out.println(contains(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}, new int[]{3, 6, 5}));
        System.out.println(containerON(new int[]{1,8,6,2,5,4,8,3,7}));


        System.out.println("Hello World!");
    }
}