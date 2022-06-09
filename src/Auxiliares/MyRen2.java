/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class MyRen2 implements TableCellRenderer{

   private JTextArea comp;
   private int colum1;
   private int colum2;
    public MyRen2 (int colum1,int colum2) {
        comp = new JTextArea();
        this.colum1=colum1;
        this.colum2=colum2;
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String v= value.toString();
        Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
         comp.setFont (new Font("Lucida Grande", Font.PLAIN, 11)); 
            comp.setLineWrap(true);
            comp.setWrapStyleWord(true);
            comp.setEditable(false);
            comp.setText(v);
            String aux1=table.getValueAt(row, colum1).toString();
            SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
            String aux2=table.getValueAt(row, colum2).toString();
            Double aux3=Double.parseDouble(aux2);
            Color miColor=new Color(220,220,220);
            Color miColor2=new Color(255,255,160);
            if(row%2==0){
                 try {
                       if( (formato.parse(aux1).before(fecha1)|| formato.parse(aux1).equals(fecha1))&&aux3<100)
                       {
                           comp.setBackground(Color.RED);
                           comp.setForeground(miColor2);
                       }
                       else{
                           comp.setBackground(Color.WHITE);
                           comp.setForeground(Color.BLACK);
                           
                       }
                   } catch (ParseException ex) {
                       Logger.getLogger(MyRen2.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
            else{
                try {
                        if( (formato.parse(aux1).before(fecha1)|| formato.parse(aux1).equals(fecha1))&&aux3<100)
                        {
                            comp.setBackground(Color.RED);
                            comp.setForeground(miColor2);
                        }
                        else{
                           comp.setBackground(miColor);
                           comp.setForeground(Color.BLACK);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(MyRen2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
       
        return comp;
    }
    
}