package modelo;

//Implementa la interfaz Plan, es una clase abstracta que sirve como base para los diferentes tipos de planes
public abstract class PlanBase implements Plan {

    protected String nombre; // Nombre del plan
    protected double precioBase; // Precio base del plan, se ajusta de acuerdo a la categoria del plan
    protected String beneficios; // Describe los beneficios incluidos
    protected int duracionDias; // Duracion del plan en dias

    public PlanBase(String nombre, double precioBase, String beneficios, int duracionDias) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.beneficios = beneficios;
        this.duracionDias = duracionDias;
    }

    //Obtiene el nombre del plan y lo retorna
    @Override
    public String getNombre() {
        return nombre;
    }

    //Obtiene los beneficios del plan y los retorna
    @Override
    public String getBeneficios() {
        return beneficios;
    }

    //Obtiene la duracion del plan en dias y la retorna
    @Override
    public int getDuracionDias() {
        return duracionDias;
    }

    //Obtiene el precio del plan y lo retorna
    @Override
    public String toString(){
        return nombre + " (₡" + calcularPrecio() + ")";
    }
}
