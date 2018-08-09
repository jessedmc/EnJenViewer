package events;

public class AddSeqInViewerToCompChartEvent {
	private static AddSeqInViewerToCompChartEvent instance;
    /**
     * Make it a singleton
     */
    private AddSeqInViewerToCompChartEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static AddSeqInViewerToCompChartEvent instance() {
        if (instance == null) {
            instance = new AddSeqInViewerToCompChartEvent();
        }
        return instance;
    }

}
