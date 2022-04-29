package com.mycompany.parkeerapplictie.controller;

import Database.DataCollectorController;
import static Database.DBCPDataSource.getConnection;
import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.model.parkeerders;
import com.mycompany.parkeerapplictie.model.parkeergarages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class ParkeergarageController {

    public String naam;
    public String email;
    public String pageview;
    public ListView listView;
    public WebView webView;

    public ParkeergarageController(AnchorPane root, parkeerders p, WebView webView, ListView lvParkergarage, String a) throws SQLException {
        // call function getparkergarage
        ObservableList<parkeergarages> list = getparkergarage(p, lvParkergarage, webView, a);
        // add items from list
        lvParkergarage.setItems(list);
    }

    // functie om een parkeergarage toe te voegen aan favo
    public static void addFavo(ListView lvParkergarage, parkeerders p, Button buttontoevoegen, Button buttonverwijderen) {
        Connection conn = null;
        try {
            conn = getConnection();
            ObservableList<parkeergarages> parkeergarageslist;
            parkeergarageslist = lvParkergarage.getSelectionModel().getSelectedItems();
            String strInsert = "INSERT INTO favorieten (`email`, `identifier`) VALUES (?, ?)";
            PreparedStatement pst;
            pst = conn.prepareStatement(strInsert);
            pst.setString(1, p.email);
            pst.setString(2, parkeergarageslist.get(0).identifier);
            int result = pst.executeUpdate();
            if (result > 0) {
                buttontoevoegen.setVisible(false);
                buttonverwijderen.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Favorieten");
                alert.setHeaderText(null);
                alert.setContentText("De parkeergarage is toegevoegd aan favorieten.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(
                        ParkeergarageController.class.getClassLoader().getResource("dark-theme.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");
                alert.showAndWait();

            }
        } catch (SQLException SE) {
        } catch (Exception ex) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ParkeergarageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // functie om een parkeergarage toe te verwijderen aan favo
    public static void delFavo(ListView lvParkergarage, parkeerders p, Button buttontoevoegen, Button buttonverwijderen, String pv) {
        Connection conn = null;
        try {
            conn = getConnection();
            ObservableList<parkeergarages> parkeergarageslist;
            parkeergarageslist = lvParkergarage.getSelectionModel().getSelectedItems();
            String strDelete = "DELETE FROM favorieten WHERE (`email` = ?) and (`identifier` = ?);";
            PreparedStatement pst;
            pst = conn.prepareStatement(strDelete);
            pst.setString(1, p.email);
            pst.setString(2, parkeergarageslist.get(0).identifier);
            int result = pst.executeUpdate();
            if (result > 0) {
                // update view 
                if (pv.equals("favo")) {
                    new viewController(pv, p, pStage);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Favorieten");
                    alert.setHeaderText(null);
                    alert.setContentText("De parkeergarage is verwijderd van favorieten.");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(
                            ParkeergarageController.class.getClassLoader().getResource("dark-theme.css").toExternalForm());
                    dialogPane.getStyleClass().add("custom-alert");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Favorieten");
                    alert.setHeaderText(null);
                    alert.setContentText("De parkeergarage is verwijderd van favorieten.");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(
                            ParkeergarageController.class.getClassLoader().getResource("dark-theme.css").toExternalForm());
                    dialogPane.getStyleClass().add("custom-alert");
                    alert.showAndWait();
                }
                buttontoevoegen.setVisible(true);
                buttonverwijderen.setVisible(false);

            }
        } catch (IndexOutOfBoundsException | SQLException ibe) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ParkeergarageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // deze funtie haalt eerst de static data op uit MySQL vervolgens wordt de dynamic data opgehaald.
    private ObservableList<parkeergarages> getparkergarage(parkeerders p, ListView lv, WebView webView, String a) throws SQLException {
        WebEngine engine = webView.getEngine();
        Connection conn = getConnection();
        ObservableList<parkeergarages> parkeergaragesList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM parkeerapplicatie.parkeergarage";
            Statement st;
            ResultSet rs;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                // Static Data
                String identifier = rs.getString("identifier");
                String name = rs.getString("name");
                int parkingCapacity = rs.getInt("parkingCapacity");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                int chargingPointCapacity = rs.getInt("chargingPointCapacity");
                double minimumHeightInMeters = rs.getDouble("minimumHeightInMeters");

                // Call function: DataCollector
                DataCollectorController dc = new DataCollectorController(identifier);

                // Dynamic data
                boolean open = dc.getHups().isOpen();
                boolean full = dc.getHups().isFull();
                long vacantSpacesl = dc.getHups().getVacantSpacesl();
                
                // doet wat hierin staat als kaart is geladen.
                engine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> obs, Worker.State oldState, Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) { 
                        // JavaScript functie; 
                        engine.executeScript("addPin(" + longitude + "," + latitude + ",\"" + name + "\")");
                    }
                });
                
               // toonen van alleen de open parkeer garage (als a gelijk is aan open)
                if (a.equals("open")) {  
                    if (open) {
                        parkeergaragesList.add(new parkeergarages(identifier, name, parkingCapacity, latitude, longitude, chargingPointCapacity, minimumHeightInMeters, open, full, vacantSpacesl));
                        lv.getItems().addAll(parkeergaragesList);
                    }
                } else {  // toon alle garages
                    parkeergaragesList.add(new parkeergarages(identifier, name, parkingCapacity, latitude, longitude, chargingPointCapacity, minimumHeightInMeters, open, full, vacantSpacesl));
                    lv.getItems().addAll(parkeergaragesList);
                }

                Callback<ListView<parkeergarages>, ListCell<parkeergarages>> cellFactory = (ListView<parkeergarages> p1) -> {
                    final ListCell<parkeergarages> cell = new ListCell<parkeergarages>() {
                        @Override
                        protected void updateItem(parkeergarages p2, boolean bln) {
                            super.updateItem(p2, bln);
                            if (p2 == null || bln) {
                                setGraphic(null);
                            } else {
                                setText(p2.getName());
                            }
                        }
                    };
                    return cell;
                };
                lv.setCellFactory(cellFactory);
            }
        } catch (SQLException ex) {
        } finally {
            conn.close();
        }
        return parkeergaragesList;
    }

    // return  Boolean TRUE; als de parkeerder de parkeergarage aan favorieten heeft toegevoegd
    static Boolean checkfavo(parkeerders p, String id) throws SQLException {
        Connection conn = null;
        Boolean parkeergaragefavorieten = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM favorieten where identifier = ? and email = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, p.email);
            ResultSet resultSet = preparedStatement.executeQuery();
            parkeergaragefavorieten = resultSet.next();
        } catch (SQLException ex) {
        } finally {
            conn.close();
            return parkeergaragefavorieten;
        }
    }

    // deze funictie vult GUI labels in ParkeergarageView als de gebruiker op een item klik in de ListView.
    public static void fillLabel(WebView webView, ListView lv, Label labelNaam, Label labelOpen, Label labelVol, Label LabelBeschikbarePlaatsen, Label LabelTotaalParkeerplaatsen, Label labelCapacityElectricity, Label labelCapacityElectricity0, Label labelHoogte, Button buttontoevoegen, parkeerders p, Button buttonverwijderen) throws SQLException {
        ObservableList<parkeergarages> buurtbewonerlist;
        buurtbewonerlist = lv.getSelectionModel().getSelectedItems();
        labelNaam.setText(buurtbewonerlist.get(0).getName());
        labelCapacityElectricity.setText((Integer.toString(buurtbewonerlist.get(0).getChargingPointCapacity())));
        labelHoogte.setText((Double.toString(buurtbewonerlist.get(0).getMinimumHeightInMeters()) + " Meter"));
        LabelTotaalParkeerplaatsen.setText(Integer.toString(buurtbewonerlist.get(0).getTotaalParkingCapacity()));
        LabelBeschikbarePlaatsen.setText(Long.toString(buurtbewonerlist.get(0).getVacantSpacesl()));
        String id = buurtbewonerlist.get(0).getIdentifier();
        // knop uitschakelen
        buttontoevoegen.setVisible(false);
        buttonverwijderen.setVisible(false);
        WebEngine engine = webView.getEngine();
        engine.executeScript("addPinOnSelect(" + buurtbewonerlist.get(0).getLongitude() + "," + buurtbewonerlist.get(0).getLatitude() + ")");
        // parkeergarrage is toegevoegd aan favorieten.
        Boolean checkfavo = checkfavo(p, id);
        if (checkfavo) {
            buttonverwijderen.setVisible(true);
        } else {
            buttontoevoegen.setVisible(true);
        }
        // pakeergarrage is geopend.
        if (!buurtbewonerlist.get(0).isOpen()) {
            labelOpen.setText("Geopend");
        } else {
            labelOpen.setText("gesloten");
        }
        // parkeergarrage is full.
        if (!buurtbewonerlist.get(0).isFull()) {
            labelVol.setText("Ja");
        } else {
            labelVol.setText("Nee");
        }

    }

}
