package fes.aragon.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Tipo;
import fes.aragon.modelo.VerificadorStrings;
import fes.aragon.modelo.implementacion.HabitacionImplBInterfaz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModificarUnaHabitacionController extends BaseController implements Initializable {

  @FXML
  private Button btnModificar;

  @FXML
  private CheckBox chbRefrigerador;

  @FXML
  private ComboBox<Tipo> cmbTipo;

  @FXML
  private Button descartar;

  @FXML
  private TextField txtCosto;

  @FXML
  private TextField txtNumero;

  @FXML
  void descartar(ActionEvent event) {
    cambiarFXML(event, "ModificarHabitacion");
  }

  @FXML
  void modificarHabitacion(ActionEvent event) {
    ArrayList<String> errores = new ArrayList<String>(); // Array de posibles errores en la verificacion de Strings
    Habitacion habTemp = new Habitacion();// Objeto temporal que servira para verificar los campos

    // Agregar al objeto temporal los campos

    // habTemp.setId();
    habTemp.setNumero(this.txtNumero.getText());
    try {
      habTemp.setCosto(Float.parseFloat(this.txtCosto.getText()));
    } catch (Exception e) {
      errores.add("El costo solo acepta numeros de punto flotante");
    }
    habTemp.setRefrigerador(this.chbRefrigerador.isSelected());
    habTemp.getTipo().setTipo(cmbTipo.getValue().getTipo());
    habTemp.getTipo().setIdTipo(cmbTipo.getValue().getIdTipo());
    // Se utiliza el indice local del hotel
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    // Se utilzia el indice local de la habitacion a modificar
    int indiceHabitacion = ObjetoControlador.getInstancia().getIndiceHabitacion();
    habTemp.setId(ObjetoControlador.getInstancia().getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones()
        .get(indiceHabitacion).getId());

    if (!VerificadorStrings.verificarNombre(habTemp.getNumero())) {
      errores.add("El Numero de habitacion no es valido");
    }
    if (errores.size() > 0) {
      String contenido = "";
      for (String error : errores) {
        contenido = contenido + error + "\n";
      }
      // Desplagar ventana emetgente con logs (errores)
      ventanaEmergente("Errores", "Erroes", contenido);
      // Limpia el array para la siguiente comprobación
      errores = new ArrayList<String>();
    } else {
      try {
        // Actualizacion en remoto
        cnHabitacion.modificar(habTemp);
        // Actualiza el objeto local para que se refleje en la interfaz
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().get(indiceHabitacion)
            .setNumero(habTemp.getNumero());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().get(indiceHabitacion)
            .setCosto(habTemp.getCosto());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().get(indiceHabitacion)
            .setRefrigerador(habTemp.isRefrigerador());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().get(indiceHabitacion)
            .getTipo().setTipo(habTemp.getTipo().getTipo());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().get(indiceHabitacion)
            .getTipo().setIdTipo(habTemp.getTipo().getIdTipo());
        cambiarFXML(event, "ModificarHabitacion");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    int inidiceHbitacion = ObjetoControlador.getInstancia().getIndiceHabitacion();
    this.txtNumero.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones()
        .get(inidiceHbitacion).getNumero());
    this.txtCosto.setText(Float.toString(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel)
        .getHabitaciones().get(inidiceHbitacion).getCosto()));
    this.chbRefrigerador.setSelected(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones()
        .get(inidiceHbitacion).isRefrigerador());
    cmbTipo.setItems(ObjetoControlador.getInstancia().getTipos());
    cmbTipo.setValue(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones()
        .get(inidiceHbitacion).getTipo());
  }

//Objetos queries
  private HabitacionImplBInterfaz<Habitacion> cnHabitacion = new HabitacionImplBInterfaz<>();

}
