package sequences;

import javafx.scene.paint.Color;

public class SequenceFactory {
    private static SequenceFactory instance;

    
    /**
     * Make it a singleton
     */
    private SequenceFactory() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static SequenceFactory instance() {
        if (instance == null) {
            instance = new SequenceFactory();
        }
        return instance;
    }
    
    public Sequence createSequence(String sequence) {
    	return this.createSequence(sequence, Sequence.GENERAL);
    }
    
    public Sequence createSequence(String sequence, int type) {
    	switch (type) {
    	case Sequence.GENERAL:
    		return new Sequence(sequence);
    	case Sequence.GENETIC:
    		return new Genetic(sequence);
    	case Sequence.RY:
    		return new RY(sequence);
    	case Sequence.AMINO_ACID:
    		return new AminoAcid(sequence);
    	}
    	return new Sequence();
    }
    
    public Sequence createSequence(String sequence, String name, Color color, int type) {
    	switch (type) {
    	case Sequence.GENERAL:
    		return new Sequence(sequence, name, color);
    	case Sequence.GENETIC:
    		return new Genetic(sequence, name, color);
    	case Sequence.RY:
    		return new RY(sequence, name, color);
    	case Sequence.AMINO_ACID:
    		return new AminoAcid(sequence, name, color);
    	}
    	return new Sequence();
    }
    
    public Sequence createSequenceBlank() {
    	return new Sequence();
    }
}
