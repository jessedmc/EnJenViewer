package events;

public class BtnCreateSelectionClickEvent {
	private static BtnCreateSelectionClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnCreateSelectionClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnCreateSelectionClickEvent instance() {
        if (instance == null) {
            instance = new BtnCreateSelectionClickEvent();
        }
        return instance;
    }
}
