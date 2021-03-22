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
public class ListaMantenimiento {
    private NodoMantenimiento inicio, fin;

    public ListaMantenimiento() {
        inicio=fin=null;
    }

    public void setInicio(NodoMantenimiento inicio) {
        this.inicio = inicio;
    }

    public void setFin(NodoMantenimiento fin) {
        this.fin = fin;
    }

    public NodoMantenimiento getInicio() {
        return inicio;
    }

    public NodoMantenimiento getFin() {
        return fin;
    } 
    
    public boolean esVacia(){
        return inicio==null && fin==null;
    }
    
    public void insertar(Mantenimiento mant){
        NodoMantenimiento nuevo=new NodoMantenimiento(mant, fin, null);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            fin.setDerecha(nuevo);
            fin=nuevo;
        }
    }
    
    public boolean enMantenimiento(Fecha actual, Fecha inicio, Fecha fin){
        boolean mant=false;
        if(actual.getAnio()>=inicio.getAnio() && actual.getAnio()<=fin.getAnio()){
            if(actual.getMes()>=inicio.getMes() && actual.getMes()<=fin.getMes()){
                if(actual.getDia()>=inicio.getDia() && actual.getDia()<=fin.getDia()){
                    mant=true;
                }
            }
        }
        return mant;
    }
    
    public String imprimirConsulta(){
        String impresion="";
        if(esVacia()){
            impresion+="No se han registrado matenimientos especiales";
        }else{
            NodoMantenimiento aux=inicio;
            while(aux!=null){           
                impresion+=aux.getDato().imprimirReporte();
                aux=aux.getDerecha();
            }
           
        }
        return impresion;
    }
    
    @Override
    public String toString(){
        String impresion="";
        if(esVacia()){
            //
        }else{
            NodoMantenimiento aux=inicio;
            while(aux!=null){           
                impresion+=aux.getDato().toString();
                aux=aux.getDerecha();
            }
           
        }
        return impresion;
    }
}
