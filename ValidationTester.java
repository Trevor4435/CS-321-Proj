import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class ValidationTester {

    @Test
    @DisplayName("Attempt to create new BO.")
    void newBOTest(){
        Business house = Business.CreateNewBO();
        assertTrue(house != null);
    }

    @Test
    @DisplayName("Attempt to add to the Reviewer's work load.")
    void addReviewWork(){
        int status = WorkFlow.addWorkFlow(123, "Reviewer");
        assertTrue(status == 1);
    }

    @Test
    @DisplayName("Attempt to read back that work.")
    void pullReviewWork(){
        String output = WorkFlow.getNextRef("Reviewer");
        assertTrue(output.equals("123"));
    }
}
