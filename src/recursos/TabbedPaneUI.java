/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Clase que sirve para poner formato a las pestañas del TabbedPane una vez que 
 * se seleccione una cambia de color junto con su contorno
 * 
 * @author kevin2
 */
public class TabbedPaneUI extends BasicTabbedPaneUI {

    //Declaración de variables de tipo Color
    public Color colorSeleccion = new Color(255, 255, 255);
    public Color colorNoSeleccion = new Color(204, 204, 204);
    public Color colorBordeContenedor = colorSeleccion;
    //Fin de la declaración de variables de tipo Color
    
    /**
     * Método sobreescrito que pinta de diferente color las pestañas si son seleccionadas o no.
     * 
     * @param g Objeto de tipo Graphics 
     * @param tabPlacement Número total de pestañas
     * @param tabIndex Indice de la pestaña
     * @param x Posición X de la pestaña
     * @param y Posición Y de la pestaña
     * @param w Ancho de la pestaña
     * @param h Alto de la pestaña
     * @param isSelected Para cada pestaña si es seleccionada o no
     */
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor((isSelected) ? colorSeleccion : colorNoSeleccion);
        g2.fillRect(rects[tabIndex].x, rects[tabIndex].y,
                rects[tabIndex].width, rects[tabIndex].height);
    }

    /**
     * Método sobreescrito que sirve para pintar el borde de la pestaña seleccionada
     * 
     * @param g Objeto de tipo Graphics 
     * @param tabPlacement Número total de pestañas
     * @param tabIndex Indice de la pestaña
     * @param x Posición X de la pestaña
     * @param y Posición Y de la pestaña
     * @param w Ancho de la pestaña
     * @param h Alto de la pestaña
     * @param isSelected Para cada pestaña si es seleccionada o no
     */
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor((isSelected) ? colorSeleccion : colorNoSeleccion);
        g2.drawRect(x, y, w, h);
    }

    /**
     * Método que sirve para pintar el margen de la pestaña seleccionada
     * 
     * @param g Objeto de tipo Graphics 
     * @param tabPlacement Número de pestañas total
     * @param selectedIndex Indice de la pestaña seleccionada
     */
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        int width = tabPane.getWidth();
        int height = tabPane.getHeight();
        Insets insets = tabPane.getInsets();

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch (tabPlacement) {
            case LEFT:
                x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
                x -= tabAreaInsets.right;
                w -= (x - insets.left);
                break;
            case RIGHT:
                w -= calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
                w += tabAreaInsets.left;
                break;
            case BOTTOM:
                h -= calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                h += tabAreaInsets.top;
                break;
            case TOP:
            default:
                y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
                y -= tabAreaInsets.bottom;
                h -= (y - insets.top);
        }

        if (tabPane.getTabCount() > 0) {
            Color color = UIManager.getColor("TabbedPane.contentAreaColor");
            if (color != null) {
                g.setColor(color);
            } else if (colorBordeContenedor == null || selectedIndex == -1) {
                g.setColor(tabPane.getBackground());
            } else {
                g.setColor(colorBordeContenedor);
            }
            g.fillRect(x, y, w, h);
        }
    }

}
