/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author vaibhav
 */
public class viewrecord extends javax.swing.JFrame {

    /**
     * Creates new form viewrecord
     */
    public viewrecord() {
        initComponents();
        Connect();
        updatetable();
    }
/************************************************************************************************************************/
     Connection con;
         PreparedStatement pst;
         ResultSet rs;
         DefaultTableModel d;
         
           public void Connect()
            {
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cms" , "root" , "mysql");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(viewrecord.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(viewrecord.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }
    /********************************************************************************************************************/
           public void updatetable(){
      try{
         pst=con.prepareStatement("SELECT * from faculty");
             rs=pst.executeQuery();
             while(rs.next()){
                 String Id=rs.getString("id");
                 String FacultyName=rs.getString("facultyname");
                 String Course=rs.getString("course");
                 String Semester=rs.getString("semester");
                 String Subject=rs.getString("subjectassign");
                 
                Object[] obj={Id,FacultyName,Course,Semester,Subject};
                d=(DefaultTableModel)faculty_table.getModel();
                d.addRow(obj);
             }
      }catch(Exception e){
      
      }
    }
           public void search(String str){
    d=(DefaultTableModel)faculty_table.getModel();
    TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(d); /**sort and filter the table for search*/
    faculty_table.setRowSorter(trs);
    trs.setRowFilter(RowFilter.regexFilter(str));
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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        faculty_table = new rojeru_san.complementos.RSTableMetro();
        jButton10 = new javax.swing.JButton();
        back_bttn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchtab = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("View Record");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 210, 30));

        faculty_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Faculty Name", "Course", "Semester", "Subject"
            }
        ));
        jScrollPane1.setViewportView(faculty_table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 840, 380));

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
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 40, 40));

        back_bttn.setBackground(new java.awt.Color(21, 25, 28));
        back_bttn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_bttnMouseClicked(evt);
            }
        });
        back_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back_bttn.png"))); // NOI18N
        jLabel1.setText("Back");
        back_bttn.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(back_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter Search String :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        searchtab.setBackground(new java.awt.Color(21, 25, 28));
        searchtab.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchtab.setForeground(new java.awt.Color(51, 51, 255));
        searchtab.setBorder(null);
        searchtab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtabKeyReleased(evt);
            }
        });
        jPanel1.add(searchtab, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 510, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 480, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void back_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseClicked
        faculty h=new faculty();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_bttnMouseClicked

    private void searchtabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtabKeyReleased
        String search=searchtab.getText();
        search(search);
    }//GEN-LAST:event_searchtabKeyReleased

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
            java.util.logging.Logger.getLogger(viewrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewrecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewrecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back_bttn;
    private rojeru_san.complementos.RSTableMetro faculty_table;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField searchtab;
    // End of variables declaration//GEN-END:variables
}