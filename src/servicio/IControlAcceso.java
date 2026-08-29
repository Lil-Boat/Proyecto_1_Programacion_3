package servicio;

public interface IControlAcceso {

    //Verifica si un usuario puede ingresar al gym
    //Retorna mensaje indicando si esta permitido o no el acceso
    String verificarAcceso(int numeroSocio);
}
