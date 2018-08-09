package handler;

import controller.Controller;


public class HandleListSequenceClickEvent {
	private static HandleListSequenceClickEvent instance;
    /**
     * Make it a singleton
     */
    private HandleListSequenceClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleListSequenceClickEvent instance() {
        if (instance == null) {
            instance = new HandleListSequenceClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Controller.instance().setSelectedItemInModelFromListSequence();
    }
}
