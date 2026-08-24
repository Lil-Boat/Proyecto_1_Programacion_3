package Proyecto01.servicio;

import Proyecto01.modelo.Usuario;

import java.util.List;
//ocupo gestor membresias
public interface IGestorSocios {

    Usuario registrarSocio(String nombre, String telefono, String correo,
                         String contactoEmergencia, String condicionesMedicas);

    void eliminarSocio(Usuario usuario);

    List<Usuario> listarSocios();

    Usuario buscarSocioPorNumero(int numeroSocio);

};
