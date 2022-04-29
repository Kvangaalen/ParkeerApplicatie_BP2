package com.mycompany.parkeerapplictie.controller;

import com.mycompany.parkeerapplictie.model.parkeerders;
import com.mycompany.parkeerapplictie.view.ParkeergarageView;
import com.mycompany.parkeerapplictie.view.loginView;
import com.mycompany.parkeerapplictie.view.myMenuBar;
import com.mycompany.parkeerapplictie.view.parkeerderView;
import com.mycompany.parkeerapplictie.view.registerView;

import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class viewController {

    public WebView webView;
    public ListView lvParkergarage;

    public viewController(String pv, parkeerders p, Stage stage) throws SQLException {
        lvParkergarage = new ListView();
        webView = new WebView();
        AnchorPane root = new AnchorPane();
        MenuBar mb = new myMenuBar(root, p);
        VBox vbox = new VBox(mb, root);

        switch (pv) {
            case "favo":
                new parkeergarageFavorietenController(root, p, webView, lvParkergarage);
                new ParkeergarageView(root, p, webView, lvParkergarage, pv);
                break;
            case "open":
                new ParkeergarageController(root, p, webView, lvParkergarage, pv);
                new ParkeergarageView(root, p, webView, lvParkergarage, pv);
                break;
            case "all":
                new ParkeergarageController(root, p, webView, lvParkergarage, pv);
                new ParkeergarageView(root, p, webView, lvParkergarage, pv);
                break;
            case "profiel":
                new parkeerderView(root, p, pv);
                break;

            default:
                break;
        }
        // call view
        Scene scene = new Scene(vbox, 1000, 700);

        String map = this.getClass().getClassLoader().getResource("dark-theme.css").toExternalForm();
        scene.getStylesheets().add(map);

        stage.setScene(scene);
        //stage.setFullScreen(!stage.isFullScreen());
    }

    // GUI voor register
    public static void registerScreen(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 450);
        stage.setScene(scene);
        registerView registerView = new registerView(root);
    }

    // GUI voor login
    public static void loginScreen(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 450);
        stage.setScene(scene);
        loginView loginView = new loginView(root);
    }

}
