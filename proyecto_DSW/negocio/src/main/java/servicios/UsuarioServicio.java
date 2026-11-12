     
//
//package servicios;
//
//import entidades.Usuario;
//import enumeradores.EstadoVerificacion;
//import repositorios.IUsuarioRepositorio;
//import repositorios.UsuarioRepositorio;
//
///**
// * Reglas de negocio de los usuarios: registro, inicio de sesión, perfil y
// * verificación de freelancers.
// *
// * Los errores de regla (correo repetido, datos faltantes...) se avisan con
// * NegocioException.
// *
// * @author Felix_isq
// */
//public class UsuarioServicio {
//
//    private final IUsuarioRepositorio usuarioRepositorio;
//
//    public UsuarioServicio() {
//        this(new UsuarioRepositorio());
//    }
//
//    public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio) {
//        this.usuarioRepositorio = usuarioRepositorio;
//    }
//
//    /**
//     * Registra un usuario nuevo. Valida los datos y que el correo no esté en
//     * uso. Por ahora la contraseña se guarda tal cual (pendiente cifrarla).
//     */
//    public Usuario registrarUsuario(Usuario usuario) {
//        validarDatos(usuario);
//        if (usuarioRepositorio.buscarPorCorreo(usuario.getCorreo()) != null) {
//            throw new NegocioException("Ya existe un usuario con ese correo");
//        }
//        usuarioRepositorio.guardar(usuario);
//        return usuario;
//    }
//
//    /**
//     * Regresa el usuario si el correo y la contraseña coinciden.
//     */
//    public Usuario iniciarSesion(String correo, String contrasenia) {
//        Validaciones.exigirTexto(correo, "El correo");
//        Validaciones.exigirTexto(contrasenia, "La contraseña");
//        Usuario usuario = usuarioRepositorio.buscarPorCorreo(correo);
//        if (usuario == null || !usuario.getContrasenia().equals(contrasenia)) {
//            throw new NegocioException("Correo o contraseña incorrectos");
//        }
//        return usuario;
//    }
//
//    public Usuario obtenerPerfil(Long idUsuario) {
//        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
//        Usuario usuario = usuarioRepositorio.buscarPorId(idUsuario);
//        if (usuario == null) {
//            throw new NegocioException("El usuario no existe");
//        }
//        return usuario;
//    }
//
//    /**
//     * Guarda los cambios del perfil. El correo no puede ser el de otro usuario.
//     */
//    public void actualizarPerfil(Usuario usuario) {
//        validarDatos(usuario);
//        obtenerPerfil(usuario.getId());
//        Usuario otro = usuarioRepositorio.buscarPorCorreo(usuario.getCorreo());
//        if (otro != null && !otro.getId().equals(usuario.getId())) {
//            throw new NegocioException("Ese correo ya pertenece a otro usuario");
//        }
//        usuarioRepositorio.actualizar(usuario);
//    }
//
//    /**
//     * Revisa que el número de cédula tenga el formato correcto (7 u 8 dígitos).
//     * Aquí es donde después se conectaría la consulta al sistema externo de la
//     * Dirección General de Profesiones.
//     */
//    public boolean verificarCedula(String numero) {
//        return numero != null && numero.matches("\\d{7,8}");
//    }
//
//    /**
//     * El usuario pide ser freelancer. Si la cédula es válida se guarda y el
//     * usuario queda VERIFICADO; si no, no se guarda nada y regresa false.
//     */
//    public boolean solicitarVerificacionFreelancer(Long idUsuario, String numeroCedula) {
//        Usuario usuario = obtenerPerfil(idUsuario);
//        if (!verificarCedula(numeroCedula)) {
//            return false;
//        }
//        usuario.setNumeroCedulaProfesional(numeroCedula);
//        usuario.setEstadoVerificacion(EstadoVerificacion.VERIFICADO);
//        usuarioRepositorio.actualizar(usuario);
//        return true;
//    }
//
//    private void validarDatos(Usuario usuario) {
//        Validaciones.exigirNoNulo(usuario, "El usuario");
//        Validaciones.exigirTexto(usuario.getNombres(), "El nombre");
//        Validaciones.exigirTexto(usuario.getApellidoPaterno(), "El apellido paterno");
//        Validaciones.exigirTexto(usuario.getApellidoMaterno(), "El apellido materno");
//        Validaciones.exigirTexto(usuario.getNombreUsuario(), "El nombre de usuario");
//        Validaciones.exigirTexto(usuario.getCorreo(), "El correo");
//        Validaciones.exigirTexto(usuario.getContrasenia(), "La contraseña");
//        Validaciones.exigirTexto(usuario.getTelefono(), "El teléfono");
//        if (!usuario.getCorreo().contains("@")) {
//            throw new NegocioException("El correo no es válido");
//        }
//    }
//}
//
