package APIs.apimarket;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase que opera con el sistema externo para consultar la cedula profesional
 * 
 * @author aron
 */
public class CedulaProfesionalAPI{

    private static final String API_URL =
            "https://apimarket.mx/api/sep/grupo/validar-cedula";

    private final HttpClient cliente;
    private final ObjectMapper mapper;

    public CedulaProfesionalAPI() {
        this.cliente = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
        
        this.mapper = new ObjectMapper();
    }

        public RespuestaCedulaDTO consultarCedula(String numero)
            throws IOException, InterruptedException {

        String token = System.getenv("API_MARKET_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                    "No se encontró la variable API_MARKET_TOKEN."
            );
        }

        if (numero == null || !numero.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "El número de cédula debe contener solamente dígitos."
            );
        }

        String cedula = URLEncoder.encode(
                numero,
                StandardCharsets.UTF_8
        );

        String url = API_URL + "?cedula=" + cedula;

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(30))
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .header("x-sandbox", "true")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> respuesta = cliente.send(
                peticion,
                HttpResponse.BodyHandlers.ofString()
        );

        if (respuesta.statusCode() < 200
                || respuesta.statusCode() >= 300) {
            throw new IOException(
                    "Error HTTP " + respuesta.statusCode()
                    + ": " + respuesta.body()
            );
        }

        return mapper.readValue(
                respuesta.body(),
                RespuestaCedulaDTO.class
        );
    }
}