package skeleton.elveszlelket.gui;

/**
 * Az ajtók nézeti osztálya
 */
public class DoorView extends View {

    /**
     * Extra attríbútumok mivel 2 szobához is tartoznak.
     */
	float x2, y2;
	
    /**
     * Doorview construcktora
     * @param x 1.szélesség
     * @param y 1. hosszúság
     * @param x2 2.szélesség
     * @param y2 2. hosszúság
     * @param texturePath a textúra elérési útvonala
     */
    public DoorView(float x, float y, float x2, float y2, String texturePath) {
        super(x, y, texturePath);
		this.x2 = x2;
		this.y2 = y2;
    }
    
    /**
     * 
     * @return Visszaadja a 2. x-et
     */
    public float getX2() {
    	return x2;
    }
    
    /**
     * 
     * @return Visszaadja a 2. y-ot.
     */
    public float getY2() {
    	return y2;
    }

    /**
     * Kirajzolja a nézetet
     */
    public void Draw(GameView jatekmegjelenito) {
        texture.setLayoutX(x);
        texture.setLayoutY(y);
        jatekmegjelenito.getChildren().add(texture);
    }

    /**
     * normalizálja a koordinátákat.
     */
    @Override
    public void normalizeTexture(float tileX, float tileY) {
        texture.setFitHeight(tileY);
        texture.setFitWidth(tileX);
    }
}
