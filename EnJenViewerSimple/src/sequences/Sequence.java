package sequences;

import java.io.Serializable;


import java.util.HashMap;
import java.util.Iterator;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import model.Model;
import parser.Parser;
import selections.SelectionList;

public class Sequence implements Serializable, Matchable<String> {
    private static final long serialVersionUID = 1L;
    protected SelectionList selectionList = SelectionList.instance();;
    protected String sequence;
    protected String type;
    protected String name;
    protected transient Color color;
    protected static int nameCount = 0;
    protected HashMap<String, Integer> compCount = new HashMap<String, Integer>();
    protected HashMap<String, Integer> compPercent = new HashMap<String, Integer>();
    public static final int GENERAL = 0;
    public static final int GENETIC = 1;
    public static final int RY = 2;
    public static final int AMINO_ACID = 3;
    
    public Sequence() {
    	this.sequence = "";
    	this.name = "";
    	this.type = "";
    }
    
    public Sequence(String sequence) {
    	Sequence.nameCount++;
    	this.sequence = sequence;
    	this.type = "General";
    	this.name = "Sequence " + Sequence.nameCount;
    	this.color = Color.BLUE;
    	this.calcCount();
    	this.calcPercent();
    }
    
    public Sequence(String sequence, String name, Color color) {
    	this.sequence = sequence;
    	this.type = "General";
    	this.name = name;
    	this.color = color;
    	this.calcCount();
    	this.calcPercent();
    }
    
    public void calcCount() {
    	this.compCount = Parser.countEachChar(this.sequence);
    }
    
    public void calcPercent() {
    	int seqLen = this.sequence.length();
    	String key = "";
    	Iterator<String> iter = this.compCount.keySet().iterator();
    	while (iter.hasNext()) {
    		key = iter.next();
    		int count = compCount.get(key).intValue();
    		double frac = (double)count / (double)seqLen;
    		frac *= 100;
    		int percent = (int)frac;
    		this.compPercent.put((String)key, (Integer)percent);
    	}
    }
    
    public int getSequenceLength() {
    	return this.sequence.length();
    }
    
	@Override
	public boolean matches(String otherName, String otherType) {
		if (this.name.equals(otherName) && this.type.equals(otherType)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override 
	public String toString() {
		return this.name + " - " + this.type;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public HashMap<String, Integer> getCompPercent() {
		return compPercent;
	}

	public void setCompPercent(HashMap<String, Integer> compPercent) {
		this.compPercent = compPercent;
	}

	public SelectionList getSelectionList() {
		return selectionList;
	}

	public void setSelectionList(SelectionList selectionList) {
		this.selectionList = selectionList;
	}
	
	

}
