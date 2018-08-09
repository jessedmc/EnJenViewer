package handler;

import controller.Controller;
import model.Model;
import parser.Parser;

public class HandleBtnCompareClickEvent {
    private static HandleBtnCompareClickEvent instance;
    
    private HandleBtnCompareClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnCompareClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnCompareClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.compare();
    	Controller.instance().displayComparePercent();
    }
    
    public void compare() {
    	double comparePercent = 0.0;
    	String one = Model.instance().getSeqInCompareOne().getSequence();
    	String two = Model.instance().getSeqInCompareTwo().getSequence();
    	comparePercent = Parser.compareTwoStrings(one, two);
    	Model.instance().setComparePercent(comparePercent);
    	System.out.println("getComparePercent: " + Model.instance().getComparePercent());
    }
}
