package sequences;

import javafx.scene.paint.Color;

public class RY extends Sequence {
	private static final long serialVersionUID = 1L;
	
	public RY(String sequence) {
		super(sequence);
		this.type = "RY";
	}
	
	public RY(String sequence, String name, Color color) {
		super(sequence, name, color);
		this.type = "RY";
	}
}
