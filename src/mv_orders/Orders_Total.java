/*
 * Cinema Management System
 * ORDERS Section
 * Administration Level
 */
package mv_orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roberto Gomez
 */
public class Orders_Total extends javax.swing.JFrame {

    /**
     * Creates new form Movie_Window
     */
    public Orders_Total() {
        initComponents();
        Show_Orders_In_JTable();
        Show_OrdersTotals_In_JTable();

    }

    String ImgPath = null;
    int pos = 0;
    int contador = 0;
    
  
  // Connect to JavaDB Database  
    public Connection getConnection()
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/cinema","cinema","cinemalogin");      
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Orders_Total.class.getName()).log(Level.SEVERE, null, ex);
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(       
                 txt_qtyadult.getText() == null
                || txt_qtychild.getText() == null
                || txt_qtysenior.getText() == null)
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
    public ArrayList<Orders> getOrdersList()
    {
            ArrayList<Orders> ordersList  = new ArrayList<Orders>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_orders";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Orders orders;
            
            while(rs.next())
            {
                orders = new Orders (rs.getInt("orderid"),rs.getInt("qty_adult"),
                        rs.getInt("qty_child"),rs.getInt("qty_senior"), rs.getInt("ticketid_fk"));
                ordersList.add(orders);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Orders_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ordersList;                 
    }   
          public ArrayList<OrdersTotals> getOrdersTotalsList()
    {
            ArrayList<OrdersTotals> orderstotalsList  = new ArrayList<OrdersTotals>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (orderid) As Total_Orders\n" +
                         "FROM cinema.mv_orders";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            OrdersTotals orderstotals;
            
            while(rs.next())
            {
                orderstotals = new OrdersTotals(rs.getInt("Total_Orders"));
                orderstotalsList.add(orderstotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Orders_Total.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
        return orderstotalsList;                 
    }    
    //      2 - Populate The JTable    
    public void Show_Orders_In_JTable()
    {
        ArrayList<Orders> list = getOrdersList();
        DefaultTableModel model = (DefaultTableModel)JTable_Orders.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getorderid();
            row[1] = list.get(i).getqtyadult();
            row[2] = list.get(i).getqtychild();
            row[3] = list.get(i).getqtysenior();
            row[4] = list.get(i).getticketidfk();
            
            model.addRow(row);
        }    
    }
          public void Show_OrdersTotals_In_JTable()
    {
        ArrayList<OrdersTotals> list = getOrdersTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_OrdersTotals.getModel();
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
            txt_orderid.setText(Integer.toString(getOrdersList().get(index).getorderid()));
            txt_qtyadult.setText(Integer.toString(getOrdersList().get(index).getqtyadult()));
            txt_qtychild.setText(Integer.toString(getOrdersList().get(index).getqtychild()));
            txt_qtysenior.setText(Integer.toString(getOrdersList().get(index).getqtysenior()));
            txt_ticketidfk.setText(Integer.toString(getOrdersList().get(index).getticketidfk()));

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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_qtyadult = new javax.swing.JTextField();
        txt_qtychild = new javax.swing.JTextField();
        txt_qtysenior = new javax.swing.JTextField();
        txt_orderid = new javax.swing.JTextField();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Orders = new javax.swing.JTable();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_first1 = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_ticketidfk = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_OrdersTotals = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Order ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Qty (Adults):");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Qty (Child):");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Qty (Senior):");

        txt_qtyadult.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qtyadult.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_qtychild.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qtychild.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_qtysenior.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_qtysenior.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_orderid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_orderid.setPreferredSize(new java.awt.Dimension(160, 50));

        JTable_Orders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Qty (Adult)", "Qty (Child)", "Qty (Senior)", "Ticket ID"
            }
        ));
        JTable_Orders.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Orders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_OrdersMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Orders);

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel3.setText("ORDERS");

        btn_first1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_first1.setText("New");
        btn_first1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_first1ActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Administration");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Ticket ID");

        txt_ticketidfk.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_ticketidfk.setPreferredSize(new java.awt.Dimension(160, 50));

        JTable_OrdersTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_OrdersTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Total Orders"
            }
        ));
        jScrollPane1.setViewportView(JTable_OrdersTotals);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_qtysenior, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_qtychild, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(35, 35, 35)
                                        .addComponent(txt_qtyadult, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_first1)
                                            .addComponent(txt_orderid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_ticketidfk, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPanel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(btn_first)
                                .addGap(18, 18, 18)
                                .addComponent(btn_next)
                                .addGap(18, 18, 18)
                                .addComponent(btn_previous)
                                .addGap(18, 18, 18)
                                .addComponent(btn_last))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btn_insert)
                                .addGap(36, 36, 36)
                                .addComponent(btn_update)
                                .addGap(31, 31, 31)
                                .addComponent(btn_delete)))
                        .addGap(0, 335, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310)
                .addComponent(btn_back)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_back))
                        .addGap(69, 69, 69)
                        .addComponent(btn_first1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_orderid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_qtyadult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_qtychild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_qtysenior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(113, 113, 113)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ticketidfk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_next)
                            .addComponent(btn_first)
                            .addComponent(btn_previous)
                            .addComponent(btn_last))
                        .addGap(7, 7, 7)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_update)
                            .addComponent(btn_delete)
                            .addComponent(btn_insert))))
                .addGap(183, 183, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
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
                    String sqlInsert = "INSERT INTO mv_orders" 
                            + "(qty_adult, qty_senior, ticketid_fk, qty_child)" 
                        + "values(?,?,?, ?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                                        
                    ps.setString(1, txt_qtyadult.getText());
                    ps.setString(2, txt_qtysenior.getText());
                    ps.setString(3, txt_ticketidfk.getText());
                    ps.setString(4, txt_qtychild.getText());
                                    
                    ps.executeUpdate();
                    Show_Orders_In_JTable();
                    Show_OrdersTotals_In_JTable();
                    
                    JOptionPane.showMessageDialog(null,"New ORDER has been Created");
                    
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
            System.out.println("Order ID =>" + txt_orderid.getText());
            System.out.println("Qty Adult => " + txt_qtyadult.getText());
            System.out.println("Qty Child => " + txt_qtychild.getText());
            System.out.println("Qty Senior => " + txt_qtysenior.getText());
            System.out.println("Ticket ID FK => " + txt_ticketidfk.getText());
        
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data From JavaDB database    
// 1 - Check if inputs are not null
// if the imgPath is not null UPDATE also the image
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_orderid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
                        try {
                            sqlUpdate = "UPDATE mv_orders SET qty_adult = ?, qty_senior = ?, ticketid_fk = ?, qty_child = ?"
                                            + "WHERE orderid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_qtyadult.getText());
                            ps.setString(2, txt_qtychild.getText());
                            ps.setString(3, txt_qtysenior.getText());
                            ps.setInt(4, Integer.parseInt(txt_orderid.getText()));
                            
                            ps.executeUpdate();
                            Show_Orders_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "ORDER has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Orders_Total.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    


            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_orderid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_orders WHERE orderid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int orderid = Integer.parseInt(txt_orderid.getText());
                ps.setInt(1, orderid);
                ps.executeUpdate();
                Show_Orders_In_JTable();
                Show_OrdersTotals_In_JTable();
                
                JOptionPane.showMessageDialog(null, "ORDER has been Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Orders_Total.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"ORDER Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"ORDER Not Deleted: No Order ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_OrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_OrdersMouseClicked
  
        int index = JTable_Orders.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_OrdersMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getOrdersList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getOrdersList().size())
        {
            pos = getOrdersList().size()-1;
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

    private void btn_first1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_first1ActionPerformed
        txt_orderid.setText(null);
        txt_qtyadult.setText(null);
        txt_qtychild.setText(null);
        txt_qtysenior.setText(null);

    }//GEN-LAST:event_btn_first1ActionPerformed

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
            java.util.logging.Logger.getLogger(Orders_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Orders_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Orders_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Orders_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Orders_Total().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Orders;
    private javax.swing.JTable JTable_OrdersTotals;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_first1;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btn_update;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private datechooser.beans.DateChooserDialog dateChooserDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_orderid;
    private javax.swing.JTextField txt_qtyadult;
    private javax.swing.JTextField txt_qtychild;
    private javax.swing.JTextField txt_qtysenior;
    private javax.swing.JTextField txt_ticketidfk;
    // End of variables declaration//GEN-END:variables
}
