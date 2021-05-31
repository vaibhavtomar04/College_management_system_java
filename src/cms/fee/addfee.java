/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.fee;

import cms.Home;
import cms.numbertoword;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author vaibhav
 */
public class addfee extends javax.swing.JFrame {

    /**
     * Creates new form addfee
     */
    public addfee() {
        initComponents();
        Connect();
        updatecourcecombo();
        displaycash();
        int receipt = receiptno();
        txt_receipt.setText(Integer.toString(receipt));

    }
    /**
     * *************************************************************************************************************
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cms", "root", "mysql");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addfee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addfee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *************************************************************************************************************
     */
    public void displaycash() {
        lbl_ddno.setVisible(false);
        lbl_chequeno.setVisible(false);
        lbl_bankname.setVisible(false);
        txt_ddno.setVisible(false);
        txt_chequeno.setVisible(false);
        txt_bankname.setVisible(false);

    }

    public boolean validation() {
        /*validation for date*/
        if (datechooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please Select a date");
            return false;
        }
        /*validation for payment mode if user selected cheque as a mode of payment*/
        if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Cheque")) {
            if (txt_chequeno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Cheque Number");
                return false;
            }
            if (txt_bankname.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return false;
            }
        }
        /*validation for payment mode if user selected demanddraft as a mode of payment*/
        if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Demand Draft")) {
            if (txt_ddno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Demand Draft Number");
                return false;
            }
            if (txt_bankname.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return false;
            }
        }
        /*validation for payment mode if user selected card as a mode of payment*/
        if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Card")) {
            if (txt_bankname.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return false;
            }
        }
        /*validation for studentname*/
        if (txt_receivedfrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter the Student Name");
            return false;
        }
        /*validation for amount*/
        if (txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+") == false) {
            /*user can enter the amount only in digits..*/
            JOptionPane.showMessageDialog(this, "Please Enter the Amount in numbers");
            return false;
        }

        return true;
    }

    /**
     * **********************************************************************************************************************
     */
    public int receiptno() {
        int receipt = 0;
        try {
            pst = con.prepareStatement("SELECT MAX(receipt_no) from fees");
            rs = pst.executeQuery();
            if (rs.next() == true) {
                receipt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receipt + 1;
    }

    /**
     * **********************************************************************************************************************
     */
    public String datainsert() {
        String status = "";
        /*this indicates whether values inserted in database or not*/
        int receiptno = Integer.parseInt(txt_receipt.getText());
        String Student_name = txt_receivedfrom.getText();
        int rollno = Integer.parseInt(txt_rollno.getText());
        String gstin = txt_gst.getText();
        float total_amount = Float.parseFloat(txt_total.getText());
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String date = df.format(datechooser.getDate());
        String payment_mode = combo_payment.getSelectedItem().toString();
        String cheque_no = txt_chequeno.getText();
        String dd_no = txt_ddno.getText();
        String bank_name = txt_bankname.getText();
        String course_name = txt_courcename.getText();
        String semester = combo_semester.getSelectedItem().toString();
        float amount = Float.parseFloat(txt_amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String total_amount_word = txt_amountwords.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());

        try {
            pst = con.prepareStatement("insert into fees values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            pst.setInt(1, receiptno);
            pst.setString(2, Student_name);
            pst.setInt(3, rollno);
            pst.setString(4, payment_mode);
            pst.setString(5, cheque_no);
            pst.setString(6, bank_name);
            pst.setString(7, dd_no);
            pst.setString(8, course_name);
            pst.setString(9, semester);
            pst.setString(10, gstin);
            pst.setFloat(11, total_amount);
            pst.setString(12, date);
            pst.setFloat(13, amount);
            pst.setFloat(14, cgst);
            pst.setFloat(15, sgst);
            pst.setString(16, total_amount_word);
            pst.setInt(17, year1);
            pst.setInt(18, year2);
            int rowcount = pst.executeUpdate();
            if (rowcount == 1) {
                status = "success";
            } else {
                status = "failed";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * ***********************************************************************************************************************
     */
    public void updatecourcecombo() {
        try {
            pst = con.prepareStatement("SELECT * FROM cource");
            rs = pst.executeQuery();
            while (rs.next()) {
                combo_cource.addItem(rs.getString("courcename"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addfee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidebar = new javax.swing.JPanel();
        home_bttn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        search_bttn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logout_bttn = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        viewrecord_bttn = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        back_bttn = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        edit_bttn = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        print_option = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        lbl_chequeno = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_receipt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_gst = new javax.swing.JLabel();
        lbl_ddno = new javax.swing.JLabel();
        txt_ddno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_chequeno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        txt_year1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_amountwords = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txt_sgst = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        print_bttn = new javax.swing.JButton();
        txt_courcename = new javax.swing.JTextField();
        txt_receivedfrom = new javax.swing.JTextField();
        lbl_bankname = new javax.swing.JLabel();
        txt_bankname = new javax.swing.JTextField();
        combo_payment = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        combo_cource = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txt_rollno = new javax.swing.JTextField();
        datechooser = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        combo_semester = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setBackground(new java.awt.Color(0, 102, 51));
        sidebar.setForeground(new java.awt.Color(0, 204, 51));
        sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home_bttn.setBackground(new java.awt.Color(0, 102, 51));
        home_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        home_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                home_bttnMouseExited(evt);
            }
        });
        home_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/home.png"))); // NOI18N
        home_bttn.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Home");
        home_bttn.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 60, 40));

        sidebar.add(home_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 70));

        search_bttn.setBackground(new java.awt.Color(0, 102, 51));
        search_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        search_bttn.setMinimumSize(new java.awt.Dimension(150, 70));
        search_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                search_bttnMouseExited(evt);
            }
        });
        search_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/search2.png"))); // NOI18N
        search_bttn.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 70));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Search Record");
        search_bttn.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 130, 30));

        sidebar.add(search_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 190, 70));

        logout_bttn.setBackground(new java.awt.Color(0, 102, 51));
        logout_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logout_bttn.setMinimumSize(new java.awt.Dimension(150, 70));
        logout_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logout_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logout_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logout_bttnMouseExited(evt);
            }
        });
        logout_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/logout.png"))); // NOI18N
        logout_bttn.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Logout");
        logout_bttn.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 110, 30));

        sidebar.add(logout_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 190, 70));

        viewrecord_bttn.setBackground(new java.awt.Color(0, 102, 51));
        viewrecord_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        viewrecord_bttn.setMinimumSize(new java.awt.Dimension(150, 70));
        viewrecord_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewrecord_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewrecord_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewrecord_bttnMouseExited(evt);
            }
        });
        viewrecord_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/list_1.png"))); // NOI18N
        viewrecord_bttn.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("View Record");
        viewrecord_bttn.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 110, 30));

        sidebar.add(viewrecord_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 190, 70));

        back_bttn.setBackground(new java.awt.Color(0, 102, 51));
        back_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        back_bttn.setMinimumSize(new java.awt.Dimension(150, 70));
        back_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                back_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                back_bttnMouseExited(evt);
            }
        });
        back_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/left-arrow.png"))); // NOI18N
        back_bttn.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Back");
        back_bttn.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 110, 30));

        sidebar.add(back_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 190, 70));

        edit_bttn.setBackground(new java.awt.Color(0, 102, 51));
        edit_bttn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        edit_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_bttnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                edit_bttnMouseExited(evt);
            }
        });
        edit_bttn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/edit2.png"))); // NOI18N
        jLabel29.setText("Edit");
        edit_bttn.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, -1));

        sidebar.add(edit_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 190, 70));

        print_option.setBackground(new java.awt.Color(0, 102, 51));
        print_option.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print_option.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                print_optionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                print_optionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                print_optionMouseExited(evt);
            }
        });
        print_option.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my icons/printer-.png"))); // NOI18N
        jLabel30.setText("Print");
        print_option.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 160, 70));

        sidebar.add(print_option, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 190, 70));

        getContentPane().add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 720));

        content.setBackground(new java.awt.Color(21, 25, 28));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_chequeno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_chequeno.setForeground(new java.awt.Color(255, 255, 255));
        lbl_chequeno.setText("Cheque no :");
        content.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("KIHEAT -");
        content.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        txt_receipt.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        txt_receipt.setForeground(new java.awt.Color(255, 51, 51));
        txt_receipt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_receipt.setBorder(null);
        content.add(txt_receipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 170, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Reciept No : ");
        content.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        txt_gst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_gst.setForeground(new java.awt.Color(255, 255, 255));
        txt_gst.setText("22HV5JH55");
        content.add(txt_gst, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 130, -1));

        lbl_ddno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_ddno.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ddno.setText("DD no :");
        content.add(lbl_ddno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        txt_ddno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_ddno.setForeground(new java.awt.Color(255, 51, 51));
        content.add(txt_ddno, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 250, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mode of Payment :");
        content.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        txt_chequeno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_chequeno.setForeground(new java.awt.Color(255, 51, 51));
        content.add(txt_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 250, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Date :");
        content.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("GSTIN :");
        content.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("The Following Payment in the college for the year");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("to");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, -1));

        txt_year2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_year2.setForeground(new java.awt.Color(255, 51, 51));
        txt_year2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_year2.setBorder(null);
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 110, 30));

        txt_year1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_year1.setForeground(new java.awt.Color(255, 51, 51));
        txt_year1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_year1.setBorder(null);
        jPanel1.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 100, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Total in Words :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Amount (RS)");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 720, 20));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 720, 10));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Receiver Signature ");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, -1, 40));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SGST 9%");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        txt_amountwords.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_amountwords.setForeground(new java.awt.Color(255, 51, 51));
        txt_amountwords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountwordsActionPerformed(evt);
            }
        });
        jPanel1.add(txt_amountwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 460, 40));

        txt_amount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_amount.setForeground(new java.awt.Color(255, 51, 51));
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        jPanel1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 150, 30));

        txt_cgst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_cgst.setForeground(new java.awt.Color(255, 51, 51));
        txt_cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cgstActionPerformed(evt);
            }
        });
        jPanel1.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 150, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Heads");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, 40));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("CGST 9%");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, -1, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 190, -1));

        txt_sgst.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_sgst.setForeground(new java.awt.Color(255, 51, 51));
        txt_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sgstActionPerformed(evt);
            }
        });
        jPanel1.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 150, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Received From :");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        txt_total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_total.setForeground(new java.awt.Color(255, 51, 51));
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 150, 30));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 180, 10));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Sr.No");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 40));

        print_bttn.setText("Print");
        print_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_bttnActionPerformed(evt);
            }
        });
        jPanel1.add(print_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, 100, 30));

        txt_courcename.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_courcename.setForeground(new java.awt.Color(255, 51, 51));
        txt_courcename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_courcenameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_courcename, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 290, 30));

        txt_receivedfrom.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_receivedfrom.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(txt_receivedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 270, 50));

        content.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 760, 440));

        lbl_bankname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_bankname.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bankname.setText("Bank Name :");
        content.add(lbl_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        txt_bankname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_bankname.setForeground(new java.awt.Color(255, 51, 51));
        txt_bankname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        content.add(txt_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 250, 40));

        combo_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Demand Draft", "Cash", "Cheque", "Card" }));
        combo_payment.setSelectedIndex(1);
        combo_payment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        combo_payment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_paymentActionPerformed(evt);
            }
        });
        content.add(combo_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 130, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Semester :");
        content.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, -1, -1));

        combo_cource.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------Select Cource-------" }));
        combo_cource.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        combo_cource.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_cource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courceActionPerformed(evt);
            }
        });
        content.add(combo_cource, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 160, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Roll No :");
        content.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, -1, -1));

        txt_rollno.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_rollno.setForeground(new java.awt.Color(255, 51, 51));
        txt_rollno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_rollno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rollnoFocusLost(evt);
            }
        });
        content.add(txt_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 160, 30));
        content.add(datechooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 160, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Cource :");
        content.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        combo_semester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----Select Semester-----", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6" }));
        content.add(combo_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 150, 30));

        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 770, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
 private void set(JPanel panel) {
        panel.setBackground(new Color(0, 153, 204));
    }

    private void reset(JPanel panel) {
        panel.setBackground(new Color(0, 102, 51));
    }

    private void home_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_bttnMouseClicked
        fees_menu fm = new fees_menu();
        fm.setVisible(true);
        dispose();
    }//GEN-LAST:event_home_bttnMouseClicked

    private void search_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseClicked
        search_fee_records sfr = new search_fee_records();
        sfr.setVisible(true);
        dispose();
    }//GEN-LAST:event_search_bttnMouseClicked

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void txt_amountwordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountwordsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_amountwordsActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        Float amount = Float.parseFloat(txt_amount.getText());
        Float cgst = (float) (amount * 0.09);
        Float sgst = (float) (amount * 0.09);
        txt_cgst.setText(cgst.toString());
        txt_sgst.setText(sgst.toString());

        float total = amount + cgst + sgst;
        txt_total.setText(Float.toString(total));
        txt_amountwords.setText(numbertoword.convert((int) total) + " only");
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cgstActionPerformed

    private void txt_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sgstActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_courcenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_courcenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_courcenameActionPerformed

    private void combo_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_paymentActionPerformed
        if (combo_payment.getSelectedIndex() == 0) {
            lbl_ddno.setVisible(true);
            lbl_bankname.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_ddno.setVisible(true);
            txt_bankname.setVisible(true);
            txt_chequeno.setVisible(false);
        }
        if (combo_payment.getSelectedIndex() == 1) {
            lbl_ddno.setVisible(false);
            lbl_bankname.setVisible(false);
            lbl_chequeno.setVisible(false);
            txt_ddno.setVisible(false);
            txt_bankname.setVisible(false);
            txt_chequeno.setVisible(false);
        }
        if (combo_payment.getSelectedIndex() == 2) {
            lbl_ddno.setVisible(false);
            lbl_bankname.setVisible(true);
            lbl_chequeno.setVisible(true);
            txt_ddno.setVisible(false);
            txt_bankname.setVisible(true);
            txt_chequeno.setVisible(true);
        }
        if (combo_payment.getSelectedItem().equals("Card")) {
            lbl_ddno.setVisible(false);
            lbl_bankname.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_ddno.setVisible(false);
            txt_bankname.setVisible(true);
            txt_chequeno.setVisible(false);
        }
    }//GEN-LAST:event_combo_paymentActionPerformed

    private void print_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_bttnActionPerformed
        if (validation() == true) {

            String status = datainsert();
            if (status.equals("success")) {
                JOptionPane.showMessageDialog(this, "Record Added Successfully!!!");
                printreceipt pr = new printreceipt();
                pr.setVisible(true);
                this.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Error!!!! Please Try Again Later");
        }


    }//GEN-LAST:event_print_bttnActionPerformed

    private void combo_courceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courceActionPerformed
        txt_courcename.setText(combo_cource.getSelectedItem().toString());
    }//GEN-LAST:event_combo_courceActionPerformed

    private void logout_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout_bttnMouseClicked
        Home h = new Home();
        h.setVisible(true);
        dispose();
    }//GEN-LAST:event_logout_bttnMouseClicked

    private void viewrecord_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewrecord_bttnMouseClicked
        viewrecords vr = new viewrecords();
        vr.setVisible(true);
        dispose();
    }//GEN-LAST:event_viewrecord_bttnMouseClicked

    private void back_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseClicked
        fees_menu f = new fees_menu();
        f.setVisible(true);
        dispose();
    }//GEN-LAST:event_back_bttnMouseClicked

    private void home_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_bttnMouseEntered
        set(home_bttn);

    }//GEN-LAST:event_home_bttnMouseEntered

    private void home_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_bttnMouseExited
        reset(home_bttn);
    }//GEN-LAST:event_home_bttnMouseExited

    private void search_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseEntered
        set(search_bttn);
    }//GEN-LAST:event_search_bttnMouseEntered

    private void search_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseExited
        reset(search_bttn);
    }//GEN-LAST:event_search_bttnMouseExited

    private void viewrecord_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewrecord_bttnMouseEntered
        set(viewrecord_bttn);
    }//GEN-LAST:event_viewrecord_bttnMouseEntered

    private void viewrecord_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewrecord_bttnMouseExited
        reset(viewrecord_bttn);
    }//GEN-LAST:event_viewrecord_bttnMouseExited

    private void back_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseEntered
        set(back_bttn);
    }//GEN-LAST:event_back_bttnMouseEntered

    private void back_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_bttnMouseExited
        reset(back_bttn);
    }//GEN-LAST:event_back_bttnMouseExited

    private void logout_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout_bttnMouseEntered
        set(logout_bttn);
    }//GEN-LAST:event_logout_bttnMouseEntered

    private void logout_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout_bttnMouseExited
        reset(logout_bttn);
    }//GEN-LAST:event_logout_bttnMouseExited

    private void edit_bttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_bttnMouseClicked
        editreceipt er = new editreceipt();
        er.setVisible(true);
        dispose();
    }//GEN-LAST:event_edit_bttnMouseClicked

    private void edit_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_bttnMouseEntered
        set(edit_bttn);
    }//GEN-LAST:event_edit_bttnMouseEntered

    private void edit_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_bttnMouseExited
        reset(edit_bttn);
    }//GEN-LAST:event_edit_bttnMouseExited

    private void print_optionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_optionMouseClicked
        printreceipt pr = new printreceipt();
        pr.setVisible(true);
        dispose();
    }//GEN-LAST:event_print_optionMouseClicked

    private void print_optionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_optionMouseEntered
        set(print_option);
    }//GEN-LAST:event_print_optionMouseEntered

    private void print_optionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_optionMouseExited
        reset(print_option);
    }//GEN-LAST:event_print_optionMouseExited

    private void txt_rollnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rollnoFocusLost
        
    }//GEN-LAST:event_txt_rollnoFocusLost

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
            java.util.logging.Logger.getLogger(addfee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addfee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addfee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addfee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addfee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back_bttn;
    private javax.swing.JComboBox<String> combo_cource;
    private javax.swing.JComboBox<String> combo_payment;
    private javax.swing.JComboBox<String> combo_semester;
    private javax.swing.JPanel content;
    private com.toedter.calendar.JDateChooser datechooser;
    private javax.swing.JPanel edit_bttn;
    private javax.swing.JPanel home_bttn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbl_bankname;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_ddno;
    private javax.swing.JPanel logout_bttn;
    private javax.swing.JButton print_bttn;
    private javax.swing.JPanel print_option;
    private javax.swing.JPanel search_bttn;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_amountwords;
    private javax.swing.JTextField txt_bankname;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_chequeno;
    private javax.swing.JTextField txt_courcename;
    private javax.swing.JTextField txt_ddno;
    private javax.swing.JLabel txt_gst;
    private javax.swing.JTextField txt_receipt;
    private javax.swing.JTextField txt_receivedfrom;
    private javax.swing.JTextField txt_rollno;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    private javax.swing.JPanel viewrecord_bttn;
    // End of variables declaration//GEN-END:variables
}
