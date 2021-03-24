package proyectofinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class frmCrearListas extends javax.swing.JFrame {
    ArrayList<Lista> lista = new ArrayList<>();
    Lista Seleccionado = null;    
    
    
    public frmCrearListas() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        Object [][] datos = new Object[][]{};
        Object [] columnas = new String [] {"ID", "Descripcion"};
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvListas.setModel(model);       
    }
    
    void ActualizarTabla()
    {
        Object [][] datos = new Object[lista.size()][2];
        Object [] columnas = new String[] {"ID", "Descripcion"};
        
        for (int i = 0; i < lista.size(); i++) {
            datos[i][0] = i;
            datos[i][1] = lista.get(i).Nombre;
        }
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvListas.setModel(model);        
    }
    
    void ActualizarIds()
    {  
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).listaId = i;
        }
    }
    
    void GuardarListas()
    {      
        //Variables              
        BufferedWriter bwA = null;
        FileWriter fwA = null;        
        
        //Creamos y validamos si existe el archivo
        try {  
        File fileA = new File("Listas.txt");
        if (!fileA.exists()) {
            fileA.createNewFile();
        }
        
   
        fwA = new FileWriter(fileA.getAbsoluteFile(), false);
        bwA = new BufferedWriter(fwA);

            for (int i = 0; i < lista.size(); i++) {
                bwA.write(i + "|");
                bwA.write(lista.get(i).Nombre + "|");        
                bwA.newLine();      
            }
        
        } catch (IOException e) {
            
        } finally {
            try {                                
                if (bwA != null)
                  bwA.close();
                if (fwA != null)
                  fwA.close();
        } catch (IOException ex) {
        }
    }            
    }
    
    Lista BuscarPorId(int id)
    {     
        ActualizarTabla();
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).listaId == id)
            {
                return lista.get(i);
            }       
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras buscabamos este registro!");
        return new Lista();     
    }
    
    void EliminarPorId()
    {        
        if(Seleccionado == null)
        {      
            JOptionPane.showMessageDialog(null, "¡NO hay un registro seleccionado!"); 
            return;
        }       

        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).listaId == Seleccionado.listaId)
            {
                lista.remove(lista.get(i).listaId);
                JOptionPane.showMessageDialog(null, "¡Registro eliminado con exito!"); 
                ActualizarIds();
                ActualizarTabla();
                GuardarListas();
                Seleccionado = null;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras eliminabamos este registro!");            
    }
    
    void ActualizarPorId()
    {  
        if(Seleccionado == null)
        {      
            JOptionPane.showMessageDialog(null, "¡NO hay un registro seleccionado!"); 
            return;
        }
        
        if("".equals(txtNombreLista.getText()))
        {
            JOptionPane.showMessageDialog(null, "¡el campo nombre esta vacio!"); 
            return;
        }
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).listaId == Seleccionado.listaId)
            {
                lista.get(i).setNombre(txtNombreLista.getText());
                JOptionPane.showMessageDialog(null, "¡Registro editado con exito!");
                ActualizarIds();
                ActualizarTabla();
                GuardarListas();
                Seleccionado = null;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras editabamos este registro!");            
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtNombreLista = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvListas = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setText("Nombre:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        dgvListas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        dgvListas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvListasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvListas);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear listas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreLista))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAgregar)
                    .addComponent(btnSalir))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtNombreLista.getText().equals("") || txtNombreLista.getText() == null)
        {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre a la lista!!!");
        }
        else
        {
            lista.add(new Lista(txtNombreLista.getText()));
            txtNombreLista.setText("");
            ActualizarIds();
            ActualizarTabla();  
            GuardarListas();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        txtNombreLista.setText("");
        this.dispose();
        ActualizarTabla();
        GuardarListas();        
    }//GEN-LAST:event_btnSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            File file = new File("Listas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader("Listas.txt"));
            String texto;
            while((texto = br.readLine()) != null)
            {
                String [] Campos = texto.split("[|\n]");
                if(Campos.length > 0)
                {
                    Lista l = new Lista();
                    l.setListaId(Integer.parseInt(Campos[0]));
                    l.setNombre(Campos[1]);
                    lista.add(l);
                }                
            }            
        } catch (IOException ex) {
            Logger.getLogger(frmCrearListas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ActualizarTabla();
    }//GEN-LAST:event_formWindowOpened

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ActualizarPorId();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       EliminarPorId();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void dgvListasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvListasMouseClicked
       JTable target = (JTable)evt.getSource();
       int row = target.getSelectedRow(); 
       int column = target.getSelectedColumn(); 
       Object id = dgvListas.getValueAt(row, 0);
       Seleccionado = BuscarPorId((int) id);
       txtNombreLista.setText(Seleccionado.Nombre);
    }//GEN-LAST:event_dgvListasMouseClicked

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
            java.util.logging.Logger.getLogger(frmCrearListas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrearListas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrearListas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrearListas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCrearListas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JTable dgvListas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNombreLista;
    // End of variables declaration//GEN-END:variables

}
