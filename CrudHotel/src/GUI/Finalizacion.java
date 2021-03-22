/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
public class Finalizacion extends javax.swing.JInternalFrame {

    Admin recepcion = new Admin();
    File[] listOfFiles;
    double estadia=0d, restaurantTotal=0d, servicioTotal=0d, ballenasTotal=0d, playaTotal=0d, daniosTotal=0d, consumosTotal=0d, subtotal=0d, iva=0d, total=0d;
    
    /**
     * Creates new form Finlizacion
     */
    public Finalizacion(Admin recepcionP) {
        initComponents();
        //Mostrar las reservas activas en el combo box
        this.recepcion = recepcionP;
        this.reservasComboBox.addItem("-");
        try{
            leerActivas();
        }catch(IOException e){
            //
        }   
        this.reservasComboBox.setSelectedIndex(0);
    }
    
    public void leerActivas() throws IOException{
        try{
            File folder = new File("PorteroAdminHotel\\Activas\\");//C:\\PorteroAdminHotel\\Activas\\
            this.listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    String[] fin = listOfFiles[i].getName().split("[.]");
                    String[] archivos = fin[0].split("_");
                    String nombre = Files.readAllLines(Paths.get(listOfFiles[i].toString())).get(5);
                    this.reservasComboBox.addItem("Huesped: "+nombre+"  Inicio: "+archivos[1]+"  Fin: "+archivos[2]);             
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error de lectura de archivo\n no existe la carpeta PorteroAdminHotel\\Finalizadas\\");
        }        
    }
    
