package sequences;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Maintains a list of items with type T and key of type K. Subclassed by
 * Catalog and MemberList
 *
 * @param <T>
 *            type of item
 * @param <K>
 *            key of item
 */
public class ItemList<T extends Matchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list = new LinkedList<T>();

	/**
	 * Checks whether an item with a given id exists.
	 * 
	 * @param key
	 *            the id of the item
	 * @return the item iff the item exists
	 * 
	 */
	public T search(K name, K type) {
		for (T item : list) {
			if (item.matches(name, type)) {
				return item;
			}
		}
		return null;
	}
	
	public T get(int index) {
		return list.get(index);
	}

	/**
	 * Adds an item to the list.
	 * 
	 * @param item
	 *            the item to be added
	 * @return true iff the item could be added
	 */
	public boolean add(T item) {
		return list.add(item);
	}

	/**
	 * Removes the item from the list
	 * 
	 * @param item
	 *            the item to be removed
	 * @return true iff the item could be removed
	 */
	public boolean remove(T item) {
		return list.remove(item);
	}
	
	public void clear() {
		this.list.clear();
	}

	/**
	 * Returns an iterator for the collection
	 * 
	 * @return iterator for the collection
	 */
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	public int size() {
		return list.size();
	}
	
	public ObservableList<T> toObservableList() {
		return FXCollections.observableList(list);
	}
	
	public void printList() {
		for (T item : list) {
			System.out.println(item.toString() + System.getProperty("line.separator"));
		}
	}
}
