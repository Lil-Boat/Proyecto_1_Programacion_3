package servicio;

import modelo.Usuario;
import java.util.List;

public interface IGestorUsuario {

    //Registra un nuevo usuario al sistema
    //Y retorna el usuario creado
    Usuario registrarUsuario(String nombre, int telefono, String correo, String contactoEmergencia, String condicionesMedicas);

    //Elimina un socio del sistema
    void eliminarUsuario(int idUsuario);

    //Lista a todos los usuarios registrados en el sistema
    List<Usuario> listarUsuarios();

    //Busca un usuario por su id
    //Retorna el usuario encontrado o null si no existe
    Usuario buscarUsuarioPorId(int idUsuario);
}