    public void calculos(){
        if(this.temporadaComboBox.getSelectedIndex()>0){
            String reserva = listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString();
            String restaurant="", servicio="", ballenas, playa="", danios="";
            ListaGasto restaurantLista= new ListaGasto(), servicioLista= new ListaGasto(), ballenasLista= new ListaGasto(), playaLista= new ListaGasto(), daniosLista = new ListaGasto();           
            
            if(this.temporadaComboBox.getSelectedItem().toString().equalsIgnoreCase("SI")){
                this.valorEstadiaField.setText(String.valueOf(Double.parseDouble(this.diasField.getText())*(20)*Double.parseDouble(this.adultosField.getText())));
            }else{
                this.valorEstadiaField.setText(String.valueOf(Double.parseDouble(this.diasField.getText())*(15)*Double.parseDouble(this.adultosField.getText())));
            }
            estadia=Double.parseDouble(this.valorEstadiaField.getText());
            
            try {
                restaurant = Files.readAllLines(Paths.get(reserva)).get(14);
                String[] restaurantClase = restaurant.split(";");  
                if(!restaurant.equalsIgnoreCase("")){
                    for(int i=0; i<restaurantClase.length; i++){
                    String[] restaurantNodo = restaurantClase[i].split(",");
                    String[] fecha = restaurantNodo[2].split("-");
                    Fecha consumo = new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                    restaurantLista.insertar(new Gasto(restaurantNodo[0], Double.parseDouble(restaurantNodo[1]), consumo));
                }
                }             
                restaurantTotal=restaurantLista.getTotal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            } 
            try {
                servicio = Files.readAllLines(Paths.get(reserva)).get(15);
                String[] servicioClase = servicio.split(";");
                if(!servicio.equalsIgnoreCase("")){
                    for(int i=0; i<servicioClase.length; i++){
                        String[] servicioNodo = servicioClase[i].split(",");
                        String[] fecha = servicioNodo[2].split("-");
                        Fecha consumo = new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                        servicioLista.insertar(new Gasto(servicioNodo[0], Double.parseDouble(servicioNodo[1]), consumo));
                    }
                }             
                servicioTotal=servicioLista.getTotal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            } 
            try {
                ballenas = Files.readAllLines(Paths.get(reserva)).get(16);
                String[] ballenasClase = ballenas.split(";");      
                if(!ballenas.equalsIgnoreCase("")){
                    for(int i=0; i<ballenasClase.length; i++){
                        String[] ballenasNodo = ballenasClase[i].split(",");
                        String[] fecha = ballenasNodo[2].split("-");
                        Fecha consumo = new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                        ballenasLista.insertar(new Gasto(ballenasNodo[0], Double.parseDouble(ballenasNodo[1]), consumo));
                    }
                }               
                ballenasTotal=ballenasLista.getTotal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            } 
            try {
                playa = Files.readAllLines(Paths.get(reserva)).get(17);
                String[] playaClase = playa.split(";"); 
                if(!playa.equalsIgnoreCase("")){
                    for(int i=0; i<playaClase.length; i++){
                        String[] playaNodo = playaClase[i].split(",");
                        String[] fecha = playaNodo[2].split("-");
                        Fecha consumo = new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                        playaLista.insertar(new Gasto(playaNodo[0], Double.parseDouble(playaNodo[1]), consumo));
                    }
                }               
                playaTotal=playaLista.getTotal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            } 
            try {
                danios = Files.readAllLines(Paths.get(reserva)).get(19);
                String[] daniosClase = danios.split(";");       
                if(!danios.equalsIgnoreCase("")){
                    for(int i=0; i<daniosClase.length; i++){
                        String[] daniosNodo = daniosClase[i].split(",");
                        String[] fecha = daniosNodo[2].split("-");
                        Fecha consumo = new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]));
                        daniosLista.insertar(new Gasto(daniosNodo[0], Double.parseDouble(daniosNodo[1]), consumo));
                    }
                }               
                daniosTotal=daniosLista.getTotal();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }     
            
            consumosTotal=restaurantTotal+servicioTotal+ballenasTotal+playaTotal;
            
            this.consumosField.setText(String.valueOf(consumosTotal));
            this.daniosField.setText(String.valueOf(daniosTotal));
            subtotal=estadia+consumosTotal+daniosTotal;
            this.subtotalField.setText(String.valueOf(subtotal));
            iva=subtotal*Double.parseDouble(this.porcentIvaField.getText())/100;
            this.ivaField.setText(String.valueOf(this.iva));
            total=subtotal+iva;
            this.totalField.setText(String.valueOf(total));
            
            this.finalizarButton.setEnabled(true);
        }          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        reservasComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        habitacionField = new javax.swing.JTextField();
        diasField = new javax.swing.JTextField();
        temporadaComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        valorEstadiaField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        consumosField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        daniosField = new javax.swing.JTextField();
        subtotalField = new javax.swing.JTextField();
        ivaField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        totalField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        metodoPagoComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sugerenciasEditorPane = new javax.swing.JEditorPane();
        finalizarButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        inicioField = new javax.swing.JTextField();
        finField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        porcentIvaField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        adultosField = new javax.swing.JTextField();
        niniosField = new javax.swing.JTextField();
        ivaButton = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Finalización");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Escoja reserva a finalizar:");

        reservasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservasComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombres y apellidos:");

        jLabel3.setText("Identificación:");

        jLabel4.setText("Ahora está disponible la habitación:");

        jLabel5.setText("Días de estadía en el hotel:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Resumen de gastos");

        jLabel7.setText("Cobrar como temporada alta:");

        nombreField.setEditable(false);

        idField.setEditable(false);

        habitacionField.setEditable(false);

        diasField.setEditable(false);

        temporadaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "SI", "NO" }));
        temporadaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temporadaComboBoxActionPerformed(evt);
            }
        });

        jLabel8.setText("Valor de la estadia (por días y cupantes):");

        valorEstadiaField.setEditable(false);

        jLabel9.setText("Gastos por consumos:");

        consumosField.setEditable(false);

        jLabel10.setText("Gastos por daños:");

        jLabel11.setText("Subtotal:");

        jLabel12.setText("IVA:");

        daniosField.setEditable(false);

        subtotalField.setEditable(false);

        ivaField.setEditable(false);

        jLabel13.setText("Total:");

        totalField.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Pagos");

        jLabel15.setText("Metodo de pago actual (puede cambiarlo):");

        metodoPagoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Crédito", "Débito", "Efectivo" }));

        jLabel16.setText("Sugerencias:");

        jScrollPane3.setViewportView(sugerenciasEditorPane);

        finalizarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        finalizarButton.setText("Finalizar reserva");
        finalizarButton.setEnabled(false);
        finalizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarButtonActionPerformed(evt);
            }
        });

        jLabel17.setText("Inicio:");

        jLabel18.setText("Fin:");

        inicioField.setEditable(false);

        finField.setEditable(false);

        jLabel19.setText("% IVA:");

        porcentIvaField.setText("12");

        jLabel20.setText("Adultos:");

        jLabel21.setText("Niños:");

        adultosField.setEditable(false);

        niniosField.setEditable(false);

        ivaButton.setText("Cambiar IVA");
        ivaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ivaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(reservasComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel16)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(habitacionField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inicioField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diasField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(finalizarButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(2, 2, 2))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(69, 69, 69))
                                    .addComponent(jLabel9))))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ivaField)
                            .addComponent(subtotalField)
                            .addComponent(daniosField)
                            .addComponent(consumosField)
                            .addComponent(valorEstadiaField)
                            .addComponent(adultosField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(temporadaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(porcentIvaField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(niniosField)
                            .addComponent(totalField))
                        .addGap(18, 18, 18)
                        .addComponent(ivaButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(metodoPagoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(reservasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(habitacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(inicioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(finField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(diasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(temporadaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(porcentIvaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ivaButton)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(adultosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(niniosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addComponent(valorEstadiaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(consumosField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel10))
                    .addComponent(daniosField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(subtotalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ivaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(metodoPagoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(finalizarButton)
                .addGap(22, 22, 22))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reservasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservasComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.reservasComboBox.getSelectedIndex()>0){           
            String reserva = listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString();
            try {
                this.nombreField.setText(Files.readAllLines(Paths.get(reserva)).get(5));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.idField.setText(Files.readAllLines(Paths.get(reserva)).get(6));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.habitacionField.setText(Files.readAllLines(Paths.get(reserva)).get(3));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.inicioField.setText(Files.readAllLines(Paths.get(reserva)).get(0));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.finField.setText(Files.readAllLines(Paths.get(reserva)).get(1));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.diasField.setText(Files.readAllLines(Paths.get(reserva)).get(2));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }           
            try {
                this.adultosField.setText(Files.readAllLines(Paths.get(reserva)).get(11));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.niniosField.setText(Files.readAllLines(Paths.get(reserva)).get(12));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }   
            try {
                String metodoPago = Files.readAllLines(Paths.get(reserva)).get(13);
                switch(metodoPago){
                    case("Crédito"):
                        this.metodoPagoComboBox.setSelectedIndex(1);
                    break;
                    case("Débito"):
                        this.metodoPagoComboBox.setSelectedIndex(2);
                    break;
                    case("Efectivo"):
                        this.metodoPagoComboBox.setSelectedIndex(3);
                    break;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                this.sugerenciasEditorPane.setText(Files.readAllLines(Paths.get(reserva)).get(25));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
        }
    }//GEN-LAST:event_reservasComboBoxActionPerformed

    private void finalizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarButtonActionPerformed
        // TODO add your handling code here:
        String reserva = listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString();
        String fechaFin="";
        LocalDate actual = LocalDate.now();//Da fecha actual 
        try {
            fechaFin = Files.readAllLines(Paths.get(reserva)).get(1);
        } catch (IOException ex) {
            //
        }
        String[] fin = fechaFin.split("-");
        if(Integer.parseInt(fin[1])<10){
            fin[1]="0"+fin[1];
        }else{
            fin[1]=""+fin[1];
        }
        if(Integer.parseInt(fin[0])<10){
            fin[0]="0"+fin[0];
        }
        else{
            fin[0]=""+fin[0];
        }
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        String mes="", dia="";
        if(actual.getMonthValue()<10){
            mes="0"+actual.getMonthValue();
        }else{
            mes=""+actual.getMonthValue();
        }
        if(actual.getDayOfMonth()<10){
            dia="0"+actual.getDayOfMonth();
        }
        else{
            dia=""+actual.getDayOfMonth();
        }
        Date finR=new Date();
        Date act = new Date();
        try {
            finR = formato.parse(fin[2]+"-"+fin[1]+"-"+fin[0]);
            act = formato.parse(String.valueOf(actual.getYear())+"-"+mes+"-"+dia);
        } catch (ParseException ex) {
            Logger.getLogger(Finalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(finR.compareTo(act)<=0){
            if(Double.parseDouble(this.porcentIvaField.getText())>0){
                File archivo = new File(listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString());
                List <String> lineas=null;
                try {       
                    lineas=Files.readAllLines(Paths.get(archivo.toString()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                }
                lineas.set(13, this.metodoPagoComboBox.getSelectedItem().toString());
                lineas.set(18, String.valueOf(consumosTotal));
                lineas.set(20, String.valueOf(daniosTotal));
                lineas.set(21, String.valueOf(estadia));
                lineas.set(22, String.valueOf(subtotal));
                lineas.set(23, String.valueOf(iva));
                lineas.set(24, String.valueOf(total));
                lineas.set(25, this.sugerenciasEditorPane.getText());

                try {
                    Files.write(archivo.toPath(), lineas);
                } catch (IOException ex) {
                    //
                }

                String mover = "";
                reserva = listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString();
                try {
                    mover+=Files.readAllLines(Paths.get(reserva)).get(3);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                }
                try {
                    mover+="_"+Files.readAllLines(Paths.get(reserva)).get(0);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                }
                try {
                    mover+="_"+Files.readAllLines(Paths.get(reserva)).get(1);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                }

                try{
                    archivo.renameTo(new File("PorteroAdminHotel\\Finalizadas\\"+mover+".txt"));//C:\\PorteroAdminHotel\\Finalizadas\\
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Error de lectura de archivo\n no existe la carpeta PorteroAdminHotel\\Finalizadas\\");
                }

                this.reservasComboBox.removeAllItems();
                this.reservasComboBox.addItem("-");
                try {
                    leerActivas();
                } catch (IOException ex) {
                    Logger.getLogger(Finalizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.reservasComboBox.setSelectedIndex(0);
                this.nombreField.setText("");
                this.idField.setText("");
                this.inicioField.setText("");
                this.finField.setText("");
                this.habitacionField.setText("");
                this.diasField.setText("");
                this.temporadaComboBox.setSelectedIndex(0);
                this.porcentIvaField.setText("12");
                this.adultosField.setText("");
                this.niniosField.setText("");
                this.valorEstadiaField.setText("");
                this.consumosField.setText("");
                this.daniosField.setText("");
                this.subtotalField.setText("");
                this.ivaField.setText("");
                this.totalField.setText("");
                this.metodoPagoComboBox.setSelectedIndex(0);
                this.sugerenciasEditorPane.setText("");
                this.finalizarButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Reserva finalizada");
            }else{
                JOptionPane.showMessageDialog(this, "El porcentaje de IVA debe ser mayor a cero");
            }          
        }else{
            JOptionPane.showMessageDialog(this, "La reserva solo puede finalizar el mismo día");
        }
        
    }//GEN-LAST:event_finalizarButtonActionPerformed

    private void temporadaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temporadaComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.temporadaComboBox.getSelectedIndex()>0){
            if(Double.parseDouble(this.porcentIvaField.getText())<=0){
                JOptionPane.showMessageDialog(this, "El porcentaje de IVA debe ser mayor a cero");
                this.temporadaComboBox.setSelectedIndex(0);
            }else{
                calculos();      
            }         
        }           
    }//GEN-LAST:event_temporadaComboBoxActionPerformed

    private void ivaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ivaButtonActionPerformed
        // TODO add your handling code here:
        if(this.temporadaComboBox.getSelectedIndex()>0 && Double.parseDouble(this.porcentIvaField.getText())>0){
            calculos();
        }else{
            JOptionPane.showMessageDialog(this, "El porcentaje de IVA debe ser mayor a cero");
        }
    }//GEN-LAST:event_ivaButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Finalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finalizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin recepcionP = new Admin();
                new Finalizacion(recepcionP).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adultosField;
    private javax.swing.JTextField consumosField;
    private javax.swing.JTextField daniosField;
    private javax.swing.JTextField diasField;
    private javax.swing.JTextField finField;
    private javax.swing.JButton finalizarButton;
    private javax.swing.JTextField habitacionField;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField inicioField;
    private javax.swing.JButton ivaButton;
    private javax.swing.JTextField ivaField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> metodoPagoComboBox;
    private javax.swing.JTextField niniosField;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField porcentIvaField;
    private javax.swing.JComboBox<String> reservasComboBox;
    private javax.swing.JTextField subtotalField;
    private javax.swing.JEditorPane sugerenciasEditorPane;
    private javax.swing.JComboBox<String> temporadaComboBox;
    private javax.swing.JTextField totalField;
    private javax.swing.JTextField valorEstadiaField;
    // End of variables declaration//GEN-END:variables
}
