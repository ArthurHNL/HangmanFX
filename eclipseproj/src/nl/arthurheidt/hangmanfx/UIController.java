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
 * @author Arthur Heidt
 *
 */
public class UIController implements Initializable {
    
    public final static String WORD = "vrachtwagenventiel";
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
	
	//Generate the guess buttons
	for (char c = 'A'; c <= 'Z'; c++) {
	    String content = "" + c;
	    Button b = new Button(content);
	    b.setOnAction( e -> handleAlphabetButton(e));
	    alphabet.getChildren().add(b);
	}
	
	//Generate the word fields
	guessWord = WORD.toUpperCase();
	guessWordArr = guessWord.toCharArray();
	for (int i = 0; i < guessWordArr.length; i++) {
	    Label l = new Label(("" + guessWordArr[i]));
	    l.setVisible(false);
	    theWord.getChildren().add(l);
	}
	
	//Generate list
	manNodes = new ArrayList<Node>(manNodes.getChildren);
	
	System.out.println("Game started");
	
    }
    
    private void handleAlphabetButton(ActionEvent e) {
	Button source = (Button) e.getSource();
	source.setDisable(true);
	if (!checkLetter(source.getText())) {
	    drawNextPart();
	}
    }
    
    private boolean checkLetter(String letter) {
	boolean succes = false;
	for (Node n : theWord.getChildren()) {
	    if (n instanceof Label) {
		Label l = (Label) n;
		if (l.getText().equals(letter)) {
		    l.setVisible(true);
		    succes = true;
		}
	    }
	}
	return succes;
    }
    
    private void drawNextPart() {
	Node n = hangNodes.get
	
	currentTries++;
	
    }

}
