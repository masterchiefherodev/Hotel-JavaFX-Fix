package fes.aragon.modelo.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.local.ObjetoControlador;
import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.mysql.Conexion;

public class HotelImpBInterfaz<E> implements IBaseDatos<E> {

  @Override
  public ArrayList<E> consulta() throws Exception {
    String query = "select * from hoteles a,gerentes b where a.id_gre=b.id_gre";
    ArrayList<E> datos = new ArrayList<>();
    Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
    ResultSet resultado = solicitud.executeQuery(query);
    if (!resultado.next()) {
      System.out.println("Sin datos Hotel");
    } else {
      do {
        Hotel ht = new Hotel();
        ht.setId(resultado.getInt(1));
        ht.setNombre(resultado.getString(2));
        ht.setDireccion(resultado.getString(3));
        ht.setCorreo(resultado.getString(4));
        ht.setTelefono(resultado.getString(5));
        Gerente gr = new Gerente();
        gr.setId(resultado.getInt(6));
        gr.setNombre(resultado.getString(8));
        gr.setApellidoPaterno(resultado.getString(9));
        gr.setApellidoMaterno(resultado.getString(10));
        gr.setRfc(resultado.getString(11));
        gr.setCorreo(resultado.getString(12));
        gr.setTelefono(resultado.getString(13));
        ht.setGerente(gr);
        // buscar habitaciones de cada hotel
        try {
          HabitacionImplBInterfaz<Habitacion> cc = new HabitacionImplBInterfaz<>();
          for (Habitacion habitacion : cc.buscarIdHotel(ht.getId())) {
            ht.getHabitaciones().add(habitacion);
          }
        } catch (Exception e) {
          throw new Exception(e.getMessage());
        }

        datos.add((E) ht);
      } while (resultado.next());
    }
    solicitud.close();
    resultado.close();
    return datos;

  }

  @Override
  public ArrayList<E> buscar(String patron) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void insertar(E obj) throws Exception {
    String query = "insert into hoteles(nombre,direccion,correo,telefono,id_gre) values(?,?,?,?,?);";
    Hotel hotelTmp = (Hotel) obj;
    PreparedStatement solicitud = Conexion.getInstancia().getInstancia().getCnn().prepareStatement(query,
        java.sql.Statement.RETURN_GENERATED_KEYS);
    ResultSet rs = null;
    solicitud.setString(1, hotelTmp.getNombre());
    solicitud.setString(2, hotelTmp.getDireccion());
    solicitud.setString(3, hotelTmp.getCorreo());
    solicitud.setString(4, hotelTmp.getTelefono());
    solicitud.setInt(5, hotelTmp.getGerente().getId());
    solicitud.executeUpdate();
    int inc = -1;
    rs = solicitud.getGeneratedKeys();
    if (rs.next()) {
      inc = rs.getInt(1);
      ObjetoControlador.getInstancia().setIdHotel(inc);
    }
    solicitud.close();
  }

  @Override
  public void modificar(E obj) throws Exception {
    String query = "UPDATE hoteles SET nombre=?, direccion=?, correo=?, telefono=? WHERE id_htl=?";
    Hotel hotelTmp = (Hotel) obj;
    PreparedStatement solicitud = Conexion.getInstancia().getInstancia().getCnn().prepareStatement(query);
    solicitud.setString(1, hotelTmp.getNombre());
    solicitud.setString(2, hotelTmp.getDireccion());
    solicitud.setString(3, hotelTmp.getCorreo());
    solicitud.setString(4, hotelTmp.getTelefono());
    solicitud.setInt(5, hotelTmp.getId());
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

  }

  @Override
  public void cerrar() throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void eliminarProc(Integer id) throws Exception {
    String query = "call Eliminar(?)";
    CallableStatement procedimiento = (CallableStatement) Conexion.getInstancia().getCnn().prepareCall(query);
    procedimiento.setInt(1, id);
    procedimiento.execute();
  }

}