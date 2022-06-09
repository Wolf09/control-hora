/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author WjhonB
 */
public class Principal1 {
      public static void main(String args[]){

        if(new Control().comprobar()){
        try{
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                UIManager.put("OptionPane.messageForeground", Color.white);
                UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.BOLD, 12));
                //new Font("Tahoma", Font.BOLD, 12);
                //UIManager.put("ProgressBar.opaque", true);
               // UIManager.put("ProgressBar.background", new Color(0,153,255));
               // UIManager.put("ProgressBar.foreground", new Color(0,153,255));
               // UIManager.put("ProgressBar.selectionBackground", new Color(0,153,255));
               // UIManager.put("ProgressBar.selectionForeground", new Color(0,153,255));
                //UIManager.put("OptionPane.foreground", Color.white);
                //UIManager.put("nimbusBase", new Color(255,255,255));
                //UIManager.put("nimbusBlueGrey", new Color(255,255,255));
                UIManager.put("control", new Color(0,81,61));
                //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                 }
       catch (Exception e)
               {
                e.printStackTrace();
               }
        ControlaHora hor = new ControlaHora();
       }
        else{
            System.exit(0);
        }

    }
}
