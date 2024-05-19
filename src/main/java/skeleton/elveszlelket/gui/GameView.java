package skeleton.elveszlelket.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class GameView extends Pane {
	public float WIDTH;
	public float HEIGHT;
	private ItemMenu itemMenu;
	private Student jelenlegiJatekos;
	private ItemPicker pickupMenu;
	
	public GameView(float w, float h) {
		WIDTH = w;
		HEIGHT = h;
		itemMenu = new ItemMenu(this, WIDTH, HEIGHT/5);
		itemMenu.getChildren().add(new Button("b"));
		pickupMenu = new ItemPicker(this, WIDTH, HEIGHT/5);
		this.setMinSize(w, h);
		this.setPrefSize(w, h);
		this.requestFocus();
		this.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case I:
					itemMenu.translate();
					pickupMenu.Close();
					break;
				case S:
					playerDown();
					pickupMenu.Close();
					break;
				case W:
					playerUp();
					pickupMenu.Close();
					break;
				case D:
					playerRight();
					pickupMenu.Close();
					break;
				case A:
					playerLeft();
					pickupMenu.Close();
					break;
				case P:
					pickupMenu.translate();
					itemMenu.Close();
					break;
				}
			e.consume();
		});
		
	}
	
	public void updateItemsPos() {
		for(Item i : this.jelenlegiJatekos.getItems()) {
			i.getView().setPos(this.jelenlegiJatekos.getView().getX(), this.jelenlegiJatekos.getView().getY());
		}
	}
	
	public void updateItemPos(Item i) {
		if(i != null) {
			i.getView().setPos(this.jelenlegiJatekos.getView().getX(), this.jelenlegiJatekos.getView().getY());
		}
	}

	public void playerLeft() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = 0 + jelenlegiRoom.getView().getTileWidth();
		
		if(jelenlegi.getView().getX() > hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX()-jelenlegiRoom.getView().egysegNegyzetWidth, jelenlegi.getView().getY());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}

	public void playerRight() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = jelenlegiRoom.getView().getX() - jelenlegiRoom.getView().getTileWidth()*2;
		
		if(jelenlegi.getView().getX() <  hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX()+jelenlegiRoom.getView().egysegNegyzetWidth, jelenlegi.getView().getY());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}

	public void playerUp() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = 0 + jelenlegiRoom.getView().getTileHeight();
		
		if(jelenlegi.getView().getY() > hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX(), jelenlegi.getView().getY()-jelenlegiRoom.getView().getTileHeight());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}
	
	public void playerDown() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = jelenlegiRoom.getView().getY() - jelenlegiRoom.getView().getTileHeight()*2;
		
		if(jelenlegi.getView().getY() < hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX(), jelenlegi.getView().getY()+jelenlegiRoom.getView().getTileHeight());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}
	
	public void Update(Student jelenlegiJatekos) {
		
		if(jelenlegiJatekos != null) {
			this.jelenlegiJatekos = jelenlegiJatekos;
			// Letorlunk mindent korabbi texturat.
			this.getChildren().clear();
			
			// Szoba rajz
			RoomView rv = jelenlegiJatekos.getRoom().getView();
			rv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
			rv.Draw(this);
			
			for(Item i : jelenlegiJatekos.getRoom().getItems()) {
				ItemView iv = i.getView();
				iv.setPos(rv.xToTileX(iv.getX()), rv.yToTileY(iv.getY()));
				iv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
				iv.Draw(this);
			}
			
			// Student Rajz
			View v = jelenlegiJatekos.getView();
			v.setPos(rv.xToTileX(v.getX()), rv.yToTileY(v.getY()));
			v.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
			v.Draw(this);
			this.getChildren().add(itemMenu);
			this.getChildren().add(pickupMenu);
		} else {
			System.out.println("GameView nem kapott jelenlegi jatekost.");
		}
	}
	
	public Student getCurrentPlayer() {
		return this.jelenlegiJatekos;
	}
}
