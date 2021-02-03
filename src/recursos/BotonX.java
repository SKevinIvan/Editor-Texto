/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Clase que sirve para graficar una X o colocar una imagen al botón de la
 * pesataña del TabbedPane para cerrar la pestaña
 *
 * @author kevin2
 */
public class BotonX extends JButton implements MouseListener {

    //Declaración de variables 
    public JTabbedPane tabbedPane;
    public CmpntTabPane paneTab;
    public int tipo;
    //Fin de la declaración de variables 

    /**
     * Constructor de la clase BotonX
     *
     * @param pane TabbedPane donde contiene las pestañas
     * @param btc El componente Pane se le agregará a la pestaña del título de
     * la pestaña
     * @param op Opción que determinará el gráfico del botón de (X)"cierre
     * pestaña"
     */
    public BotonX(JTabbedPane pane, CmpntTabPane btc, int op) {
        tabbedPane = pane;
        this.paneTab = btc;
        tipo = op;
        int size = 16;
        setPreferredSize(new Dimension(size, size));
        setToolTipText("Cerrar Pestaña");
        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
        addMouseListener(this);
        setRolloverEnabled(true);
        setBorder(null);
        //Si el botón se le da Clic, eliminará del TabbedPane la pesataña en el que
        //el botón se encontraba
        addActionListener((ActionEvent e) -> {
            if (tipo == 0) {
                int i = tabbedPane.indexOfTabComponent(BotonX.this.paneTab);
                if (i != -1) {
                    tabbedPane.remove(i);
                }
            } else {

            }
        });
    }

    /**
     * Método que sirve para graficar y pintar una X cuando el Mouse pase sobre
     * ella o colocar una imagen de cerrar
     *
     * @param g Objeto de tipo Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        if (tipo == 0) {
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.GRAY);
            if (getModel().isRollover()) {
                g2.setColor(Color.RED);
            }
            g2.drawLine(5, 5, 12, 12);
            g2.drawLine(12, 5, 5, 12);
            g2.dispose();
        } else {
            //ImageIcon img = new ImageIcon(this.getClass().getResource("../iconos/94776 - stock stop.png"));
            //g2.drawImage(img.getImage(), 0, 0, this);
            g2.dispose();
        }
    }

    /**
     * Método que se activa una vez que el mouse sea cliqueado en el botón
     *
     * @param e Evento Clicked del Mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Método que se activa una vez que el mouse es pasado sobre del componente
     * Pintará el borde si es así.
     *
     * @param e Evento Entered del Mouse
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton) component;
            button.setBorderPainted(true);
        }
    }

    /**
     * Método que se activa una vez que el mouse deje de estar sobre el
     * componente Despintará el borde si es así.
     *
     * @param e Evento Exited del Mouse
     */
    @Override
    public void mouseExited(MouseEvent e) {
        Component component = e.getComponent();
        if (component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton) component;
            button.setBorderPainted(false);
        }
    }

    /**
     * Método que se activa una vez que el mouse se presione sobre el componente
     *
     * @param e Evento Pressed del Mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Método que se activa una vez que el mouse pase sobre el componente
     *
     * @param e Evento Relased del Mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

}
