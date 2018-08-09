package handler;

import controller.Controller;
import javafx.scene.paint.Color;
import model.Model;

public class HandleAddSeqInViewerToCompChartEvent {
    private static HandleAddSeqInViewerToCompChartEvent instance;
    
    private HandleAddSeqInViewerToCompChartEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleAddSeqInViewerToCompChartEvent instance() {
        if (instance == null) {
            instance = new HandleAddSeqInViewerToCompChartEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.addSeqInViewerToCompChart();
    }
    
    public void addSeqInViewerToCompChart() {
    	Model.instance().addNewToCompChartList(Model.instance().getSeqInViewer());
    }
}
