package events;



public class BtnLoadTextClickEvent {
	private static BtnLoadTextClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnLoadTextClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnLoadTextClickEvent instance() {
        if (instance == null) {
            instance = new BtnLoadTextClickEvent();
        }
        return instance;
    }
}
