import java.util.*;
import edu.duke.*;
import org.junit.Test;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedPart = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            char currChar = message.charAt(i);
            slicedPart.append(currChar);
        }
        return slicedPart.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i < klength; i++){
            String currPart = sliceString(encrypted,i,klength);
            CaesarCracker cr = new CaesarCracker(mostCommon);
            int currKey = cr.getKey(currPart);
            key[i] = currKey;
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()){
            line = line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        ArrayList<String> wordsOfMessage = new ArrayList<String>();
        for (String word : message.split("\\W")){
            wordsOfMessage.add(word);
        }
        for (String s : wordsOfMessage){
            if (dictionary.contains(s.toLowerCase())){
                count += 1;
            }
        }
        return count;
    }

//    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonCharIn){
//        HashMap<String,Integer> messageMap= new HashMap<String,Integer>();
//        for (int i = 1; i <= 100 && i < encrypted.length(); i++){
//            int[] currKeys = tryKeyLength(encrypted,i,mostCommonCharIn);
//            VigenereCipher vc = new VigenereCipher(currKeys);
//            String currMessage = vc.decrypt(encrypted);
//            int currWordMatches = countWords(currMessage,dictionary);
//            messageMap.put(currMessage,currWordMatches);
//        }
//        int maxCount = 0;
//        String message = "";
//        for (String s : messageMap.keySet()){
//            if (maxCount < messageMap.get(s)){
//                maxCount =  messageMap.get(s);
//                message = s;
//            }
//        }
//        System.out.println("Max valid words: " + maxCount);
//        return message;
//    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommonCharIn){
        HashMap<Integer,Integer> messageMap= new HashMap<Integer,Integer>();
        for (int i = 1; i <= 100 && i < encrypted.length(); i++){
            int[] currKeys = tryKeyLength(encrypted,i,mostCommonCharIn);
            VigenereCipher vc = new VigenereCipher(currKeys);
            String currMessage = vc.decrypt(encrypted);
            int currWordMatches = countWords(currMessage,dictionary);
            messageMap.put(i,currWordMatches);
        }
        int maxCount = 0;
        int validKeyLength = 0;
        for (Integer s : messageMap.keySet()){
            if (maxCount < messageMap.get(s)){
                maxCount =  messageMap.get(s);
                validKeyLength = s;
            }
            if (s == 38){
                System.out.println("Valid words at " + s + ": " + messageMap.get(s));
            }
        }
        System.out.println("Max valid key length: " + validKeyLength);
        System.out.println("Max valid words: " + maxCount);

        int[] validKeys = tryKeyLength(encrypted,validKeyLength,mostCommonCharIn);
        VigenereCipher vc = new VigenereCipher(validKeys);
        return vc.decrypt(encrypted);
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> countChar = new HashMap<Character, Integer>();
        for (String word : dictionary){
            for (int i=0; i<word.length();i++){
                char currCh = word.charAt(i);
                if (!countChar.containsKey(currCh)) {
                    countChar.put(currCh,1);
                }else {
                    countChar.put(currCh,countChar.get(currCh)+1);
                }
            }
        }
        char mostCommonChar = highestChar(countChar);
        return mostCommonChar;
    }

    private char highestChar(HashMap<Character, Integer> map){
        char maxCh ='e';
        int maxLetter = 0;
        for(char ch : map.keySet()){
            int value = map.get(ch);
            if(maxLetter == 0){
                maxLetter = value;
                maxCh = ch;
            }
            else if(value > maxLetter){
                maxLetter = value;
                maxCh = ch;
            }
        }
        return maxCh;
    }


    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int countMaxWords = 0;
        int maxWords = 0;
        String message = "";
        String language = "";
        for(String s : languages.keySet()){
            HashSet<String> dict = languages.get(s);
            String decrypted =  breakForLanguage(encrypted, dict, mostCommonCharIn(dict));
            maxWords = countWords(decrypted, dict);//this will give you maxWord in breakForLanguage
            if(maxWords > countMaxWords){
                countMaxWords = maxWords;
                message = decrypted;
                language = s;
            }
        }
        System.out.println(language);
        return message;
    }



    @Test
    public void breakVigenere () {
        FileResource fr = new FileResource();// remember call file given  here
        String encrypted = fr.asString();

        String[] languageList = new String[]{"Danish","Dutch","English","French","German","Italian","Portuguese","Spanish"};
        HashMap<String,HashSet<String>> myMap = new HashMap<String,HashSet<String>>();
        for(int i = 0; i < languageList.length; i++){
            String language = languageList[i];
            FileResource fr2 = new FileResource("C:\\workspace\\Java\\bleezerJava\\c2w4\\src\\main\\resources\\dictionaries\\"+language); // fri = fr1, fr2,fr3
            HashSet<String> dictionary = readDictionary(fr2);
            myMap.put(language,dictionary);
        }


        String decrypted = breakForAllLangs(encrypted, myMap);
        System.out.println(decrypted);


//        FileResource fr = new FileResource();
//        String encrypted = fr.asString();
//        FileResource fr2 = new FileResource("C:\\workspace\\Java\\bleezerJava\\c2w4\\src\\main\\resources\\dictionaries\\English");
//        HashSet<String> dictionary = readDictionary(fr2);
//        String message = breakForLanguage(encrypted,dictionary,'e');
//        System.out.println(message);

//        int klength = 4;
//        int[] keys = tryKeyLength(encrypted,klength,'e');
//        VigenereCipher vc = new VigenereCipher(keys);
//        String message = vc.decrypt(encrypted);
//        System.out.println(message);
    }
}
