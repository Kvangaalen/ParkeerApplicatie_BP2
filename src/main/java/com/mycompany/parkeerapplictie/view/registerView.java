package com.mycompany.parkeerapplictie.view;

import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.controller.registerController;
import com.mycompany.parkeerapplictie.controller.viewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javax.swing.JFrame;

/**
 *
 * @author Koen
 */
public class registerView extends JFrame {

    private VBox vBox;
    private Line line;
    private Label labelWachtwoord, labelheader1, labelheader2, labelEmail, labelNaam, labelStatus;
    private TextField tfEmail, tfNaam;
    private PasswordField passwordField1, passwordField2;
    private ButtonBar buttonBar;
    private Button btnLogin, btnRegistreren;
    private AnchorPane anchorPane;
    private ImageView imageView;
   

    public registerView(BorderPane aanmelden) {

        vBox = new VBox();
        labelheader1 = new Label();
        labelheader2 = new Label();
        line = new Line();
        labelNaam = new Label("Naam *");
        labelEmail = new Label("Email *");
        labelWachtwoord = new Label("Wachtwoord *");
        tfEmail = new TextField();
        tfNaam = new TextField();
        passwordField1 = new PasswordField();
        passwordField2 = new PasswordField();
        buttonBar = new ButtonBar();
        btnRegistreren = new Button("Registreren");
        anchorPane = new AnchorPane();
        imageView = new ImageView();
        btnLogin = new Button("Terug na login");
        labelStatus = new Label();
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
        labelheader2.setText("Nieuwe bij de parkeerapplicatie? registreer direct!");
        labelheader2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        labelheader2.setFont(new Font(14.0));

        line.setEndX(100.0);
        line.setStartX(-100.0);

        tfNaam.setPrefHeight(25.0);
        tfNaam.setPrefWidth(154.0);
        tfNaam.setPromptText("Koen");
        VBox.setMargin(tfNaam, new Insets(0.0, 20.0, 0.0, 0.0));

        tfEmail.setPrefHeight(25.0);
        tfEmail.setPrefWidth(154.0);
        tfEmail.setPromptText("Koen@avans.nl");
        VBox.setMargin(tfEmail, new Insets(0.0, 20.0, 0.0, 0.0));

        passwordField1.setPrefHeight(25.0);
        passwordField1.setPrefWidth(137.0);
        passwordField1.setPromptText("********");
        VBox.setMargin(passwordField1, new Insets(0.0, 20.0, 0.0, 0.0));

        passwordField2.setPrefHeight(25.0);
        passwordField2.setPrefWidth(137.0);
        passwordField2.setPromptText("********");
        VBox.setMargin(passwordField2, new Insets(0.0, 20.0, 0.0, 0.0));
        buttonBar.setPrefHeight(40.0);
        buttonBar.setPrefWidth(200.0);

        btnRegistreren.setMnemonicParsing(false);
        btnRegistreren.setPrefHeight(25.0);
        btnRegistreren.setPrefWidth(253.0);

        btnLogin.setMnemonicParsing(false);
        btnLogin.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btnLogin.setOpaqueInsets(new Insets(0.0));
        VBox.setMargin(buttonBar, new Insets(0.0, 20.0, 0.0, 0.0));

        aanmelden.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);

        imageView.setFitHeight(469.0);
        imageView.setFitWidth(505.0);
        imageView.setLayoutY(-10.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        try {
            FileInputStream input = new FileInputStream("src/main/resources/img/brugnijmegen.jpg");
            Image image = new Image(input);
            imageView = new ImageView(image);

            imageView.setPreserveRatio(true);
            imageView.setFitHeight(500);
        } catch (FileNotFoundException fnf) {
            System.out.println("Afbeelding niet gevonden... : " + fnf.getMessage());
        }

        // toevoegen aan scherm
        aanmelden.setRight(vBox);
        aanmelden.setLeft(anchorPane);
        buttonBar.getButtons().addAll(btnRegistreren, btnLogin);
        anchorPane.getChildren().add(imageView);
        vBox.getChildren().addAll(labelheader1, labelheader2, line, labelNaam, tfNaam, labelEmail, tfEmail, labelWachtwoord, passwordField1, passwordField2, labelStatus, buttonBar);

        btnRegistreren.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              registerController registerController = new registerController(labelStatus, tfNaam.getText(), tfEmail.getText(), passwordField1.getText(), passwordField2.getText());
            }
        });

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                viewController.loginScreen(pStage);
            }
        });

    }


}
