/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControlHora;
import Principal.Control;
import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Auxiliares.MyRen3;
import Principal.Conexion;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien2;
import ziconos.BotAct;
import ziconos.BotConsul;
import ziconos.BotInsertar;
import ziconos.BotTodos;

/**
 *
 * @author WjhonB
 */
public class ControlarHoraEst extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private boolean band7;
    public ControlarHoraEst() {
        initComponents();
        band7=true;
        this.setTitle("CONTROL DE HORARIO ESTUDIANTE");
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setLocation(0, 0);
        this.setMinimumSize(new Dimension(ancho-20, alto-50));
       this.setMaximumSize(new Dimension(ancho-10, alto-20));
        tabA.getTableHeader().setPreferredSize(new Dimension(10,30));
        tabA.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabA.setRowHeight(60);
        this.addWindowListener ( new WindowAdapter() { 
        public void windowClosing(WindowEvent e) { 
            new Control().cerrarApp();
        } 
        }); 
        try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/logoreloj.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        icono =new Advertencia();
        icono1=new BotConsul();
        icono2=new BotTodos();
        icono3=new BotInsertar();
        icono4=new BotAct();
        icono5=new Bien2();
        botCPF3.setIcon(icono1);
        botcPA3.setIcon(icono1);
        botCPAN3.setIcon(icono1);
        
    }

    public void mostrardatos(){
        MiModelo modelo = new MiModelo();
        modelo.addColumn("codigo");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("NOMBRES");
         tabA.setModel(modelo);
        TableColumnModel columnModel = tabA.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0);
       columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        String sql = "";

        sql="SELECT codigoest,apellidos,nombres FROM estudiante GROUP BY codigoest ORDER BY apellidos";
     
    Object []datos = new Object [4];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);  
                datos[2]=rs.getString(3);  
                modelo.addRow(datos);
            }
            tabA.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    public void mostrardatos4(String apel){
        MiModelo modelo = new MiModelo();
        modelo.addColumn("codigo");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("NOMBRES");
         tabA.setModel(modelo);
        TableColumnModel columnModel = tabA.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0);
       columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        String sql = "";

        sql="SELECT codigoest,apellidos,nombres FROM estudiante WHERE apellidos like '%"+apel+"%' GROUP BY codigoest ORDER BY apellidos";
     
    Object []datos = new Object [4];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);  
                datos[2]=rs.getString(3);  
                modelo.addRow(datos);
            }
            tabA.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    
     public void mostrardatos1(String feci,String fecf){
        MiModelo modelo = new MiModelo();
        modelo.addColumn("codigo");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("<html>HORA/INGRESO<br>MAÑANA</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>MAÑANA</html>");
        modelo.addColumn("<html>HORA/INGRESO<br>TARDE</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>TARDE</html>");
        modelo.addColumn("<html>HORA/INGRESO<br>NOCHE</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>NOCHE</html>");
         tabA.setModel(modelo);
        TableColumnModel columnModel = tabA.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0);
       columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(0).setCellRenderer(new MyRen3());
        columnModel.getColumn(1).setCellRenderer(new MyRen3());
        columnModel.getColumn(2).setCellRenderer(new MyRen3());
        columnModel.getColumn(3).setCellRenderer(new MyRen3());
        columnModel.getColumn(4).setCellRenderer(new MyRen3());
        columnModel.getColumn(5).setCellRenderer(new MyRen3());
        columnModel.getColumn(6).setCellRenderer(new MyRen3());
        columnModel.getColumn(7).setCellRenderer(new MyRen3());
        columnModel.getColumn(8).setCellRenderer(new MyRen3());;
        String sql = "";

        sql="SELECT estudiante.codigoest,apellidos,nombres,horaim,horasm,horait,horast,horain,horasn FROM estudiante,horaestudiante WHERE estudiante.codigoest=horaestudiante.codigoest AND llave>='"+feci+"' AND llave<='"+fecf+"' GROUP BY horaestudiante.codigoest ORDER BY apellidos";
     
    Object []datos = new Object [10];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
             while(rs.next()){
                    datos[0]=rs.getString(1);
                    datos[1]=rs.getString(2);
                    datos[2]=rs.getString(3); 
    
                    if(rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    }else if(rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }
                    else if(rs.getDate(6)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                     }else if(rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                     }else if(rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(6)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(7)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]=""; 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(8)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(9)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                       // Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                       
                     }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(6)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]=""; 
   
                       
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[5]="";
                        datos[6]="";   
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
    
                    }else if(rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
    
                    }else if(rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]=""; 
    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
    
                    }else if(rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
    
                    }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(8)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(7)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(9)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(6)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";

                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[5]="";
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
  
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
  
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
  
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
  
                     }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(4)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                     }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                     }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(6)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1); 
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1); 
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1); 
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(8)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                     }else if(!(rs.getDate(4)==null)&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null){
                         Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }else if(!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        /*
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
*/
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }
                    
                  
                     
                modelo.addRow(datos);
         }
            tabA.setModel(modelo);
        } catch (Exception ex) {
            Logger.getLogger(ControlarHoraEst.class.getName()).log(Level.SEVERE, null, ex);
             //new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
             
        }
    
    }
    
      
     
     
     
     
     
     //              HASTA AQUI!!!!!!!!!!!!!!!!! --------------------------------------------------------------------------
     
     
     
     
     
     
     
     
     
     public void mostrardatos2(String apel,String feci,String fecf){
        MiModelo modelo = new MiModelo();
        modelo.addColumn("codigo");
        modelo.addColumn("APELLIDOS");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("<html>HORA/INGRESO<br>MAÑANA</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>MAÑANA</html>");
        modelo.addColumn("<html>HORA/INGRESO<br>TARDE</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>TARDE</html>");
        modelo.addColumn("<html>HORA/INGRESO<br>NOCHE</html>");
        modelo.addColumn("<html>HORA/SALIDA<br>NOCHE</html>");
         tabA.setModel(modelo);
        TableColumnModel columnModel = tabA.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0);
       columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(0).setCellRenderer(new MyRen3());
        columnModel.getColumn(1).setCellRenderer(new MyRen3());
        columnModel.getColumn(2).setCellRenderer(new MyRen3());
        columnModel.getColumn(3).setCellRenderer(new MyRen3());
        columnModel.getColumn(4).setCellRenderer(new MyRen3());
        columnModel.getColumn(5).setCellRenderer(new MyRen3());
        columnModel.getColumn(6).setCellRenderer(new MyRen3());
        columnModel.getColumn(7).setCellRenderer(new MyRen3());
        columnModel.getColumn(8).setCellRenderer(new MyRen3());
        String sql = "";

         sql="SELECT estudiante.codigoest,apellidos,nombres,horaim,horasm,horait,horast,horain,horasn FROM estudiante,horaestudiante WHERE estudiante.codigoest=horaestudiante.codigoest AND apellidos like '%"+apel+"%' AND llave>='"+feci+"' AND llave<='"+fecf+"' GROUP BY horaestudiante.codigoest ORDER BY apellidos";
     
    Object []datos = new Object [10];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
             while(rs.next()){
                    datos[0]=rs.getString(1);
                    datos[1]=rs.getString(2);
                    datos[2]=rs.getString(3); 
    
                    if(rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    }else if(rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }
                    else if(rs.getDate(6)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                     }else if(rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                     }else if(rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(6)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(7)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]=""; 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(8)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
                    }else if(rs.getDate(4)==null&&rs.getDate(9)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                       // Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                       
                     }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                       
                    }else if(rs.getDate(5)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(6)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]=""; 
   
                       
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[5]="";
                        datos[6]="";   
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
    
                    }else if(rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
    
                    }else if(rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]=""; 
    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]=""; 
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
    
                    }else if(rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
    
                    }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(8)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(7)==null&&!(rs.getDate(6)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(9)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(6)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[3]="";
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";

                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[5]="";
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                        
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
  
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
  
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(5)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
  
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                       
  
                     }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(4)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4);
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                     }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                     }else if(rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(6)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)&&!(rs.getDate(7)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
  
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(8)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(9)==null&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4); 
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(8)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    
                    }else if(rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1); 
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(4)==null&&!(rs.getDate(5)==null)&&!(rs.getDate(6)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1); 
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&rs.getDate(5)==null&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)){
                        Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1); 
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        datos[4]="";
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[7]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);
                        datos[8]="";
                    
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&!(rs.getDate(9)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[6]="";
                        datos[7]="";
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(9)==null&&!(rs.getDate(8)==null)){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[3]="";
                        datos[4]="";
                        datos[5]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[6]="";
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                     }else if(!(rs.getDate(4)==null)&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null){
                         Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        //Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }else if(rs.getDate(4)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null&&!(rs.getDate(5)==null)){
                        //Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        //Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        //Time time3=rs.getTime(6);
                        //Time time4=rs.getTime(7);
                        //Time time5=rs.getTime(8);
                        //Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }else if(!(rs.getDate(5)==null)&&!(rs.getDate(4)==null)&&!(rs.getDate(6)==null)&&!(rs.getDate(7)==null)&&!(rs.getDate(8)==null)&&!(rs.getDate(9)==null)){
                        Date fecha1= rs.getDate(4);
                        Date fecha2= rs.getDate(5);
                        Date fecha3= rs.getDate(6);
                        Date fecha4= rs.getDate(7);
                        Date fecha5= rs.getDate(8);
                        Date fecha6= rs.getDate(9);
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        datos[3]=formato1.format(fecha1)+"  "+formato2.format(time1);
                        datos[4]=formato1.format(fecha2)+"  "+formato2.format(time2);
                        datos[5]=formato1.format(fecha3)+"  "+formato2.format(time3);
                        datos[6]=formato1.format(fecha4)+"  "+formato2.format(time4);
                        datos[7]=formato1.format(fecha5)+"  "+formato2.format(time5);
                        datos[8]=formato1.format(fecha6)+"  "+formato2.format(time6);
                        
                    }else if(rs.getDate(4)==null&&rs.getDate(5)==null&&rs.getDate(6)==null&&rs.getDate(7)==null&&rs.getDate(8)==null&&rs.getDate(9)==null){
                        //Date fecha1= rs.getDate(4);
                        //Date fecha2= rs.getDate(5);
                        //Date fecha3= rs.getDate(6);
                        //Date fecha4= rs.getDate(7);
                        //Date fecha5= rs.getDate(8);
                        //Date fecha6= rs.getDate(9);
                        /*
                        Time time1=rs.getTime(4);
                        Time time2=rs.getTime(5);
                        Time time3=rs.getTime(6);
                        Time time4=rs.getTime(7);
                        Time time5=rs.getTime(8);
                        Time time6=rs.getTime(9);
*/
                        SimpleDateFormat formato1= new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                        //datos[3]=formato1.format(fecha1)+" "+formato2.format(time1);
                        datos[3]="";
                        //datos[4]=formato1.format(fecha2)+" "+formato2.format(time2);
                        //datos[5]=formato1.format(fecha3)+" "+formato2.format(time3);
                        datos[4]="";
                        datos[5]="";
                        datos[6]="";
                        //datos[6]=formato1.format(fecha4)+" "+formato2.format(time4); 
                        datos[7]="";
                        //datos[7]=formato1.format(fecha5)+" "+formato2.format(time5);
                        datos[8]="";
                        //datos[8]=formato1.format(fecha6)+" "+formato2.format(time6);

                    }
                    
                  
                     
                modelo.addRow(datos);
         }
            tabA.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        him = new javax.swing.JMenuItem();
        hsm = new javax.swing.JMenuItem();
        hit = new javax.swing.JMenuItem();
        hst = new javax.swing.JMenuItem();
        hin = new javax.swing.JMenuItem();
        hsn = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabA = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        botCPF3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cPA3 = new javax.swing.JTextField();
        botReg2 = new javax.swing.JButton();
        botAct2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        fi2 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        ff2 = new com.toedter.calendar.JDateChooser();
        botcPA3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cPAN3 = new javax.swing.JTextField();
        botCPAN3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        him.setText("HORA DE INGRESO MAÑANA");
        him.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                himActionPerformed(evt);
            }
        });
        capturar.add(him);

        hsm.setText("HORA DE SALIDA MAÑANA");
        hsm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsmActionPerformed(evt);
            }
        });
        capturar.add(hsm);

        hit.setText("HORA DE INGRESO TARDE");
        hit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hitActionPerformed(evt);
            }
        });
        capturar.add(hit);

        hst.setText("HORA DE SALIDA TARDE");
        hst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hstActionPerformed(evt);
            }
        });
        capturar.add(hst);

        hin.setText("HORA DE INGRESO NOCHE");
        hin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hinActionPerformed(evt);
            }
        });
        capturar.add(hin);

        hsn.setText("HORA DE SALIDA NOCHE");
        hsn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hsnActionPerformed(evt);
            }
        });
        capturar.add(hsn);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabA.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabA);

        jPanel3.setBackground(new java.awt.Color(0, 0, 123));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "CONTROL DE HORARIO ESTUDIANTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        botCPF3.setBackground(new java.awt.Color(0, 0, 0));
        botCPF3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPF3.setForeground(new java.awt.Color(255, 255, 255));
        botCPF3.setText("Consultar");
        botCPF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPF3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Consultar por Apellidos:");

        botReg2.setBackground(new java.awt.Color(0, 0, 0));
        botReg2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botReg2.setForeground(new java.awt.Color(255, 255, 255));
        botReg2.setText("REGISTRAR NUEVO");
        botReg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botReg2ActionPerformed(evt);
            }
        });

        botAct2.setBackground(new java.awt.Color(0, 0, 0));
        botAct2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botAct2.setForeground(new java.awt.Color(255, 255, 255));
        botAct2.setText("TODOS");
        botAct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAct2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha Inicial:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fecha Final:");

        botcPA3.setBackground(new java.awt.Color(0, 0, 0));
        botcPA3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botcPA3.setForeground(new java.awt.Color(255, 255, 255));
        botcPA3.setText("Consultar");
        botcPA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botcPA3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Consultar por Apellidos (NUEVA HORA):");

        botCPAN3.setBackground(new java.awt.Color(0, 0, 0));
        botCPAN3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPAN3.setForeground(new java.awt.Color(255, 255, 255));
        botCPAN3.setText("Consultar");
        botCPAN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPAN3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fi2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ff2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPA3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botcPA3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(botCPF3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botReg2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botAct2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPAN3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCPAN3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cPA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botcPA3)
                    .addComponent(jLabel12)
                    .addComponent(cPAN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCPAN3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botAct2)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(botCPF3)
                        .addComponent(botReg2))
                    .addComponent(fi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ff2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Atras");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void himActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_himActionPerformed
        int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horaim='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, formato.format(fecha1));
                                    pst.setString(2, null);
                                    pst.setString(3, null);
                                    pst.setString(4, null);
                                    pst.setString(5, null);
                                    pst.setString(6, null);
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                        
                      }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_himActionPerformed

    private void hsmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsmActionPerformed
        int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horasm='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, null);
                                    pst.setString(2, formato.format(fecha1));
                                    pst.setString(3, null);
                                    pst.setString(4, null);
                                    pst.setString(5, null);
                                    pst.setString(6, null);
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Salida Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                        
                   }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_hsmActionPerformed

    private void hitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hitActionPerformed
          int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horait='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, null);
                                    pst.setString(2, null);
                                    pst.setString(3, formato.format(fecha1));
                                    pst.setString(4, null);
                                    pst.setString(5, null);
                                    pst.setString(6, null);
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Ingreso Tarde NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                    }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_hitActionPerformed

    private void hstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hstActionPerformed
          int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horast='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, null);
                                    pst.setString(2, null);
                                    pst.setString(3, null);
                                    pst.setString(4, formato.format(fecha1));
                                    pst.setString(5, null);
                                    pst.setString(6, null);
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Salida Tarde NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                   }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_hstActionPerformed

    private void hinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hinActionPerformed
        int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horain='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, null);
                                    pst.setString(2, null);
                                    pst.setString(3, null);
                                    pst.setString(4, null);
                                    pst.setString(5, formato.format(fecha1));
                                    pst.setString(6, null);
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Ingreso Noche NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                   }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_hinActionPerformed

    private void hsnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hsnActionPerformed
         int fila= tabA.getSelectedRow();
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fila>=0){
            Estudiante1 doc=new Estudiante1(new java.awt.Frame(), true,tabA.getValueAt(fila, 0).toString());
            if(doc.band==true){
                        if(comprobar(tabA.getValueAt(fila, 0).toString(),formato1.format(fecha1),formato2.format(fecha1))==true){
                                   try {
                                PreparedStatement pst = cn.prepareStatement ("UPDATE horaestudiante SET horasn='"+formato.format(fecha1)+"' WHERE codigoest='"+tabA.getValueAt(fila, 0).toString()+"' AND llave>='"+formato1.format(fecha1)+"' AND llave<='"+formato2.format(fecha1)+"'");
                                pst.executeUpdate();
                                new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, error al actualizar"+sep+"La hora de ingreso Mañana NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                //JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                        }else{
                                try {
                                    PreparedStatement pst = cn.prepareStatement("INSERT INTO horaestudiante(horaim,horasm,horait,horast,horain,horasn,llave,codigoest) VALUES (?,?,?,?,?,?,?,?)");
                                    pst.setString(1, null);
                                    pst.setString(2, null);
                                    pst.setString(3, null);
                                    pst.setString(4, null);
                                    pst.setString(5, null);
                                    pst.setString(6, formato.format(fecha1));
                                    pst.setString(7, formato.format(fecha1));
                                    pst.setString(8, tabA.getValueAt(fila, 0).toString());
                                    pst.executeUpdate();
                                    new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                                    mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
                                } catch (Exception ex) {
                                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Error interno"+sep+"Salida Noche NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                        }
                    }
             else{
                 new MensajeBeep().showMessageDialog(this, "Usted no esta autorizado para Registrarse como este Usuario","Fila",JOptionPane.WARNING_MESSAGE,null);
             }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_hsnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        Principal.ControlaHora cl=new Principal.ControlaHora();
        cl.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void botCPF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPF3ActionPerformed
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if(fi2.getDate()==null||ff2.getDate()==null){
            new MensajeBeep().showMessageDialog(this, "NINGUNA DE LAS FECHAS DEBE SER VACIA","FECHA",JOptionPane.WARNING_MESSAGE,null);
        }else{
            mostrardatos1(formato1.format(fi2.getDate()),formato2.format(ff2.getDate()));
        }
        limpiar();
    }//GEN-LAST:event_botCPF3ActionPerformed

    private void botReg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botReg2ActionPerformed
        mostrardatos();
        limpiar();
    }//GEN-LAST:event_botReg2ActionPerformed

    private void botAct2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAct2ActionPerformed
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        mostrardatos1(formato1.format(fecha1),formato2.format(fecha1));
        /*
        him.setEnabled(false);
        hsm.setEnabled(false);
        hit.setEnabled(false);
        hst.setEnabled(false);
        hin.setEnabled(false);
        hsn.setEnabled(false);
        */
    }//GEN-LAST:event_botAct2ActionPerformed

    private void botcPA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botcPA3ActionPerformed
        String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato1= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat formato2= new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        mostrardatos2(cPA3.getText(),formato1.format(fecha1),formato2.format(fecha1));
        limpiar();
    }//GEN-LAST:event_botcPA3ActionPerformed

    private void botCPAN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPAN3ActionPerformed
        mostrardatos4(cPAN3.getText());
        limpiar();
    }//GEN-LAST:event_botCPAN3ActionPerformed
private void limpiar(){
    cPAN3.setText("");
    cPA3.setText("");
}


    private boolean comprobar(String cod,String feci,String fecf){
             String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT codigoest FROM horaestudiante WHERE codigoest='"+cod+"' AND llave>='"+feci+"' AND llave<='"+fecf+"'";
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
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" ","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return band4;
    
    
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAct2;
    private javax.swing.JButton botCPAN3;
    private javax.swing.JButton botCPF3;
    private javax.swing.JButton botReg2;
    private javax.swing.JButton botcPA3;
    private javax.swing.JTextField cPA3;
    private javax.swing.JTextField cPAN3;
    private javax.swing.JPopupMenu capturar;
    private com.toedter.calendar.JDateChooser ff2;
    private com.toedter.calendar.JDateChooser fi2;
    private javax.swing.JMenuItem him;
    private javax.swing.JMenuItem hin;
    private javax.swing.JMenuItem hit;
    private javax.swing.JMenuItem hsm;
    private javax.swing.JMenuItem hsn;
    private javax.swing.JMenuItem hst;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabA;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
