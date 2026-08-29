package servicio;

import modelo.Usuario;
import servicio.Repositorio;
import java.util.List;

public class GestorUsuarios implements IGestorUsuario {

    private final Repositorio<Usuario> usuario;
    private final IGestorMembresias gestorMembresias;
    private int siguienteIdUsuario;

    //Crea el gestor de usuarios
    public GestorUsuarios(IGestorMembresias gestorMembresia) {
        this.usuario = new Repositorio<>();
        this.gestorMembresias = gestorMembresia;
        this.siguienteIdUsuario = 1;
    }

    //Registra un nuevo usuario al sistema
    @Override
    public Usuario registrarUsuario(String nombre, int telefono, String correo, String contactoEmergencia, String condicionesMedicas){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        Usuario usuario = new Usuario(siguienteIdUsuario, nombre, telefono, correo, contactoEmergencia, condicionesMedicas);
        this.usuario.agregar(usuario);
        siguienteIdUsuario++;
        return usuario;
    }

    //Elimina un socio del sistema
    @Override
    public void eliminarUsuario(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            gestorMembresias.eliminarMembresia(usuario);
            this.usuario.eliminar(usuario);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuario.obtenerTodos();
    }

    @Override
    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuario.buscar(s -> s.getNumeroUsuario() == idUsuario);
    }
}


