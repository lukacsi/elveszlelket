package skeleton.elveszlelket.gui;

public abstract class View {
	protected float x, y;
	
	public View() {
		System.out.println("View osztaly nem tartalmaz koordinatakat!");
	}
	
	public View(float x, float y) {
		this.x = x;
		this.y = y;
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
