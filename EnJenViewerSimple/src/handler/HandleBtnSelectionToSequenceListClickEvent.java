package handler;

import model.Model;

public class HandleBtnSelectionToSequenceListClickEvent {
    private static HandleBtnSelectionToSequenceListClickEvent instance;
    
    private HandleBtnSelectionToSequenceListClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnSelectionToSequenceListClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnSelectionToSequenceListClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Model.instance().addSelectionToSequenceList();
    }
}
