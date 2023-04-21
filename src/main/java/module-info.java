module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;

    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}