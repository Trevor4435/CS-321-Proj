import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class ValidationTester {
    
    Business BusinessObject = new Business();

    @Test
    @DisplayName("Hello World Test")
    void HelloTest(){
        String test = BusinessObject.Hello();
        assertTrue(test.equals("Hello World!"));
    }
}
