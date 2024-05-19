package skeleton.elveszlelket.gui;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public abstract class View {
	protected float x, y;
	protected ImageView texture;
	protected String texPath;
	
	public View() {
		System.out.println("View osztaly nem tartalmaz koordinatakat!");
	}
	
	public String getTexturePath() {
		return texPath;
	}
	
	public ImageView getTexture() {
		return texture;
	}

	public View(float x, float y) {
		this.x = x;
		this.y = y;
		this.texture = null;
	}
	
	public View(float x, float y, String texturePath) {
		this.x = x;
		this.y = y;
		this.texture = new ImageView(new Image(texturePath));
		this.texPath = texturePath;
	}
	
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void Draw(GameView jatekmegjelenito) {
		System.out.println("View osztaly nem rajzolhat, view absztakt!");
	}
	
	// Egység négyzet területére normalizálja a texturát.
	public abstract void normalizeTexture(float tileX, float tileY);
}
