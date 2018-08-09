package sequences;

import javafx.scene.paint.Color;

public class AminoAcid extends Sequence {
	private static final long serialVersionUID = 1L;

	public AminoAcid(String sequence) {
		super(sequence);
		this.type = "Amino Acid";
	}
	
	public AminoAcid(String sequence, String name, Color color) {
		super(sequence, name, color);
		this.type = "Amino Acid";
	}
}
