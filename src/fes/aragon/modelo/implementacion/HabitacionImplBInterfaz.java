package fes.aragon.modelo.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Tipo;
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
    String query = "UPDATE habitaciones SET numero=?, costo=?, refrigerador=?, id_tps=? " + "where id_hbt=?";
    Habitacion hbtTmp = (Habitacion) obj;
    PreparedStatement solicitud = Conexion.getInstancia().getInstancia().getCnn().prepareStatement(query);
    solicitud.setString(1, hbtTmp.getNumero());
    solicitud.setFloat(2, hbtTmp.getCosto());
    solicitud.setBoolean(3, hbtTmp.isRefrigerador());
    solicitud.setInt(4, hbtTmp.getTipo().getIdTipo());
    solicitud.setInt(5, hbtTmp.getId());
    solicitud.executeUpdate();
    solicitud.close();
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
    String query = "select a.id_hbt,a.numero,a.costo,a.refrigerador,a.id_htl,b.tipo,b.id_tps"
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
        hb.getTipo().setTipo(resultado.getString(6));
        hb.getTipo().setIdTipo(resultado.getInt(7));
        hb.setIdHotel(id);
        datos.add((E) hb);
      } while (resultado.next());
    }
    solicitud.close();
    resultado.close();
    return datos;
  }

  public ArrayList<Tipo> buscarTipo() throws Exception {
    String query = "select tipo,id_tps from tipos";
    ArrayList<Tipo> tipos = new ArrayList<>();
    Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
    ResultSet resultado = solicitud.executeQuery(query);
    if (!resultado.next()) {
      //
    } else {
      do {
        tipos.add(new Tipo(resultado.getString(1), resultado.getInt(2)));
      } while (resultado.next());
    }
    solicitud.close();
    resultado.close();
    return tipos;
  }

}
