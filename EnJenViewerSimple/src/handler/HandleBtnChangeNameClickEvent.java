package handler;

import controller.Controller;
import model.Model;
import sequences.Sequence;

public class HandleBtnChangeNameClickEvent {
    private static HandleBtnChangeNameClickEvent instance;
    
    private HandleBtnChangeNameClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnChangeNameClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnChangeNameClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.changeName();
    }
    
    public void changeName() {
    	if (!(Model.instance().getSeqSelectedInList().getName().equals(""))) {
	    	String inName = Controller.instance().getInputText();
	    	if (!(inName.equals(""))) {
		    	String name = Model.instance().getSeqSelectedInList().getName();
		    	String type = Model.instance().getSeqSelectedInList().getType();
		    	Sequence seq = Model.instance().getSequenceList().search(name, type);
		    	if (Model.instance().getSequenceList().search(inName, type) != null) {
		    		Controller.instance().showNameAndTypeAlreadyExists();
		    	}
		    	else {
		    		seq.setName(inName);
		    		Model.instance().updateView();
		    	}
	    	}
    	}
    }
}
