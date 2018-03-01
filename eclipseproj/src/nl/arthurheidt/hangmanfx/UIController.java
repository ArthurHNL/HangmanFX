package nl.arthurheidt.hangmanfx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

/**
 * Controller class for UI.fxml
 * 
 * @author Arthur Heidt
 *
 */
public class UIController implements Initializable {

	private String word;
	private String guessWord;
	private char[] guessWordArr;
	private int maxTries = 11;
	private int currentTries = 0;

	@FXML
	private SplitPane rootPane;
	@FXML
	private HBox alphabet, theWord;
	@FXML
	private Arc mouth;
	@FXML
	private Shape hang1, hang2, hang3, hang4, hang5, hang6, hang7, hang8, hang9, hang10, hang11;
	@FXML
	private AnchorPane face, man;
	private ArrayList<Node> manNodes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Get the word
		word = Popup.getString("Welcome", "Please enter a word for your opponent...", true).toUpperCase();
		
		// Generate the guess buttons
		for (char c = 'A'; c <= 'Z'; c++) {
			String content = "" + c;
			Button b = new Button(content);
			b.setOnAction(e -> handleAlphabetButton(e));
			alphabet.getChildren().add(b);
		}

		// Generate the word fields
		guessWord = word.toUpperCase();
		guessWordArr = guessWord.toCharArray();
		for (int i = 0; i < guessWordArr.length; i++) {
			Label l = new Label(("" + guessWordArr[i]));
			l.setVisible(false);
			theWord.getChildren().add(l);
		}

		// Generate list
		manNodes = new ArrayList<Node>(man.getChildren());

		System.out.println("Game started");

	}

	private void handleAlphabetButton(ActionEvent e) {
		Button source = (Button) e.getSource();
		source.setDisable(true);
		boolean wasInWord = parseLetter(source.getText());
		System.out.println(wasInWord);
		if (!wasInWord) {
			drawNextPart();
		}
		checkWinLose();
	}

	private void checkWinLose() {
		if (currentTries >= maxTries) {
			mouth.setStartAngle(0);
			mouth.setLayoutY(25);
			
			face.setVisible(true);
			for (Node n : alphabet.getChildren()) {
				n.setDisable(true);
			}
			Popup.showAndQuit("TOO BAD", "Seems like you've lost...", "Exit", 0);
		} else {
			boolean win = true;
			for (Node n : theWord.getChildren()) {
				if (!n.isVisible()) {
					win = false;
				}
			}
			if (win) {
				for (int i = 5; i < manNodes.size(); i++) {
					manNodes.get(i).setVisible(true);
				}
				for (int i = 0; i < 5; i++) {
					manNodes.get(i).setVisible(false);
				}
				face.setVisible(true);
				for (Node n : alphabet.getChildren()) {
					n.setDisable(true);
				}
				Popup.showAndQuit("SUCCES", "You have won the game.", "Exit", 0);
			}
		}
		
	}

	private boolean parseLetter(String letter) {
		boolean retValue = false;
		for (Node n : theWord.getChildren()) {
			if (n instanceof Label) {
				Label l = (Label) n;
				if (l.getText().equals(letter)) {
					retValue = true;
					l.setVisible(true);
				}
			}
		}
		return retValue;
	}

	private void drawNextPart() {
		if (currentTries < maxTries) {
			manNodes.get(currentTries).setVisible(true);
			currentTries++;
		}
	}

}
