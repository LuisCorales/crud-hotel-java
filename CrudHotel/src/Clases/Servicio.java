package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */

public class Servicio {

    private String nombre;

    private String descripcion;

    private float costo;

    private boolean disponible;

    private boolean estadoMatenimiento;

    public Servicio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isEstadoMatenimiento() {
        return estadoMatenimiento;
    }

    public void setEstadoMatenimiento(boolean estadoMatenimiento) {
        this.estadoMatenimiento = estadoMatenimiento;
    }   
    
}
