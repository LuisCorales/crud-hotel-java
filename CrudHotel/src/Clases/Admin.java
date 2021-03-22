package Clases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */

public class Admin {

    private Fecha fecha=new Fecha();

    private int[] numerosHabitaciones;

    private Reserva reserva=new Reserva();

    private Huesped huesped = new Huesped();
    
    private ListaHab habitaciones = new ListaHab();

    public Admin() {
    }  

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public int[] getNumerosHabitaciones() {
        return numerosHabitaciones;
    }

    public void setNumerosHabitaciones(int[] numerosHabitaciones) {
        this.numerosHabitaciones = numerosHabitaciones;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.reserva.setHuesped(huesped);
    }
    
    public void setHabitacionActual(int actual){
        this.reserva.setHabitacionActual(actual);
    }
    
    public void setHabitacionOriginal(int original){
        this.reserva.setHabitacionActual(original);
    }
    
    public void setMetodoPago(String metodo){
        this.reserva.setMetodoPago(metodo);
    }
    
    public void setNinios(int ninios){
        this.reserva.setNinios(ninios);
    }
    
    public void setAdultos(int adultos){
        this.reserva.setNinios(adultos);
    }
    
    /*public boolean existeConsumo(Gasto consumo){
        return this.reserva.getConsumos().existe(consumo);
    }*/
    
    public void cambioHabitacion(){
        this.reserva.setCambioHabitacion(true);
    }

