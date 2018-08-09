package handler;

import controller.Controller;

public class HandleBtnLoadClickEvent {
    private static HandleBtnLoadClickEvent instance;
    
    private HandleBtnLoadClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnLoadClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnLoadClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Controller.instance().loadWorkspace();
    }
    
}
