package nl.arthurheidt.hangmanfx;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * 
 * @author Arthur Heidt
 * Main class for HangmanFX
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene s = new Scene(root);
			primaryStage.setScene(s);
			primaryStage.setTitle("HangmanFX");
			primaryStage.setWidth(1000);
			primaryStage.setHeight(500);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
