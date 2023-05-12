import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        int[] arr1 = {1, 4, 2, 6, 3, 8, 5};
        int[] arr2 = {4, 6, 1, 3, 5, 2, 8};

        Set<Integer> set1 = new HashSet<>();

        for (int i : arr1) 
        {
            set1.add(i);
        }

        Set<Integer> set2 = new HashSet<>();

        for (int i : arr2) 
        {
            set2.add(i);
        }

        if (set1.equals(set2)) 
        {
            System.out.println("Same set of numbers was found in both arrays!");
        } 
        else 
        {
            System.out.println("Different set of numbers was found in both arrays!");
        }
    }
}