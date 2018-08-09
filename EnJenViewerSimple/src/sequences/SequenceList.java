package sequences;

import selections.Selection;

public class SequenceList extends ItemList<Sequence, String> {
    
	private static final long serialVersionUID = 1L;
	private static SequenceList instance;

    /*
     * Private constructor for singleton pattern
     */
    private SequenceList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static SequenceList instance() {
        if (instance == null) {
            instance = new SequenceList();
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
    public Sequence search(String name, String type) {
        return super.search(name, type);
    }

    
    /**
     * Inserts a member into the collection
     * 
     * @param member
     *            the member to be inserted
     * @return true iff the member could be inserted. Currently always true
     */
    public boolean insertSequence(Sequence sequence) {
        return super.add(sequence);
    }
    


    /*
     * Supports serialization
     * 
     * @param output the stream to be written to
     */
   /* private void writeObject(java.io.ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeObject(sequenceList);
    }*/
}
