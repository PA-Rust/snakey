package Commons;

import java.awt.Color;

public enum Avatar {
	VIBORITA_VERDE("viborita_0.png", Color.GREEN),
	VIBORITA_AMARILLA("viborita_3.png", Color.YELLOW),
	VIBORITA_AZUL("viborita_1.png", Color.BLUE),
	VIBORITA_NARANJA("viborita_2.png", Color.ORANGE),
	MANZANITA("manzanita.png", Color.RED);
	
	private final String sprite;
	private final Color color;
	
	private Avatar(String sprite, Color color) {
		this.sprite = sprite;
		this.color = color;
	}
	
	public String getSprite() {
		return sprite;
	}
	
	public Color getColor() {
		return color;
	}
}
