package com.leviwillrich.speedrunrtcmanip;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalDateTimePicker;

import java.time.ZoneId;
import java.util.Date;

public class NewButtonController {
    @FXML private TextField name;
    @FXML private LocalDateTimePicker dateTimePicker;

    private RTCController parent;
    public NewButtonController(RTCController parent) {
        this.parent = parent;
    }

    @FXML
    protected void onAddButtonClick() {

        // convert from local time to utc
        var date = Date.from(dateTimePicker.getLocalDateTime().atZone(ZoneId.systemDefault()).toInstant());
        var timerName = name.getText();

        parent.addButton(timerName, date);

        name.getScene().getWindow().hide();
    }
}
