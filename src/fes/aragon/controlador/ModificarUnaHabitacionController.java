package fes.aragon.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
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
  private ComboBox<String> cmbTipo;

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
  private HabitacionImplBInterfaz<Gerente> cnHabitacion = new HabitacionImplBInterfaz<>();

}
