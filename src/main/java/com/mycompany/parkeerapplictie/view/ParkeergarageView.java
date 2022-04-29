package com.mycompany.parkeerapplictie.view;

import com.mycompany.parkeerapplictie.controller.ParkeergarageController;
import com.mycompany.parkeerapplictie.model.parkeerders;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ParkeergarageView extends AnchorPane {

    public Label lbNaam, lbOpen, lbFull, lbVacantCapacity, lbCapacity, lbCapacityElectricity, lbHeight, labelNaam, labelOpen, labelVol, LabelBeschikbarePlaatsen, LabelTotaalParkeerplaatsen, labelCapacityElectricity, labelHoogte, labelHeader;
    public Line line;
    public Rectangle rectangle;
    public Button buttontoevoegen, buttonverwijderen;

    public ParkeergarageView(AnchorPane root, parkeerders p, WebView webView, ListView lvParkergarage, String pv) throws SQLException {

        lbNaam = new Label();
        lbOpen = new Label();
        lbFull = new Label();
        lbVacantCapacity = new Label();
        lbCapacity = new Label();
        lbCapacityElectricity = new Label();
        lbHeight = new Label();
        labelNaam = new Label();
        labelOpen = new Label();
        labelVol = new Label();
        LabelBeschikbarePlaatsen = new Label();
        LabelTotaalParkeerplaatsen = new Label();
        labelCapacityElectricity = new Label();
        labelHoogte = new Label();
        labelHeader = new Label();
        line = new Line();
        rectangle = new Rectangle();
        buttontoevoegen = new Button();
        buttonverwijderen = new Button();
        lvParkergarage.setLayoutX(14.0);
        lvParkergarage.setLayoutY(72.0);
        lvParkergarage.setPrefHeight(295.0);
        lvParkergarage.setPrefWidth(239.0);

        webView.setLayoutX(267.0);
        webView.setLayoutY(72.0);
        webView.setPrefHeight(507.0);
        webView.setPrefWidth(519.0);

        lbNaam.setLayoutX(14.0);
        lbNaam.setLayoutY(425.0);
        lbNaam.setText("Naam:");

        lbOpen.setLayoutX(14.0);
        lbOpen.setLayoutY(442.0);
        lbOpen.setText("Open/gesloten:");

        lbFull.setLayoutX(14.0);
        lbFull.setLayoutY(459.0);
        lbFull.setText("Vol:");

        lbVacantCapacity.setLayoutX(14.0);
        lbVacantCapacity.setLayoutY(476.0);
        lbVacantCapacity.setText("Beschikbaar parkeerplaatsen:");

        lbCapacity.setLayoutX(14.0);
        lbCapacity.setLayoutY(493.0);
        lbCapacity.setText("Capaciteit Totaal:");

        lbCapacityElectricity.setLayoutX(14.0);
        lbCapacityElectricity.setLayoutY(510.0);
        lbCapacityElectricity.setText("Capaciteit oplaadpunten");

        lbHeight.setLayoutX(14.0);
        lbHeight.setLayoutY(527.0);
        lbHeight.setText("Hoogte:");

        labelNaam.setLayoutX(183.0);
        labelNaam.setLayoutY(425.0);

        labelOpen.setLayoutX(183.0);
        labelOpen.setLayoutY(442.0);

        labelVol.setLayoutX(183.0);
        labelVol.setLayoutY(459.0);

        LabelBeschikbarePlaatsen.setLayoutX(183.0);
        LabelBeschikbarePlaatsen.setLayoutY(476.0);

        LabelTotaalParkeerplaatsen.setLayoutX(183.0);
        LabelTotaalParkeerplaatsen.setLayoutY(493.0);

        labelCapacityElectricity.setLayoutX(183.0);
        labelCapacityElectricity.setLayoutY(510.0);

        labelHoogte.setLayoutX(183.0);
        labelHoogte.setLayoutY(527.0);

        labelHeader.setLayoutX(15.0);
        labelHeader.setLayoutY(373.0);
        labelHeader.setText("Informatie van parkeergarage");
        labelHeader.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        labelHeader.setFont(new Font(17.0));

        line.setEndX(122.0);
        line.setLayoutX(115.0);
        line.setLayoutY(399.0);
        line.setStartX(-100.0);

        webView.setPrefSize(720, 590);
        WebEngine engine = webView.getEngine();

        try {
            String map = this.getClass().getClassLoader().getResource("map_Nijmegen.html").toExternalForm();
            engine.load(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(35.0);
        rectangle.setLayoutX(-1.0);
        rectangle.setLayoutY(27.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(1282.0);

        buttontoevoegen.setLayoutX(48.0);
        buttontoevoegen.setLayoutY(554.0);
        buttontoevoegen.setMnemonicParsing(false);
        buttontoevoegen.setText("Toevoegen favo");
        buttontoevoegen.setVisible(false);

        buttonverwijderen.setLayoutX(48.0);
        buttonverwijderen.setLayoutY(554.0);
        buttonverwijderen.setMnemonicParsing(false);
        buttonverwijderen.setText("Verwijderen favo");
        buttonverwijderen.setVisible(false);

        root.getChildren().addAll(lvParkergarage, webView, lbNaam, lbOpen, lbFull, lbVacantCapacity, lbCapacity, lbCapacityElectricity, lbHeight, labelNaam, labelOpen, labelVol, LabelBeschikbarePlaatsen, LabelTotaalParkeerplaatsen, labelCapacityElectricity, labelHoogte, labelHeader, line);
        root.getChildren().addAll(rectangle, buttontoevoegen, buttonverwijderen);

        // Eventhandler call; function fillLabel
        lvParkergarage.setOnMouseClicked((MouseEvent event) -> {
            try {
                ParkeergarageController.fillLabel(webView, lvParkergarage, labelNaam, labelOpen, labelVol, LabelBeschikbarePlaatsen, LabelTotaalParkeerplaatsen,
                labelCapacityElectricity, labelCapacityElectricity, labelHoogte, buttontoevoegen, p, buttonverwijderen);
            } catch (SQLException ex) {
                Logger.getLogger(ParkeergarageView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        buttontoevoegen.setOnAction((ActionEvent event) -> {
            ParkeergarageController.addFavo(lvParkergarage, p, buttontoevoegen, buttonverwijderen);

        });
        
        buttonverwijderen.setOnAction((ActionEvent event) -> {
              ParkeergarageController.delFavo(lvParkergarage, p, buttontoevoegen, buttonverwijderen, pv);         
        
        });
    }

}
