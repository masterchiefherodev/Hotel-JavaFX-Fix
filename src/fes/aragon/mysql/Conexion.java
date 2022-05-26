package fes.aragon.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//	Patron singleton
public class Conexion {
  private String url = "jdbc:mysql://localhost:3306/hotel?serverTimezone=UTC";
  private String usuario = "root";
  private String clave = "Admin12$$$";
  private Connection cnn = null;
  private static Conexion instancia;

  // Solo se puede crear la conexión dentro de la clase
  private Conexion() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    cnn = DriverManager.getConnection(url, usuario, clave);
  }

  public static Conexion getInstancia() throws Exception {
    // Si no existe la conexión llama el constructor de la clase
    if (instancia == null) {
      instancia = new Conexion();
    }
    return instancia;
  }

  // Devuelve la conexión
  public Connection getCnn() {
    return cnn;
  }

  // Cierra la conexión
  public void cerrar() throws SQLException {
    this.cnn.close();
  }

}
