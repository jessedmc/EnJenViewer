package handler;

import controller.Controller;
import model.Model;
import sequences.Sequence;

public class HandleBtnToViewerClickEvent {
    private static HandleBtnToViewerClickEvent instance;
    
    private HandleBtnToViewerClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnToViewerClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnToViewerClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.seqToViewer();
    }
    
    public void seqToViewer() {
    	Sequence seq = Model.instance().getSeqSelectedInList();
    	Model.instance().setSeqInViewer(seq);
    }
}
