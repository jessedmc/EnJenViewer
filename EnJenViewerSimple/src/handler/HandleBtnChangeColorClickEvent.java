package handler;

import controller.Controller;
import javafx.scene.paint.Color;
import model.Model;

public class HandleBtnChangeColorClickEvent {
    private static HandleBtnChangeColorClickEvent instance;
    
    private HandleBtnChangeColorClickEvent() {
		
	}
    
    /**
     * Static method to return the only instance of the class.
     * 
     * @return - the only instance
     */
    public static HandleBtnChangeColorClickEvent instance() {
        if (instance == null) {
            instance = new HandleBtnChangeColorClickEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.changeColor();
    }
    
    public void changeColor() {
    	Color color = Controller.instance().getColorPicker();
    	Model.instance().getSeqSelectedInList().setColor(color);
    	Model.instance().updateView();
    }
}
