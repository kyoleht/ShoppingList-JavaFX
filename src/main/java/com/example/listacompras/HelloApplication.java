package com.example.listacompras;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloApplication extends Application {
    @Override
    public void start(Stage palco) throws IOException {
        Label label = new Label("Insira o item da lista");
        TextField entrada = new TextField();
        entrada.setMaxWidth(300);
        Button add = new Button("Adicionar");

        ObservableList<String> compras = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<String>(compras);
        listView.setMaxHeight(300);

        add.setOnAction(e -> {
            String item = entrada.getText();
            if (item != null && !item.trim().isEmpty()) { // verificação se não está vazio
                compras.add(item);
                entrada.clear();
                entrada.requestFocus(); // devolve o foco para o campo de entrada
            };
        });

        HBox Hbox = new HBox();
        VBox Vbox = new VBox(15);
        Vbox.setAlignment(Pos.CENTER);
        Vbox.getChildren().addAll(entrada, label, add, listView);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));

        Scene scene = new Scene(Vbox, 600, 600);
        palco.setScene(scene);
        palco.setTitle("Lista de Compras");
        palco.show();

        Button btnExportar = new Button("Exportar");
        btnExportar.setOnAction(e -> {
            if (compras.isEmpty()) {
                System.out.println("A lista está vazia!");
                return;
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar lista de compras");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos de Texto", "*.txt"));

            fileChooser.setInitialFileName("lista_de_compras.txt");

            File arquivo = fileChooser.showSaveDialog(palco);

            if(arquivo != null) {
                try(PrintWriter writer = new PrintWriter(arquivo)) {
                    writer.println("---- MINHA LISTA DE COMPRAS ----");
                    for(String item : compras) {
                        writer.println("- " + item);
                    }
                    System.out.println("Arquivo salvo em: " + arquivo.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Vbox.getChildren().add(btnExportar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}