package Commons;

public enum Avatar {
	VIBORITA_VERDE("viborita_0.png"),
	VIBORITA_AMARILLA("viborita_1.png"),
	VIBORITA_AZUL("viborita_2.png"),
	VIBORITA_NARANJA("viborita_3.png"),
	MANZANITA("manzanita.png");
	
	private final String sprite;
	
	private Avatar(String sprite) {
		this.sprite = sprite;
	}
	
	public String getSprite() {
		return sprite;
	}
}
