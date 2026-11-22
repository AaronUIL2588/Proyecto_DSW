package APIs.apimarket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Clase DTO para los datos de la cedula profesional
 * 
 * @author aron
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CedulaRegistroDTO {

    private String idCedula;
    private String tipo;
    private String nombre;
    private String paterno;
    private String materno;
    private String titulo;
    private String desins;
    private Integer anioreg;

    public String getIdCedula() {
        return idCedula;
    }

    public void setIdCedula(String idCedula) {
        this.idCedula = idCedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesins() {
        return desins;
    }

    public void setDesins(String desins) {
        this.desins = desins;
    }

    public Integer getAnioreg() {
        return anioreg;
    }

    public void setAnioreg(Integer anioreg) {
        this.anioreg = anioreg;
    }
}