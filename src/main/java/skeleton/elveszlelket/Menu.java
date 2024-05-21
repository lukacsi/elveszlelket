package skeleton.elveszlelket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skeleton.elveszlelket.controller.GameMan;;



public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ELVESZLELKET THE GAME");

        Label titleLabel = new Label("ELVESZLELKET");
        titleLabel.setStyle("-fx-font-family: 'Serif'; -fx-font-size: 24; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        Label titleLabel2 = new Label("THE GAME");
        titleLabel2.setStyle("-fx-font-family: 'Serif'; -fx-font-size: 24; -fx-font-weight: bold;");
        titleLabel2.setAlignment(Pos.CENTER);
        
        Region spacer = new Region();
        spacer.setMinHeight(20);

        Button playButton = new Button("PLAY");
        playButton.setStyle("-fx-font-family: 'Serif'; -fx-font-size: 12; -fx-font-weight: bold;");
        Button optionsButton = new Button("OPTIONS");
        optionsButton.setStyle("-fx-font-family: 'Serif'; -fx-font-size: 12; -fx-font-weight: bold;");
        Button exitButton = new Button("EXIT");
        exitButton.setStyle("-fx-font-family: 'Serif'; -fx-font-size: 12; -fx-font-weight: bold;");

        // Set button sizes
        playButton.setPrefSize(150, 50);
        optionsButton.setPrefSize(150, 50);
        exitButton.setPrefSize(150, 50);

        playButton.setOnAction(e -> {
            primaryStage.close(); 
            try {
                GameMan gm = new GameMan(1,0,0,new Stage());
		        gm.playRound();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Add action for the options button
        optionsButton.setOnAction(e -> {
            Options.display();
        });

        // Close the application
        exitButton.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10); // 10 is the spacing between elements
        layout.setAlignment(Pos.CENTER); // Center align the elements
        layout.getChildren().addAll(titleLabel, titleLabel2, spacer, playButton, optionsButton, exitButton);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
