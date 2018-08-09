package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import parser.Parser;
import selections.Selection;
import selections.SelectionList;
import sequences.CompChartList;
import sequences.Sequence;
import sequences.SequenceFactory;
import sequences.SequenceList;

public class Model extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Model instance;
    private String tempSeqString;
    private Sequence seqInViewer;
    private Sequence seqSelectedInList;
    private Selection selectionSelectedInList;
    private SequenceList sequenceList;
    private SelectionList selectionList;
    private CompChartList compChartList;
    private boolean compareOneOccupied, compareTwoOccupied;
    private Sequence seqInCompareOne, seqInCompareTwo;
    private HashMap<String, Integer> compCount = new HashMap<String, Integer>();
    private HashMap<String, Integer> compPercent = new HashMap<String, Integer>();
    private double comparePercent = 0.0;
    
  
    /**
     * Make it a singleton
     */
    protected Model() {
        this.sequenceList = SequenceList.instance();
        this.selectionList = SelectionList.instance();
        this.compChartList = CompChartList.instance();
        this.compareOneOccupied = false;
        this.compareTwoOccupied = false;
        this.seqInCompareOne = SequenceFactory.instance().createSequenceBlank();
        this.seqInCompareTwo = SequenceFactory.instance().createSequenceBlank();
        this.seqInViewer = SequenceFactory.instance().createSequenceBlank();
        this.seqSelectedInList = SequenceFactory.instance().createSequenceBlank();
        this.tempSeqString = "";
    }
    
    public boolean save(File file) {
    	try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.seqInViewer);
            oos.writeObject(this.seqSelectedInList);
            oos.writeObject(this.selectionSelectedInList);
            oos.writeObject(this.sequenceList);
            oos.writeObject(this.selectionList);
            oos.writeObject(this.compChartList);
            oos.writeObject(this.compCount);
            oos.writeObject(this.compPercent);
            fos.close();
            oos.close();
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
  
    public boolean load(File file) {
    	try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.seqInViewer = (Sequence) ois.readObject();
            this.seqSelectedInList = (Sequence) ois.readObject();
            this.selectionSelectedInList = (Selection) ois.readObject();
            this.sequenceList = (SequenceList) ois.readObject();
            this.selectionList = (SelectionList) ois.readObject();
            this.compChartList = (CompChartList) ois.readObject();
            this.compCount = (HashMap<String, Integer>) ois.readObject();
            this.compPercent = (HashMap<String, Integer>) ois.readObject();
            fis.close();
            ois.close();
            this.updateView();
            //MemberIdServer.retrieve(input);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }
    
    public void createMark(int start, int end, Color color) {
    	
    }
    
    public void removeMark(int start, int end) {
    	/*for (Mark m : this.markList) {
    		if (m.start == start) {
    			if (m.end == end) {
    				this.markList.remove(m);
    			}
    		}
    	}*/
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static Model instance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }
    
    /**
     * Updates the view.
     * 
     */
    public void setChanged() {
        updateView();
    }
    
    /**
     * Notifies the view
     */
    public void updateView() {
        super.setChanged();
        notifyObservers();
    }
    
    public SequenceList getSequenceList() {
    	return sequenceList;
    }
 
    
    public ObservableList<Sequence> getAsObservableList() {
    	return sequenceList.toObservableList();
    }
    
    public void addNewToSequenceList(Sequence seq) {
    	sequenceList.insertSequence(seq);
    	this.updateView();
    }
    
    public void addSelectionToSequenceList() {
    	String str = this.seqInViewer.getSequence();
    	str = Parser.getSubString(str, this.selectionSelectedInList.getMarkStart().getPosition(), this.selectionSelectedInList.getMarkEnd().getPosition());
    	String name = this.selectionSelectedInList.getName();
    	System.out.println("xxxxxxxxxxxxxxxxxxxxx in Model.addSelectionToSequenceList() name: " + name);
    	Color color = this.selectionSelectedInList.getMarkStart().getColor();
    	int typ = Parser.detectGeneticAAOrRYType(str);
    	Sequence seq = SequenceFactory.instance().createSequence(str, name, color, typ);
    	sequenceList.insertSequence(seq);
    	this.updateView();
    }
    
    public void addNewToSelectionList(String name, int start, int end, Color color) {
    	/*this.seqInViewer.getSelectionList().insertSelection(name, start, end, color);
    	if (this.seqInViewer.getSelectionList().size() == 1) {
    		this.selectionSelectedInList = this.seqInViewer.getSelectionList().get(0);
    	}*/
    	Selection sel = new Selection(name, start, end, color);
    	if (this.selectionList.insertSelection(sel)) {
    		this.selectionSelectedInList = sel;
    		this.updateView();
    	}
    }
    
    public void clearSelections() {
    	this.selectionList.clear();
    	this.updateView();
    }
    
    public void addNewToCompChartList(Sequence seq) {
    	compChartList.insertSequence(seq);
    	this.updateView();
    }
    
    public void printSequenceList() {
    	sequenceList.printList();
    }
    
    public void clearCompare() {
    	this.compareOneOccupied = false;
    	this.compareTwoOccupied = false;
    	this.seqInCompareOne = SequenceFactory.instance().createSequenceBlank();
    	this.seqInCompareTwo = SequenceFactory.instance().createSequenceBlank();
    	this.updateView();
    }
	
	public void createSequence(String sequence) {
		this.seqInViewer = SequenceFactory.instance().createSequence(sequence);
		this.updateView();
	}
	
	public String mapTypeIntToString(int intType) {
		switch (intType) {
			case Sequence.AMINO_ACID:
				return "Amino Acid";
			case Sequence.GENERAL:
				return "General";
			case Sequence.GENETIC:
				return "Genetic";
			case Sequence.RY:
				return "RY";
		}
		return "";
		
	}

	//  ************ Getters/Setters *************** //
	public void setSequenceList(SequenceList sequenceList) {
		this.sequenceList = sequenceList;
	}

	public Sequence getSeqInViewer() {
		return seqInViewer;
	}

	public void setSeqInViewer(Sequence seqInViewer) {
		this.seqInViewer = seqInViewer;
		this.updateView();
	}

	public Sequence getSeqSelectedInList() {
		return seqSelectedInList;
	}
	
	public Selection getSelectionSelectedInList() {
		return selectionSelectedInList;
	}
	
	public void setSeqSelectedInList(Sequence sequence) {
		this.seqSelectedInList = sequence;
	}
	
	public void setSelectionSelectedInList(Selection selection) {
		this.selectionSelectedInList = selection;
	}
	
	public boolean isCompareOneOccupied() {
		return compareOneOccupied;
	}

	public void setCompareOneOccupied(boolean compareOneOccupied) {
		this.compareOneOccupied = compareOneOccupied;
	}

	public boolean isCompareTwoOccupied() {
		return compareTwoOccupied;
	}

	public void setCompareTwoOccupied(boolean compareTwoOccupied) {
		this.compareTwoOccupied = compareTwoOccupied;
	}

	public Sequence getSeqInCompareOne() {
		return seqInCompareOne;
	}

	public void setSeqInCompareOne(Sequence seqInCompareOne) {
		this.seqInCompareOne = seqInCompareOne;
		this.updateView();
	}

	public Sequence getSeqInCompareTwo() {
		return seqInCompareTwo;
	}

	public void setSeqInCompareTwo(Sequence seqInCompareTwo) {
		this.seqInCompareTwo = seqInCompareTwo;
		this.updateView();
	}

	public HashMap<String, Integer> getCompCount() {
		return compCount;
	}

	public void setCompCount(HashMap<String, Integer> compCount) {
		this.compCount = compCount;
	}

	public HashMap<String, Integer> getCompPercent() {
		return compPercent;
	}

	public void setCompPercent(HashMap<String, Integer> compPercent) {
		this.compPercent = compPercent;
	}

	public double getComparePercent() {
		return comparePercent;
	}

	public void setComparePercent(double comparePercent) {
		this.comparePercent = comparePercent;
	}

	public String getTempSeqString() {
		return tempSeqString;
	}

	public void setTempSeqString(String tempSeqString) {
		this.tempSeqString = tempSeqString;
	}

	public CompChartList getCompChartList() {
		return compChartList;
	}

	public void setCompChartList(CompChartList compChartList) {
		this.compChartList = compChartList;
	}

	
	
	
	
}
