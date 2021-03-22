package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */

public class Gasto {

    private String nombre;

    private double precio;

    private Fecha fecha;

    public Gasto() {
    }

    public Gasto(String nombre, double precio, Fecha fecha) {
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String imprimirConsulta(){
        return "Descripcion: "+this.nombre+", Valor: "+this.precio+", Fecha: "+this.fecha+"; ";
    }
    
    @Override
    public String toString() {
        return nombre + "," + precio + "," + fecha + ";";
    }
      
}
