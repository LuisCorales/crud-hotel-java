/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 *
 * @author diego
 */
public class Grafo {
    private double matriz[][];
    private ArrayList<Nodo> nodos;

    public Grafo() {
    }

    public Grafo(double[][] matriz) {
        this.matriz = matriz;       
    }
    
    public Grafo(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matriz[i][j]=Double.MAX_VALUE;
            }
        }
    }
    
    public void presetDistancias(int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                matriz[i][j]=Infinity;
            }
        }
    }

    public Grafo(double[][] matriz, ArrayList<Nodo> nodos) {
        this.matriz = matriz;
        this.nodos = nodos;
    }

    public Grafo(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }
    
    public int size(){
            return matriz.length;
    }
    
    //peso o factor de peso
    public double distancia(int a, int b){
        return matriz[a][b];
    }
    
    public void addArista(Arista arista) {//Creacion de la matriz de distancias
        matriz[vertice(arista.getOrig().getNombre())][vertice(arista.getDest().getNombre())]=arista.getDistancia();//Si esto habilitado es bidereccional
        matriz[vertice(arista.getDest().getNombre())][vertice(arista.getOrig().getNombre())]=arista.getDistancia();
    }
    
    public ArrayList<Nodo> getVecinos(Nodo nodo){//Aniade a una lista los todas los pocisiones (i) de la matriz en las que la arista n tiene conexion
        ArrayList<Nodo> vecinos = new ArrayList<Nodo>();
        for(Arista arista : nodo.getAristas()){
            vecinos.add(arista.getDest());
        }
        return vecinos;
    }
    
    
    public Grafo(int n, Arista[] arr){//numero de elementos y arreglo de aristas
        this(n);//LLamado al constructor que utiliza n, da lo mismo que llamar a Grafo(n)
        for(Arista arista : arr){//for todos los elementos en el arreglo de aristas arr, se aniade la arista
            addArista(arista);
        }
    } 
    
    public Grafo dijkstra(Grafo grafo, Nodo partida){
        ArrayList<Nodo> camino = new ArrayList<Nodo>();
        ArrayList<Nodo> procesados = new ArrayList<Nodo>();
        ArrayList<Nodo> pendientes = new ArrayList<Nodo>();
        partida.setDistancia(0d);
        pendientes.add(partida);
        while(!pendientes.isEmpty()){
            Nodo actual = cortoPartida(pendientes);
            pendientes.remove(actual);
            for(Arista arista : actual.getAristas()){
                Nodo vecino = arista.getDest();
                double distancia = arista.getDistancia();
                if(!procesados.contains(vecino)){
                    calcularDistancia(vecino, distancia, actual);
                    pendientes.add(vecino);
                }
            }
            procesados.add(actual);          
        }             
        return grafo;
    }
    
    public Nodo cortoPartida(ArrayList<Nodo> pendientes){
        Nodo cercano = null;
        double min = Double.MAX_VALUE;
        for(Nodo nodo : pendientes){
            double distancia = nodo.getDistancia();
            if(distancia < min){
                min = distancia;
                cercano=nodo;
            }
        }
        return cercano;
    }
    
    public void calcularDistancia(Nodo vecino, double distancia, Nodo actual){
        double distanciaOrigen = actual.getDistancia();
        if(distanciaOrigen + distancia < vecino.getDistancia()){
            vecino.setDistancia(distanciaOrigen + distancia);
            ArrayList<Nodo> corto = new ArrayList<>(actual.getCamino());
            corto.add(actual);
            vecino.setCamino(corto);
        }
    }
    
    public ArrayList<String> camino(Nodo fin){
        ArrayList<String> camino = new ArrayList<String>();
        for(Nodo nodo : fin.getCamino()){
            camino.add(nodo.getNombre());
        }
        return camino;
    }
    
    public int vertice(String nombre){
        int id = 0;
        Map nombres = new HashMap();
        nombres.put("Entrada", 0);
        nombres.put("Camino pto. A", 1);
        nombres.put("Parqueadero", 2);
        nombres.put("Recepción", 3);
        nombres.put("Sala común", 4);
        nombres.put("Zona de parasoles", 5);
        nombres.put("Bar", 6);
        nombres.put("Piscina", 7);
        nombres.put("Duchas", 8);
        nombres.put("Camino pto. B", 9);
        nombres.put("Habs. 1-4, 21-24", 10);
        nombres.put("Habs. 5-7, 25, 26", 11);
        nombres.put("Habs. 8-10, 27, 28", 12);
        nombres.put("Casa 11", 13);
        nombres.put("Casas 12-14", 14);
        id = (Integer) nombres.get(nombre);
        return id;
    }
    
    /*public ArrayList<String> dijkstra(int origen, int dest){
        ArrayList<String> vertices = new ArrayList<String>();
        ArrayList<Arista> aristas = new ArrayList<Arista>();
        ArrayList<Arista> caminos = new ArrayList<Arista>();
        ArrayList<Integer> destinos = new ArrayList<Integer>();
        double min = Double.MAX_VALUE;
        int orig = origen;
        Arista quitar=new Arista();
        for(int i = 0; i < this.size() ; i++){//El arlogirtmo se repite n veces, n es tamaño de matriz
            if(i==0){
                destinos.addAll(this.getVecinos(orig));
                for(Integer destino : destinos){
                    caminos.add(new Arista(orig, destino, this.distancia(orig, destino)));            
                }
                for(Arista camino : caminos){
                    if(camino.getDistancia()<min){                 
                        min=camino.getDistancia();
                        quitar=camino;                        
                    }
                }
                caminos.remove(quitar);
                 aristas.add(new Arista(quitar.getOrig(), quitar.getDest(), 
                        this.distancia(quitar.getOrig(), quitar.getDest())));
                min = Double.MAX_VALUE;
                for(Arista camino : caminos){
                    if(camino.getDistancia()<min){
                        orig=camino.getOrig();
                        min=camino.getDistancia();
                    }
                }
                min = Double.MAX_VALUE;                
            }else{//A partir de la segunda iteracion hay cambios 
                destinos.removeAll(destinos);
                destinos.addAll(this.getVecinos(orig));
                for(Integer destino : destinos){
                    if(caminos.contains(new Arista(orig, destino, this.distancia(orig, destino)))){
                        caminos.add(new Arista(orig, destino, this.distancia(orig, destino)));
                    }else{
                        Arista minimo = new Arista();
                        for(Arista camino : caminos){
                            if(camino.getDistancia()<min){
                                minimo=camino;
                                min=camino.getDistancia();
                            }
                        }
                        caminos.add(new Arista(orig, destino, 
                                this.distancia(orig, destino)+minimo.getDistancia()));
                    }                                
                }
                min = Double.MAX_VALUE; 
                for(Arista camino : caminos){
                    if(camino.getDistancia()<min){
                        min=camino.getDistancia();
                        quitar=camino;
                    }
                }
                caminos.remove(quitar);
                 aristas.add(new Arista(quitar.getOrig(), quitar.getDest(), 
                        this.distancia(quitar.getOrig(), quitar.getDest())));
                min = Double.MAX_VALUE;
                for(Arista camino : caminos){
                    if(camino.getDistancia()<min){
                        orig=camino.getOrig();
                        min=camino.getDistancia();
                    }
                }
                min = Double.MAX_VALUE;
            }
            if(caminos.get(caminos.size()-1).getDest()==dest){
                break;
            }
        }
        vertices=this.nombres(aristas, origen);
        return vertices;
    }
    
    public ArrayList<String> nombres(ArrayList<Arista> aristas, int origen){
        int actual=origen;
        ArrayList<String> vertices = new ArrayList<String>();
        vertices.add(vertice(aristas.get(aristas.size()-1).getDest()));
        vertices.add(vertice(aristas.get(aristas.size()-1).getOrig()));
        actual=aristas.get(aristas.size()-1).getOrig();
        for(Arista arista : aristas){
            if(actual==arista.getOrig()){
                actual=arista.getOrig();
                vertices.add(vertice(arista.getOrig()));
            }
        }
        return vertices;
    }*/
    
}
