package events;

public class BtnTestClickEvent {
	private static BtnTestClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnTestClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnTestClickEvent instance() {
        if (instance == null) {
            instance = new BtnTestClickEvent();
        }
        return instance;
    }
}
