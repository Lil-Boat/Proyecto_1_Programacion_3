package modelo;

//Plan de membresia anual, este aplica descuento sobre 12 pagos
//como incentivo por el compromiso a largo plazo
public class PlanAnual extends PlanBase {

    //Descuento aplicado al precio base del plan anual, se calcula sobre 12 pagos
    private static final double DESCUENTO = 0.15; // 15% de descuento

    public PlanAnual() {
        super ("Anual", 20000.0, "Acceso a todas las maquinas de ejercico y clases grupales",365);
    }

    //Calcula el precio anual aplicando un 15% de descuento
    //sobre el equivalente a 12 meses y retorna el precio final
    //del plan anual
    @Override
    public double calcularPrecio() {
        return precioBase * (1 - DESCUENTO);
    }


}
