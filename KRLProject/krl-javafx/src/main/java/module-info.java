module com.krlsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    opens com.krlsystem to javafx.fxml;
    opens com.krlsystem.controller to javafx.fxml;
    opens com.krlsystem.model to javafx.base;

    exports com.krlsystem;
}
