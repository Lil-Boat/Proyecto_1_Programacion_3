package modelo;

public interface Plan {
    //Calcula el precio final del plan
    //Retorna precio del plan en colones
    double calcularPrecio();

    //Duracion del plan en dias
    //Retorna la duracion del plan en dias, se usa para calcular
    //la fecha de vencimiento de una membresia
    int getDuracionDias();

    //Describe los beneficios que incluye el plan
    //Retorna un String con los beneficios del plan
    String getBeneficios();

    //Nombre del plan
    //Retorna el nombre del plan
    String getNombre();
}
