package application;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import controller.Controller;
import controls.SeqViewerControl;
import events.BtnChangeColorClickEvent;
import events.BtnChangeNameClickEvent;
import events.BtnChangeTypeClickEvent;
import events.BtnClearCompareClickEvent;
import events.BtnClearSelectionsClickEvent;
import events.BtnCompareClickEvent;
import events.BtnCreateSelectionClickEvent;
import events.BtnLoadClickEvent;
import events.BtnLoadTextClickEvent;
import events.BtnSaveClickEvent;
import events.BtnSelectionToSequenceListClickEvent;
import events.BtnSendToCompareClickEvent;
import events.BtnTestClickEvent;
import events.BtnToSequenceListClickEvent;
import events.BtnToViewerClickEvent;
import events.ListSelectionClickEvent;
import events.ListSequenceClickEvent;
import events.SeqViewerClickEvent;
import handler.Handler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Model;
import selections.Selection;
import sequences.Sequence;

public class FXMLDocumentController implements Initializable, Observer {
	@FXML private Pane mainPane = new Pane();
	@FXML private Pane paneSv = new Pane();
	@FXML private ScrollPane scrSv = new ScrollPane();
	@FXML private Button btnLoadText;
	@FXML private Button btnToSequenceList;
	@FXML private Button btnSelectionToSequenceList;
	@FXML private Button btnChangeType;
	@FXML private Button btnChangeName;
	@FXML private Button btnChangeColor;
	@FXML private Button btnSendToCompare;
	@FXML private Button btnTest;
	@FXML private Button btnCreateSelection;
	@FXML private ListView<Sequence> listSequence;
	@FXML private ListView<Selection> listSelection;
	@FXML private TextField txtInput;
	@FXML private ToggleButton tgBtnGenetic;
	@FXML private ToggleButton tgBtnRY;
	@FXML private ToggleButton tgBtnAminoAcid;
	@FXML private ToggleGroup toggleGroupType;
	@FXML private Label lblSequenceSimilarity;
	@FXML private Label lblCompare;
	@FXML private Label lblCompareOne;
	@FXML private Label lblCompareTwo;
	@FXML private Label lblCompPercent;
	@FXML private Label lblLength;
	@FXML private Label lblLengthDisplay;
	@FXML private Label lblComparePercent;
	@FXML private Label lblNameAndType;
	@FXML private Label lblSequenceList;
	@FXML private Label lblMarkLengthDisplay;
	@FXML private Label lblMarkLength;
	@FXML private Label lblListMark;
	@FXML private Label lblMousePos;
	@FXML private LineChart<String, Number> compChart;
	@FXML private ColorPicker colorPicker;
	@FXML private Rectangle menuRectangle;
	@FXML private Rectangle rectListMark;
	@FXML private Rectangle rectListSequence;
	@FXML private Text text;
	@FXML private Line mainLine;

	GridPane gridCompPercent = new GridPane();
	
	private Model model;
	private SeqViewerControl seqViewerControl;
	private Sequence selectedItemInSequenceList;
	private Selection selectedItemInSelectionList;
	private boolean cssOn = true;
	private MouseEvent mouseEvent;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.menuRectangle.toBack();
		this.rectListSequence.toBack();
		if (cssOn) {
			this.btnLoadText.getStyleClass().add("button");
			this.btnSendToCompare.getStyleClass().add("button");
			this.btnChangeType.getStyleClass().add("button");
			this.btnChangeName.getStyleClass().add("button");
			this.btnChangeColor.getStyleClass().add("button");
			this.tgBtnGenetic.getStyleClass().add("button");
			this.tgBtnRY.getStyleClass().add("button");
			this.tgBtnAminoAcid.getStyleClass().add("button");		
			this.lblSequenceList.getStyleClass().add("label1");
			this.lblSequenceList.getStyleClass().add("label1");
			this.lblListMark.getStyleClass().add("label1");
			this.lblMousePos.getStyleClass().add("label1");
			this.lblCompare.getStyleClass().add("label1");
			this.lblSequenceSimilarity.getStyleClass().add("label1");
			this.lblCompPercent.getStyleClass().add("label1");
			this.lblComparePercent.getStyleClass().add("label1");
			this.lblNameAndType.getStyleClass().add("label1");
			this.lblCompareOne.getStyleClass().add("label1");
			this.lblCompareTwo.getStyleClass().add("label1");
			this.lblMarkLengthDisplay.getStyleClass().add("label1");
			this.lblMarkLength.getStyleClass().add("label1");
			this.lblLength.getStyleClass().add("label1");
			this.lblLengthDisplay.getStyleClass().add("label1");
			this.listSequence.getStyleClass().add("listSequence");
			this.listSelection.getStyleClass().add("listSequence");
			this.colorPicker.getStyleClass().add("colorPicker");
			this.txtInput.getStyleClass().add("txtChangeName");
			//this.compChart.getStyleClass().add("compChart");
			this.mainPane.getStyleClass().add("mainPane");
			this.menuRectangle.getStyleClass().add("menuRectangle");
			this.rectListSequence.getStyleClass().add("captionRectangle");
			this.rectListMark.getStyleClass().add("captionRectangle");
		}
		SeqViewerControl.createSeqViewerControl(scrSv, paneSv);
		seqViewerControl = SeqViewerControl.instance();
		this.seqViewerControl.setVGap(10);
		Color colorBack = Color.web("#101f3d");
		Color colorFont = Color.web("#f5f6fa");
		this.seqViewerControl.setSvBackgroundColor(colorBack);
		this.seqViewerControl.setCellBackColor(colorBack);
		this.seqViewerControl.setTextColor(colorFont);
		
