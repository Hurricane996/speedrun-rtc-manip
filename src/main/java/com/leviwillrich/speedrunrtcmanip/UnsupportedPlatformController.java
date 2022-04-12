package com.leviwillrich.speedrunrtcmanip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UnsupportedPlatformController {
    @FXML
    private Button button;

    public void closeWindow(ActionEvent actionEvent) {
        button.getScene().getWindow().hide();
    }
}
