import org.junit.Test;

public class TestSimple{
    @Test
    public void print() {
        Simple item = new Simple(3, "blue");
        System.out.println(item);
    }
}