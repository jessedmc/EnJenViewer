package handler;

import controller.Controller;

import events.CalculateGeneralInfoEvent;
import events.CreateSequenceEvent;
import events.AddSeqInViewerToCompChartEvent;

public class HandleBtnLoadTextClickEvent {
    private static HandleBtnLoadTextClickEvent instance;
    
    private HandleBtnLoadTextClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnLoadTextClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnLoadTextClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	Controller.instance().loadTextFileToSequenceViewer();
    	Handler.instance().handleEvent(CreateSequenceEvent.instance());
    	Handler.instance().handleEvent(CalculateGeneralInfoEvent.instance());
 
    	Handler.instance().handleEvent(AddSeqInViewerToCompChartEvent.instance());

    }
}
