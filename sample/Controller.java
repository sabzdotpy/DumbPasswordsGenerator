package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javax.print.URIException;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private StringBuilder builder = new StringBuilder();
    private Clipboard clipboard = Clipboard.getSystemClipboard();
    private ClipboardContent content = new ClipboardContent();
    private final String displayIDLE = "-fx-background-color: #2e2e2e; -fx-background-radius: 15px;";
    private final String displayHOVER = "-fx-background-color: #3a3a3a; -fx-background-radius: 15px;";
    private final String btnIDLE = "-fx-background-radius: 10px; -fx-background-color: #454545; -fx-border-color: #a2a2a2; -fx-border-radius: 10px";
    private final String btnHOVER = "-fx-background-radius: 10px; -fx-background-color: #505050; -fx-border-color: #b2b2b2; -fx-border-radius: 10px";

    private String generatePassword() {

        builder.setLength(0);
        Integer length = sp.getValue();
        String allLetters = "abcdefghijklmnopqrstuvwxyz";
        String upperLetters = allLetters.toUpperCase();
        String nums = "1234567890";
        String syms = "@#.,-+=%*";
        Random random = new Random();

        if (upper_cbox.isSelected()) {
            allLetters += upperLetters;
        }
        if (nums_cbox.isSelected()) {
            allLetters += nums;
        }
        if (syms_cbox.isSelected()) {
            allLetters += syms;
        }

        for (int i = 0; i < length; i++) {
            builder.append(allLetters.charAt(random.nextInt(allLetters.length())));
        }

        return builder.toString();

    }


    @FXML
    private Label password_display;

    @FXML
    private Button gen_btn;

    @FXML
    private CheckBox upper_cbox;

    @FXML
    private CheckBox nums_cbox;

    @FXML
    private CheckBox syms_cbox;

    @FXML
    private Spinner<Integer> sp;

    @FXML Hyperlink url;

    @FXML
    private void btnClicked(ActionEvent event) {
        String password = generatePassword();
        password_display.setText(password);
    }

    @FXML
    private void copyPassword() {
        if (!(password_display.getText().equals("Password will appear here"))) {
            password_display.setStyle("-fx-background-color: #544461; -fx-background-radius: 15px");
            content.putString(password_display.getText());
            clipboard.setContent(content);
        }
    }

    @FXML void openURL(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.github.com/sabzdotpy"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(6, 25);
        valueFactory.setValue(8);
        sp.setValueFactory(valueFactory);

        password_display.setStyle(displayIDLE);
        password_display.setOnMouseEntered(e -> password_display.setStyle(displayHOVER));
        password_display.setOnMouseExited(e -> password_display.setStyle(displayIDLE));
        gen_btn.setStyle(btnIDLE);
        gen_btn.setOnMouseEntered(e -> gen_btn.setStyle(btnHOVER));
        gen_btn.setOnMouseExited(e -> gen_btn.setStyle(btnIDLE));
    }
}
