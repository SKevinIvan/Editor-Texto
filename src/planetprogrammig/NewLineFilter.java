/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetprogrammig;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

/**
 *
 * @author por_s
 */
public class NewLineFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
            throws BadLocationException {
        if ("\n".equals(str)) {
            str = addWhiteSpace(fb.getDocument(), offs);
        }

        super.insertString(fb, offs, str, a);
    }

    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
            throws BadLocationException {
        if ("\n".equals(str)) {
            str = addWhiteSpace(fb.getDocument(), offs);
        }

        super.replace(fb, offs, length, str, a);
    }

    public String addWhiteSpace(Document doc, int offset)
            throws BadLocationException {
        StringBuilder whiteSpace = new StringBuilder("\n");
        Element rootElement = doc.getDefaultRootElement();
        int line = rootElement.getElementIndex(offset);
        int i = rootElement.getElement(line).getStartOffset();
        System.out.println(doc.getText(i, 1)+" :Dato");
        while (true) {
            String temp = doc.getText(i, 1);
            if (temp.equals("\t")) {
                i++;
                whiteSpace.append(temp);
            } else {
                break;
            }
        }
  
        return whiteSpace.toString();
    }

    private static void createAndShowUI() {
        JTextArea textArea = new JTextArea(5, 50);
        AbstractDocument doc = (AbstractDocument) textArea.getDocument();
        doc.setDocumentFilter(new NewLineFilter());

        JFrame frame = new JFrame("NewLineFilter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(textArea));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowUI();
            }
        });
    }
}
