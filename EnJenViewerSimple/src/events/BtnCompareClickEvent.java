package events;

public class BtnCompareClickEvent {
	private static BtnCompareClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnCompareClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnCompareClickEvent instance() {
        if (instance == null) {
            instance = new BtnCompareClickEvent();
        }
        return instance;
    }
}
