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
    //Variables globales para almacenar listas y setear el registro seleccionado
    ArrayList<Lista> lista = new ArrayList<>();
    Lista Seleccionado = null;    
    
    
    public frmCrearListas() {
        initComponents();
        //Configuracion general y declaracion del modelo del grid
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
        Object [][] datos = new Object[][]{};
        Object [] columnas = new String [] {"ID", "Descripcion"};
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvListas.setModel(model);       
    }
    
    //Metodo para actualizar tabla
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
    
    //Metodo para actualizar IDs de la tabla
    void ActualizarIds()
    {  
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).listaId = i;
        }
    }
    
    //Metodo para guardar las listas
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
    
    //Metodo para buscar listas por id
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
    
    //Metodo para eliminar listas por id
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
    
    //Metodo para actualizar listas por id
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

        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombreLista = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvListas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CREAR LISTA");
        kGradientPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 310, -1));

        getContentPane().add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

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

        jLabel2.setText("Nombre:");

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreLista, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 480, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Evento cuando abre el formulario que carga las listas al aparecer
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

    //Evento cuando le dan click a un registro del grid cargue un objeto seleccionado por medio de la deteccion de la fila y columna seleccionada
    private void dgvListasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvListasMouseClicked
       JTable target = (JTable)evt.getSource();
       int row = target.getSelectedRow(); 
       int column = target.getSelectedColumn(); 
       Object id = dgvListas.getValueAt(row, 0);
       Seleccionado = BuscarPorId((int) id);
       txtNombreLista.setText(Seleccionado.Nombre);
    }//GEN-LAST:event_dgvListasMouseClicked

    //Evento para agregar
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

    //Evento para editar
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ActualizarPorId();
    }//GEN-LAST:event_btnEditarActionPerformed

    //Evento para eliminar
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarPorId();
    }//GEN-LAST:event_btnEliminarActionPerformed

    //Evento para salir
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        txtNombreLista.setText("");
        this.dispose();
        ActualizarTabla();
        GuardarListas();
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextField txtNombreLista;
    // End of variables declaration//GEN-END:variables

}
