package Proyecto01.servicio;

import Proyecto01.modelo.RegistroUsuarios;
import Proyecto01.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios implements IGestorUsuarios {

    private final IGestorMembresias gestorMembresias;
    private final RegistroUsuarios registroUsuarios;
    private int siguienteNumeroUsuario;

    public GestorUsuarios(IGestorMembresias gestorMembresias) {
        this.gestorMembresias = gestorMembresias;
        this.registroUsuarios = RegistroUsuarios.getInstancia();
        this.siguienteNumeroUsuario = registroUsuarios.getUsuarios().stream()
                .mapToInt(Usuario::getNumeroUsuario)
                .max()
                .orElse(0) + 1;
    }

    @Override
    public Usuario registrarUsuario(String nombreCompleto, int edad, String correoElectronico, int telefono,
                                    boolean pagoAlDia, String contactoEmergencia, String condicionesMedicas) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del socio es obligatorio.");
        }

        Usuario usuario = new Usuario(nombreCompleto, edad, correoElectronico, telefono,
                siguienteNumeroUsuario, siguienteNumeroUsuario, pagoAlDia,
                contactoEmergencia, condicionesMedicas);
        registroUsuarios.registrar(usuario);
        siguienteNumeroUsuario++;
        return usuario;
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        gestorMembresias.eliminarMembresiaDeUsuario(usuario);
        registroUsuarios.eliminarPorNumeroUsuario(usuario.getNumeroUsuario());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(registroUsuarios.getUsuarios());
    }

    @Override
    public Usuario buscarUsuarioPorNumero(int numeroUsuario) {
        return registroUsuarios.buscarPorNumeroUsuario(numeroUsuario);
    }


}
