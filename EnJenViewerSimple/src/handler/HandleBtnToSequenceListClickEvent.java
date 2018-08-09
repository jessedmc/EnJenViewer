package handler;

import controller.Controller;
import events.CalculateGeneralInfoEvent;
import model.Model;

public class HandleBtnToSequenceListClickEvent {
    private static HandleBtnToSequenceListClickEvent instance;
    
    private HandleBtnToSequenceListClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnToSequenceListClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnToSequenceListClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Model.instance().addNewToSequenceList(Model.instance().getSeqInViewer());

    }
}
