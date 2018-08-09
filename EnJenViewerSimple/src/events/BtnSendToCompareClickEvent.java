package events;

public class BtnSendToCompareClickEvent {
	private static BtnSendToCompareClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnSendToCompareClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnSendToCompareClickEvent instance() {
        if (instance == null) {
            instance = new BtnSendToCompareClickEvent();
        }
        return instance;
    }
}
