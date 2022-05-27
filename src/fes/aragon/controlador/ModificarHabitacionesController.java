package fes.aragon.controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Tipo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModificarHabitacionesController extends BaseController implements Initializable {

  @FXML
  private Button btnAgregar;

  @FXML
  private Button btnModificar;

  @FXML
  private Button btnSalir;

  @FXML
  private TableColumn<Habitacion, Float> clmCosto;

  @FXML
  private TableColumn<Habitacion, String> clmNumero;

  @FXML
  private TableColumn<Habitacion, Boolean> clmRefrigerador;

  @FXML
  private TableColumn<Habitacion, Tipo> clmTipo;

  @FXML
  private TableView<Habitacion> tblTabla;

  @FXML
  void agregarHabitacion(ActionEvent event) {

  }

  @FXML
  void modificarHabitacion(ActionEvent event) {
    // Obtiene el objeto al que se hace referencia
    int indice = this.tblTabla.getSelectionModel().getSelectedIndex();
    // Se comprueba que se haya seleccionado un objeto
    if (indice >= 0) {
      ObjetoControlador.getInstancia().setIndiceHabitacion(indice);
      this.cambiarFXML(event, "ModificarUnaHabitacion");
    } else {
      Alert alerta;
      alerta = new Alert(AlertType.INFORMATION);
      alerta.setTitle("Dialogo de Aviso");
      alerta.setHeaderText("Se necesita una fila");
      alerta.setContentText("Por favor selecciona una fila, para la modificar");
      Optional<ButtonType> resultado = alerta.showAndWait();
    }
  }

  @FXML
  void salir(ActionEvent event) {
    cambiarFXML(event, "ModificarHotel");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    this.clmNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
    this.clmCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
    this.clmRefrigerador.setCellValueFactory(new PropertyValueFactory<>("refrigerador"));
    this.clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    Hotel hotel = ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel);
    this.tblTabla.setItems(hotel.getHabitaciones());

  }

}
