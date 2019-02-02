/*
 * Cinema Management System
 * Roomn Section
 * Administration Level
 */
package mv_rooms;

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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roberto Gomez
 */
public class Rooms_Total extends javax.swing.JFrame {

    /**
     * Creates new form Movie_Window
     */
    public Rooms_Total() {
        initComponents();
        Show_Rooms_In_JTable();
        Show_RoomsTotals_In_JTable();
    }

    String ImgPath = null;
    int pos = 0;
    
  // Connect to JavaDB Database  
    public Connection getConnection()
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/cinema","cinema","cinemalogin");      
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Rooms_Total.class.getName()).log(Level.SEVERE, null, ex);
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(       
                 txt_capacity.getText() == null
                || txt_roomtype.getText() == null
                || txt_size.getText() == null)
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
    public ArrayList<Rooms> getRoomsList()
    {
            ArrayList<Rooms> roomsList  = new ArrayList<Rooms>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_room ORDER BY roomtype";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Rooms rooms;
            
            while(rs.next())
            {
                rooms = new Rooms (rs.getInt("roomid"),rs.getString("capacity"),
                        rs.getString("roomtype"),rs.getString("size"));
                roomsList.add(rooms);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Rooms_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return roomsList;                 
    }   
          public ArrayList<RoomsTotals> getRoomsTotalsList()
    {
            ArrayList<RoomsTotals> roomstotalsList  = new ArrayList<RoomsTotals>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (roomid) As Total_Rooms\n" +
                         "FROM cinema.mv_room";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            RoomsTotals roomstotals;
            
            while(rs.next())
            {
                roomstotals = new RoomsTotals(rs.getInt("Total_Rooms"));
 //               JOptionPane.message()
                roomstotalsList.add(roomstotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Rooms_Total.class.getName()).log(Level.SEVERE, null, ex);
        }  
           
        return roomstotalsList;                 
    }
    //      2 - Populate The JTable    
    public void Show_Rooms_In_JTable()
    {
        ArrayList<Rooms> list = getRoomsList();
        DefaultTableModel model = (DefaultTableModel)JTable_Rooms.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getroomid();
            row[1] = list.get(i).getcapacity();
            row[2] = list.get(i).getroomtype();
            row[3] = list.get(i).getsize();
            
            model.addRow(row);
        }    
    }
         public void Show_RoomsTotals_In_JTable()
    {
        ArrayList<RoomsTotals> list = getRoomsTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_RoomsTotals.getModel();
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
            txt_roomid.setText(Integer.toString(getRoomsList().get(index).getroomid()));
            txt_capacity.setText(getRoomsList().get(index).getcapacity());
            txt_roomtype.setText(getRoomsList().get(index).getroomtype());
            txt_size.setText(getRoomsList().get(index).getsize());

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
        txt_capacity = new javax.swing.JTextField();
        txt_roomtype = new javax.swing.JTextField();
        txt_size = new javax.swing.JTextField();
        txt_roomid = new javax.swing.JTextField();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Rooms = new javax.swing.JTable();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_new = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_RoomsTotals = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Room ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Capacity:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Room Type:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Size:");

        txt_capacity.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_capacity.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_roomtype.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_roomtype.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_size.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_size.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_roomid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_roomid.setPreferredSize(new java.awt.Dimension(160, 50));

        JTable_Rooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Capacity", "Room Type", "Size"
            }
        ));
        JTable_Rooms.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Rooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_RoomsMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Rooms);

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
        jLabel3.setText("ROOMS");

        btn_new.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Administration");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        JTable_RoomsTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_RoomsTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Total Rooms"
            }
        ));
        jScrollPane1.setViewportView(JTable_RoomsTotals);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(35, 35, 35)
                            .addComponent(txt_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_new)
                                .addComponent(txt_roomid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_size, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_roomtype, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(btn_insert)
                        .addGap(36, 36, 36)
                        .addComponent(btn_update)
                        .addGap(31, 31, 31)
                        .addComponent(btn_delete)
                        .addContainerGap(459, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(btn_first)
                        .addGap(39, 39, 39)
                        .addComponent(btn_next)
                        .addGap(35, 35, 35)
                        .addComponent(btn_previous)
                        .addGap(34, 34, 34)
                        .addComponent(btn_last)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(JScrollPanel)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_back))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btn_new)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_roomid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_capacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_roomtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first)
                            .addComponent(btn_next)
                            .addComponent(btn_previous)
                            .addComponent(btn_last))
                        .addGap(18, 18, 18)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_update)
                            .addComponent(btn_delete)
                            .addComponent(btn_insert))))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1484, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    String sqlInsert = "INSERT INTO mv_room" 
                            + "(capacity, roomtype, size)" 
                        + "values(?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                    
                    
                    ps.setString(1, txt_capacity.getText());

                   
                    ps.setString(2, txt_roomtype.getText());

                    ps.setString(3, txt_size.getText());


                                    
                    ps.executeUpdate();
                    Show_Rooms_In_JTable();
                    Show_RoomsTotals_In_JTable();
                    
                    JOptionPane.showMessageDialog(null,"New ROOM has been Created");
                    
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
            System.out.println("Room ID =>" + txt_roomid.getText());
            System.out.println("Capacity => " + txt_capacity.getText());

            System.out.println("Rating => " + txt_roomtype.getText());

            System.out.println("Category => " + txt_size.getText());

        
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data From JavaDB database    
// 1 - Check if inputs are not null
// if the imgPath is not null UPDATE also the image
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_roomid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            

                        try {
                            sqlUpdate = "UPDATE mv_room SET capacity = ?, roomtype = ?, size = ?"
                                            + "WHERE roomid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_capacity.getText());

                            
                            ps.setString(2, txt_roomtype.getText());
 
                            ps.setString(3, txt_size.getText());
 
                            ps.setInt(4, Integer.parseInt(txt_roomid.getText()));
                            
                            ps.executeUpdate();
                            Show_Rooms_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "Room has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Rooms_Total.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    


            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_roomid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_room WHERE roomid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int roomid = Integer.parseInt(txt_roomid.getText());
                ps.setInt(1, roomid);
                ps.executeUpdate();
                Show_Rooms_In_JTable();
                Show_RoomsTotals_In_JTable();
 //       txt_roomid.setText(null);
        txt_capacity.setText(null);
        txt_roomtype.setText(null);
        txt_size.setText(null);
                               
                JOptionPane.showMessageDialog(null, "Room has been Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Rooms_Total.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Room Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"Room Not Deleted: No Room ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_RoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_RoomsMouseClicked
  
        int index = JTable_Rooms.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_RoomsMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getRoomsList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getRoomsList().size())
        {
            pos = getRoomsList().size()-1;
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
        txt_roomid.setText(null);
        txt_capacity.setText(null);
        txt_roomtype.setText(null);
        txt_size.setText(null);

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
            java.util.logging.Logger.getLogger(Rooms_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Rooms_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Rooms_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Rooms_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Rooms_Total().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Rooms;
    private javax.swing.JTable JTable_RoomsTotals;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txt_capacity;
    private javax.swing.JTextField txt_roomid;
    private javax.swing.JTextField txt_roomtype;
    private javax.swing.JTextField txt_size;
    // End of variables declaration//GEN-END:variables
}
