import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class ValidationTester {

    @Test
    @DisplayName("Attempt to create new BO.")
    void newBOTest(){
        Business house = Business.createNewBO();
        assertTrue(house instanceof Business);
    }

    @Test
    @DisplayName("Attempt to set a name to the business object.")
    void setBOName(){
        Business house = Business.createNewBO();
        house.setFirstName("John");
        String output = house.getFirstName();
        assertTrue(output.equals("John"));
    }

    @Test
    @DisplayName("Invalid Reference number to WorkFlow")
    void invalidWorkAdd(){
        int status = WorkFlow.addWorkFlow(-1, "Reviewer");
        assertTrue(status == -1);
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

    @Test
    @DisplayName("Attempt to read an empty Reviewer list.")
    void pullEmptyReview(){
        String output = WorkFlow.getNextRef("Reviewer");
        assertTrue(output.equals("Coffee Time"));
    }
}
