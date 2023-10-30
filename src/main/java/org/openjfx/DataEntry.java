package org.openjfx;

import java.util.Arrays;

import javafx.application.Platform;
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

    public void showScreen(){

        int ref = App.refNumber;

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


        Label fName = new Label("First Name: ");
        grid.add(fName, 0, 1);
        TextField firstName = new TextField();
        grid.add(firstName, 1, 1);

        // Dont forget this is meant to be split and handled as an Array
        Label mNames = new Label("Middle Name: \n(Seperate with a ',' or leave empty)");
        grid.add(mNames, 0, 2);
        TextField middleNames = new TextField();
        grid.add(middleNames, 1, 2);

        Label lName = new Label("Last Name: ");
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
                Platform.exit();
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
                }
                else if(firstName.getText().isEmpty()){
                    error.setText("ERROR: Empty First Name!");
                }
                else if(lastName.getText().isEmpty()){
                    error.setText("ERROR: Empty Last Name!");
                }
                else if(DOB.getText().isEmpty()){
                    error.setText("ERROR: Empty Date of Birth!");
                }
                else if(mailingAddress.getText().isEmpty()){
                    error.setText("ERROR: Empty Mailing Address!");
                }

                // TODO Add in parsing for the error returned by the BO validation metric.


                BO.setAlienNumber(alienNumber.getText());
                BO.setRefNumber(Integer.toString(ref));
                BO.setFirstName(firstName.getText());
                BO.setLastName(lastName.getText());

                // Split on ", " to permit middle names with spaces
                BO.setMiddleName(middleNames.getText().split(", "));
                
                BO.setAddress(mailingAddress.getText());
                BO.setDOB(DOB.getText());

                switch (BO.validate()) {
                    case -1:
                        
                        break;
                
                    default:
                        System.out.println("ERROR ERROR, SWITCH DEFAULT OCCURANCE");
                }
                
                
            }
        });

        stage.setScene(scene);
        stage.show();

    }
}
