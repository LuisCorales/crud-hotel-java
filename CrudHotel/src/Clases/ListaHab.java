/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */
public class ListaHab {
    private NodoHab inicio, fin;

    public ListaHab() {
        inicio=fin=null;
    }
    
    public boolean esVacia(){
        return inicio==null && fin==null;
    }
    
    public void insertar(Habitacion insertada){
        NodoHab nuevo=new NodoHab(insertada, fin, null);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            fin.setSiguiente(nuevo);
            fin=nuevo;
        }
    }
    
    public int contar(){
        if(esVacia()){
            return 0;
        }else{
            NodoHab aux=inicio;
            int cont = 0;
            while (aux!=null){
                cont++;
                aux=aux.getSiguiente();
            }
            return cont;
        }      
    }
    
    public void imprimirDisponibles(){
        if(esVacia()){
            System.out.println("No se han registrado habitaciones disponibles");
        }else{
            NodoHab aux=inicio;
            while(aux!=null){
                if(aux.getDato().getDisponible()){
                    System.out.println("Habitacion numero: "+aux.getDato().getNumero());
                }                
                aux=aux.getSiguiente();
            }
        }
    }
    
    public boolean verDisponibilidad(int numero){
        boolean disponible=false;
        if(esVacia()){
            System.out.println("No se han registrado habitaciones disponibles");
        }else{
            NodoHab aux=inicio;
            while(aux!=null){
                if(aux.getDato().getDisponible() && aux.getDato().getNumero()==numero){
                    disponible=true;
                    break;
                }                
                aux=aux.getSiguiente();
            }
        }
        return disponible;
    }
    
    public Habitacion asginarHabitacion(int numero){
        Habitacion asignada = new Habitacion();
        if(esVacia()){
            System.out.println("No se han registrado habitaciones disponibles");
        }else{
            NodoHab aux=inicio;
            while(aux!=null){
                if(aux.getDato().getNumero()==numero){
                    asignada=aux.getDato();
                    break;
                }                
                aux=aux.getSiguiente();
            }
        }
        return asignada;
    }
    
    public void quitarDispoinibilidad(int numero){
        if(esVacia()){
            System.out.println("No se han registrado habitaciones disponibles");
        }else{
            NodoHab aux=inicio;
            while(aux!=null){
                if(aux.getDato().getNumero()==numero){
                    aux.getDato().setDisponible(false);
                    break;
                }                
                aux=aux.getSiguiente();
            }
        }
    }
    
    public void habilitarDisponibilidad(int numero){
        if(esVacia()){
            System.out.println("No se han registrado habitaciones disponibles");
        }else{
            NodoHab aux=inicio;
            while(aux!=null){
                if(aux.getDato().getNumero()==numero){
                    aux.getDato().setDisponible(true);
                    break;
                }                
                aux=aux.getSiguiente();
            }
        }
    }
}
