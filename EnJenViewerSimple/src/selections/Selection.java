package selections;

import java.io.Serializable;

import javafx.scene.paint.Color;
import sequences.Matchable;



public class Selection implements Serializable, Matchable<Integer> {
	private static final long serialVersionUID = 1L;
	private int length = 0;
	private String name = "";
	private Mark markStart = new Mark();
	private Mark markEnd = new Mark();
	private String seq = "";


	public Selection(String name, int start, int end, Color color) {
		this.name = name;
		this.markStart = new Mark(start, color);
		this.markEnd = new Mark(end, color);
		this.length = this.markEnd.getPosition() - this.markStart.getPosition();
	}
	
	@Override
	public boolean matches(Integer start, Integer end) {
		if (this.markStart.getPosition() == start.intValue()) {
			if (this.markEnd.getPosition() == end.intValue()) {
				return true;
			}
		}
		return false;
	}
	
	@Override 
	public String toString() {
		return this.name + "  " + String.valueOf(this.markStart.getPosition())
				+ "," + String.valueOf(this.markEnd.getPosition());
	}
	
	public void setMarkStart(int position) {
		this.markStart = new Mark(position);
	}
	
	public void setMarkEnd(int position) {
		this.markEnd = new Mark(position);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Mark getMarkStart() {
		return markStart;
	}

	public void setMarkStart(Mark markStart) {
		this.markStart = markStart;
	}

	public Mark getMarkEnd() {
		return markEnd;
	}

	public void setMarkEnd(Mark markEnd) {
		this.markEnd = markEnd;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	
}
