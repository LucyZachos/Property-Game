package com.example.propertygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PropertyGameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PropertyGameApplication.class.getResource("property-game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Property Game!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}