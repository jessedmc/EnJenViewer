package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Stage stage;
	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMLDocument.fxml"));
        //System.out.println(this.getClass().getResource("theCss.css").toString());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage() {
		return Main.stage;
	}
}
