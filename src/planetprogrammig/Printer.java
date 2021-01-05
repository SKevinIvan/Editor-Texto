/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetprogrammig;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class Printer implements Printable {

    final Component comp;

    public Printer(Component comp) {
        this.comp = comp;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page_index)
            throws PrinterException {
        if (page_index > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart,pYStart);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
}
