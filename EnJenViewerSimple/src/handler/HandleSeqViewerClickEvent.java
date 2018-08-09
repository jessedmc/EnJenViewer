package handler;

import java.util.Iterator;

import controller.Controller;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Model;
import selections.Selection;
import sequences.Sequence;

public class HandleSeqViewerClickEvent {
	private static HandleSeqViewerClickEvent instance;
    /**
     * Make it a singleton
     */
    private HandleSeqViewerClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleSeqViewerClickEvent instance() {
        if (instance == null) {
            instance = new HandleSeqViewerClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.mouseClick();
    }
    
    public void mouseClick() {
    	MouseEvent me = Controller.instance().getMouseEvent();
    	Color color = Controller.instance().getColorPicker();
    	Controller.instance().getSeqViewerControl().mouseClick(me, color);
    	int scenario = 0;
    	/*
    	 *   3 scenarios
    	 *   1. No Marks exist
    	 *   2. Only markStart exists
    	 *   3. Two marks exist
    	 */
    	Sequence seq = Model.instance().getSeqInViewer();
    	Iterator<Selection> iter = seq.getSelectionList().iterator();
    	while (iter.hasNext()) {
    		Selection sel = iter.next();
    		if (sel.getMarkEnd().isEmpty()) {
    			
    		}
    		
    	}
    	
    }
}
