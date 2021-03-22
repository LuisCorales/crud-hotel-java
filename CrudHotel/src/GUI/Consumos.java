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
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Diego Hiriart
 * @author Luis Corales
 * @author Martin Galvez
 * @author Daniel Alban
 * @author Daniela Estupinan
 */
public class Consumos extends javax.swing.JInternalFrame {

    Admin recepcion = new Admin();
    File[] listOfFiles;
    
    /**
     * Creates new form Consumos
     */
    public Consumos(Admin recepcionP) {
        initComponents();
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
    
    public void registrarConsumo(String reserva) throws IOException{
        if(this.reservasComboBox.getSelectedIndex()>0 && this.consumoComboBox.getSelectedIndex()>0){
            String error = "";
            int diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;

            String lineaEspecifica = Files.readAllLines(Paths.get(reserva)).get(0);
            String[] inicio = lineaEspecifica.split("-");
            lineaEspecifica = Files.readAllLines(Paths.get(reserva)).get(1);
            String[] fin = lineaEspecifica.split("-");

            diaInicio=Integer.parseInt(inicio[0]);
            mesInicio=Integer.parseInt(inicio[1])-1;
            anioInicio=Integer.parseInt(inicio[2]);

            diaFin=Integer.parseInt(fin[0]);
            mesFin=Integer.parseInt(fin[1])-1;
            anioFin=Integer.parseInt(fin[2]);
              
            String consumo = this.consumoComboBox.getSelectedItem().toString();
            double valor=0d;
            try{
                valor = Double.parseDouble(this.valorConsumoField.getText());
                if(valor<=0){
                    error+="Ingrese un valor del consumo mayor a cero\n";
                }
            }catch(Exception e){
                error+="Ingresó un valor de consumo inválido\n";
            } 
            Fecha fecha = null;                    
            try{     
                fecha = new Fecha(Integer.parseInt(this.diaField.getText()), (this.mesCombo.getSelectedIndex()), Integer.parseInt(this.anioField.getText()));          
                if(Integer.parseInt(this.anioField.getText())<=anioInicio || Integer.parseInt(this.anioField.getText())>=anioFin){
                    if((this.mesCombo.getSelectedIndex())<=mesInicio || (this.mesCombo.getSelectedIndex())>=mesFin){
                        if(Integer.parseInt(this.diaField.getText())<diaInicio&&(this.mesCombo.getSelectedIndex())<=mesInicio 
                                || Integer.parseInt(this.diaField.getText())>=diaFin&&(this.mesCombo.getSelectedIndex())>=mesFin){
                            error+="Ha ingresado una fecha fuera del rango del fechas de la reserva, ingrese de nuevo\n";
                        }
                    }
                }
                if((Integer.parseInt(this.anioField.getText())%4==0) && (Integer.parseInt(this.anioField.getText())%100!=0 || Integer.parseInt(this.anioField.getText())%400==0)){
                    int mes=this.mesCombo.getSelectedIndex();
                    if(mes==2){
                        if(Integer.parseInt(this.diaField.getText())>29){
                            error+="El mes de Febrero en ese año tiene solo 29 días\n";
                        }
                    }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                        if(Integer.parseInt(this.diaField.getText())>31){
                            error+="El mes de "+this.mesCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                        }
                    }else{
                        if(Integer.parseInt(this.diaField.getText())>30){
                            error+="El mes de "+this.mesCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                        }
                    }
                }else{
                    int mes=this.mesCombo.getSelectedIndex();
                    if(mes==2){
                        if(Integer.parseInt(this.diaField.getText())>28){
                            error+="El mes de Febrero en ese año tiene solo 28 días\n";
                        }
                    }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                        if(Integer.parseInt(this.diaField.getText())>31){
                            error+="El mes de "+this.mesCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                        }
                    }else{
                        if(Integer.parseInt(this.diaField.getText())>30){
                            error+="El mes de "+this.mesCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                        }
                    }
                }
            }catch(Exception e){
                error+="Ha dejado un campo de fecha vacio\n"; 
            }
                        
            try{
                String descripcion = this.descripcionField.getText();
                if(descripcion.equalsIgnoreCase("")||descripcion.equalsIgnoreCase(" ")){
                    error+="Ingrese una descripción del consumo\n";
                }
            }catch(Exception e){
                error+="Error en ingreso de descripcion\n";
            }
            
            if(error.equalsIgnoreCase("")){
                int modificar=14;
                switch(consumo){                    
                    case "Restaurant":
                        modificar=14;
                    break;
                    case "Servicio a la habitación":
                        modificar=15;
                    break;
                    case "Visita a las ballenas":
                        modificar=16;
                    break;
                    case "Visita a la playa escondida":
                        modificar=17;
                    break;
                    case "Daños":
                        modificar=19;
                    break;
                }
                
                //Cambiar Datos
                File archivo = new File(listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString());
                List <String> lineas=null;
                String gastosExistentes="";
                
                try {
                    gastosExistentes = Files.readAllLines(Paths.get(archivo.toString())).get(modificar);
                } catch (IOException ex) {
                    //
                }
                
                try {
                    lineas = Files.readAllLines(Paths.get(archivo.toString()));
                } catch (IOException ex) {
                    //
                }
                
                lineas.set(modificar, gastosExistentes+(new Gasto(this.descripcionField.getText(), valor, fecha)).toString());
                  
                try {
                    Files.write(archivo.toPath(), lineas);
                } catch (IOException ex) {
                    //
                }

                this.diaField.setText("");
                this.mesCombo.setSelectedIndex(-1);
                this.anioField.setText(""); 
                this.consumoComboBox.setSelectedIndex(-1);
                this.descripcionField.setText("");
                this.valorConsumoField.setText("");
                this.reservasComboBox.removeAllItems();
                this.reservasComboBox.addItem("-");
                
                try {
                    leerActivas();
                } catch (IOException ex) {
                    //
                }
                
                this.reservasComboBox.setSelectedIndex(0);
                        
                JOptionPane.showMessageDialog(this, "Edicion exitosa");
                
            }else{
                JOptionPane.showMessageDialog(this, "Han ocurrido lo siguientes problemas:\n "+error);
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "No ha seleccionado una reserva");
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

        jLabel2 = new javax.swing.JLabel();
        diaField = new javax.swing.JTextField();
        mesCombo = new javax.swing.JComboBox<>();
        anioField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        reservasComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        consumoComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        valorConsumoField = new javax.swing.JTextField();
        registarButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        descripcionField = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Gastos y consumos");

        jLabel2.setText("Fecha de consumo*:");

        mesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembe", "Diciembre" }));

        jLabel1.setText("Escoja reserva para añadir consumo*:");

        jLabel3.setText("Consumo/gasto*:");

        consumoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Restaurant", "Servicio a la habitación", "Visita a las ballenas", "Visita a la playa escondida", "Daños" }));

        jLabel4.setText("Valor del consumo*:");

        registarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registarButton.setText("Registrar consumo");
        registarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registarButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("Dia");

        jLabel7.setText("Mes");

        jLabel14.setText("Año");

        jLabel5.setText("Descripción*:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(anioField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(reservasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(consumoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valorConsumoField, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(diaField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registarButton)
                .addGap(269, 269, 269))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(reservasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(consumoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(descripcionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(diaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(mesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(anioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(valorConsumoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(registarButton)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registarButtonActionPerformed
        // TODO add your handling code here:
        //Cambiar datos en el archivo, verificar errores de ingreso
        String reserva="";
        if(this.reservasComboBox.getSelectedIndex()>0 && this.consumoComboBox.getSelectedIndex()>0){
        boolean disponible = true;
        LocalDate actual = LocalDate.now();//Da fecha actual    
        if(Integer.parseInt(this.diaField.getText())==actual.getDayOfMonth() && this.mesCombo.getSelectedIndex()==actual.getMonthValue() 
                && Integer.parseInt(this.anioField.getText())==actual.getYear()){
            String consumo = this.consumoComboBox.getSelectedItem().toString(), estado="";
            String ruta = "PorteroAdminHotel\\Servicios.txt";//C:\\PorteroAdminHotel\\Servicios.txt
            switch(consumo){               
                case "Restaurant":
                    try {
                        estado = Files.readAllLines(Paths.get(ruta)).get(2);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                    }
                break;
                case "Servicio a la habitación":
                    try {
                        estado = Files.readAllLines(Paths.get(ruta)).get(3);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                    }
                break;
                case "Visita a las ballenas":
                    try {
                        estado = Files.readAllLines(Paths.get(ruta)).get(4);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                    }
                break;
                case "Visita a la playa escondida":
                    try {
                        estado = Files.readAllLines(Paths.get(ruta)).get(5);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error de lectura de archivo");
                    }
                break;                
            }
            disponible=Boolean.parseBoolean(estado);
        }
            reserva = listOfFiles[this.reservasComboBox.getSelectedIndex()-1].toString();
            if(!reserva.equalsIgnoreCase("-") && disponible){
                try{
                    registrarConsumo(reserva);
                }catch(IOException e){
                    System.out.println("fechas");
                }
            }else if(disponible==false){
                JOptionPane.showMessageDialog(this, "En esta fecha ese consumo o servicio \n no está disponible");
            }
        }else{
            JOptionPane.showMessageDialog(this, "No ha seleccionado una reserva y/o un consumo");
        }  
    }//GEN-LAST:event_registarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Consumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consumos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin recepcionP = new Admin();
                new Consumos(recepcionP).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioField;
    private javax.swing.JComboBox<String> consumoComboBox;
    private javax.swing.JTextField descripcionField;
    private javax.swing.JTextField diaField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> mesCombo;
    private javax.swing.JButton registarButton;
    private javax.swing.JComboBox<String> reservasComboBox;
    private javax.swing.JTextField valorConsumoField;
    // End of variables declaration//GEN-END:variables
}
