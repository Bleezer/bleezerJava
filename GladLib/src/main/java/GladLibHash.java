import edu.duke.FileResource;
import edu.duke.URLResource;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibHash {

    private HashMap<String, ArrayList<String>> myMap;

//    private ArrayList<String> adjectiveList;
//    private ArrayList<String> nounList;
//    private ArrayList<String> colorList;
//    private ArrayList<String> countryList;
//    private ArrayList<String> nameList;
//    private ArrayList<String> animalList;
//    private ArrayList<String> timeList;
//    private ArrayList<String> verbList;
//    private ArrayList<String> fruitList;

    private ArrayList<String> usedList;

    private ArrayList<String> usedFiles;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "C:\\workspace\\Java\\stuffs\\week2\\data";

    public GladLibHash(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedList = new ArrayList<String>();
        usedFiles = new ArrayList<String>();
    }

    public GladLibHash(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        usedList = new ArrayList<String>();
        usedFiles = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {
        String[] labels = {"country","noun","animal","adjective","name","color","timeframe","verb","fruit"};


        for (String s : labels){
            String path = source+"\\" + s + ".txt";
            ArrayList<String> list = readIt(path);
            myMap.put(s,list);
        }
//        adjectiveList= readIt(source+"\\adjective.txt");
//        nounList = readIt(source+"\\noun.txt");
//        colorList = readIt(source+"\\color.txt");
//        countryList = readIt(source+"\\country.txt");
//        nameList = readIt(source+"\\name.txt");
//        animalList = readIt(source+"\\animal.txt");
//        timeList = readIt(source+"\\timeframe.txt");
//        verbList = readIt(source + "\\verb.txt");
//        fruitList = readIt(source + "\\fruit.txt");


    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
//        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String currSub = "";
        while (true) {
            currSub = getSubstitute(w.substring(first+1,last));
            if (usedList.indexOf(currSub) == -1){
                usedList.add(currSub);
                usedFiles.add(w.substring(first+1,last));
                break;
            }
        }
        String sub = currSub;
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap(){
        int count = 0;
        for (String s : myMap.keySet()){
            count += 1;
        }
        return count;
    }

    public int totalWordsConsidered(){
        int count = 0;
        for (String s : myMap.keySet()){
            if (usedFiles.contains(s)){
                count += 1;
            }
        }
        return count;
    }


    public void makeStory(){
        usedList.clear();
        System.out.println("\n");
        String story = fromTemplate("C:\\workspace\\Java\\stuffs\\week2\\data\\madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Used words for replacment: " + usedList.size());
        System.out.println("Count of all available words: " + totalWordsInMap());
        System.out.println("Count of all available words from used category: " + totalWordsConsidered());
    }

    public static void main(String[] args){
        GladLibHash run = new GladLibHash();
        run.makeStory();
    }

}
