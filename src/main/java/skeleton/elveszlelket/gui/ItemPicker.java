package skeleton.elveszlelket.gui;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class ItemPicker extends ScrollPane {
	protected GameView parent;
	protected float HEIGHT, WIDTH;
	protected boolean shown;
	protected float transitionTime = 150;
	private HBox view;
	private int ScrollBarWidth = 10;
	private int padding = 8;
	private float normalizedWidth, normalizedHeight;
	private Insets paddingInsets;
	
	/**
	 * ItemPicker konstruktora.
	 * @param parent A GameView.
	 * @param WIDTH A felvehető tárgyak listájának szélessége.
	 * @param HEIGHT A felvehető tárgyak listájának magassága.
	 */
	public ItemPicker(GameView parent, float WIDTH, float HEIGHT) {
		this.HEIGHT = HEIGHT;
		this.WIDTH = WIDTH;
		this.parent = parent;
		shown = false;
		view = new HBox();
		view.setAlignment(Pos.CENTER);
		view.setStyle("-fx-background-color:grey; -fx-border-width: 0;");
		view.setMinSize(WIDTH, HEIGHT);
		view.setMaxSize(WIDTH, HEIGHT);
		
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		this.setMinSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
		this.setLayoutY(parent.HEIGHT);
		this.setStyle("-fx-border-width: 0;");
		this.setContent(view);
		
		this.normalizedHeight = HEIGHT-padding*2-this.ScrollBarWidth;
		this.normalizedWidth = WIDTH/5-padding*2-this.ScrollBarWidth;
		this.paddingInsets = new Insets(0, padding, padding, padding+this.ScrollBarWidth);
	}
	
	/**
	 * Nyitja, illetve csukja felvehető tárgyak listáját.
	 */
	public void translate() {
		if(shown) {
			Close();
		} else {
			Open();
		}
	}
	
	/**
	 * Frissíti a felvehető tárgyak listáját.
	 */
	public void refresh() {
		ItemPicker onmaga = this;
		Student jelenlegiJatekos = parent.getCurrentPlayer();
		Room jelenlegiRoom = jelenlegiJatekos.getRoom();
		view.getChildren().clear();
		for(Item i : jelenlegiRoom.getItems()) {
			if(i.getView().getX() == jelenlegiJatekos.getView().getX() && i.getView().getY() == jelenlegiJatekos.getView().getY()) {
				VBox kozepV = new VBox();
				HBox kozepH = new HBox();
				kozepV.setPadding(this.paddingInsets);
				
				Button b = new Button();
				b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
				ItemView temp = new ItemView(0,0, i.getView().getTexturePath());
				temp.normalizeTexture(this.normalizedWidth, normalizedHeight);
				b.setGraphic(temp.getTexture());
				b.setOnMouseClicked(e -> {
					jelenlegiJatekos.pickupItem(i);
					parent.updateItemPos(i);
					parent.Update(jelenlegiJatekos);
					onmaga.refresh();
					e.consume();
				});
				kozepV.setAlignment(Pos.CENTER);
				kozepH.setAlignment(Pos.CENTER);
				kozepV.getChildren().add(b);
				kozepH.getChildren().add(kozepV);
				kozepH.setMinWidth(WIDTH/5);
				view.getChildren().add(kozepH);
			}
		}
	}
	
	/**
	 * Kinyitja a felvehető tárgyak listáját.
	 */
	public void Open() {
		
		Student jelenlegiJatekos = parent.getCurrentPlayer();
		if(jelenlegiJatekos != null && !shown) {
			Room jelenlegiRoom = jelenlegiJatekos.getRoom();
			
			ItemPicker onmaga = this;
			view.getChildren().clear();
			for(Item i : jelenlegiRoom.getItems()) {
				if(i.getView().getX() == jelenlegiJatekos.getView().getX() && i.getView().getY() == jelenlegiJatekos.getView().getY()) {
					VBox kozepV = new VBox();
					HBox kozepH = new HBox();
					kozepV.setPadding(this.paddingInsets);
					
					Button b = new Button();
					b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
					ItemView temp = new ItemView(0,0, i.getView().getTexturePath());
					temp.normalizeTexture(this.normalizedWidth, this.normalizedHeight);
					b.setGraphic(temp.getTexture());
					b.setOnMouseClicked(e -> {
						jelenlegiJatekos.pickupItem(i);
						parent.updateItemPos(i);
						parent.Update(jelenlegiJatekos);
						onmaga.refresh();
						e.consume();
					});
					kozepV.setAlignment(Pos.CENTER);
					kozepH.setAlignment(Pos.CENTER);
					kozepV.getChildren().add(b);
					kozepH.getChildren().add(kozepV);
					kozepH.setMinWidth(WIDTH/5);
					view.getChildren().add(kozepH);
				}
			}
			
			shown = true;
			TranslateTransition translation = new TranslateTransition(Duration.millis(this.transitionTime), this);
			translation.setByY(-HEIGHT);
			translation.setAutoReverse(false);
			translation.setCycleCount(1);
			translation.play();
		}
	}
	
	/**
	 * Bezárja a felvehető tárgyak listáját.
	 */
	public void Close() {
		if(shown) {
			shown = false;
			TranslateTransition translation = new TranslateTransition(Duration.millis(this.transitionTime), this);
			translation.setByY(HEIGHT);
			translation.setAutoReverse(false);
			translation.setCycleCount(0);
			translation.play();
		}
	}
	
}
