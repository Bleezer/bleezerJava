import edu.duke.FileResource;
import org.junit.Test;

import java.util.HashMap;

public class CodonCount {

    private HashMap<String,Integer> countDNA;

    public CodonCount(){
        countDNA = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna){
        int currPos = start;
        while (dna.length() >= currPos+3){
            String currDNA = dna.substring(currPos,currPos+3);
            if (!countDNA.containsKey(currDNA)){
                countDNA.put(currDNA,1);
            }else{
                countDNA.put(currDNA,countDNA.get(currDNA)+1);
            }
            currPos += 3;
        }
    }

    public String getMostCommonCodon(){
        String mostCommonDNA = "";
        int maxOccurance = 0;
        for (String s : countDNA.keySet()){
            if (countDNA.get(s) > maxOccurance){
                maxOccurance = countDNA.get(s);
                mostCommonDNA = s;
            }
        }
        return mostCommonDNA;
    }

    public void printCodonCounts(int start, int end){
        for (String s : countDNA.keySet()){
            if (start <= countDNA.get(s) && countDNA.get(s) <= end){
                System.out.println(s + "\t" + countDNA.get(s));
            }
        }
    }

    @Test
    public void test(){
        countDNA.clear();
        FileResource fr = new FileResource();

        int start = 0;

        for (String line : fr.lines()){
            String currLine = line.toUpperCase().trim();
            buildCodonMap(start,currLine);
            printCodonCounts(7,7);
            System.out.println("Most common is: " + getMostCommonCodon());
        }

    }

}
