package handler;

import controller.Controller;
import model.Model;

public class HandleBtnClearCompareClickEvent {
    private static HandleBtnClearCompareClickEvent instance;
    
    private HandleBtnClearCompareClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnClearCompareClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnClearCompareClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.clearCompare();
    }
    
    public void clearCompare() {
    	Model.instance().clearCompare();
    	Controller.instance().clearCompare();
    }
}
