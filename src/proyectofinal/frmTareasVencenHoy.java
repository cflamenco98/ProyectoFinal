package proyectofinal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Formulario que carga la informacion solicitada
public class frmTareasVencenHoy extends javax.swing.JFrame {
    ArrayList<Lista> listas = new ArrayList<>();
    ArrayList<Tarea> listaTareas = new ArrayList<>();


    public frmTareasVencenHoy() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        ObtenerListas();

        Object[][] datos = new Object[][]{};
        Object[] columnas = new String[]{"ID", "Descripcion", "Importante", "Completa", "Fecha Vencimiento", "ListaID", "Lista"};

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvTareasVencenHoy.setModel(model);
    }

    void ObtenerListas() {
        try {
            File file = new File("Listas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader("Listas.txt"));
            String texto;
            while ((texto = br.readLine()) != null) {
                String[] Campos = texto.split("[|\n]");
                if (Campos.length > 0) {
                    Lista l = new Lista();
                    l.setListaId(Integer.parseInt(Campos[0]));
                    l.setNombre(Campos[1]);
                    listas.add(l);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(frmCrearListas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Lista BuscarListaPorId(int id) {
        for (int i = 0; i < listas.size(); i++) {
            if (listas.get(i).listaId == id) {
                return listas.get(i);
            }
        }
        JOptionPane.showMessageDialog(null, "??Ha ocurrido un error mientras buscabamos las listas!");
        return new Lista();
    }

    void ActualizarTabla() {
        Object[][] datos = new Object[listaTareas.size()][7];
        Object[] columnas = new String[]{"ID", "Descripcion", "Importante", "Completa", "Fecha Vencimiento", "ListaID", "Lista"};

        for (int i = 0; i < listaTareas.size(); i++) {
            datos[i][0] = i;
            datos[i][1] = listaTareas.get(i).Descripcion;
            datos[i][2] = listaTareas.get(i).EsImportante;
            datos[i][3] = listaTareas.get(i).Completa;
            datos[i][4] = listaTareas.get(i).FechaVencimiento;
            datos[i][5] = listaTareas.get(i).ListaId;

            Object listaId = listaTareas.get(i).ListaId;
            Lista listaAsociada = BuscarListaPorId((int) listaId);
            datos[i][6] = listaAsociada.Nombre;
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvTareasVencenHoy.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgvTareasVencenHoy = new javax.swing.JTable();
        btnSalir = new keeptoo.KButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(475, 401));

        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("TAREAS QUE VENCEN HOY");
        kGradientPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 310, -1));

        dgvTareasVencenHoy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(dgvTareasVencenHoy);

        btnSalir.setText("SALIR");
        btnSalir.setkEndColor(new java.awt.Color(0, 0, 255));
        btnSalir.setkHoverEndColor(new java.awt.Color(0, 0, 255));
        btnSalir.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnSalir.setkPressedColor(new java.awt.Color(255, 255, 255));
        btnSalir.setkSelectedColor(new java.awt.Color(255, 255, 255));
        btnSalir.setkStartColor(new java.awt.Color(255, 0, 255));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            File file = new File("Tareas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader("Tareas.txt"));
            String texto;
            while ((texto = br.readLine()) != null) {
                String[] Campos = texto.split("[|\n]");
                if (Campos.length > 0) {
                    Tarea t = new Tarea();
                    t.setTareaId(Integer.parseInt(Campos[0]));
                    t.setDescripcion(Campos[1]);

                    boolean importante = false;
                    if (Campos[2].equals("true")) {
                        importante = true;
                    }
                    t.setEsImportante(importante);

                    boolean completa = false;
                    if (Campos[3].equals("true")) {
                        completa = true;
                    }
                    t.setCompleta(completa);

                    String Fecha = Campos[4].toString();
                    Date fechaV = new Date(Fecha);
                    t.setFechaVencimiento(fechaV);

                    t.setListaId(Integer.parseInt(Campos[5]));

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    Date fechaHoy = new Date();
                    Date fechaVencimiento = t.FechaVencimiento;

                    String tFechaHoy = formatter.format(fechaHoy);
                    String tfechaVencimiento = formatter.format(fechaVencimiento);

                    if (tFechaHoy.equals(tfechaVencimiento)) {
                        listaTareas.add(t);
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(frmCrearListas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ActualizarTabla();
    }//GEN-LAST:event_formWindowOpened

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(frmTareasVencenHoy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTareasVencenHoy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTareasVencenHoy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTareasVencenHoy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmTareasVencenHoy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KButton btnSalir;
    private javax.swing.JTable dgvTareasVencenHoy;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel2;
    // End of variables declaration//GEN-END:variables
}
