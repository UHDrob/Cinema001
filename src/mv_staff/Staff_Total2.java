/*
 * Cinema Management System
 * Staff Section
 * Administration Level
 */
package mv_staff;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roberto Gomez
 */
public class Staff_Total2 extends javax.swing.JFrame {

    /**
     * Creates new form Movie_Window
     */
    public Staff_Total2() {
        initComponents();
        Show_Staff_In_JTable();
        Show_StaffTotals_In_JTable();

        frmcombolocations();

    }

    String ImgPath = null;
    int pos = 0;
    int contador = 0;

   public void frmcombolocations() {
       this.combolocations.removeAllItems();
          try{
           Connection con = getConnection();
           Statement Sent=con.createStatement();
           ResultSet rs = Sent.executeQuery("Select * from mv_locations");
           while(rs.next()) {
               this.combolocations.addItem(rs.getString("locationname"));
           }
           contador++;
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
           }
   }
    
  // Connect to JavaDB Database  
    public Connection getConnection()
    {
        Connection con = null;
        try {
            String urlDB = "jdbc:derby://localhost:1527/cinema";
            String usernameDB = "cinema";
            String passwordDB = "cinemalogin";

            con = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(   txt_firstname.getText() == null
                || txt_lastname.getText() == null                    
                || txt_username.getText() ==null
                || txt_password.getText() == null)                
        {
        return false;
        }
        else
        {
            try{
                Double.parseDouble(txt_salary.getText());                
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }        
    }
      
    // Resize the Image to fit into JLabel
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myImage = null;
        
        if(imagePath !=null)
        {
            myImage = new ImageIcon(imagePath);
        }
        else
        {
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_staffphoto.getWidth(), lbl_staffphoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        
        return image;        
    }
     
    // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    public ArrayList<Staff> getStaffList()
    {
            ArrayList<Staff> staffList  = new ArrayList<Staff>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_staff ORDER BY firstname, lastname";
            Statement st;
            ResultSet rs;           
                                    
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Staff staff;
            
            while(rs.next())
            {
                staff = new Staff(rs.getInt("employeeid"), 
                        rs.getInt("locationid_FK"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("employeetitle"),
                        rs.getString("employeeaddress"), 
                        rs.getInt("salary"), 
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBytes("staffphoto"));
                staffList.add(staff);
 
            }
                                   
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return staffList;                 
    }   
             public ArrayList<StaffTotals> getStaffTotalsList()
    {         
            ArrayList<StaffTotals> stafftotalsList  = new ArrayList<StaffTotals>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (employeeid) As Total_Employees\n" +
                         "FROM cinema.mv_staff";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            StaffTotals stafftotals;
            
            while(rs.next())
            {
                stafftotals = new StaffTotals(rs.getInt("Total_Employees"));
                stafftotalsList.add(stafftotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
        return stafftotalsList;                 
    }    
    //      2 - Populate The JTable    
    public void Show_Staff_In_JTable()
    {
        ArrayList<Staff> list = getStaffList();
        DefaultTableModel model = (DefaultTableModel)JTable_Staff.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getemployeeid();
            row[1] = list.get(i).getfirstname();
            row[2] = list.get(i).getlastname();
            row[3] = list.get(i).getemployeetitle();
            row[4] = list.get(i).getusername();
            
            model.addRow(row);
        }    
    }
             public void Show_StaffTotals_In_JTable()
    {
        ArrayList<StaffTotals> list = getStaffTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_StaffTotals.getModel();
        // clear jtable content
        model2.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).gettotalcount();
            
            model2.addRow(row);
        }    
    }   
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_employeeid.setText(Integer.toString(getStaffList().get(index).getemployeeid()));
            txt_locationidFK.setText(Integer.toString(getStaffList().get(index).getlocationidFK()));           
            txt_firstname.setText(getStaffList().get(index).getfirstname());
            txt_lastname.setText(getStaffList().get(index).getlastname()); 
            txt_employeetitle.setText(getStaffList().get(index).getemployeetitle());
            txt_employeeaddress.setText(getStaffList().get(index).getemployeeaddress());
            
            txt_salary.setText(Double.toString(getStaffList().get(index).getsalary()));
            txt_username.setText(getStaffList().get(index).getusername());
            txt_password.setText(getStaffList().get(index).getpassword()); 
            lbl_staffphoto.setIcon(ResizeImage(null, getStaffList().get(index).getstaffphoto()));
            
    }       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog3 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog4 = new datechooser.beans.DateChooserDialog();
        jTextField1 = new javax.swing.JTextField();
        cinemaPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("cinemaPU").createEntityManager();
        mvLocationsQuery = java.beans.Beans.isDesignTime() ? null : cinemaPUEntityManager.createQuery("SELECT m FROM MvLocations m");
        mvLocationsList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : mvLocationsQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_firstname = new javax.swing.JTextField();
        txt_lastname = new javax.swing.JTextField();
        txt_employeeaddress = new javax.swing.JTextField();
        txt_employeetitle = new javax.swing.JTextField();
        txt_salary = new javax.swing.JTextField();
        txt_employeeid = new javax.swing.JTextField();
        lbl_staffphoto = new javax.swing.JLabel();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Staff = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        txt_username = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        combolocations = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_locationidFK = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_StaffTotals = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Employee ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Last Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Address:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Title:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Salary:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Photo");

        txt_firstname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_firstname.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_lastname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_lastname.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_employeeaddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_employeeaddress.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_employeetitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_employeetitle.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_salary.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_salary.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_employeeid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_employeeid.setPreferredSize(new java.awt.Dimension(160, 50));
        txt_employeeid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_employeeidActionPerformed(evt);
            }
        });

        lbl_staffphoto.setBackground(new java.awt.Color(204, 255, 255));
        lbl_staffphoto.setOpaque(true);

        JTable_Staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Title", "Username"
            }
        ));
        JTable_Staff.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_StaffMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Staff);

        Btn_Choose_Image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Choose_Image.setText("Choose Image");
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/add.png"))); // NOI18N
        btn_insert.setText("ADD NEW");
        btn_insert.setIconTextGap(15);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/update.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.setIconTextGap(15);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/minus.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setIconTextGap(15);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_first.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_first.setText("First");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_previous.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_previous.setText("Previous");
        btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previousActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_last.setText("Last");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        btn_new.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new.setText("NEW");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_username.setPreferredSize(new java.awt.Dimension(160, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Username:");

        txt_password.setText("jPasswordField1");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Password");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Select Location:");

        combolocations.setMaximumRowCount(10);
        combolocations.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combolocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combolocationsActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel14.setText("STAFF");

        txt_locationidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_locationidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Location ID:");

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Administration");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        JTable_StaffTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_StaffTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Total Employees"
            }
        ));
        jScrollPane1.setViewportView(JTable_StaffTotals);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(96, 96, 96)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(51, 51, 51))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(22, 22, 22))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addComponent(jLabel15))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_employeetitle, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_employeeaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(combolocations, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_locationidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(54, 54, 54)
                                .addComponent(txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(40, 40, 40)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_new)
                                .addComponent(txt_employeeid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_first))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(btn_next)
                                .addGap(84, 84, 84)
                                .addComponent(btn_previous)
                                .addGap(65, 65, 65)
                                .addComponent(btn_last)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_back)
                                .addGap(48, 48, 48))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl_staffphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(Btn_Choose_Image)
                                        .addGap(87, 87, 87))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(117, 117, 117)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btn_insert)
                                .addGap(88, 88, 88)
                                .addComponent(btn_update)
                                .addGap(69, 69, 69)
                                .addComponent(btn_delete)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_back)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_first)
                    .addComponent(btn_next)
                    .addComponent(btn_previous)
                    .addComponent(btn_last))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_staffphoto, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Choose_Image)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 66, Short.MAX_VALUE)
                .addComponent(btn_new)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_employeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combolocations, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_locationidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_employeetitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_employeeaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// Button Browse Image From Your Computer    
    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_staffphoto.setIcon(ResizeImage(path, null)); 
            ImgPath = path;
        }
        else
        {
            System.out.println("No File has been Selected");
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

// Button Insert Data Into JavaDB Database
// 1 - Check If the imgPath is Not NUll and the inputs are not empty
// 2- Insert the Data        
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
     
        if(checkInputs()  &&  ImgPath !=null)
        {
            try{
                    Connection con = getConnection();      
                    String sqlInsert = "INSERT INTO mv_staff" 
                            + "(locationid_fk, firstname, lastname, employeetitle, employeeaddress, salary, username, password,staffphoto)" 
                        + "values(?,?,?,?,?,?,?,?, ?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                    
                    ps.setString(1, txt_locationidFK.getText());
                 
                    ps.setString(2, txt_firstname.getText());
                    ps.setString(3, txt_lastname.getText()); 
                    ps.setString(4, txt_employeetitle.getText());
                    ps.setString(5, txt_employeeaddress.getText());                    
                                
                    ps.setDouble(6, Double.parseDouble(txt_salary.getText()));
                    ps.setString(7, txt_username.getText());
                    ps.setString(8, txt_password.getText());
                    
                    InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob (9, img);
                                    
                    ps.executeUpdate();
                    Show_Staff_In_JTable();
                    Show_StaffTotals_In_JTable();
         
                    JOptionPane.showMessageDialog(null,"New STAFF has been Created");
                    
                 }
                catch(Exception ex)  
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
            //Logger.getLogger(Movie_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ONLY FOR TESTING:
        //    System.out.println("Movie ID =>" + txt_movieid.getText());
        //    System.out.println("Movie Title => " + txt_movietitle.getText());
        //    System.out.println("Release Date => " + txt_releasedate.getDate());
        //    System.out.println("Rating => " + txt_rating.getText());
        //    System.out.println("Running Time => " + txt_runningtime.getText());
        //    System.out.println("Category => " + txt_category.getText());
        //    System.out.println("Director => " + txt_director.getText());
        //    System.out.println("Movie Cast => " + txt_moviecast.getText());
        //    System.out.println("Poster => " + ImgPath);
                    
            
            
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data From JavaDB database    
// 1 - Check if inputs are not null
// if the imgPath is not null UPDATE also the image
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_employeeid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            // Update without image
            if(ImgPath == null)
                    {
                        try {
                            sqlUpdate = "UPDATE mv_staff SET firstname = ?, lastname = ?, employeetitle = ?, employeeaddress = ?, salary = ?, username = ?, password = ?"
                                            + "WHERE employeeid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_firstname.getText());
                            ps.setString(2, txt_lastname.getText());
                            ps.setString(3, txt_employeetitle.getText());
                            ps.setString(4, txt_employeeaddress.getText());
                                                    
                            ps.setString(5, txt_salary.getText());
                            ps.setString(6, txt_username.getText());
                            ps.setString(7, txt_password.getText());
                            
                            ps.setInt(8, Integer.parseInt(txt_employeeid.getText()));
                            
                            ps.executeUpdate();
                            Show_Staff_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "EMPLOYEE RECORD has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
            
                    // Update with Image
            else{
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    
                            sqlUpdate = "UPDATE mv_staff SET firstname = ?, lastname = ?, employeetitle = ?, employeeaddress = ?, salary = ?, username = ?, password = ?"
                                            + ", staffphoto = ? WHERE employeeid = ?";
                            
                            ps=con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_firstname.getText());
                            ps.setString(2, txt_lastname.getText());
                            ps.setString(3, txt_employeetitle.getText());
                            ps.setString(4, txt_employeeaddress.getText());
                             

                            
                            ps.setString(5, txt_salary.getText());
                            ps.setString(6, txt_username.getText());
                            ps.setString(7, txt_password.getText());
              
                            ps.setBlob(8, img);
                            
                            ps.setInt(9, Integer.parseInt(txt_employeeid.getText()));
                            ps.executeUpdate();
                            Show_Staff_In_JTable();
                            
                            JOptionPane.showMessageDialog(null,"Staff Record has been updated");
                            
  
                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null,ex.getMessage());
                                Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_employeeid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_staff WHERE employeeid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int employeeid = Integer.parseInt(txt_employeeid.getText());
                ps.setInt(1, employeeid);
                ps.executeUpdate();
                Show_Staff_In_JTable();
                Show_StaffTotals_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Staff Record has been Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Staff Record Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"Staff Record Not Deleted: No Employee ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_StaffMouseClicked
  
        int index = JTable_Staff.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_StaffMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getStaffList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getStaffList().size())
        {
            pos = getStaffList().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previousActionPerformed
        pos--;
        if(pos<0)
        {
            pos=0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_previousActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        txt_employeeid.setText(null);
        txt_firstname.setText(null);
        txt_lastname.setText(null);
        txt_employeeaddress.setText(null);
        txt_employeetitle.setText(null);
        txt_username.setText(null);
        txt_password.setText(null);
        txt_salary.setText(null);
           ImgPath=null;     
                
        //txt_dob.setText(0000-00-00);
        txt_salary.setText(null);
    }//GEN-LAST:event_btn_newActionPerformed

    private void combolocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combolocationsActionPerformed
           
                   
        try { 
            if(this.contador>0) 
            {
            Statement st;
            ResultSet rs;                
                
            Connection con = getConnection();
            String sqlcombolocations = "SELECT * FROM mv_locations WHERE locationname= '"+this.combolocations.getSelectedItem()+"'";  
            st = con.createStatement();
            rs = st.executeQuery(sqlcombolocations);          
            rs.next();        
               this.txt_locationidFK.setText(String.valueOf(rs.getInt("locationid")));
             }
           
                
            }catch (SQLException ex){
                Logger.getLogger(Staff_Total2.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Selection not VALID");
            }
        
    }//GEN-LAST:event_combolocationsActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        mv_staff.administrators Info = new mv_staff.administrators();
        Info.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void txt_employeeidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_employeeidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_employeeidActionPerformed

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
            java.util.logging.Logger.getLogger(Staff_Total2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff_Total2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff_Total2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff_Total2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff_Total2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Staff;
    private javax.swing.JTable JTable_StaffTotals;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btn_update;
    private javax.persistence.EntityManager cinemaPUEntityManager;
    private javax.swing.JComboBox<String> combolocations;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private datechooser.beans.DateChooserDialog dateChooserDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_staffphoto;
    private java.util.List<mv_staff.MvLocations> mvLocationsList;
    private javax.persistence.Query mvLocationsQuery;
    private javax.swing.JTextField txt_employeeaddress;
    private javax.swing.JTextField txt_employeeid;
    private javax.swing.JTextField txt_employeetitle;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JTextField txt_locationidFK;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_salary;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
