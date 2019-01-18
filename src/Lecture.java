import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Ufuk Bombar
 * Last modified: 2019.01.18
 */
class Lecture 
{
    // Holds all the Lectures, index of a lecture == id of that lecture
    private static ArrayList<Lecture> __lectures = new ArrayList<Lecture>();

    // Holds the id of a lecture (thereby index in the __lectures array) for a given name
    private static HashMap<String, Integer> __nameConverseMap = new HashMap<String, Integer>();


    private String __sname;
    private String __lname;
    private int __id;
    private ArrayList<Integer[]> __prerequisites;

    /**
     * Complexity O(1)
     * Precondition: takes not null Strings and positive integer
     * Postcondition: Creates an object of a Lecture
     * 
     * This constructor does not registers the object into static collections.
     * use addLecture function to register the object.
     * 
     * @param id lecture id, positive
     * @param sname lectures short name, ex. MATH225
     * @param lname lectures long name, ex. Linear Algebra and Differential Equations
     */
    protected Lecture(int id, String sname, String lname)
    {
        __sname = sname;
        __lname = lname;
        __id = id;
        __prerequisites = new ArrayList<Integer[]>();
    }

    /**
     * Complexity O(1)
     * Precondition: Lecture is expected to be not null
     * Postcondition: HashMap and Lecture array contains the lecture
     * 
     * @param lecture not null lecture object
     */
    public static void addLecture(int id, String sname, String lname)
    {
        addLecture(new Lecture(id, sname, lname));
    }

    /**
     * Complexity O(1)
     * Precondition: Lecture is expected to be not null
     * Postcondition: HashMap and Lecture array contains the lecture
     * 
     * @param lecture not null lecture object
     */
    public static void addLecture(Lecture lecture)
    {
        __lectures.add(lecture.getId(), lecture);
        __nameConverseMap.put(lecture.getShortName(), lecture.getId());
    }
    
    /**
     * Complexity O(N^2) / O(N*logN)
     * Precondition: Lectures array represents a list of lectures that is prerequisite
     * Postcondition: Lecture array is sorted and added to the lectures prerequisite list
     * 
     * @param id the id of a lecture
     * @param lectures prerequisites
     * @return if no problem occurs returns true
     */
    public static boolean addPrerequisite(int id, int[] lectures)
    {
        Arrays.sort(lectures);
        return __lectures.get(id).__prerequisites.add(Arrays.stream(removeDuplicates(lectures)).boxed().toArray(Integer[]::new));
    }

    // Computational complexity is O(n*n), it is not important since the data wont be huge
    /**
     * Complexity: O(N^2)
     * Precondition: Taken lectures can be mixed and can contain duplicates
     * Postcondition: Returns true if the lecture founded by id, can be taken
     * 
     * @param id the id of the  lecture
     * @param takenLectures the array of integers represents taken lecture ids
     * @return true is prerequisite satisfies
     */
    public static boolean satisfiesPrerequisite(int id, int[] takenLectures)
    {
        int[] takenLecturesCopy = Arrays.copyOf(takenLectures, takenLectures.length);
        Arrays.sort(takenLecturesCopy);
        takenLecturesCopy = removeDuplicates(takenLecturesCopy);
        
        for (Integer[] required : __lectures.get(id).__prerequisites)
            if (contains(takenLecturesCopy, required))
                return true;
            

        return false;
    }

    /**
     * Complexity: O(N)
     * Precondition: Takes two sorted arrays that does not contains duplicates
     * Postcondition: Returns true if every element on required array is located inside taken array
     * @param taken ids of the taken lectures
     * @param required required lectures
     * @return
     */
    private static boolean contains(int[] taken, Integer[] required)
    {
        if (taken.length < required.length)
            return false;

        int pr = 0;

        for (int i = 0; i < taken.length; i++)
            if (taken[i] == required[pr])
                pr++;

        if (pr == required.length)
            return true;

        return false;
    }

    /**
     * Precondition: Requires a sorted array to operate
     * Postcondition: Removes the duplicates of the array, returns a new array.
     * @param array needs to be sorted
     * @return new array
     */
    private static int[] removeDuplicates(int[] array)
    {
        if (array == null)
            return null;
        else if (array.length < 2)
            return array;
        
        int[] newArray = new int[array.length];
        int ptr = 0;

        for (int i = 0; i < array.length - 1; i++)
            if (array[i] != array[i + 1])
                newArray[ptr++] = array[i];
        
        return Arrays.copyOf(newArray, ptr + 1);
    }

    /**
     * @return short name of the lecture
     */
    public String getShortName()
    {
        return __sname;
    }

    /**
     * @return long name of the lecture
     */
    public String getLongName()
    {
        return __lname;
    }
    /**
     * @return id of the lecture
     */
    public int getId()
    {
        return __id;
    }
}