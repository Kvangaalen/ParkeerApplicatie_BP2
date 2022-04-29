package com.mycompany.parkeerapplictie.view;

import static com.mycompany.parkeerapplictie.App.pStage;
import com.mycompany.parkeerapplictie.controller.viewController;
import com.mycompany.parkeerapplictie.model.parkeerders;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class myMenuBar extends MenuBar {

    private Menu mHUP, mHUP1;
    private MenuItem Alle, Geopend, Favorieten, Gegevens;

    public myMenuBar(Pane root, parkeerders p) {
        mHUP = new Menu("Parkeergarage");
        Alle = new MenuItem("Alle parkeergarage");
        Geopend = new MenuItem("Geopend parkeergarage");
        Favorieten = new MenuItem("Favorieten parkeergarage");

        Gegevens = new MenuItem("Wijzigen gevens");
        mHUP1 = new Menu("Mijn gevens");


        mHUP.getItems().addAll(Alle, Geopend, Favorieten);
        mHUP1.getItems().addAll(Gegevens);

        this.getMenus().addAll(mHUP, mHUP1);

        Gegevens.setOnAction(event -> {
            try {

                String a = "profiel";
                        new viewController(a, p, pStage);
            } catch (SQLException ex) {
                Logger.getLogger(myMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Alle.setOnAction(event -> {
            try {
                String a = "all";
                new viewController(a, p, pStage);
                // viewController.allParkeergarageSreen(p, pStage);
            } catch (SQLException ex) {
                Logger.getLogger(myMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Favorieten.setOnAction(event -> {
            try {
                String a = "favo";
    new viewController(a, p, pStage);
            } catch (SQLException ex) {
                Logger.getLogger(myMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Geopend.setOnAction(event -> {
            try {
                String a = "open";
          new viewController(a, p, pStage);
            } catch (SQLException ex) {
                Logger.getLogger(myMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
}
