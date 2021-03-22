package Clases;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */

public class Reserva {

    private Fecha inicio;

    private Fecha fin;
    
    private int dias;

    private Huesped huesped;
    
    private ListaGasto restaurant = new ListaGasto(); 
    
    private ListaGasto servicio = new ListaGasto(); 
    
    private ListaGasto ballenas = new ListaGasto(); 
    
    private ListaGasto playa = new ListaGasto(); 

    private String metodoPago;

    private double totalConsumos;
    
    private double precioEstadia;
    
    private double subtotal;
    
    private double iva;

    private double pagoTotal;

    private int habitacionActual;

    private int habitacionOriginal;

    private boolean cambioHabitacion=false;

    private ListaGasto danios = new ListaGasto();
    
    private double pagoDanios;
    
    private int adultos, ninios;
    
    private String sugerencias;
    
    private boolean ecuatoriana;

    public Reserva() {
    }

    public Reserva(Fecha inicio, Fecha fin, int dias, Huesped huesped, String metodoPago, double totalConsumos, double precioEstadia, 
            double subtotal, double iva, double pagoTotal, int habitacionActual, int habitacionOriginal, double pagoDanios, int adultos, int ninios, String sugerencias, boolean ecuatoriana) {
        this.inicio = inicio;
        this.fin = fin;
        this.dias = dias;
        this.huesped = huesped;
        this.metodoPago = metodoPago;
        this.totalConsumos = totalConsumos;
        this.precioEstadia = precioEstadia;
        this.subtotal = subtotal;
        this.iva = iva;
        this.pagoTotal = pagoTotal;
        this.habitacionActual = habitacionActual;
        this.habitacionOriginal = habitacionOriginal;
        this.pagoDanios = pagoDanios;
        this.adultos = adultos;
        this.ninios = ninios;
        this.sugerencias = sugerencias;
        this.ecuatoriana=ecuatoriana;
    }  
    
    public Reserva(Fecha inicio, Fecha fin, int dias, Huesped huesped, ListaGasto restaurant, ListaGasto servicio, ListaGasto ballenas, ListaGasto playa, 
            String metodoPago, double totalConsumos, double precioEstadia, double subtotal, double iva, double pagoTotal, int habitacionActual, int habitacionOriginal, 
            boolean cambioHabitacion, ListaGasto danios, double pagoDanios, int adultos, int ninios, String sugerencias, boolean ecuatoriana) {
        this.inicio = inicio;
        this.fin = fin;
        this.dias = dias;
        this.huesped = huesped;
        this.restaurant = restaurant;
        this.servicio = servicio;
        this.ballenas = ballenas;
        this.playa = playa;
        this.metodoPago = metodoPago;
        this.totalConsumos = totalConsumos;
        this.precioEstadia = precioEstadia;
        this.subtotal = subtotal;
        this.iva = iva;
        this.pagoTotal = pagoTotal;
        this.habitacionActual = habitacionActual;
        this.habitacionOriginal = habitacionOriginal;
        this.cambioHabitacion = cambioHabitacion;
        this.danios = danios;
        this.pagoDanios = pagoDanios;
        this.adultos = adultos;
        this.ninios = ninios;
        this.sugerencias = sugerencias;
        this.ecuatoriana=ecuatoriana;
    }

    public Fecha getInicio() {
        return inicio;
    }

    public void setInicio(Fecha inicio) {
        this.inicio = inicio;
    }

    public Fecha getFin() {
        return fin;
    }

    public void setFin(Fecha fin) {
        this.fin = fin;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }  

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
    /*public void insertarConsumo(Gasto consumo){
        this.consumos.insertar(consumo);
    }
    
    public boolean existeConsumo(Gasto consumo){
        return consumos.existe(consumo);
    }*/

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getTotalConsumos() {
        return totalConsumos;
    }

    public void setTotalConsumos(float totalConsumos) {
        this.totalConsumos = totalConsumos;
    }

    public double getPrecioEstadia() {
        return precioEstadia;
    }

