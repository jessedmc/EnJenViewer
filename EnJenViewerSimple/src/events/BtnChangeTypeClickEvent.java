package events;

public class BtnChangeTypeClickEvent {
	private static BtnChangeTypeClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnChangeTypeClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnChangeTypeClickEvent instance() {
        if (instance == null) {
            instance = new BtnChangeTypeClickEvent();
        }
        return instance;
    }
}
