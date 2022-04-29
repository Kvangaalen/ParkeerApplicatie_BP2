package com.mycompany.parkeerapplictie.controller;

import Database.DBCPDataSource;
import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.model.parkeerders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.apache.commons.codec.digest.DigestUtils;

public class loginController {

    private String Email, Passwoord;
    private String pv;

    public loginController(Label labelStatus, String Email, String Passwoord) {
        Connection con = null;
        labelStatus.setText("");
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            if ((Passwoord.isEmpty()) || (Email).isEmpty()) {
                labelStatus.setTextFill(Color.RED);
                labelStatus.setText("Invoer velden zijn leeg..");
            } else {
                String pword = DigestUtils.shaHex(Passwoord);
                String sql = "SELECT * FROM parkeerders Where email = ? and paswoord = ? LIMIT 1";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, Email);
                preparedStatement.setString(2, pword);
                ResultSet resultSet = preparedStatement.executeQuery();
                //foutmelding bij onjuiste gegevens
                if (!resultSet.next()) {
                    labelStatus.setTextFill(Color.RED);
                    labelStatus.setText("Voer geldige gegevens in");
                    //inloggen bij juiste gegevens
                } else {

                    String email = resultSet.getString("email");
                    String naam = resultSet.getString("naam");
                    parkeerders p = new parkeerders(email, naam);
                    p.setEmail(resultSet.getString("email"));
                    p.setNaam(resultSet.getString("naam"));

                    System.out.println("Ingelogd");
                    // open View Parkeerapplictie
                    pv = "all";
                    new viewController(pv, p, pStage);

                }
            }
        } catch (NumberFormatException | SQLException ex) {

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
