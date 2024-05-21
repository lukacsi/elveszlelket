package skeleton.elveszlelket.gui;

public class ItemView extends View {
	
	/**
	 * ItemView konstruktora.
	 * @param x A tárgy x koordinátája.
	 * @param y A tárgy y koordinátája.
	 * @param texturePath A tárgy textúrájának elérési útvonala.
	 */
	public ItemView(float x, float y, String texturePath) {
		super(x,y,texturePath);
	}
	
	/**
	 * Kirajzolja a tárgyat.
	 * @param jatekmegjelenito A GameWiew.
	 */
	public void Draw(GameView jatekmegjelenito) {
		texture.setLayoutX(x);
		texture.setLayoutY(y);
		jatekmegjelenito.getChildren().add(texture);
	}

	/**
	 * Ráilleszti a tárgyra a textúrát.
	 * @param tileX Az egységnégyzet szélessége.
	 * @param tileY Az egységnégyzet magassága.
	 */
	@Override
	public void normalizeTexture(float tileX, float tileY) {
		texture.setFitHeight(tileY);
		texture.setFitWidth(tileX);
	}
}
