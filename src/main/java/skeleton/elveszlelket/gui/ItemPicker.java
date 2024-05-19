package skeleton.elveszlelket.gui;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class ItemPicker extends ItemMenu {
	public ItemPicker(GameView parent, float WIDTH, float HEIGHT) {
		super(parent, WIDTH, HEIGHT);
	}
	
	public void refresh() {
		ItemPicker onmaga = this;
		Student jelenlegiJatekos = parent.getCurrentPlayer();
		Room jelenlegiRoom = jelenlegiJatekos.getRoom();
		this.getChildren().clear();
		for(Item i : jelenlegiRoom.getItems()) {
			if(i.getView().getX() == jelenlegiJatekos.getView().getX() && i.getView().getY() == jelenlegiJatekos.getView().getY()) {
				VBox kozepV = new VBox();
				HBox kozepH = new HBox();
				int padding = 10;
				kozepV.setPadding(new Insets(padding));
				
				Button b = new Button();
				b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
				ItemView temp = new ItemView(0,0, i.getView().getTexturePath());
				temp.normalizeTexture(WIDTH/5-padding*2, HEIGHT-padding*2);
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
				this.getChildren().add(kozepH);
			}
		}
	}
	
	public void Open() {
		
		Student jelenlegiJatekos = parent.getCurrentPlayer();
		if(jelenlegiJatekos != null && !shown) {
			Room jelenlegiRoom = jelenlegiJatekos.getRoom();
			
			ItemPicker onmaga = this;
			this.getChildren().clear();
			for(Item i : jelenlegiRoom.getItems()) {
				if(i.getView().getX() == jelenlegiJatekos.getView().getX() && i.getView().getY() == jelenlegiJatekos.getView().getY()) {
					VBox kozepV = new VBox();
					HBox kozepH = new HBox();
					int padding = 10;
					kozepV.setPadding(new Insets(padding));
					
					Button b = new Button();
					b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
					ItemView temp = new ItemView(0,0, i.getView().getTexturePath());
					temp.normalizeTexture(WIDTH/5-padding*2, HEIGHT-padding*2);
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
					this.getChildren().add(kozepH);
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
