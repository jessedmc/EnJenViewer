  package selections;

import java.io.IOException;

import javafx.scene.paint.Color;
import sequences.ItemList;



public class SelectionList extends ItemList<Selection, Integer> {
    
	private static final long serialVersionUID = 1L;
	private static SelectionList instance;

    /*
     * Private constructor for singleton pattern
     */
    private SelectionList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static SelectionList instance() {
        if (instance == null) {
            instance = new SelectionList();
        }
        return instance;
    }

    /**
     * Checks whether a member with a given member id exists.
     * 
     * @param memberId
     *            the id of the member
     * @return true iff member exists
     * 
     */
    @Override
    public Selection search(Integer start, Integer end) {
        return super.search(start, end);
    }

    
    /**
     * Inserts a member into the collection
     * 
     * @param member
     *            the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertSelection(String name, int start, int end, Color color) {
    	Selection sel = new Selection(name, start, end, color);
    	return super.add(sel);
    }
    
    /**
     * Inserts a member into the collection
     * 
     * @param member
     *            the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertSelection(Selection selection) {
    	return super.add(selection);
    }
    
    
    

    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
    private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeObject(instance);
    }
}
