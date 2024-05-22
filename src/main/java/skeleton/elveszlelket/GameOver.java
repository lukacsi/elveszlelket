package skeleton.elveszlelket;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOver {
    private Stage stage;

    public GameOver(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        StackPane root = new StackPane();
        Label gameOverLabel = new Label("Game Over! All students are dead.");
        gameOverLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: red;");
        root.getChildren().add(gameOverLabel);

        Scene scene = new Scene(root, 800, 600); // Adjust size as needed
        stage.setScene(scene);
    }
}
