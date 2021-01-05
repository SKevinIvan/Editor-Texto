/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

/**
 * Clase que facilita operaciones básicas con objetos gráficos, para dar una
 * mejor apariencia en uso
 *
 * @author kevin2
 */
public class CtrlInterfaz {

    /**
     * Método para limpiar cajas de texto y/o TextPane
     *
     * @param jts Arreglo no definido de cajas de texto, JTextPane
     */
    public static void limpia(Object... jts) {
        for (Object x : jts) {
            if (x instanceof JTextField) {
                ((JTextField) x).setText("");
            }
            if (x instanceof JTextPane) {
                ((JTextPane) x).setText("");
            }

        }
    }

    /**
     * Método para habilitar o deshabilitar una cajas de texto, botones,
     * comboBox, menuItems y/o toolBars
     *
     * @param b Valor boleano que especifica si habilita o deshabilita un obj
     * dependiendo de su valor de true=habilita o false=deshabilita
     * @param objs Arreglo no definido de objetos como cajas de texto, botones,
     * comboBox, menuItems y/o toolBars
     */
    public static void habilita(boolean b, Object... objs) {
        for (Object obj : objs) {
            if (obj instanceof JTextField) {
                ((JTextField) obj).setEnabled(b);
            }
            if (obj instanceof JButton) {
                ((JButton) obj).setEnabled(b);
            }
            if (obj instanceof JComboBox) {
                ((JComboBox) obj).setEnabled(b);
            }
            if (obj instanceof JMenuItem) {
                ((JMenuItem) obj).setEnabled(b);
            }
            if (obj instanceof JToolBar) {
                ((JToolBar) obj).setEnabled(b);
            }
        }
    }

    /**
     * Método que permite cambiar el cursor a un objeto de tipo caja, botón,
     * comboBox, menuItem y/o toolBar
     *
     * @param obj Objeto de tipo caja, botón, comboBox, menuItem o toolBar
     */
    public static void cambia(Object obj) {
        habilita(true, obj);
        if (obj instanceof JTextField) {
            selecciona((JTextField) obj);
        }
        if (obj instanceof JButton) {
            ((JButton) obj).requestFocus();
        }
        if (obj instanceof JComboBox) {
            ((JComboBox) obj).requestFocus();
        }
        if (obj instanceof JMenuItem) {
            ((JMenuItem) obj).requestFocus();
        }
        if (obj instanceof JToolBar) {
            ((JToolBar) obj).requestFocus();
        }
    }

    /**
     * Método que permite la selección de todo el texto contenido en una caja de
     * texto
     *
     * @param jt Objeto de tipo caja de texto
     */
    public static void selecciona(JTextField jt) {
        jt.setSelectionStart(0);
        jt.setSelectionEnd(jt.getText().length());
        jt.requestFocus();
    }

}
