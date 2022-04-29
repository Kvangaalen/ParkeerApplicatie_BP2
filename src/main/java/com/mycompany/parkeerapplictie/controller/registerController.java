package com.mycompany.parkeerapplictie.controller;

import Database.DBCPDataSource;
import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.view.registerView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

public class registerController {

    private String Naam, Email, Passwoord1, Passwoord2;
    Connection con = null;

    public registerController(Label labelStatus, String Naam, String Email, String Passwoord1, String Passwoord2) {

        this.Naam = Naam;
        this.Email = Email;
        this.Passwoord1  = DigestUtils.shaHex(Passwoord1);
        this.Passwoord2 = DigestUtils.shaHex(Passwoord1);
        labelStatus.setText("");

        if (this.Naam.isEmpty() || this.Email.isEmpty() || this.Passwoord1.isEmpty()) {
            labelStatus.setTextFill(Color.RED);
            labelStatus.setText("Vul alle verplichte invoervelden in *");
        } else {
            try {
     
                Connection con = DBCPDataSource.getConnection();
                String sql = "SELECT * FROM parkeerders Where email = ? LIMIT 1";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, this.Email);
                ResultSet resultSet = preparedStatement.executeQuery();
                //foutmelding bij onjuiste gegevens
                if (!resultSet.next()) {
                    String strInsert = "INSERT INTO parkeerders (`email`, `naam`, `paswoord`) VALUES (?, ?, ?)";
                    PreparedStatement pst1;
                    pst1 = con.prepareStatement(strInsert);
                    pst1.setString(1, this.Email);
                    pst1.setString(2, this.Naam);
                    pst1.setString(3, this.Passwoord1);
                    pst1.executeUpdate();
                    labelStatus.setTextFill(Color.GREEN);
                    labelStatus.setText("Account is aangemaakt");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Account is aangemaakt");
                    alert.setHeaderText(null);
                    alert.setContentText("Account is aangemaakt, je wordt terug gestuurd na de Login");
                    alert.showAndWait();
                    viewController.loginScreen(pStage);
                } else {
                    labelStatus.setTextFill(Color.RED);
                    labelStatus.setText("account bestaat");
                }

            } catch (NumberFormatException | SQLException ex) {
                labelStatus.setTextFill(Color.RED);
                labelStatus.setText("Vul alle verplichte invoervelden in *");
                
            } finally {
                try {
                    if (con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(registerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public static void switchtoregisterScreen(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 450);
        // open GuiAanmelden
        registerView registerView = new registerView(root);
        primaryStage.setScene(scene);
    }

}
