package skeleton.elveszlelket.gui;

import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import skeleton.elveszlelket.CleaningLady;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.item.Item;

public class GameView extends Pane {
	public float WIDTH;
	public float HEIGHT;
	private ItemMenu itemMenu;
	private Student jelenlegiJatekos;
	private ItemPicker pickupMenu;
	private Screen Parent;

	/**
	 * Nyitja, illetve zárja a leltárat.
	 */
	public void translateItemMenu() {
		itemMenu.translate();
	}

	/**
	 * Nyitja, illetve zárja felvehető tárgyak listáját.
	 */
	public void translatePickUpMenu() {
		this.pickupMenu.translate();
	}

	/**
	 * Bezárja a felvehető tárgyak menüjét.
	 */
	public void closePickUpMenu() {
		this.pickupMenu.Close();
	}

	/**
	 * Bezárja a játékos leltárát.
	 */
	public void closeItemMenu() {
		this.itemMenu.Close();
	}

	/**
	 * A játéknézet konstruktora.
	 * 
	 * @param w A nézet szélessége.
	 * @param h A nézet magassága.
	 */
	public GameView(Screen Parent, float w, float h) {
		this.Parent = Parent;
		WIDTH = w;
		HEIGHT = h;
		itemMenu = new ItemMenu(this, WIDTH, HEIGHT / 5);
		pickupMenu = new ItemPicker(this, WIDTH, HEIGHT / 5);
		itemMenu = new ItemMenu(this, WIDTH, HEIGHT / 5);
		pickupMenu = new ItemPicker(this, WIDTH, HEIGHT / 5);
		this.setMinSize(w, h);
		this.setPrefSize(w, h);
		/*
		 * this.setOnKeyPressed(e -> {
		 * switch(e.getCode()) {
		 * case I:
		 * translateItemMenu();
		 * closePickUpMenu();
		 * break;
		 * case S:
		 * playerDown();
		 * closePickUpMenu();
		 * break;
		 * case W:
		 * playerUp();
		 * closePickUpMenu();
		 * break;
		 * case D:
		 * playerRight();
		 * closePickUpMenu();
		 * break;
		 * case A:
		 * playerLeft();
		 * closePickUpMenu();
		 * break;
		 * case P:
		 * translatePickUpMenu();
		 * closeItemMenu();
		 * break;
		 * }
		 * e.consume();
		 * });
		 *
		 * this.setOnKeyPressed(e -> {
		 * switch(e.getCode()) {
		 * case I:
		 * translateItemMenu();
		 * closePickUpMenu();
		 * break;
		 * case S:
		 * playerDown();
		 * closePickUpMenu();
		 * break;
		 * case W:
		 * playerUp();
		 * closePickUpMenu();
		 * break;
		 * case D:
		 * playerRight();
		 * closePickUpMenu();
		 * break;
		 * case A:
		 * playerLeft();
		 * closePickUpMenu();
		 * break;
		 * case P:
		 * translatePickUpMenu();
		 * closeItemMenu();
		 * break;
		 * }
		 * e.consume();
		 * });
		 */
	}

	/**
	 * Frissíti az összes tárgy pozícióját.
	 */
	public void updateItemsPos() {
		for (Item i : this.jelenlegiJatekos.getItems()) {
			i.getView().setPos(this.jelenlegiJatekos.getView().getX(), this.jelenlegiJatekos.getView().getY());
		}
	}

	/**
	 * Frissíti az adott tárgy pozícióját.
	 * 
	 * @param i Az adott tárgy.
	 */
	public void updateItemPos(Item i) {
		if (i != null) {
			i.getView().setPos(this.jelenlegiJatekos.getView().getX(), this.jelenlegiJatekos.getView().getY());
		}
	}

