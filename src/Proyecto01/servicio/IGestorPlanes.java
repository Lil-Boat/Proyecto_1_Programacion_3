package Proyecto01.servicio;

import Proyecto01.modelo.Plan;

import java.util.List;

public interface IGestorPlanes {

    List<Plan> listarPlanes();

    Plan buscarPlanPorNombre(String nombre);
}
