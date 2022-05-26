package fes.aragon.modelo;

import java.util.regex.Pattern;

public class VerificadorStrings {

  public static boolean verificarNombre(String nombreParam) {
    String nombre = nombreParam;
    if (nombre == null || nombre.equals(null) || nombre.isEmpty() || nombre.isBlank()) {
      return false;
    }
    nombre = nombre.trim();
    nombre = nombre.replaceAll(" ", "");
    if (nombre.length() < 2) {
      return false;
    } else if (!nombre.matches("[a-zA-Z0-9]*")) {
      return false;
    }
    return true;
  }

  public static boolean verificarDireccion(String dirParam) {
    String dir = dirParam;
    if (dir == null || dir.equals(null) || dir.isEmpty() || dir.isBlank()) {
      return false;
    }
    dir = dir.trim();
    dir = dir.replaceAll(" ", "");
    if (dir.length() < 5) {
      return false;
    } else if (!dir.matches("[a-zA-Z0-9]*")) {
      return false;
    }
    return true;
  }

  public static boolean verificarCorreo(String correoParam) {
    if (correoParam == null || correoParam.equals(null) || correoParam.isEmpty() || correoParam.isBlank()) {
      return false;
    }
    String correo = correoParam.trim();
    final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    return EMAIL_REGEX.matcher(correo).matches();
  }

  public static boolean verificarTelefono(String telParam) {
    if (telParam == null || telParam.equals(null) || telParam.isEmpty() || telParam.isBlank()) {
      return false;
    }
    if (telParam.length() != 10) {
      return false;
    } else if (!telParam.matches("[0-9]+")) {
      return false;
    }
    return true;
  }

  public static boolean verificarRFC(String rfcParam) {
    return true;
  }

}
