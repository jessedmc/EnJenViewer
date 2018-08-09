package handler;

import controller.Controller;
import model.Model;
import sequences.Sequence;

public class HandleBtnChangeTypeClickEvent {
    private static HandleBtnChangeTypeClickEvent instance;
    
    private HandleBtnChangeTypeClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnChangeTypeClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnChangeTypeClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.changeType();
    }
    
    public void changeType() {
    	Sequence seq = Model.instance().getSeqSelectedInList();
    	if (!(seq.getName().equals(""))) {
	    	int selectedType = Controller.instance().getSelectedType();
	    	String proposedType = Model.instance().mapTypeIntToString(selectedType);
	    	System.out.println("proposed type: " + proposedType);
	    	if (Model.instance().getSequenceList().search(seq.getName(), proposedType) == null) {
		    	switch (selectedType) {
		    		case Sequence.AMINO_ACID:
		    			seq.setType("Amino Acid");
		    			break;
		    		case Sequence.RY:
		    			seq.setType("RY");
		    			break;
		    		case Sequence.GENETIC:
		    			seq.setType("Genetic");
		    			break;
		    		default:
		    			seq.setType("General");
		    	}
		    	Model.instance().updateView();
	    	}
	    	else {
	    		Controller.instance().showNameAndTypeAlreadyExists();
	    	}
    	}
    }
    
    
}
