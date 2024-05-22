package skeleton.elveszlelket.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class View {
	protected float x, y;
	protected ImageView texture;
	protected String texPath;
	
	/**
	 * A View osztály alapértelmezett konstruktora.
	 */
	/**
	 * A View osztály alapértelmezett konstruktora.
	 */
	public View() {
		System.out.println("View osztaly nem tartalmaz koordinatakat!");
	}
	
	/**
	 * Visszaadja a nézethez tartozó textúra elérési útját.
	 * @return Az elérési út.
	 */
	public void rotateTexture90() {
		texture.setRotate(90);
	}
	
	/**
	 * Visszaadja a nézethez tartozó textúra elérési útját.
	 * @return Az elérési út.
	 */
	public String getTexturePath() {
		return texPath;
	}
	
	/**
	 * Visszaadja a nézet textúráját.
	 * @return A nézet textúrája.
	 */
	/**
	 * Visszaadja a nézet textúráját.
	 * @return A nézet textúrája.
	 */
	public ImageView getTexture() {
		return texture;
	}

	/**
	 * A View osztály konstruktora.
	 * @param x x koordináta.
	 * @param y y koordináta.
	 */
	/**
	 * A View osztály konstruktora.
	 * @param x x koordináta.
	 * @param y y koordináta.
	 */
	public View(float x, float y) {
		this.x = x;
		this.y = y;
		this.texture = null;
	}
	
	/**
	 * A View osztály konstruktora.
	 * @param x x koordináta.
	 * @param y y koordináta.
	 * @param texturePath A nézet textúrájának elérési útvonala.
	 */
	/**
	 * A View osztály konstruktora.
	 * @param x x koordináta.
	 * @param y y koordináta.
	 * @param texturePath A nézet textúrájának elérési útvonala.
	 */
	public View(float x, float y, String texturePath) {
		this.x = x;
		this.y = y;
		this.texture = new ImageView(new Image(texturePath));
		this.texPath = texturePath;
	}
	
	/**
	 * Beállítja a nézet x és y koordinátáját.
	 * @param x új x koordináta.
	 * @param y új y koordináta.
	 */
	/**
	 * Beállítja a nézet x és y koordinátáját.
	 * @param x új x koordináta.
	 * @param y új y koordináta.
	 */
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter az x koordinátához.
	 * @return
	 */
	/**
	 * Getter az x koordinátához.
	 * @return
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Getter az y koordinátához.
	 * @return
	 */
	/**
	 * Getter az y koordinátához.
	 * @return
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Kirajzolja a nézetet a paraméterként kapott GameView-ba.
	 * @param jatekmegjelenito Az adott GameView.
	 */
	/**
	 * Kirajzolja a nézetet a paraméterként kapott GameView-ba.
	 * @param jatekmegjelenito Az adott GameView.
	 */
	public void Draw(GameView jatekmegjelenito) {
		System.out.println("View osztaly nem rajzolhat, view absztakt!");
	}
	
	// Egység négyzet területére normalizálja a texturát.
	public abstract void normalizeTexture(float tileX, float tileY);
}
