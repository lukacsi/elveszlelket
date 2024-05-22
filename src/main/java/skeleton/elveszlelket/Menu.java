package skeleton.elveszlelket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skeleton.elveszlelket.controller.GameMan;
import skeleton.elveszlelket.controller.Settings;
import skeleton.elveszlelket.gui.Screen;

/**
 * A Menu osztály kezeli a főmenü megjelenítését és annak funkcióit.
 * Itt találhatók a játék indítására, beállítások megnyitására és kilépésre szolgáló gombok.
 */
public class Menu extends VBox {
    public static Settings settings;
    private Screen parent;

    /**
     * Létrehoz egy Menu objektumot a megadott szülő Screen objektummal.
     * Inicializálja a menü elemeit és beállítja a gombok eseménykezelőit.
     *
     * @param parent a menü szülő Screen objektuma
     */
    public Menu(Screen parent) {
        this.parent = parent;
        settings = new Settings(2, 1, 1, 15, 0.1f, 0.1f, 0.1f, 0.5f, 0.1f, 0.1f);

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


        playButton.setPrefSize(150, 50);
        optionsButton.setPrefSize(150, 50);
        exitButton.setPrefSize(150, 50);

        playButton.setOnAction(e -> {
            parent.close();
            try {
                parent.startGame(settings);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        optionsButton.setOnAction(e -> {
            Options.display(settings);
        });

        exitButton.setOnAction(e -> {
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
        });

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(titleLabel, titleLabel2, spacer, playButton, optionsButton, exitButton);
    }
}
