package modelo;

//Plan de membresia mensual, de duracion corta y sin beneficios adicionales, es el plan mas basico
public class PlanMensual extends PlanBase {

    //Crea un plan mensual con precio y beneficios predefinidos
    public PlanMensual() {
        super("Mensual", 20000.0, "Acceso a todas las maquinas de ejercicio", 30);
    }

    //Se cobra el precio base sin ningun ajuste adicional
    //Retorna precio final del plan mensual en colones
    @Override
    public double calcularPrecio() {
        return precioBase;
    }
}
