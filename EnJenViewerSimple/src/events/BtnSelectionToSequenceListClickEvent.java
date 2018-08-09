package events;

public class BtnSelectionToSequenceListClickEvent {
	private static BtnSelectionToSequenceListClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnSelectionToSequenceListClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnSelectionToSequenceListClickEvent instance() {
        if (instance == null) {
            instance = new BtnSelectionToSequenceListClickEvent();
        }
        return instance;
    }
}
