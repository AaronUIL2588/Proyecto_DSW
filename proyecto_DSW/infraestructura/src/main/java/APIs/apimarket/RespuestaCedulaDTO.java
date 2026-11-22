package APIs.apimarket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Clase DTO para los resultados de la respuesta de la cedula profesional
 * 
 * @author aron
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaCedulaDTO {

    private Boolean success;
    private String codigoValidacion;
    private String message;
    private Integer status;
    private List<CedulaRegistroDTO> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CedulaRegistroDTO> getData() {
        return data;
    }

    public void setData(List<CedulaRegistroDTO> data) {
        this.data = data;
    }
}