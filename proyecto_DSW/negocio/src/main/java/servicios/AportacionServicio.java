package servicios;

import Builders.AportacionBuilder;
import entidades.Aportacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoProyecto;
import java.util.List;
import repositorios.AportacionRepositorio;
import interfaces.IAportacionRepositorio;
import interfaces.IAportacionServicio;
import interfaces.IProyectoRepositorio;
import interfaces.IUsuarioRepositorio;
import repositorios.ProyectoRepositorio;
import repositorios.UsuarioRepositorio;

/**
 * Clase de servicio para los objetos Aportacion, el cual realiza las
 * operaciones necesarias para trabajar con ellas en la capa de Presentación.
 *
 * @author Felix_isq
 */
public class AportacionServicio implements IAportacionServicio {

    /**
     * Comisión de la plataforma sobre cada aportación: 5 %.
     */
    public static final double PORCENTAJE_COMISION = 0.05;

    /**
     * Interfaces de los repositorios con los que trabajará esta clase
     */
    private final IAportacionRepositorio aportacionRepositorio;
    private final IProyectoRepositorio proyectoRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;

    /**
     * Constructor vacio que inicializa los repositorios necesarios para el
     * funcionamiento de esta misma clase.
     */
    public AportacionServicio() {
        this(new AportacionRepositorio(), new ProyectoRepositorio(), new UsuarioRepositorio());
    }

    /**
     * Constructor con parametros, utilizado por el constructor vacio, para
     * inicializar los repositorios necesarios para esta clase.
     * 
     * @param aportacionRepositorio Interfaz de la clase AportacionRepositorio
     * @param proyectoRepositorio Interfaz de la clase ProyectoRepositorio
     * @param usuarioRepositorio Interfaz de la clase UsuarioRepositorio
     */
    public AportacionServicio(IAportacionRepositorio aportacionRepositorio,
            IProyectoRepositorio proyectoRepositorio,
            IUsuarioRepositorio usuarioRepositorio) {
        this.aportacionRepositorio = aportacionRepositorio;
        this.proyectoRepositorio = proyectoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Método que calcula la comisión al hacer aportaciones. La comisión (5 %)
     * de un monto, es redondeada a centavos. Sirve para mostrarse justo antes
     * de confirmar.
     * 
     * @param monto valor numerico que representa el monto de la aportacion
     * @return valor del monto con la comisión del porgrama aplicada
     */
    @Override
    public double calcularComision(double monto) {
        return Math.round(monto * PORCENTAJE_COMISION * 100.0) / 100.0;
    }

    /**
     * Método que registra la aportación de un usuario a un proyecto de otro
     * usuario. El proyecto debe estar activo. Aquí es donde se haría el cobro
     * con el sistema bancario antes de guardar.
     * 
     * @param idUsuario valor numerico que representa el ID del usuario al que le pertenece la aportacion
     * @param idProyecto valor numerico que representa el ID del proyecto al que se le hara la aportacion
     * @param monto valor numerico que representa el monto de la aportacion
     * @return valor del tipo Aportacion con los datos de la aportacion, al procesar correctamente la aportacion
     */
    @Override
    public Aportacion donar(Long idUsuario, Long idProyecto, double monto) {
        // Se realizan validaciones antes de procesar la aportacion
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        if (monto <= 0) {
            throw new NegocioException("El monto debe ser mayor a cero");
        }
        Usuario usuario = usuarioRepositorio.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new NegocioException("El usuario no existe");
        }
        Proyecto proyecto = proyectoRepositorio.buscarPorId(idProyecto);
        if (proyecto == null) {
            throw new NegocioException("El proyecto no existe");
        }
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se puede aportar a proyectos activos");
        }
        if (proyecto.getUsuario().getId().equals(idUsuario)) {
            throw new NegocioException("No puedes aportar a tu propio proyecto");
        }
        
        // Si las validaciones pasaron sin ningun error, se crea un objeto nuevo
        // del tipo Aportacion, haciendo uso de la clase de AportacionBuilder
        Aportacion aportacion = new AportacionBuilder()
                .monto(monto)
                .comisionPlataforma(calcularComision(monto))
                .usuario(usuario)
                .proyecto(proyecto)
                .build();
        // Se guarda la aportacion en la base de datos y la misma se devuelve
        aportacionRepositorio.guardar(aportacion);
        return aportacion;
    }

    /**
     * Método para obtener la lista de las aportaciones, agrupadas por proyecto.
     * Utiliza el ID del proyecto para la busqueda.
     * 
     * @param idProyecto valor numerico que representa el ID del proyecto
     * @return Lista de aportaciones del proyecto buscado
     */
    @Override
    public List<Aportacion> listarPorProyecto(Long idProyecto) {
        // Se realiza la validacion del ID del proyecto
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        // Se realiza la busqueda de las aportaciones directa en la base de datos
        return aportacionRepositorio.listarPorProyecto(idProyecto);
    }

    /**
     * Método para obtener la lista de las aportaciones, agrupadas por usuario.
     * Utiliza el ID del usuario para la busqueda.
     * 
     * @param idUsuario valor nummerico que representa el ID del usuario
     * @return Lista de aportaciones del usuario buscado
     */
    @Override
    public List<Aportacion> listarPorUsuario(Long idUsuario) {
        // Se realiza la validacion del ID del usuario
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        // Se realiza la busqueda de las aportaciones directa en la base de datos
        return aportacionRepositorio.listarPorUsuario(idUsuario);
    }
}
