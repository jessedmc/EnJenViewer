package events;

public class BtnChangeColorClickEvent {
	private static BtnChangeColorClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnChangeColorClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnChangeColorClickEvent instance() {
        if (instance == null) {
            instance = new BtnChangeColorClickEvent();
        }
        return instance;
    }

}
