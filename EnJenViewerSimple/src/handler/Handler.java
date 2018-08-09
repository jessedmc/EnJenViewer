package handler;

import events.AddSeqInViewerToCompChartEvent;
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
import events.CalculateCompositionEvent;
import events.CalculateGeneralInfoEvent;
import events.CalculateSequenceLengthEvent;
import events.CreateSequenceEvent;
import events.ListSelectionClickEvent;
import events.ListSequenceClickEvent;
import events.SeqViewerClickEvent;

public class Handler {
    private static Handler instance;

    /**
     * Make it a singleton
     */
    private Handler() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static Handler instance() {
        if (instance == null) {
            instance = new Handler();
        }
        return instance;
    }
   
    public void handleEvent(BtnSaveClickEvent event) {
    	HandleBtnSaveClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnLoadClickEvent event) {
    	HandleBtnLoadClickEvent.instance().handle();
    }
    
    public void handleEvent(SeqViewerClickEvent event) {
    	HandleSeqViewerClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnClearSelectionsClickEvent event) {
    	HandleBtnClearSelectionsClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnLoadTextClickEvent event) {
    	HandleBtnLoadTextClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnToSequenceListClickEvent event) {
    	HandleBtnToSequenceListClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnSelectionToSequenceListClickEvent event) {
    	HandleBtnSelectionToSequenceListClickEvent.instance().handle();
    }
    
    public void handleEvent(ListSequenceClickEvent event) {
    	HandleListSequenceClickEvent.instance().handle();
    }
    
    public void handleEvent(ListSelectionClickEvent event) {
    	HandleListSelectionClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnChangeNameClickEvent event) {
    	HandleBtnChangeNameClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnChangeTypeClickEvent event) {
    	HandleBtnChangeTypeClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnToViewerClickEvent event) {
    	HandleBtnToViewerClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnSendToCompareClickEvent event) {
    	HandleBtnSendToCompareClickEvent.instance().handle();
    }

    public void handleEvent(BtnClearCompareClickEvent event) {
    	HandleBtnClearCompareClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnTestClickEvent event) {
    	HandleBtnTestClickEvent.instance().handle();
    }
    
    public void handleEvent(CalculateCompositionEvent event) {
    	HandleCalculateCompositionEvent.instance().handle();
    }
    
    public void handleEvent(CalculateGeneralInfoEvent event) {
    	HandleCalculateGeneralInfoEvent.instance().handle();
    }
    
    public void handleEvent(CalculateSequenceLengthEvent event) {
    	HandleCalculateSequenceLengthEvent.instance().handle();
    }
    
    public void handleEvent(BtnCompareClickEvent event) {
    	HandleBtnCompareClickEvent.instance().handle();
    }
    
    public void handleEvent(CreateSequenceEvent event) {
    	HandleCreateSequenceEvent.instance().handle();
    }
    
    public void handleEvent(BtnChangeColorClickEvent event) {
    	HandleBtnChangeColorClickEvent.instance().handle();
    }
    
    public void handleEvent(BtnCreateSelectionClickEvent event) {
    	HandleBtnCreateSelectionClickEvent.instance().handle();
    }
    
    public void handleEvent(AddSeqInViewerToCompChartEvent event) {
    	HandleAddSeqInViewerToCompChartEvent.instance().handle();
    }
}
