package servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Repositorio<T> {

    private final List<T> elementos;

    //Crea un repositorio vacio
    public Repositorio(){
        this.elementos = new ArrayList<>();
    }

    //Agrega un elemento a la lista de elementos
    public void agregar(T elemento){
        elementos.add(elemento);
    }

    //Elimina un elemento de la lista de elementos
    public boolean eliminar(T elemento){
        return elementos.remove(elemento);
    }

    //Retorna una lista con todos los elementos del repositorio
    public List<T> obtenerTodos(){
        return elementos;
    }

    //Filtra los elementos del repositorio
    //retorna el elemento o null si no cumple el criterio
    public T buscar(Predicate <T> criterio){
        for(T elemento : elementos){
            if(criterio.test(elemento)) {
                return elemento;
            }
        }
        return null;
    }

    //Filtrar los elementos que cumplen con el criterio
    //retorna una lista con los elementos que cumplan los criterios
    public List<T> filtrar(Predicate <T> criterio){
        List<T> resultado = new ArrayList<>();
        for(T elemento : elementos){
            if(criterio.test(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }

    //Retorna la cantidad de elementos en el repositorio
    public int cantidad(){
        return elementos.size();
    }
}