    /*public Mantenimiento[] historialMantenimiento(int habitacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

    
    /*public Mantenimiento mantenimientoActual(int habitacion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
    
    public void setFechaInicio(Fecha inicio){
        reserva.setInicio(inicio);
    }
    
    public Fecha getFechaInicio(){
        return reserva.getInicio();
    }  
    
    public void setFechaFin(Fecha fin){
        reserva.setFin(fin);
    }
    
    public Fecha getFechaFin(){
        return reserva.getFin();
    }
    
    public boolean cedula(String cedula){//Verifica que la cedula o pasaporte ecuatorinos sean corrrectos
        boolean provincia=false, verif=false, valida=false;
        String dosDig = cedula.substring(0, 1);
        
        if(Integer.parseInt(dosDig)>0&&Integer.parseInt(dosDig)<=24){
            provincia = true;
        }
        
        int sumaP = Character.getNumericValue(cedula.charAt(1))+Character.getNumericValue(cedula.charAt(3))+
                Character.getNumericValue(cedula.charAt(5))+Character.getNumericValue(cedula.charAt(7));
        
        int sumaI=0;   
        for(int i=0; i<=8; i+=2){
            if(Character.getNumericValue(cedula.charAt(i))*2>9){
                sumaI+=(Character.getNumericValue(cedula.charAt(i))*2-9);
            }else{
                sumaI+=Character.getNumericValue(cedula.charAt(i))*2;
            }
        }
        
        int sumaT = sumaP+sumaI;
        
        if(sumaT<=10){
            sumaT=10-sumaT;
        }else if(sumaT<=20){
            sumaT=20-sumaT;
        }else if(sumaT<=30){
            sumaT=30-sumaT;
        }else if(sumaT<=40){
            sumaT=40-sumaT;
        }else if(sumaT<=50){
            sumaT=50-sumaT;
        }else if(sumaT<=60){
            sumaT=60-sumaT;
        }else if(sumaT<=70){
            sumaT=70-sumaT;
        }else if(sumaT<=80){
            sumaT=80-sumaT;
        }else if(sumaT<=90){
            sumaT=90-sumaT;
        }
        
        if(sumaT==Character.getNumericValue(cedula.charAt(9))){
            verif=true;
        }
        
        if(provincia&&verif){
            valida=true;
        }
        
        return valida;
    }

    public ListaHab getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ListaHab habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    public void sugerencias(){
        
    }
    
    public int diasEstadia(){
        return this.reserva.diasEstadia();
    }
    
    public void aumentarDanios(double valor){
        this.reserva.aumentarDanios(valor);
    }
    
    public int diasEstadia(Fecha fin, Fecha inicio){
        String mesI="", diaI="", mesF="", diaF="";
        if(inicio.getMes()<10){
            mesI="0"+inicio.getMes();
        }else{
            mesI=""+inicio.getMes();
        }
        if(inicio.getDia()<10){
            diaI="0"+inicio.getDia();
        }
        else{
            diaI=""+inicio.getDia();
        }
        if(fin.getMes()<10){
            mesF="0"+fin.getMes();
        }else{
            mesF=""+fin.getMes();
        }
        if(fin.getDia()<10){
            diaF="0"+fin.getDia();
        }
        else{
            diaF=""+fin.getDia();
        }
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        Date ini = new Date(), fi = new Date();
        try {
            ini = formato.parse(String.valueOf(inicio.getAnio())+"-"+mesI+"-"+diaI);
            fi = formato.parse(String.valueOf(fin.getAnio())+"-"+mesF+"-"+diaF);
        } catch (ParseException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }       
        long tiempo=Math.abs(fi.getTime()-ini.getTime());
        int dias = Integer.parseInt(String.valueOf(TimeUnit.DAYS.convert(tiempo, TimeUnit.MILLISECONDS)));
        return dias;
    }
    
    public List<String> habDisponiblesRe(Fecha inicia, Fecha finaliza){
        Fecha inicio = new Fecha(inicia.getDia(), inicia.getMes(), inicia.getAnio()), fin = new Fecha(finaliza.getDia(), finaliza.getMes(), finaliza.getAnio());
        List<String> disponibles = new ArrayList<String>();
        int habs[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,21,22,23,24,25,26,27,28};
        for(int i = 0; i<habs.length; i++){
            disponibles.add(String.valueOf(habs[i]));
        }
        try{
            File[] activas;
            File folder = new File("PorteroAdminHotel\\Activas\\");//C:\\PorteroAdminHotel\\Activas\\
            activas = folder.listFiles();
            for (File reserva : activas) {
                if (reserva.isFile()) {
                    String[] sinTxt = reserva.getName().split("[.]");
                    String[] archivos = sinTxt[0].split("_");
                    String[] ini = archivos[1].split("-"), fi = archivos[2].split("-");      
                    Fecha inicioRe = new Fecha(Integer.parseInt(ini[0]), Integer.parseInt(ini[1]), Integer.parseInt(ini[2])), 
                            finRe = new Fecha(Integer.parseInt(fi[0]), Integer.parseInt(fi[1]), Integer.parseInt(fi[2]));
                    SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
                    String mes="";
                    String dia="";
                    if(inicio.getMes()<10){
                        mes="0"+inicio.getMes();
                    }else{
                        mes=""+inicio.getMes();
                    }
                    if(inicio.getDia()<10){
                        dia="0"+inicio.getDia();
                    }
                    else{
                        dia=""+inicio.getDia();
                    }
                    Date inicioN = formato.parse(String.valueOf(inicio.getAnio())+"-"+mes+"-"+dia);
                    if(fin.getMes()<10){
                        mes="0"+fin.getMes();
                    }else{
                        mes=""+fin.getMes();
                    }
                    if(fin.getDia()<10){
                        dia="0"+fin.getDia();
                    }else{
                        dia=""+fin.getDia();
                    }
                    Date finN = formato.parse(String.valueOf(fin.getAnio())+"-"+mes+"-"+dia);
                    if(inicioRe.getMes()<10){
                        mes="0"+inicioRe.getMes();
                    }else{
                        mes=""+inicioRe.getMes();
                    }
                    if(inicioRe.getDia()<10){
                        dia="0"+inicioRe.getDia();
                    }else{
                        dia=""+inicioRe.getDia();
                    }
                    Date inicioE = formato.parse(String.valueOf(inicioRe.getAnio())+"-"+mes+"-"+dia);
                    if(finRe.getMes()<10){
                        mes="0"+finRe.getMes();
                    }else{
                        mes=""+finRe.getMes();
                    }
                    if(finRe.getDia()<10){
                        dia="0"+finRe.getDia();
                    }else{
                        dia=""+finRe.getDia();
                    }
                    Date finE = formato.parse(String.valueOf(finRe.getAnio())+"-"+mes+"-"+dia);
                    if((inicioN.compareTo(finE)<0 && finN.compareTo(inicioE)>0) || (inicioN.compareTo(inicioE)==0&&finN.compareTo(finE)==0)){
                        disponibles.remove(archivos[0]);
                    }                                                   
                }
            }
        }catch(Exception e){
            //
        }
            
        return disponibles;
    }
    
    public List<String> habDisponiblesRe(Fecha inicia, Fecha finaliza, File ignorar){
        Fecha inicio = new Fecha(inicia.getDia(), inicia.getMes(), inicia.getAnio()), fin = new Fecha(finaliza.getDia(), finaliza.getMes(), finaliza.getAnio());
        List<String> disponibles = new ArrayList<String>();
        int habs[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,21,22,23,24,25,26,27,28};
        for(int i = 0; i<habs.length; i++){
            disponibles.add(String.valueOf(habs[i]));
        }
        try{
            File[] activas;
            File folder = new File("PorteroAdminHotel\\Activas\\");//C:\\PorteroAdminHotel\\Activas\\
            activas = folder.listFiles();
            for (File reserva : activas) {
                if (reserva.isFile() && reserva.compareTo(ignorar)!=0) {
                    String[] sinTxt = reserva.getName().split("[.]");
                    String[] archivos = sinTxt[0].split("_");
                    String[] ini = archivos[1].split("-"), fi = archivos[2].split("-");      
                    Fecha inicioRe = new Fecha(Integer.parseInt(ini[0]), Integer.parseInt(ini[1]), Integer.parseInt(ini[2])), 
                            finRe = new Fecha(Integer.parseInt(fi[0]), Integer.parseInt(fi[1]), Integer.parseInt(fi[2]));
                    SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
                    String mes="";
                    String dia="";
                    if(inicio.getMes()<10){
                        mes="0"+inicio.getMes();
                    }else{
                        mes=""+inicio.getMes();
                    }
                    if(inicio.getDia()<10){
                        dia="0"+inicio.getDia();
                    }
                    else{
                        dia=""+inicio.getDia();
                    }
                    Date inicioN = formato.parse(String.valueOf(inicio.getAnio())+"-"+mes+"-"+dia);
                    if(fin.getMes()<10){
                        mes="0"+fin.getMes();
                    }else{
                        mes=""+fin.getMes();
                    }
                    if(fin.getDia()<10){
                        dia="0"+fin.getDia();
                    }else{
                        dia=""+fin.getDia();
                    }
                    Date finN = formato.parse(String.valueOf(fin.getAnio())+"-"+mes+"-"+dia);
                    if(inicioRe.getMes()<10){
                        mes="0"+inicioRe.getMes();
                    }else{
                        mes=""+inicioRe.getMes();
                    }
                    if(inicioRe.getDia()<10){
                        dia="0"+inicioRe.getDia();
                    }else{
                        dia=""+inicioRe.getDia();
                    }
                    Date inicioE = formato.parse(String.valueOf(inicioRe.getAnio())+"-"+mes+"-"+dia);
                    if(finRe.getMes()<10){
                        mes="0"+finRe.getMes();
                    }else{
                        mes=""+finRe.getMes();
                    }
                    if(finRe.getDia()<10){
                        dia="0"+finRe.getDia();
                    }else{
                        dia=""+finRe.getDia();
                    }
                    Date finE = formato.parse(String.valueOf(finRe.getAnio())+"-"+mes+"-"+dia);                
                    if((inicioN.compareTo(finE)<0 && finN.compareTo(inicioE)>0) || (inicioN.compareTo(inicioE)==0&&finN.compareTo(finE)==0)){
                        disponibles.remove(archivos[0]);
                    }                                       
                }
            }
            
        }catch(Exception e){
            //
        }
            
        return disponibles;
    }
    
    public List<String> habMantenimiento(Fecha inicia, Fecha finaliza){
        Fecha inicio = new Fecha(inicia.getDia(), inicia.getMes(), inicia.getAnio()), fin = new Fecha(finaliza.getDia(), finaliza.getMes(), finaliza.getAnio());
        List<String> quitar = new ArrayList<String>();
        try{           
            File archivo = new File("PorteroAdminHotel\\Mantenimientos.txt");//C:\\PorteroAdminHotel\\Mantenimientos.txt           
            String historial="";
            int habs[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,21,22,23,24,25,26,27,28};
            for(int i=0; i<=21; i++){
                try {
                    historial = Files.readAllLines(archivo.toPath()).get(i);
                } catch (IOException ex) {
                    //Error de lectura
                }  
                String[] mantNodos = historial.split(";");
                if(!historial.equalsIgnoreCase("")){
                    for(int j=0; j<mantNodos.length; j++){
                        String[] mantDatos = mantNodos[j].split(",");
                        String[] ini = mantDatos[1].split("-"), fi = mantDatos[2].split("-");
                        Fecha inicioRe = new Fecha(Integer.parseInt(ini[0]), Integer.parseInt(ini[1]), Integer.parseInt(ini[2])), 
                            finRe = new Fecha(Integer.parseInt(fi[0]), Integer.parseInt(fi[1]), Integer.parseInt(fi[2]));
                        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
                        String mes="";
                        String dia="";
                        if(inicio.getMes()<10){
                            mes="0"+inicio.getMes();
                        }else{
                            mes=""+inicio.getMes();
                        }
                        if(inicio.getDia()<10){
                            dia="0"+inicio.getDia();
                        }
                        else{
                            dia=""+inicio.getDia();
                        }
                        Date inicioN = formato.parse(String.valueOf(inicio.getAnio())+"-"+mes+"-"+dia);
                        if(fin.getMes()<10){
                            mes="0"+fin.getMes();
                        }else{
                            mes=""+fin.getMes();
                        }
                        if(fin.getDia()<10){
                            dia="0"+fin.getDia();
                        }else{
                            dia=""+fin.getDia();
                        }
                        Date finN = formato.parse(String.valueOf(fin.getAnio())+"-"+mes+"-"+dia);
                        if(inicioRe.getMes()<10){
                            mes="0"+inicioRe.getMes();
                        }else{
                            mes=""+inicioRe.getMes();
                        }
                        if(inicioRe.getDia()<10){
                            dia="0"+inicioRe.getDia();
                        }else{
                            dia=""+inicioRe.getDia();
                        }
                        Date inicioE = formato.parse(String.valueOf(inicioRe.getAnio())+"-"+mes+"-"+dia);
                        if(finRe.getMes()<10){
                            mes="0"+finRe.getMes();
                        }else{
                            mes=""+finRe.getMes();
                        }
                        if(finRe.getDia()<10){
                            dia="0"+finRe.getDia();
                        }else{
                            dia=""+finRe.getDia();
                        }
                        Date finE = formato.parse(String.valueOf(finRe.getAnio())+"-"+mes+"-"+dia);
                        if((inicioN.compareTo(finE)<=0 && finN.compareTo(inicioE)>=0) || (inicioN.compareTo(inicioE)==0&&finN.compareTo(finE)==0)){
                            quitar.add(String.valueOf(habs[i]));
                        }                                    
                    }
                }
                 
            }                     
        }catch(Exception e){
            //
        }
        return quitar;
    }
}
