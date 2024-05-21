package skeleton.elveszlelket.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import skeleton.elveszlelket.Menu;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.controller.GameMan;
import skeleton.elveszlelket.controller.Settings;

public class Screen extends Pane {
	private GameView palyamegjelenito;
	
	public Screen(float WIDTH, float HEIGHT) {
		palyamegjelenito = new GameView(WIDTH, HEIGHT);
		this.getChildren().add(palyamegjelenito);
		
		this.setOnKeyPressed(e -> {
			switch(e.getCode()) {
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
				}
			e.consume();
		});
	}
	
	public void setParentScene(Scene mire) {
		this.parentScene = mire;
	}
	
	public void startGame(Settings settings) {
        GameMan gm = new GameMan(settings, new Stage());
        gm.playRound();
        
        showCurrentRound(gm.getCurrentPlayer());
	}
	
	public void showCurrentRound(Student jelenlegiJatekos) {
		this.getChildren().clear();
		Room jelenlegiSzoba = jelenlegiJatekos.getRoom();
		
		palyamegjelenito = new GameView(jelenlegiSzoba.getView().getX(), jelenlegiSzoba.getView().getY());
		this.WIDTH = this.palyamegjelenito.WIDTH;
		this.HEIGHT = this.palyamegjelenito.HEIGHT;
		this.getChildren().add(palyamegjelenito);

		this.parentScene.getWindow().setWidth(WIDTH + 14.66666);
		this.parentScene.getWindow().setHeight(HEIGHT + 37.333333);
		//System.out.println(this.parentScene.getWindow().getWidth() + " " + this.parentScene.getWindow().getHeight());
		
        this.palyamegjelenito.Update(jelenlegiJatekos);
	}
	
	public void close() {
		parent.close();
	}
	
	/**
	 * Eseménykezelő gombnyomásra.
	 * @param e Az esemény.
	 */
	public void keyPressed(KeyEvent e) {
		switch(e.getCode()) {
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
			}
		e.consume();
	}
	
	/**
	 * Frissíti a jelenleg aktív játékos kinézetét.
	 * @param jelenlegiJatekos A jelenleg aktív játékos.
	 */
	public void Update(Student jelenlegiJatekos) {
		palyamegjelenito.Update(jelenlegiJatekos);
	}
	
	// Csak a tesztelesnel kell.
	public void updateBecauseItemsHaveBeenManuallyAddedToStudent() {
		palyamegjelenito.updateItemsPos();
	}
}
