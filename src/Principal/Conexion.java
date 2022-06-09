/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ziconos.FatalError;

/**
 *
 * @author WjhonB
 */
public class Conexion {
    Connection ConexionUser=null;
    private FatalError icono;
    public Conexion(){
    icono =new FatalError();
    }
    public Connection conexion(){
        String sep = System.getProperty("line.separator");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ConexionUser=DriverManager.getConnection("jdbc:mysql://167.157.10.31:3306/docenestuclas","user1","the126%$k1");
        } catch (Exception ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error de Conexion, Al Parecer La Red (Wi-Fi)esta Baja en Conexion, Porfavor Intente Mas Tarde"+sep+"Si El problema persiste Consulte Al Administrador del Servidor, El Programa se cerrara!!!", "", JOptionPane.WARNING_MESSAGE, icono);
            new Control().cerrarApp();
        }
        return ConexionUser;
    }
    
}
