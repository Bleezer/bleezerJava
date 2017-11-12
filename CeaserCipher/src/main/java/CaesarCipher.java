import edu.duke.*;

public class CaesarCipher {

//    private String alphabet;
//    private String shiftedAlphabet;
//
//    public CaesarCipher(int key){
//        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
//    }

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);


        for (int i =0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);


        for (int i =0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));

            if (idx != -1) {
                char newChar = currChar;
                if ((i+1)%2 != 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                }else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                if (Character.isLowerCase(currChar)) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0;k<message.length();k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public String decrypt(String encrypted){
//        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex -4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return encrypt(encrypted,26-dkey);
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k<vals.length; k++){
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String halfOfString(String message, int start) {
        String resMessage = "";
        for (int i=0; i<message.length();i++){
            if (start == 0 && i%2 == 0){
                resMessage += message.charAt(i);
            }else if (start == 1 && i%2 == 1) {
                resMessage += message.charAt(i);
            }
        }
        return resMessage;
    }

    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex -4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }

    public String decryptTwoKeys(String encrypted){
        String decrypted;

        String firstPart = halfOfString(encrypted, 0);
        String secondPart = halfOfString(encrypted,1);

        int key1 = getKey(firstPart);
        int key2 = getKey(secondPart);

        System.out.println("Key1: " + key1 + " Key2: " + key2);

        String firstDe = encrypt(firstPart,26-key1);
        String secondDe = encrypt(secondPart,26-key2);


        int count1 = 0;
        int count2 = 0;

        decrypted = encryptTwoKeys(encrypted,26-key1,26-key2);
        return decrypted;
    }

    ///// TEST

    public void testCaesar() {
//        int key = 17;
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String encrypted = encrypt(message,key);
//        System.out.println(encrypted);
//        String dectypted = encrypt(encrypted, 26-key);
//        System.out.println(dectypted);

        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
//        System.out.println(decrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS! tttttttttt"));
//
//        System.out.println(halfOfString("Qbkm Zgis", 1));

//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        int key = 17;
//        String encrypted = encrypt(message, key);
//        System.out.println("key is " + key + "\n" + encrypted);

        System.out.println(encryptTwoKeys ("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8));

//        System.out.println(decryptTwoKeys(encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees",15,7)));

//        System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",26-14,26-24));

//        System.out.println(decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));

    }

    public static void main(String[] args) {
        CaesarCipher run = new CaesarCipher();
        run.testCaesar();
    }
}
