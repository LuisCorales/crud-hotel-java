package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */

public class Habitacion {
    
    private String descripcion;

    private int numero;

    private int camas;
    
    private int piso;
    
   private boolean porche;
    
    private boolean esCasa;

    private boolean disponible;

    private boolean balcon;

    private int ocupantesMax;
    
    //private Mantenimiento mantenimientoActual;

    public Habitacion() {
    }

    public Habitacion(int numero, int camas, boolean disponible, boolean balcon, int ocupantesMax) {
        this.numero = numero;
        this.camas = camas;
        this.disponible = disponible;
        this.balcon = balcon;
        this.ocupantesMax = ocupantesMax;
    }

    public Habitacion(String descripcion, int numero, int piso, boolean porche, boolean esCasa, boolean disponible, int ocupantesMax) {
        this.descripcion = descripcion;
        this.numero = numero;
        this.piso = piso;
        this.porche = porche;
        this.esCasa = esCasa;
        this.disponible = disponible;
        this.ocupantesMax = ocupantesMax;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public int getOcupantesMax() {
        return ocupantesMax;
    }

    public void setOcupantesMax(int ocupantesMax) {
        this.ocupantesMax = ocupantesMax;
    }

    @Override
    public String toString(){
        String casa="No", porche="No", balcon="No", disponible="No";
        if(this.esCasa){
            casa="Sí";
            balcon="Sí";
        }
        if(this.piso==1){
            porche="Sí";
        }
        if(this.disponible){
            disponible="Sí";
        }
        return "Numero de habitacion: "+this.numero+"\n"+
                "Es casa: "+casa+"\n"+
                "Balcon: "+balcon+"\n"+
                "Descripción: "+this.descripcion+"\n"+
                "Disponible: "+disponible+"\n"+
                "Capacidad: "+this.ocupantesMax+"\n"+
                "Tiene porche: "+porche+"\n"+
                "Piso: "+this.piso+"\n";
    }
    
}
