package events;

public class CalculateCompositionEvent {
	private static CalculateCompositionEvent instance;
    /**
     * Make it a singleton
     */
    private CalculateCompositionEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static CalculateCompositionEvent instance() {
        if (instance == null) {
            instance = new CalculateCompositionEvent();
        }
        return instance;
    }
}
