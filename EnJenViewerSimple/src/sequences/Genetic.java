package sequences;

import javafx.scene.paint.Color;

public class Genetic extends Sequence {
	private static final long serialVersionUID = 1L;

	public Genetic(String sequence) {
		super(sequence);
		this.type = "Genetic";
	}
	
	public Genetic(String sequence, String name, Color color) {
		super(sequence, name, color);
		this.type = "Genetic";
	}
}
