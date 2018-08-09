package events;

public class CalculateGeneralInfoEvent {
	private static CalculateGeneralInfoEvent instance;
    /**
     * Make it a singleton
     */
    private CalculateGeneralInfoEvent() {
        
    }

    /**
     * Return the instance
     * 
     * @return the object
     */
    public static CalculateGeneralInfoEvent instance() {
        if (instance == null) {
            instance = new CalculateGeneralInfoEvent();
        }
        return instance;
    }
}
