/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetprogrammig;

import Utils.CustomJTree;
import Utils.Node;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import recursos.*;
import clases.*;
import estructurasDatos.Nodo;
import estructurasDatos.PilaD;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author kevin2
 */
public final class EditorPlanetProgramming extends javax.swing.JFrame {

    /**
     * Creates new form EditorPlanetProgramming
     */
    public EditorPlanetProgramming() {

        TabbedPaneUI blackTabbedPaneUI = new TabbedPaneUI();
        panelTab = new CmpntTabPane[9];
        //Inicializacion de las pilas de los cambios
        pilaDeshacer = new PilaD();
        pilaRehacer = new PilaD();

        initComponents();

        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/planetUrano.png"));
        this.setIconImage(icono);
        setTitle("Planet Editor");
        setLocationRelativeTo(null);
        //para preguntar si se quiere cerrar ventana desactivamos el boton de cerrar
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        pestaniasEntradaSalidas.setUI(blackTabbedPaneUI);
        crearPestaniaInicio();
        //pestaniasEntradaSalidas.setIconAt(0, imageInicio);
        crearPestaniaEdicion();
        crearPestaniaCompilacion();
        crearPestaniaSalida();
        //Cursor
        //cursorForm1=tk.createCustomCursor(imgCursor.getImage(), new Point(1,1), "Cursor 1");
        //setCursor(cursorForm1);
        //Inicializar las variables para la pantalla
        //anchoScreen=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        //altoScreen=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        //setSize(new java.awt.Dimension(anchoScreen,altoScreen-40));
        //setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        progressBarEditor.setVisible(false);
        lblProgreso.setVisible(false);

    }

    private void txtPanelEditandoCaretUpdate(javax.swing.event.CaretEvent evt) {

        if (!(txtPanelEditando.getText().isEmpty()) && (true)) {
            btnGuardar.setEnabled(true);
            if (pilaDeshacer.getTope() == null) {
                n1 = new Nodo(txtPanelEditando.getText(), -1);
                pilaDeshacer.inserta(n1, null);
                btnDeshacer.setEnabled(true);
                itemDeshacer.setEnabled(true);

            } else {
                if (!pilaDeshacer.getTope().getS().equals(txtPanelEditando.getText())) {
                    n1 = new Nodo(txtPanelEditando.getText(), -1);
                    pilaDeshacer.inserta(n1, null);
                    btnDeshacer.setEnabled(true);
                    itemDeshacer.setEnabled(true);
                    if (pilaRehacer.getTope() != null) {
                        do {
                            pilaRehacer.elimina(null);
                        } while (pilaRehacer.getTope() != null);
                        btnRehacer.setEnabled(false);
                        itemRehacer.setEnabled(false);
                    }
                }
            }
            if (txtPanelEditando.getSelectedText() != null) {

                itemCopiar.setEnabled(true);
                itemEliminar.setEnabled(true);
                itemCortar.setEnabled(true);
                btnCopiar.setEnabled(true);
                btnCortar.setEnabled(true);
                btnGuardar.setEnabled(true);

            } else {

                itemCopiar.setEnabled(false);
                itemEliminar.setEnabled(false);
                itemCortar.setEnabled(false);
                btnCopiar.setEnabled(false);
                btnCortar.setEnabled(false);
            }

            if (pegar().isEmpty()) {
                btnPegar.setEnabled(false);
                itemPegar.setEnabled(false);
            } else {
                btnPegar.setEnabled(true);
                itemPegar.setEnabled(true);
            }
            itemGuardar.setEnabled(true);
        } else {
            itemCopiar.setEnabled(false);
            itemEliminar.setEnabled(false);
            itemCortar.setEnabled(false);
            btnCopiar.setEnabled(false);
            btnCortar.setEnabled(false);
            btnGuardar.setEnabled(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenuEditar = new javax.swing.JPopupMenu();
        popMRehacer = new javax.swing.JMenuItem();
        popMDeshacer = new javax.swing.JMenuItem();
        spEntreDeshacer = new javax.swing.JPopupMenu.Separator();
        popMCopiar = new javax.swing.JMenuItem();
        popMCortar = new javax.swing.JMenuItem();
        popMPegar = new javax.swing.JMenuItem();
        spEntrePegar = new javax.swing.JPopupMenu.Separator();
        popMBuscar = new javax.swing.JMenuItem();
        popMReemplazar = new javax.swing.JMenuItem();
        spEntreReemplazar = new javax.swing.JPopupMenu.Separator();
        popMIdentar = new javax.swing.JMenuItem();
        popMSeleccionTotal = new javax.swing.JMenuItem();
        popMenuCerrar = new javax.swing.JPopupMenu();
        popMLimpiar = new javax.swing.JMenuItem();
        popMCerrar = new javax.swing.JMenuItem();
        popMCerrarTodo = new javax.swing.JMenuItem();
        spEntreCerrarTodo = new javax.swing.JPopupMenu.Separator();
        popMDerecha = new javax.swing.JMenuItem();
        popMIzquierda = new javax.swing.JMenuItem();
        toolBarAccesosDirectos = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnFormato = new javax.swing.JButton();
        btnTraducir = new javax.swing.JButton();
        spEntreArchivo = new javax.swing.JToolBar.Separator();
        btnCortar = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        btnPegar = new javax.swing.JButton();
        btnDeshacer = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        spEntreEdicion = new javax.swing.JToolBar.Separator();
        btnBuscar = new javax.swing.JButton();
        btnReemplazar = new javax.swing.JButton();
        spEntreBusqueda = new javax.swing.JToolBar.Separator();
        btnEjecutar = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        spEntreEjecucion = new javax.swing.JToolBar.Separator();
        btnAyudar = new javax.swing.JButton();
        spEntreBarraProgress = new javax.swing.JToolBar.Separator();
        lblProgreso = new javax.swing.JLabel();
        progressBarEditor = new javax.swing.JProgressBar();
        pestaniasEntradaSalidas = new javax.swing.JTabbedPane();
        MenuInicio = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        itemNuevo = new javax.swing.JMenuItem();
        itemAbrir = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemGuardarComo = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        itemCerrar = new javax.swing.JMenuItem();
        spArchivo = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        menuEdicion = new javax.swing.JMenu();
        itemDeshacer = new javax.swing.JMenuItem();
        itemRehacer = new javax.swing.JMenuItem();
        spEntrerRehacerCortar = new javax.swing.JPopupMenu.Separator();
        itemCortar = new javax.swing.JMenuItem();
        itemCopiar = new javax.swing.JMenuItem();
        itemPegar = new javax.swing.JMenuItem();
        itemEliminar = new javax.swing.JMenuItem();
        itemSeleccionTotal = new javax.swing.JMenuItem();
        spEntreSeleccionBusqueda = new javax.swing.JPopupMenu.Separator();
        itemBuscar = new javax.swing.JMenuItem();
        itemBusquedaAnterior = new javax.swing.JMenuItem();
        itemBusquedaSiguiente = new javax.swing.JMenuItem();
        itemReemplazar = new javax.swing.JMenuItem();
        spEntreReemplazoFormato = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        itemFormato = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuProcesos = new javax.swing.JMenu();
        itemLexico = new javax.swing.JMenuItem();
        itemSintactico = new javax.swing.JMenuItem();
        itemSemantico = new javax.swing.JMenuItem();
        itemIntermedio = new javax.swing.JMenuItem();
        itemOptmizacion = new javax.swing.JMenuItem();
        itemObjeto = new javax.swing.JMenuItem();
        menuEjecicion = new javax.swing.JMenu();
        itemCompilar = new javax.swing.JMenuItem();
        itemEjecutar = new javax.swing.JMenuItem();
        menuVista = new javax.swing.JMenu();
        checkEditando = new javax.swing.JCheckBoxMenuItem();
        checkCompilando = new javax.swing.JCheckBoxMenuItem();
        checkSalida = new javax.swing.JCheckBoxMenuItem();
        psEntreChecks = new javax.swing.JPopupMenu.Separator();
        checkLexico = new javax.swing.JCheckBoxMenuItem();
        checkSintactico = new javax.swing.JCheckBoxMenuItem();
        checkSemantico = new javax.swing.JCheckBoxMenuItem();
        checkIntermedio = new javax.swing.JCheckBoxMenuItem();
        checkOptimizacion = new javax.swing.JCheckBoxMenuItem();
        checkObjeto = new javax.swing.JCheckBoxMenuItem();
        menuFormato = new javax.swing.JMenu();
        itemFuente = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        itemAyuda = new javax.swing.JMenuItem();
        spAyuda = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        itemAcerca = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        popMRehacer.setText("Rehacer");
        popMenuEditar.add(popMRehacer);

        popMDeshacer.setText("Deshacer");
        popMDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMDeshacerActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMDeshacer);
        popMenuEditar.add(spEntreDeshacer);

        popMCopiar.setText("Copiar");
        popMCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMCopiarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMCopiar);

        popMCortar.setText("Cortar");
        popMCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMCortarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMCortar);

