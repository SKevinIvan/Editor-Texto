/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetprogrammig;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JColorChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import recursos.NumeroLinea;
import recursos.TabbedPaneUI;

/**
 *
 * @author kevin2
 */
public class VtnConfiguracion extends javax.swing.JDialog {

    private String fuente = EditorPlanetProgramming.fontEditor;
    private int dimension = EditorPlanetProgramming.tamanio;
    private int estilo = EditorPlanetProgramming.estilo;
    private int defecto = EditorPlanetProgramming.defecto;
    private int caracter = EditorPlanetProgramming.caracter;
    private int comentario = EditorPlanetProgramming.comentario;
    private int entidad_de_referencia = EditorPlanetProgramming.entidad_de_referencia;
    private int error = EditorPlanetProgramming.error;
    private int campo = EditorPlanetProgramming.campo;
    private int identificador = EditorPlanetProgramming.identificador;
    private int palabra_clave = EditorPlanetProgramming.palabra_clave;
    private int atributo_de_marcado = EditorPlanetProgramming.atributo_de_marcado;
    private int valor_del_atributo_marcado = EditorPlanetProgramming.valor_del_atributo_marcado;
    private int elemento_marcado_Etiqueta = EditorPlanetProgramming.elemento_marcado_Etiqueta;
    private int metodo = EditorPlanetProgramming.metodo;
    private int numero = EditorPlanetProgramming.numero;
    private int cadena = EditorPlanetProgramming.cadena;
    private int operador = EditorPlanetProgramming.operador;
    private int separador = EditorPlanetProgramming.separador;
    private int URL = EditorPlanetProgramming.URL;
    private int precaucion = EditorPlanetProgramming.precaución;
    private int espacio_vacio = EditorPlanetProgramming.espacio_vacio;

    private Color colorFondo = EditorPlanetProgramming.cFondo;
    private Color colorTexto = EditorPlanetProgramming.cCadenas;
    private Color color_defecto = EditorPlanetProgramming.cDefectoTexto;
    private Color color_caracter = EditorPlanetProgramming.cCaracteres;
    private Color color_comentario = EditorPlanetProgramming.cComentariosSimples;
    private Color color_entidad_de_referencia = EditorPlanetProgramming.cEntidadDeReferencia;
    private Color color_error = EditorPlanetProgramming.cError;
    private Color color_campo = EditorPlanetProgramming.cCampo;
    private Color color_identificador = EditorPlanetProgramming.cIdentificador;
    private Color color_palabra_clave = EditorPlanetProgramming.cPalabrasReservadas;
    private Color color_atributo_de_marcado = EditorPlanetProgramming.cAtributoDeMarcado;
    private Color color_valor_del_atributo_marcado = EditorPlanetProgramming.cValorDelAtributoMarcado;
    private Color color_elemento = EditorPlanetProgramming.cElemento;
    private Color color_marcad_etiqueta = EditorPlanetProgramming.cMarcaeEtiqueta;
    private Color color_metodo = EditorPlanetProgramming.cMetodo;
    private Color color_numero = EditorPlanetProgramming.cNumeros;
    private Color color_cadena = EditorPlanetProgramming.cCadenas;
    private Color color_operador = EditorPlanetProgramming.cOperadoresAritmeticos;
    private Color color_separador = EditorPlanetProgramming.cSeparador;
    private Color color_URL = EditorPlanetProgramming.cURL;
    private Color color_precaucion = EditorPlanetProgramming.cAdvertencia;
    private Color color_espacio_vacio = EditorPlanetProgramming.cEspacioVacio;

    /**
     * Creates new form Fuente
     * @param parent designa a quien esta heredando la ventana
     * @param modal es un booleano que dice que si se mueve el cursor a otra parte este tendra que cerrarse esta ventana para que se pueda ver otra parte
     */
    public VtnConfiguracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Image icono = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/planetUrano.png"));
        this.setIconImage(icono);
        setLocationRelativeTo(null);
        setTitle("Configuración estilo");

