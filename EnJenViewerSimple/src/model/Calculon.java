package model;

public class Calculon extends Model {
	private static Calculon instance;
    /**
     * Return the instance
     * 
     * @return the object
     */
    public static Calculon instance() {
        if (instance == null) {
            instance = new Calculon();
        }
        return instance;
    }
    
    
}