        popMPegar.setText("Pegar");
        popMPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMPegarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMPegar);
        popMenuEditar.add(spEntrePegar);

        popMBuscar.setText("Buscar");
        popMBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMBuscarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMBuscar);

        popMReemplazar.setText("Buscar y reemplazar");
        popMReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMReemplazarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMReemplazar);
        popMenuEditar.add(spEntreReemplazar);

        popMIdentar.setText("Identar");
        popMIdentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMIdentarActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMIdentar);

        popMSeleccionTotal.setText("Seleccionar todo");
        popMSeleccionTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMSeleccionTotalActionPerformed(evt);
            }
        });
        popMenuEditar.add(popMSeleccionTotal);

        popMLimpiar.setText("Limpiar");
        popMLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMLimpiarActionPerformed(evt);
            }
        });
        popMenuCerrar.add(popMLimpiar);

        popMCerrar.setText("Cerrar");
        popMCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMCerrarActionPerformed(evt);
            }
        });
        popMenuCerrar.add(popMCerrar);

        popMCerrarTodo.setText("Cerrar todo");
        popMCerrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMCerrarTodoActionPerformed(evt);
            }
        });
        popMenuCerrar.add(popMCerrarTodo);
        popMenuCerrar.add(spEntreCerrarTodo);

        popMDerecha.setText("Pasar a la derecha");
        popMenuCerrar.add(popMDerecha);

        popMIzquierda.setText("Pasar a la izquierda");
        popMenuCerrar.add(popMIzquierda);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor planet");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        toolBarAccesosDirectos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        toolBarAccesosDirectos.setFloatable(false);
        toolBarAccesosDirectos.setOpaque(false);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94509 - layout modify stock.png"))); // NOI18N
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnNuevo);

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94491 - insert macro stock.png"))); // NOI18N
        btnAbrir.setToolTipText("Abrir");
        btnAbrir.setContentAreaFilled(false);
        btnAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAbrir.setFocusable(false);
        btnAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnAbrir);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93639 - lc save.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnGuardar);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93566 - dbquerydelete lc.png"))); // NOI18N
        btnCerrar.setToolTipText("Cerrar todo");
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setFocusable(false);
        btnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnCerrar);

        btnFormato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93569 - dbqueryrename lc.png"))); // NOI18N
        btnFormato.setToolTipText("Dar formato");
        btnFormato.setAutoscrolls(true);
        btnFormato.setContentAreaFilled(false);
        btnFormato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFormato.setFocusable(false);
        btnFormato.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFormato.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormatoActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnFormato);

        btnTraducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18362 - binary.png"))); // NOI18N
        btnTraducir.setToolTipText("Traducir a");
        btnTraducir.setAutoscrolls(true);
        btnTraducir.setContentAreaFilled(false);
        btnTraducir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTraducir.setFocusable(false);
        btnTraducir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTraducir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTraducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraducirActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnTraducir);

        spEntreArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toolBarAccesosDirectos.add(spEntreArchivo);

        btnCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18632 - editcut.png"))); // NOI18N
        btnCortar.setToolTipText("Cortar");
        btnCortar.setContentAreaFilled(false);
        btnCortar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCortar.setFocusable(false);
        btnCortar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCortar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCortarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnCortar);

        btnCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18631 - copy edit.png"))); // NOI18N
        btnCopiar.setToolTipText("Copiar");
        btnCopiar.setContentAreaFilled(false);
        btnCopiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCopiar.setFocusable(false);
        btnCopiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCopiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnCopiar);

        btnPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18634 - editpaste.png"))); // NOI18N
        btnPegar.setToolTipText("Pegar");
        btnPegar.setContentAreaFilled(false);
        btnPegar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPegar.setFocusable(false);
        btnPegar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPegar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPegarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnPegar);

        btnDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94908 - stock undo.png"))); // NOI18N
        btnDeshacer.setToolTipText("Deshacer");
        btnDeshacer.setContentAreaFilled(false);
        btnDeshacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeshacer.setFocusable(false);
        btnDeshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeshacerActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnDeshacer);

        btnRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94687 - redo stock.png"))); // NOI18N
        btnRehacer.setToolTipText("Rehacer");
        btnRehacer.setContentAreaFilled(false);
        btnRehacer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRehacer.setFocusable(false);
        btnRehacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRehacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnRehacer);

        spEntreEdicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toolBarAccesosDirectos.add(spEntreEdicion);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18013 - kghostview.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnBuscar);

        btnReemplazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18006 - kfilereplace.png"))); // NOI18N
        btnReemplazar.setToolTipText("Buscar y reemplazar");
        btnReemplazar.setContentAreaFilled(false);
        btnReemplazar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReemplazar.setFocusable(false);
        btnReemplazar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReemplazar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReemplazarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnReemplazar);

        spEntreBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toolBarAccesosDirectos.add(spEntreBusqueda);

        btnEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18864 - play player.png"))); // NOI18N
        btnEjecutar.setToolTipText("Ejecutar");
        btnEjecutar.setContentAreaFilled(false);
        btnEjecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEjecutar.setFocusable(false);
        btnEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEjecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnEjecutar);

        btnCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/118830 - terminal utilities.png"))); // NOI18N
        btnCompilar.setToolTipText("Compilar");
        btnCompilar.setContentAreaFilled(false);
        btnCompilar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompilar.setFocusable(false);
        btnCompilar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompilar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnCompilar);

        spEntreEjecucion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toolBarAccesosDirectos.add(spEntreEjecucion);

        btnAyudar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/118806 - browser help (2).png"))); // NOI18N
        btnAyudar.setToolTipText("Ayuda");
        btnAyudar.setContentAreaFilled(false);
        btnAyudar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAyudar.setFocusable(false);
        btnAyudar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAyudar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAyudar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudarActionPerformed(evt);
            }
        });
        toolBarAccesosDirectos.add(btnAyudar);

        spEntreBarraProgress.setForeground(new java.awt.Color(204, 204, 204));
        spEntreBarraProgress.setMaximumSize(new java.awt.Dimension(32, 6));
        spEntreBarraProgress.setOpaque(true);
        toolBarAccesosDirectos.add(spEntreBarraProgress);

        lblProgreso.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblProgreso.setText("Progreso: ");
        lblProgreso.setName(""); // NOI18N
        toolBarAccesosDirectos.add(lblProgreso);

        progressBarEditor.setBackground(new java.awt.Color(255, 255, 255));
        progressBarEditor.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        progressBarEditor.setMaximumSize(new java.awt.Dimension(250, 16));
        progressBarEditor.setMinimumSize(new java.awt.Dimension(50, 16));
        progressBarEditor.setOpaque(true);
        progressBarEditor.setPreferredSize(new java.awt.Dimension(100, 16));
        progressBarEditor.setStringPainted(true);
        toolBarAccesosDirectos.add(progressBarEditor);

        pestaniasEntradaSalidas.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        pestaniasEntradaSalidas.setComponentPopupMenu(popMenuCerrar);
        pestaniasEntradaSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pestaniasEntradaSalidas.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                pestaniasEntradaSalidasComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                pestaniasEntradaSalidasComponentRemoved(evt);
            }
        });
        pestaniasEntradaSalidas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pestaniasEntradaSalidasMouseDragged(evt);
            }
        });
        pestaniasEntradaSalidas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pestaniasEntradaSalidasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pestaniasEntradaSalidasFocusLost(evt);
            }
        });
        pestaniasEntradaSalidas.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pestaniasEntradaSalidasMouseWheelMoved(evt);
            }
        });
        pestaniasEntradaSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pestaniasEntradaSalidasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pestaniasEntradaSalidasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pestaniasEntradaSalidasMouseReleased(evt);
            }
        });
        pestaniasEntradaSalidas.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                pestaniasEntradaSalidasCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        pestaniasEntradaSalidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pestaniasEntradaSalidasKeyPressed(evt);
            }
        });
        pestaniasEntradaSalidas.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                pestaniasEntradaSalidasVetoableChange(evt);
            }
        });

        MenuInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        MenuInicio.setPreferredSize(new java.awt.Dimension(243, 25));
        MenuInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenuInicioKeyPressed(evt);
            }
        });

        menuArchivo.setText("Archivo");
        menuArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94547 - new stock.png"))); // NOI18N
        itemNuevo.setText("Nuevo");
        itemNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemNuevo.setInheritsPopupMenu(true);
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        menuArchivo.add(itemNuevo);

        itemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94488 - insert macro stock.png"))); // NOI18N
        itemAbrir.setText("Abrir");
        itemAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemAbrir);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93717 - save sc.png"))); // NOI18N
        itemGuardar.setText("Guardar");
        itemGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        menuArchivo.add(itemGuardar);

        itemGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itemGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93718 - saveas sc.png"))); // NOI18N
        itemGuardarComo.setText("Guardar como ");
        itemGuardarComo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarComoActionPerformed(evt);
            }
        });
        menuArchivo.add(itemGuardarComo);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18200 - .png"))); // NOI18N
        jMenuItem5.setText("Imprimir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem5);

        jMenu1.setText("Exportar");

        jMenuItem6.setText("PDF");
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("JPG");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("PNG");
        jMenu1.add(jMenuItem8);

        menuArchivo.add(jMenu1);

        itemCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/118757 - format indent less_1.png"))); // NOI18N
        itemCerrar.setText("Cerrar");
        itemCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCerrarActionPerformed(evt);
            }
        });
        menuArchivo.add(itemCerrar);

        spArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuArchivo.add(spArchivo);

        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/17902 - power shutdown.png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(itemSalir);

        jMenuItem10.setText("AgregarPestaña");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem10);

        MenuInicio.add(menuArchivo);

        menuEdicion.setText("Edición");

        itemDeshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        itemDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94907 - stock undo.png"))); // NOI18N
        itemDeshacer.setText("Deshacer");
        itemDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeshacerActionPerformed(evt);
            }
        });
        menuEdicion.add(itemDeshacer);

        itemRehacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        itemRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94686 - redo stock.png"))); // NOI18N
        itemRehacer.setText("Rehacer");
        itemRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRehacerActionPerformed(evt);
            }
        });
        menuEdicion.add(itemRehacer);
        menuEdicion.add(spEntrerRehacerCortar);

        itemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itemCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18632 - editcut_1.png"))); // NOI18N
        itemCortar.setText("Cortar");
        itemCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCortarActionPerformed(evt);
            }
        });
        menuEdicion.add(itemCortar);

        itemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18631 - copy edit_1.png"))); // NOI18N
        itemCopiar.setText("Copiar");
        itemCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCopiarActionPerformed(evt);
            }
        });
        menuEdicion.add(itemCopiar);

        itemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        itemPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18634 - editpaste_1.png"))); // NOI18N
        itemPegar.setText("Pegar");
        itemPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPegarActionPerformed(evt);
            }
        });
        menuEdicion.add(itemPegar);

        itemEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18474 - 14 deletelayer layer.png"))); // NOI18N
        itemEliminar.setText("Eliminar");
        itemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarActionPerformed(evt);
            }
        });
        menuEdicion.add(itemEliminar);

        itemSeleccionTotal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        itemSeleccionTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94105 - and edit footers headers stock.png"))); // NOI18N
        itemSeleccionTotal.setText("Seleccionar todo");
        itemSeleccionTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSeleccionTotalActionPerformed(evt);
            }
        });
        menuEdicion.add(itemSeleccionTotal);
        menuEdicion.add(spEntreSeleccionBusqueda);

        itemBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        itemBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18213 - find magnifying glass search zoom.png"))); // NOI18N
        itemBuscar.setText("Buscar");
        menuEdicion.add(itemBuscar);

        itemBusquedaAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94965 - previous stock zoom.png"))); // NOI18N
        itemBusquedaAnterior.setText("Búsqueda anterior");
        menuEdicion.add(itemBusquedaAnterior);

        itemBusquedaSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94953 - next stock zoom.png"))); // NOI18N
        itemBusquedaSiguiente.setText("Búsqueda siguiente");
        menuEdicion.add(itemBusquedaSiguiente);

        itemReemplazar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        itemReemplazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18006 - kfilereplace_1.png"))); // NOI18N
        itemReemplazar.setText("Reemplazar");
        menuEdicion.add(itemReemplazar);
        menuEdicion.add(spEntreReemplazoFormato);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18976 - viewmag.png"))); // NOI18N
        jMenuItem2.setText("Alejar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuEdicion.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18975 - viewmag+.png"))); // NOI18N
        jMenuItem3.setText("Acercar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuEdicion.add(jMenuItem3);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18978 - viewmag_1.png"))); // NOI18N
        jMenuItem9.setText("Restaurar 100%");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuEdicion.add(jMenuItem9);

        itemFormato.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        itemFormato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/93694 - dbqueryrename sc.png"))); // NOI18N
        itemFormato.setText("Dar formato");
        menuEdicion.add(itemFormato);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/17884 - date.png"))); // NOI18N
        jMenuItem4.setText("Fecha y hora");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuEdicion.add(jMenuItem4);

        MenuInicio.add(menuEdicion);

        menuProcesos.setText("Procesos");
        menuProcesos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        itemLexico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        itemLexico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94476 - enum list off stock.png"))); // NOI18N
        itemLexico.setText("Léxico");
        itemLexico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLexicoActionPerformed(evt);
            }
        });
        menuProcesos.add(itemLexico);

        itemSintactico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        itemSintactico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94481 - insert list stock unnumbered.png"))); // NOI18N
        itemSintactico.setText("Sintáctico");
        itemSintactico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSintacticoActionPerformed(evt);
            }
        });
        menuProcesos.add(itemSintactico);

        itemSemantico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        itemSemantico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94478 - enum list restart stock.png"))); // NOI18N
        itemSemantico.setText("Semántico");
        itemSemantico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemSemantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSemanticoActionPerformed(evt);
            }
        });
        menuProcesos.add(itemSemantico);

        itemIntermedio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        itemIntermedio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94475 - enum list stock.png"))); // NOI18N
        itemIntermedio.setText("Intermedio");
        itemIntermedio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIntermedioActionPerformed(evt);
            }
        });
        menuProcesos.add(itemIntermedio);

        itemOptmizacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        itemOptmizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18418 - moc src.png"))); // NOI18N
        itemOptmizacion.setText("Optimización");
        itemOptmizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemOptmizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOptmizacionActionPerformed(evt);
            }
        });
        menuProcesos.add(itemOptmizacion);

        itemObjeto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        itemObjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94494 - macro objects stock.png"))); // NOI18N
        itemObjeto.setText("Objeto");
        itemObjeto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemObjetoActionPerformed(evt);
            }
        });
        menuProcesos.add(itemObjeto);

        MenuInicio.add(menuProcesos);

        menuEjecicion.setText("Ejecutar");
        menuEjecicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        itemCompilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/118830 - terminal utilities_1.png"))); // NOI18N
        itemCompilar.setText("Compilar");
        itemCompilar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCompilarActionPerformed(evt);
            }
        });
        menuEjecicion.add(itemCompilar);

        itemEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18864 - play player_1.png"))); // NOI18N
        itemEjecutar.setText("Ejecutar");
        itemEjecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEjecutarActionPerformed(evt);
            }
        });
        menuEjecicion.add(itemEjecutar);

        MenuInicio.add(menuEjecicion);

        menuVista.setText("Ventana");

        checkEditando.setSelected(true);
        checkEditando.setText("Editando");
        checkEditando.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkEditando.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkEditandoItemStateChanged(evt);
            }
        });
        checkEditando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEditandoActionPerformed(evt);
            }
        });
        menuVista.add(checkEditando);

        checkCompilando.setSelected(true);
        checkCompilando.setText("Compilando");
        checkCompilando.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkCompilando.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkCompilandoItemStateChanged(evt);
            }
        });
        checkCompilando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCompilandoActionPerformed(evt);
            }
        });
        menuVista.add(checkCompilando);

        checkSalida.setSelected(true);
        checkSalida.setText("Salida");
        checkSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkSalida.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSalidaItemStateChanged(evt);
            }
        });
        checkSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSalidaActionPerformed(evt);
            }
        });
        menuVista.add(checkSalida);

        psEntreChecks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuVista.add(psEntreChecks);

        checkLexico.setSelected(true);
        checkLexico.setText("Léxico");
        checkLexico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkLexico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLexicoItemStateChanged(evt);
            }
        });
        checkLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLexicoActionPerformed(evt);
            }
        });
        menuVista.add(checkLexico);

        checkSintactico.setSelected(true);
        checkSintactico.setText("Sintáctico");
        checkSintactico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkSintactico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSintacticoItemStateChanged(evt);
            }
        });
        checkSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSintacticoActionPerformed(evt);
            }
        });
        menuVista.add(checkSintactico);

        checkSemantico.setSelected(true);
        checkSemantico.setText("Semántico");
        checkSemantico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkSemantico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSemanticoItemStateChanged(evt);
            }
        });
        menuVista.add(checkSemantico);

        checkIntermedio.setSelected(true);
        checkIntermedio.setText("Intermedio");
        checkIntermedio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkIntermedio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkIntermedioItemStateChanged(evt);
            }
        });
        menuVista.add(checkIntermedio);

        checkOptimizacion.setSelected(true);
        checkOptimizacion.setText("Optimización");
        checkOptimizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkOptimizacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkOptimizacionItemStateChanged(evt);
            }
        });
        menuVista.add(checkOptimizacion);

        checkObjeto.setSelected(true);
        checkObjeto.setText("Objeto");
        checkObjeto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkObjeto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkObjetoItemStateChanged(evt);
            }
        });
        menuVista.add(checkObjeto);

        MenuInicio.add(menuVista);

        menuFormato.setText("Formato");
        menuFormato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        itemFuente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/94374 - font increase stock.png"))); // NOI18N
        itemFuente.setText("Fuente");
        itemFuente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemFuenteActionPerformed(evt);
            }
        });
        menuFormato.add(itemFuente);

        MenuInicio.add(menuFormato);

        menuAyuda.setText("Ayuda");
        menuAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        itemAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        itemAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/118806 - browser help.png"))); // NOI18N
        itemAyuda.setText("Ver la ayuda");
        itemAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAyudaActionPerformed(evt);
            }
        });
        menuAyuda.add(itemAyuda);

        spAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuAyuda.add(spAyuda);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/planetUrano - copia.png"))); // NOI18N
        jMenuItem1.setText("Página inicial");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuAyuda.add(jMenuItem1);

        itemAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/18621 - documentinfo_1.png"))); // NOI18N
        itemAcerca.setText("Acerca del Editor");
        itemAcerca.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcercaActionPerformed(evt);
            }
        });
        menuAyuda.add(itemAcerca);

        MenuInicio.add(menuAyuda);

        jMenu2.setText("Dibujar");

        jMenuItem11.setText("Nuevo lienzo");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Guardar como");
        jMenu2.add(jMenuItem12);

        MenuInicio.add(jMenu2);

        jMenu3.setText("Notas");

        jMenuItem13.setText("Agregar nota");
        jMenu3.add(jMenuItem13);

        jMenuItem14.setText("Ver lista de notas");
        jMenu3.add(jMenuItem14);

        MenuInicio.add(jMenu3);

        setJMenuBar(MenuInicio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarAccesosDirectos, javax.swing.GroupLayout.DEFAULT_SIZE, 1403, Short.MAX_VALUE)
            .addComponent(pestaniasEntradaSalidas, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarAccesosDirectos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pestaniasEntradaSalidas, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        abrir();
        if (archivo) {
            posicionC = txtPanelEditando.getCaretPosition();
          //  formato();
            try {
                txtPanelEditando.setCaretPosition(posicionC);
            } catch (Exception e) {
                System.out.println("Posicion del cursor no valida");
            }
            
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        int ints = txtPanelEditando.getCaretPosition();

        guardar();
        if (ints != -1) {
            txtPanelEditando.setCaretPosition(ints);
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCortarActionPerformed
        String s = txtPanelEditando.getSelectedText();
        txtPanelEditando.setText(txtPanelEditando.getText().replaceAll(s, ""));
        cortar(s);
    }//GEN-LAST:event_btnCortarActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        String s = txtPanelEditando.getSelectedText();
        copiar(s);
    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPegarActionPerformed
        int p = txtPanelEditando.getCaretPosition();
        String s = pegar();
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        txtPanelEditando.setCaretPosition(p);
        txtPanelEditando.setCharacterAttributes(aset2, true);
        txtPanelEditando.replaceSelection(s);

    }//GEN-LAST:event_btnPegarActionPerformed

    private void btnDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeshacerActionPerformed
        deshacer();

    }//GEN-LAST:event_btnDeshacerActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        rehacer();
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReemplazarActionPerformed
        buscarReemplazar();
    }//GEN-LAST:event_btnReemplazarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        cerrar();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        compilar();
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnAyudarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudarActionPerformed
        ayuda();
    }//GEN-LAST:event_btnAyudarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        // ejecutar();
        crearPestaniaLexico();
        lexico();

    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void itemIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIntermedioActionPerformed
        crearPestaniaIntermedio();
        //  intermedio();
    }//GEN-LAST:event_itemIntermedioActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        closeEditor();
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        abrir();
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void popMCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMCopiarActionPerformed
        String s = txtPanelEditando.getSelectedText();
        copiar(s);
    }//GEN-LAST:event_popMCopiarActionPerformed

    private void itemFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemFuenteActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        fuente();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itemFuenteActionPerformed

    private void itemAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercaActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        acercaDe();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itemAcercaActionPerformed

    private void itemAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAyudaActionPerformed
        ayuda();
    }//GEN-LAST:event_itemAyudaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        openEditor();
    }//GEN-LAST:event_formWindowOpened

    private void itemLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLexicoActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        crearPestaniaLexico();
        lexico();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_itemLexicoActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_itemGuardarActionPerformed

    private void itemGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarComoActionPerformed
        guardarComo();
    }//GEN-LAST:event_itemGuardarComoActionPerformed

    private void itemSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSintacticoActionPerformed

        if (!bError) {

            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            crearPestaniaSintactico();
            sintactico();
            bError = false;

            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            Mensaje.advertencia(this, "El analisis sintactico no se puede realizar, debido a que hay un error léxico");
            TextPaneTest.appendToPane(txtPanelSalida, "\nError al realizar analisis sinctatico...", cRojo);

        }
    }//GEN-LAST:event_itemSintacticoActionPerformed

    private void itemSemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSemanticoActionPerformed
        crearPestaniaSemantico();
        //   semantico();
    }//GEN-LAST:event_itemSemanticoActionPerformed

    private void itemOptmizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOptmizacionActionPerformed
        crearPestaniaOptimizado();
        //  optimizacion();
    }//GEN-LAST:event_itemOptmizacionActionPerformed

    private void itemObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemObjetoActionPerformed
        crearPestaniaObjeto();
        //  objeto();
    }//GEN-LAST:event_itemObjetoActionPerformed

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_itemNuevoActionPerformed

    private void itemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarActionPerformed
        cerrar();
    }//GEN-LAST:event_itemCerrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeEditor();

    }//GEN-LAST:event_formWindowClosing

    private void itemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCompilarActionPerformed
        compilar();

    }//GEN-LAST:event_itemCompilarActionPerformed

    private void itemEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEjecutarActionPerformed
        ejecutar();
    }//GEN-LAST:event_itemEjecutarActionPerformed

    private void popMDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMDeshacerActionPerformed
        deshacer();
    }//GEN-LAST:event_popMDeshacerActionPerformed

    private void popMCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMCortarActionPerformed
        String s = txtPanelEditando.getSelectedText();
        txtPanelEditando.setText(txtPanelEditando.getText().replaceAll(s, ""));
        cortar(s);
    }//GEN-LAST:event_popMCortarActionPerformed

    private void popMPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMPegarActionPerformed
        int p = txtPanelEditando.getCaretPosition();
        String s = pegar();
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        txtPanelEditando.setCaretPosition(p);
        txtPanelEditando.setCharacterAttributes(aset2, true);
        txtPanelEditando.replaceSelection(s);

    }//GEN-LAST:event_popMPegarActionPerformed

    private void popMBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_popMBuscarActionPerformed

    private void popMReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMReemplazarActionPerformed
        buscarReemplazar();
    }//GEN-LAST:event_popMReemplazarActionPerformed

    private void popMIdentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMIdentarActionPerformed
        formato();
    }//GEN-LAST:event_popMIdentarActionPerformed

    private void popMSeleccionTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMSeleccionTotalActionPerformed
        seleccionarTodoCodigo();
    }//GEN-LAST:event_popMSeleccionTotalActionPerformed
    int posicionC = 0;
    private void btnFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormatoActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        posicionC = txtPanelEditando.getCaretPosition();
        formato();

        txtPanelEditando.setCaretPosition(posicionC);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnFormatoActionPerformed

    private void checkLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLexicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkLexicoActionPerformed

    private void checkEditandoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkEditandoItemStateChanged
        if (checkEditando.isSelected()) {
            crearPestaniaEdicion();
        } else {
            cerrarPestaniaEdicion();
        }
    }//GEN-LAST:event_checkEditandoItemStateChanged

    private void checkCompilandoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkCompilandoItemStateChanged
        if (checkCompilando.isSelected()) {
            crearPestaniaCompilacion();
        } else {
            cerrarPestaniaCompilacion();
        }

    }//GEN-LAST:event_checkCompilandoItemStateChanged

    private void checkSalidaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSalidaItemStateChanged
        if (checkSalida.isSelected()) {
            crearPestaniaSalida();
        } else {
            cerrarPestaniaSalida();
        }

    }//GEN-LAST:event_checkSalidaItemStateChanged

    private void checkLexicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkLexicoItemStateChanged
        if (checkLexico.isSelected()) {
            crearPestaniaLexico();
        } else {
            cerrarPestaniaLexico();
        }

    }//GEN-LAST:event_checkLexicoItemStateChanged

    private void checkSintacticoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSintacticoItemStateChanged
        if (checkSintactico.isSelected()) {
            crearPestaniaSintactico();
        } else {
            cerrarPestaniaSintactico();
        }

    }//GEN-LAST:event_checkSintacticoItemStateChanged

    private void checkSemanticoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSemanticoItemStateChanged
        if (checkSemantico.isSelected()) {
            crearPestaniaSemantico();
        } else {
            cerrarPestaniaSemantico();
        }

    }//GEN-LAST:event_checkSemanticoItemStateChanged

    private void checkIntermedioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkIntermedioItemStateChanged
        if (checkIntermedio.isSelected()) {
            crearPestaniaIntermedio();
        } else {
            cerrarPestaniaIntermedio();
        }

    }//GEN-LAST:event_checkIntermedioItemStateChanged

    private void checkOptimizacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkOptimizacionItemStateChanged
        if (checkOptimizacion.isSelected()) {
            crearPestaniaOptimizado();
        } else {
            cerrarPestaniaOptimizacion();
        }

    }//GEN-LAST:event_checkOptimizacionItemStateChanged

    private void checkObjetoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkObjetoItemStateChanged
        if (checkObjeto.isSelected()) {
            crearPestaniaObjeto();
        } else {
            cerrarPestaniaObjeto();
        }

    }//GEN-LAST:event_checkObjetoItemStateChanged

    private void MenuInicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenuInicioKeyPressed

    }//GEN-LAST:event_MenuInicioKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void pestaniasEntradaSalidasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasMousePressed

    }//GEN-LAST:event_pestaniasEntradaSalidasMousePressed

