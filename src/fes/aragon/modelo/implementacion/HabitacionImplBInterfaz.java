package fes.aragon.modelo.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.mysql.Conexion;

public class HabitacionImplBInterfaz<E> implements IBaseDatos<E> {

	public HabitacionImplBInterfaz() {

	}

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
		String query = "insert into habitaciones(numero,costo,refrigerador,id_tps,id_htl) values(?,?,?,?,?);";
		Habitacion tmpHab = new Habitacion();
		PreparedStatement solicitud = Conexion.getInstancia().getInstancia().getCnn().prepareStatement(query);
		solicitud.setString(1, tmpHab.getNumero());
		solicitud.setFloat(2, tmpHab.getCosto());
		solicitud.setBoolean(3, tmpHab.isRefrigerador());
		solicitud.setInt(4, ObjetoControlador.getInstancia().getTipos().indexOf(tmpHab.getTipo()));
		solicitud.setInt(5, ObjetoControlador.getInstancia().getIdHotel());
		solicitud.executeUpdate();
		solicitud.close();
	}

	@Override
	public void modificar(E obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void cerrar() throws Exception {
		Conexion.getInstancia().cerrar();
	}

	@Override
	public void eliminarProc(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	public ArrayList<E> buscarIdHotel(Integer id) throws Exception {
		String query = "select a.id_hbt,a.numero,a.costo,a.refrigerador,a.id_htl,b.tipo"
				+ " from habitaciones a,tipos b where id_htl=" + id + " and a.id_tps=b.id_tps";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			// System.out.println("Sin datos Habitacion");
		} else {
			do {
				Habitacion hb = new Habitacion();
				hb.setId(resultado.getInt(1));
				hb.setNumero(resultado.getString(2));
				hb.setCosto(resultado.getFloat(3));
				hb.setRefrigerador(resultado.getBoolean(4));
				hb.setTipo(resultado.getString(6));
				hb.setIdHotel(id);
				datos.add((E) hb);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

	public ArrayList<String> buscarTipo() throws Exception {
		String query = "select tipo from tipos";
		ArrayList<String> tipos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			//
		} else {
			do {
				tipos.add(resultado.getString(1));
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return tipos;
	}

}
