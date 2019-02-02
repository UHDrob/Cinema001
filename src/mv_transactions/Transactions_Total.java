/*
 * Cinema Management System
 * TRANSACTION Section
 * Administration Level
 */
package mv_transactions;

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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roberto Gomez
 */
public class Transactions_Total extends javax.swing.JFrame {

    /**
     * Creates new form Movie_Window
     */
    public Transactions_Total() {
        initComponents();
        Show_Transactions_In_JTable();
        Show_TransactionsTotals_In_JTable();
        
        
    }

    String ImgPath = null;
    int pos = 0;
    
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
            Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(        txt_transactionid.getText() == null
                || txt_transactiondate.getDate()== null
                || txt_transactiontime.getText() == null)
         
        {
        return false;
        }
        else
        {
            try{
                //Float.parseFloat(txt_.getText());                
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }        
    }
      
    // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    public ArrayList<Transactions> getTransactionsList()
    {
            ArrayList<Transactions> transactionsList  = new ArrayList<Transactions>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_transactions ORDER BY transactiondate DESC";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Transactions transactions;
            
            while(rs.next())
            {
                transactions = new Transactions(rs.getInt("transactionid"),rs.getString("transactiondate"),
                        rs.getString("transactiontime"),rs.getInt("promotionid_fk"),
                        rs.getInt("showtimeid_fk"), rs.getInt("paymentid_fk"),
                        rs.getInt("locationid_fk"), rs.getInt("orderid_fk"));
                transactionsList.add(transactions);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return transactionsList;                 
    }   
              public ArrayList<TransactionsTotals> getTransactionsTotalsList()
    {
            ArrayList<TransactionsTotals> transactionstotalsList  = new ArrayList<TransactionsTotals>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (transactionid) As Total_Transactions\n" +
                         "FROM cinema.mv_transactions";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            TransactionsTotals transactionstotals;
            
            while(rs.next())
            {
                transactionstotals = new TransactionsTotals(rs.getInt("Total_Transactions"));
 //               JOptionPane.message()
                transactionstotalsList.add(transactionstotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
        return transactionstotalsList;                 
    }
    //      2 - Populate The JTable    
    public void Show_Transactions_In_JTable()
    {
        ArrayList<Transactions> list = getTransactionsList();
        DefaultTableModel model = (DefaultTableModel)JTable_Transactions.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).gettransactionid();
            row[1] = list.get(i).gettransactiondate();
            row[2] = list.get(i).gettransactiontime();
            row[3] = list.get(i).getshowtimeidfk();
            
            model.addRow(row);
        }    
    }
          public void Show_TransactionsTotals_In_JTable()
    {
        ArrayList<TransactionsTotals> list = getTransactionsTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_TransactionsTotals.getModel();
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
            txt_transactionid.setText(Integer.toString(getTransactionsList().get(index).gettransactionid()));

        try {
           Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getTransactionsList().get(index).gettransactiondate());
            txt_transactiondate.setDate(addDate);
 
        } catch (ParseException ex) {
            Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
            txt_transactiontime.setText(getTransactionsList().get(index).gettransactiontime());
            txt_promotionidFK.setText(Integer.toString(getTransactionsList().get(index).getpromotionidfk()));
            txt_showtimeidFK.setText(Integer.toString(getTransactionsList().get(index).getshowtimeidfk()));
            txt_paymentidFK.setText(Integer.toString(getTransactionsList().get(index).getpaymentidfk()));
            txt_locationidFK.setText(Integer.toString(getTransactionsList().get(index).getlocationidfk()));
            txt_orderidFK.setText(Integer.toString(getTransactionsList().get(index).getorderidfk()));    
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_transactiontime = new javax.swing.JTextField();
        txt_showtimeidFK = new javax.swing.JTextField();
        txt_promotionidFK = new javax.swing.JTextField();
        txt_locationidFK = new javax.swing.JTextField();
        txt_transactionid = new javax.swing.JTextField();
        txt_paymentidFK = new javax.swing.JTextField();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Transactions = new javax.swing.JTable();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_transactiondate = new com.toedter.calendar.JDateChooser();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_orderidFK = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_TransactionsTotals = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Transaction ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Transaction Date:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Transaction Time:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Showtime ID");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Promotion ID");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Payment ID");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Location ID");

        txt_transactiontime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_transactiontime.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_showtimeidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_showtimeidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_promotionidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_promotionidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_locationidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_locationidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_transactionid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_transactionid.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_paymentidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_paymentidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        JTable_Transactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Transaction Date", "Transaction Time", "Showtime"
            }
        ));
        JTable_Transactions.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Transactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_TransactionsMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Transactions);

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

        txt_transactiondate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel10.setText("TRANSACTIONS");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Order ID");

        txt_orderidFK.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_orderidFK.setPreferredSize(new java.awt.Dimension(160, 50));

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Administration");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        JTable_TransactionsTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_TransactionsTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Total Transactions"
            }
        ));
        jScrollPane1.setViewportView(JTable_TransactionsTotals);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_transactionid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_promotionidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transactiontime, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_transactiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_showtimeidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_paymentidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_locationidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_new)
                    .addComponent(txt_orderidFK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(btn_first)
                        .addGap(69, 69, 69)
                        .addComponent(btn_next)
                        .addGap(86, 86, 86)
                        .addComponent(btn_previous)
                        .addGap(67, 67, 67)
                        .addComponent(btn_last))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btn_back)))
                .addGap(458, 458, 458))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(595, 595, 595)
                .addComponent(btn_insert)
                .addGap(77, 77, 77)
                .addComponent(btn_update)
                .addGap(53, 53, 53)
                .addComponent(btn_delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btn_new)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_transactionid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addComponent(txt_transactiondate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jLabel3)
                                .addGap(36, 36, 36)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_transactiontime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_promotionidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_showtimeidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_paymentidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_locationidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_orderidFK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_back)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first)
                            .addComponent(btn_next)
                            .addComponent(btn_previous)
                            .addComponent(btn_last))
                        .addGap(18, 18, 18)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_update)
                    .addComponent(btn_delete))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// Button Insert Data Into JavaDB Database
