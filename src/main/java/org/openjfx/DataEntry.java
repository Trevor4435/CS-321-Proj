package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DataEntry {

    private static int refNumber = 1;

    public void showScreen(){

        // Take the stage declared in App.java and prepare a fresh scene to go into it before being showed again.
        Stage stage = App.permStage;
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        // Declare the label and field for an alien number to be entered.
        Label aNumber = new Label("Alien Number: ");
        grid.add(aNumber, 0, 0);
        TextField alienNumber = new TextField();
        grid.add(alienNumber, 1, 0);


        Label fName = new Label("First Name: \n (No special characters or numbers)");
        grid.add(fName, 0, 1);
        TextField firstName = new TextField();
        grid.add(firstName, 1, 1);

        // Dont forget this is meant to be split and handled as an Array
        Label mNames = new Label("Middle Name: \n(Seperate with a ',' or leave empty)");
        grid.add(mNames, 0, 2);
        TextField middleNames = new TextField();
        grid.add(middleNames, 1, 2);

        Label lName = new Label("Last Name: \n (No special characters or numbers)");
        grid.add(lName, 0, 3);
        TextField lastName = new TextField();
        grid.add(lastName, 1, 3);

        Label bDay = new Label("Date of Birth: \n (Please enter in MM/DD/YYYY)");
        grid.add(bDay, 0, 4);
        TextField DOB = new TextField();
        grid.add(DOB, 1, 4);

        Label mAddress = new Label("Mailing Address for Delivery: ");
        grid.add(mAddress, 0, 5);
        TextField mailingAddress = new TextField();
        grid.add(mailingAddress, 1, 5);

        Label error = new Label();
        grid.add(error, 0, 6);
        error.setTextFill(Color.RED);


        Button submit = new Button("Submit");
        grid.add(submit, 2, 9);

        Button exit = new Button("Exit");
        grid.add(exit, 0, 9);


        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                stage.setScene(App.homeScene);
            }
        });

        // This is out here to stop a possibility of a memory leak my mashing submit
        Business BO = Business.createNewBO();

        
        // This is where we are finally going to start checking for input.
        // These are very basic checks just to make sure input exists where it's required.
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                // Reset the error text each time submit is pressed
                error.setText("");

                // Only the middle name is permitted to be empty, no other field may be empty
                if(alienNumber.getText().isEmpty()){
                    error.setText("ERROR: Empty Alien Number!");
                    return;
                }
                else if(firstName.getText().isEmpty()){
                    error.setText("ERROR: Empty First Name!");
                    return;
                }
                else if(lastName.getText().isEmpty()){
                    error.setText("ERROR: Empty Last Name!");
                    return;
                }
                else if(DOB.getText().isEmpty()){
                    error.setText("ERROR: Empty Date of Birth!");
                    return;
                }
                else if(mailingAddress.getText().isEmpty()){
                    error.setText("ERROR: Empty Mailing Address!");
                    return;
                }

                BO.setAlienNumber(alienNumber.getText());
                BO.setRefNumber(Integer.toString(refNumber));
                BO.setFirstName(firstName.getText());
                BO.setLastName(lastName.getText());

                // Split on ", " to permit middle names with spaces
                BO.setMiddleName(middleNames.getText().split(", "));
                
                BO.setAddress(mailingAddress.getText());
                BO.setDOB(DOB.getText());

                // Here we handle the possible errors and rejections from the business rules
                switch (BO.validate()) {
                    // All of these return statements simply escape the event handler to prevent further processing
                    // Invalid Reference Number
                    case -1:
                        error.setText("Internal Error! Please Notify Support!");
                        return;
                    // Invalid Alien Number
                    case -2:
                        error.setText("Invalid Alien Number!\n Please Try Again.");
                        return;
                    // Invalid last name somehow
                    case -3:
                        error.setText("Invalid Last Name!\n Please Try Again.");
                        return;
                    // Invalid first name
                    case -4:
                        error.setText("Invalid First Name!\n Please Try Again.");
                        return;
                    // Invalid middle name
                    case -5:
                        error.setText("Invalid Middle Name!\n Please Try Again.");
                        return;
                    case -6:
                        error.setText("Invalid Mailing Address!\n Please Try Again.");
                        return;
                    case -7:
                        error.setText("Invalid Date Of Birth!\n Please Try Again.");
                        return;
                    // Any unexpected output from Validate is "logged" and the addition of the request is aborted
                   default:
                        System.out.println("ERROR ERROR, Invalid Validate Result!");
                        error.setText("Internal Error! \n Please Notify Support!");
                        return; 
                    // The rare occurance of us accepting a valid request and saving it. 
                    case 1:
                        BO.saveFile();

                        // Now we need to add it to workflow
                        int status = WorkFlow.addWorkFlow(String.valueOf(refNumber), "Reviewer");

                        // If the workflow failed to be added, notify and "log"
                        if(status != 1){
                            error.setText("Internal Error! Please notify support with your reference number: " + refNumber);
                            DataEntry.refNumber++;
                            return;
                        }
                        
                        // Increment the stored reference number.
                        DataEntry.refNumber++;
                        // Return to the home screen without detonating the JVM.
                        stage.setScene(App.homeScene);
                }
            }
        });

        // Show our beautiful screen
        stage.setScene(scene);
        stage.show();
    }
}
