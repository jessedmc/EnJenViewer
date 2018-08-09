package handler;

import controller.Controller;

public class HandleListSelectionClickEvent {
	private static HandleListSelectionClickEvent instance;
    /**
     * Make it a singleton
     */
    private HandleListSelectionClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleListSelectionClickEvent instance() {
        if (instance == null) {
            instance = new HandleListSelectionClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Controller.instance().setSelectedItemInModelFromListSelection();
    }
}
