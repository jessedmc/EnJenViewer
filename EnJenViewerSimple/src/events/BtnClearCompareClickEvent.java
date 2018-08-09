package events;

public class BtnClearCompareClickEvent {
	private static BtnClearCompareClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnClearCompareClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnClearCompareClickEvent instance() {
        if (instance == null) {
            instance = new BtnClearCompareClickEvent();
        }
        return instance;
    }
}
