package com.mycompany.parkeerapplictie.view;

import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.controller.loginController;
import com.mycompany.parkeerapplictie.controller.viewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javax.swing.JFrame;

public class loginView extends JFrame {

    private VBox vBox;
    private Label labelheader1, labelheader2, labelEmail, labelWachtwoord, labelStatus;
    private PasswordField passwordField;
    private Line line;
    private TextField tfEmail;
    private ButtonBar buttonBar;
    private Button btnLogin, btnRegistreren;
    private AnchorPane anchorPane;
    private ImageView imageView;

    public loginView(BorderPane aanmelden) {

        vBox = new VBox();
        labelheader1 = new Label();
        labelheader2 = new Label();
        labelStatus = new Label();
        line = new Line();
        labelEmail = new Label();
        tfEmail = new TextField();
        labelWachtwoord = new Label();
        passwordField = new PasswordField();
        buttonBar = new ButtonBar();

        btnRegistreren = new Button("Registreren");
        anchorPane = new AnchorPane();
        imageView = new ImageView();
        btnLogin = new Button("Login");
        aanmelden.setAlignment(vBox, javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(491.0);
        vBox.setPrefWidth(268.0);

        labelheader1.setPrefHeight(89.0);
        labelheader1.setPrefWidth(531.0);
        labelheader1.setText("Parkeerapplicatie - Nijmegen");
        labelheader1.setTextFill(javafx.scene.paint.Color.valueOf("#0078d7"));
        labelheader1.setFont(new Font(15.0));

        labelheader2.setPrefHeight(27.0);
        labelheader2.setPrefWidth(274.0);
        labelheader2.setText("Login voor de parkeerapplicatie ");
        labelheader2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        labelheader2.setFont(new Font(14.0));

        line.setEndX(100.0);
        line.setStartX(-100.0);

        labelEmail.setText("Email");

        tfEmail.setPrefHeight(25.0);
        tfEmail.setPrefWidth(154.0);
        tfEmail.setPromptText("Email");
        // for effectiveness in development (VERWIJDEREN)
        tfEmail.setText("Dev@avans.nl");
        //
        VBox.setMargin(tfEmail, new Insets(0.0, 20.0, 0.0, 0.0));

        labelWachtwoord.setText("Wachtwoord");

        passwordField.setPrefHeight(25.0);
        passwordField.setPrefWidth(137.0);
        passwordField.setPromptText("********");
        // for effectiveness in development (VERWIJDEREN)
        passwordField.setText("Devuser");
        //
        VBox.setMargin(passwordField, new Insets(0.0, 20.0, 0.0, 0.0));

        buttonBar.setPrefHeight(40.0);
        buttonBar.setPrefWidth(200.0);

        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(25.0);
        btnLogin.setPrefWidth(253.0);

        btnRegistreren.setMnemonicParsing(false);
        btnRegistreren.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btnRegistreren.setOpaqueInsets(new Insets(0.0));
        VBox.setMargin(buttonBar, new Insets(0.0, 20.0, 0.0, 0.0));
        aanmelden.setRight(vBox);

        aanmelden.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        imageView.setFitHeight(469.0);
        imageView.setFitWidth(505.0);
        imageView.setLayoutY(-10.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        InputStream stream = null;
        try {
            FileInputStream input = new FileInputStream("src/main/resources/img/brugnijmegen.jpg");
            Image image = new Image(input);
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(500);
        } catch (FileNotFoundException fnf) {
            System.out.println("Afbeelding niet gevonden... : " + fnf.getMessage());
        }

        aanmelden.setLeft(anchorPane);

        buttonBar.getButtons().addAll(btnLogin, btnRegistreren);
            aanmelden.setStyle("-fx-background-color : #2f588a;");
        anchorPane.getChildren().add(imageView);
        vBox.getChildren().addAll(labelheader1, labelheader2, line, labelEmail, tfEmail, labelWachtwoord, passwordField, labelStatus, buttonBar);

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginController loginController = new loginController(labelStatus, tfEmail.getText(), passwordField.getText());
            }
        });

        btnRegistreren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //registerController.switchtoregisterScreen(primaryStage);
                viewController.registerScreen(pStage);
            }
        });

        vBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    loginController loginController = new loginController(labelStatus, tfEmail.getText(), passwordField.getText());
                }
            }
        });

    }
}
