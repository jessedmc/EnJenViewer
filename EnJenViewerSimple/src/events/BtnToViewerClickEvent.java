package events;

public class BtnToViewerClickEvent {
	private static BtnToViewerClickEvent instance;
    /**
     * Make it a singleton
     */
    private BtnToViewerClickEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static BtnToViewerClickEvent instance() {
        if (instance == null) {
            instance = new BtnToViewerClickEvent();
        }
        return instance;
    }
}
