package proyectofinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class frmCrearTarea extends javax.swing.JFrame {
    ArrayList<Lista> listas = new ArrayList<>();
    ArrayList<Tarea> listaTareas = new ArrayList<>();
    Tarea Seleccionado = null;

    
    public frmCrearTarea() {
        initComponents();
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        ObtenerListas();
        
        Object [][] datos = new Object[][]{};
        Object [] columnas = new String [] {"ID", "Descripcion", "Importante", "Completa", "Fecha Vencimiento", "ListaID", "Lista"};
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvTareas.setModel(model);
    }
    
    void ObtenerListas()
    {
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
                    listas.add(l);
                }                
            }            
        } catch (IOException ex) {
            Logger.getLogger(frmCrearListas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Lista BuscarListaPorId(int id)
    {
        for (int i = 0; i < listas.size(); i++) {
            if(listas.get(i).listaId == id)
            {
                return listas.get(i);
            }
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras buscabamos las listas!");
        return new Lista();     
    }
    
    void ActualizarTabla()
    {
        Object [][] datos = new Object[listaTareas.size()][7];
        Object [] columnas = new String[] {"ID", "Descripcion", "Importante", "Completa", "Fecha Vencimiento", "ListaID", "Lista"};
        
        for (int i = 0; i < listaTareas.size(); i++) {
            datos[i][0] = i;
            datos[i][1] = listaTareas.get(i).Descripcion;
            datos[i][2] = listaTareas.get(i).EsImportante;
            datos[i][3] = listaTareas.get(i).Completa;
            datos[i][4] = listaTareas.get(i).FechaVencimiento;
            datos[i][5] = listaTareas.get(i).ListaId;
            
            Object listaId = listaTareas.get(i).ListaId;
            Lista listaAsociada = BuscarListaPorId((int)listaId);                        
            datos[i][6] = listaAsociada.Nombre;
        }
        
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        dgvTareas.setModel(model);        
    }
    
    void ActualizarIds()
    {  
        for (int i = 0; i < listaTareas.size(); i++) {
            listaTareas.get(i).tareaId = i;
        }
    }
    
    void GuardarTareas()
    {      
        //Variables              
        BufferedWriter bwA = null;
        FileWriter fwA = null;        
        
        //Creamos y validamos si existe el archivo
        try {  
        File fileA = new File("Tareas.txt");
        if (!fileA.exists()) {
            fileA.createNewFile();
        }
        
   
        fwA = new FileWriter(fileA.getAbsoluteFile(), false);
        bwA = new BufferedWriter(fwA);

            for (int i = 0; i < listaTareas.size(); i++) {
                bwA.write(i + "|");
                bwA.write(listaTareas.get(i).Descripcion + "|");
                bwA.write(listaTareas.get(i).EsImportante + "|");
                bwA.write(listaTareas.get(i).Completa + "|");
                bwA.write(listaTareas.get(i).FechaVencimiento + "|");
                bwA.write(listaTareas.get(i).ListaId + "|");
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
    
    Tarea BuscarPorId(int id)
    {     
        ActualizarTabla();
        for (int i = 0; i < listaTareas.size(); i++) {
            if(listaTareas.get(i).tareaId == id)
            {
                return listaTareas.get(i);
            }       
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras buscabamos este registro!");
        return new Tarea();     
    }
    
    void EliminarPorId()
    {        
        if(Seleccionado == null)
        {      
            JOptionPane.showMessageDialog(null, "¡NO hay un registro seleccionado!"); 
            return;
        }       

        for (int i = 0; i < listaTareas.size(); i++) {
            if(listaTareas.get(i).tareaId == Seleccionado.tareaId)
            {
                listaTareas.remove(listaTareas.get(i).tareaId);
                JOptionPane.showMessageDialog(null, "¡Registro eliminado con exito!"); 
                ActualizarIds();
                ActualizarTabla();
                GuardarTareas();
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
        
        if(!ValidarFormulario())
        { 
            return;
        }
        
        for (int i = 0; i < listaTareas.size(); i++) {
            if(listaTareas.get(i).tareaId == Seleccionado.tareaId)
            {
                listaTareas.get(i).setDescripcion(txtDescripcionTarea.getText());
                listaTareas.get(i).setEsImportante(cbImportante.isSelected());
                listaTareas.get(i).setCompleta(cbCompletada.isSelected());
                listaTareas.get(i).setFechaVencimiento(jdFechaVencimiento.getDate());
                
                JOptionPane.showMessageDialog(null, "¡Registro editado con exito!");
                ActualizarIds();
                ActualizarTabla();
                GuardarTareas();
                Seleccionado = null;
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Ha ocurrido un error mientras editabamos este registro!");            
    }
    
    boolean ValidarFormulario()
    {
        if("".equals(txtDescripcionTarea.getText()))
        {
            JOptionPane.showMessageDialog(null, "¡el campo descripcion esta vacio!"); 
            return false;
        }
        
        if("".equals(cmbListas.getSelectedItem().toString()))
        {
            JOptionPane.showMessageDialog(null, "¡el campo nombre esta vacio!"); 
            return false;
        }
        
        if("".equals(jdFechaVencimiento.getCalendar()))
        {
            JOptionPane.showMessageDialog(null, "¡el campo Fecha esta vacio!"); 
            return false;
        }
        
        return true;
    }
    
    void VaciarFormulario()
    {
        txtDescripcionTarea.setText("");
        cbCompletada.setSelected(false);
        cbImportante.setSelected(false);
        cbParaHoy.setSelected(false); 
        jdFechaVencimiento.setEnabled(true);
    }
    
    void LlenarComboBox()
    {
        cmbListas.removeAllItems();        
        listas.forEach((Lista lista) -> {
            cmbListas.addItem(lista.Nombre);
        });
        
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
        jPanel2 = new javax.swing.JPanel();
        btnEditarTarea = new javax.swing.JButton();
        btnAgregarTarea = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbCompletada = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        cbImportante = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jdFechaVencimiento = new com.toedter.calendar.JDateChooser();
        cmbListas = new javax.swing.JComboBox<>();
        txtDescripcionTarea = new javax.swing.JTextField();
        cbParaHoy = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgvTareas = new javax.swing.JTable();
        btnSalirTarea = new javax.swing.JButton();
        btnEliminarTarea = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("CREAR TAREAS");
        kGradientPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 310, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        btnEditarTarea.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarTarea.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        btnEditarTarea.setText("Editar");
        btnEditarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTareaActionPerformed(evt);
            }
        });

        btnAgregarTarea.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarTarea.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        btnAgregarTarea.setText("Agregar");
        btnAgregarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarTarea, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarTarea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarTarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(btnAgregarTarea)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("Escribe tu Tarea");

        cbCompletada.setText("Completada");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Lista a la que Pertenece");

        cbImportante.setText("Importante");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Fecha de Vencimiento");

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbParaHoy.setText("¿Para hoy?");
        cbParaHoy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbParaHoyMouseClicked(evt);
            }
        });
        cbParaHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParaHoyActionPerformed(evt);
            }
        });

        dgvTareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        dgvTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvTareasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dgvTareas);

        btnSalirTarea.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        btnSalirTarea.setText("Salir");
        btnSalirTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirTareaActionPerformed(evt);
            }
        });

        btnEliminarTarea.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        btnEliminarTarea.setText("Eliminar");
        btnEliminarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnEliminarTarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalirTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescripcionTarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCompletada))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(cbImportante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbParaHoy)
                .addGap(131, 131, 131))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcionTarea))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbParaHoy)
                    .addComponent(cbImportante))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(cbCompletada)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalirTarea)
                    .addComponent(btnEliminarTarea))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTareaActionPerformed
        if(!ValidarFormulario())
        {        
        }
        else
        {
            Date Fecha = jdFechaVencimiento.getDate();            
            if(cbParaHoy.isSelected())
            {
                Fecha = new Date();
            }
            
            listaTareas.add(new Tarea(txtDescripcionTarea.getText(), cbImportante.isSelected(), cbCompletada.isSelected(), Fecha, cmbListas.getSelectedIndex()));
            
            VaciarFormulario();
            ActualizarIds();
            ActualizarTabla();  
            GuardarTareas();
        }
    }//GEN-LAST:event_btnAgregarTareaActionPerformed

    private void btnSalirTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirTareaActionPerformed
        VaciarFormulario();
        this.dispose();
        ActualizarTabla();
        GuardarTareas();
    }//GEN-LAST:event_btnSalirTareaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LlenarComboBox();
        try {
            File file = new File("Tareas.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader("Tareas.txt"));
            String texto;
            while((texto = br.readLine()) != null)
            {
                String [] Campos = texto.split("[|\n]");
                if(Campos.length > 0)
                {
                    //{"ID", "Descripcion", "Importante", "Completa", "Fecha Vencimiento", "ListaID", "Lista"};
                    Tarea t = new Tarea();
                    t.setTareaId(Integer.parseInt(Campos[0]));
                    t.setDescripcion(Campos[1]);
                    
                    boolean importante = false; 
                    if(Campos[2].equals("true"))
                    {
                        importante = true;
                    }
                    t.setEsImportante(importante);
                    
                    boolean completa = false;
                    if(Campos[3].equals("true"))
                    {
                        completa = true;
                    }
                    t.setCompleta(completa);
                    
                    String Fecha = Campos[4].toString();
                    Date fechaV = new Date(Fecha);
                    t.setFechaVencimiento(fechaV);
                    
                    t.setListaId(Integer.parseInt(Campos[5]));      
                    listaTareas.add(t);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(frmCrearListas.class.getName()).log(Level.SEVERE, null, ex);
        }
        ActualizarTabla();
    }//GEN-LAST:event_formWindowOpened

    private void btnEliminarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTareaActionPerformed
        EliminarPorId();
        VaciarFormulario();
    }//GEN-LAST:event_btnEliminarTareaActionPerformed

    private void btnEditarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTareaActionPerformed
        ActualizarPorId();
        VaciarFormulario();
    }//GEN-LAST:event_btnEditarTareaActionPerformed

    private void dgvTareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvTareasMouseClicked
        JTable target = (JTable)evt.getSource();
       int row = target.getSelectedRow(); 
       int column = target.getSelectedColumn(); 
       Object id = dgvTareas.getValueAt(row, 0);
       Seleccionado = BuscarPorId((int) id);
       
       txtDescripcionTarea.setText(Seleccionado.Descripcion);
       cmbListas.setSelectedIndex(Seleccionado.ListaId);
       jdFechaVencimiento.setDate(Seleccionado.FechaVencimiento);
       cbImportante.setSelected(Seleccionado.EsImportante);
       cbCompletada.setSelected(Seleccionado.Completa);    
       
       
       Date fechaHoy = new Date();       
       Date fechaVencimiento = jdFechaVencimiento.getDate();
       
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
       String tFechaHoy = formatter.format(fechaHoy);
       String tfechaVencimiento = formatter.format(fechaVencimiento);
       
       if(tFechaHoy.equals(tfechaVencimiento))
       {
           cbParaHoy.setSelected(true);
       }
       else
       {
           cbParaHoy.setSelected(false);
       }
        
    }//GEN-LAST:event_dgvTareasMouseClicked

    private void cbParaHoyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbParaHoyMouseClicked
        
    }//GEN-LAST:event_cbParaHoyMouseClicked

    private void cbParaHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbParaHoyActionPerformed
        if(cbParaHoy.isSelected())
        {
            jdFechaVencimiento.setEnabled(false);
        }
        else
        {
            jdFechaVencimiento.setEnabled(true);
        }
    }//GEN-LAST:event_cbParaHoyActionPerformed

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
            java.util.logging.Logger.getLogger(frmCrearTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCrearTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCrearTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCrearTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCrearTarea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarTarea;
    private javax.swing.JButton btnEditarTarea;
    private javax.swing.JButton btnEliminarTarea;
    private javax.swing.JButton btnSalirTarea;
    private javax.swing.JCheckBox cbCompletada;
    private javax.swing.JCheckBox cbImportante;
    private javax.swing.JCheckBox cbParaHoy;
    private javax.swing.JComboBox<String> cmbListas;
    private javax.swing.JTable dgvTareas;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdFechaVencimiento;
    private keeptoo.KGradientPanel kGradientPanel2;
    private javax.swing.JTextField txtDescripcionTarea;
    // End of variables declaration//GEN-END:variables
}
