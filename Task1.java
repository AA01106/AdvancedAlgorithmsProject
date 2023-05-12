import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        Stack<Character> stk = new Stack<Character>();
        char[] arr = {'a', 'b', 'c', 'a', 'd', 'e', 'e', 'c'};

        Set<Character> set = new HashSet<>();
        for (char c : arr) 
        {
            if (!set.contains(c)) 
            {
                set.add(c);
                stk.push(c);
            }
        }

        char[] result = new char[stk.size()];
        int i = 0;
        while (!stk.isEmpty()) 
        {
            result[i++] = stk.pop();
        }

        System.out.println("Here is the array after removing duplicated characters: ");
        for (char c : result) 
        {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}