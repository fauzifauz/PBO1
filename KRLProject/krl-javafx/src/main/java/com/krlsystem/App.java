package com.krlsystem;

import com.krlsystem.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("login");
        scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        stage.setTitle("KRL System - Terpadu");
        stage.show();
    }

    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        launch();
    }
}
