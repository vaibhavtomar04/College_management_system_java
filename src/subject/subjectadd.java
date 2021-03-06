/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package subject;

import cms.Home;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author vaibhav
 */
public class subjectadd extends javax.swing.JFrame {

    /** Creates new form subjectadd */
    public subjectadd() {
        initComponents();
    Connect();
        updatesubject();
        updatecombo();
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cms", "root", "mysql");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatesubject() {
        try {
            pst = con.prepareStatement("SELECT * from subject");
            rs = pst.executeQuery();
            while (rs.next()) {
                String Code = rs.getString("subjectcode");
                String Subject = rs.getString("subjectname");
                String Course = rs.getString("course");
                String Semester = rs.getString("semester");
                String InternalMark = rs.getString("internalmark");
                String ExternalMark = rs.getString("externalmark");
                Object[] obj = {Code, Subject, Course, Semester, InternalMark, ExternalMark};
                d = (DefaultTableModel) sub_table.getModel();
                d.addRow(obj);
            }
        } catch (Exception e) {

        }
    }

    /**
     * ************************************************************************************************************
     */
    public void cleartable() {
        d = (DefaultTableModel) sub_table.getModel();
        d.setRowCount(0);
    }

    /**
     * **************************************************************************************
     */
    public void updatecombo() {
        try {
            pst = con.prepareStatement("SELECT * FROM cource");
            rs = pst.executeQuery();
            while (rs.next()) {
                course_combo.addItem(rs.getString("courcename"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        back_bttn = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_mark2 = new javax.swing.JTextField();
        txt_code = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_mark1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        sub_table = new rojeru_san.complementos.RSTableMetro();
        sem_combo = new javax.swing.JComboBox<>();
        course_combo = new javax.swing.JComboBox<>();
        add = new rojerusan.RSMaterialButtonCircle();
        update = new rojerusan.RSMaterialButtonCircle();
        delete = new rojerusan.RSMaterialButtonCircle();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setPreferredSize(new java.awt.Dimension(1010, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_bttn.setBackground(new java.awt.Color(21, 25, 28));
        back_bttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_bttnMouseClicked(evt);
            }
        });
        back_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back_bttn.png"))); // NOI18N
        jLabel17.setText("Back");
        back_bttn.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(back_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton10.setToolTipText("Close");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel_reed.png"))); // NOI18N
        jButton10.setVerifyInputWhenFocusTarget(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 40, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Subject");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("External Marks :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Subject Code :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Subject Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Semester :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Internal Marks :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        txt_mark2.setBackground(new java.awt.Color(21, 25, 28));
        txt_mark2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_mark2.setForeground(new java.awt.Color(255, 0, 102));
        txt_mark2.setBorder(null);
        txt_mark2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mark2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mark2FocusLost(evt);
            }
        });
        txt_mark2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mark2KeyPressed(evt);
            }
        });
        jPanel1.add(txt_mark2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 190, 30));

        txt_code.setBackground(new java.awt.Color(21, 25, 28));
        txt_code.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_code.setForeground(new java.awt.Color(255, 0, 102));
        txt_code.setBorder(null);
        txt_code.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_codeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_codeFocusLost(evt);
            }
        });
        txt_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codeKeyPressed(evt);
            }
        });
        jPanel1.add(txt_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 190, 30));

        txt_name.setBackground(new java.awt.Color(21, 25, 28));
        txt_name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_name.setForeground(new java.awt.Color(255, 0, 102));
        txt_name.setBorder(null);
        txt_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nameFocusLost(evt);
            }
        });
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nameKeyPressed(evt);
            }
        });
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 190, 30));

        txt_mark1.setBackground(new java.awt.Color(21, 25, 28));
        txt_mark1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_mark1.setForeground(new java.awt.Color(255, 0, 102));
        txt_mark1.setBorder(null);
        txt_mark1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mark1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_mark1FocusLost(evt);
            }
        });
        txt_mark1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mark1KeyPressed(evt);
            }
        });
        jPanel1.add(txt_mark1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 190, 30));

        sub_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Subject", "Course", "Semester", "Internal Mark", "External Mark"
            }
        ));
        sub_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sub_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sub_table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 80, 620, 440));

        sem_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------Select Semester-----------", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));
        jPanel1.add(sem_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 190, 40));

        course_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---------Select-----------" }));
        jPanel1.add(course_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 170, 40));

        add.setBackground(new java.awt.Color(204, 0, 0));
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 210, 50));

        update.setBackground(new java.awt.Color(204, 0, 0));
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 200, 50));

        delete.setBackground(new java.awt.Color(204, 0, 0));
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 240, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 190, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 222, 190, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 190, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 190, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void back_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseClicked
        Home h = new Home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_bttnMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txt_mark2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mark2FocusGained

    }//GEN-LAST:event_txt_mark2FocusGained

    private void txt_mark2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mark2FocusLost

    }//GEN-LAST:event_txt_mark2FocusLost

    private void txt_mark2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mark2KeyPressed

    }//GEN-LAST:event_txt_mark2KeyPressed

    private void txt_codeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeFocusGained

    private void txt_codeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codeFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeFocusLost

    private void txt_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeKeyPressed

    private void txt_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameFocusGained

    private void txt_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameFocusLost

    private void txt_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameKeyPressed

    private void txt_mark1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mark1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mark1FocusGained

    private void txt_mark1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mark1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mark1FocusLost

    private void txt_mark1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mark1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mark1KeyPressed

    private void sub_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sub_tableMouseClicked
        int r = sub_table.getSelectedRow();
        TableModel tm = sub_table.getModel();
        txt_code.setText(tm.getValueAt(r, 0).toString());
        txt_name.setText(tm.getValueAt(r, 1).toString());
        course_combo.setSelectedItem(tm.getValueAt(r, 2).toString());
        sem_combo.setSelectedItem(tm.getValueAt(r, 3).toString());
        txt_mark1.setText(tm.getValueAt(r, 4).toString());
        txt_mark2.setText(tm.getValueAt(r, 5).toString());
    }//GEN-LAST:event_sub_tableMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        int subjectcode = Integer.parseInt(txt_code.getText());
        String subjectname = txt_name.getText();
        String course = course_combo.getSelectedItem().toString();
        String semester = sem_combo.getSelectedItem().toString();
        int internalmark = Integer.parseInt(txt_mark1.getText());
        int externalmark = Integer.parseInt(txt_mark2.getText());
        try {
            pst = con.prepareStatement("insert into subject values(?,?,?,?,?,?)");
            pst.setInt(1, subjectcode);
            pst.setString(2, subjectname);
            pst.setString(3, course);
            pst.setString(4, semester);
            pst.setInt(5, internalmark);
            pst.setInt(6, externalmark);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Subject Added Successfully!!");
            cleartable();
            txt_code.setText("");
            txt_name.setText("");
            course_combo.setSelectedItem("");
            sem_combo.setSelectedItem("");
            txt_mark1.setText("");
            txt_mark2.setText("");
            txt_code.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        }
        updatesubject();
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        int subjectcode = Integer.parseInt(txt_code.getText());
        String subjectname = txt_name.getText();
        String course = course_combo.getSelectedItem().toString();
        String semester = sem_combo.getSelectedItem().toString();
        int internalmark = Integer.parseInt(txt_mark1.getText());
        int externalmark = Integer.parseInt(txt_mark2.getText());

        try {
            pst = con.prepareStatement("update subject set subjectname=? , course=? ,semester=? ,internalmark=? ,externalmark=? where subjectcode=?");
            pst.setString(1, subjectname);
            pst.setString(2, course);
            pst.setString(3, semester);
            pst.setInt(4, internalmark);
            pst.setInt(5, externalmark);
            pst.setInt(6, subjectcode);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Subject Edited Successfully!!");
            cleartable();

        } catch (SQLException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        }
        updatesubject();
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        int subjectcode = Integer.parseInt(txt_code.getText());
        try {
            pst = con.prepareStatement("delete from subject  where subjectcode=?");
            pst.setInt(1, subjectcode);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Subject Deleted Successfully!!");
            cleartable();
            txt_code.setText("");
            txt_name.setText("");
            course_combo.setSelectedItem("");
            sem_combo.setSelectedItem("");
            txt_mark1.setText("");
            txt_mark2.setText("");
            txt_code.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(subjectadd.class.getName()).log(Level.SEVERE, null, ex);
        }
        updatesubject();
    }//GEN-LAST:event_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(subjectadd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(subjectadd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(subjectadd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(subjectadd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new subjectadd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle add;
    private javax.swing.JPanel back_bttn;
    private javax.swing.JComboBox<String> course_combo;
    private rojerusan.RSMaterialButtonCircle delete;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox<String> sem_combo;
    private rojeru_san.complementos.RSTableMetro sub_table;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_mark1;
    private javax.swing.JTextField txt_mark2;
    private javax.swing.JTextField txt_name;
    private rojerusan.RSMaterialButtonCircle update;
    // End of variables declaration//GEN-END:variables

}
