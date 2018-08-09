package handler;

import controller.Controller;
import events.CalculateCompositionEvent;
import events.CalculateSequenceLengthEvent;

public class HandleCalculateGeneralInfoEvent {
    private static HandleCalculateGeneralInfoEvent instance;
    
    private HandleCalculateGeneralInfoEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleCalculateGeneralInfoEvent instance() {
        if (instance == null) {
            instance = new HandleCalculateGeneralInfoEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Handler.instance().handleEvent(CalculateCompositionEvent.instance());
    	Handler.instance().handleEvent(CalculateSequenceLengthEvent.instance());

    }
}
