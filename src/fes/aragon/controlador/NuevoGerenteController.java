package fes.aragon.controlador;

import java.util.ArrayList;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.VerificadorStrings;
import fes.aragon.modelo.implementacion.GerenteImplBInterfaz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NuevoGerenteController extends BaseController {

  @FXML
  private Button btnCrear;

  @FXML
  private Button btnDescartar;

  @FXML
  private TextField txtApMaterno;

  @FXML
  private TextField txtApPaterno;

  @FXML
  private TextField txtCorreo;

  @FXML
  private TextField txtNombre;

  @FXML
  private TextField txtRfc;

  @FXML
  private TextField txtTelefono;

  @FXML
  void descartarGerente(ActionEvent event) {
    cambiarFXML(event, "NuevoHotel");
  }

  @FXML
  void nuevoGerente(ActionEvent event) {
    ArrayList<String> errores = new ArrayList<String>(); // Array de posibles errores en la verificacion de Strings
    Gerente tmpGerente = new Gerente();
    tmpGerente.setNombre(txtNombre.getText());
    tmpGerente.setApellidoPaterno(txtApPaterno.getText());
    tmpGerente.setApellidoMaterno(txtApMaterno.getText());
    tmpGerente.setCorreo(txtCorreo.getText());
    tmpGerente.setRfc(txtRfc.getText());
    tmpGerente.setTelefono(txtTelefono.getText());

    if (!VerificadorStrings.verificarNombre(tmpGerente.getNombre())) {
      errores.add("El nombre no es valido");
    }
    if (!VerificadorStrings.verificarNombre(tmpGerente.getApellidoPaterno())) {
      errores.add("El Apellido Paterno no es valido");
    }
    if (!VerificadorStrings.verificarNombre(tmpGerente.getApellidoMaterno())) {
      errores.add("El Apellido Materno no es valido");
    }
    if (!VerificadorStrings.verificarRFC(tmpGerente.getRfc())) {
      errores.add("El RFC no es valido (5 digitos)");
    }
    if (!VerificadorStrings.verificarCorreo(tmpGerente.getCorreo())) {
      errores.add("El correo no es valido");
    }
    if (!VerificadorStrings.verificarTelefono(tmpGerente.getTelefono())) {
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
      try {
        cnn.insertar(tmpGerente);
        int id = ObjetoControlador.getInstancia().getIdGerente();
        tmpGerente.setId(id);
        cambiarFXML(event, "NuevoHotel");
      } catch (Exception e) {
        ventanaEmergente("Error", "Error al insertar nuevo gerente", "No se ha insetado el nuevo gerente");
      }
    }

  }

  private GerenteImplBInterfaz<Gerente> cnn = new GerenteImplBInterfaz<>();
}
