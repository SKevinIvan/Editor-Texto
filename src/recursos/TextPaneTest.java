/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.*;
import javax.swing.*;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Clase que sirve para colorear o dar otro formato a los caracteres del texto
 * en un JTextPane
 *
 * @author kevin2
 */
public class TextPaneTest {

    //Agregar iconos al TextPane
    // txtPanelLexico.insertIcon(image);
    //Agregar icono a la pestaña junto al titulo y el componente
    /**
     * Método que pinta el texto recibido y lo muestra en un JTextPane Después
     * de esto, el texto volverá a su normalidad
     *
     * @param tp JTextPane en donde se colocara el texto con formato
     * @param msg Mensaje que se le dará formato
     * @param c Color que se pintará el texto
     */
    public static void appendToPane(JTextPane tp, String msg, Color c) {

        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, true);
        tp.replaceSelection(msg);

        colorearNormal(tp, Color.black);
    }

    /**
     * Método que sirve para poner en estlio el texto que se recibe Despues de
     * esto el texto vuelve a la normalidad
     *
     * @param txtPanelSalida TextPane en donde se colocará el estilo
     * @param estilo Cadena que contendra el estilo: Normal, Negritas,
     * Cursiva...
     * @param palabra Cadena de texto donde se le impactará el estilo
     */
    public static void ponerEstilo(JTextPane txtPanelSalida, String estilo, String palabra) {
        try {
            SimpleAttributeSet att = new SimpleAttributeSet();
            if (estilo.equals("NEGRITAS")) {
                StyleConstants.setBold(att, true);
            } else if (estilo.equals("CURSIVA")) {
                StyleConstants.setItalic(att, true);
            } else if (estilo.equals("CURSIVA Y NEGRITA")) {
                StyleConstants.setItalic(att, true);
                StyleConstants.setBold(att, true);
            } else {

            }
            //Marcador
            //StyleConstants.setBackground(att, Color.BLUE);

            txtPanelSalida.getStyledDocument().insertString(txtPanelSalida.getStyledDocument().getLength(), palabra, att);

            colorearNormal(txtPanelSalida, Color.black);
        } catch (BadLocationException ex) {
            Mensaje.error(null, "Ha ocurrido un error al colocar un texto en negritas");
        }
    }

    private static void colorearNormal(JTextPane tp, Color c) {
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        tp.setCaretPosition(tp.getDocument().getLength());
        tp.setCharacterAttributes(aset2, true);
        tp.replaceSelection("");

    }

    public static void ponerEspacio(JTextPane tp) {
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        tp.setCaretPosition(tp.getDocument().getLength());
        tp.setCharacterAttributes(aset2, true);
        tp.replaceSelection(" ");

    }
    public static void ponerTab(JTextPane tp) {
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        tp.setCaretPosition(tp.getDocument().getLength());
        tp.setCharacterAttributes(aset2, true);
        tp.replaceSelection("\t");

    }

    public static void ponerEnter(JTextPane tp) {
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        tp.setCaretPosition(tp.getDocument().getLength());
        tp.setCharacterAttributes(aset2, true);
        tp.replaceSelection("\n");
    }
}
