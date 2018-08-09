package events;

public class BtnSaveClickEvent {
	private static BtnSaveClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnSaveClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnSaveClickEvent instance() {
        if (instance == null) {
            instance = new BtnSaveClickEvent();
        }
        return instance;
    }
}
