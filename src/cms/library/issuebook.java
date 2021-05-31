/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.library;
import cms.login.LoginForm;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author vaibhav
 */
public class issuebook extends javax.swing.JFrame {
   
    /**
     * Creates new form issuebook
     */
    public issuebook() {
        initComponents();
        Connect();
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
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /***********************************Fetch book_details from the database****************************************************************/
    public void bookdetails(){
        int bookid=Integer.parseInt(txt_bookid.getText());
        try{
               pst=con.prepareStatement("select * from book_detail where bookid=?");
               pst.setInt(1, bookid);
               rs=pst.executeQuery();
               if(rs.next()){
                     lbl_bookid.setText(rs.getString("bookid"));
                     lbl_bookname.setText(rs.getString("book_name"));
                     lbl_author.setText(rs.getString("author"));
                     lbl_quantity.setText(rs.getString("quantity"));
               }else{
                  txt_berror.setText("Invalid Book Id");
               }
        } catch (SQLException ex) {
            Logger.getLogger(issuebook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**********************************************************************************************************/
       public void studentdetails(){
       int rollno=Integer.parseInt(txt_studentroll.getText());
        try{
               pst=con.prepareStatement("select * from students where rollno=?");
               pst.setInt(1, rollno);
               rs=pst.executeQuery();
               if(rs.next()){
                     lbl_rollno.setText(rs.getString("rollno"));
                     lbl_studentname.setText(rs.getString("firstname"));
                     lbl_cource.setText(rs.getString("cource"));
               }else{
                  txt_serror.setText("Invalid Student Rollno");
               }
        } catch (SQLException ex) {
            Logger.getLogger(issuebook.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       /**********************************************************************************************************/
       public boolean issuebook(){
           boolean status=false;
       int book_id=Integer.parseInt(txt_bookid.getText());
       int student_roll=Integer.parseInt(txt_studentroll.getText());
       String semester=sem_combo.getSelectedItem().toString();
       String student_name=lbl_studentname.getText();
       String book_name=lbl_bookname.getText();
       Date issue=issuedate.getDatoFecha();
       Date due=duedate.getDatoFecha();
       
       Long a=issue.getTime();
       Long b=due.getTime();
       
         java.sql.Date issuedate=new java.sql.Date(a); /**covert util date to sql date as database support only sql date**/
        java.sql.Date duedate=new java.sql.Date(b);
        
        try{
               pst=con.prepareStatement("insert into issue_book (book_id,book_name,student_roll,student_name,semester,"
                       + "issue_date,due_date,status)values(?,?,?,?,?,?,?,?)");
               pst.setInt(1, book_id);
               pst.setString(2, book_name);
               pst.setInt(3, student_roll);
               pst.setString(4, student_name);
               pst.setString(5, semester);
               pst.setDate(6, issuedate);
               pst.setDate(7, duedate);
               pst.setString(8, "Pending");
              int rowcount=pst.executeUpdate();
               if(rowcount>0){
                     status=true;
               }else{
                  status=false;
               }
        } catch (SQLException ex) {
            Logger.getLogger(issuebook.class.getName()).log(Level.SEVERE, null, ex);
        }
          return status;
       }
       /***********************************************************************************************/
       public void bookquantity(){
         int bookid=Integer.parseInt(txt_bookid.getText());
          try{
               pst=con.prepareStatement("update book_detail set quantity=quantity-1 where bookid=?");
               pst.setInt(1, bookid);
              int rowcount=pst.executeUpdate();
               if(rowcount>0){
                     JOptionPane.showMessageDialog(this, "Book Quantity Updated Successfully!!");
                     int intitialcount=Integer.parseInt(lbl_quantity.getText());
                     lbl_quantity.setText(Integer.toString(intitialcount -1));
               }else{
                  JOptionPane.showMessageDialog(this, "Can't Update Book Quantity!!!");
               }
        } catch (SQLException ex) {
            Logger.getLogger(issuebook.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       /*********************************************************************************************/
       public boolean alreadyissue(){
          boolean issuedalready=false;
           int bookid=Integer.parseInt(txt_bookid.getText());
            int rollno=Integer.parseInt(txt_studentroll.getText());
             try{
               pst=con.prepareStatement("select * from issue_book where book_id=? and student_roll=? and status=?");
               pst.setInt(1, bookid);
               pst.setInt(2, rollno);
               pst.setString(3, "Pending");
              rs=pst.executeQuery();
               if(rs.next()){
                    issuedalready=true;
               }else{
                  issuedalready=false;
               }
        } catch (SQLException ex) {
            Logger.getLogger(issuebook.class.getName()).log(Level.SEVERE, null, ex);
        }
          return issuedalready;
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
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        txt_berror = new javax.swing.JLabel();
        back_bttn = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_cource = new javax.swing.JLabel();
        lbl_rollno = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        txt_serror = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_studentroll = new javax.swing.JTextField();
        txt_bookid = new javax.swing.JTextField();
        duedate = new rojeru_san.componentes.RSDateChooser();
        issuedate = new rojeru_san.componentes.RSDateChooser();
        jLabel10 = new javax.swing.JLabel();
        sem_combo = new javax.swing.JComboBox<>();
        issue_bttn = new rojerusan.RSMaterialButtonCircle();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib icons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 320, 100));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 310, 5));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Book Id :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Book Name : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Author Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quantity :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 200, 30));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 200, 30));

        lbl_author.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 190, 30));

        lbl_quantity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 190, 30));

        txt_berror.setBackground(new java.awt.Color(255, 51, 51));
        txt_berror.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_berror.setForeground(new java.awt.Color(255, 255, 51));
        jPanel1.add(txt_berror, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 210, 30));

        back_bttn.setBackground(new java.awt.Color(255, 51, 51));
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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 580));

        jPanel2.setBackground(new java.awt.Color(102, 51, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib icons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel6.setText("Student Details");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 370, 100));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 370, 5));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student Rollno : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Name : ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cource Name :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        lbl_cource.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_cource.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_cource, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 190, 30));

        lbl_rollno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_rollno.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 190, 30));

        lbl_studentname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 190, 30));

        txt_serror.setBackground(new java.awt.Color(102, 51, 255));
        txt_serror.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_serror.setForeground(new java.awt.Color(255, 255, 51));
        jPanel2.add(txt_serror, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 210, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 420, 580));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib icons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel11.setText("Issue Book");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 300, -1));

        jPanel6.setBackground(new java.awt.Color(255, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 125, 370, 5));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Due Date :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Book Id :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Student Rollno : ");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Issue Date :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        txt_studentroll.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_studentroll.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
        txt_studentroll.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentrollFocusLost(evt);
            }
        });
        jPanel3.add(txt_studentroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 250, 30));

        txt_bookid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        jPanel3.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 250, 30));

        duedate.setColorBackground(new java.awt.Color(255, 0, 0));
        duedate.setColorButtonHover(new java.awt.Color(255, 0, 0));
        duedate.setColorForeground(new java.awt.Color(255, 0, 0));
        duedate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        duedate.setPlaceholder("Select Due Date");
        jPanel3.add(duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 250, -1));

        issuedate.setColorBackground(new java.awt.Color(255, 0, 0));
        issuedate.setColorButtonHover(new java.awt.Color(255, 0, 0));
        issuedate.setColorForeground(new java.awt.Color(255, 0, 0));
        issuedate.setPlaceholder("Select Issue Date");
        jPanel3.add(issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 250, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Semester ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 20));

        sem_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------Select Semester-------", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));
        jPanel3.add(sem_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 250, 30));

        issue_bttn.setBackground(new java.awt.Color(255, 0, 0));
        issue_bttn.setText(" ISSUE BOOK");
        issue_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issue_bttnActionPerformed(evt);
            }
        });
        jPanel3.add(issue_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 230, 70));

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
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 40, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 480, 580));

        setSize(new java.awt.Dimension(1283, 581));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void issue_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issue_bttnActionPerformed
      if(lbl_quantity.getText().equals("0")){
        JOptionPane.showMessageDialog(this, "Sorry!! The book is not available.");
      }else{
          if(alreadyissue()==false){
           if(issuebook()==true){
       JOptionPane.showMessageDialog(this, "Book Issued Successfully!!");
       bookquantity();
       }else{
         JOptionPane.showMessageDialog(this, "Can't Issue Book!!!");
       }
          }else{
          JOptionPane.showMessageDialog(this, "This student already has this book");
          }
      }
    }//GEN-LAST:event_issue_bttnActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
     if(!txt_bookid.getText().equals("")){
        bookdetails();
        }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentrollFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentrollFocusLost
        if(!txt_studentroll.getText().equals("")){
        studentdetails();
       }
    }//GEN-LAST:event_txt_studentrollFocusLost

    private void back_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseClicked
        library h=new library();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_bttnMouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issuebook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issuebook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back_bttn;
    private rojeru_san.componentes.RSDateChooser duedate;
    private rojerusan.RSMaterialButtonCircle issue_bttn;
    private rojeru_san.componentes.RSDateChooser issuedate;
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_cource;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_rollno;
    private javax.swing.JLabel lbl_studentname;
    private javax.swing.JComboBox<String> sem_combo;
    private javax.swing.JLabel txt_berror;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JLabel txt_serror;
    private javax.swing.JTextField txt_studentroll;
    // End of variables declaration//GEN-END:variables
}
