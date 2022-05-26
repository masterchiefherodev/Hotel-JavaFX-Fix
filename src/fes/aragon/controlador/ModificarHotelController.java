package fes.aragon.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.VerificadorStrings;
import fes.aragon.modelo.implementacion.HotelImpBInterfaz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModificarHotelController extends BaseController implements Initializable {

  @FXML
  private Button btnDescartar;

  @FXML
  private Button btnModificar;

  @FXML
  private Button btnModificarGerente;

  @FXML
  private Button btnModificarHabitacion;

  @FXML
  private TextField txtCorreo;

  @FXML
  private TextField txtDireccion;

  @FXML
  private TextField txtNombre;

  @FXML
  private TextField txtTelefono;

  @FXML
  void descartar(ActionEvent event) {
    cambiarFXML(event, "Inicio");
  }

  @FXML
  void modificar(ActionEvent event) {
    ArrayList<String> errores = new ArrayList<String>(); // Array de posibles errores en la verificacion de Strings
    Hotel hotelTmp = new Hotel(); // Objeto temporal que servira para verificar los campos
    hotelTmp.setNombre(this.txtNombre.getText().trim()); // Agregar nombre desde el TestField al objeto temporal
    hotelTmp.setDireccion(this.txtDireccion.getText().trim()); // Agregar Direccion desde el TestField al objeto
                                                               // temporal
    hotelTmp.setCorreo(this.txtCorreo.getText().trim()); // Agregar Correo desde el TestField al objeto temporal
    hotelTmp.setTelefono(this.txtTelefono.getText().trim()); // Agregar Telefono desde el TestField al objeto temporal
    // Se asigna el valor del indice (del objeto local) del hotel a modificar a la
    // variable indiceHotel
    // Este indice es en local
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    // Se asigna el id remoto al objeto en su atributo Id
    hotelTmp.setId(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getId());
    // Se verifica .a integridad en todos los campos
    if (!VerificadorStrings.verificarNombre(hotelTmp.getNombre())) {
      errores.add("El nombre no es valido");
    }
    if (!VerificadorStrings.verificarDireccion(hotelTmp.getDireccion())) {
      errores.add("La dirección no es valida");
    }
    if (!VerificadorStrings.verificarCorreo(hotelTmp.getCorreo())) {
      errores.add("El correo no es valido");
    }
    if (!VerificadorStrings.verificarTelefono(hotelTmp.getTelefono())) {
      errores.add("El telefono no es valido");
    }
    // Agregar el log de los errores (si existen) a la ventana de feedback
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
      // Si todos los campos son correctos se actualiza en la base de datos
      try {
        cnHotel.modificar(hotelTmp);
        // Actualiza el objeto local para que se refleje en la interfaz
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).setNombre(hotelTmp.getNombre());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).setDireccion(hotelTmp.getDireccion());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).setCorreo(hotelTmp.getCorreo());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).setTelefono(hotelTmp.getTelefono());
        cambiarFXML(event, "Inicio");
      } catch (Exception e) {
        // e.printStackTrace();
        ventanaEmergente("Error", "Fallo la modificación", "No se ha  podido hacer la actualización");
      }
    }
  }

  @FXML
  void modificarGerente(ActionEvent event) {
    cambiarFXML(event, "ModificarGerente");
  }

  @FXML
  void modificarHabitacion(ActionEvent event) {
    cambiarFXML(event, "ModificarHabitacion");
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    this.txtNombre.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getNombre());
    this.txtDireccion.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getDireccion());
    this.txtCorreo.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getCorreo());
    this.txtTelefono.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getTelefono());
  }

  // Objetos queries
  private HotelImpBInterfaz<Hotel> cnHotel = new HotelImpBInterfaz<>();

}
