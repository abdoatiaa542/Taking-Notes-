/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takingnotes;

import java.awt.Font;
import javax.swing.*;


public class NoteFrame extends javax.swing.JFrame {
    private Note note;
    private static boolean isShown = false;
    private boolean isEditable;
    private final String filePath;
    private final NotesFrame nf;
    public NoteFrame(Note note,String filePath , NotesFrame nf) {
        initComponents();
        this.note = note;
        this.filePath = filePath;
        this.jTextArea1.setText(note.getBody());
        this.nf = nf;
        start();
    }
    public static boolean checkVisibilty()
    {
        return isShown;
    }
    private void start()
    {
        titleField.setText(note.getTitle());
        this.setVisible(true);
        NoteFrame.isShown = true;
        textValidation();
    }
    @Override
    public void dispose() {
        super.dispose();
        NoteFrame.isShown = false;
        nf.getNotes();
    } 
    
    private void textValidation()
    {
       if(note.isEmpty())
          isEditable = true;
       else
          isEditable = false;
       
        jTextArea1.setEditable(isEditable);
        titleField.setEditable(isEditable);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Note details");
        setResizable(false);

        titleField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        titleField.setText("Untitled");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Title");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Body");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addGap(32, 32, 32)
                        .addComponent(saveButton)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if(isEditable)
            isEditable = false;
        else
            isEditable = true;
        jTextArea1.setEditable(isEditable);
        titleField.setEditable(isEditable);
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
    
        String title = note.getTitle();
        String fullPath = filePath + title + ".txt";
        String newPath = filePath + titleField.getText() + ".txt";
        if(nf.notes.get(titleField.getText()) != null && note.isEmpty())
        {
        	JOptionPane.showMessageDialog(this, "There's a note by this title exist", "Error", 0);
        	return;
        }
        String noteDetailsPath = filePath + nf.getUserName() + "-notes-details.txt";
        String body = jTextArea1.getText();
        if(!titleField.getText().equals(title) && !note.isEmpty())
        {
            FileManager.renameFile(fullPath, newPath);
            FileManager.editNotesFile(noteDetailsPath, title, titleField.getText());
            fullPath = newPath;
            note.setTitle(titleField.getText());
            FileManager.addNoteToFile(fullPath, body);
        }else{
           if(note.isEmpty())
           {
               if(titleField.getText().isEmpty())
               {
                   JOptionPane.showMessageDialog(this, "Title musn't be empty", "Error", 0);
                   return;
               }
               fullPath = newPath;
               int result = JOptionPane.showConfirmDialog(null, "Do you want to add Password" , "Question",  2);
               if(result == 0){
                   String password = showPasswordField();
                   if(password.isEmpty())
                       return;
                   note.setTitle(titleField.getText());
                   title = note.getTitle();
                   note = new Note(title, password, body);
               }
               else{
                   note.setTitle(titleField.getText());
                   title = note.getTitle();
                   note = new Note(title ,body);
               } 
             FileManager.addNotesToDetailsFile(noteDetailsPath, nf.notes, note);     
           }
           else
           {
                note.setTitle(titleField.getText());
                title = note.getTitle();
                String password = note.getPassword();
                note = new Note(title,password,body);
           }
            FileManager.addNoteToFile(fullPath, body);
        }
        
        
    }//GEN-LAST:event_saveButtonActionPerformed

    private String showPasswordField()
    {
        JPasswordField pf = new JPasswordField(10);
          pf.setFont(new Font("Tahoma", 0, 18));
         int okCxl = JOptionPane.showConfirmDialog(this, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
       	 char pass[] = pf.getPassword();
         String password = new String(pass);
         if(okCxl == 0 && password.isEmpty())
             JOptionPane.showMessageDialog(this, "Password musn't be empty", "Error", 0);
         return password;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables
}
