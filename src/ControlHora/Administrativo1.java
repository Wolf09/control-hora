/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlHora;

import Auxiliares.MensajeBeep;
import Principal.Conexion;
import Principal.Control;
import Principal.ControlaHora;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import ziconos.Error1;

/**
 *
 * @author WJhonBarH
 */
public class Administrativo1 extends javax.swing.JDialog {

   private Error1 icono;
   private static String user="";
   public static boolean band=false;
   private static String cod3;
    public Administrativo1(java.awt.Frame parent, boolean modal,String cod2) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        icono = new Error1();
        cod3=cod2;
         try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/logoreloj.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        this.setVisible(true);
    }
    
   
    
          
          
     private boolean buscarAdministrativo(String cod){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT codadm FROM administrativo WHERE codadm='"+cod+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              rs.beforeFirst();                   
              if(rs.next()){
                  band4=true;
              }else{
                  band4=false;
              }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
                //JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return band4;
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cod1 = new javax.swing.JTextField();
        pass1 = new javax.swing.JPasswordField();
        botRegis = new javax.swing.JButton();
        botCan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña:");

        cod1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cod1KeyTyped(evt);
            }
        });

        pass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pass1KeyTyped(evt);
            }
        });

        botRegis.setText("REGISTRAR");
        botRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botRegisActionPerformed(evt);
            }
        });

        botCan.setText("CANCELAR");
        botCan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cod1)
                            .addComponent(pass1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(botRegis)
                        .addGap(95, 95, 95)
                        .addComponent(botCan)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botRegis)
                    .addComponent(botCan))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cod1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod1KeyTyped
         char tecla= evt.getKeyChar();
        if(tecla== KeyEvent.VK_ENTER){
            botRegis.doClick();
        }
    }//GEN-LAST:event_cod1KeyTyped

    private void botRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botRegisActionPerformed
         String usu=cod1.getText();
            String pas=new String(pass1.getPassword());
            if(usu.equals("")&&pas.equals("")){
                new MensajeBeep().showMessageDialog(this, "No Ingreso Datos, ingrese Por favor","Datos Nulos",JOptionPane.WARNING_MESSAGE,icono);
            }
            else if(!(cod3.equals(usu))){
                new MensajeBeep().showMessageDialog(this, "Ingreso mal su codigo de Docente","Datos Nulos",JOptionPane.WARNING_MESSAGE,icono);
            }
            
            else{
                acceder(usu, pas);
            }
    }//GEN-LAST:event_botRegisActionPerformed

    private void pass1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass1KeyTyped
         char tecla= evt.getKeyChar();
        if(tecla== KeyEvent.VK_ENTER){
            botRegis.doClick();
        }
    }//GEN-LAST:event_pass1KeyTyped

    private void botCanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCanActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botCanActionPerformed


     public String getUser(){
    return user;
    }

     public void acceder(String usuario, String pass)
    {
        String cap="";
        String cap1="";
        String cap2="";
       String sql="SELECT usuario,contrasena,tipo FROM usuarios WHERE usuario='"+usuario+"' AND contrasena='"+pass+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString("usuario");
                cap1 =rs.getString("contrasena");
                cap2 =rs.getString("tipo");
               }
            rs.beforeFirst();                   
             if(rs.next()){
                  
                   if(cap2.equals("ADMINISTRATIVO")){
                    String sep = System.getProperty("line.separator");
                    if(buscarAdministrativo(cap)==true){
                         this.setVisible(false);
                          band=true;
                    }
                    else{
                         new MensajeBeep().showMessageDialog(null, "Operación Incorrecta Usted no esta Registrado"+sep+"Como Admnistrativo","",JOptionPane.ERROR_MESSAGE,null);
                         this.setVisible(false);
                     }
                }
                   
            }
             else{
                           new MensajeBeep().showMessageDialog(this, "Nombre de Usuario O Contraseña Incorrectos","",JOptionPane.WARNING_MESSAGE,icono);  
             }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"",JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
     
     
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCan;
    private javax.swing.JButton botRegis;
    private javax.swing.JTextField cod1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField pass1;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
