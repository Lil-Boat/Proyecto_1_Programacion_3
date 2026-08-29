package Proyecto01.modelo;

public class PlanMensual extends PlanBase {

    public PlanMensual() {
        super("Mensual", 25000.0, "Acceso a sala de pesas y cardio", 30);
    }

    @Override
    public double calcularPrecio() {
        return precioBase;
    }

}