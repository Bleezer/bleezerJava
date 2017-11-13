import edu.duke.FileResource;
import org.junit.Test;

public class Tester {

    @Test
    public void CaesarCipherTest(){
        CaesarCipher cc = new CaesarCipher(1);
        FileResource fr = new FileResource("C:\\workspace\\Java\\stuffs\\c2w4\\VigenereTestData\\titus-small.txt");
        String message = fr.asString();
        String encrypted = cc.encrypt(message);
        System.out.println(encrypted);
        System.out.println(cc.decrypt(encrypted));
    }

    @Test
    public void CaesarCrackerTest(){
        char mostCommonChar = 'a';
        CaesarCracker cr = new CaesarCracker(mostCommonChar);
        FileResource fr = new FileResource("C:\\workspace\\Java\\stuffs\\c2w4\\VigenereTestData\\oslusiadas_key17.txt");
        String encrypted = fr.asString();
        int key =cr.getKey(encrypted);
        System.out.println(key);
        System.out.println(cr.decrypt(encrypted));
    }

    @Test
    public void VigenereCipherTest(){
        int[] keys = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(keys);
        FileResource fr = new FileResource("C:\\workspace\\Java\\stuffs\\c2w4\\VigenereTestData\\titus-small.txt");
        String message = fr.asString();
        String encrypted = vc.encrypt(message);
        System.out.println(encrypted);
        System.out.println(vc.decrypt(encrypted));
    }

    @Test
    public void VBsliceStringTest(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }

    @Test
    public void VBtryKeyLengthTest(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("C:\\workspace\\Java\\stuffs\\c2w4\\VigenereProgram\\messages\\secretmessage1.txt");
        String encrypted = fr.asString();
        int[] keys = vb.tryKeyLength(encrypted,4,'e');
        for (int i : keys){
            System.out.println(i);
        }
    }

//    @Test
//    public void VBbreakVigenereTest(){
//        VigenereBreaker vb = new VigenereBreaker();
//        vb.breakVigenere();
//    }

}
