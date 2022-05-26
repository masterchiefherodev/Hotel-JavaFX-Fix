package fes.aragon.modelo.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.mysql.Conexion;

public class GerenteImplBInterfaz<E> implements IBaseDatos<E> {

	@Override
	public ArrayList<E> consulta() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<E> buscar(String patron) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(E obj) throws Exception {
		// TODO Auto-generated method stub
		String query = "insert into gerentes(nombre,apellido_paterno,apellido_materno,rfc,correo,telefono) values(?,?,?,?,?,?);";
		Gerente gerenteTmp = (Gerente) obj;
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				java.sql.Statement.RETURN_GENERATED_KEYS);
		solicitud.setString(1, gerenteTmp.getNombre());
		solicitud.setString(2, gerenteTmp.getApellidoPaterno());
		solicitud.setString(3, gerenteTmp.getApellidoMaterno());
		solicitud.setString(4, gerenteTmp.getRfc());
		solicitud.setString(5, gerenteTmp.getCorreo());
		solicitud.setString(6, gerenteTmp.getTelefono());
		solicitud.executeUpdate();
		ResultSet rs = solicitud.getGeneratedKeys();
		rs.next();
		ObjetoControlador.getInstancia().setIdGerente(rs.getInt(1));
		solicitud.close();
	}

	@Override
	public void modificar(E obj) throws Exception {
		String query = "UPDATE gerentes SET nombre=?, apellido_paterno=?, apellido_materno=?, "
				+ "rfc =?, correo=?, telefono=? where id_gre=?";
		Gerente gerenteTmp = (Gerente) obj;
		PreparedStatement solicitud = Conexion.getInstancia().getInstancia().getCnn().prepareStatement(query);
		solicitud.setString(1, gerenteTmp.getNombre());
		solicitud.setString(2, gerenteTmp.getApellidoPaterno());
		solicitud.setString(3, gerenteTmp.getApellidoMaterno());
		solicitud.setString(4, gerenteTmp.getRfc());
		solicitud.setString(5, gerenteTmp.getCorreo());
		solicitud.setString(6, gerenteTmp.getTelefono());
		solicitud.setInt(7, gerenteTmp.getId());
		solicitud.executeUpdate();
		solicitud.close();

	}

	@Override
	public E consulta(Integer id) throws Exception {
		String query = "select * from gerentes where id_gre="+id;
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		resultado.next();
		Gerente tmp = new Gerente();
		tmp.setId(resultado.getInt(1));
		tmp.setNombre(resultado.getString(2));
		tmp.setApellidoPaterno(resultado.getString(3));
		tmp.setApellidoMaterno(resultado.getString(4));
		tmp.setRfc(resultado.getString(5));
		tmp.setCorreo(resultado.getString(6));
		tmp.setTelefono(resultado.getString(7));
		return (E) tmp;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProc(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

}
