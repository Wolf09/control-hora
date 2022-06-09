/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author WjhonB
 */
public class MensajeBeep{
    //public MensajeBeep(){}
    public void showMessageDialog(Component pC, Object m, String t, int mT,Icon icono)
        {
            /*
            UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(0,51,31));
        UI.put("Panel.background",new ColorUIResource(0,51,31));
        UI.put("this.background",new ColorUIResource(0,51,31));
        UI.put("t.background",new ColorUIResource(0,51,31));
        UI.put("mT.background",new ColorUIResource(0,51,31));
*/        
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog( pC, m,t,mT,icono);
        
        }
}
