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
import java.util.ArrayList;
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
public class ReporteFechas extends javax.swing.JInternalFrame {

    Admin recepcion = new Admin();
    File[] activas, finalizadas; 
    List<File> listaReservas = new ArrayList<>();
    String reservasActivas = "", reservasFinalizadas="";
    
    /**
     * Creates new form ReporteFechas
     */
    public ReporteFechas(Admin recepcionP) {
        initComponents();
        this.recepcion = recepcionP;
    }
    
    public void leerActivas(int diaInicio, int mesInicio, int anioInicio, int diaFin, int mesFin, int anioFin) throws IOException, ParseException{
        try{
            this.reservasActivas="";
            File folder = new File("PorteroAdminHotel\\Activas\\");//C:\\PorteroAdminHotel\\Activas\\
            this.activas = folder.listFiles();
            for (int i = 0; i < activas.length; i++) {
                if (activas[i].isFile()) {
                    String[] sinTxt = activas[i].getName().split("[.]");
                    String[] archivos = sinTxt[0].split("_");
                    String[] inicio = archivos[1].split("-"), fin = archivos[2].split("-"); 
                    SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
                    if(Integer.parseInt(inicio[1])<10){
                        inicio[1]="0"+inicio[1];
                    }else{
                        inicio[1]=""+inicio[1];
                    }
                    if(Integer.parseInt(inicio[0])<10){
                        inicio[0]="0"+inicio[0];
                    }
                    else{
                        inicio[0]=""+inicio[0];
                    }
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
                    Date inicioB = formato.parse(inicio[2]+"-"+inicio[1]+"-"+inicio[0]);
                    Date finB = formato.parse(fin[2]+"-"+fin[1]+"-"+fin[0]);
                    String mesF, diaF, mesI, diaI;
                    if(mesFin<10){
                        mesF="0"+mesFin;
                    }else{
                        mesF=""+mesFin;
                    }
                    if(diaFin<10){
                        diaF="0"+diaFin;
                    }
                    else{
                        diaF=""+diaFin;
                    }
                    if(mesInicio<10){
                        mesI="0"+mesInicio;
                    }else{
                        mesI=""+mesInicio;
                    }
                    if(diaInicio<10){
                        diaI="0"+diaInicio;
                    }
                    else{
                        diaI=""+diaInicio;
                    }
                    Date inicioR = formato.parse(inicio[2]+"-"+inicio[1]+"-"+inicio[0]);
                    Date finR = formato.parse(fin[2]+"-"+fin[1]+"-"+fin[0]);
                    if((inicioR.compareTo(finB)<0 && finR.compareTo(inicioB)>0) || (inicioR.compareTo(inicioB)==0&&finR.compareTo(finB)==0)){
                        listaReservas.add(activas[i]);
                        String nombre = Files.readAllLines(Paths.get(listaReservas.get(listaReservas.size()-1).toString())).get(5);
                        this.reservasActivas+=("Huesped: "+nombre+"  Inicio: "+archivos[1]+"  Fin: "+archivos[2]+"\n");                         
                    }         
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error de lectura de archivo\n no existe la carpeta PorteroAdminHotel\\Activas\\");
        }     
    }
    
    public void leerAnteriores(int diaInicio, int mesInicio, int anioInicio, int diaFin, int mesFin, int anioFin) throws IOException, ParseException{
        try{
            this.reservasFinalizadas="";
            File folder = new File("PorteroAdminHotel\\Finalizadas\\");//C:\\PorteroAdminHotel\\Finalizadas\\
            this.finalizadas = folder.listFiles();
            for (int i = 0; i < finalizadas.length; i++) {
                if (finalizadas[i].isFile()) {
                    String[] sinTxt = finalizadas[i].getName().split("[.]");
                    String[] archivos = sinTxt[0].split("_");
                    String[] inicio = archivos[1].split("-"), fin = archivos[2].split("-");
                    SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
                    if(Integer.parseInt(inicio[1])<10){
                        inicio[1]="0"+inicio[1];
                    }else{
                        inicio[1]=""+inicio[1];
                    }
                    if(Integer.parseInt(inicio[0])<10){
                        inicio[0]="0"+inicio[0];
                    }
                    else{
                        inicio[0]=""+inicio[0];
                    }
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
                    Date inicioB = formato.parse(inicio[2]+"-"+inicio[1]+"-"+inicio[0]);
                    Date finB = formato.parse(fin[2]+"-"+fin[1]+"-"+fin[0]);
                    String mesF, diaF, mesI, diaI;
                    if(mesFin<10){
                        mesF="0"+mesFin;
                    }else{
                        mesF=""+mesFin;
                    }
                    if(diaFin<10){
                        diaF="0"+diaFin;
                    }
                    else{
                        diaF=""+diaFin;
                    }
                    if(mesInicio<10){
                        mesI="0"+mesInicio;
                    }else{
                        mesI=""+mesInicio;
                    }
                    if(diaInicio<10){
                        diaI="0"+diaInicio;
                    }
                    else{
                        diaI=""+diaInicio;
                    }
                    Date inicioR = formato.parse(inicio[2]+"-"+inicio[1]+"-"+inicio[0]);                  
                    Date finR = formato.parse(fin[2]+"-"+fin[1]+"-"+fin[0]);
                    if((inicioR.compareTo(finB)<0 && finR.compareTo(inicioB)>0) || (inicioR.compareTo(inicioB)==0&&finR.compareTo(finB)==0)){                 
                        listaReservas.add(finalizadas[i]);
                        String nombre = Files.readAllLines(Paths.get(listaReservas.get(listaReservas.size()-1).toString())).get(5);
                        this.reservasFinalizadas+=("Huesped: "+nombre+"  Inicio: "+archivos[1]+"  Fin: "+archivos[2]+"\n");  
                    }                                             
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error de lectura de archivo\n no existe la carpeta PorteroAdminHotel\\Finalizadas\\");
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
        jLabel12 = new javax.swing.JLabel();
        diaInicioField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        mesInicioCombo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        anioInicioField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        diaFinField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        mesFinCombo = new javax.swing.JComboBox<>();
        anioFinField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reporteEditorPane = new javax.swing.JEditorPane();
        consultaButton = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setTitle("Reporte de reservas por rango de fechas");

        jLabel2.setText("Fecha inicio");

        jLabel12.setText("Dia");

        jLabel7.setText("Mes");

        mesInicioCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembe", "Diciembre" }));

        jLabel14.setText("Año");

        jLabel3.setText("Fecha fin");

        jLabel13.setText("Dia");

        jLabel8.setText("Mes");

        mesFinCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembe", "Diciembre" }));

        jLabel15.setText("Año");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Reservas en el rango seleccionado");

        jScrollPane1.setViewportView(reporteEditorPane);

        consultaButton.setText("Consultar");
        consultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(mesInicioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(mesFinCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(anioFinField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(anioInicioField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(consultaButton)))
                            .addComponent(diaInicioField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diaFinField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(diaInicioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(mesInicioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(anioInicioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13)
                    .addComponent(diaFinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(mesFinCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(anioFinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(consultaButton)
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaButtonActionPerformed
        // TODO add your handling code here:
        String error="";
        int diaInicio=0, mesInicio=0, anioInicio=0, diaFin=0, mesFin=0, anioFin=0;
        try{
            diaInicio=Integer.parseInt(this.diaInicioField.getText());
        }catch(Exception e){
            error+="Ha ingresado datos invalidos en el día de inicio o lo ha dejado vacío\n";
        }
        try{
            mesInicio=this.mesInicioCombo.getSelectedIndex();
            if(this.mesInicioCombo.getSelectedIndex()==0){
                error+="Ha dejado vacío el campo de mes de inicio";
            }
        }catch(Exception e){
            error+="Ha dejado vacío el campo de mes de inicio";
        }
        try{
            anioInicio=Integer.parseInt(this.anioInicioField.getText());
        }catch(Exception e){
            error+="Ha ingresado datos invalidos en el año de inicio o lo ha dejado vacio\n";
        }
        try{
            diaFin=Integer.parseInt(this.diaFinField.getText());
        }catch(Exception e){
            error+="Ha ingresado datos invalidos en el día de fin o lo ha dejado vacío\n";
        }
        try{
            mesFin=this.mesFinCombo.getSelectedIndex();
            if(this.mesFinCombo.getSelectedIndex()==0){
                error+="Ha dejado vacío el campo de mes de fin";
            }
        }catch(Exception e){
            error+="Ha dejado vacío el campo de mes de fin";
        }
        try{
            anioFin=Integer.parseInt(this.anioFinField.getText());
        }catch(Exception e){
            error+="Ha ingresado datos invalidos en el año de fin o lo ha dejado vacio\n";
        }  
        
        //validacion inicio
        try{          
            recepcion.setFechaInicio(new Fecha(Integer.parseInt(this.diaInicioField.getText()), this.mesInicioCombo.getSelectedIndex(), 
                Integer.parseInt(this.anioInicioField.getText())));
            if((Integer.parseInt(this.anioInicioField.getText())%4==0) && (Integer.parseInt(this.anioInicioField.getText())%100!=0 || Integer.parseInt(this.anioInicioField.getText())%400==0)){
                int mes=this.mesInicioCombo.getSelectedIndex();
                if(mes==2){
                    if(Integer.parseInt(this.diaInicioField.getText())>29){
                        error+="El mes de Febrero en ese año tiene solo 29 días\n";
                    }
                }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                    if(Integer.parseInt(this.diaInicioField.getText())>31){
                        error+="El mes de "+this.mesInicioCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                    }
                }else{
                    if(Integer.parseInt(this.diaInicioField.getText())>30){
                        error+="El mes de "+this.mesInicioCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                    }
                }
            }else{
                int mes=this.mesInicioCombo.getSelectedIndex();
                if(mes==2){
                    if(Integer.parseInt(this.diaInicioField.getText())>28){
                        error+="El mes de Febrero en ese año tiene solo 28 días\n";
                    }
                }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                    if(Integer.parseInt(this.diaInicioField.getText())>31){
                        error+="El mes de "+this.mesInicioCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                    }
                }else{
                    if(Integer.parseInt(this.diaInicioField.getText())>30){
                        error+="El mes de "+this.mesInicioCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                    }
                }
            }
        }catch(Exception e){
            error+="Ha dejado un campo de fecha de inicio vacio\n";
        }
               
        //validacion fin
        try{
            if(Integer.parseInt(this.anioFinField.getText())<=recepcion.getFechaInicio().getAnio()){
                if((this.mesFinCombo.getSelectedIndex())<=recepcion.getFechaInicio().getMes()){
                    if(Integer.parseInt(this.diaFinField.getText())<=recepcion.getFechaInicio().getDia()){
                        error+="Ha ingresado una fecha de fin anterior o igual \na la fecha de inicio de \nla reserva, ingrese de nuevo\n";
                    }
                }
            }
            if((Integer.parseInt(this.anioFinField.getText())%4==0) && (Integer.parseInt(this.anioFinField.getText())%100!=0 || Integer.parseInt(this.anioFinField.getText())%400==0)){
                int mes=this.mesFinCombo.getSelectedIndex();
                if(mes==2){
                    if(Integer.parseInt(this.diaFinField.getText())>29){
                        error+="El mes de Febrero en ese año tiene solo 29 días\n";
                    }
                }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                    if(Integer.parseInt(this.diaFinField.getText())>31){
                        error+="El mes de "+this.mesFinCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                    }
                }else{
                    if(Integer.parseInt(this.diaFinField.getText())>30){
                        error+="El mes de "+this.mesFinCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                    }
                }
            }else{
                int mes=this.mesFinCombo.getSelectedIndex();
                if(mes==2){
                    if(Integer.parseInt(this.diaFinField.getText())>28){
                        error+="El mes de Febrero en ese año tiene solo 28 días\n";
                    }
                }else if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
                    if(Integer.parseInt(this.diaFinField.getText())>31){
                        error+="El mes de "+this.mesFinCombo.getSelectedItem().toString()+" solo tiene 31 días\n";
                    }
                }else{
                    if(Integer.parseInt(this.diaFinField.getText())>30){
                        error+="El mes de "+this.mesFinCombo.getSelectedItem().toString()+" solo tiene 30 días\n";
                    }
                }
            }
        }catch(Exception e){
            error+="Ha dejado un campo de fecha de fin vacio\n"; 
        }
        
        if(error.equalsIgnoreCase("")){
            try {
                this.listaReservas.clear();
                leerActivas(diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin);
                this.listaReservas.clear();
                leerAnteriores(diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error de lectura");
            } catch (ParseException ex) {          
                Logger.getLogger(ReporteFechas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(this.reservasActivas.equalsIgnoreCase("")&&this.reservasFinalizadas.equalsIgnoreCase("")){
                this.reporteEditorPane.setText("Reservas activas: \nNinguna entra en el rango\n\nReservas finalizadas: \nNinguna entra en el rango\n");
            }else if(this.reservasActivas.equalsIgnoreCase("")){
                this.reporteEditorPane.setText("Reservas activas: \nNinguna entra en el rango\n\nReservas finalizadas: \n"+this.reservasFinalizadas);
            }else if(this.reservasFinalizadas.equalsIgnoreCase("")){
                this.reporteEditorPane.setText("Reservas activas: \n"+this.reservasActivas+"\nReservas finalizadas: \nNinguna entra en el rango\n");
            }else{
                this.reporteEditorPane.setText("Reservas activas: \n"+this.reservasActivas+"\nReservas finalizadas: \n"+this.reservasFinalizadas);
            }

        }else{
            JOptionPane.showMessageDialog(this, "Han ocurrido los siguientes problemas: \n"+error);
        }                               
    }//GEN-LAST:event_consultaButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Admin recepcionP = new Admin();
                new ReporteFechas(recepcionP).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioFinField;
    private javax.swing.JTextField anioInicioField;
    private javax.swing.JButton consultaButton;
    private javax.swing.JTextField diaFinField;
    private javax.swing.JTextField diaInicioField;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> mesFinCombo;
    private javax.swing.JComboBox<String> mesInicioCombo;
    private javax.swing.JEditorPane reporteEditorPane;
    // End of variables declaration//GEN-END:variables
}
