import edu.duke.*;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> wordMap;

    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fName = f.getName();

        for (String word : fr.words()){
            if (!wordMap.containsKey(word)) {
                ArrayList<String> wordList = new ArrayList<String>();
                wordList.add(fName);
                wordMap.put(word, wordList);
            }
            else {
                ArrayList<String> wordList = new ArrayList<String>();
                wordList = wordMap.get(word);
                if (!wordList.contains(fName)) {
                    wordList.add(fName);
                    wordMap.put(word, wordList);
                }
            }
        }
    }

    public void buildWordFileMap(){
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        String filename = "";

        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber(){
        int maxCount = 0;
        for (String s : wordMap.keySet()){
            int currCount = wordMap.get(s).size();
            if (currCount > maxCount){
                maxCount = currCount;
            }
        }
        return maxCount;
    }

    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> givenAppearence = new ArrayList<String>();
        for (String s : wordMap.keySet()){
            if (number == wordMap.get(s).size()){
                if (!givenAppearence.contains(s)) {
                    givenAppearence.add(s);
                }
            }
        }
        return givenAppearence;
    }

    public void printFilesIn(String word){
        for (String s : wordMap.get(word)){
            System.out.println(s);
        }
    }

    @Test
    public void test(){
        buildWordFileMap();
        System.out.println("Max Number: " + maxNumber());
        printFilesIn("tree");
        System.out.println(wordsInNumFiles(4));
        System.out.println(wordsInNumFiles(4).size());

    }
}
