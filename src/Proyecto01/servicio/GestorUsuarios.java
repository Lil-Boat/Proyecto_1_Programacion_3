package Proyecto01.servicio;

import Proyecto01.modelo.Usuario;

import java.util.List;

public class GestorUsuarios implements IGestorUsuarios {

    private final Repositorio<Usuario> usuarios;
    private final IGestorMembresias gestorMembresias;
    private int siguienteNumeroUsuario;

    public GestorUsuarios(IGestorMembresias gestorMembresias) {
        this.usuarios = new Repositorio<>();
        this.gestorMembresias = gestorMembresias;
        this.siguienteNumeroUsuario = 1;
    }

    @Override
    public Usuario registrarUsuario(String nombreCompleto, int edad, String correoElectronico, int telefono,
                                    boolean pagoAlDia, String contactoEmergencia, String condicionesMedicas) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del socio es obligatorio.");
        }
        // El número de socio y el ID se asignan automáticamente en forma consecutiva
        Usuario usuario = new Usuario(nombreCompleto, edad, correoElectronico, telefono,
                siguienteNumeroUsuario, siguienteNumeroUsuario, pagoAlDia,
                contactoEmergencia, condicionesMedicas);
        usuarios.agregar(usuario);
        siguienteNumeroUsuario++;
        return usuario;
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        gestorMembresias.eliminarMembresiaDeUsuario(usuario);
        usuarios.eliminar(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarios.obtenerTodos();
    }

    @Override
    public Usuario buscarUsuarioPorNumero(int numeroUsuario) {
        return usuarios.buscar(s -> s.getNumeroUsuario() == numeroUsuario);
    }


}
