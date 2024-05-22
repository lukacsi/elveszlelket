package skeleton.elveszlelket.gui;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Transistor;

public class ItemMenu extends HBox {
	protected GameView parent;
	protected float HEIGHT, WIDTH;
	protected boolean shown;
	protected float transitionTime = 150;

	/**
	 * Az ItemMenu konstruktora.
	 * 
	 * @param parent A GameView
	 * @param WIDTH  Az ItemMenu szélessége.
	 * @param HEIGHT Az ItemMenu magassága.
	 */
	public ItemMenu(GameView parent, float WIDTH, float HEIGHT) {
		this.parent = parent;
		shown = false;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:grey;");
		this.setMinSize(WIDTH, HEIGHT);
		this.setMaxSize(WIDTH, HEIGHT);
		this.setLayoutY(parent.HEIGHT);
	}

	/**
	 * Megmondja, hogy a leltár meg van-e jelenítve.
	 * 
	 * @return Igaz, ha meg van jelenítve, egyébként hamis.
	 */
	public boolean isShown() {
		return shown;
	}

	/**
	 * Frissíti a leltárat.
	 */
	public void refresh() {
		Student current = parent.getCurrentPlayer();
		if (current != null) {
			this.getChildren().clear();
			ItemMenu onmaga = this;
			for (Item i : current.getItems()) {
				VBox kozepV = new VBox();
				HBox kozepH = new HBox();
				int padding = 10;
				kozepV.setPadding(new Insets(padding));

				Button b = new Button();
				b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
				i.getView().normalizeTexture(WIDTH / 5 - padding * 2, HEIGHT - padding * 2);
				b.setGraphic(i.getView().getTexture());
				b.setOnMouseClicked(e -> {
					if (e.getButton().equals(MouseButton.PRIMARY)) {
						i.use(current);
						parent.Update(current);
						onmaga.refresh();
						e.consume();
					} else if (e.getButton().equals(MouseButton.SECONDARY)) {
						current.dropItem(i);
						parent.Update(current);
						onmaga.refresh();
						e.consume();
					}
				});

				Label usesLabel = new Label("Uses: " + /* ((i.getUses() == 0) ? "inf" : */i.getUses());// );
				usesLabel.setStyle("-fx-text-fill: white;");
				usesLabel.setPadding(new Insets(0, 5, 0, 5));

				kozepV.setAlignment(Pos.CENTER);
				kozepH.setAlignment(Pos.CENTER);
				kozepV.getChildren().add(b);
				kozepH.getChildren().addAll(kozepV, usesLabel);
				kozepH.setMinWidth(WIDTH / 5);
				this.getChildren().add(kozepH);
			}
		}
	}

	/**
	 * Nyitja, illetve zárja a leltárat.
	 */
	public void translate() {
		if (shown) {
			Close();
		} else {
			Open();
		}
	}

	/**
	 * Kinyitja a leltárat.
	 */
	public void Open() {
		Student current = parent.getCurrentPlayer();
		if (current != null && !shown) {
			this.getChildren().clear();
			ItemMenu onmaga = this;
			for (Item i : current.getItems()) {
				VBox kozepV = new VBox();
				HBox kozepH = new HBox();
				int padding = 10;
				kozepV.setPadding(new Insets(padding));

				Button b = new Button();
				b.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
				i.getView().normalizeTexture(WIDTH / 5 - padding * 2, HEIGHT - padding * 2);
				b.setGraphic(i.getView().getTexture());
				b.setOnMouseClicked(e -> {
					if (e.getButton().equals(MouseButton.PRIMARY)) {
						if (i.getName().equals("Transistor")) {
							Transistor t = (Transistor) i;
							if (t.hasPair())
								i.use(current);
							else
								t.connectRandom(current);
						} else {
							i.use(current);
						}
						parent.Update(current);
						onmaga.refresh();
						e.consume();
					} else if (e.getButton().equals(MouseButton.SECONDARY)) {
						current.dropItem(i);
						parent.Update(current);
						onmaga.refresh();
						e.consume();
					}
				});

				Label usesLabel = new Label("Uses: " + /* ((i.getUses() == 0) ? "inf" : */i.getUses());// );
				usesLabel.setStyle("-fx-text-fill: white;");
				usesLabel.setPadding(new Insets(0, 5, 0, 5));

				kozepV.setAlignment(Pos.CENTER);
				kozepH.setAlignment(Pos.CENTER);
				kozepV.getChildren().add(b);
				kozepH.getChildren().addAll(kozepV, usesLabel);
				kozepH.setMinWidth(WIDTH / 5);
				this.getChildren().add(kozepH);
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
	 * Bezárja a leltárat.
	 */
	public void Close() {
		Student current = parent.getCurrentPlayer();
		if (current != null && shown) {
			shown = false;
			TranslateTransition translation = new TranslateTransition(Duration.millis(this.transitionTime), this);
			translation.setByY(HEIGHT);
			translation.setAutoReverse(false);
			translation.setCycleCount(1);
			translation.play();
		}
	}

}
