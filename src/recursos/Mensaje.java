/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase que permite enviar mensajes por pantalla
 *
 * @author kevin2
 */
public class Mensaje {

    /**
     * Método para enviar mensajes de error si es a pantalla aparece con el
     * icono de tache (X)
     *
     * @param jf Objeto de tipo JFrame para que se despliege en pantalla como
     * ventana emergente
     * @param s Cadena con el mensaje a desplegar
     */
    public static void error(JFrame jf, String s) {
        JOptionPane.showMessageDialog(jf, s, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Método para enviar mensajes de éxito si es a pantalla aparece con el
     * icono de información (i)
     *
     * @param jf Objeto de tipo JFrame se despliega en pantalla como ventana
     * emergente
     * @param s Cadena con el mensaje a desplegar
     */
    public static void exito(JFrame jf, String s) {
        JOptionPane.showMessageDialog(jf, s, "Mensaje de éxito", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Método para enviar mensajes de pregunta a pantalla aparece con el icono
     * de interrrogación (?)
     *
     * @param jf Objeto de tipo JFrame se despliega en pantalla como ventana
     * emergente
     * @param s Cadena con el mensaje a desplegar
     * @return Valor entero, si la respuesta es si regresa 1, sino regresa 0
     */
    public static int pregunta(JFrame jf, String s) {
        return JOptionPane.showConfirmDialog(jf, s, "Mensaje de pregunta", JOptionPane.YES_NO_OPTION);
    }
    
    /**
     * Método para enviar mensajes de advertencia si es a pantalla aparece con el
     * icono de interrogacion
     *
     * @param jf Objeto de tipo JFrame para que se despliege en pantalla como
     * ventana emergente
     * @param s Cadena con el mensaje a desplegar
     */
    public static void advertencia(JFrame jf, String s) {
        JOptionPane.showMessageDialog(jf, s, "Mensaje de advertencia", JOptionPane.WARNING_MESSAGE);

    }
}
