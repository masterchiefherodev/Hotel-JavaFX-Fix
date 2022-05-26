package fes.aragon.controlador;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.implementacion.GerenteImplBInterfaz;
import fes.aragon.modelo.implementacion.HotelImpBInterfaz;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NuevoHotelController extends BaseController implements Initializable{

	@FXML
	private Button btnCrear;

	@FXML
	private Button btnDescartar;

	@FXML
	private Button btnNuevoGerente;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtTelefono;

	@FXML
	void crearHotel(ActionEvent event) {
		try {
			Hotel tmpHotel = new Hotel();
			tmpHotel.setCorreo(txtCorreo.getText());
			tmpHotel.setDireccion(txtDireccion.getText());
			tmpHotel.setGerente(new Gerente());
			tmpHotel.setNombre(txtNombre.getText());
			tmpHotel.setTelefono(txtTelefono.getText());

			int idGerente = ObjetoControlador.getInstancia().getIdGerente();
			Gerente nuevoG = cnGerente.consulta(idGerente);
			tmpHotel.setGerente(nuevoG);
			ObjetoControlador.getInstancia().setIdGerente(-1);

			cnHotel.insertar(tmpHotel);
			tmpHotel.setId(ObjetoControlador.getInstancia().getIdHotel());
			
			ObjetoControlador.getInstancia().getArrayHotel().add(tmpHotel);
			cambiarFXML(event, "Inicio");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void descartar(ActionEvent event) {
		cambiarFXML(event, "Inicio");
	}

	@FXML
	void nuevoGerente(ActionEvent event) {
		if(txtNombre.getText()=="" &&
				txtDireccion.getText()=="" &&
				txtCorreo.getText()=="" &&
				txtTelefono.getText()=="") {
			cambiarFXML(event, "NuevoGerente");
		}else {
			Hotel tmp = new Hotel();
			tmp.setNombre(txtNombre.getText());
			tmp.setCorreo(txtCorreo.getText());
			tmp.setDireccion(txtDireccion.getText());
			tmp.setTelefono(txtNombre.getText());
			ObjetoControlador.getInstancia().setTmpHotel(tmp);
			cambiarFXML(event, "NuevoGerente");
		}
	}

	private HotelImpBInterfaz<Hotel> cnHotel = new HotelImpBInterfaz<>();
	private GerenteImplBInterfaz<Gerente> cnGerente = new GerenteImplBInterfaz<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ObjetoControlador.getInstancia().getTmpHotel()!=null) {
			Hotel hotelTmp = ObjetoControlador.getInstancia().getTmpHotel();
			txtNombre.setText(hotelTmp.getNombre());
			txtCorreo.setText(hotelTmp.getCorreo());
			txtDireccion.setText(hotelTmp.getDireccion());
			txtTelefono.setText(hotelTmp.getTelefono());
			ObjetoControlador.getInstancia().setTmpHotel(null);
		}
		if(ObjetoControlador.getInstancia().getIdGerente() < 0) {
			btnNuevoGerente.setDisable(false);
		}else {
			btnNuevoGerente.setDisable(true);
		}
	}
}
