module com.leviwillrich.speedrunrtcmanip {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxtras.controls;


    opens com.leviwillrich.speedrunrtcmanip to javafx.fxml;
    exports com.leviwillrich.speedrunrtcmanip;
}