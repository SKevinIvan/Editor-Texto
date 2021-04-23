/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.swing.*;

/**
 * Clase que integra una etiqueta para el título de la pestaña, un botón para
 * cerrarla y un icono
 *
 * @author kevin2
 */
public class CmpntTabPane extends JPanel {

    //Declaración de las variables para la clase
    private JTabbedPane txtPane;
    private int tipoX;
    private ImageIcon image;
    //Fin de la declaración de las variables para la clase

    /**
     * Constructor de la clase PanelTab que inicaliza las variables
     *
     * @param txtPane El contenedor de pestañas en donde se le añadirá el
     * componente a la pestaña
     * @param tipoX Tipo de X que se mostrará en el botón de cierre
     * @param image Icono que se le colocará en la pestaña
     */
    public CmpntTabPane(JTabbedPane txtPane, int tipoX, ImageIcon image) {
        this.txtPane = txtPane;
        this.tipoX = tipoX;
        this.image = image;

        establecerEncabezado();
    }

    /**
     * Método de la clase que incorpora la etiqueta y el icono junto con el del
     * botón de la X
     *
     */
    private void establecerEncabezado() {
        if (txtPane != null) {
            setOpaque(false);
            JLabel titulo = new JLabel() {
                @Override
                public String getText() {
                    int i = txtPane.indexOfTabComponent(CmpntTabPane.this);
                    if (i != -1) {
                        return txtPane.getTitleAt(i);
                    }
                    return null;
                }
            };
            titulo.setIcon(image);
            add(titulo);
            titulo.setBorder(null);
            JButton button = new BotonX(txtPane, this, tipoX);
            add(button);
        }
    }

    public JTabbedPane getTxtPane() {
        return txtPane;
    }

    public void setTxtPane(JTabbedPane txtPane) {
        this.txtPane = txtPane;
    }

    public int getTipoX() {
        return tipoX;
    }

    public void setTipoX(int tipoX) {
        this.tipoX = tipoX;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

}
