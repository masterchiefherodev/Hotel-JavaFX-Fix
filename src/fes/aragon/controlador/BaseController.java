package fes.aragon.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BaseController {
  // Crea una ventana para dar feedback al usuario
  protected void ventanaEmergente(String titulo, String encabezado, String contenido) {
    Alert alerta;
    alerta = new Alert(AlertType.INFORMATION);
    alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
    alerta.setTitle(titulo);
    alerta.setHeaderText(encabezado);
    alerta.setContentText(contenido);
    alerta.showAndWait();
  }

  // Metodo que sirve para cambiar el fxml ativo
  protected void cambiarFXML(ActionEvent event, String fxml) {
    try {
      Node node = (Node) event.getSource();
      Stage primaryStage = (Stage) node.getScene().getWindow();
      Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + fxml + ".fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("/fes/aragon/css/application.css").toExternalForm());
      primaryStage.setScene(scene);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
