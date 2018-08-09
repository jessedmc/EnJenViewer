package handler;

import model.Model;
import parser.Parser;
import sequences.Sequence;
import sequences.SequenceFactory;

public class HandleCreateSequenceEvent {
	private static HandleCreateSequenceEvent instance;
    /**
     * Make it a singleton
     */
    private HandleCreateSequenceEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static HandleCreateSequenceEvent instance() {
        if (instance == null) {
            instance = new HandleCreateSequenceEvent();
        }
        return instance;
    }
    
    public void handle() {
    	this.createSequence();
    }
    
    public void createSequence() {
    	String tempStr = Model.instance().getTempSeqString();
    	int type = Parser.detectGeneticAAOrRYType(tempStr);
    	Sequence seq = SequenceFactory.instance().createSequence(tempStr, type);
    	System.out.println("in create name:  " + seq.getName() + " type: " + seq.getType());
    	Model.instance().setSeqInViewer(seq);
    	
    }
}
