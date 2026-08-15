package Proyecto01.modelo;

import java.util.ArrayList;
import java.util.List;

public class RegistroUsuarios {

    private static RegistroUsuarios instancia;

    private final List<Usuario> usuarios;

    private RegistroUsuarios() {
        usuarios = new ArrayList<>();
        precargarSociosDePrueba();
    }

    public static RegistroUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new RegistroUsuarios();
        }
        return instancia;
    }

    public void registrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public boolean existeNumeroSocio(int numeroSocio) {
        return buscarPorNumeroSocio(numeroSocio) != null;
    }

    // Busca al socio por su número; devuelve null si no existe
    public Usuario buscarPorNumeroSocio(int numeroSocio) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNumeroSocio() == numeroSocio) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Carga algunos socios de ejemplo para poder probar la simulación de acceso
    private void precargarSociosDePrueba() {
        usuarios.add(new Usuario("Maria Gonzalez", 32, "maria.gonzalez@correo.com", 55510101, 101, 1, true));
        usuarios.add(new Usuario("Carlos Perez", 45, "carlos.perez@correo.com", 55520202, 102, 2, false));
        usuarios.add(new Usuario("Lucia Fernandez", 28, "lucia.fernandez@correo.com", 55530303, 103, 3, true));
        usuarios.add(new Usuario("Jorge Ramirez", 51, "jorge.ramirez@correo.com", 55540404, 104, 4, false));
    }
}