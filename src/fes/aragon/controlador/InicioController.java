package fes.aragon.controlador;

import static javafx.scene.control.ButtonType.OK;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.implementacion.HabitacionImplBInterfaz;
import fes.aragon.modelo.implementacion.HotelImpBInterfaz;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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

public class InicioController extends BaseController implements Initializable {

  @FXML
  private Button btnAgregar;

  @FXML
  private Button btnCargar;

  @FXML
  private Button btnEliminar;

  @FXML
  private Button btnModificar;

  @FXML
  private Button btnSalir;

  @FXML
  private TableColumn<Hotel, String> clmCorreo;

  @FXML
  private TableColumn<Hotel, String> clmDireccion;

  @FXML
  private TableColumn<Hotel, String> clmGerente;

  @FXML
  private TableColumn<Hotel, String> clmNombre;

  @FXML
  private TableColumn<Hotel, String> clmTelefono;

  @FXML
  private TableView<Hotel> tblTabla;

  @FXML
  void agregarHotel(ActionEvent event) {
    try {
      cambiarFXML(event, "NuevoHotel");
    } catch (Exception e) {
      System.out.println("No se pudo cargar el fxml");
      e.printStackTrace();
    }

  }

  @FXML
  void cargar(ActionEvent event) throws Exception {
    ArrayList<Hotel> datos = cnHotel.consulta();
    for (Hotel hotel : datos) {
      Hotel objeto = new Hotel();
      objeto.setId(hotel.getId());
      objeto.setNombre(hotel.getNombre());
      objeto.setDireccion(hotel.getDireccion());
      objeto.setCorreo(hotel.getCorreo());
      objeto.setTelefono(hotel.getTelefono());
      objeto.setGerente(hotel.getGerente());
      objeto.setHabitaciones(FXCollections.observableArrayList(hotel.getHabitaciones()));
      ObjetoControlador.getInstancia().getArrayHotel().add(objeto);
    }
    this.btnAgregar.setDisable(false);
    this.btnModificar.setDisable(false);
    this.btnEliminar.setDisable(false);
    this.btnCargar.setDisable(true);

  }

  @FXML
  void eliminarHotel(ActionEvent event) {
    try {
      int indice = this.tblTabla.getSelectionModel().getSelectedIndex();
      Hotel h = ObjetoControlador.getInstancia().getArrayHotel().get(indice);
      ObjetoControlador.getInstancia().getArrayHotel().remove(indice);
      cnHotel.eliminarProc(h.getId());
    } catch (Exception e) {
      Alert alerta;
      alerta = new Alert(AlertType.INFORMATION);
      alerta.setTitle("Dialogo de Aviso");
      alerta.setHeaderText("Se necesita elegir un hotel");
      alerta.setContentText("Por favor selecciona un hotel para eliminar");
      e.printStackTrace();
    }
  }

  @FXML
  void modificarHotel(ActionEvent event) throws Exception {
    // Obtiene el objeto al que se hace referencia
    int indice = this.tblTabla.getSelectionModel().getSelectedIndex();
    // Se comprueba que se haya seleccionado un objeto
    if (indice >= 0) {
      ObjetoControlador.getInstancia().setIndiceHotel(indice);
      this.cambiarFXML(event, "ModificarHotel");
    } else {
      Alert alerta;
      alerta = new Alert(AlertType.INFORMATION);
      alerta.setTitle("Dialogo de Aviso");
      alerta.setHeaderText("Se necesita una fila");
      alerta.setContentText("Por favor selecciona una fila, para la modificar");
      Optional<ButtonType> resultado = alerta.showAndWait();
      if (resultado.get().equals(OK)) {
      }
    }

  }

  @FXML
  void salir(ActionEvent event) {
    Platform.exit();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // Configurar tabla con objeto de clase Hotel
    // Cada PropertyValueFactory que se quiere representar debe tener el nombre
    // igual al campo de la clase
    this.clmNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    this.clmDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    this.clmCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    this.clmTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    this.clmGerente.setCellValueFactory(new PropertyValueFactory<>("gerente"));
    this.tblTabla.setItems(ObjetoControlador.getInstancia().getArrayHotel());

    if (ObjetoControlador.getInstancia().getArrayHotel().isEmpty()) {
      // Si el array de Hotel esta vacio cargar esta habilitado y agregar y modificar
      // están deshabilitados
      System.out.println("Example");
      this.btnCargar.setDisable(false);
      this.btnAgregar.setDisable(true);
      this.btnModificar.setDisable(true);
      this.btnEliminar.setDisable(true);

      try {
        ArrayList<String> tipos = cnHabitacion.buscarTipo();
        for (String tipo : tipos) {
          ObjetoControlador.getInstancia().getTipos().add(tipo);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    } else {
      // Si el array de Hotel contiene objetos cargar está deshabilitado y agregar y
      // modificar están habilitados
      this.btnCargar.setDisable(true);
      this.btnAgregar.setDisable(false);
      this.btnModificar.setDisable(false);
      this.btnEliminar.setDisable(false);
    }
  }

  // Objetos queries
  private HotelImpBInterfaz<Hotel> cnHotel = new HotelImpBInterfaz<>();
  private HabitacionImplBInterfaz<Gerente> cnHabitacion = new HabitacionImplBInterfaz<>();

}
