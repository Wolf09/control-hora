/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliares;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wjhonatan
 */
public class MiModelo extends DefaultTableModel
{
   public boolean isCellEditable (int row, int column)
   {
       //tabla ineditable
       return false;
   }
}
