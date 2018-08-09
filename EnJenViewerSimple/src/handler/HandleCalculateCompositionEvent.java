package handler;

import java.util.HashMap;
import java.util.Iterator;

import controller.Controller;
import model.Model;
import parser.Parser;

public class HandleCalculateCompositionEvent {
    private static HandleCalculateCompositionEvent instance;
    
    private HandleCalculateCompositionEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleCalculateCompositionEvent instance() {
        if (instance == null) {
            instance = new HandleCalculateCompositionEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.calcCount();
    	this.calcPercent();
    	Controller.instance().displayCompPercent();

    }
    
    public void calcCount() {
    	String seq = Model.instance().getSeqInViewer().getSequence();
    	Model.instance().setCompCount(Parser.countEachChar(seq));
    }
    
    public void calcPercent() {
    	HashMap<String, Integer> compCount = Model.instance().getCompCount();
    	HashMap<String, Integer> compPercent = new HashMap<String, Integer>();
    	int seqLen = Model.instance().getSeqInViewer().getSequence().length();
    	String key = "";
    	Iterator<String> iter = compCount.keySet().iterator();
    	while (iter.hasNext()) {
    		key = iter.next();
    		int count = compCount.get(key).intValue();
    		double frac = (double)count / (double)seqLen;
    		frac *= 100;
    		int percent = (int)frac;
    		compPercent.put((String)key, (Integer)percent);
    	}
    	Model.instance().setCompCount(compCount);
    	Model.instance().setCompPercent(compPercent);
    }
}
