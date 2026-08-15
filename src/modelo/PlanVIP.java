package modelo;

//Plan VIP, de mayor costo y con beneficios adicionales al resto de planes
public class PlanVIP extends PlanBase{

    private static final double RECARGO_VIP = 1500.0; // El reccargo adicional para el plan VIP

    //Crea un plan VIP mensual con recargo fijo sobre el precio base
    public PlanVIP() {
        super("VIP", 20000.0, "Acceso a todas las maquinas de ejercico, clases grupales, area de spa y piscina", 30);
    }

    //Calcula el precio del plan VIP sumando el recargo por
    //beneficios adicionales al precio base
    //Retorna precio final del plan VIP
    @Override
    public double calcularPrecio() {
        return precioBase + RECARGO_VIP;
    }
}
