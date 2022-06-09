/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ziconos;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Advertencia implements Icon{

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        try{
        Image imagen = new ImageIcon(getClass().getResource("/zimagenes/advertencia.jpg")).getImage();
        g.drawImage(imagen, x, y, 100, 100, c);
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }

    @Override
    public int getIconWidth() {
        return 100;
    }

    @Override
    public int getIconHeight() {
        return 100;
    }
 
}

