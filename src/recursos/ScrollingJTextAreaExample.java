/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class ScrollingJTextAreaExample extends JFrame {
    // Worker thread to help periodically append example messages to JTextArea

    Timer timer = new Timer();
    // Merely informative counter, will be displayed with the example messages
    int messageCounter = 0;
    // GUI components
    JScrollPane jScrollPane;
    JTextArea jTextArea;

    public ScrollingJTextAreaExample() {
        initComponents(); // Boiler plate GUI construction and layout

        // Configure JTextArea to not update the cursor position after
        // inserting or appending text to the JTextArea. This disables the
        // JTextArea's usual behavior of scrolling automatically whenever
        // inserting or appending text into the JTextArea: we want scrolling
        // to only occur at our discretion, not blindly. NOTE that this
        // breaks normal typing into the JTextArea. This approach assumes
        // that all updates to the ScrollingJTextArea are programmatic.
        DefaultCaret caret = (DefaultCaret) jTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

        // Schedule a task to periodically append example messages to jTextArea
        timer.schedule(new TextGeneratorTask(), 250, 250);

        // This DocumentListener takes care of re-scrolling when appropriate
        Document document = jTextArea.getDocument();
        document.addDocumentListener(new ScrollingDocumentListener());
    }

    // Boring, vanilla GUI construction and layout code
    private void initComponents() {
        jScrollPane = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jScrollPane.setViewportView(jTextArea);
        getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);
        setSize(320, 240);
        setLocationRelativeTo(null);
    }

    // ScrollingDocumentListener takes care of re-scrolling when appropriate
    class ScrollingDocumentListener implements DocumentListener {

        public void changedUpdate(DocumentEvent e) {
            maybeScrollToBottom();
        }

        public void insertUpdate(DocumentEvent e) {
            maybeScrollToBottom();
        }

        public void removeUpdate(DocumentEvent e) {
            maybeScrollToBottom();
        }

        private void maybeScrollToBottom() {
            JScrollBar scrollBar = jScrollPane.getVerticalScrollBar();
            boolean scrollBarAtBottom = isScrollBarFullyExtended(scrollBar);
            boolean scrollLock = Toolkit.getDefaultToolkit()
                    .getLockingKeyState(KeyEvent.VK_SCROLL_LOCK);
            if (scrollBarAtBottom && !scrollLock) {
                // Push the call to "scrollToBottom" back TWO PLACES on the
                // AWT-EDT queue so that it runs *after* Swing has had an
                // opportunity to "react" to the appending of new text:
                // this ensures that we "scrollToBottom" only after a new
                // bottom has been recalculated during the natural
                // revalidation of the GUI that occurs after having
                // appending new text to the JTextArea.
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                scrollToBottom(jTextArea);
                            }
                        });
                    }
                });
            }
        }
    }

    class TextGeneratorTask extends TimerTask {

        public void run() {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    String message = (++messageCounter)
                            + " Lorem ipsum dolor sit amet, consectetur"
                            + " adipisicing elit, sed do eiusmod tempor"
                            + " incididunt ut labore et dolore magna aliqua.\n";
                    jTextArea.append(message);
                }
            });
        }
    }

    public static boolean isScrollBarFullyExtended(JScrollBar vScrollBar) {
        BoundedRangeModel model = vScrollBar.getModel();
        return (model.getExtent() + model.getValue()) == model.getMaximum();
    }

    public static void scrollToBottom(JComponent component) {
        Rectangle visibleRect = component.getVisibleRect();
        visibleRect.y = component.getHeight() - visibleRect.height;
        component.scrollRectToVisible(visibleRect);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScrollingJTextAreaExample().setVisible(true);
            }
        });
    }
}
