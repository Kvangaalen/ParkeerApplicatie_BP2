package com.mycompany.parkeerapplictie.controller;

import Database.DataCollectorController;
import static Database.DBCPDataSource.getConnection;
import com.mycompany.parkeerapplictie.model.parkeerders;
import com.mycompany.parkeerapplictie.model.parkeergarages;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

public class parkeergarageFavorietenController {

    private GridPane grid = new GridPane();
    private ListView ListView;
    public String naam;
    public String email;

    public parkeergarageFavorietenController(AnchorPane root, parkeerders p, WebView webView, ListView lvParkergarage) throws SQLException {
            ObservableList<parkeergarages> list = getFavoparkergarage(p, lvParkergarage, webView);
            lvParkergarage.setItems(list);
    }

    public ObservableList<parkeergarages> getFavoparkergarage(parkeerders p, ListView lv, WebView webView) throws SQLException {
        WebEngine engine = webView.getEngine();
        Connection conn = getConnection();
        ObservableList<parkeergarages> parkeergaragesList = FXCollections.observableArrayList();
        try {
            String query1 = "Select * From favorieten Inner Join parkeergarage On parkeergarage.identifier = favorieten.identifier where email = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query1);
            preparedStatement.setString(1, p.email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String identifier = rs.getString("identifier");
                String name = rs.getString("name");
                int parkingCapacity = rs.getInt("parkingCapacity");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                int chargingPointCapacity = rs.getInt("chargingPointCapacity");
                double minimumHeightInMeters = rs.getDouble("minimumHeightInMeters");

                // Call function: DataCollector
                DataCollectorController dc = new DataCollectorController(identifier);
                boolean open = dc.getHups().isOpen();
                boolean full = dc.getHups().isFull();
                long vacantSpacesl = dc.getHups().getVacantSpacesl();
                engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        // doet wat hierin staat als volledige kaart is geladen
                   engine.executeScript("addPin(" + longitude + "," + latitude + ",\"" + name + "\")");
                    }
                });

                parkeergaragesList.add(new parkeergarages(identifier, name, parkingCapacity, latitude, longitude, chargingPointCapacity, minimumHeightInMeters, open, full, vacantSpacesl));
                lv.getItems().addAll(parkeergaragesList);

                Callback<ListView<parkeergarages>, ListCell<parkeergarages>> cellFactory = new Callback<ListView<parkeergarages>, ListCell<parkeergarages>>() {
                    public ListCell<parkeergarages> call(ListView<parkeergarages> p) {
                        final ListCell<parkeergarages> cell = new ListCell<parkeergarages>() {
                            @Override
                            protected void updateItem(parkeergarages p, boolean bln) {
                                super.updateItem(p, bln);
                                if (p == null || bln) {
                                    setGraphic(null);
                                } else {
                                    setText(p.getName());
                                }
                            }
                        };
                        return cell;
                    }
                };
                lv.setCellFactory(cellFactory);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }
        return parkeergaragesList;
    }

    // deze funictie vult GUI labels.
    public static void fillLabel(WebView webView, ListView lv, Label labelNaam, Label labelOpen, Label labelVol, Label LabelBeschikbarePlaatsen, Label LabelTotaalParkeerplaatsen, Label labelCapacityElectricity, Label labelCapacityElectricity0, Label labelHoogte) {
        ObservableList<parkeergarages> buurtbewonerlist;
        buurtbewonerlist = lv.getSelectionModel().getSelectedItems();
        labelNaam.setText(buurtbewonerlist.get(0).getName());
        labelCapacityElectricity.setText((Integer.toString(buurtbewonerlist.get(0).getChargingPointCapacity())));
        labelHoogte.setText((Double.toString(buurtbewonerlist.get(0).getMinimumHeightInMeters()) + " Meter"));
        LabelTotaalParkeerplaatsen.setText(Integer.toString(buurtbewonerlist.get(0).getTotaalParkingCapacity()));
        LabelBeschikbarePlaatsen.setText(Long.toString(buurtbewonerlist.get(0).getVacantSpacesl()));

        if (!buurtbewonerlist.get(0).isOpen()) {
            labelOpen.setText("Geopend");

        } else {
            labelOpen.setText("gesloten");
        }
        if (!buurtbewonerlist.get(0).isFull()) {
            labelVol.setText("Ja");

        } else {
            labelVol.setText("Nee");
        }

    }

}
