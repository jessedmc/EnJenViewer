package events;

public class BtnClearSelectionsClickEvent {
	private static BtnClearSelectionsClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnClearSelectionsClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnClearSelectionsClickEvent instance() {
        if (instance == null) {
            instance = new BtnClearSelectionsClickEvent();
        }
        return instance;
    }
}
