package skeleton.elveszlelket.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import skeleton.elveszlelket.Menu;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.controller.GameMan;
import skeleton.elveszlelket.controller.Settings;

public class Screen extends StackPane {
	private GameView palyamegjelenito;
	private Stage parent;
	private Scene parentScene;
	private Menu menu;
	private GameMan gameManager;
	private float WIDTH, HEIGHT;
	private long lastKeyPressTime = 0;
	private static final long KEY_PRESS_DELAY = 200; // delay in milliseconds

	public Screen(Stage parent, float WIDTH, float HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		menu = new Menu(this);
		this.parent = parent;
		this.getChildren().add(menu);
		StackPane.setAlignment(menu, Pos.CENTER); // Center the menu
	}

	public void setParentScene(Scene mire) {
		this.parentScene = mire;
	}

	public void changeRoom(Student jelenlegi) {
		showCurrentRound(jelenlegi);
	}

	public void endGame() {
		this.getChildren().clear();
		this.getChildren().add(menu);
	}

	public boolean isEveryoneDead() {
		return gameManager.isEveryoneDead();
	}

	public void startGame(Settings settings) {
		gameManager = new GameMan(settings, new Stage());
		gameManager.playRound();

		this.setOnKeyPressed(e -> handleKeyPress(e));
		showCurrentRound(gameManager.getCurrentPlayer());
	}

	private void handleKeyPress(KeyEvent e) {
		long currentTime = System.currentTimeMillis();

		if (currentTime - lastKeyPressTime > KEY_PRESS_DELAY && !gameManager.getCurrentPlayer().isDead()
				&& gameManager.getCurrentPlayer().getStunDuration() <= 0) {
			lastKeyPressTime = currentTime;
			switch (e.getCode()) {
				case I:
					this.palyamegjelenito.translateItemMenu();
					this.palyamegjelenito.closePickUpMenu();
					break;
				case S:
					this.palyamegjelenito.playerDown();
					this.palyamegjelenito.closePickUpMenu();
					break;
				case W:
					this.palyamegjelenito.playerUp();
					this.palyamegjelenito.closePickUpMenu();
					break;
				case D:
					this.palyamegjelenito.playerRight();
					this.palyamegjelenito.closePickUpMenu();
					break;
				case A:
					this.palyamegjelenito.playerLeft();
					this.palyamegjelenito.closePickUpMenu();
					break;
				case P:
					this.palyamegjelenito.translatePickUpMenu();
					this.palyamegjelenito.closeItemMenu();
					break;
				case X:
					this.palyamegjelenito.closeItemMenu();
					nextRound();
					break;
				default:
					break;
			}
			e.consume();
		}
	}

	public void showCurrentRound(Student jelenlegiJatekos) {
		this.getChildren().clear();
		Room jelenlegiSzoba = jelenlegiJatekos.getRoom();

		palyamegjelenito = new GameView(this, jelenlegiSzoba.getView().getX(), jelenlegiSzoba.getView().getY());
		this.WIDTH = this.palyamegjelenito.WIDTH;
		this.HEIGHT = this.palyamegjelenito.HEIGHT;
		this.getChildren().add(palyamegjelenito);
		StackPane.setAlignment(palyamegjelenito, Pos.CENTER); // Center the game view

		this.parentScene.getWindow().setWidth(WIDTH + 14.66666);
		this.parentScene.getWindow().setHeight(HEIGHT + 37.333333);

		this.palyamegjelenito.Update(jelenlegiJatekos);
	}

	public void close() {
		parent.close();
	}

	public void keyPressed(KeyEvent e) {
		handleKeyPress(e);
	}

	public void Update(Student jelenlegiJatekos) {
		palyamegjelenito.Update(jelenlegiJatekos);
	}

	// Csak a tesztelesnel kell.
	public void updateBecauseItemsHaveBeenManuallyAddedToStudent() {
		palyamegjelenito.updateItemsPos();
	}

	public void nextRound() {
		gameManager.playRound();
		showCurrentRound(gameManager.getCurrentPlayer());
	}

	public void nextPlayer() {
		gameManager.removeCurrent();
		showCurrentRound(gameManager.getCurrentPlayer());
	}
}
