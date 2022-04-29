package com.mycompany.parkeerapplictie.view;

import com.mycompany.parkeerapplictie.controller.parkeerderController;
import com.mycompany.parkeerapplictie.model.parkeerders;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class parkeerderView {

    protected final Rectangle rectangle;
    protected final Text headerText, emailText, naamText, wachtwoordText;
    protected final TextField emailTF, naamTF;
    protected final Label labelStatus;
    protected final Button opslaanBtn;
    protected final PasswordField passwordField, passwordField0;

    public parkeerderView(AnchorPane root, parkeerders p, String pv) {
        rectangle = new Rectangle();
        headerText = new Text();
        emailText = new Text();
        naamText = new Text();
        wachtwoordText = new Text();
        labelStatus = new Label();
        emailTF = new TextField();
        naamTF = new TextField();
        opslaanBtn = new Button();
        passwordField = new PasswordField();
        passwordField0 = new PasswordField();

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(50.0);
        rectangle.setLayoutY(49.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(1000.0);

        headerText.setLayoutX(207.0);
        headerText.setLayoutY(40.0);
        headerText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        headerText.setStrokeWidth(0.0);
        headerText.setText("Gebruikersgegevens");
        headerText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        headerText.setWrappingWidth(585.5593872070312);
        headerText.setFont(new Font(31.0));
        
        emailText.setLayoutX(372.0);
        emailText.setLayoutY(205.0);
        emailText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        emailText.setStrokeWidth(0.0);
        emailText.setText("Email:");
   
        
        naamText.setLayoutX(375.0);
        naamText.setLayoutY(152.0);
        naamText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        naamText.setStrokeWidth(0.0);
        naamText.setText("Naam: *");

        wachtwoordText.setLayoutX(372.0);
        wachtwoordText.setLayoutY(251.0);
        wachtwoordText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wachtwoordText.setStrokeWidth(0.0);
        wachtwoordText.setText("Wachtwoord:");

        naamTF.setLayoutX(576.0);
        naamTF.setLayoutY(135.0);
        naamTF.setText(p.getNaam());

        emailTF.setLayoutX(576.0);
        emailTF.setLayoutY(188.0);
        emailTF.setText(p.getEmail());

        labelStatus.setLayoutX(576.0);
        labelStatus.setLayoutY(320.0);

        opslaanBtn.setLayoutX(824.0);
        opslaanBtn.setLayoutY(618.0);
        opslaanBtn.setMnemonicParsing(false);
        opslaanBtn.setPrefHeight(41.0);
        opslaanBtn.setPrefWidth(143.0);
        opslaanBtn.setText("Opslaan");

        passwordField.setLayoutX(576.0);
        passwordField.setLayoutY(274.0);
        passwordField.setDisable(true);
        passwordField.setEditable(false);
        passwordField0.setLayoutX(576.0);
        passwordField0.setLayoutY(234.0);
        passwordField0.setDisable(true);
        passwordField0.setEditable(false);
        root.getChildren().addAll(rectangle, headerText, emailText, naamText, wachtwoordText, naamTF, emailTF, labelStatus, opslaanBtn, passwordField, passwordField0);

        opslaanBtn.setOnAction((ActionEvent event) -> {
           new parkeerderController(naamTF.getText(), emailTF.getText(), passwordField, passwordField0, p, labelStatus);
        });
    

    }
}
