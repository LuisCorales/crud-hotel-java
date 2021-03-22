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
public class ListaGasto {
    
    private NodoGasto inicio, fin;
    
    public ListaGasto(){
        inicio=fin=null;
    }
    
    public boolean esVacia(){
        return inicio==null && fin==null;
    }
    
    public void insertar(Gasto insertada){
        NodoGasto nuevo=new NodoGasto(insertada, fin, null);
        if(esVacia()){
            inicio=fin=nuevo;
        }else{
            fin.setSiguiente(nuevo);
            fin=nuevo;
        }
    }
    
    public String imprimir(){   
        String impresionCompleta = "";
        
        if(esVacia()){
            //
        }else{
            NodoGasto aux=inicio;
            while(aux!=null){           
                impresionCompleta+=aux.getDato().toString();
                aux=aux.getSiguiente();
            }
        }
        
        return impresionCompleta;
    }
    
    public String imprimirConsulta(){
        String impresionCompleta = "";
        
        if(esVacia()){
            impresionCompleta="No se han registrado consumos de este tipo";
        }else{
            NodoGasto aux=inicio;
            while(aux!=null){           
                impresionCompleta+=aux.getDato().imprimirConsulta();
                aux=aux.getSiguiente();
            }
        }
        
        return impresionCompleta;
    }
    
    public double getTotal(){
        double total=0;
        NodoGasto aux=inicio;
        while(aux!=null){
            total+=aux.getDato().getPrecio();
            aux=aux.getSiguiente();
        } 
        return total;
    }
    
    public boolean existe(Gasto consumo){
        boolean existe=false;
        NodoGasto aux = inicio;
        while(aux!=null){
            if(aux.getDato().getNombre()==consumo.getNombre()){
                existe=true;
                break;
            }
            aux=aux.getSiguiente();
        }
        return existe;
    }

    
    

}
