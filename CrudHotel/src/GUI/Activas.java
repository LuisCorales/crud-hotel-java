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
import java.util.ArrayList;
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
public class Activas extends javax.swing.JInternalFrame {

    Admin recepcion = new Admin();
    int diaInicioOriginal, mesInicioOriginal, anioInicioOriginal;
    File[] listOfFiles; 
    List<File> listaReservas = new ArrayList<>();
    
    /**
     * Creates new form Activas
     */
    public Activas(Admin recepcionP) {
        initComponents();
        this.recepcion = recepcionP;
    }
    
    public void leerActivas(int habitacion) throws IOException{
        try{
            listaReservas.removeAll(listaReservas);
            File folder = new File("PorteroAdminHotel\\Activas\\");//C:\\PorteroAdminHotel\\Activas\\
            this.listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    String[] archivos = listOfFiles[i].getName().split("_");
                    if(archivos[0].equalsIgnoreCase(this.habitacionComboBox.getSelectedItem().toString())){
                        listaReservas.add(listOfFiles[i]);
                        String nombre = Files.readAllLines(Paths.get(listaReservas.get(listaReservas.size()-1).toString())).get(5);
                        this.reservasComboBox.addItem("Huesped: "+nombre+"  Inicio: "+archivos[1]+"  Fin: "+archivos[2]);    
                    }                   
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error de lectura de archivo\n no existe la carpeta");
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

        habitacionComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        reservasComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        consultaPane = new javax.swing.JTextPane();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Reporte de reservas activas");

        habitacionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "21", "22", "23", "24", "25", "26", "27", "28" }));
        habitacionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habitacionComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Habitaci??n");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Reporte de reserva:");

        jLabel3.setText("Reserva:");

        reservasComboBox.setEnabled(false);
        reservasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservasComboBoxActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(consultaPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(habitacionComboBox, 0, 348, Short.MAX_VALUE)
                            .addComponent(reservasComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(habitacionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(reservasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void habitacionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionComboBoxActionPerformed
        // TODO add your handling code here:
        this.reservasComboBox.setEnabled(true);
        this.reservasComboBox.removeAllItems();
        this.reservasComboBox.addItem("-");
        if(this.habitacionComboBox.getSelectedIndex()>0){
            try {
                leerActivas((this.habitacionComboBox.getSelectedIndex()-1));
            } catch (IOException ex) {
                //
            }
        }
    }//GEN-LAST:event_habitacionComboBoxActionPerformed

    private void reservasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservasComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.reservasComboBox.getSelectedIndex()>0){
            String reserva = listaReservas.get(this.reservasComboBox.getSelectedIndex()-1).toString();
            String nombre="", id="", correo="", telefono="", sugerencias="",metodoPago="";
            int edad=0, habitacionActual=0, habitacionOriginal=0, dias=0, adultos=0, ninios=0;
            Fecha inicio=new Fecha(), fin = new Fecha();
            double total=0d, iva=0d, subtotal=0d, consumos=0d, daniosTotal=0d, estadia=0d;
            double restaurantTotal=0d, servicioTotal=0d, ballenasTotal=0d, playaTotal=0d, consumosTotal=0d;
            String restaurant="", servicio="", ballenas, playa="", danios="";
            ListaGasto restaurantLista= new ListaGasto(), servicioLista= new ListaGasto(), ballenasLista= new ListaGasto(), playaLista= new ListaGasto(), daniosLista = new ListaGasto();
            boolean ecuatoriana=false;
            
            try {
                nombre = Files.readAllLines(Paths.get(reserva)).get(5);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                id = Files.readAllLines(Paths.get(reserva)).get(6);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                correo = Files.readAllLines(Paths.get(reserva)).get(10);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                telefono = Files.readAllLines(Paths.get(reserva)).get(9);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                edad= Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(8));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                habitacionActual = Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(3));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                habitacionOriginal = Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(4));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                String ini = Files.readAllLines(Paths.get(reserva)).get(0);
                String[] fechaIni = ini.split("-");
                inicio = new Fecha(Integer.parseInt(fechaIni[0]), Integer.parseInt(fechaIni[1]), Integer.parseInt(fechaIni[2]));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                String fi = Files.readAllLines(Paths.get(reserva)).get(1);
                String[] fechaFin = fi.split("-");
                fin = new Fecha(Integer.parseInt(fechaFin[0]), Integer.parseInt(fechaFin[1]), Integer.parseInt(fechaFin[2]));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                dias = Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(2));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }           
            try {
                adultos = Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(11));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                ninios = Integer.parseInt(Files.readAllLines(Paths.get(reserva)).get(12));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }   
            try {
                metodoPago = Files.readAllLines(Paths.get(reserva)).get(13);            
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                sugerencias = Files.readAllLines(Paths.get(reserva)).get(25);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                consumos = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(18));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                daniosTotal = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(20));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                estadia = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(21));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                subtotal = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(22));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                iva = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(23));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            try {
                total = Double.parseDouble(Files.readAllLines(Paths.get(reserva)).get(24));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            
            Huesped huesped = new Huesped(nombre, id, edad, correo, telefono);
            Boolean habitacionCambio=false;
            if(habitacionActual!=habitacionOriginal){
                habitacionCambio=true;
            }
            
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
            try {
                ecuatoriana = Boolean.parseBoolean(Files.readAllLines(Paths.get(reserva)).get(7));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
            }
            
            
            consumosTotal=restaurantTotal+servicioTotal+ballenasTotal+playaTotal;
            
            Reserva activa = new Reserva(inicio, fin, dias, huesped, restaurantLista, servicioLista, ballenasLista, playaLista, metodoPago, consumosTotal, estadia, subtotal, iva, total, 
            habitacionActual, habitacionOriginal, habitacionCambio, daniosLista, daniosTotal, adultos, ninios, sugerencias, ecuatoriana);
            
            this.consultaPane.setText(activa.imprimirConsulta());
            
        }
    }//GEN-LAST:event_reservasComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Activas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Activas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Activas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Activas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin recepcionP = new Admin();
                new Activas(recepcionP).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane consultaPane;
    private javax.swing.JComboBox<String> habitacionComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> reservasComboBox;
    // End of variables declaration//GEN-END:variables
}
