package events;

public class ListSequenceClickEvent {
	private static ListSequenceClickEvent instance;
    /**
     * Make it a singleton
     */
    private ListSequenceClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static ListSequenceClickEvent instance() {
        if (instance == null) {
            instance = new ListSequenceClickEvent();
        }
        return instance;
    }
}
