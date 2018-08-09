/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import parser.Parser;

/**
 *
 * @author ne5469ww
 */
public class SeqViewerControl {
	private static SeqViewerControl instance;
    private ScrollPane scrSv;
    private Pane paneSv;
    private ArrayList<Selection> selectionList = new ArrayList<Selection>();
    private int mousePos = 0;
    private int mouseLabelIndex = 0;
    private Mark lastMark = new Mark();
    private Font font = new Font("Courier New", 12);
    
   
    private static final double defaultRightPad = 16.0;
    private double paneSvHeight = 0.0;
    private int numOfRows = 0, numOfCols = 0, totalCells = 0;
    private double leftPad = 30.0, rightPad = 10.0, topPad = 10.0, bottomPad = 10;
    
    private Pos cellAlignment = Pos.CENTER;
    private double vGap = 10.0, hGap = 20.0;
    private String seq;
    private String[] seqArr;
    private int charStep = 10, numOfRemCols = 0, numOfRemRows = 0;
    
    private Label[] lblSeq;
    private Mark mainMarkStart, mainMarkEnd;
    private Background cellBack;
    private Color cellBackColor = Color.WHITE, svBackColor = Color.WHITE, textColor = Color.BLACK;
    private Color markColor = Color.AQUA;
    
    private double cellWidth, cellHeight, prefWidth, prefHeight;
    //private double leftPadding, rightPadding, topPadding, bottomPadding;
    private double width, height;
    
    
    public SeqViewerControl(ScrollPane scrSv, Pane paneSv) {
        this.paneSv = paneSv;
        this.scrSv = scrSv;
        //fontLoader = Toolkit.getToolkit().getFontLoader();
        
        paneSv.setLayoutX(0);
        paneSv.setLayoutY(0);
        
        paneSv.setPrefWidth(scrSv.getPrefWidth());
       // paneSv.setPrefHeight(2000);
        paneSv.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
        
        scrSv.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrSv.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        
        
        this.paneSv.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				updateMousePos(event);
			}
		});
        
        
    }
    
    public int getLastMarkPosition() {
    	return this.lastMark.position;
    }
    
    public Rectangle getLastMarkRect() {
    	return this.lastMark.rect;
    }
    
    public Color getLastMarkColor() {
    	return this.lastMark.color;
    }
    
    
    
    public static SeqViewerControl instance() {
        return instance;
    }
    
    public static SeqViewerControl createSeqViewerControl(ScrollPane scrSv, Pane paneSv) {
    	if (instance == null) {
            instance = new SeqViewerControl(scrSv, paneSv);
        }
        return instance;
    }
    
    public void clearSelectionList() {
    	this.selectionList.clear();
    }
    
    public void mouseClick(MouseEvent me, Color color) {
    	this.markColor = color;
    	createMark(me);
    }
    
    public void updateMousePos(MouseEvent me) {
    	double meX = me.getX(), meY = me.getY();
		if (this.lblSeq != null) {
	    	for (int h = 0; h < this.lblSeq.length; h++) {
	    		double top = this.lblSeq[h].getLayoutY();
	    		double bottom = this.lblSeq[h].getLayoutY() + this.cellHeight;
	    		double left = this.lblSeq[h].getLayoutX();
	    		double right = this.lblSeq[h].getLayoutX() + this.cellWidth;
	    		if (meY > top) {
	    			if (meY < bottom) {
    					if (meX > left) {
    						if (meX < right) {
    							double charW = meX - left;
    							double oneCharW = this.calcWidthOfChars(1);
    							int charIn = (int)(charW / oneCharW);
    							int charBegin = h * this.charStep;	    						
    							this.mousePos = charBegin + charIn + 1;
    							this.mouseLabelIndex = h;
    							break;
    						}
    					}
	    			}
	    		}
	    	}
		}
    }
    
    public int getMousePos() {
    	return this.mousePos;
    }
    
    public Pane getPaneSv() {
    	return this.paneSv;
    }
    
    public ScrollPane getScrSv() {
    	return this.scrSv;
    }
    
    public void setText(String in) {
        this.seq = in.replaceAll("\\r|\\n", "");
        this.seqArr = Parser.getAtInterval(this.seq, this.charStep);
   
    }
    
   
    
    class Selection {
    	Mark markStart = new Mark();
    	Mark markEnd = new Mark();
    	ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
    	Color color;
    	private Selection(int start, int end, Color color) {
    		this.markStart = new Mark(start, color);
    		this.markEnd = new Mark(end, color);
    		this.color = color;
    	}
		public ArrayList<Rectangle> getRectList() {
			return rectList;
		}
		public void setRectList(ArrayList<Rectangle> rectList) {
			this.rectList = rectList;
		}
    	
    }
    
    class Mark {
    	int position = 0;
    	Color color = Color.WHITE;
    	Rectangle rect;
    	public Mark() {
    		
    	}
    	
    	public Mark(int position, Color color) {
    		this.position = position;
    		this.color = color;
    	}
    }
    
    public void createMark(MouseEvent me) {
    	double meX = me.getX(), meY = me.getY();
    	int meNum = this.getMousePos();
    	int scenario = 0;
    	/*
    	 *   4 scenarios
    	 *   1. No Marks exist
    	 *   2. Only markStart exists
    	 *   3. Only markEnd exists
    	 *   4. Two marks exist
    	 */
    	if (this.mainMarkStart == null) {
    		scenario = 2;
    		if (this.mainMarkEnd == null) {
    			scenario = 1;
    		}
    	}
    	else if (this.mainMarkEnd == null) {
    		scenario = 3;
    	}
    	else {
    		scenario = 4;
    	}
    	
    	switch (scenario) {
    	case 1:
    		this.mainMarkStart = new Mark(meNum, this.markColor);
    		this.mainMarkStart.rect = new Rectangle();
    		this.mainMarkStart.rect.setLayoutX(meX);
    		this.mainMarkStart.rect.setLayoutY(this.lblSeq[this.mouseLabelIndex].getLayoutY());
    		this.mainMarkStart.rect.setWidth(4);
    		this.mainMarkStart.rect.setHeight(30);
    		this.paneSv.getChildren().add(this.mainMarkStart.rect);
    		this.lastMark = this.mainMarkStart;
    		this.mainMarkEnd = null;
    		break;
    	case 2:
    		
    		break;
    	case 3:
    		
    		break;
		case 4:
	
			break;
    	}
    }
   
    public void createSelection(int start, int end, Color color) {
    	Selection sel = new Selection(start, end, color);
    	this.setSelection(sel);
    	this.selectionList.add(sel);
    }
    
    public void drawLatestMark() {
    	this.setSelection(this.selectionList.get(this.selectionList.size() - 1));
    }
    
    public void setSelection(Selection sel) {
    	sel.markStart.position -= 1;
    	/*
    	 *  We have 3 scenarios
    	 *  1. If Mark starts and ends in same row.
    	 *  2. If Mark ends in row after the start row.
    	 *  3. If we have a start, an end, and middle rows
    	 */
    	int scenario = 0;
    	System.out.println("---------------------------------in setMark()");
    	int markLen = sel.markEnd.position - sel.markStart.position;
    	int numOfCharsInRow = (int)(this.numOfCols * charStep);
    	int cellMarkStarts = sel.markStart.position / charStep;
    	int cellMarkEnds = (markLen / charStep) + cellMarkStarts;
    	int charStartInCell = sel.markStart.position % charStep;
    	double rectStartX = 0.0, rectStartY = 0.0;
    	int charStartInRow = sel.markStart.position % numOfCharsInRow;
    	int numOfRemCharsInRow = numOfCharsInRow - charStartInRow;
    	double restOfCellWidth = this.calcWidthOfChars(charStep - charStartInCell);
    	int numOfCellsLeft = this.numOfCols - (cellMarkStarts + 1);
    	double lastCellX = lblSeq[this.numOfCols - 1].getLayoutX();
		double endX = lastCellX + this.cellWidth;
		int cellIn = cellMarkStarts % this.numOfCols;
		double firstX = lblSeq[cellIn].getLayoutX() + this.cellWidth;
		double remainingCellsWidth = endX - firstX;
		int numOfCharsInLastCell = sel.markEnd.position % charStep;
		int cellsInBetweenStartAndEnd = cellMarkEnds - cellMarkStarts;
		int markLastRow = cellMarkEnds / this.numOfCols;
		int markFirstRow = cellMarkStarts / this.numOfCols;
		System.out.println("remainingCellsWidth: " + remainingCellsWidth);
		System.out.println("numOfCellsLeft: " + numOfCellsLeft);
    	System.out.println("markLen: " + markLen);
    	System.out.println("cellMarkStarts: " + cellMarkStarts);
    	System.out.println("cellMarkEnds: " + cellMarkEnds);
    	System.out.println("charStartInCell: " + charStartInCell);
    	System.out.println("numOfCharsInRow: " + numOfCharsInRow);
    	System.out.println("charStartInRow: " + charStartInRow);
    	System.out.println("numOfRemCharsInRow: " + numOfRemCharsInRow);
		System.out.println("restOfCellWidth: " + restOfCellWidth + " cellWidth: " + this.cellWidth);
		System.out.println("numOfCharsInLastCell: " + numOfCharsInLastCell);
		System.out.println("cellsInBetweenStartAndEnd: " + cellsInBetweenStartAndEnd);
		System.out.println("markLastRow: " + markLastRow);
		System.out.println("markFirstRow: " + markFirstRow);
		// determine which scenario we have
    	if (markLen <= numOfRemCharsInRow) {
    		scenario = 1;
    	}
    	else if (markLen <= (numOfRemCharsInRow + numOfCharsInRow)) {
    		scenario = 2;
    	}
    	else if (markLen > (numOfRemCharsInRow + numOfCharsInRow)) {
    		scenario = 3;
    	}
    	
    	// startRect
    	rectStartX = lblSeq[cellMarkStarts].getLayoutX() + this.calcWidthOfChars(charStartInCell);
    	rectStartY = lblSeq[cellMarkStarts].getLayoutY();
    	Rectangle startRect = new Rectangle();
    	startRect.setLayoutX(rectStartX);
    	startRect.setLayoutY(rectStartY);
    	startRect.setFill(sel.color);
    	startRect.setHeight(this.cellHeight + 2);
    	startRect.opacityProperty().set(0.5);
    	startRect.toFront();
    	
    	
    	
    	switch (scenario) {
    	case 1:
    		System.out.println("SCENARIO 1");
    		// 3 cases
    		// 1. start and end in same cell
    		if (cellMarkEnds == cellMarkStarts) {
    			startRect.setWidth(this.calcWidthOfChars(markLen));
    		}
    		// 2. ends at end of row
    		else if (markLen == numOfRemCharsInRow) {
    			System.out.println("Subcase 2");
    			double w = remainingCellsWidth + restOfCellWidth;
    			System.out.println("w: " + w);
    			startRect.setWidth(w);
    		}
    		// 3. start and end not in same cell
    		else if (cellMarkEnds > cellMarkStarts) {
    			System.out.println("Subcase 3");
    			double e = lblSeq[cellMarkEnds].getLayoutX() + this.calcWidthOfChars(numOfCharsInLastCell);
    			startRect.setWidth(e - rectStartX);
    		}
    		
    		sel.rectList.add(startRect);
    		break;
    	case 2: {
    		System.out.println("SCENARIO 2");

    		startRect.setWidth(remainingCellsWidth + restOfCellWidth);
    		sel.rectList.add(startRect);
    		Rectangle endRect = new Rectangle();
    		endRect.setLayoutX(lblSeq[0].getLayoutX());
    		endRect.setLayoutY(lblSeq[markLastRow * this.numOfCols].getLayoutY());
    		endRect.setWidth((lblSeq[cellMarkEnds].getLayoutX() - lblSeq[0].getLayoutX()) + this.calcWidthOfChars(numOfCharsInLastCell));
    		endRect.setHeight(this.cellHeight + 2);
    		endRect.setFill(sel.color);
    		endRect.setOpacity(0.5);
    		sel.rectList.add(endRect);
    		break;
    	}
    	case 3: {
    		System.out.println("SCENARIO 3");

    		startRect.setWidth(remainingCellsWidth + restOfCellWidth);
    		sel.rectList.add(startRect);
    		int numOfMidRects = (markLen - numOfRemCharsInRow) / numOfCharsInRow;
        	System.out.println("numOfMidRects: " + numOfMidRects);

    		for (int i = 0; i < numOfMidRects; i++) {
	    		Rectangle midRect = new Rectangle();
	    		double startMidRectY = lblSeq[(markFirstRow + 1 + i) * this.numOfCols].getLayoutY();
	    		midRect.setLayoutX(lblSeq[0].getLayoutX());
	    		midRect.setLayoutY(startMidRectY);
	    		midRect.setHeight(this.cellHeight + 2);
	    		midRect.setWidth(lblSeq[this.numOfCols - 1].getLayoutX() + this.cellWidth - lblSeq[0].getLayoutX());
	    		midRect.setFill(sel.color);
	    		midRect.setOpacity(0.5);
	    		sel.rectList.add(midRect);
	    	}
    		// end rect
    		Rectangle endRect = new Rectangle();
    		endRect.setLayoutX(lblSeq[0].getLayoutX());
    		endRect.setLayoutY(lblSeq[markLastRow * this.numOfCols].getLayoutY());
    		endRect.setWidth((lblSeq[cellMarkEnds].getLayoutX() - lblSeq[0].getLayoutX()) + this.calcWidthOfChars(numOfCharsInLastCell));
    		endRect.setHeight(this.cellHeight + 2);
    		endRect.setFill(sel.color);
    		endRect.setOpacity(0.5);
    		sel.rectList.add(endRect);
    		break;
    	} // end case 3
    	} // end switch
    	System.out.println("----------------end setMark)");
    }
    
    public void drawAllSelections() {
    	for (Selection m : this.selectionList) {
    		for (Rectangle rect : m.getRectList()) {
    			this.paneSv.getChildren().add(rect);
    		}
    	}
    }
    
    public void refresh() {      
        paneSv.getChildren().clear();
        if ((this.seqArr != null) && (this.seqArr.length > 0)) {
            this.calcCellSize();
            System.out.println("in refresh calced cell width: " + this.cellWidth);
            this.calcWidthAndCols();
            this.numOfRows = this.seq.length() / (this.charStep * this.numOfCols);
            System.out.println("numOfRows: " + this.numOfRows);
            this.calcPaneSvHeight();
            this.lblSeq = new Label[seqArr.length];
            this.numOfRows = this.seq.length() / (this.charStep * this.numOfCols);
            this.calcNumOfRem();
            System.out.println("numOfRemRCols: " + this.numOfRemCols);
            System.out.println("numOfRemRows: " + this.numOfRemRows);
            this.paneSv.setBackground(new Background(new BackgroundFill(this.svBackColor, null, null)));
            
            // setting labels in cells
            int h = -1;
            for (int i = 0; i < (this.numOfRows); i++) {
                for (int j = 0; j < this.numOfCols; j++) {
                    h++;
                    this.lblSeq[h] = new Label(this.seqArr[h]);
                    this.lblSeq[h].setFont(this.font);
                    this.lblSeq[h].setLayoutX(this.leftPad + ((this.cellWidth + this.hGap) * (j)));
                    this.lblSeq[h].setLayoutY(this.topPad + ((this.cellHeight + this.vGap) * (i)));
                    
                    //this.lblSeq[h].setPrefWidth(this.cellWidth);
                    this.lblSeq[h].setBackground(new Background(new BackgroundFill(this.cellBackColor, null, null)));
                    this.lblSeq[h].setTextFill(this.textColor);
                    this.lblSeq[h].setAlignment(this.cellAlignment);
                    this.paneSv.getChildren().add(lblSeq[h]);
                }
            }
            if (this.numOfRemRows > 0) {
                for (int i = 0; i < this.numOfRemCols; i++) {
                    h++;
                    this.lblSeq[h] = new Label(this.seqArr[h]);
                    this.lblSeq[h].setLayoutX(this.leftPad + (i * (this.hGap + this.cellWidth)));
                    this.lblSeq[h].setLayoutY(this.topPad + (this.numOfRows * (this.cellHeight + this.vGap)));
                    this.lblSeq[h].setFont(font);
                  //  this.lblSeq[h].setPrefWidth(this.cellWidth);
                    this.lblSeq[h].setTextFill(this.textColor);
                    this.lblSeq[h].setBackground(new Background(new BackgroundFill(this.cellBackColor, null, null)));
                    this.lblSeq[h].setAlignment(this.cellAlignment);
                    this.paneSv.getChildren().add(this.lblSeq[h]);
                }
                
            }
            // set alignment of last label to left if its text length is less than charStep
            if (this.lblSeq[this.lblSeq.length - 1].getText().length() < this.charStep) {
                this.lblSeq[this.lblSeq.length - 1].setAlignment(Pos.BASELINE_LEFT);
            }
            System.out.println("this.paneSv.getPrefHeight()" + this.paneSv.getPrefHeight());
            System.out.println("this.paneSv.getHeight()" + this.paneSv.getHeight());
            System.out.println("this.paneSv.getPrefWidth()" + this.paneSv.getPrefWidth());
            System.out.println("this.paneSv.getWidth()" + this.paneSv.getWidth());
            
            // adjust height of paneSv accordingly for rem chars
            if (this.paneSv.getPrefHeight() < this.scrSv.getPrefHeight()) {
                this.paneSv.setPrefHeight(this.scrSv.getPrefHeight());
            }
            else if (this.numOfRemRows > 0) {
                this.paneSv.setPrefHeight(this.paneSv.getPrefHeight() + this.cellHeight + this.bottomPad);
            }
            
            this.drawAllSelections();
            scrSv.setContent(paneSv);
            
        }         
    }
    
    private double calcWidthOfChars(int numOfChars) {
    	double ret = 0.0;
    	Text text = new Text();
    	String str = "";
    	for (int i = 0; i < numOfChars; i++) {
    		str += "W";
    	}
    	text.setText(str);
        text.setFont(this.font);
        Bounds tb = text.getBoundsInLocal();
        // calc max stringWidth
		ret = tb.getWidth();
    	return ret;
    }
    
    private void calcNumOfRem() {
        int numOfCharsInRow = this.charStep * this.numOfCols;
        int numOfBlockChars = numOfCharsInRow * this.numOfRows;
        int remChars = this.seq.length() - numOfBlockChars;
        int iterTo = 0;
        if (remChars > 0) {
            this.numOfRemRows = 1;
            if ((remChars % charStep) == 0) {
                this.numOfRemCols = remChars / charStep;
            }
            else if ((remChars % charStep) > 0) {
                this.numOfRemCols = (remChars / charStep) + 1;
            }
        }
        else if (remChars == 0) {
            this.numOfRemCols = 0;
            this.numOfRemRows = 0;
        }
    }
    
    private void calcPaneSvHeight() {
       
        this.paneSvHeight = ((this.cellHeight + this.vGap) * this.numOfRows) + this.bottomPad + this.topPad;// + 8000;
        this.paneSv.setPrefHeight(this.paneSvHeight);
        System.out.println("in calcPaneSvHeight paneSvHeight: " + this.paneSvHeight);
        System.out.println("cell height: " + this.cellHeight);
        System.out.println("top pad: " + this.topPad);
        System.out.println("bottom pad: " + this.bottomPad);
    }
    
    // also calcs numOfCols
    private void calcWidthAndCols() {
        this.prefWidth = scrSv.getPrefWidth();
        this.prefHeight = scrSv.getPrefHeight();
        double totalHorizPad = this.leftPad + this.rightPad + SeqViewerControl.defaultRightPad;
        double totalWidth = 0.0;
        double cellAndHGap = this.cellWidth + this.hGap;
        int i = 0;
        while (true) {
            i++;
            totalWidth = (cellAndHGap * i) + totalHorizPad;
            if (totalWidth > this.prefWidth) {
                break;
            }
        }
        this.numOfCols = i - 1;
        this.width = totalHorizPad + (this.numOfCols * (this.hGap + this.cellWidth));
        scrSv.setPrefWidth(this.width);
        
        System.out.println("numOfCols: " + this.numOfCols);
        System.out.println("width: " + this.width);
        System.out.println("prefWidth: " + this.prefWidth);
        System.out.println("scrSv width: " + scrSv.getPrefWidth());
    }
    
    private void calcCellSize() {
        // approximate min width by making Text object with text of a block (one element)
        // of seqArr
        double stringWidth = 0.0;
      
        if ((seqArr != null) && (seqArr.length > 0)) {
        	Text text = new Text();
        	String str = "";
        	for (int i = 0; i < charStep; i++) {
        		str += "W";
        	}
        	text.setText(str);
            text.setFont(this.font);
            Bounds tb = text.getBoundsInLocal();
            // calc max stringWidth
    		this.cellWidth = tb.getWidth();
    		System.out.println("cellWidth: " + this.cellWidth);
            //this.cellWidth = (font.getSize() / 2) * this.charStep;
            this.cellHeight = font.getSize();
            /*double max = 0.0, temp = 0.0;
            label = new Label();
            label.setText(seqArr[0]);
            label.setFont(font);
            max = label.getPrefWidth();
            for (int i = 1; i < this.seqArr.length; i++) {
                temp = fontLoader.computeStringWidth(this.seqArr[i], this.font);
            	label = new Label();
            	label.setText(seqArr[i]);
            	label.setFont(font);
            	temp = label.getPrefWidth();
                if (temp > max) {
                    max = temp;
                }
            }
            stringWidth = max;
            System.out.println("in calcCellWidth calced width: " + stringWidth);
            // cellWidth = txt.width + 0.5 for every char in seq
            this.cellWidth = stringWidth;
            this.cellHeight = font.getSize();*/
        }
        else {
            System.out.println("null found in calcCellWidth");
        }
    }
    
    public void setTextColor(Color color) {
        this.textColor = color;
    }
    
    public Color getTextColor(Color color) {
        return this.textColor;
    }
    
    public void setCellBackgroundColor(Color color) {
        this.cellBackColor = color;
    }
    
    public Color getCellBackgroundColor() {
        return this.cellBackColor;
    }
    
    public void setSvBackgroundColor(Color color) {
        this.svBackColor = color;
    }
    
    public Color getSvBackgroundColor() {
        return this.svBackColor;
    }
    
    public void setCharStep(int charStep) {
        this.charStep = charStep;
        this.setText(this.seq);
    }
    
    public int getCharStep() {
        return this.charStep;
    }
    
    public void setFont(String fontName, double fontSize) {
        font = new Font(fontName, fontSize);
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public Font getFont() {
        return this.font;
    }

    public void setCellBackColor(Color color) {
        this.cellBackColor = color;
        this.cellBack = new Background(new BackgroundFill(this.cellBackColor, null, null));
    }
    
    public Color getCellBackColor() {
        return this.cellBackColor;
    }
    
    public double getVGap() {
        return this.vGap;
    }

    public void setVGap(double vGap) {
        this.vGap = vGap;
    }

    public double getHGap() {
        return this.hGap;
    }

    public void setHGap(double hGap) {
        this.hGap = hGap;
    }
    
    public double getCellWidth() {
        return this.cellWidth;
    }
    
    public void setLeftPad(double leftPad) {
        this.leftPad = leftPad;
    }
    
    public double getLeftPad() {
        return this.leftPad;
    }

    public void setRightPad(double rightPad) {
        this.rightPad = rightPad;
    }
    
    public double getRightPad() {
        return this.rightPad;
    }
    
    public void setTopPad(double topPad) {
        this.topPad = topPad;
    }
    
    public void setBottomPadding(double bottomPad) {
        this.bottomPad = bottomPad;
    }
    
    public void setPrefWidth(double prefWidth) {
        this.prefWidth = prefWidth;
    }

    public double getPrefWidth() {
        return this.prefWidth;
    }

    public double getPrefHeight() {
        return this.prefHeight;
    }

    public double getBottomPadding() {
        return this.bottomPad;
    }

    public double getWidth() {
        return this.width;
    }
    
    
    
    public Color getMarkColor() {
		return markColor;
	}


	public void setMarkColor(Color markColor) {
		this.markColor = markColor;
	}


	public void setCellAlignment(String align) {
        switch (align) {
            case "left":
                this.cellAlignment = Pos.CENTER_LEFT;
                break;
            case "right":
                this.cellAlignment = Pos.CENTER_RIGHT;
                break;
            case "center":
                this.cellAlignment = Pos.CENTER;
                break;
            default:
                
        }
    }
    
    public String getCellAlignment() {
        
        if (this.cellAlignment == Pos.CENTER_LEFT) {
            return "left";
        }
        else if (this.cellAlignment == Pos.CENTER_RIGHT) {
            return "right";
        }
        else if (this.cellAlignment == Pos.CENTER) {
            return "center";
        }
        else {
            return "null";
        }
    }
    
    public String getSeq() {
        return this.seq;
    }
}
