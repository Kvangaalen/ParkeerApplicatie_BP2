package com.mycompany.parkeerapplictie;

import com.mycompany.parkeerapplictie.controller.viewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage pStage;

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;
        viewController.loginScreen(pStage);
        pStage.setResizable(false);
        pStage.setTitle("Parkeerapplicatie - Nijmegen");
        pStage.show();
        getAppIcon();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void getAppIcon() {
        try {
            FileInputStream input;
            input = new FileInputStream("src/main/resources/img/rsz_logo.jpg");
            Image image = new Image(input);
            pStage.getIcons().add(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
