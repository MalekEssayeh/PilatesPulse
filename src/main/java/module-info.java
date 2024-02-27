module com.esprit.pilatespulse.pilatespulsev3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.esprit.pilatespulse.pilatespulsev3 to javafx.fxml;
    exports com.esprit.pilatespulse.pilatespulsev3;
    exports com.esprit.pilatespulse.pilatespulsev3.Controllers;
    opens com.esprit.pilatespulse.pilatespulsev3.Controllers to javafx.fxml;
}