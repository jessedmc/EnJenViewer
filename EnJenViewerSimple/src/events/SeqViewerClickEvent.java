package events;

public class SeqViewerClickEvent {
	private static SeqViewerClickEvent instance;
    /**
     * Make it a singleton
     */
    private SeqViewerClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static SeqViewerClickEvent instance() {
        if (instance == null) {
            instance = new SeqViewerClickEvent();
        }
        return instance;
    }
}
