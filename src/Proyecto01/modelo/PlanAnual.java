package Proyecto01.modelo;

public class PlanAnual extends PlanBase {

    private static final double DESCUENTO = 0.15;

    public PlanAnual() {
        super("Anual", 25000.0 * 12, "Acceso a sala de pesas, cardio y clases grupales", 365);
    }

    @Override
    public double calcularPrecio() {
        return precioBase * (1 - DESCUENTO);
    }

}