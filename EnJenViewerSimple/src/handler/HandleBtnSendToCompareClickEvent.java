package handler;

import events.BtnSendToCompareClickEvent;
import model.Model;

public class HandleBtnSendToCompareClickEvent {
	private static HandleBtnSendToCompareClickEvent instance;
    /**
     * Make it a singleton
     */
    private HandleBtnSendToCompareClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleBtnSendToCompareClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnSendToCompareClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.sendToCompare();
    }
    
    public void sendToCompare() {
    	if (!Model.instance().isCompareOneOccupied()) {
    		this.sendToCompareOne();
    	}
    	else if (!Model.instance().isCompareTwoOccupied()) {
    		this.sendToCompareTwo();
    	}
    }
    
    public void sendToCompareOne() {
    	System.out.println("in sendToCompareOne()");
    	Model.instance().setCompareOneOccupied(true);
    	Model.instance().setSeqInCompareOne(Model.instance().getSeqSelectedInList());
    }
    
    private void sendToCompareTwo() {
    	System.out.println("in sendToCompareTwo()");
    	Model.instance().setCompareTwoOccupied(true);
    	Model.instance().setSeqInCompareTwo(Model.instance().getSeqSelectedInList());
    }
    
    
}
