package fes.aragon.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.VerificadorStrings;
import fes.aragon.modelo.implementacion.GerenteImplBInterfaz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModificarGerenteController extends BaseController implements Initializable {

  @FXML
  private Button btnDescartar;

  @FXML
  private Button btnModificar;

  @FXML
  private TextField txtApellidoMaterno;

  @FXML
  private TextField txtApellidoPaterno;

  @FXML
  private TextField txtCorreo;

  @FXML
  private TextField txtNombte;

  @FXML
  private TextField txtRFC;

  @FXML
  private TextField txtTelefono;

  @FXML
  void descartar(ActionEvent event) {
    cambiarFXML(event, "ModificarHotel");
  }

  @FXML
  void modificarGerente(ActionEvent event) {
    ArrayList<String> errores = new ArrayList<String>(); // Array de posibles errores en la verificacion de Strings
    Gerente gerenteTmp = new Gerente(); // Objeto temporal que servira para verificar los campos
    // Agregar al objeto temporal los campos
    gerenteTmp.setNombre(this.txtNombte.getText());
    gerenteTmp.setApellidoPaterno(this.txtApellidoPaterno.getText());
    gerenteTmp.setApellidoMaterno(this.txtApellidoMaterno.getText());
    gerenteTmp.setRfc(this.txtRFC.getText());
    gerenteTmp.setCorreo(this.txtCorreo.getText());
    gerenteTmp.setTelefono(this.txtTelefono.getText());
    // Se utiliza el indice local del hotel
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    // S inserta el id remoto al objeto local con ayuda del indiceHotel
    gerenteTmp.setId(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getId());

    if (!VerificadorStrings.verificarNombre(gerenteTmp.getNombre())) {
      errores.add("El nombre no es valido");
    }
    if (!VerificadorStrings.verificarNombre(gerenteTmp.getApellidoPaterno())) {
      errores.add("El Apellido Paterno no es valido");
    }
    if (!VerificadorStrings.verificarNombre(gerenteTmp.getApellidoMaterno())) {
      errores.add("El Apellido Materno no es valido");
    }
    if (!VerificadorStrings.verificarRFC(gerenteTmp.getRfc())) {
      errores.add("El RFC no es valido");
    }
    if (!VerificadorStrings.verificarCorreo(gerenteTmp.getCorreo())) {
      errores.add("El correo no es valido");
    }
    if (!VerificadorStrings.verificarTelefono(gerenteTmp.getTelefono())) {
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
        cnGerente.modificar(gerenteTmp);
        // Actualiza el objeto local para que se refleje en la interfaz
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente()
            .setNombre(gerenteTmp.getNombre());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente()
            .setApellidoPaterno(gerenteTmp.getApellidoPaterno());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente()
            .setApellidoMaterno(gerenteTmp.getApellidoMaterno());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().setRfc(gerenteTmp.getRfc());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente()
            .setCorreo(gerenteTmp.getCorreo());
        ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente()
            .setTelefono(gerenteTmp.getTelefono());
        ;
        cambiarFXML(event, "ModificarHotel");
      } catch (Exception e) {
        // e.printStackTrace();
        ventanaEmergente("Error", "Fallo la modificación", "No se ha  podido hacer la actualización");
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    int indiceHotel = ObjetoControlador.getInstancia().getIndiceHotel();
    this.txtNombte.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getNombre());
    this.txtApellidoPaterno
        .setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getApellidoPaterno());
    this.txtApellidoMaterno
        .setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getApellidoMaterno());
    this.txtRFC.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getRfc());
    this.txtCorreo.setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getCorreo());
    this.txtTelefono
        .setText(ObjetoControlador.getInstancia().getArrayHotel().get(indiceHotel).getGerente().getTelefono());
  }

//Objetos queries
  private GerenteImplBInterfaz<Gerente> cnGerente = new GerenteImplBInterfaz<>();

}
