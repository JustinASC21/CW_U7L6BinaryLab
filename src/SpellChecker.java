import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SpellChecker
{
    private ArrayList<String> dictionary;

    // constructor; uses try-catch syntax which we haven't discussed!
    public SpellChecker()
    {
        importDictionary();
    }

    public ArrayList<String> getDictionary()
    {
        return dictionary;
    }

    /** This uses LINEAR search to find a word in the dictionary ArrayList and also
     * prints out the number of words checked.
     *
     * Instead of returning the index the word is found, it simply returns TRUE
     * if the word is found, and FALSE otherwise.
     */
    public boolean linearSpellCheck(String word)
    {
        int numChecks = 0;

        for(int i=0; i < dictionary.size(); i++)
        {
            numChecks++;

            if (word.equals(dictionary.get(i)))
            {
                System.out.println("-- LINEAR SEARCH: Number of words checked (loops/runtime): " + numChecks);
                return true;
            }
        }
        System.out.println("LINEAR SEARCH: Number of words checked (loops/runtime): " + numChecks);
        return false;
    }

    /** This uses BINARY search to find a word in the dictionary ArrayList and also
     * prints out the number of words checked.
     *
     * Instead of returning the index the word is found, it simply returns TRUE
     * if the word is found, and FALSE otherwise.
     */
    public boolean binarySpellCheck(String word)
    {
        int numChecks = 0;
        int firstInd = 0;
        int lastInd = dictionary.size() - 1;

        while (firstInd <= lastInd) {
            numChecks++;
            int mid = ((firstInd + lastInd) / 2) + 1;
            if (dictionary.get(mid).compareTo(word) < 0) {
                firstInd = mid + 1;
            }
            else if (dictionary.get(mid).compareTo(word) > 0) {

                lastInd = mid - 1 ;
            }
            else {
                System.out.println("-- BINARY SEARCH: Number of words checked (loops/runtime): " + numChecks);
                return true;
            }
            System.out.println("test");
        }
        System.out.println("BINARY SEARCH: Number of words checked (loops/runtime): " + numChecks);
        return false;
    }

    public boolean myBinarySearch(String word) {
        int mid = dictionary.size() / 2;
        while (mid != 1) {
            if (word.compareTo(dictionary.get(mid)) < 0) {
            dictionary = dictionary.removeRange(0,mid);
            }
        }
    }
    public void importDictionary()
    {
        String[] tmp = null;
        try
        {
            tmp = readLines("src/dictionary.txt"); //readLines method is below
        }
        catch(IOException e)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access "+e.getMessage());
        }
        dictionary = new ArrayList<String>(Arrays.asList(tmp));
    }

    public static String[] readLines(String filename) throws IOException
    {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null)
        {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
}