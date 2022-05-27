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

public class NuevaHabitacionController extends BaseController implements Initializable {

  @FXML
  private Button btnCrear;

  @FXML
  private Button btnDescartar;

  @FXML
  private CheckBox chkRefrigerador;

  @FXML
  private ComboBox<Tipo> cmbTipo;

  @FXML
  private TextField txtCosto;

  @FXML
  private TextField txtNumero;

  @FXML
  void crearHabitacion(ActionEvent event) {
    ArrayList<String> errores = new ArrayList<String>(); // Array de posibles errores en la verificacion de Strings
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    Habitacion tmpHab = new Habitacion();
    tmpHab.setNumero(this.txtNumero.getText());
    try {
      tmpHab.setCosto(Float.parseFloat(this.txtCosto.getText()));
    } catch (Exception e) {
      errores.add("El costo solo acepta numeros de punto flotante");
    }
    tmpHab.setRefrigerador(chkRefrigerador.isSelected());
    try {
      tmpHab.getTipo().setTipo(cmbTipo.getValue().getTipo());
      tmpHab.getTipo().setIdTipo(cmbTipo.getValue().getIdTipo());
    } catch (Exception e) {
      errores.add("Debe seleccionar un tipo");
    }

    if (!VerificadorStrings.verificarNombre(tmpHab.getNumero())) {
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
        // Agregar en remoto
        cnHab.insertar(tmpHab);
        // Agregar en local
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().add(tmpHab);
        cambiarFXML(event, "ModificarHotel");
      } catch (Exception e) {
        ventanaEmergente("Error", "Error al agregar habitacion", "No se ha agregado la habitación");
      }
    }
  }

  @FXML
  void descartarHabitacion(ActionEvent event) {
    cambiarFXML(event, "ModificarHabitacion");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    cmbTipo.setItems(ObjetoControlador.getInstancia().getTipos());
  }

  // Objetos queries
  HabitacionImplBInterfaz<Habitacion> cnHab = new HabitacionImplBInterfaz<>();

}
