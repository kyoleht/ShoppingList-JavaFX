module com.example.listacompras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.listacompras to javafx.fxml;
    exports com.example.listacompras;
}