// 1 - Check If the imgPath is Not NUll and the inouts are not empty
// 2- Insert the Data        
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        if(checkInputs() )
        {
            try{
                    Connection con = getConnection();      
                    String sqlInsert = "INSERT INTO mv_transactions" 
                            + "(transactiondate, transactiontime, promotionid_fk, showtimeid_fk, paymentid_fk, locationid_fk, orderid_fk)" 
                        + "values(?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_transactiondate.getDate());
                    ps.setString(1, addDate);
                    
                    ps.setString(2, txt_transactiontime.getText());
                    ps.setInt(3, Integer.parseInt(txt_promotionidFK.getText()));
                    ps.setInt(4, Integer.parseInt(txt_showtimeidFK.getText()));                    
                    ps.setInt(5, Integer.parseInt(txt_paymentidFK.getText()));
                    ps.setInt(6, Integer.parseInt(txt_locationidFK.getText()));
                    ps.setInt(7, Integer.parseInt(txt_orderidFK.getText()));

                                    
                    ps.executeUpdate();
                    Show_Transactions_In_JTable();
                    Show_TransactionsTotals_In_JTable();
                    
                    JOptionPane.showMessageDialog(null,"New Transaction has been Created");
                    
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
            System.out.println("Transaction ID =>" + txt_transactionid.getText());
;
            System.out.println("Transaction Date => " + txt_transactiondate.getDate());
            System.out.println("Transaction Time => " + txt_transactiontime.getText());
            
            System.out.println("Promotion ID FK => " + txt_promotionidFK.getText());
            System.out.println("Showtime ID FK => " + txt_showtimeidFK.getText());
            
            System.out.println("Payment ID FK => " + txt_paymentidFK.getText());
            System.out.println("Location ID FKt => " + txt_locationidFK.getText());
            System.out.println("Order ID FK => " + txt_orderidFK);
        
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data From JavaDB database    
// 1 - Check if inputs are not null
// if the imgPath is not null UPDATE also the image
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_transactionid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            // Update without image
            if(ImgPath == null)
                    {
                        try {
                            sqlUpdate = "UPDATE mv_transactions SET transactiondate = ?, transactiontime = ?, promotionid_fk = ?, showtimeid_fk = ?, paymentid_fk = ?, locationid_fk =?, orderid_fk = ?"
                                            + "WHERE transactionid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String addDate = dateFormat.format(txt_transactiondate.getDate());
                            ps.setString(1, addDate);                            
                            ps.setString(2, txt_transactiontime.getText());
                            ps.setInt(3, Integer.parseInt(txt_promotionidFK.getText()));                           
                            ps.setInt(4, Integer.parseInt(txt_showtimeidFK.getText()));

                            ps.setInt(5, Integer.parseInt(txt_paymentidFK.getText()));
                            ps.setInt(7, Integer.parseInt(txt_locationidFK.getText()));
                            
                            ps.setInt(8, Integer.parseInt(txt_orderidFK.getText()));
                            
                            ps.executeUpdate();
                            Show_Transactions_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "Transactions has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
            

            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_transactionid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_transactions WHERE transactionid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int movieid = Integer.parseInt(txt_transactionid.getText());
                ps.setInt(1, movieid);
                ps.executeUpdate();
                Show_Transactions_In_JTable();
                Show_TransactionsTotals_In_JTable();
                
                
                JOptionPane.showMessageDialog(null, "Transaction Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Transactions_Total.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Transaction Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"Transaction Not Deleted: No Transaction ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_TransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_TransactionsMouseClicked
  
        int index = JTable_Transactions.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_TransactionsMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getTransactionsList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getTransactionsList().size())
        {
            pos = getTransactionsList().size()-1;
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
        txt_transactionid.setText(null);
        txt_transactiondate.setDate(null);
        txt_transactiontime.setText(null);
        txt_promotionidFK.setText(null);        
        txt_showtimeidFK.setText(null);
        txt_paymentidFK.setText(null);
        txt_locationidFK.setText(null);
        txt_orderidFK.setText(null);        
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        mv_staff.administrators Info = new mv_staff.administrators();
        Info.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(Transactions_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transactions_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transactions_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transactions_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transactions_Total().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Transactions;
    private javax.swing.JTable JTable_TransactionsTotals;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btn_update;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private datechooser.beans.DateChooserDialog dateChooserDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_locationidFK;
    private javax.swing.JTextField txt_orderidFK;
    private javax.swing.JTextField txt_paymentidFK;
    private javax.swing.JTextField txt_promotionidFK;
    private javax.swing.JTextField txt_showtimeidFK;
    private com.toedter.calendar.JDateChooser txt_transactiondate;
    private javax.swing.JTextField txt_transactionid;
    private javax.swing.JTextField txt_transactiontime;
    // End of variables declaration//GEN-END:variables
}
