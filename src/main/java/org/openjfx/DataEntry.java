package org.openjfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DataEntry {

    public void showScreen(){

        // Take the stage declared in App.java and prepare a fresh scene to go into it before being showed again.
        Stage stage = App.permStage;
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Label aNumber = new Label("Alien Number: ");
        grid.add(aNumber, 0, 0);
        TextField alienNumber = new TextField();
        grid.add(alienNumber, 1, 0);

        Button exit = new Button("Exit");
        grid.add(exit, 0, 5);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Platform.exit();
            }
        });

        stage.setScene(scene);
        stage.show();

    }
}
