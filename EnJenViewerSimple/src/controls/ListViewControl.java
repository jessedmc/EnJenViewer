package controls;

import java.util.List;

import javafx.scene.control.ListView;
import sequences.Sequence;

public class ListViewControl {
	private static ListViewControl instance;
	private ListView<Sequence> listView;
	
    public ListViewControl(ListView listview) {
    	this.listView = listView;
    }
    
    public static ListViewControl instance() {
        return instance;
    }
    
    public static ListViewControl createListViewControl(ListView listView) {
    	if (instance == null) {
            instance = new ListViewControl(listView);
        }
        return instance;
    }
    
    public void addItems(List<Sequence> items) {
    	//listView.getSelectionModel().
    }
}
