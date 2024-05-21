package skeleton.elveszlelket.gui;

public class DoorView extends View {

    public DoorView(float x, float y, String texturePath) {
        super(x, y, texturePath);
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
