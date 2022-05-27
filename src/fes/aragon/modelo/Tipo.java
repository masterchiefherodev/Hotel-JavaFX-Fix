package fes.aragon.modelo;

public class Tipo {
  private String tipo;
  private Integer idTipo;

  public Tipo(String tipo, Integer idTipo) {
    this.tipo = tipo;
    this.idTipo = idTipo;
  }

  public Tipo() {
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Integer getIdTipo() {
    return idTipo;
  }

  public void setIdTipo(Integer idTipo) {
    this.idTipo = idTipo;
  }

  @Override
  public String toString() {
    return tipo;
  }

}
