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
public class ListaFecha {
    private NodoFecha inicio, fin;
    
    public ListaFecha(){
        inicio=fin=null;
    }
    
    public boolean esVacia(){
        return inicio==null && fin==null;
    }
    
    public void insertar(Fecha insertada){
        NodoFecha nuevo=new NodoFecha(insertada, fin, null);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            fin.setSiguiente(nuevo);
            fin=nuevo;
        }
    }
    
    public void imprimir(){
        if(esVacia()){
            //
        }else{
            NodoFecha aux=inicio;
            while(aux!=null){
                System.out.println("Anio: "+aux.getDato().getAnio()+" Mes: "+aux.getDato().getMes()+" Dia: "+aux.getDato().getDia());         
                aux=aux.getSiguiente();
            }
        }
    }
}
