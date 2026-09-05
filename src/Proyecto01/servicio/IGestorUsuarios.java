package Proyecto01.servicio;

import Proyecto01.modelo.Usuario;

import java.util.List;

//ocupo gestor membresias
public interface IGestorUsuarios {

    //Registra un nuevo socio; el número de socio y el ID se asignan automáticamente.
    //Retorna el socio creado.
    Usuario registrarUsuario(String nombreCompleto, int edad, String correoElectronico, int telefono,
                             boolean pagoAlDia, String contactoEmergencia, String condicionesMedicas);

    void eliminarUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorNumero(int numeroUsuario);

}
