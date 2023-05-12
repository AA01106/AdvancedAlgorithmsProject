import java.util.*;

public class SpellChecker 
{
    private Set<String> lexicon;

    public SpellChecker(Set<String> lexicon) 
    {
        this.lexicon = lexicon;
    }

    public List<String> check(String s) 
    {
        List<String> result = new ArrayList<>();
        if (lexicon.contains(s)) 
        {
            result.add(s);
        } else {
            result.addAll(checkSwaps(s));
            result.addAll(checkInsertions(s));
            result.addAll(checkDeletions(s));
            result.addAll(checkReplacements(s));
        }
        return result;
    }

    private List<String> checkSwaps(String s) 
    {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) 
        {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String swapped = new String(chars);
            if (lexicon.contains(swapped)) 
            {
                result.add(swapped);
            }
            chars[i + 1] = chars[i];
            chars[i] = temp;
        }
        return result;
    }

    private List<String> checkInsertions(String s) 
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length() + 1; i++) 
        {
            for (char c = 'a'; c <= 'z'; c++) 
            {
                String inserted = s.substring(0, i) + c + s.substring(i);
                if (lexicon.contains(inserted)) 
                {
                    result.add(inserted);
                }
            }
        }
        return result;
    }

    private List<String> checkDeletions(String s) 
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) 
        {
            String deleted = s.substring(0, i) + s.substring(i + 1);
            if (lexicon.contains(deleted)) 
            {
                result.add(deleted);
            }
        }
        return result;
    }

    private List<String> checkReplacements(String s) 
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) 
        {
            for (char c = 'a'; c <= 'z'; c++) 
            {
                if (c != s.charAt(i)) 
                {
                    String replaced = s.substring(0, i) + c + s.substring(i + 1);
                    if (lexicon.contains(replaced)) 
                    {
                        result.add(replaced);
                    }
                }
            }
        }
        return result;
    }
}