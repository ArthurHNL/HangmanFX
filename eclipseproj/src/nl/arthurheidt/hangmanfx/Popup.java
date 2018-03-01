package nl.arthurheidt.hangmanfx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class Popup {
	public static void showAlert(String title, String text, String buttonText) {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle(title);
		popup.setAlwaysOnTop(true);

		Label l = new Label(text);
		Button b = new Button(buttonText);
		b.setOnAction(e -> {
			popup.close();
			return;
		});

		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(l, b);

		Scene s = new Scene(vb, 300, 150);
		popup.setScene(s);
		popup.showAndWait();

	}

	public static String getString(String title, String text, boolean validate) {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle(title);
		popup.setAlwaysOnTop(true);

		Label l = new Label(text);
		TextField t = new TextField();
		Button b = new Button("OK");
		Label le = new Label("Invalid input!");
		le.setVisible(false);
		
		t.setOnAction(e -> {
			validateAndExit(popup, t, validate, le);});
		b.setOnAction(e -> {
			validateAndExit(popup, t, validate, le);
		});
		popup.setOnCloseRequest(e -> {System.exit(0);});

		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(l, t, b, le);

		Scene s = new Scene(vb, 300, 150);
		popup.setScene(s);
		popup.showAndWait();
		return t.getText();

	}

	private static void validateAndExit(Stage popup, TextField t, boolean validate, Label le) {
		if (!validate) {
			popup.close();
		} else {
			if (t.getText().isEmpty() ||!t.getText().matches("^[a-zA-Z]*$")) {
				le.setVisible(true);
			} else {
				popup.close();
			}
		}
	}

	public static void showAndQuit(String title, String text, String buttonText, int exitCode) {
		showAlert(title, text, buttonText);
		System.exit(exitCode);
	}

}
