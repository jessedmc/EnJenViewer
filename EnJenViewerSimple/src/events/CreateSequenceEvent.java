package events;

public class CreateSequenceEvent {
	private static CreateSequenceEvent instance;
    /**
     * Make it a singleton
     */
    private CreateSequenceEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static CreateSequenceEvent instance() {
        if (instance == null) {
            instance = new CreateSequenceEvent();
        }
        return instance;
    }
}
