package Proyecto01.servicio;

import Proyecto01.modelo.Membresia;
import Proyecto01.modelo.Plan;
import Proyecto01.modelo.Usuario;


public interface IGestorMembresia {

    //Asigna un plan a un usuario y cobra el precio del plan
    //Retorna la membresia creada o actualizada
    Membresia asignarPlanYCobrar(Usuario usuario, Plan plan);


    //Registra el pago sobre la membresia del usuario
    void registrarPago(Usuario usuario);

    //Busca la membresia de un usuario
    //Retorna la membresia encontrada o null si no tiene
    Membresia buscarMembresiaPorUsuario(Usuario usuario);

    //Elimina la memebresia asociada a un usuario, si existe
    void eliminarMembresia(Usuario usuario);
}