        setResizable(false);
        TabbedPaneUI blackTabbedPaneUI = new TabbedPaneUI();
        tabbedPanePreview.setUI(blackTabbedPaneUI);
        jTextPane2 = new JTextPane();
        scrollPane2 = new JScrollPane();
        jTextPane2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextPane2.setSelectionColor(new java.awt.Color(153, 204, 255));
        scrollPane2.setViewportView(jTextPane2);
        tabbedPanePreview.add("Texto de muestra ", scrollPane2);
        NumeroLinea numLineEditor = new NumeroLinea(jTextPane2);
        scrollPane2.setRowHeaderView(numLineEditor);
        jTextPane2.setText("Hola, No soy un robot");

    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public int getDimension() {
        return dimension;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getEstilo() {
        return estilo;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    public int getDefecto() {
        return defecto;
    }

    public void setDefecto(int defecto) {
        this.defecto = defecto;
    }

    public int getCaracter() {
        return caracter;
    }

    public void setCaracter(int caracter) {
        this.caracter = caracter;
    }

    public int getComentario() {
        return comentario;
    }

    public void setComentario(int comentario) {
        this.comentario = comentario;
    }

    public int getEntidad_de_referencia() {
        return entidad_de_referencia;
    }

    public void setEntidad_de_referencia(int entidad_de_referencia) {
        this.entidad_de_referencia = entidad_de_referencia;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getCampo() {
        return campo;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getPalabra_clave() {
        return palabra_clave;
    }

    public void setPalabra_clave(int palabra_clave) {
        this.palabra_clave = palabra_clave;
    }

    public int getAtributo_de_marcado() {
        return atributo_de_marcado;
    }

    public void setAtributo_de_marcado(int atributo_de_marcado) {
        this.atributo_de_marcado = atributo_de_marcado;
    }

    public int getValor_del_atributo_marcado() {
        return valor_del_atributo_marcado;
    }

    public void setValor_del_atributo_marcado(int valor_del_atributo_marcado) {
        this.valor_del_atributo_marcado = valor_del_atributo_marcado;
    }

    public int getElemento_marcado_Etiqueta() {
        return elemento_marcado_Etiqueta;
    }

    public void setElemento_marcado_Etiqueta(int elemento_marcado_Etiqueta) {
        this.elemento_marcado_Etiqueta = elemento_marcado_Etiqueta;
    }

    public int getMetodo() {
        return metodo;
    }

    public void setMetodo(int metodo) {
        this.metodo = metodo;
    }

    public int getCadena() {
        return cadena;
    }

    public void setCadena(int cadena) {
        this.cadena = cadena;
    }

    public int getOperador() {
        return operador;
    }

    public void setOperador(int operador) {
        this.operador = operador;
    }

    public int getSeparador() {
        return separador;
    }

    public void setSeparador(int separador) {
        this.separador = separador;
    }

    public int getURL() {
        return URL;
    }

    public void setURL(int URL) {
        this.URL = URL;
    }

    public int getEspacio_vacio() {
        return espacio_vacio;
    }

    public void setEspacio_vacio(int espacio_vacio) {
        this.espacio_vacio = espacio_vacio;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

    public Color getColor_defecto() {
        return color_defecto;
    }

    public void setColor_defecto(Color color_defecto) {
        this.color_defecto = color_defecto;
    }

    public Color getColor_caracter() {
        return color_caracter;
    }

    public void setColor_caracter(Color color_caracter) {
        this.color_caracter = color_caracter;
    }

    public Color getColor_comentario() {
        return color_comentario;
    }

    public void setColor_comentario(Color color_comentario) {
        this.color_comentario = color_comentario;
    }

    public Color getColor_entidad_de_referencia() {
        return color_entidad_de_referencia;
    }

    public void setColor_entidad_de_referencia(Color color_entidad_de_referencia) {
        this.color_entidad_de_referencia = color_entidad_de_referencia;
    }

    public Color getColor_error() {
        return color_error;
    }

    public void setColor_error(Color color_error) {
        this.color_error = color_error;
    }

    public Color getColor_campo() {
        return color_campo;
    }

    public void setColor_campo(Color color_campo) {
        this.color_campo = color_campo;
    }

    public Color getColor_identificador() {
        return color_identificador;
    }

    public void setColor_identificador(Color color_identificador) {
        this.color_identificador = color_identificador;
    }

    public Color getColor_palabra_clave() {
        return color_palabra_clave;
    }

    public void setColor_palabra_clave(Color color_palabra_clave) {
        this.color_palabra_clave = color_palabra_clave;
    }

    public Color getColor_atributo_de_marcado() {
        return color_atributo_de_marcado;
    }

    public void setColor_atributo_de_marcado(Color color_atributo_de_marcado) {
        this.color_atributo_de_marcado = color_atributo_de_marcado;
    }

    public Color getColor_valor_del_atributo_marcado() {
        return color_valor_del_atributo_marcado;
    }

    public void setColor_valor_del_atributo_marcado(Color color_valor_del_atributo_marcado) {
        this.color_valor_del_atributo_marcado = color_valor_del_atributo_marcado;
    }

    public Color getColor_elemento() {
        return color_elemento;
    }

    public void setColor_elemento(Color color_elemento) {
        this.color_elemento = color_elemento;
    }

    public Color getColor_marcad_etiqueta() {
        return color_marcad_etiqueta;
    }

    public void setColor_marcad_etiqueta(Color color_marcad_etiqueta) {
        this.color_marcad_etiqueta = color_marcad_etiqueta;
    }

    public Color getColor_metodo() {
        return color_metodo;
    }

    public void setColor_metodo(Color color_metodo) {
        this.color_metodo = color_metodo;
    }

    public Color getColor_numero() {
        return color_numero;
    }

    public void setColor_numero(Color color_numero) {
        this.color_numero = color_numero;
    }

    public Color getColor_cadena() {
        return color_cadena;
    }

    public void setColor_cadena(Color color_cadena) {
        this.color_cadena = color_cadena;
    }

    public Color getColor_operador() {
        return color_operador;
    }

    public void setColor_operador(Color color_operador) {
        this.color_operador = color_operador;
    }

    public Color getColor_separador() {
        return color_separador;
    }

    public void setColor_separador(Color color_separador) {
        this.color_separador = color_separador;
    }

    public Color getColor_URL() {
        return color_URL;
    }

    public void setColor_URL(Color color_URL) {
        this.color_URL = color_URL;
    }

    public int getPrecaucion() {
        return precaucion;
    }

    public void setPrecaucion(int precaucion) {
        this.precaucion = precaucion;
    }

    public Color getColor_precaucion() {
        return color_precaucion;
    }

    public void setColor_precaucion(Color color_precaucion) {
        this.color_precaucion = color_precaucion;
    }

    public Color getColor_espacio_vacio() {
        return color_espacio_vacio;
    }

    public void setColor_espacio_vacio(Color color_espacio_vacio) {
        this.color_espacio_vacio = color_espacio_vacio;
    }

    public JTextField getTxtFuente() {
        return txtFuente;
    }

    public void setTxtFuente(JTextField txtFuente) {
        this.txtFuente = txtFuente;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        pnlColores = new javax.swing.JPanel();
        panelFondo = new javax.swing.JPanel();
        panelTexto = new javax.swing.JPanel();
        lblColorTexto = new javax.swing.JLabel();
        lblColorFondo = new javax.swing.JLabel();
        lblFuente = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtFuente = new javax.swing.JTextField();
        btnRestablecer = new javax.swing.JButton();
        scrollPaneCategoria = new javax.swing.JScrollPane();
        lstCategoria = new javax.swing.JList<>();
        lblCategoria = new javax.swing.JLabel();
        tabbedPanePreview = new javax.swing.JTabbedPane();
        lblPreview = new javax.swing.JLabel();
        btnFonts = new javax.swing.JButton();
        lblLenguaje = new javax.swing.JLabel();
        comboBoxLenguajes = new javax.swing.JComboBox<>();
        pnlEncabezado = new org.edisoncor.gui.panel.Panel();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelContenedor.setBackground(new java.awt.Color(255, 255, 255));
        panelContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlColores.setBackground(new java.awt.Color(255, 255, 255));
        pnlColores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Color", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        panelFondo.setBackground(new java.awt.Color(255, 255, 255));
        panelFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFondoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFondoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelTexto.setBackground(new java.awt.Color(0, 0, 0));
        panelTexto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelTexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelTextoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelTextoLayout = new javax.swing.GroupLayout(panelTexto);
        panelTexto.setLayout(panelTextoLayout);
        panelTextoLayout.setHorizontalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelTextoLayout.setVerticalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblColorTexto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblColorTexto.setText("Color de Texto");

        lblColorFondo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblColorFondo.setText("Color de Fondo");

        javax.swing.GroupLayout pnlColoresLayout = new javax.swing.GroupLayout(pnlColores);
        pnlColores.setLayout(pnlColoresLayout);
        pnlColoresLayout.setHorizontalGroup(
            pnlColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColoresLayout.createSequentialGroup()
                        .addComponent(lblColorFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColoresLayout.createSequentialGroup()
                        .addComponent(lblColorTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        pnlColoresLayout.setVerticalGroup(
            pnlColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColoresLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblColorTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblColorFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelContenedor.add(pnlColores, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 270, 100));

        lblFuente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFuente.setText("Fuente:");
        panelContenedor.add(lblFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 60, 20));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        panelContenedor.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 510, 90, 30));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        panelContenedor.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, 90, 30));

        txtFuente.setEditable(false);
        txtFuente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtFuente.setFocusable(false);
        txtFuente.setRequestFocusEnabled(false);
        panelContenedor.add(txtFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 160, 20));

        btnRestablecer.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRestablecer.setText("Restablecer");
        btnRestablecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerActionPerformed(evt);
            }
        });
        panelContenedor.add(btnRestablecer, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 120, 30));

        lstCategoria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstCategoria.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Defecto", "Caracter", "Comentario", "Entidad de referencia", "Error", "Campo", "Identificador", "Palabra clave", "Atributo de marcado", "Valor del atributo marcado", "Elemento marcado (Etiqueta)", "Método", "Número", "Cadena", "Operador", "Separador", "URL", "Adevertencia", "Espacio vacio" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstCategoria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCategoria.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCategoriaValueChanged(evt);
            }
        });
        scrollPaneCategoria.setViewportView(lstCategoria);

        panelContenedor.add(scrollPaneCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 320, 140));

        lblCategoria.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCategoria.setText("Categoria:");
        panelContenedor.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 130, -1));
        panelContenedor.add(tabbedPanePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 630, 170));

        lblPreview.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblPreview.setText("Vista previa:");
        panelContenedor.add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        btnFonts.setText("...");
        btnFonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFontsActionPerformed(evt);
            }
        });
        panelContenedor.add(btnFonts, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 30, 20));

        lblLenguaje.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblLenguaje.setText("Lenguaje:");
        panelContenedor.add(lblLenguaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        comboBoxLenguajes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        comboBoxLenguajes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Assembly" }));
        panelContenedor.add(comboBoxLenguajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 210, -1));

        pnlEncabezado.setColorPrimario(new java.awt.Color(0, 102, 102));
        pnlEncabezado.setColorSecundario(new java.awt.Color(0, 102, 102));

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Configuración de estilo");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelContenedor.add(pnlEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelFondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFondoMouseClicked

    }//GEN-LAST:event_panelFondoMouseClicked

    private void panelFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFondoMousePressed
        colorFondo = JColorChooser.showDialog(this, "Selecciona un color", Color.WHITE);
        if (colorFondo != null) {
            panelFondo.setBackground(colorFondo);
            jTextPane2.setBackground(colorFondo);
        }
    }//GEN-LAST:event_panelFondoMousePressed

    private void panelTextoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTextoMousePressed
        colorTexto = JColorChooser.showDialog(this, "Selecciona un color", Color.BLACK);
        if (colorTexto != null) {
            panelTexto.setBackground(colorTexto);
            jTextPane2.setForeground(colorTexto);
            Color c = null;
            if (lstCategoria.getSelectedValue().equals("Defecto")) {
                setColor_defecto(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Caracter")) {
                setColor_caracter(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Comentario")) {
                setColor_comentario(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Entidad de referencia")) {
                setColor_entidad_de_referencia(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Error")) {
                setColor_error(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Campo")) {
                setColor_campo(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Identificador")) {
                setColor_identificador(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Palabra clave")) {
                setColor_palabra_clave(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Atributo de marcado")) {
                setColor_atributo_de_marcado(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Valor del atributo marcado")) {
                setColor_valor_del_atributo_marcado(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Elemento marcado (Etiqueta)")) {
                setColor_elemento(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Método")) {
                setColor_metodo(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Número")) {
                setColor_numero(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Cadena")) {
                setColor_cadena(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Operador")) {
                setColor_operador(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Separador")) {
                setColor_separador(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("URL")) {
                setColor_URL(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Adevertencia")) {
                setColor_precaucion(colorTexto);
            } else if (lstCategoria.getSelectedValue().equals("Espacio vacio")) {
                setColor_espacio_vacio(colorTexto);
            } else {

            }

        }
    }//GEN-LAST:event_panelTextoMousePressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtFuente.setText(fuente + " " + dimension);
        lstCategoria.setSelectedIndex(0);
    }//GEN-LAST:event_formWindowOpened

    private void btnFontsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFontsActionPerformed
        VtnFuente fE = new VtnFuente(null, true);
        fE.setVtnConfig(this);
        fE.setFuente(getFuente());
        fE.setDimension(getDimension());
        fE.setEstilo(getEstilo());
        fE.setVisible(true);
    }//GEN-LAST:event_btnFontsActionPerformed

    private void btnRestablecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerActionPerformed
        setFuente(EditorPlanetProgramming.fontEditor);
        setDimension(EditorPlanetProgramming.tamanio);
        setEstilo(EditorPlanetProgramming.estilo);
        setDefecto(EditorPlanetProgramming.defecto);
        setCaracter(EditorPlanetProgramming.caracter);
        setComentario(EditorPlanetProgramming.comentario);
        setEntidad_de_referencia(EditorPlanetProgramming.entidad_de_referencia);
        setError(EditorPlanetProgramming.error);
        setCampo(EditorPlanetProgramming.campo);
        setIdentificador(EditorPlanetProgramming.identificador);
        setPalabra_clave(EditorPlanetProgramming.palabra_clave);
        setAtributo_de_marcado(EditorPlanetProgramming.atributo_de_marcado);
        setValor_del_atributo_marcado(EditorPlanetProgramming.valor_del_atributo_marcado);
        setElemento_marcado_Etiqueta(EditorPlanetProgramming.elemento_marcado_Etiqueta);
        setMetodo(EditorPlanetProgramming.metodo);
        setNumero(EditorPlanetProgramming.numero);
        setCadena(EditorPlanetProgramming.cadena);
        setOperador(EditorPlanetProgramming.operador);
        setSeparador(EditorPlanetProgramming.separador);
        setURL(EditorPlanetProgramming.URL);
        setPrecaucion(EditorPlanetProgramming.precaución);
        setEspacio_vacio(EditorPlanetProgramming.espacio_vacio);

        setColorFondo(EditorPlanetProgramming.cFondo);
        setColorTexto(EditorPlanetProgramming.cCadenas);
        setColor_defecto(EditorPlanetProgramming.cDefectoTexto);
        setColor_caracter(EditorPlanetProgramming.cCaracteres);
        setColor_comentario(EditorPlanetProgramming.cComentariosSimples);
        setColor_entidad_de_referencia(EditorPlanetProgramming.cEntidadDeReferencia);
        setColor_error(EditorPlanetProgramming.cError);
        setColor_campo(EditorPlanetProgramming.cCampo);
        setColor_identificador(EditorPlanetProgramming.cIdentificador);
        setColor_palabra_clave(EditorPlanetProgramming.cPalabrasReservadas);
        setColor_atributo_de_marcado(EditorPlanetProgramming.cAtributoDeMarcado);
        setColor_valor_del_atributo_marcado(EditorPlanetProgramming.cValorDelAtributoMarcado);
        setColor_elemento(EditorPlanetProgramming.cElemento);
        setColor_marcad_etiqueta(EditorPlanetProgramming.cMarcaeEtiqueta);
        setColor_metodo(EditorPlanetProgramming.cMetodo);
        setColor_numero(EditorPlanetProgramming.cNumeros);
        setColor_cadena(EditorPlanetProgramming.cCadenas);
        setColor_operador(EditorPlanetProgramming.cOperadoresAritmeticos);
        setColor_separador(EditorPlanetProgramming.cSeparador);
        setColor_URL(EditorPlanetProgramming.cURL);
        setColor_precaucion(EditorPlanetProgramming.cAdvertencia);
        setColor_espacio_vacio(EditorPlanetProgramming.cEspacioVacio);
        this.dispose();
    }//GEN-LAST:event_btnRestablecerActionPerformed

    private void lstCategoriaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCategoriaValueChanged
        panelFondo.setBackground(getColorFondo());
        Color c = null;
        if (lstCategoria.getSelectedValue().equals("Defecto")) {
            c = getColor_defecto();
        } else if (lstCategoria.getSelectedValue().equals("Caracter")) {
            c = getColor_caracter();
        } else if (lstCategoria.getSelectedValue().equals("Comentario")) {
            c = getColor_comentario();
        } else if (lstCategoria.getSelectedValue().equals("Entidad de referencia")) {
            c = getColor_entidad_de_referencia();
        } else if (lstCategoria.getSelectedValue().equals("Error")) {
            c = getColor_error();
        } else if (lstCategoria.getSelectedValue().equals("Campo")) {
            c = getColor_campo();
        } else if (lstCategoria.getSelectedValue().equals("Identificador")) {
            c = getColor_identificador();
        } else if (lstCategoria.getSelectedValue().equals("Palabra clave")) {
            c = getColor_palabra_clave();
        } else if (lstCategoria.getSelectedValue().equals("Atributo de marcado")) {
            c = getColor_atributo_de_marcado();
        } else if (lstCategoria.getSelectedValue().equals("Valor del atributo marcado")) {
            c = getColor_valor_del_atributo_marcado();
        } else if (lstCategoria.getSelectedValue().equals("Elemento marcado (Etiqueta)")) {
            c = getColor_elemento();
        } else if (lstCategoria.getSelectedValue().equals("Método")) {
            c = getColor_metodo();
        } else if (lstCategoria.getSelectedValue().equals("Número")) {
            c = getColor_numero();
        } else if (lstCategoria.getSelectedValue().equals("Cadena")) {
            c = getColor_cadena();
        } else if (lstCategoria.getSelectedValue().equals("Operador")) {
            c = getColor_operador();
        } else if (lstCategoria.getSelectedValue().equals("Separador")) {
            c = getColor_separador();
        } else if (lstCategoria.getSelectedValue().equals("URL")) {
            c = getColor_URL();
        } else if (lstCategoria.getSelectedValue().equals("Adevertencia")) {
            c = getColor_precaucion();
        } else if (lstCategoria.getSelectedValue().equals("Espacio vacio")) {
            c = getColor_espacio_vacio();
        } else {

        }
        panelTexto.setBackground(c);

    }//GEN-LAST:event_lstCategoriaValueChanged

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VtnConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VtnConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VtnConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VtnConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VtnConfiguracion dialog = new VtnConfiguracion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JScrollPane scrollPane2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFonts;
    private javax.swing.JButton btnRestablecer;
    private javax.swing.JComboBox<String> comboBoxLenguajes;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblColorFondo;
    private javax.swing.JLabel lblColorTexto;
    private javax.swing.JLabel lblFuente;
    private javax.swing.JLabel lblLenguaje;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> lstCategoria;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JPanel panelTexto;
    private javax.swing.JPanel pnlColores;
    private org.edisoncor.gui.panel.Panel pnlEncabezado;
    private javax.swing.JScrollPane scrollPaneCategoria;
    private javax.swing.JTabbedPane tabbedPanePreview;
    private javax.swing.JTextField txtFuente;
    // End of variables declaration//GEN-END:variables
}
