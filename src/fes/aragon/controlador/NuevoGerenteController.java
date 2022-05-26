package fes.aragon.controlador;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
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
		try {
			Gerente tmpGerente = new Gerente();
			tmpGerente.setNombre(txtNombre.getText());
			tmpGerente.setApellidoPaterno(txtApPaterno.getText());
			tmpGerente.setApellidoMaterno(txtApMaterno.getText());
			tmpGerente.setCorreo(txtCorreo.getText());
			tmpGerente.setRfc(txtRfc.getText());
			tmpGerente.setTelefono(txtTelefono.getText());
			cnn.insertar(tmpGerente);
			int id = ObjetoControlador.getInstancia().getIdGerente();
			tmpGerente.setId(id);
			cambiarFXML(event, "NuevoHotel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private GerenteImplBInterfaz<Gerente> cnn = new GerenteImplBInterfaz<>();
}
