import edu.duke.FileResource;
import org.junit.Test;

/**
 * Created by Eylon on 2017.11.05..
 */
public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts){
        for (String word : resource.words()){
            int currLength = word.length();

            char chFirst = word.charAt(0);
            char chLast = word.charAt(currLength-1);

            if (! Character.isLetter(chFirst)){
                currLength = currLength-1;
            }
            if (! Character.isLetter(chLast) && currLength != 0){
                currLength = currLength-1;
            }
            System.out.println(word);

            counts[currLength] += 1;
        }
    }

    @Test
    public void testCountWordLengths(){
        int[] counts = new int[50];
        FileResource fr = new FileResource();
        countWordLengths(fr,counts);
        for (int k=1;k<counts.length;k++)
            if (counts[k] > 0){
                System.out.println(counts[k] + " with length " + k);
            }

    }

}
