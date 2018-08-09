package selections;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;

	private int position = -1;
	private transient Color color = Color.ORANGE;
	private Rectangle rect;
	
	public Mark() {
		
	}
	
	public Mark(int position) {
		this.position = position;
	}
	
	public Mark(int position, Color color) {
		this.position = position;
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isEmpty() {
		if (this.position == -1) {
			return true;
		}
		return false;
	}
	
}
