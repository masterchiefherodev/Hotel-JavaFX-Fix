package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Tipo;
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
    try {
      int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
      Habitacion tmpHab = new Habitacion();
      tmpHab.setNumero(this.txtNumero.getText());
      tmpHab.setCosto(Float.parseFloat(txtCosto.getText()));
      tmpHab.setRefrigerador(chkRefrigerador.isSelected());
      tmpHab.getTipo().setTipo(cmbTipo.getValue().getTipo());
      tmpHab.getTipo().setIdTipo(cmbTipo.getValue().getIdTipo());
      cnHab.insertar(tmpHab);
      ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getHabitaciones().add(tmpHab);
      cambiarFXML(event, "ModificarHotel");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      ventanaEmergente("Error", "No se ha insertado",
          "No se ha podido insertar la habitación consulte con un programador");
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
