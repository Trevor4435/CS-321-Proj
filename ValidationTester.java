import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class ValidationTester {

    @Test
    @DisplayName("Attempt to create new BO.")
    void NewBOTest(){
        Business house = Business.CreateNewBO();
        assertTrue(house != null);
    }

    @Test
    @DisplayName("Attempt to add to the Reviewer's work load.")
    void AddReviewWork(){
        int status = WorkFlow.addWorkFlow(123, "Reviewer");
        assertTrue(status == 1);
    }
}
