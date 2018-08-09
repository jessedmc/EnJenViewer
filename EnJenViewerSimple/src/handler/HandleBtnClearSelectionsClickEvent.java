package handler;

import controller.Controller;
import model.Model;

public class HandleBtnClearSelectionsClickEvent {
    private static HandleBtnClearSelectionsClickEvent instance;
    
    private HandleBtnClearSelectionsClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnClearSelectionsClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnClearSelectionsClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.clearSelections();
    }
    
    public void clearSelections() {
    	Model.instance().clearSelections();
    
    }
}
