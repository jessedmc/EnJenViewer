package handler;

import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import javafx.scene.paint.Color;
import model.Model;
import parser.Parser;

public class HandleBtnTestClickEvent {
	private static HandleBtnTestClickEvent instance;
    /**
     * Make it a singleton
     */
    private HandleBtnTestClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleBtnTestClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnTestClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	System.out.println("in test button seqInViewer color: " + Model.instance().getSeqInViewer().getColor().toString());
    	//this.countChars();
    	this.doMark();
    }
    
    public void doMark() {
    	//Controller.instance().createMark(23, 246, Color.YELLOW);
    }
    
    public void countChars() {
    	HashMap<String, Integer> count = Parser.countEachChar(Model.instance().getSeqInViewer().getSequence());
    	Iterator<String> iter = count.keySet().iterator();
    	String key = "";
    	while (iter.hasNext()) {
    		key = (String)iter.next();
    		System.out.println(key + " " + count.get(key));
    	}
    	System.out.println("map length: " + count.size());
    	
    }
    
}
