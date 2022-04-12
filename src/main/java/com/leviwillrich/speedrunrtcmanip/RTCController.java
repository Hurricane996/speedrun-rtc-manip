package com.leviwillrich.speedrunrtcmanip;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RTCController {
    private int currentId;
    private final Map<Integer, HBox> buttons = new HashMap<>();

    public void addButton(String name, Date date) {
        var id = currentId++;

        var hbox = new HBox();

        var actionButton = new Button(name);

        actionButton.setOnAction(event -> {
            try {
                Time.setDate(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        var deleteButton = new Button("X");

        deleteButton.setOnAction(event -> removeButton(id));

        hbox.getChildren().add(actionButton);
        hbox.getChildren().add(deleteButton);

        buttons.put(id, hbox);

        buttonContainer.getChildren().add(hbox);
    }

    private void removeButton(int index) {
        var hbox = buttons.get(index);

        buttonContainer.getChildren().remove(hbox);

        buttons.remove(index);
    }

    @FXML
    private VBox buttonContainer;

    @FXML
    private Label currentTime;


    public void setCurrentTime(String s) {
        currentTime.setText("Current Time: " + Utils.dateFormatter.format(new Date()));
    }

    @FXML
    protected void initialize () {
        var rtc = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setCurrentTime(Utils.dateFormatter.format(new Date()));
            }
        };
        rtc.start();
    }

    @FXML
    protected void onAddButtonClick () throws IOException {
        var fxmlLoader = new FXMLLoader(RTCApplication.class.getResource("new-button-view.fxml"));
        fxmlLoader.setControllerFactory(param -> new NewButtonController(this));

        var secondWindow = new Stage();
        secondWindow.setScene(new Scene(fxmlLoader.load(), 200,200 ));
        secondWindow.show();
    }

    @FXML
    protected void onCloseRequest() throws IOException {
        // be a good citizen and turn ntp back on
        Time.turnOnNtp();
    }
}