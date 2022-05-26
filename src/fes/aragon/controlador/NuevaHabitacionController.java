package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
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
  private ComboBox<String> cmbTipo;

  @FXML
  private TextField txtCosto;

  @FXML
  private TextField txtNombre;

  @FXML
  void crearHabitacion(ActionEvent event) {
    try {
      Habitacion tmpHab = new Habitacion();
      tmpHab.setNumero(txtNombre.getText());
      tmpHab.setCosto(Float.parseFloat(txtCosto.getText()));
      tmpHab.setRefrigerador(chkRefrigerador.isSelected());
      tmpHab.setTipo(cmbTipo.getSelectionModel().getSelectedItem());
      cnHab.insertar(tmpHab);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @FXML
  void descartarHabitacion(ActionEvent event) {
    cambiarFXML(event, "ModificarHabitacion");
  }

  HabitacionImplBInterfaz<Habitacion> cnHab = new HabitacionImplBInterfaz<>();

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    cmbTipo.setItems(ObjetoControlador.getInstancia().getTipos());
  }
}
