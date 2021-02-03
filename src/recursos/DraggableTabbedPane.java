/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JTabbedPane;

public class DraggableTabbedPane extends JTabbedPane {

    private boolean dragging = false;
    private Image tabImage = null;
    private int currentMouseLocationX = 0;
    private int currentMouseLocationY = 0;
    private int draggedTabIndex = 0;

    public DraggableTabbedPane() {
        super();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (!dragging) {
                    // Gets the tab index based on the mouse position
                    int tabNumber = getUI().tabForCoordinate(DraggableTabbedPane.this, e.getX(), e.getY());

                    if (tabNumber >= 0) {
                        draggedTabIndex = tabNumber;
                        Rectangle bounds = getUI().getTabBounds(DraggableTabbedPane.this, tabNumber);

                        // Paint the tabbed pane to a buffer
                        Image totalImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Graphics totalGraphics = totalImage.getGraphics();
                        totalGraphics.setClip(bounds);
                        // Don't be double buffered when painting to a static image.
                        setDoubleBuffered(false);
                        // Are we dragging?

                        paintComponent(totalGraphics);

                        // Paint just the dragged tab to the buffer
                        tabImage = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB);
                        Graphics graphics = tabImage.getGraphics();
                        graphics.drawImage(totalImage, 0, 0, bounds.width, bounds.height, bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, DraggableTabbedPane.this);

                        dragging = true;
                        repaint();
                    }
                } else {
                    currentMouseLocationX = e.getX();
                    if (currentMouseLocationY != 0) {
                        currentMouseLocationY = e.getY();
                    }
                    // Need to repaint
                    repaint();
                }

                super.mouseDragged(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (dragging) {
                    int tabNumber = getUI().tabForCoordinate(DraggableTabbedPane.this, e.getX(), e.getY());

                    if (tabNumber >= 0) {
                        Component comp = getComponentAt(draggedTabIndex);
                        String title = getTitleAt(draggedTabIndex);
                        removeTabAt(draggedTabIndex);
                        insertTab(title, null, comp, null, tabNumber);
                    } else {
                        repaint();
                    }
                }

                dragging = false;
                tabImage = null;
                currentMouseLocationX = 0;
                currentMouseLocationY = 0;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dragging) {
            // Draw the dragged tab
            g.drawImage(tabImage, currentMouseLocationX, currentMouseLocationY, (ImageObserver) this);
        }

    }

}
