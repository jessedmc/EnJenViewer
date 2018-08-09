package events;

public class ListSelectionClickEvent {
	private static ListSelectionClickEvent instance;
    /**
     * Make it a singleton
     */
    private ListSelectionClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static ListSelectionClickEvent instance() {
        if (instance == null) {
            instance = new ListSelectionClickEvent();
        }
        return instance;
    }
}
