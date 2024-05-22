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

/**
 * A Screen osztály kezeli a különböző nézeteket és képernyőket az alkalmazásban.
 * Itt történik a fő játék megjelenítése, a menünek és a különböző képernyők közötti váltások kezelése.
 */
public class Screen extends StackPane {
    private GameView palyamegjelenito;
    private Stage parent;
    private Scene parentScene;
    private Menu menu;
    private GameMan gameManager;
    private float WIDTH, HEIGHT;
    private long lastKeyPressTime = 0;
    private static final long KEY_PRESS_DELAY = 200;

    /**
     * Létrehoz egy Screen objektumot a megadott szülő Stageel, szélességgel és magassággal.
     * Inicializálja a fő menüt és beállítja a képernyő elrendezését.
     *
     * @param parent a képernyő szülő színpada
     * @param WIDTH  a képernyő szélessége
     * @param HEIGHT a képernyő magassága
     */
    public Screen(Stage parent, float WIDTH, float HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        menu = new Menu(this);
        this.parent = parent;
        this.getChildren().add(menu);
        StackPane.setAlignment(menu, Pos.CENTER); 
    }

    /**
     * Beállítja a szülő jelenetet a megadott jelenetre.
     *
     * @param mire a szülő jelenet
     */
    public void setParentScene(Scene mire) {
        this.parentScene = mire;
    }

    /**
     * Megváltoztatja a szobát a megadott jelenlegi játékos alapján.
     *
     * @param jelenlegi a jelenlegi játékos
     */
    public void changeRoom(Student jelenlegi) {
        showCurrentRound(jelenlegi);
    }

    /**
     * Befejezi a játékot és visszatér a főmenübe.
     */
    public void endGame() {
        this.getChildren().clear();
        this.getChildren().add(menu);
    }

    /**
     * Ellenőrzi, hogy mindenki meghalt-e a játékban.
     *
     * @return true, ha mindenki meghalt, különben false
     */
    public boolean isEveryoneDead() {
        return gameManager.isEveryoneDead();
    }

    /**
     * Elindítja a játékot a megadott beállításokkal.
     *
     * @param settings a játék beállításai
     */
    public void startGame(Settings settings) {
        gameManager = new GameMan(settings, new Stage());
        gameManager.playRound();

        this.setOnKeyPressed(e -> handleKeyPress(e));
        showCurrentRound(gameManager.getCurrentPlayer());
    }

    /**
     * Kezeli a billentyűleütési eseményeket és végrehajtja a megfelelő műveleteket.
     *
     * @param e a billentyűleütési esemény
     */
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

    /**
     * Megjeleníti az aktuális kört a megadott játékos alapján.
     *
     * @param jelenlegiJatekos a jelenlegi játékos
     */
    public void showCurrentRound(Student jelenlegiJatekos) {
        this.getChildren().clear();
        Room jelenlegiSzoba = jelenlegiJatekos.getRoom();

        palyamegjelenito = new GameView(this, jelenlegiSzoba.getView().getX(), jelenlegiSzoba.getView().getY());
        this.WIDTH = this.palyamegjelenito.WIDTH;
        this.HEIGHT = this.palyamegjelenito.HEIGHT;
        this.getChildren().add(palyamegjelenito);
        StackPane.setAlignment(palyamegjelenito, Pos.CENTER);

        this.parentScene.getWindow().setWidth(WIDTH + 14.66666);
        this.parentScene.getWindow().setHeight(HEIGHT + 37.333333);

        this.palyamegjelenito.Update(jelenlegiJatekos);
    }

    /**
     * Bezárja a szülő Staget.
     */
    public void close() {
        parent.close();
    }

    /**
     * Kezeli a billentyűleütési eseményeket.
     *
     * @param e a billentyűleütési esemény
     */
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e);
    }

    /**
     * Frissíti a megjelenítést a megadott játékos alapján.
     *
     * @param jelenlegiJatekos a jelenlegi játékos
     */
    public void Update(Student jelenlegiJatekos) {
        palyamegjelenito.Update(jelenlegiJatekos);
    }
    
    /**
     * Elindítja a következő kört.
     */
    public void nextRound() {
        gameManager.playRound();
        showCurrentRound(gameManager.getCurrentPlayer());
    }

    /**
     * Eltávolítja az aktuális játékost és elindítja a következő játékost.
     */
    public void nextPlayer() {
        gameManager.removeCurrent();
        showCurrentRound(gameManager.getCurrentPlayer());
    }
}
