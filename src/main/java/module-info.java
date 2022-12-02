module com.iesfranciscodelosrios.Proyecto_Hilos {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.iesfranciscodelosrios.Proyecto_Hilos to javafx.fxml;
    exports com.iesfranciscodelosrios.Proyecto_Hilos;
}
