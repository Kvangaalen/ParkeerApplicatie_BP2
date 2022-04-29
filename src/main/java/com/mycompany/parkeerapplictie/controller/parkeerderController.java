package com.mycompany.parkeerapplictie.controller;

import static Database.DBCPDataSource.getConnection;
import com.mycompany.parkeerapplictie.model.parkeerders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;

public class parkeerderController {

    public parkeerderController(String naamTF, String emailTF, PasswordField passwordField, PasswordField passwordField0, parkeerders p, Label labelStatus) {
        Connection con = null;
        System.out.println(naamTF);
        Connection conn = null;
        if (naamTF.isEmpty()) {
            labelStatus.setTextFill(Color.RED);
            labelStatus.setText("Verplicht invoervelden zijn leeg *");
        } else {
            try {
                conn = getConnection();
                String strInsert = "UPDATE parkeerders SET naam = ? WHERE (email = ?);";
                PreparedStatement pst;
                pst = conn.prepareStatement(strInsert);
                pst.setString(1, naamTF);
                pst.setString(2, emailTF);
                int result = pst.executeUpdate();
                if (result > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update");
                    alert.setHeaderText(null);
                    alert.setContentText("Account details zijn geupdate.");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(
                            this.getClass().getClassLoader().getResource("dark-theme.css").toExternalForm());
                    dialogPane.getStyleClass().add("myDialog");

                    dialogPane.getStyleClass().add("custom-alert");

                    alert.showAndWait();

                    p.setNaam(naamTF);
                }
            } catch (Exception ex) {
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ParkeergarageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
