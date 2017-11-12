import java.util.ArrayList;
import edu.duke.*;
import org.junit.Test;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;

    public CharactersInPlay(){
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }

    private void update(String person){
        int index = characters.indexOf(person);
        if (index == -1){
            characters.add(person);
            counts.add(1);
        }else {
            int value = counts.get(index);
            counts.set(index,value+1);
        }
    }

    public void findAllCharacters(){
        characters.clear();
        counts.clear();

        FileResource fr = new FileResource();

        for (String line : fr.lines()){
            int idxOfPoint = line.indexOf(".");
            if (idxOfPoint != -1){
                String person = line.substring(0,idxOfPoint);
                update(person);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2){
        for (int i=0; i < counts.size(); i++){
            int currCount = counts.get(i);
            if (num1 <= currCount && currCount < num2){
                System.out.println(characters.get(i) + " " + counts.get(i));
            }
        }
    }

    @Test
    public void test(){
        findAllCharacters();
        for (int i=0; i < characters.size(); i++){
            if (counts.get(i) > 1){
                System.out.println(characters.get(i) + " " + counts.get(i));
            }
        }
        System.out.println("--------------------------------------------------------------------------------------");
        charactersWithNumParts(10,15);
    }
}
