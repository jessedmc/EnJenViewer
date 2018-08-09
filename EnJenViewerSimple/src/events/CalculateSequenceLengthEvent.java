package events;

public class CalculateSequenceLengthEvent {
	private static CalculateSequenceLengthEvent instance;
    /**
     * Make it a singleton
     */
    private CalculateSequenceLengthEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static CalculateSequenceLengthEvent instance() {
        if (instance == null) {
            instance = new CalculateSequenceLengthEvent();
        }
        return instance;
    }
}
