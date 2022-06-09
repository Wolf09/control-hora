/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

//import com.inet.jortho.FileUserDictionary;
//import com.inet.jortho.SpellChecker;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author root
 */
public class MyRen3 implements TableCellRenderer{

    private JTextArea comp;
 
    public MyRen3 () {
        comp = new JTextArea();
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String v= value.toString();
         comp.setFont (new Font("Lucida Grande", Font.PLAIN, 11)); 
            comp.setLineWrap(true);
            comp.setWrapStyleWord(true);
            comp.setEditable(false);
            comp.setText(v);
            Color miColor=new Color(220,220,220);
            if(row%2==0){
                comp.setBackground(Color.WHITE);
                comp.setForeground(Color.BLACK);    
            }
            else{
                comp.setBackground(miColor);
                comp.setForeground(Color.BLACK);
            }
        return comp;
    }
    
}
