package calculator;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScannerTest {

    @Test
    public void testCreateFailNull(){
        try {
            Scanner s = new Scanner(null);
        }catch (Exception exception){
            Assertions.assertEquals(RuntimeException.class, exception.getClass());
        }

    }
}
