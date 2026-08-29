package Proyecto01.modelo;

public abstract class PlanBase implements Plan {
    protected String nombre;
    protected double precioBase;
    protected String beneficios;
    protected int duracionDias;

    public PlanBase(String nombre, double precioBase, String beneficios, int duracionDias) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.beneficios = beneficios;
        this.duracionDias = duracionDias;
    }

    @Override
    public String getNombre() { return nombre; }
    @Override
    public int getDuracionDias() { return duracionDias; }
    @Override
    public String getBeneficios() { return beneficios; }

    @Override
    public String toString() {
        return nombre + " (₡" + calcularPrecio() + ")";
    }
}


