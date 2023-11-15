package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Approval {
    public final boolean DEBUG = true; 

    public void showScreen(){

        // Stage set-up
        Stage stage = App.permStage;

        //Load scene
        GridPane grid0 = new GridPane();
        grid0.setHgap(20);
        grid0.setVgap(20);
        grid0.setPadding(new Insets(25,25,25,25));

        Label msg1 = new Label("Press the 'Load' to retreive data");
        Button load = new Button("Load");

        grid0.add(msg1, 5, 1);
        grid0.add(load, 5, 3);
        Scene loadScene = new Scene(grid0, 400, 400);

        // For option or error
        GridPane grid = new GridPane();

/*Error on WF */
        Label error = new Label("Nothing to work on!");
        grid.add(error, 0, 6);
        error.setTextFill(Color.RED);
        Button menu3 = new Button("Exit");

        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(error,menu3);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setSpacing(20);

        Scene errorScene = new Scene(vbox3, 400, 400);

//Options decide
        

        Scene approvalOptionsScene = new Scene(grid, 400, 400);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));


        // Display current data working if have
        Label greeting = new Label ("Working on Reference #");
        //greeting.setAlignment(Pos.CENTER);

        Label alienN = new Label("Alien number: None");
        Label firstN = new Label("First name: None");
        Label lastN = new Label("Last name: None");
        Label dob = new Label("Date of birth: None");
        Label mail = new Label("Mailing: None");

        Label[] arr = {greeting, alienN, firstN, lastN, dob, mail};

        for(int i = 0; i < arr.length; i++){
                grid.addRow(i, arr[i]);
        }
       

/* Reject case */
        // Rejected button on main screen
        Button rejectB = new Button("REJECTED");
        grid.add(rejectB, 0, 9);

        //Nodes
        VBox vbox1 = new VBox();
        Label rejectMsg = new Label("Process the data back to reviewer!");
        Button menu1 = new Button("Menu");

        vbox1.getChildren().addAll(rejectMsg,menu1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(20);
        
        Scene rejectScene = new Scene(vbox1, 400, 400);
        

/* Approve case */
        //Approved button on main screen
        Button approveB = new Button("APPROVED");
        grid.add(approveB, 5, 9);

        // Message 
        VBox vbox2 = new VBox();
        Label approveMsg = new Label("Congratulation! Your request is processing to mailing department");
        Button menu2 = new Button("Menu");

        vbox2.getChildren().addAll(approveMsg,menu2);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(20);

        Scene approveScene = new Scene(vbox2, 400, 400);

/* Handle actions */
        rejectB.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.setScene(rejectScene);
        	}
        });

        approveB.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.setScene(approveScene);
        	}
        });

        menu1.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.setScene(App.homeScene);
        	}
        });

        menu2.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.setScene(App.homeScene);
        	}
        });

        menu3.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		stage.setScene(App.homeScene);
        	}
        });







        load.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
                        //No item on WF assigned
                        if(DEBUG){
                                stage.setScene(errorScene);   
                        }

                        else{
                                stage.setScene(approvalOptionsScene);
                        }
        		
        	}
        });

        





        // Show the screen
        stage.setScene(loadScene);
        stage.setTitle("Approval screen");
        stage.show();
    }
}