//mousedragged editando
    //seleccionarCadena();
    private void pestaniasEntradaSalidasComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasComponentRemoved

        if (evt.getChild().getName() != null) {
            switch (evt.getChild().getName()) {
                case "scrollPaneEditando":
                    checkEditando.setSelected(false);
                    break;
                case "scrollPaneCompilando":
                    checkCompilando.setSelected(false);
                    break;
                case "scrollPaneSalida":
                    checkSalida.setSelected(false);
                    break;
                case "scrollPaneLexico":
                    checkLexico.setSelected(false);
                    break;
                case "scrollPaneSintactico":
                    checkSintactico.setSelected(false);
                    break;
                case "scrollPaneSemantico":
                    checkSemantico.setSelected(false);
                    break;
                case "scrollPaneIntermedio":
                    checkIntermedio.setSelected(false);
                    break;
                case "scrollPaneOptimizacion":
                    checkOptimizacion.setSelected(false);
                    break;
                case "scrollPaneObjeto":
                    checkObjeto.setSelected(false);
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_pestaniasEntradaSalidasComponentRemoved

    private void pestaniasEntradaSalidasComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasComponentAdded
        if (evt.getChild().getName() != null) {
            switch (evt.getChild().getName()) {
                case "scrollPaneEditando":
                    checkEditando.setSelected(true);
                    break;
                case "scrollPaneCompilando":
                    checkCompilando.setSelected(true);
                    break;
                case "scrollPaneSalida":
                    checkSalida.setSelected(true);
                    break;
                case "scrollPaneLexico":
                    checkLexico.setSelected(true);
                    break;
                case "scrollPaneSintactico":
                    checkSintactico.setSelected(true);
                    break;
                case "scrollPaneSemantico":
                    checkSemantico.setSelected(true);
                    break;
                case "scrollPaneIntermedio":
                    checkIntermedio.setSelected(true);
                    break;
                case "scrollPaneOptimizacion":
                    checkOptimizacion.setSelected(true);
                    break;
                case "scrollPaneObjeto":
                    checkObjeto.setSelected(true);
                    break;
                default:
                    break;
            }

        }
        popMCerrarTodo.setEnabled(true);
    }//GEN-LAST:event_pestaniasEntradaSalidasComponentAdded

    private void btnTraducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraducirActionPerformed
        posicionC = txtPanelEditando.getCaretPosition();
        formatoIngles();
        txtPanelEditando.setCaretPosition(posicionC);
    }//GEN-LAST:event_btnTraducirActionPerformed

    private void popMCerrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMCerrarTodoActionPerformed
        pestaniasEntradaSalidas.removeAll();
        popMCerrarTodo.setEnabled(false);
    }//GEN-LAST:event_popMCerrarTodoActionPerformed

    private void pestaniasEntradaSalidasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasFocusLost

        if (pestaniasEntradaSalidas.getSelectedIndex() != -1) {
            CtrlInterfaz.habilita(true, popMCerrar, popMLimpiar);
        } else {
            CtrlInterfaz.habilita(false, popMCerrar, popMLimpiar);
        }
    }//GEN-LAST:event_pestaniasEntradaSalidasFocusLost

    private void pestaniasEntradaSalidasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasFocusGained

        if (pestaniasEntradaSalidas.getSelectedIndex() != -1) {
            CtrlInterfaz.habilita(true, popMCerrarTodo);
        } else {
            CtrlInterfaz.habilita(false, popMCerrarTodo);
        }
    }//GEN-LAST:event_pestaniasEntradaSalidasFocusGained

    private void pestaniasEntradaSalidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasMouseClicked

    }//GEN-LAST:event_pestaniasEntradaSalidasMouseClicked

    private void popMCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMCerrarActionPerformed
        pestaniasEntradaSalidas.remove(pestaniasEntradaSalidas.getSelectedIndex());
    }//GEN-LAST:event_popMCerrarActionPerformed

    private void popMLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMLimpiarActionPerformed
        switch (pestaniasEntradaSalidas.getTitleAt(pestaniasEntradaSalidas.getSelectedIndex())) {
            case "Editando":
                txtPanelEditando.setText("");
                break;
            case "Compilando":
                txtPanelCompilando.setText("");
                break;
            case "Salida":
                txtPanelSalida.setText("");
                break;
            case "Léxico":
                txtPanelLexico.setText("");
                break;
            case "Sintáctico":
                txtPanelSintactico1.setText("");
                txtPanelSintactico2.setText("");
                break;
            case "Semántico":
                txtPanelSemantico.setText("");
                break;
            case "Intermedio":
                txtPanelIntermedio.setText("");
                break;
            case "Optimización":
                txtPanelOptimizacion.setText("");
                break;
            case "Objeto":
                txtPanelObjeto.setText("");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_popMLimpiarActionPerformed

    private void pestaniasEntradaSalidasMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasMouseWheelMoved
        if (pestaniasEntradaSalidas.getTabCount() != 0) {
            if (evt.getWheelRotation() == 1) {
                if (pestaniasEntradaSalidas.getSelectedIndex() - 1 != -1) {
                    pestaniasEntradaSalidas.setSelectedIndex(pestaniasEntradaSalidas.getSelectedIndex() - 1);
                } else {
                    pestaniasEntradaSalidas.setSelectedIndex(pestaniasEntradaSalidas.getTabCount() - 1);
                }
            } else {
                if (pestaniasEntradaSalidas.getSelectedIndex() + 1 != pestaniasEntradaSalidas.getTabCount()) {
                    pestaniasEntradaSalidas.setSelectedIndex(pestaniasEntradaSalidas.getSelectedIndex() + 1);
                } else {
                    pestaniasEntradaSalidas.setSelectedIndex(0);
                }
            }
        }
    }//GEN-LAST:event_pestaniasEntradaSalidasMouseWheelMoved

    private void pestaniasEntradaSalidasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasMouseDragged

        int tabNumber = pestaniasEntradaSalidas.getUI().tabForCoordinate(pestaniasEntradaSalidas, evt.getX(), evt.getY());
        if (tabNumber != -1) {
            tabIndexOrigen = tabNumber;
        }
    }//GEN-LAST:event_pestaniasEntradaSalidasMouseDragged

    private void pestaniasEntradaSalidasCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasCaretPositionChanged

    }//GEN-LAST:event_pestaniasEntradaSalidasCaretPositionChanged

    private void pestaniasEntradaSalidasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasMouseReleased

        int tabNumber = pestaniasEntradaSalidas.getUI().tabForCoordinate(pestaniasEntradaSalidas, evt.getX(), evt.getY());
        if (tabNumber != -1) {

            /*CmpntTabPane p = null;
            pestaniasEntradaSalidas.removeTabAt(tabIndexOrigen);
            if (nameComponente.equals("scrollPaneEditando")) {
                pestaniasEntradaSalidas.insertTab("Editando", null, scrollPaneEditando, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Editando")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[0]);
            } else if (nameComponente.equals("scrollPaneCompilando")) {

                pestaniasEntradaSalidas.insertTab("Compilando", null, scrollPaneCompilando, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Compilando")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[1]);
            } else if (nameComponente.equals("scrollPaneSalida")) {
                pestaniasEntradaSalidas.insertTab("Salida", null, scrollPaneSalida, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Salida")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[2]);
            } else if (nameComponente.equals("scrollPaneLexico")) {
                pestaniasEntradaSalidas.insertTab("Léxico", null, scrollPaneLexico, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Léxico")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[3]);
            } else if (nameComponente.equals("scrollPaneSintactico")) {
                pestaniasEntradaSalidas.insertTab("Sintáctico", null, scrollPaneSintactico, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Sintáctico")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[4]);
            } else if (nameComponente.equals("scrollPaneSemantico")) {
                pestaniasEntradaSalidas.insertTab("Semántico", null, scrollPaneSemantico, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Semántico")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[5]);
            } else if (nameComponente.equals("scrollPaneIntermedio")) {
                pestaniasEntradaSalidas.insertTab("Intermedio", null, scrollPaneIntermedio, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Intermedio")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[6]);
            } else if (nameComponente.equals("scrollPaneOptimizacion")) {
                pestaniasEntradaSalidas.insertTab("Optimización", null, scrollPaneOptimizacion, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Optimización")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[7]);
            } else if (nameComponente.equals("scrollPaneObjeto")) {
                pestaniasEntradaSalidas.insertTab("Objeto", null, scrollPaneObjeto, null, tabNumber);
                int indexTab = 0;
                for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
                    if (pestaniasEntradaSalidas.getTitleAt(i).equals("Objeto")) {
                        indexTab = i;
                    }
                }
                pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[8]);
            }*/
        }

    }//GEN-LAST:event_pestaniasEntradaSalidasMouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        crearPestaniaInicio();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void itemRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRehacerActionPerformed
        rehacer();
    }//GEN-LAST:event_itemRehacerActionPerformed

    private void itemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCopiarActionPerformed
        String s = txtPanelEditando.getSelectedText();
        copiar(s);
    }//GEN-LAST:event_itemCopiarActionPerformed

    private void itemPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPegarActionPerformed
        int p = txtPanelEditando.getCaretPosition();
        String s = pegar();
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        txtPanelEditando.setCaretPosition(p);
        txtPanelEditando.setCharacterAttributes(aset2, true);
        txtPanelEditando.replaceSelection(s);

    }//GEN-LAST:event_itemPegarActionPerformed

    private void pestaniasEntradaSalidasVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_pestaniasEntradaSalidasVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_pestaniasEntradaSalidasVetoableChange

    private void itemCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCortarActionPerformed

        String s = txtPanelEditando.getSelectedText();
        txtPanelEditando.setText(txtPanelEditando.getText().replaceAll(s, ""));
        cortar(s);
    }//GEN-LAST:event_itemCortarActionPerformed

    private void itemSeleccionTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSeleccionTotalActionPerformed
        txtPanelEditando.setSelectionStart(0);
        txtPanelEditando.setSelectionEnd(txtPanelEditando.getText().length());
    }//GEN-LAST:event_itemSeleccionTotalActionPerformed

    private void itemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarActionPerformed
        try {
            if (txtPanelEditando.getSelectionStart() != -1) {
                n1 = new Nodo(txtPanelEditando.getText(), -1);
                pilaDeshacer.inserta(n1, null);
                String s = txtPanelEditando.getSelectedText();
                txtPanelEditando.setText(txtPanelEditando.getText().replaceAll(s, ""));
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_itemEliminarActionPerformed

    private void checkCompilandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCompilandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkCompilandoActionPerformed

    private void checkSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSalidaActionPerformed

    private void itemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeshacerActionPerformed
        deshacer();
    }//GEN-LAST:event_itemDeshacerActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            int w = txtPanelEditando.getWidth();
            int h = txtPanelEditando.getHeight();
            BufferedImage imaG = new BufferedImage(txtPanelEditando.getWidth(), txtPanelEditando.getHeight(), BufferedImage.TYPE_INT_BGR);
            //  imaG.setData();

            try {
                //        File file = new File("nombrImg.png");
//                javax.imageio.ImageIO.write( g, "jpg", file);
            } catch (Exception e) {
                System.out.println("" + e);
            }
            txtPanelEditando.setFont(new Font(txtPanelEditando.getFont().getFamily(), txtPanelEditando.getFont().getStyle(), txtPanelEditando.getFont().getSize() - 2));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        String fechaActual = "Fecha Actual: " + dia + "/" + (mes + 1) + "/" + año + " ";
        String horaActual = hora + ":" + minuto + ":" + segundo;
        txtPanelEditando.replaceSelection("\n" + fechaActual + " " + horaActual + "\n");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        /* //CASUAL DE IMPRIMIR    
        PrinterJob pjob = PrinterJob.getPrinterJob();
        pjob.setPrintable(new Printer(txtPanelEditando), pjob.defaultPage());
        if (pjob.printDialog()) {
            try {
                pjob.print();
            } catch (Exception ex) {
                //         Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error " + ex);
            }
        }
         */
        PrinterJob pjob = PrinterJob.getPrinterJob();

        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.PORTRAIT);
        PageFormat postformat = pjob.pageDialog(preformat);

        if (preformat != postformat) {
            pjob.setPrintable(new Printer(txtPanelEditando), postformat);
            if (pjob.printDialog()) {
                try {
                    pjob.print();
                } catch (PrinterException ex) {
                    Mensaje.advertencia(this, "Se ha cancelado la impresion o ha surgudio un error");
                }
            }
        }


    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        txtPanelEditando.setFont(new Font(txtPanelEditando.getFont().getFamily(), txtPanelEditando.getFont().getStyle(), txtPanelEditando.getFont().getSize() + 2));
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void checkEditandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEditandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkEditandoActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        txtPanelEditando.setFont(new Font(txtPanelEditando.getFont().getFamily(), txtPanelEditando.getFont().getStyle(), 14));
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void checkSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSintacticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSintacticoActionPerformed

    private void pestaniasEntradaSalidasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pestaniasEntradaSalidasKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_pestaniasEntradaSalidasKeyPressed
    public int i = 1;
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        crearPagEdicion("", "txt");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        CmpntTabPane panelI = new CmpntTabPane(pestaniasEntradaSalidas, 0, imgCursor);
        JScrollPane scrollPaneI = new JScrollPane();
        scrollPaneI.setViewportView(new Paint());
        if (pestaniasEntradaSalidas.getTabCount() == 0) {
            pestaniasEntradaSalidas.add("Página de inicio", scrollPaneI);
        } else {
            pestaniasEntradaSalidas.insertTab("Pintando", null, scrollPaneI, null, pestaniasEntradaSalidas.getTabCount());
        }
        pestaniasEntradaSalidas.setTabComponentAt(pestaniasEntradaSalidas.getTabCount() - 1, panelI);
        // pestaniasEntradaSalidas.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    public void openEditor() {

        //Items
        CtrlInterfaz.habilita(false, itemEjecutar,
                itemCompilar, itemLexico, itemIntermedio, itemOptmizacion, itemSemantico,
                itemSintactico, itemObjeto, itemGuardar, itemGuardarComo, itemCerrar);
        //MenuOpciones

        CtrlInterfaz.habilita(false, btnBuscar, btnCerrar, btnCompilar, btnCopiar, btnCortar, btnDeshacer,
                btnEjecutar, btnGuardar, btnPegar, btnReemplazar, btnRehacer, btnFormato);
        //PopMenus
        CtrlInterfaz.habilita(false, popMBuscar, popMCopiar, popMCortar, popMDeshacer, popMIdentar,
                popMPegar, popMReemplazar, popMSeleccionTotal, popMenuEditar);
        //Deshabilitar PopCerrar
        CtrlInterfaz.habilita(false, popMCerrar, popMCerrarTodo);
        //Checks
        checkEditando.setSelected(true);
        checkCompilando.setSelected(true);
        checkSalida.setSelected(true);
        checkLexico.setSelected(false);
        checkSintactico.setSelected(false);
        checkSemantico.setSelected(false);
        checkIntermedio.setSelected(false);
        checkOptimizacion.setSelected(false);
        checkObjeto.setSelected(false);
        //Habilitar CheckMenus
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida);
        //Deshabilitar
        CtrlInterfaz.habilita(false, checkIntermedio, checkLexico, checkObjeto, checkOptimizacion, checkSemantico, checkSintactico);
    }

    public void crearPestaniaInicio() {
        if (pestaniasEntradaSalidas.getTabCount() == 0 || scrollPaneInicio == null || !pestaniasEntradaSalidas.getComponentAt(0).equals(scrollPaneInicio)) {
            CmpntTabPane panelI = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageInicio);
            scrollPaneInicio = new JScrollPane();
            scrollPaneInicio.setViewportView(new PageStart());
            if (pestaniasEntradaSalidas.getTabCount() == 0) {
                pestaniasEntradaSalidas.add("Página de inicio", scrollPaneInicio);
            } else {
                pestaniasEntradaSalidas.insertTab("Página de inicio", null, scrollPaneInicio, null, 0);
            }
            pestaniasEntradaSalidas.setTabComponentAt(0, panelI);
            pestaniasEntradaSalidas.setSelectedIndex(0);
        } else {
            if (pestaniasEntradaSalidas.getTabCount() != 0) {
                pestaniasEntradaSalidas.setSelectedIndex(0);
            }
        }

    }
    int tb = 0;

    public void crearPestaniaEdicion() {

        if (txtPanelEditando == null) {
            // txtPanelEditando = new javax.swing.JTextPane();
            scrollPaneEditando = new javax.swing.JScrollPane();

            txtPanelEditando = new HighlightLineTextPaneAzul();
            EmptyBorder eb = new EmptyBorder(new Insets(0, 5, 0, 0));
            txtPanelEditando.setBorder(eb);

            scrollPaneEditando.setName("scrollPaneEditando");

            txtPanelEditando.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelEditando.setSelectionColor(new java.awt.Color(153, 204, 255));

            txtPanelEditando.setEditorKit(new TabSizeEditorKit());
            AbstractDocument documento = (AbstractDocument) txtPanelEditando.getDocument();

            txtPanelEditando.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                    //  documento.setDocumentFilter(new NewLineFilter());
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                    if (evt.getKeyChar() == '\n') {
                        //     highlight(txtPanelEditando, "public");
                    }
                }
            });

            txtPanelEditando.addCaretListener(this::txtPanelEditandoCaretUpdate);
            txtPanelEditando.setComponentPopupMenu(popMenuEditar);
            scrollPaneEditando.setViewportView(txtPanelEditando);
            pestaniasEntradaSalidas.addTab("Editando", scrollPaneEditando);

            numLineEditor = new NumeroLinea(txtPanelEditando);
            scrollPaneEditando.setRowHeaderView(numLineEditor);
            panelTab[0] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageEditar);
            int indexTab = 0;
            for (int p = 0; p < pestaniasEntradaSalidas.getTabCount(); p++) {
                if (pestaniasEntradaSalidas.getTitleAt(p).equals("Editando")) {
                    indexTab = p;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[0]);
            checkEditando.setSelected(true);

        }
    }

    public void crearPestaniaCompilacion() {

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("iconos/17857 - animal bug insect ladybird.png"));
        if (txtPanelCompilando == null) {

            txtPanelCompilando = new HighlightLineTextPaneAzul();
            EmptyBorder eb = new EmptyBorder(new Insets(0, 5, 0, 0));
            txtPanelCompilando.setBorder(eb);

            scrollPaneCompilando = new javax.swing.JScrollPane();
            scrollPaneCompilando.setName("scrollPaneCompilando");
            txtPanelCompilando.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelCompilando.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelCompilando.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });

            scrollPaneCompilando.setViewportView(txtPanelCompilando);
            pestaniasEntradaSalidas.addTab("Compilando", scrollPaneCompilando);
            numLineCompilador = new NumeroLinea(txtPanelCompilando);
            scrollPaneCompilando.setRowHeaderView(numLineCompilador);
            panelTab[1] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageCompilar);

            int indexTab = 0;
            for (int j = 0; j < pestaniasEntradaSalidas.getTabCount(); j++) {
                if (pestaniasEntradaSalidas.getTitleAt(j).equals("Compilando")) {
                    indexTab = j;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[1]);
            checkCompilando.setSelected(true);
        }
    }

    public void crearPestaniaSalida() {

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("iconos/17857 - animal bug insect ladybird.png"));
        if (txtPanelSalida == null) {
            txtPanelSalida = new javax.swing.JTextPane();
            scrollPaneSalida = new javax.swing.JScrollPane();
            scrollPaneSalida.setName("scrollPaneSalida");
            txtPanelSalida.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelSalida.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelSalida.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });

            scrollPaneSalida.setViewportView(txtPanelSalida);
            pestaniasEntradaSalidas.addTab("Salida", scrollPaneSalida);
            numLineSalida = new NumeroLinea(txtPanelSalida);
            scrollPaneSalida.setRowHeaderView(numLineSalida);
            panelTab[2] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageSalida);
            int indexTab = 0;
            for (int p = 0; p < pestaniasEntradaSalidas.getTabCount(); p++) {
                if (pestaniasEntradaSalidas.getTitleAt(p).equals("Salida")) {
                    indexTab = p;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[2]);
            checkSalida.setSelected(true);
        }
    }

    public void crearPestaniaLexico() {

        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("iconos/17857 - animal bug insect ladybird.png"));
        if (txtPanelLexico == null) {
            txtPanelLexico = new javax.swing.JTextPane();
            scrollPaneLexico = new javax.swing.JScrollPane();
            scrollPaneLexico.setName("scrollPaneLexico");
            txtPanelLexico.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelLexico.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelLexico.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });

            // scrollPaneLexico.setViewportView(txtPanelLexico);
            pestaniasEntradaSalidas.addTab("Léxico", scrollPaneLexico);
            //  numLineLexico = new NumeroLinea(txtPanelLexico);
            scrollPaneLexico.setRowHeaderView(numLineLexico);
            panelTab[3] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageLexico);
            int indexTab = 0;
            for (int p = 0; p < pestaniasEntradaSalidas.getTabCount(); p++) {
                if (pestaniasEntradaSalidas.getTitleAt(p).equals("Léxico")) {
                    indexTab = p;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[3]);
            checkLexico.setSelected(true);

        }

    }
    private javax.swing.JSplitPane splitPaneSintactico;

    public void crearPestaniaSintactico() {

        if (txtPanelSintactico1 == null) {
            splitPaneSintactico = new javax.swing.JSplitPane();

            txtPanelSintactico1 = new javax.swing.JTextPane();
            scrollPaneSintactico1 = new javax.swing.JScrollPane();

            scrollPaneSintactico1.setName("scrollPaneSintactico");
            txtPanelSintactico1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelSintactico1.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelSintactico1.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });

            txtPanelSintactico2 = new javax.swing.JTextPane();
            scrollPaneSintactico2 = new javax.swing.JScrollPane();
            scrollPaneSintactico2.setName("scrollPaneSintactico");
            txtPanelSintactico2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelSintactico2.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelSintactico2.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });

            scrollPaneSintactico1.setViewportView(txtPanelSintactico1);
            scrollPaneSintactico2.setViewportView(txtPanelSintactico2);
            splitPaneSintactico.setLeftComponent(scrollPaneSintactico1);
            splitPaneSintactico.setRightComponent(scrollPaneSintactico2);

            pestaniasEntradaSalidas.addTab("Sintáctico", splitPaneSintactico);
            numLineSintactico1 = new NumeroLinea(txtPanelSintactico1);
            scrollPaneSintactico1.setRowHeaderView(numLineSintactico1);
            numLineSintactico2 = new NumeroLinea(txtPanelSintactico2);
            scrollPaneSintactico2.setRowHeaderView(numLineSintactico2);
            panelTab[4] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageSintactico);
            int indexTab = 0;
            for (int p = 0; p < pestaniasEntradaSalidas.getTabCount(); p++) {
                if (pestaniasEntradaSalidas.getTitleAt(p).equals("Sintáctico")) {
                    indexTab = p;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[4]);
            checkSintactico.setSelected(true);

        }
    }

    public void buscaLaLineaError(int i, String c) {

    }

    public void crearPestaniaSemantico() {

        if (txtPanelSemantico == null) {
            txtPanelSemantico = new javax.swing.JTextPane();
            scrollPaneSemantico = new javax.swing.JScrollPane();
            scrollPaneSemantico.setName("scrollPaneSemantico");
            txtPanelSemantico.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelSemantico.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelSemantico.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });
            scrollPaneSemantico.setViewportView(txtPanelSemantico);
            pestaniasEntradaSalidas.addTab("Semántico", scrollPaneSemantico);
            numLineSemantico = new NumeroLinea(txtPanelSemantico);
            scrollPaneSemantico.setRowHeaderView(numLineSemantico);
            panelTab[5] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageSemantico);
            int indexTab = 0;
            for (int j = 0; j < pestaniasEntradaSalidas.getTabCount(); j++) {
                if (pestaniasEntradaSalidas.getTitleAt(j).equals("Semántico")) {
                    indexTab = j;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[5]);
            checkSemantico.setSelected(true);
        }
    }

    public void crearPestaniaIntermedio() {

        if (txtPanelIntermedio == null) {
            txtPanelIntermedio = new javax.swing.JTextPane();
            scrollPaneIntermedio = new javax.swing.JScrollPane();
            scrollPaneIntermedio.setName("scrollPaneIntermedio");
            txtPanelIntermedio.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelIntermedio.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelIntermedio.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });
            scrollPaneIntermedio.setViewportView(txtPanelIntermedio);
            pestaniasEntradaSalidas.addTab("Intermedio", scrollPaneIntermedio);
            numLineIntermedio = new NumeroLinea(txtPanelIntermedio);
            scrollPaneIntermedio.setRowHeaderView(numLineIntermedio);
            panelTab[6] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageIntermedio);
            int indexTab = 0;
            for (int p = 0; p < pestaniasEntradaSalidas.getTabCount(); p++) {
                if (pestaniasEntradaSalidas.getTitleAt(p).equals("Intermedio")) {
                    indexTab = p;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[6]);
            checkIntermedio.setSelected(true);
        }
    }

    public void crearPestaniaOptimizado() {

        if (txtPanelOptimizacion == null) {
            txtPanelOptimizacion = new javax.swing.JTextPane();
            scrollPaneOptimizacion = new javax.swing.JScrollPane();
            scrollPaneOptimizacion.setName("scrollPaneOptimizacion");
            txtPanelOptimizacion.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelOptimizacion.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelOptimizacion.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });
            scrollPaneOptimizacion.setViewportView(txtPanelOptimizacion);
            pestaniasEntradaSalidas.addTab("Optimización", scrollPaneOptimizacion);
            numLineOptimizado = new NumeroLinea(txtPanelOptimizacion);
            scrollPaneOptimizacion.setRowHeaderView(numLineOptimizado);
            panelTab[7] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageOptimizacion);
            int indexTab = 0;
            for (int j = 0; j < pestaniasEntradaSalidas.getTabCount(); j++) {
                if (pestaniasEntradaSalidas.getTitleAt(j).equals("Optimización")) {
                    indexTab = j;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[7]);
            checkOptimizacion.setSelected(true);
        }
    }

    public void crearPestaniaObjeto() {

        if (txtPanelObjeto == null) {
            txtPanelObjeto = new javax.swing.JTextPane();
            scrollPaneObjeto = new javax.swing.JScrollPane();
            scrollPaneObjeto.setName("scrollPaneObjeto");
            txtPanelObjeto.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
            txtPanelObjeto.setSelectionColor(new java.awt.Color(153, 204, 255));
            txtPanelObjeto.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    // txtPanelSalidaKeyPressed(evt);
                }

                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    //txtPanelSalidaKeyTyped(evt);
                }
            });
            scrollPaneObjeto.setViewportView(txtPanelObjeto);
            pestaniasEntradaSalidas.addTab("Objeto", scrollPaneObjeto);
            numLineObjeto = new NumeroLinea(txtPanelObjeto);
            scrollPaneObjeto.setRowHeaderView(numLineObjeto);
            panelTab[8] = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageObjeto);
            int indexTab = 0;
            for (int j = 0; j < pestaniasEntradaSalidas.getTabCount(); j++) {
                if (pestaniasEntradaSalidas.getTitleAt(j).equals("Objeto")) {
                    indexTab = j;
                }
            }
            pestaniasEntradaSalidas.setTabComponentAt(indexTab, panelTab[8]);
            checkObjeto.setSelected(true);
        }
    }

    public void cerrarPestaniaInicio() {
        if (scrollPaneInicio != null) {
            scrollPaneInicio.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneInicio);
            scrollPaneInicio = null;
        }
    }

    public void cerrarPestaniaEdicion() {
        if (txtPanelEditando != null) {
            scrollPaneEditando.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneEditando);
            txtPanelEditando = null;
            scrollPaneEditando = null;
            numLineEditor = null;
            checkEditando.setSelected(false);
        }
    }

    public void cerrarPestaniaCompilacion() {
        if (txtPanelCompilando != null) {
            scrollPaneCompilando.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneCompilando);
            txtPanelCompilando = null;
            scrollPaneCompilando = null;
            numLineCompilador = null;
            checkCompilando.setSelected(false);
        }
    }

    public void cerrarPestaniaSalida() {
        if (txtPanelSalida != null) {
            scrollPaneSalida.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneSalida);
            txtPanelSalida = null;
            scrollPaneSalida = null;
            numLineSalida = null;
            checkSalida.setSelected(false);
        }
    }

    public void cerrarPestaniaLexico() {
        if (txtPanelLexico != null) {
            scrollPaneLexico.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneLexico);
            txtPanelLexico = null;
            scrollPaneLexico = null;
            numLineLexico = null;
            checkLexico.setSelected(false);
        }
    }

    public void cerrarPestaniaSintactico() {
        if (txtPanelSintactico1 != null) {
            scrollPaneSintactico1.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneSintactico1);
            txtPanelSintactico1 = null;
            scrollPaneSintactico1 = null;
            numLineSintactico1 = null;
            checkSintactico.setSelected(false);
        }
        if (txtPanelSintactico2 != null) {
            scrollPaneSintactico1.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneSintactico2);
            txtPanelSintactico2 = null;
            scrollPaneSintactico2 = null;
            numLineSintactico2 = null;
            checkSintactico.setSelected(false);
        }
        pestaniasEntradaSalidas.remove(splitPaneSintactico);
    }

    public void cerrarPestaniaSemantico() {
        if (txtPanelSemantico != null) {
            scrollPaneSemantico.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneSemantico);
            txtPanelSemantico = null;
            scrollPaneSemantico = null;
            numLineSemantico = null;
            checkSemantico.setSelected(false);
        }
    }

    public void cerrarPestaniaIntermedio() {
        if (txtPanelIntermedio != null) {
            scrollPaneIntermedio.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneIntermedio);
            txtPanelIntermedio = null;
            scrollPaneIntermedio = null;
            numLineIntermedio = null;
            checkIntermedio.setSelected(false);
        }
    }

    public void cerrarPestaniaOptimizacion() {
        if (txtPanelOptimizacion != null) {
            scrollPaneOptimizacion.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneOptimizacion);
            txtPanelOptimizacion = null;
            scrollPaneOptimizacion = null;
            numLineOptimizado = null;
            checkOptimizacion.setSelected(false);
        }
    }

    public void cerrarPestaniaObjeto() {
        if (txtPanelObjeto != null) {
            scrollPaneObjeto.removeAll();
            pestaniasEntradaSalidas.remove(scrollPaneObjeto);
            txtPanelObjeto = null;
            scrollPaneObjeto = null;
            numLineObjeto = null;
            checkObjeto.setSelected(false);
        }
    }

    public void closeEditor() {

        if (archivo) {
            if (txtPanelEditando != null) {
                ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);
                txtPanelEditando.setText("");
                setTitle("Editor Planet");
                archivo = false;
            }
            System.exit(0);
        } else if (txtPanelEditando != null) {
            if (txtPanelEditando.getText().trim().length() != 0) {

                int opcion;
                String menu[] = {"Si", "No", "Cancelar"};
                opcion = JOptionPane.showOptionDialog(this, "¿Desea guardar los cambios hechos en el texto?", "Saliendo... Editor Planet", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, menu, menu[0]);

                switch (opcion) {
                    case 0:
                        if (guardarAntesSalir()) {
                            System.exit(0);
                        } else {
                            Mensaje.error(this, "Error al guardar archivo... ");
                        }
                        break;
                    case 1:
                        System.exit(0);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            } else {
                String opc[] = {"Cerrar editor", "Cancelar"};
                int seleccion = JOptionPane.showOptionDialog(this, "¿Desea salir del editor?", "Saliendo... ", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, opc, opc[0]);
                if (seleccion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        } else {
            String opc[] = {"Cerrar editor", "Cancelar"};
            int seleccion = JOptionPane.showOptionDialog(this, "¿Desea salir del editor?", "Saliendo... ", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, opc, opc[0]);
            if (seleccion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public void abrir() {

        try {
            JFileChooser selecciona = new JFileChooser();  //crea objeto file chooser
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt", "txt");  //solo muestra archivos .mp3 en file chocer
            selecciona.setFileFilter(filtro);  //se agrega el filtro al file chooser
            selecciona.showOpenDialog(this);   //sin esto me marcaba error :v
            File archivoExtraido = selecciona.getSelectedFile(); //obtiene el archivo en la variable archivo
            String url; //borra la ruta para evitar que se quede una anterior

            String etiquetaNombre;
            if (archivoExtraido != null) // si no se selecciono ningun archivo no hace nada
            {

                if (archivo) {
                    ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);
                }
                url = archivoExtraido.getCanonicalPath(); //obtiene la ruta del archivo
                etiquetaNombre = archivoExtraido.getName();//obtiene el nombre del archivo/cancion                

                TextPaneTest.appendToPane(txtPanelSalida, "\nAbriendo achivo...", cVerde);
                String etiExt[] = etiquetaNombre.split(".");
                for (String etiExt1 : etiExt) {
                    System.out.println(etiExt1);
                }
                String extencion = "";
                switch (extencion) {
                    case "html":
                        //HTML
                        break;
                    case "css":
                        //CSS
                        break;
                    case "js":
                        //JAVASCRIPT
                        break;
                    case "pl":
                        //PROLOG
                        break;
                    case "csdos":
                        //CS-DOS
                        break;
                    case "txt":
                        //TEXTO PLANO
                        break;
                    case "c":
                        //C
                        break;
                    case "cs":
                        //C#
                        break;
                    case "php":
                        //PHP
                        break;
                    case "sql":
                        //SQL
                        break;
                    case "ino":
                        //ARDUINO
                        break;
                    case "h":
                        //LIBRERIA ARDUINO
                        break;
                    case "class":
                        //CLASE DE JAVA
                        break;
                    case "java":
                        //JAVA
                        break;
                    case "asm":
                        //ENSAMBLADOR
                        break;
                    case "hs":
                        //HASKEL
                        break;
                    default:
                        break;
                }
                String s = "";
                ArrayList<String> ar = ManipulaArchivos.cargar(url);

                rutaArchivo = url;
                this.archivo = true;
                if (ar != null) {
                    for (int j = 0; j < ar.size(); j++) {
                        s += ar.get(j);
                    }
                    //Escribiendo el texto en el editor
                    if (txtPanelEditando == null) {
                        crearPestaniaEdicion();

                        txtPanelEditando.setText(s);
                    } else {
                        if (txtPanelEditando.getText().isEmpty()) {

                            txtPanelEditando.setText(s);
                        } else {
                            crearPagEdicion(s, extencion);
                        }

                    }
                    //Encabezado con el titulo del archivo
                    setTitle("Editor Planet" + " - " + etiquetaNombre);
                    //habilita MenuOpciones
                    CtrlInterfaz.habilita(true, btnGuardar, btnAbrir, btnBuscar, btnCerrar, btnCompilar, btnEjecutar, btnFormato, btnNuevo, btnReemplazar);
                    //Deshabilita MenuOpciones
                    CtrlInterfaz.habilita(false, btnCopiar, btnCortar, btnDeshacer, btnRehacer, btnPegar);
                    //Habilitar itemMenus
                    CtrlInterfaz.habilita(true, itemLexico, itemAbrir, itemCerrar, itemCompilar, itemEjecutar, itemGuardar, itemGuardarComo, itemNuevo);
                    //Deshabilitar itemMenus
                    CtrlInterfaz.habilita(false, itemIntermedio, itemObjeto, itemOptmizacion, itemSemantico, itemSintactico);
                    //Habilitar popMenus
                    CtrlInterfaz.habilita(true, popMBuscar, popMIdentar, popMReemplazar, popMSeleccionTotal);
                    //Deshabilitar popMenus
                    CtrlInterfaz.habilita(false, popMCopiar, popMCortar, popMDeshacer, popMPegar);

                    CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico);
                    CtrlInterfaz.habilita(false, checkIntermedio, checkObjeto, checkOptimizacion, checkSemantico, checkSintactico);
                }

            }

        } catch (HeadlessException | IOException e) {
            TextPaneTest.appendToPane(txtPanelSalida, "\nNo se pudo abrir el achivo..." + e, cRojo);
        }

    }

    public void nuevo() {

        try {
            JFileChooser selecciona = new JFileChooser();  //crea objeto file chooser
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt", "txt");  //solo muestra archivos .mp3 en file chocer
            selecciona.setFileFilter(filtro);  //se agrega el filtro al file chooser
            selecciona.showSaveDialog(this);
            File archivoNuevo = selecciona.getSelectedFile(); //obtiene el archivo en la variable archivo
            String url; //borra la ruta para evitar que se quede una anterior
            String etiquetaNombre;

            if (archivoNuevo != null) // si no se selecciono ningun archivo no hace nada
            {

                if (txtPanelEditando != null) {
                    if (archivo) {
                        ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);
                    }

                } else {
                    crearPestaniaEdicion();

                }
                TextPaneTest.appendToPane(txtPanelSalida, "\nCreando achivo...", cVerde);
                url = archivoNuevo.getCanonicalPath(); //obtiene la ruta del archivo
                etiquetaNombre = archivoNuevo.getName();//obtiene el nombre del archivo
                setTitle("Editor Planet" + " - " + etiquetaNombre);

                txtPanelEditando.setText("");
                ManipulaArchivos.crearNuevo(url);
                rutaArchivo = url;
                archivo = true;
                //habilita MenuOpciones
                CtrlInterfaz.habilita(true, btnGuardar, btnAbrir, btnBuscar, btnCerrar, btnCompilar, btnEjecutar, btnFormato, btnNuevo, btnReemplazar);
                //Deshabilita MenuOpciones
                CtrlInterfaz.habilita(false, btnCopiar, btnCortar, btnDeshacer, btnRehacer, btnPegar);
                //Habilitar itemMenus
                CtrlInterfaz.habilita(true, itemLexico, itemAbrir, itemCerrar, itemCompilar, itemEjecutar, itemGuardar, itemGuardarComo, itemNuevo);
                //Deshabilitar itemMenus
                CtrlInterfaz.habilita(false, itemIntermedio, itemObjeto, itemOptmizacion, itemSemantico, itemSintactico);
                //Habilitar popMenus
                CtrlInterfaz.habilita(true, popMBuscar, popMIdentar, popMReemplazar, popMSeleccionTotal);
                //Deshabilitar popMenus
                CtrlInterfaz.habilita(false, popMCopiar, popMCortar, popMDeshacer, popMPegar);

                CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico);
                CtrlInterfaz.habilita(false, checkIntermedio, checkObjeto, checkOptimizacion, checkSemantico, checkSintactico);
            }

        } catch (HeadlessException | IOException e) {
            TextPaneTest.appendToPane(txtPanelSalida, "\nError al crear nuevo achivo..." + e, cRojo);
        }
    }

    public void guardar() {

        if (archivo) {
            ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);
            TextPaneTest.appendToPane(txtPanelSalida, "\nGuardando achivo...", cVerde);
            Validaciones.enterColor(txtPanelEditando);
        } else {
            if (guardarAntesSalir()) {
                File s = new File(rutaArchivo);
                setTitle("Editor Planet" + " - " + s.getName());
            }
        }
    }

    public void guardarComo() {
        if (archivo) {

            ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);

            try {
                JFileChooser selecciona = new JFileChooser();  //crea objeto file chooser
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt", "txt");  //solo muestra archivos .mp3 en file chocer
                selecciona.setFileFilter(filtro);  //se agrega el filtro al file chooser
                selecciona.showSaveDialog(this);
                File archivoNuevo = selecciona.getSelectedFile(); //obtiene el archivo en la variable archivo
                String url; //borra la ruta para evitar que se quede una anterior
                String etiquetaNombre;

                if (archivoNuevo != null) // si no se selecciono ningun archivo no hace nada
                {

                    url = archivoNuevo.getCanonicalPath(); //obtiene la ruta del archivo
                    etiquetaNombre = archivoNuevo.getName();//obtiene el nombre del archivo/cancion                

                    setTitle("Editor Planet" + " - " + etiquetaNombre);

                    ManipulaArchivos.guardarComo(txtPanelEditando.getText(), url);

                    txtPanelEditando.setText("");

                    String s = ManipulaArchivos.cargarArchivo(url);
                    txtPanelEditando.setText(s);

                    rutaArchivo = url;
                    archivo = true;

                    TextPaneTest.appendToPane(txtPanelSalida, "\nGuardando nuevo achivo...", cVerde);
                    Validaciones.enterColor(txtPanelEditando);
                    //habilita MenuOpciones
                    CtrlInterfaz.habilita(true, btnGuardar, btnAbrir, btnBuscar, btnCerrar, btnCompilar, btnEjecutar, btnFormato, btnNuevo, btnReemplazar);
                    //Deshabilita MenuOpciones
                    CtrlInterfaz.habilita(false, btnCopiar, btnCortar, btnDeshacer, btnRehacer, btnPegar);
                    //Habilitar itemMenus
                    CtrlInterfaz.habilita(true, itemLexico, itemAbrir, itemCerrar, itemCompilar, itemEjecutar, itemGuardar, itemGuardarComo, itemNuevo);
                    //Deshabilitar itemMenus
                    CtrlInterfaz.habilita(false, itemIntermedio, itemObjeto, itemOptmizacion, itemSemantico, itemSintactico);
                    //Habilitar popMenus
                    CtrlInterfaz.habilita(true, popMBuscar, popMIdentar, popMReemplazar, popMSeleccionTotal);
                    //Deshabilitar popMenus
                    CtrlInterfaz.habilita(false, popMCopiar, popMCortar, popMDeshacer, popMPegar);

                    CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico);
                    CtrlInterfaz.habilita(false, checkIntermedio, checkObjeto, checkOptimizacion, checkSemantico, checkSintactico);
                }

            } catch (HeadlessException | IOException e) {
                TextPaneTest.appendToPane(txtPanelSalida, "\nError al guardar nuevo archivo..." + e, cRojo);
            }

        }

    }

    public boolean guardarAntesSalir() {

        try {
            JFileChooser selecciona = new JFileChooser();  //crea objeto file chooser
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .txt", "txt", "txt");  //solo muestra archivos .mp3 en file chocer
            selecciona.setFileFilter(filtro);  //se agrega el filtro al file chooser
            selecciona.showSaveDialog(this);
            File archivoNuevo = selecciona.getSelectedFile(); //obtiene el archivo en la variable archivo
            String url; //borra la ruta para evitar que se quede una anterior

            if (archivoNuevo != null) // si no se selecciono ningun archivo no hace nada
            {
                url = archivoNuevo.getCanonicalPath(); //obtiene la ruta del archivo
                ManipulaArchivos.guardarComo(txtPanelEditando.getText(), url);
                rutaArchivo = url;
                archivo = true;

                return true;
            }

        } catch (HeadlessException | IOException e) {
            return false;
        }
        return false;

    }

    public void cerrar() {

        if (archivo) {
            ManipulaArchivos.guardar(txtPanelEditando.getText(), rutaArchivo);
            TextPaneTest.appendToPane(txtPanelSalida, "\nCerrando archivo...", cVerde);
            txtPanelEditando.setText("");
            setTitle("Editor Planet");
            archivo = false;
            rutaArchivo = "";

            //habilita MenuOpciones
            CtrlInterfaz.habilita(true, btnAbrir, btnNuevo);
            //Deshabilita MenuOpciones
            CtrlInterfaz.habilita(false, btnCopiar, btnCortar, btnDeshacer, btnRehacer, btnPegar, btnReemplazar, btnFormato, btnEjecutar,
                    btnBuscar, btnCerrar, btnCompilar, btnGuardar);
            //Habilitar itemMenus
            CtrlInterfaz.habilita(true, itemAbrir, itemNuevo);
            //Deshabilitar itemMenus
            CtrlInterfaz.habilita(false, itemLexico, itemCerrar, itemIntermedio, itemObjeto, itemOptmizacion, itemSemantico, itemSintactico, itemCompilar, itemEjecutar, itemGuardar, itemGuardarComo);

            //Deshabilitar popMenus
            CtrlInterfaz.habilita(false, popMCopiar, popMCortar, popMDeshacer, popMPegar, popMBuscar, popMIdentar, popMReemplazar, popMSeleccionTotal);
        }
    }
    ArrayList<Lexema> lexemas;

    public void lexico() {

        bError = false;
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico);
        CtrlInterfaz.habilita(false, itemSemantico, itemIntermedio, itemOptmizacion, itemObjeto);
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico);
        CtrlInterfaz.habilita(false, checkIntermedio, checkObjeto, checkOptimizacion, checkSemantico);
        TextPaneTest.appendToPane(txtPanelSalida, "\nRealizando análisis léxico...", cVerde);


        //char[] alfabeto = {'"', 'A', 'L', '$', '&', '_', '~', '@', 'D', '-', '#', '\'', 'C', '.', '?'};
        //Lexico analisisLexico = new Lexico(txtPanelEditando.getText(), "+-=*&| {}()[]!?^/%;:,<>\n\t\r\b\f", matrizGeneral, alfabeto);
        Lexico analisisLexico = new Lexico(txtPanelEditando.getText(), "+-=*&| {}()[]!?^/%;:,<>\n\t\r\b\f", "Tabla del automata general.xlsx");

        lexemas = analisisLexico.analisisLexico();
        javax.swing.JTable tablaLexemas = new javax.swing.JTable();

        tablaLexemas.setFont(new java.awt.Font("Arial", 0, 14));

        tablaLexemas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tablaLexemas.getTableHeader().setReorderingAllowed(false);
        tablaLexemas.setGridColor(new java.awt.Color(204, 204, 204));
        tablaLexemas.setRowHeight(20);
        tablaLexemas.setSelectionBackground(new java.awt.Color(0, 102, 102));

        tablaLexemas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        DefaultTableModel model;
        String[] columnas = {"Lexema", "Nombre del token", "Numero de token", "Renglon"};
        model = new DefaultTableModel(null, columnas);
        String[] filas = new String[4];

        for (int p = 0; p < lexemas.size(); p++) {
            //textoMostrar += " " +  + "\t" +  + "\t" + lexemas.get(i).getNumToken() + "\t" + lexemas.get(i).getRenglon() + "\n";

            filas[0] = lexemas.get(p).getLexema();
            filas[1] = lexemas.get(p).getNombreToken();
            filas[2] = String.valueOf(lexemas.get(p).getNumToken());
            filas[3] = String.valueOf(lexemas.get(p).getRenglon());

            model.addRow(filas);
        }
        tablaLexemas.setModel(model);

        scrollPaneLexico.setViewportView(tablaLexemas);

        int errora = 0;

        for (int p = 0; p < lexemas.size(); p++) {
            //textoMostrar += " " +  + "\t" +  + "\t" + lexemas.get(i).getNumToken() + "\t" + lexemas.get(i).getRenglon() + "\n";
            if (lexemas.get(p).getNumToken() == 80 || lexemas.get(p).getNumToken() == 81 || lexemas.get(p).getNumToken() == 82 || lexemas.get(p).getNumToken() == 83 || lexemas.get(p).getNumToken() == 84 || lexemas.get(p).getNumToken() == 85 || lexemas.get(p).getNumToken() == 86 || lexemas.get(p).getNumToken() == 87 || lexemas.get(p).getNumToken() == 88 || lexemas.get(p).getNumToken() == 89 || lexemas.get(p).getNumToken() == 90 || lexemas.get(p).getNumToken() == 91 || lexemas.get(p).getNumToken() == 92 || lexemas.get(p).getNumToken() == 93 || lexemas.get(p).getNumToken() == 94) {
                errora = p;
                bError = true;
                break;
            }
        }
        if (bError) {
            TextPaneTest.appendToPane(txtPanelSalida, "\nLexicamente incorrecto... " + lexemas.get(errora).getNombreToken()
                    + " por " + lexemas.get(errora).getLexema() + " en la linea " + lexemas.get(errora).getRenglon(), cRojo);
        } else {
            TextPaneTest.appendToPane(txtPanelSalida, "\nLexicamente correcto...", cVerde);
        }

    }
    boolean bError = false;

    public void sintactico() {
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico, itemSemantico);
        CtrlInterfaz.habilita(false, itemIntermedio, itemOptmizacion, itemObjeto);
        TextPaneTest.appendToPane(txtPanelSalida, "\nRealizando análisis sintáctico...", cVerde);

        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico, checkSemantico);
        CtrlInterfaz.habilita(false, checkIntermedio, checkObjeto, checkOptimizacion);
        if (lexemas.isEmpty()) {
            TextPaneTest.appendToPane(txtPanelSalida, "\nNo se puede hacer el análisis sintactico, realice el análisis léxico...", cRojo);
        } else {

            Sintactico analisisSintactico = new Sintactico();
            analisisSintactico.setLexemas(lexemas);
            analisisSintactico.setPathGramatica("gramaticaLeng.txt");
            analisisSintactico.setPathTablaExcel("TablaPredictivaV1.xlsx");
            String o[] = analisisSintactico.analisisSintactico();
            txtPanelSintactico1.setText(o[0]);
            txtPanelSintactico2.setText(o[1]);
            int contador = 0;
            int renglonCaret = 0;

            if (analisisSintactico.getNumError() != 0) {
                StringTokenizer st = new StringTokenizer(txtPanelEditando.getText(), "+ -=*&| {}()[]^/%;:,<>\n\t\r!", true);

                while (st.hasMoreElements()) {
                    String cad = st.nextToken();
                    if (contador == analisisSintactico.getNumError()) {
                        break;
                    }
                    if ("\n".equals(cad)) {
                        contador++;
                        renglonCaret++;
                    } else {
                        char[] toCharArray = cad.toCharArray();
                        renglonCaret += toCharArray.length;

                    }

                }

                txtPanelEditando.setCaretPosition(renglonCaret - 1);

            }
            if (analisisSintactico.getNumError() != 0) {
                TextPaneTest.appendToPane(txtPanelSalida, "\nSintacticamente incorrecto..." + " error en la línea " + analisisSintactico.getNumError(), cRojo);
            } else {
                TextPaneTest.appendToPane(txtPanelSalida, "\nSintacticamente correcto...", cVerde);
            }
        }

    }

    public void semantico() {
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico, itemSemantico, itemIntermedio);
        CtrlInterfaz.habilita(false, itemOptmizacion, itemObjeto);
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico, checkSemantico, checkIntermedio);
        CtrlInterfaz.habilita(false, checkObjeto, checkOptimizacion);
        TextPaneTest.appendToPane(txtPanelSalida, "\nRealizando análisis semántico...", cVerde);

    }

    public void intermedio() {
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico, itemSemantico, itemIntermedio, itemOptmizacion);
        CtrlInterfaz.habilita(false, itemObjeto);
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico, checkSemantico, checkIntermedio, checkOptimizacion);
        CtrlInterfaz.habilita(false, checkObjeto);
        TextPaneTest.appendToPane(txtPanelSalida, "\nGenerando código intermedio...", cVerde);
    }

    public void optimizacion() {
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico, itemSemantico, itemIntermedio, itemOptmizacion, itemObjeto);
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico, checkSemantico, checkIntermedio, checkOptimizacion, checkObjeto);

        TextPaneTest.appendToPane(txtPanelSalida, "\nRealizando optimización del código...", cVerde);

    }

    public void objeto() {
        CtrlInterfaz.habilita(true, itemGuardar, itemLexico, itemSintactico, itemSemantico, itemIntermedio, itemOptmizacion, itemObjeto);
        CtrlInterfaz.habilita(true, checkEditando, checkCompilando, checkSalida, checkLexico, checkSintactico, checkSemantico, checkIntermedio, checkOptimizacion, checkObjeto);
        TextPaneTest.appendToPane(txtPanelSalida, "\nGenerando código objeto...", cVerde);

    }

    public void compilar() {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        crearPestaniaLexico();
        lexico();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (!bError) {

            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            crearPestaniaSintactico();
            sintactico();
            bError = false;

            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            Mensaje.advertencia(this, "El analisis sintactico no se puede realizar, debido a que hay un error léxico");
            TextPaneTest.appendToPane(txtPanelSalida, "\nError al realizar analisis sinctatico...", cRojo);

        }

    }

    public void ejecutar() {
        VtnEjecutar c = new VtnEjecutar();
        c.setTexto(txtPanelEditando.getText());
        c.setePP(this);
        c.setVisible(true);
    }

    public void fuente() {
        VtnConfiguracion c = new VtnConfiguracion(this, true);
        c.setVisible(true);

    }

    public void ayuda() {
        VtnAyuda vtnAy = new VtnAyuda(this, false);
        vtnAy.setVisible(true);
    }

    public void acercaDe() {
        VtnAcercaDe abaut = new VtnAcercaDe(this, false);
        abaut.setVisible(true);
    }

    public void eliminarPalabra() {

    }

    public void limpiar() {

    }

    public void cortar(String txtCopy) {
        StringSelection ss = new StringSelection(txtCopy);

        //Preparar el portapapeles
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        //Ajustamos el contenido al portapapeles
        cb.setContents(ss, null);
    }

    public void copiar(String txtCopy) {
        StringSelection ss = new StringSelection(txtCopy);

        //Preparar el portapapeles
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        //Ajustamos el contenido al portapapeles
        cb.setContents(ss, null);
    }

    public String pegar() {
        String rs = "";
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        //Almacenar en "contenido" todo lo que tiene el portapapeles
        Transferable contenido = cb.getContents(null);
        try {
            if (contenido.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                //Casteamos y guardamos el resultado
                rs = (String) contenido.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (UnsupportedFlavorException | IOException e) {
            Mensaje.error(this, "Ha ocurrido un error al pegar contenido");
        }

        return rs;
    }

    public void deshacer() {
        if (pilaDeshacer.getTope() != null) {

            pilaRehacer.inserta(pilaDeshacer.elimina(null), null);
            btnRehacer.setEnabled(true);
            itemRehacer.setEnabled(true);
            if (pilaDeshacer.getTope() == null) {
                txtPanelEditando.setText("");
                btnDeshacer.setEnabled(false);
                itemDeshacer.setEnabled(false);
            } else {
                txtPanelEditando.setText(pilaDeshacer.getTope().getS());
            }

        } else {
            btnDeshacer.setEnabled(false);
            itemDeshacer.setEnabled(false);
        }
    }

    public void rehacer() {

        if (pilaRehacer.getTope() != null) {
            pilaDeshacer.inserta(pilaRehacer.elimina(null), null);
            btnDeshacer.setEnabled(true);
            itemDeshacer.setEnabled(true);
            txtPanelEditando.setText(pilaDeshacer.getTope().getS());
            if (pilaRehacer.getTope() == null) {
                btnRehacer.setEnabled(false);
                itemRehacer.setEnabled(false);
            }

        } else {
            btnRehacer.setEnabled(false);
            itemRehacer.setEnabled(false);
        }
    }

    public void buscar() {
        VtnBuscarReemplazar vtnBR = new VtnBuscarReemplazar(this, false);
        vtnBR.getjTabbedPane1().setSelectedIndex(0);
        vtnBR.setVisible(true);
    }

    public void buscarReemplazar() {
        VtnBuscarReemplazar vtnBR = new VtnBuscarReemplazar(this, false);
        vtnBR.getjTabbedPane1().setSelectedIndex(1);
        vtnBR.setVisible(true);
    }

    public void seleccionarPalabra() {
        int finalS = txtPanelEditando.getSelectionEnd();
        int inicioS = txtPanelEditando.getSelectionStart();

        if (inicioS != finalS) {
            palabraSeleccionada = txtPanelEditando.getText().substring(inicioS, finalS);

            //Habilitar PopMenus
            CtrlInterfaz.habilita(true, popMBuscar,
                    popMCopiar, popMCortar);
            //Habilitar MenuOpciones
            CtrlInterfaz.habilita(true, btnBuscar,
                    btnCopiar, btnCortar);

        } else {
            //deshabilitar popMenu
            CtrlInterfaz.habilita(false, popMBuscar,
                    popMCopiar, popMCortar);
            //Desabilitar MenuOpciones
            CtrlInterfaz.habilita(false, btnBuscar,
                    btnCopiar, btnCortar);
        }
    }

    public void seleccionarTodoCodigo() {
        txtPanelEditando.setSelectionEnd(0);
        txtPanelEditando.setSelectionStart(txtPanelEditando.getText().length());
        int finalS = txtPanelEditando.getSelectionEnd();
        int inicioS = txtPanelEditando.getSelectionStart();
        if (inicioS != finalS) {
            cadenaSeleccionada = txtPanelEditando.getText().substring(inicioS, finalS);
            //Habilitar popMenu
            CtrlInterfaz.habilita(true, popMBuscar,
                    popMCopiar, popMCortar);
            //Habilitar MenuOpciones
            CtrlInterfaz.habilita(true, btnBuscar,
                    btnCopiar, btnCortar);

        } else {
            //deshabilitar popMenu
            CtrlInterfaz.habilita(false, popMBuscar,
                    popMCopiar, popMCortar);
            //desabilitar MenuOpciones
            CtrlInterfaz.habilita(false, btnBuscar,
                    btnCopiar, btnCortar);
        }
    }

    public void seleccionarCadena() {
        int finalS = txtPanelEditando.getSelectionEnd();
        int inicioS = txtPanelEditando.getSelectionStart();
        if (inicioS != finalS) {
            cadenaSeleccionada = txtPanelEditando.getText().substring(inicioS, finalS);
            //Habilitar popMenu
            CtrlInterfaz.habilita(true, popMBuscar,
                    popMCopiar, popMCortar);
            //Habilitar MenuOpciones
            CtrlInterfaz.habilita(true, btnBuscar,
                    btnCopiar, btnCortar);

        } else {
            //deshabilitar popMenu
            CtrlInterfaz.habilita(false, popMBuscar,
                    popMCopiar, popMCortar);
            //desabilitar MenuOpciones
            CtrlInterfaz.habilita(false, btnBuscar,
                    btnCopiar, btnCortar);
        }

    }

    public void editando() {
        CtrlInterfaz.habilita(true, btnGuardar, btnEjecutar, btnCerrar, btnReemplazar, btnBuscar, btnCompilar);
        //Habilitar popMenu
        CtrlInterfaz.habilita(true, popMBuscar, popMDeshacer, popMPegar, popMReemplazar, popMSeleccionTotal);
        //Habilitar itemMenus
        CtrlInterfaz.habilita(true, itemCompilar, itemEjecutar, itemGuardar, itemGuardarComo, itemNuevo, itemLexico);
        //Deshabilitar itemMenus
        CtrlInterfaz.habilita(false, itemIntermedio, itemObjeto, itemOptmizacion, itemSemantico, itemSintactico);

    }

    public void formato() {
        Validaciones.enterColor(txtPanelEditando);
    }

    public void esperandoDatos(String s) {
        TextPaneTest.appendToPane(txtPanelSalida, s + "\n", cNegro);
        pointInicio = txtPanelSalida.getText().length();
        txtPanelSalida.setEditable(true);
    }

    public void introductiendoDatos() {
        //pointFinal = txtPanelEditando.getCaretPosition();
        //pointFinal = txtPanelSalida.getCaretPosition();
        StyleContext sc2 = StyleContext.getDefaultStyleContext();
        AttributeSet aset2 = sc2.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);

        int len2 = txtPanelSalida.getDocument().getLength();
        txtPanelSalida.setCaretPosition(len2);
        txtPanelSalida.setCharacterAttributes(aset2, true);
        txtPanelSalida.replaceSelection("");

    }

    public void entradandoDatos() {
        pointFinal = txtPanelSalida.getText().length();
        txtPanelSalida.setEditable(false);
        entrada = txtPanelSalida.getText().substring(pointInicio, pointFinal);
        System.out.println("Salida del dato: " + entrada);

        TextPaneTest c = new TextPaneTest();
        TextPaneTest.ponerEstilo(txtPanelSalida, "CURSIVA Y NEGRITA", "\n" + entrada);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "ERROR  ", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EditorPlanetProgramming().setVisible(true);
        });
    }

    public JTextPane getTxtPanelCompilando() {
        return txtPanelCompilando;
    }

    public void setTxtPanelCompilando(JTextPane txtPanelCompilando) {
        this.txtPanelCompilando = txtPanelCompilando;
    }

    //Declaracion de variables estáticas de la clase 
    public static String fontEditor = "Monospaced";
    public static int tamanio = 14;
    public static int estilo = 0;
    public static int defecto = 0;
    public static int caracter = 0;
    public static int comentario = 0;
    public static int entidad_de_referencia = 0;
    public static int error = 0;
    public static int campo = 0;
    public static int identificador = 0;
    public static int palabra_clave = 0;
    public static int atributo_de_marcado = 0;
    public static int valor_del_atributo_marcado = 0;
    public static int elemento_marcado_Etiqueta = 0;
    public static int metodo = 0;
    public static int numero = 0;
    public static int cadena = 0;
    public static int operador = 0;
    public static int separador = 0;
    public static int URL = 0;
    public static int precaución = 0;
    public static int espacio_vacio = 0;

    public static Color cVerde = new Color(0, 153, 0);
    public static Color cRojo = new Color(255, 0, 0);
    public static Color cNegro = new Color(0, 0, 0);
    public static Color cGris = new Color(192, 192, 192);
    public static Color cNumeros = new Color(192, 192, 192);
    public static Color cCadenas = new Color(192, 192, 192);
    public static Color cCaracteres = new Color(192, 192, 192);
    public static Color cCaracteresEspeciales = new Color(192, 192, 192);
    public static Color cComentariosSimples = new Color(192, 192, 192);
    public static Color cPalabrasReservadas = new Color(192, 192, 192);
    public static Color cOperadoresAritmeticos = new Color(192, 192, 192);
    public static Color cOperadoresRelacionales = new Color(192, 192, 192);
    public static Color cCerraduras = new Color(192, 192, 192);
    public static Color cConstantes = new Color(192, 192, 192);
    public static Color cVariables = new Color(192, 192, 192);
    public static Color cFondo = new Color(192, 192, 192);
    public static Color cDefectoTexto = new Color(0, 0, 0);
    public static Color cEntidadDeReferencia = new Color(0, 0, 0);
    public static Color cError = new Color(0, 0, 0);
    public static Color cCampo = new Color(0, 0, 0);
    public static Color cIdentificador = new Color(0, 0, 0);
    public static Color cAtributoDeMarcado = new Color(0, 0, 0);
    public static Color cValorDelAtributoMarcado = new Color(0, 0, 0);
    public static Color cElemento = new Color(0, 0, 0);
    public static Color cMarcaeEtiqueta = new Color(0, 0, 0);
    public static Color cMetodo = new Color(0, 0, 0);
    public static Color cSeparador = new Color(0, 0, 0);
    public static Color cURL = new Color(0, 0, 0);
    public static Color cAdvertencia = new Color(0, 0, 0);
    public static Color cEspacioVacio = new Color(0, 0, 0);

    //Fin de la eclaracion de variables estáticas de la clase 
    //Declaracion de constantes de la clase 
    private final ImageIcon imageEditar = new ImageIcon(ClassLoader.getSystemResource("iconos/18171 - editors package.png"));
    private final ImageIcon imageCompilar = new ImageIcon(ClassLoader.getSystemResource("iconos/118830 - terminal utilities_1.png"));
    private final ImageIcon imageSalida = new ImageIcon(ClassLoader.getSystemResource("iconos/18046 - konsole.png"));
    private final ImageIcon imageLexico = new ImageIcon(ClassLoader.getSystemResource("iconos/94476 - enum list off stock.png"));
    private final ImageIcon imageSintactico = new ImageIcon(ClassLoader.getSystemResource("iconos/94481 - insert list stock unnumbered.png"));
    private final ImageIcon imageSemantico = new ImageIcon(ClassLoader.getSystemResource("iconos/94478 - enum list restart stock.png"));
    private final ImageIcon imageIntermedio = new ImageIcon(ClassLoader.getSystemResource("iconos/94475 - enum list stock.png"));
    private final ImageIcon imageOptimizacion = new ImageIcon(ClassLoader.getSystemResource("iconos/18418 - moc src.png"));
    private final ImageIcon imageObjeto = new ImageIcon(ClassLoader.getSystemResource("iconos/94494 - macro objects stock.png"));
    private final ImageIcon imageInicio = new ImageIcon(ClassLoader.getSystemResource("imagenes/planetUrano - copia.png"));
    //Fin de la declaracion de constantes
    private NumeroLinea numLineEditor;
    private NumeroLinea numLineCompilador;
    private NumeroLinea numLineSalida;
    private NumeroLinea numLineLexico;
    private NumeroLinea numLineSintactico1;
    private NumeroLinea numLineSintactico2;
    private NumeroLinea numLineSemantico;
    private NumeroLinea numLineIntermedio;
    private NumeroLinea numLineObjeto;
    private NumeroLinea numLineOptimizado;

    private boolean archivo = false;
    public String rutaArchivo = "";

    private String cadenaSeleccionada = "";
    private String palabraSeleccionada = "";
    private String textoPanelEditando = "";
    private String textoPanelCompilando = "";
    private String textoPanelSalida = "";
    private String textoPanelLexico = "";
    private String textoPanelSintactico = "";
    private String textoPanelSemantico = "";
    private String textoPanelIntermedio = "";
    private String textoPanelOptimizacion = "";
    private String textoPanelObjeto = "";

    private String entrada = "";
    private int pointInicio = 0;
    private int pointFinal = 0;

    private int vecSal[];
    private final CmpntTabPane panelTab[];
    //Cursor
    private final ImageIcon imgCursor = new ImageIcon(ClassLoader.getSystemResource("iconos/17857 - animal bug insect ladybird.png"));
    private Cursor cursorForm1;
    private final Toolkit tk = Toolkit.getDefaultToolkit();
    //Ajustar a la pantalla
    private int altoScreen;
    private int anchoScreen;
    //Cambiar orden a las pestañas
    private int tabIndexOrigen = 0;
    String nameComponente = "";
    String textoComponente = "";
    //Declaracion de la pila de deshacer y rehacer
    private PilaD pilaDeshacer = null;
    private PilaD pilaRehacer = null;
    //Declaracion de los nodos de la pila
    private Nodo n1;
    private Nodo n2;
    //Declaracion de variables 
    private javax.swing.JScrollPane scrollPaneInicio;
    private javax.swing.JScrollPane scrollPaneLexico;
    private javax.swing.JScrollPane scrollPaneSintactico1;
    private javax.swing.JScrollPane scrollPaneSintactico2;
    private javax.swing.JScrollPane scrollPaneSemantico;
    private javax.swing.JScrollPane scrollPaneIntermedio;
    private javax.swing.JScrollPane scrollPaneOptimizacion;
    private javax.swing.JScrollPane scrollPaneObjeto;
    private javax.swing.JScrollPane scrollPaneCompilando;
    private javax.swing.JScrollPane scrollPaneEditando;
    private javax.swing.JScrollPane scrollPaneSalida;
    private javax.swing.JTextPane txtPanelCompilando;
    private javax.swing.JTextPane txtPanelEditando;
    private javax.swing.JTextPane txtPanelSalida;
    private javax.swing.JTextPane txtPanelLexico;
    private javax.swing.JTextPane txtPanelSintactico1;
    private javax.swing.JTextPane txtPanelSintactico2;
    private javax.swing.JTextPane txtPanelSemantico;
    private javax.swing.JTextPane txtPanelIntermedio;
    private javax.swing.JTextPane txtPanelOptimizacion;
    private javax.swing.JTextPane txtPanelObjeto;
    //Fin de declaracion de variables
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuInicio;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAyudar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnCortar;
    private javax.swing.JButton btnDeshacer;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnFormato;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPegar;
    private javax.swing.JButton btnReemplazar;
    private javax.swing.JButton btnRehacer;
    private javax.swing.JButton btnTraducir;
    private javax.swing.JCheckBoxMenuItem checkCompilando;
    private javax.swing.JCheckBoxMenuItem checkEditando;
    private javax.swing.JCheckBoxMenuItem checkIntermedio;
    private javax.swing.JCheckBoxMenuItem checkLexico;
    private javax.swing.JCheckBoxMenuItem checkObjeto;
    private javax.swing.JCheckBoxMenuItem checkOptimizacion;
    private javax.swing.JCheckBoxMenuItem checkSalida;
    private javax.swing.JCheckBoxMenuItem checkSemantico;
    private javax.swing.JCheckBoxMenuItem checkSintactico;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemAcerca;
    private javax.swing.JMenuItem itemAyuda;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemBusquedaAnterior;
    private javax.swing.JMenuItem itemBusquedaSiguiente;
    private javax.swing.JMenuItem itemCerrar;
    private javax.swing.JMenuItem itemCompilar;
    private javax.swing.JMenuItem itemCopiar;
    private javax.swing.JMenuItem itemCortar;
    private javax.swing.JMenuItem itemDeshacer;
    private javax.swing.JMenuItem itemEjecutar;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JMenuItem itemFormato;
    private javax.swing.JMenuItem itemFuente;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemGuardarComo;
    private javax.swing.JMenuItem itemIntermedio;
    private javax.swing.JMenuItem itemLexico;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemObjeto;
    private javax.swing.JMenuItem itemOptmizacion;
    private javax.swing.JMenuItem itemPegar;
    private javax.swing.JMenuItem itemReemplazar;
    private javax.swing.JMenuItem itemRehacer;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemSeleccionTotal;
    private javax.swing.JMenuItem itemSemantico;
    private javax.swing.JMenuItem itemSintactico;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JLabel lblProgreso;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuEdicion;
    private javax.swing.JMenu menuEjecicion;
    private javax.swing.JMenu menuFormato;
    private javax.swing.JMenu menuProcesos;
    private javax.swing.JMenu menuVista;
    private javax.swing.JTabbedPane pestaniasEntradaSalidas;
    private javax.swing.JMenuItem popMBuscar;
    private javax.swing.JMenuItem popMCerrar;
    private javax.swing.JMenuItem popMCerrarTodo;
    private javax.swing.JMenuItem popMCopiar;
    private javax.swing.JMenuItem popMCortar;
    private javax.swing.JMenuItem popMDerecha;
    private javax.swing.JMenuItem popMDeshacer;
    private javax.swing.JMenuItem popMIdentar;
    private javax.swing.JMenuItem popMIzquierda;
    private javax.swing.JMenuItem popMLimpiar;
    private javax.swing.JMenuItem popMPegar;
    private javax.swing.JMenuItem popMReemplazar;
    private javax.swing.JMenuItem popMRehacer;
    private javax.swing.JMenuItem popMSeleccionTotal;
    private javax.swing.JPopupMenu popMenuCerrar;
    private javax.swing.JPopupMenu popMenuEditar;
    private javax.swing.JProgressBar progressBarEditor;
    private javax.swing.JPopupMenu.Separator psEntreChecks;
    private javax.swing.JPopupMenu.Separator spArchivo;
    private javax.swing.JPopupMenu.Separator spAyuda;
    private javax.swing.JToolBar.Separator spEntreArchivo;
    private javax.swing.JToolBar.Separator spEntreBarraProgress;
    private javax.swing.JToolBar.Separator spEntreBusqueda;
    private javax.swing.JPopupMenu.Separator spEntreCerrarTodo;
    private javax.swing.JPopupMenu.Separator spEntreDeshacer;
    private javax.swing.JToolBar.Separator spEntreEdicion;
    private javax.swing.JToolBar.Separator spEntreEjecucion;
    private javax.swing.JPopupMenu.Separator spEntrePegar;
    private javax.swing.JPopupMenu.Separator spEntreReemplazar;
    private javax.swing.JPopupMenu.Separator spEntreReemplazoFormato;
    private javax.swing.JPopupMenu.Separator spEntreSeleccionBusqueda;
    private javax.swing.JPopupMenu.Separator spEntrerRehacerCortar;
    private javax.swing.JToolBar toolBarAccesosDirectos;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTabbedPane getPestaniasEntradaSalidas() {
        return pestaniasEntradaSalidas;
    }

    public void setPestaniasEntradaSalidas(javax.swing.JTabbedPane pestaniasEntradaSalidas) {
        this.pestaniasEntradaSalidas = pestaniasEntradaSalidas;
    }

    public Image zoomImage(int w, int h, Image img) {
        BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D grf = buf.createGraphics();
        grf.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        grf.drawImage(img, 0, 0, w, h, null);
        grf.dispose();

        return buf;
    }

    private void crearPagEdicion(String s, String extension) {
        javax.swing.JScrollPane scrollPag = new javax.swing.JScrollPane();
        PanelTextoEditor pag = new PanelTextoEditor();

        // txtPanelEditando = new javax.swing.JTextPane();
        pag.setComponentPopupMenu(popMenuEditar);
        scrollPag.setViewportView(pag);
        pestaniasEntradaSalidas.addTab("Nuevo " + i, scrollPag);
        i++;
        NumeroLinea numLinea = new NumeroLinea(pag);
        scrollPag.setRowHeaderView(numLinea);

        CmpntTabPane pnlTab = new CmpntTabPane(pestaniasEntradaSalidas, 0, imageEditar);
        int indexTab = 0;
        /*
        for (int i = 0; i < pestaniasEntradaSalidas.getTabCount(); i++) {
            if (pestaniasEntradaSalidas.getTitleAt(i).equals("Editando")) {
                indexTab = i;
            }
        }
         */
        AbstractDocument doc = (AbstractDocument) pag.getDocument();
        doc.setDocumentFilter(new NewLineFilter());
        pag.setText(s);
        pestaniasEntradaSalidas.setTabComponentAt((pestaniasEntradaSalidas.getTabCount() - 1), pnlTab);
        /*
        checkEditando.setSelected(true);
         */
    }

    private void formatoIngles() {

    }

    private static class HighlightLineTextPaneAzul extends JTextPane {

        public HighlightLineTextPaneAzul() {
            // Has to be marked as transparent so the background is not replaced by 
            // super.paintComponent(g); 
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            try {
                Rectangle rect = modelToView(getCaretPosition());
                if (rect != null) {
                    g.setColor(new java.awt.Color(232, 242, 254));
                    // g.setColor(new java.awt.Color(255, 153, 153));

                    g.fillRect(0, rect.y, getWidth(), rect.height);
                }
            } catch (BadLocationException e) {
            }
            super.paintComponent(g);
        }

        @Override
        public void repaint(long tm, int x, int y, int width, int height) {
            // This forces repaints to repaint the entire TextPane. 
            super.repaint(tm, 0, 0, getWidth(), getHeight());
        }
    }

// Creates highlights around all occurrences of pattern in textComp
    public void highlight(JTextComponent textComp, String pattern) {
        // First remove all old highlights
        removeHighlights(textComp);

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }
        } catch (BadLocationException e) {
        }
    }

// Removes only our private highlights
    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (Highlighter.Highlight hilite1 : hilites) {
            if (hilite1.getPainter() instanceof MyHighlightPainter) {
                hilite.removeHighlight(hilite1);
            }
        }
    }

// An instance of the private subclass of the default highlight painter
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.YELLOW);

// A private subclass of the default highlight painter
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

}