    public void setPrecioEstadia(double precioEstadia) {
        this.precioEstadia = precioEstadia;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public ListaGasto getDanios() {
        return danios;
    }

    public void setDanios(ListaGasto danios) {
        this.danios = danios;
    }  
    
    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(float pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(int habitacionActual) {
        this.habitacionActual = habitacionActual;
    }

    public int getHabitacionOriginal() {
        return habitacionOriginal;
    }

    public void setHabitacionOriginal(int habitacionOriginal) {
        this.habitacionOriginal = habitacionOriginal;
    }

    public boolean getCambioHabitacion() {
        return cambioHabitacion;
    }

    public void setCambioHabitacion(boolean cambioHabitacion) {
        this.cambioHabitacion = cambioHabitacion;
    }

    public double getPagoDanios() {
        return pagoDanios;
    }

    public void setPagoDanios(double pagoDanios) {
        this.pagoDanios = pagoDanios;
    }
    
    public void aumentarDanios(double valor){
        this.pagoDanios+=valor;
    }

    public int getAdultos() {
        return adultos;
    }

    public void setAdultos(int adultos) {
        this.adultos = adultos;
    }

    public int getNinios() {
        return ninios;
    }

    public void setNinios(int ninios) {
        this.ninios = ninios;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }
    
    public int diasEstadia(){
        int dias=0;
        Fecha finaliza=fin, empieza=inicio;
        if(finaliza.getMes()<empieza.getMes()){
            finaliza.setMes(finaliza.getMes()+12);
        }
        if(finaliza.getDia()<empieza.getDia()){
            finaliza.setDia(finaliza.getDia()+30);
        }
        dias=(finaliza.getAnio()-empieza.getAnio())*365+(finaliza.getMes()-empieza.getMes())*30+(finaliza.getDia()-empieza.getDia());
        return dias;
    }

    public ListaGasto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ListaGasto restaurant) {
        this.restaurant = restaurant;
    }

    public ListaGasto getServicio() {
        return servicio;
    }

    public void setServicio(ListaGasto servicio) {
        this.servicio = servicio;
    }

    public ListaGasto getBallenas() {
        return ballenas;
    }

    public void setBallenas(ListaGasto ballenas) {
        this.ballenas = ballenas;
    }

    public ListaGasto getPlaya() {
        return playa;
    }

    public void setPlaya(ListaGasto playa) {
        this.playa = playa;
    }  

    @Override
    public String toString() {
        return inicio + "\n" + //0
                fin + "\n" +   //1
                dias + "\n" + //2
                habitacionActual + "\n" + //3
                habitacionOriginal + "\n" + //4
                huesped.getNombre() + "\n" + //5              
                huesped.getCedula() + "\n" +   //6
                ecuatoriana+"\n"+ //7 id ecuador 
                huesped.getEdad() + "\n" +   //8
                huesped.getTelefono() + "\n" +   //9
                huesped.getEmail() + "\n" +   //10
                adultos + "\n" +   //11
                ninios + "\n" + //12
                metodoPago + "\n" +   //13
                restaurant.imprimir() + "\n" + //14 restaurant    
                servicio.imprimir() + "\n" +   //15 servicio cuarto     
                ballenas.imprimir() + "\n" +   //16 ballena   
                playa.imprimir() + "\n" +      //17 playa
                totalConsumos + "\n" +    //18 total consumos
                danios.imprimir()+"\n"+     //19 lista danios
                pagoDanios + "\n" +      //20 total danios
                precioEstadia + "\n" +   //21  precio estadia(dias*valor)
                subtotal + "\n" +     //22 subtotal
                iva + "\n" +   //23 iva             
                pagoTotal + "\n" + //24 total
                sugerencias+ "\n";  //25 sugerencias

    }
    
    public String imprimirConsulta() {
        String habitacionCambio="No", ecuador="No";
        if(this.cambioHabitacion){
            habitacionCambio="Sí";
        }
        if(this.ecuatoriana){
            ecuador="Sí";
        }
        return "Fecha Inicio: "+inicio + "\n" + //0
                "Fecha fin:" +fin + "\n" +   //1
                "Dias estadia: "+dias + "\n" + //2
                "Habitacion actual: "+habitacionActual + "\n" + //3
                "Habitacion original: "+habitacionOriginal + "\n" + //4
                "Hubo cambio de habitacion: "+habitacionCambio+"\n"+
                "Nombre cliente: "+huesped.getNombre() + "\n" + //5              
                "Identifación: "+huesped.getCedula() + "\n" +   //6   
                "Es identificación ecuatoriana: "+ecuador+"\n"+ //7
                "Edad: "+huesped.getEdad() + "\n" +   //8
                "Telefono: "+huesped.getTelefono() + "\n" +   //9
                "Correo electrónico: "+huesped.getEmail() + "\n" +   //10
                "Numero de adultos: "+adultos + "\n" +   //11
                "Numero de niños: "+ninios + "\n" + //12
                "Método de pago: "+metodoPago + "\n" +   //13
                "Consumos de restaurant: "+restaurant.imprimirConsulta() + "\n\n" + //14 restaurant    
                "Consumos servicio a la habitación:"+servicio.imprimirConsulta() + "\n\n" +   //15 servicio cuarto     
                "Consumos visita a las ballenas: "+ballenas.imprimirConsulta() + "\n\n" +   //16 ballena    
                "Consumos visita a la playa escondida: "+playa.imprimirConsulta() + "\n\n" +     // 17 playa
                "Total de consumos: "+totalConsumos + "\n" +    //18 total consumos
                "Gastos daños: "+danios.imprimirConsulta()+"\n"+     //19 lista danios
                "Total daños: "+pagoDanios + "\n" +      //20 total danios
                "Precio estadia (por dias): "+precioEstadia + "\n" +   //21  precio estadia(dias*valor)
                "Subtotal: "+subtotal + "\n" +     //22 subtotal
                "IVA: "+iva + "\n" +   //23 iva             
                "Total: "+pagoTotal + "\n" + //24 total
                "Sugerencias: "+sugerencias+ "\n";  //25 sugerencias
    }
  
}