		Controller.instance().setDisplay(this);
		this.model = Model.instance();
		model.addObserver(this);
		this.mainPane.getChildren().add(gridCompPercent);
		
		listSequence.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				listSequenceClick(event);
			}
		});
		
		listSelection.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				listSelectionClick(event);
			}
		});
		
		this.seqViewerControl.getScrSv().setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				getMousePos();
			}
		});
		
		this.seqViewerControl.getPaneSv().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				seqViewerClick(event);
			}
		});
		
		
	}
	
	@Override
    public void update(Observable o, Object arg) {
		seqViewerControl.setText(model.getSeqInViewer().getSequence());
		seqViewerControl.refresh();
		Iterator<Sequence> iter = model.getSequenceList().iterator();
		listSequence.getItems().clear();
		while (iter.hasNext()) {
			Sequence seq = iter.next();
			String selName = model.getSeqSelectedInList().getName();
			String selType = model.getSeqSelectedInList().getType();
			listSequence.getItems().add(seq);
			if (seq.matches(selName, selType)) {
				listSequence.getSelectionModel().select(seq);
			}
		}
		this.seqViewerControl.clearSelectionList();
		Iterator<Selection> iterSel = model.getSeqInViewer().getSelectionList().iterator();
		listSelection.getItems().clear();
		while (iterSel.hasNext()) {
			Selection sel = iterSel.next();
			System.out.println("ggggggggg sel.name: " + sel.getName());
			int selStart = model.getSelectionSelectedInList().getMarkStart().getPosition();
			int selEnd = model.getSelectionSelectedInList().getMarkEnd().getPosition();
			listSelection.getItems().add(sel);
			this.seqViewerControl.createSelection(sel.getMarkStart().getPosition(), sel.getMarkEnd().getPosition(), sel.getMarkStart().getColor());
			if (sel.matches(selStart, selEnd)) {
				listSelection.getSelectionModel().select(sel);
			}
		}
		
		
		
		
		lblCompareOne.setText(model.getSeqInCompareOne().toString());
		lblCompareTwo.setText(model.getSeqInCompareTwo().toString());
		this.lblNameAndType.setText(model.getSeqInViewer().getName() + " - " + model.getSeqInViewer().getType());
		Controller.instance().displayCompChart();
		Controller.instance().displayCompPercent();
		Controller.instance().displaySequenceLength();
		Controller.instance().displayComparePercent();
		//this.seqViewerControl.drawAllMarks();
	//	this.seqViewerControl.createMark(23, 40, Color.YELLOW);
		this.btnTest.setPrefWidth(328);
	}
	
	public SeqViewerControl getSeqViewerControl() {
		return this.seqViewerControl;
	}
	
	public MouseEvent getMouseEvent() {
		return this.mouseEvent;
	}
	
	public void seqViewerClick(MouseEvent event) {
		this.mouseEvent = event;
		Handler.instance().handleEvent(SeqViewerClickEvent.instance());
	}
	
	@FXML
	public void btnLoadClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnLoadClickEvent.instance());
	}
	
	@FXML
	public void btnClearSelectionsClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnClearSelectionsClickEvent.instance());
	}
	
	@FXML
	public void btnSaveClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnSaveClickEvent.instance());
	}
	
	@FXML
	public void btnChangeNameClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnChangeNameClickEvent.instance());
	}
	
	@FXML
	public void btnChangeTypeClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnChangeTypeClickEvent.instance());
	}

	@FXML
	public void btnToSequenceListClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnToSequenceListClickEvent.instance());
	}
	
	@FXML
	public void btnSelectionToSequenceListClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnSelectionToSequenceListClickEvent.instance());
	}
	
	@FXML
	public void btnLoadTextClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnLoadTextClickEvent.instance());
	}
	
	@FXML
	public void btnToViewerClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnToViewerClickEvent.instance());
	}
	
	@FXML
	public void btnSendToCompareClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnSendToCompareClickEvent.instance());
	}

	@FXML
	public void btnClearCompareClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnClearCompareClickEvent.instance());
	}
	
	@FXML
	public void btnTestClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnTestClickEvent.instance());
	}
	
	@FXML
	public void btnCompareClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnCompareClickEvent.instance());
	}
	
	@FXML
	public void btnChangeColorClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnChangeColorClickEvent.instance());
	}
	
	@FXML
	public void btnCreateSelectionClick(ActionEvent event) {
		Handler.instance().handleEvent(BtnCreateSelectionClickEvent.instance());
	}
	
	public void getMousePos() {
		this.lblMousePos.setText(String.valueOf(this.seqViewerControl.getMousePos()));
	}
	
	public void listSequenceClick(MouseEvent event) {
		this.selectedItemInSequenceList = listSequence.getSelectionModel().getSelectedItem();
		Handler.instance().handleEvent(ListSequenceClickEvent.instance());
	}
	
	public void listSelectionClick(MouseEvent event) {
		this.selectedItemInSelectionList = listSelection.getSelectionModel().getSelectedItem();
		Handler.instance().handleEvent(ListSelectionClickEvent.instance());
	}

	public Sequence getSelectedItemInSequenceList() {
		return selectedItemInSequenceList;
	}
	
	public Selection getSelectedItemInSelectionList() {
		return selectedItemInSelectionList;
	}
	
	public String getInputText() {
		return txtInput.getText();
	}
	
	public void addSelectionToSeqViewerControl(int start, int end, Color color) {
		this.seqViewerControl.createSelection(start, end, color);
	}
	
	public int getSelectedType() {
		if (this.tgBtnAminoAcid.isSelected()) {
			return Sequence.AMINO_ACID;
		}
		else if (this.tgBtnGenetic.isSelected()) {
			return Sequence.GENETIC;
		}
		else if (this.tgBtnRY.isSelected()) {
			return Sequence.RY;
		}
		else {
			return Sequence.GENERAL;
		}
	}
	
	public void displayCompPercent(HashMap<String, Integer> map) {
		this.gridCompPercent.getChildren().clear();
		int startX = (int)lblCompPercent.getLayoutX();
		int startY = (int)lblCompPercent.getLayoutY() + 30;
		int numOfCols = 6;
		int colNum = 0, rowNum = 0;
		String key = "";
		Integer value = 0;
		gridCompPercent.setLayoutX(startX);
		gridCompPercent.setLayoutY(startY);
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			value = map.get(key);
			Label tempLabel = new Label(key + " " + value + "    ");
			tempLabel.getStyleClass().add("label2");
			gridCompPercent.add(tempLabel, colNum, rowNum);
			colNum++;
			if (colNum > numOfCols) {
				colNum = 0;
				rowNum++;
			}
		}
	}
	
	public void clearCompare() {
		this.lblComparePercent.setText("");
    }
	
	public void displayCompChart(HashMap<String, Integer> compPercent, Color color) {
    	XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
    	Iterator<String> iter = compPercent.keySet().iterator();
    	Integer percent = 0;
    	String key = "";
    	while (iter.hasNext()) {
    		key = iter.next();
    		percent = compPercent.get(key);
    		series.getData().add(new XYChart.Data<>(key, percent));
    	}
    	
    	this.compChart.getData().add(series);
    	int blue = (int)(color.getBlue() * 255);
    	int red = (int)(color.getRed() * 255);
    	int green = (int)(color.getGreen() * 255);
    	System.out.println("color rgb " + red + " , " + green + " , " + blue);
    	this.compChart.applyCss();
    	series.getNode().setStyle("-fx-stroke: rgb(" + red + "," + green + "," + blue + ");");
    	//this.compChart.setStyle(".chart-line-symbol series0 default-color2 {-fx-background-color: #ff00ff;}");
    	
	}
	
    public void clearTxtInput() {
    	this.txtInput.setText("");
    }	
	
	public Color getColorPicker() {
		return colorPicker.getValue();
	}
	
	public void displaySequenceLength(int len) {
		this.lblLength.setText(String.valueOf(len));
	}
	
	public void displayComparePercent(String in) {
		this.lblComparePercent.setText(in);
	}
	
	
}
