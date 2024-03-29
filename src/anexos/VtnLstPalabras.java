
package anexos;

import clases.ManipulaArchivos;
import java.util.ArrayList;
import javax.swing.JPanel;
import planetprogrammig.EditorPlanetProgramming;
import recursos.Validaciones;

public class VtnLstPalabras extends javax.swing.JFrame {

    EditorPlanetProgramming ePP;
    String texto="";

    public VtnLstPalabras() {
        this.initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Separador de palabras");
    }

    public EditorPlanetProgramming getePP() {
        return ePP;
    }

    public void setePP(EditorPlanetProgramming ePP) {
        this.ePP = ePP;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public JPanel getPnlContenedor() {
        return pnlContenedor;
    }

    public void setPnlContenedor(JPanel pnlContenedor) {
        this.pnlContenedor = pnlContenedor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        scrollPaneContenedor = new javax.swing.JScrollPane();
        pnlContenedor = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setLayout(new java.awt.GridLayout(0, 1, 2, 2));
        scrollPaneContenedor.setViewportView(pnlContenedor);

        pnlFondo.add(scrollPaneContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 147, 460, 280));

        pnlTitulo.setBackground(new java.awt.Color(0, 102, 102));

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Separación de cadenas");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
        );

        pnlFondo.add(pnlTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar separador");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        pnlFondo.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 109, 184, 32));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        pnlFondo.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 432, 91, 30));

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlFondo.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 432, 106, 30));

        jTextPane1.setBackground(new java.awt.Color(255, 153, 153));
        jScrollPane1.setViewportView(jTextPane1);

        pnlFondo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 200, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        ArrayList<PnlPalabra> p = new ArrayList<>();
        
        if (getPnlContenedor().getComponentCount() != 0) {
            for (int j = 0; j < pnlContenedor.getComponentCount(); j++) {
                p.add((PnlPalabra) pnlContenedor.getComponent(j));
            }
        }
        char separador[]=new char[p.size()];
        
         for (int j = 0; j < p.size(); j++) {
             separador[j]=p.get(j).getTxtCarcacter().getText().charAt(0);
        }
        ArrayList<String> palabras=new ArrayList<>();
        
      //  palabras=Validaciones.verificaPalabra(ePP.getTxtPanelCompilando(),getTexto(),separador);
      //  ManipulaArchivos.guardar(palabras, ePP.rutaArchivo.substring(0, ePP.rutaArchivo.length()-4)+"Analisis.txt");
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        PnlPalabra pL = new PnlPalabra();
        pL.setVtnLista(this);
        pnlContenedor.add(pL);
        pnlContenedor.updateUI();
    }//GEN-LAST:event_btnAgregarActionPerformed

  public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VtnLstPalabras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JScrollPane scrollPaneContenedor;
    // End of variables declaration//GEN-END:variables
}
