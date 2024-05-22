package skeleton.elveszlelket;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import skeleton.elveszlelket.controller.Settings;

/**
 * Az Options osztály megjeleníti és kezeli a játék beállításait tartalmazó ablakot.
 * Lehetővé teszi a felhasználó számára a különböző beállítások módosítását.
 */
public class Options {

    /**
     * Megjeleníti a beállítások ablakot és kezeli a felhasználói bevitelt.
     *
     * @param settings a játék jelenlegi beállításai
     */
    public static void display(Settings settings) {
        Stage window = new Stage();
        window.setTitle("Options");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(20);

        // Címek
        Label settingsTitle = new Label("SETTINGS");
        settingsTitle.setFont(Font.font("Serif", FontWeight.BOLD, 16));
        GridPane.setConstraints(settingsTitle, 0, 0, 2, 1); // Két oszlopot fed le

        Label randomWeightsTitle = new Label("RANDOM WEIGHTS");
        randomWeightsTitle.setFont(Font.font("Serif", FontWeight.BOLD, 16));
        GridPane.setConstraints(randomWeightsTitle, 0, 5, 4, 1); // Négy oszlopot fed le

        // Beállítások címkék és szövegmezők
        Label playersLabel = new Label("players");
        GridPane.setConstraints(playersLabel, 0, 1);
        TextField playersField = new TextField(String.valueOf(settings.studentNum));
        playersField.setPrefWidth(80);
        GridPane.setConstraints(playersField, 1, 1);

        Label teachersLabel = new Label("teachers");
        GridPane.setConstraints(teachersLabel, 0, 2);
        TextField teachersField = new TextField(String.valueOf(settings.teacherNum));
        teachersField.setPrefWidth(80);
        GridPane.setConstraints(teachersField, 1, 2);

        Label cleanersLabel = new Label("cleaners");
        GridPane.setConstraints(cleanersLabel, 0, 3);
        TextField cleanersField = new TextField(String.valueOf(settings.cleanerNum));
        cleanersField.setPrefWidth(80);
        GridPane.setConstraints(cleanersField, 1, 3);

        Label mapSizeLabel = new Label("map size");
        GridPane.setConstraints(mapSizeLabel, 0, 4);
        TextField mapSizeField = new TextField(String.valueOf(settings.size));
        mapSizeField.setPrefWidth(80);
        GridPane.setConstraints(mapSizeField, 1, 4);

        // Véletlen-súlyozotr címkék és szövegmezők
        Label curseLabel = new Label("curse");
        GridPane.setConstraints(curseLabel, 0, 6);
        TextField curseField = new TextField(String.valueOf(settings.curse));
        curseField.setPrefWidth(80);
        GridPane.setConstraints(curseField, 1, 6);

        Label gasLabel = new Label("gas");
        GridPane.setConstraints(gasLabel, 2, 6);
        TextField gasField = new TextField(String.valueOf(settings.gas));
        gasField.setPrefWidth(80);
        GridPane.setConstraints(gasField, 3, 6);

        Label itemLabel = new Label("item");
        GridPane.setConstraints(itemLabel, 0, 7);
        TextField itemField = new TextField(String.valueOf(settings.item));
        itemField.setPrefWidth(80);
        GridPane.setConstraints(itemField, 1, 7);

        Label falseItemsLabel = new Label("false items");
        GridPane.setConstraints(falseItemsLabel, 2, 7);
        TextField falseItemsField = new TextField(String.valueOf(settings.fals));
        falseItemsField.setPrefWidth(80);
        GridPane.setConstraints(falseItemsField, 3, 7);

        Label doorLabel = new Label("door");
        GridPane.setConstraints(doorLabel, 0, 8);
        TextField doorField = new TextField(String.valueOf(settings.door));
        doorField.setPrefWidth(80);
        GridPane.setConstraints(doorField, 1, 8);

        Label oneWayLabel = new Label("one way");
        GridPane.setConstraints(oneWayLabel, 2, 8);
        TextField oneWayField = new TextField(String.valueOf(settings.oneway));
        oneWayField.setPrefWidth(80);
        GridPane.setConstraints(oneWayField, 3, 8);

        // Vissza gomb
        Button backButton = new Button("BACK");
        backButton.setOnAction(e -> {
            // mezőkből származó értékek beolvasása és beállítások frissítése
            settings.studentNum = Integer.parseInt(playersField.getText());
            settings.teacherNum = Integer.parseInt(teachersField.getText());
            settings.cleanerNum = Integer.parseInt(cleanersField.getText());
            settings.size = Integer.parseInt(mapSizeField.getText());
            settings.curse = Float.parseFloat(curseField.getText());
            settings.gas = Float.parseFloat(gasField.getText());
            settings.item = Float.parseFloat(itemField.getText());
            settings.fals = Float.parseFloat(falseItemsField.getText());
            settings.door = Float.parseFloat(doorField.getText());
            settings.oneway = Float.parseFloat(oneWayField.getText());

            // bezárás
            window.close();
        });
        GridPane.setConstraints(backButton, 0, 10);

        grid.getChildren().addAll(
            settingsTitle, randomWeightsTitle,
            playersLabel, playersField, 
            teachersLabel, teachersField, 
            cleanersLabel, cleanersField, 
            mapSizeLabel, mapSizeField, 
            curseLabel, curseField, 
            gasLabel, gasField,
            itemLabel, itemField, 
            falseItemsLabel, falseItemsField, 
            doorLabel, doorField, 
            oneWayLabel, oneWayField, 
            backButton
        );

        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene);
        window.show();
    }
}
