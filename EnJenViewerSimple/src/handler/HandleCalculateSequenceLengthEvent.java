package handler;

import controller.Controller;

public class HandleCalculateSequenceLengthEvent {
    private static HandleCalculateSequenceLengthEvent instance;
    
    private HandleCalculateSequenceLengthEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleCalculateSequenceLengthEvent instance() {
        if (instance == null) {
            instance = new HandleCalculateSequenceLengthEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Controller.instance().displaySequenceLength();
    }
}
