import edu.duke.*;
import org.junit.Test;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        myWords.clear();
        myFreqs.clear();

        FileResource resource = new FileResource();

        for (String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }

    public int findIndexOfMax(){
        int maxValIndex = 0;
        int maxVal = 0;
        for (int i=0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > maxVal){
                maxVal = myFreqs.get(i);
                maxValIndex = i;
            }
        }
        return maxValIndex;
    }

    @Test
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for (int k=0; k < myWords.size(); k++){
            System.out.println(myFreqs.get(k)+" "+myWords.get(k));
        }
        int idx = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(idx) + " " + myFreqs.get(idx));

    }
}
