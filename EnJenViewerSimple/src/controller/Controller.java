package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import application.FXMLDocumentController;
import application.Main;
import controls.SeqViewerControl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Model;
import sequences.CompChartList;
import sequences.Sequence;

public class Controller {
    private static Controller instance;
    private FXMLDocumentController display;

    /**
     * Make it a singleton
     */
    private Controller() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static Controller instance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
   public void addSelectionToSeqViewerControl(int start, int end, Color color) {
	   this.display.addSelectionToSeqViewerControl(start, end, color);
   }
    
    public void getSelectedItemInListSequence() {
    	
    }
    
    public SeqViewerControl getSeqViewerControl() {
    	return display.getSeqViewerControl();
    }
    
    public MouseEvent getMouseEvent() {
		return this.display.getMouseEvent();
	}
    
    public void setSelectedItemInModelFromListSequence() {
    	Model.instance().setSeqSelectedInList(display.getSelectedItemInSequenceList());
    }
    
    public void setSelectedItemInModelFromListSelection() {
    	Model.instance().setSelectionSelectedInList(display.getSelectedItemInSelectionList());
    }
    
    public void loadTextFileToSequenceViewer() {
    	FileChooser chooser = new FileChooser();
    	String text = "";
    	try {
    		File file = chooser.showOpenDialog(Main.getStage());
    		Scanner scan = new Scanner(file);
    		text = scan.useDelimiter("\\A").next();
    		text = text.replaceAll("\n|\r", "");
    		scan.close();
    	}
    	catch (IOException ioe) {
    		System.out.println("Error opening text file");
    	}
    	Model.instance().setTempSeqString(text);
    	
    }
    
    public void saveWorkspace() {
    	FileChooser chooser = new FileChooser();
    	String text = "";
		File file = chooser.showSaveDialog(Main.getStage());
		boolean success = Model.instance().save(file);
		System.out.println("save success: " + success);
    }
    
    public void loadWorkspace() {
    	FileChooser chooser = new FileChooser();
    	String text = "";
		File file = chooser.showOpenDialog(Main.getStage());
		boolean success = Model.instance().load(file);
		System.out.println("load success: " + success);
    }
    
    public void clearCompare() {
    	this.display.clearCompare();
    }
    
    public void displayCompPercent() {
    	display.displayCompPercent(Model.instance().getSeqInViewer().getCompPercent());
    }
    
    public void displaySequenceLength() {
    	display.displaySequenceLength(Model.instance().getSeqInViewer().getSequence().length());
    }
    
    public void setDisplay(FXMLDocumentController display) {
    	this.display = display;
    }
    
    public String getInputText() {
    	return this.display.getInputText();
    }
    
    public int getSelectedType() {
    	return this.display.getSelectedType();
    }
    
    public void displayComparePercent() {
    	String displayPercent = String.valueOf(Model.instance().getComparePercent());
    	System.out.println("displayPercent in controller: " + displayPercent);
    	this.display.displayComparePercent(displayPercent);
    }
   
    public void showNameAndTypeAlreadyExists() {
    	String msg = "That name and type already exists";
    	Alert alert = new Alert(AlertType.NONE, msg, ButtonType.OK);
    	alert.setTitle("EnJenViewer");
    	alert.show();
    }
    
    public void displayCompChart() {
    	CompChartList seqList = Model.instance().getCompChartList();
    	Iterator<Sequence> iter = seqList.iterator();
    	while (iter.hasNext()) {
    		Sequence seq = iter.next();
    		HashMap<String, Integer> compPercent = seq.getCompPercent();
    		Color color = seq.getColor();
    		if (color == null) {
    			color = Color.LIGHTGREEN;
    		}
    		display.displayCompChart(compPercent, color);
    	}
    }
    
    public Color getColorPicker() {
		return display.getColorPicker();
	}
    
    public void createSelection(int start, int end, Color color) {
    	/*this.display.addSelectionToSeqViewerControl(start, end, color);
    	Model.instance().addMark(start, end, color);*/
    }
    
    public void clearTxtInput() {
    	this.display.clearTxtInput();
    }
   
}
