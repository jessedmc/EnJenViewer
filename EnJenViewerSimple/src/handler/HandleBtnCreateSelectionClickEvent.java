package handler;

import controller.Controller;
import javafx.scene.paint.Color;
import model.Model;
import parser.Parser;

public class HandleBtnCreateSelectionClickEvent {
    private static HandleBtnCreateSelectionClickEvent instance;
    
    private HandleBtnCreateSelectionClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnCreateSelectionClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnCreateSelectionClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	
    	this.createSelection();
    }
    
    public void createSelection() {
    	boolean success = false;
    	System.out.println("in createMark()");
    	String in = Controller.instance().getInputText();
    	if (!(in.equals(""))) {
    		int start = 0, end = 0;
	    	String[] inArrName = Parser.breakAt(" " , in, 2);
	    	String inName = inArrName[0];
	    	//in = inArrName[0] + inArrName[1];
	    	//inName = Parser.getSubString(inName, 1, inName.length());
	    	in = inArrName[1];
	    	
	    	String[] inArrPos = Parser.breakAt("," , in, 2);
	    	String inPosOne = inArrPos[0];
	    	String inPosTwo = inArrPos[1];
	    	inPosOne = Parser.getSubString(inPosOne, 1, inPosOne.length());
	    	inPosTwo = Parser.getSubString(inPosTwo, 1, inPosTwo.length());
	    	System.out.println("inName: " + inName);
	    	System.out.println("in: " + in);
	    	System.out.println("inPosOne: " + inPosOne);
	    	System.out.println("inPosTwo: " + inPosTwo);
	    	try {
	    		start = Integer.parseInt(inPosOne);
	    		end = Integer.parseInt(inPosTwo);
	    		if (!(inName.equals(""))) {
	    			success = true;
	    		}
	    	} catch (Exception err) {
	    		
	    	}
	    	Color color = Controller.instance().getColorPicker();
	    	if (success) {
	    		//Controller.instance().addSelectionToSeqViewerControl(start, end, color);
	    		Model.instance().addNewToSelectionList(inName, start, end, color);
	    	}
    	}
    }
}
