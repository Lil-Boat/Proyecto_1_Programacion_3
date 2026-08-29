package Proyecto01.modelo;

public class PlanVIP extends PlanBase {
    private static final double RECARGO_VIP = 15000.0;

    public PlanVIP() {
        super("VIP", 25000.0, "Acceso a clases especiales, casillero y entrenador personal", 30);
    }

    @Override
    public double calcularPrecio() {
        return precioBase + RECARGO_VIP;
    }
}