package events;

public class BtnLoadClickEvent {
	private static BtnLoadClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnLoadClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnLoadClickEvent instance() {
        if (instance == null) {
            instance = new BtnLoadClickEvent();
        }
        return instance;
    }
}
