package handler;

import controller.Controller;
import events.AddSeqInViewerToCompChartEvent;
import events.CalculateGeneralInfoEvent;
import events.CreateSequenceEvent;

public class HandleBtnSaveClickEvent {
    private static HandleBtnSaveClickEvent instance;
    
    private HandleBtnSaveClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnSaveClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnSaveClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.save();
    }
    
    public void save() {
    	Controller.instance().saveWorkspace();
    }
}
