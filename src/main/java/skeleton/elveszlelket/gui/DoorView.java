package skeleton.elveszlelket.gui;

public class DoorView extends View {

	float x2, y2;
	
    public DoorView(float x, float y, float x2, float y2, String texturePath) {
        super(x, y, texturePath);
		this.x2 = x2;
		this.y2 = y2;
    }
    
    public float getX2() {
    	return x2;
    }
    
    public float getY2() {
    	return y2;
    }

    public void Draw(GameView jatekmegjelenito) {
        texture.setLayoutX(x);
        texture.setLayoutY(y);
        jatekmegjelenito.getChildren().add(texture);
    }

    @Override
    public void normalizeTexture(float tileX, float tileY) {
        texture.setFitHeight(tileY);
        texture.setFitWidth(tileX);
    }
}
