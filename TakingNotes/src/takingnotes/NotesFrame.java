
package takingnotes;

import java.awt.Font;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
public class NotesFrame extends javax.swing.JFrame {
    private final String userName;
    private final String directory;
    private final String notesDetailsPath;
    public  Map<String , String> notes;
    public NotesFrame(String userName , String directory) {
        initComponents();
        this.userName = userName;
        this.directory = directory;
        notesDetailsPath = this.directory + this.userName + "\\" + this.userName + "-notes-details.txt";
        start();
       
    }
    private void start()
    {
        this.setVisible(true);
        
        getNotes();
    }
    public void getNotes()
    {	
    	notes = FileManager.readNotesFile(notesDetailsPath);
        DefaultTableModel tableModel =  (DefaultTableModel) jTable1.getModel();
        tableModel.getDataVector().removeAllElements();
        if(notes.size() == 0)
        {
            DefaultTableModel model = new  DefaultTableModel(null, new String[]{"Note Title"}) {

                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false;
                }
                
            };
            jTable1.setModel(model);
            return;
        }
        for(Map.Entry<String, String> m : notes.entrySet()) 
        {
        	String data[] = {m.getKey().replace(".txt", "")};
        	tableModel.addRow(data);
        }
    }
    public String getUserName()
    {
    	return userName;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addNoteButton = new javax.swing.JButton();
        deleteNoteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Notes");
        setResizable(false);

        addNoteButton.setText("Add note");
        addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoteButtonActionPerformed(evt);
            }
        });

        deleteNoteButton.setText("Delete note");
        deleteNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Note Title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(2);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addNoteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteNoteButton))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(addNoteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(deleteNoteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteButtonActionPerformed
        // TODO add your handling code here:
        
        if(notes.isEmpty())
        {
            JOptionPane.showMessageDialog(this , "There isn't any notes to delete", "Info" ,1);
            return;
        }
        int x = JOptionPane.showConfirmDialog(this , "Are your sure to delete this note?", "Confirmation" ,2);
        if(x == 0)
        {
            int index = jTable1.getSelectedRow();
            TableModel model = jTable1.getModel();
            String title = model.getValueAt(index, 0).toString();
            String notePath = directory + userName + "\\" + title + ".txt";
            FileManager.deleteFromNotesFile(notesDetailsPath, title);
            boolean deleted = FileManager.deleteFile(notePath);
            if(deleted)
            {
                JOptionPane.showMessageDialog(this, "Deleted Successfully", "Done", 1);
                this.getNotes();
            }
        }
       
    }//GEN-LAST:event_deleteNoteButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        if(evt.getClickCount() == 2){
            if(NoteFrame.checkVisibilty())
            JOptionPane.showMessageDialog(this, "Please close the current frame", "Error", 0);
        else
        {
            int index = jTable1.getSelectedRow();
            TableModel model = jTable1.getModel();
            if(index < notes.size() && index >= 0)
            {
                String title = model.getValueAt(index, 0).toString();
            if(!notes.get(title).isEmpty())
            {
            	JPasswordField pf = new JPasswordField(10);
              	 pf.setFont(new Font("Tahoma", 0, 18));
              	 int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
              	 if(pf.getText().equals(notes.get(title)))
              	 {
              		ShowNote(title);
              	 }else if(okCxl == 0)
                    JOptionPane.showMessageDialog(this, "In correct password", "Error", 0);
            }else 
            	ShowNote(title);
            }
            
            
          }
        }   
    }//GEN-LAST:event_jTable1MouseClicked
    private void ShowNote(String title)
    {
    	String filePath = directory + userName + "\\";
        String body = FileManager.readFile(filePath + title + ".txt");
        Note note = new Note(title , body);
        new NoteFrame(note,filePath , this);
    }
    private void addNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteButtonActionPerformed
        // TODO add your handling code here:
        new NoteFrame(new Note(), Path.fullPath + userName + "\\" , this);
    }//GEN-LAST:event_addNoteButtonActionPerformed

    @Override
    public void dispose() {
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNoteButton;
    private javax.swing.JButton deleteNoteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
