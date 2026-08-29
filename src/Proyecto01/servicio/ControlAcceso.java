package Proyecto01.servicio;

import Proyecto01.modelo.Membresia;
import Proyecto01.modelo.Usuario;

public class ControlAcceso implements IControlAcceso {
    private final IGestorUsuarios gestorUsuarios;
    private final IGestorMembresias gestorMembresias;

    public ControlAcceso(IGestorUsuarios gestorUsuarios, IGestorMembresias gestorMembresias) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorMembresias = gestorMembresias;
    }

    @Override
    public String verificarAcceso(int numeroUsuario) {
        Usuario usuario = gestorUsuarios.buscarUsuarioPorNumero(numeroUsuario);
        if (usuario == null) return "Socio no encontrado.";
        Membresia membresia = gestorMembresias.buscarMembresiaPorUsuario(usuario);
        if (membresia == null) return "El socio no tiene una membresía activa.";
        return membresia.estaAlDia() ? "Acceso Permitido" : "Acceso Denegado por Morosidad";
    }
}