	/**
	 * Balra mozgatja a jelenleg aktív játékost.
	 */
	/**
	 * Balra mozgatja a jelenleg aktív játékost.
	 */
	public void playerLeft() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = 0 + jelenlegiRoom.getView().getTileWidth();
		if (jelenlegi.getView().getX() > hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX() - jelenlegiRoom.getView().egysegNegyzetWidth,
					jelenlegi.getView().getY());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}

	/**
	 * Jobbra mozgatja a jelenleg aktív játékost.
	 */
	/**
	 * Jobbra mozgatja a jelenleg aktív játékost.
	 */
	public void playerRight() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = jelenlegiRoom.getView().getX() - jelenlegiRoom.getView().getTileWidth() * 2;
		if (jelenlegi.getView().getX() < hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX() + jelenlegiRoom.getView().egysegNegyzetWidth,
					jelenlegi.getView().getY());
			Update(jelenlegiJatekos);
			updateItemsPos();
		} else {
			for (Door d : jelenlegiRoom.getDoors()) {
				if (d.getView().getY() == jelenlegi.getView().getY() && d.getView()
						.getX() == jelenlegiRoom.getView().getX() - jelenlegiRoom.getView().getTileWidth()) {
					d.accept(jelenlegi);
					this.Parent.changeRoom(jelenlegi);
					if (d.getOwnerRoom().equals(jelenlegiRoom)) {
						jelenlegi.getView().setPos(d.getView().getX2(),
								d.getView().getY2() - d.getDest(jelenlegiRoom).getView().getTileWidth());
					} else {
						jelenlegi.getView().setPos(d.getView().getX(),
								d.getView().getY() - d.getDest(jelenlegiRoom).getView().getTileWidth());
					}
					updateItemsPos();
				}
			}
		}
	}

	/**
	 * Felfele mozgatja a jelenleg aktív játékost.
	 */
	public void playerUp() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = 0 + jelenlegiRoom.getView().getTileHeight();

		if (jelenlegi.getView().getY() > hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX(),
					jelenlegi.getView().getY() - jelenlegiRoom.getView().getTileHeight());
			Update(jelenlegiJatekos);
			updateItemsPos();
		} else {
			for (Door d : jelenlegiRoom.getDoors()) {
				if (d.getView().getX() == jelenlegi.getView().getX() && d.getView().getY() == 0) {
					d.accept(jelenlegi);
					this.Parent.changeRoom(jelenlegi);
					if (d.getOwnerRoom().equals(jelenlegiRoom)) {
						jelenlegi.getView().setPos(d.getView().getX2(),
								d.getView().getY2() - d.getDest(jelenlegiRoom).getView().getTileWidth());
					} else {
						jelenlegi.getView().setPos(d.getView().getX(),
								d.getView().getY() - d.getDest(jelenlegiRoom).getView().getTileWidth());
					}
					updateItemsPos();
				}
			}
		}
	}

	/**
	 * Lefele mozgatja a jelenleg aktív játékost.
	 */
	public void playerDown() {
		Student jelenlegi = this.jelenlegiJatekos;
		Room jelenlegiRoom = this.jelenlegiJatekos.getRoom();
		float hatar = jelenlegiRoom.getView().getY() - jelenlegiRoom.getView().getTileHeight() * 2;

		if (jelenlegi.getView().getY() < hatar) {
			jelenlegi.getView().setPos(jelenlegi.getView().getX(),
					jelenlegi.getView().getY() + jelenlegiRoom.getView().getTileHeight());
			Update(jelenlegiJatekos);
			updateItemsPos();
		}
	}

	/**
	 * Frissíti az adott játékos megjelenítését.
	 * 
	 * @param jelenlegiJatekos Az adott játékos.
	 */
	public void Update(Student jelenlegiJatekos) {

		if (jelenlegiJatekos != null) {

			if (jelenlegiJatekos != null) {
				this.jelenlegiJatekos = jelenlegiJatekos;
				// Letorlunk mindent korabbi texturat.
				this.getChildren().clear();

				// Szoba rajz
				RoomView rv = jelenlegiJatekos.getRoom().getView();
				rv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
				rv.Draw(this);

				for (Item i : jelenlegiJatekos.getRoom().getItems()) {
					ItemView iv = i.getView();
					iv.setPos(rv.xToTileX(iv.getX()), rv.yToTileY(iv.getY()));
					iv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
					iv.Draw(this);
				}

				// Student Rajz
				for (Student st : jelenlegiJatekos.getRoom().getStudents()) {
					if (st == jelenlegiJatekos) {
						this.getChildren().add(itemMenu);
						this.getChildren().add(pickupMenu);
					}
					View v = st.getView();
					v.setPos(rv.xToTileX(v.getX()), rv.yToTileY(v.getY()));
					v.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
					v.Draw(this);
				}

				// Ajto Rajz
				for (Door d : jelenlegiJatekos.getRoom().getDoors()) {
					DoorView dv = d.getView();
					if (d.getOwnerRoom().equals(jelenlegiJatekos.getRoom())) {
						dv.setPos(rv.xToTileX(dv.getX()), rv.yToTileY(dv.getY()));
					} else {
						dv.setPos(rv.xToTileX(dv.getX2()), rv.yToTileY(dv.getY2()));
					}

					dv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
					dv.Draw(this);
				}
			} else {
				System.out.println("GameView nem kapott jelenlegi jatekost.");
			}
		}
	}

	/**
	 * Visszaadja a jelenleg aktív játékost.
	 * 
	 * @return A jelenleg aktív játékos.
	 */
	public Student getCurrentPlayer() {
		return this.jelenlegiJatekos;
	}
}
