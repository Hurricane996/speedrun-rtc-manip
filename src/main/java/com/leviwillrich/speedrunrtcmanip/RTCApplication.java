package com.leviwillrich.speedrunrtcmanip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RTCApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var pageToLoad = OSUtils.os == OSUtils.OS.UNSUPPORTED ?
                "unsupported-platform-view.fxml"
                : "rtc-view.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(RTCApplication.class.getResource(pageToLoad));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        if (OSUtils.os != OSUtils.OS.UNSUPPORTED) {
            Time.turnOffNtp();
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}