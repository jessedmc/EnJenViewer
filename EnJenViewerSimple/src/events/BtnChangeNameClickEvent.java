package events;

public class BtnChangeNameClickEvent {
	private static BtnChangeNameClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnChangeNameClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnChangeNameClickEvent instance() {
        if (instance == null) {
            instance = new BtnChangeNameClickEvent();
        }
        return instance;
    }
}
