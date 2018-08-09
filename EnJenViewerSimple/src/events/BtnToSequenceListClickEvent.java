package events;

public class BtnToSequenceListClickEvent {
	private static BtnToSequenceListClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnToSequenceListClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnToSequenceListClickEvent instance() {
        if (instance == null) {
            instance = new BtnToSequenceListClickEvent();
        }
        return instance;
    }
}
