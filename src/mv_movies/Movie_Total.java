/*
 * Cinema Management System
 * Movies Section
 * Administration Level
 */
package mv_movies;

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
public class Movie_Total extends javax.swing.JFrame {

    /**
     * Creates new form Movie_Window
     */
    public Movie_Total() {
        initComponents();
        Show_Movies_In_JTable();
        Show_MoviesTotals_In_JTable();
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
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/cinema","cinema","cinemalogin");
            con = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(        txt_movietitle.getText() == null
                || txt_releasedate.getDate()== null
                || txt_rating.getText() == null
                || txt_category.getText() == null)

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
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        
        return image;        
    }
     
    // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    public ArrayList<Movies> getMoviesList()
    {
            ArrayList<Movies> moviesList  = new ArrayList<Movies>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_movies ORDER by movietitle";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Movies movies;
            
            while(rs.next())
            {
                movies = new Movies(rs.getInt("movieid"),rs.getString("movietitle"),
                        rs.getString("releasedate"),rs.getString("rating"),
                        rs.getString("category"), rs.getString("runningtime"),
                        rs.getString("director"), rs.getString("moviecast"),
                        rs.getBytes("poster"));
                moviesList.add(movies);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return moviesList;                 
    }   
        public ArrayList<MoviesTotals> getMoviesTotalsList()
    {
            ArrayList<MoviesTotals> moviestotalsList  = new ArrayList<MoviesTotals>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (movieid) As Total_Movies\n" +
                         "FROM cinema.mv_movies";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            MoviesTotals moviestotals;
            
            while(rs.next())
            {
                moviestotals = new MoviesTotals(rs.getInt("Total_Movies"));
 //               JOptionPane.message()
                moviestotalsList.add(moviestotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return moviestotalsList;                 
    }   
    //      2 - Populate The JTable    
    public void Show_Movies_In_JTable()
    {
        ArrayList<Movies> list = getMoviesList();
        DefaultTableModel model = (DefaultTableModel)JTable_Movies.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getmovieid();
            row[1] = list.get(i).getmovietitle();
            row[2] = list.get(i).getreleasedate();
            row[3] = list.get(i).getrating();
            
            model.addRow(row);
        }    
    }
        public void Show_MoviesTotals_In_JTable()
    {
        ArrayList<MoviesTotals> list = getMoviesTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_MoviesTotals.getModel();
        // clear jtable content
        model2.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).gettotalcount();
 //           row[1] = list.get(i).getmovietitle();
 //           row[2] = list.get(i).getreleasedate();
 //           row[3] = list.get(i).getrating();
            
            model2.addRow(row);
        }    
    }
    
    
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_movieid.setText(Integer.toString(getMoviesList().get(index).getmovieid()));
            txt_movietitle.setText(getMoviesList().get(index).getmovietitle());
            
        try {
           Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getMoviesList().get(index).getreleasedate());
            txt_releasedate.setDate(addDate);
 
        } catch (ParseException ex) {
            Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
            txt_rating.setText(getMoviesList().get(index).getrating());
            txt_category.setText(getMoviesList().get(index).getcategory());
            txt_runningtime.setText(getMoviesList().get(index).getrunningtime());
            txt_director.setText(getMoviesList().get(index).getdirector());
            txt_moviecast.setText(getMoviesList().get(index).getmoviecast());
            
        lbl_image.setIcon(ResizeImage(null, getMoviesList().get(index).getImage()));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_movietitle = new javax.swing.JTextField();
        txt_rating = new javax.swing.JTextField();
        txt_runningtime = new javax.swing.JTextField();
        txt_category = new javax.swing.JTextField();
        txt_moviecast = new javax.swing.JTextField();
        txt_movieid = new javax.swing.JTextField();
        txt_director = new javax.swing.JTextField();
        lbl_image = new javax.swing.JLabel();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Movies = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_releasedate = new com.toedter.calendar.JDateChooser();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_MoviesTotals = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Movie ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Movie Title:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Release Date:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Rating:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Running Time:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Category");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Director:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Movie Cast:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Poster:");

        txt_movietitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_movietitle.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_rating.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_rating.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_runningtime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_runningtime.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_category.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_category.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_moviecast.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_moviecast.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_movieid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_movieid.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_director.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_director.setPreferredSize(new java.awt.Dimension(160, 50));

        lbl_image.setBackground(new java.awt.Color(204, 255, 255));
        lbl_image.setOpaque(true);

        JTable_Movies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "movieid", "movietitle", "releasedate", "rating"
            }
        ));
        JTable_Movies.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Movies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_MoviesMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Movies);
        if (JTable_Movies.getColumnModel().getColumnCount() > 0) {
            JTable_Movies.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

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

        txt_releasedate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Administration");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel10.setText("MOVIES");

        JTable_MoviesTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_MoviesTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "TOTAL MOVIES"
            }
        ));
        jScrollPane1.setViewportView(JTable_MoviesTotals);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel3)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(Btn_Choose_Image)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_movietitle, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_rating, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_runningtime, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(111, 111, 111))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(115, 115, 115)))
                                .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_movieid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_new))
                                        .addGap(124, 124, 124)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(152, 152, 152))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_releasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(251, 251, 251)
                                        .addComponent(btn_first)
                                        .addGap(71, 71, 71)
                                        .addComponent(btn_next)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(btn_previous)
                                        .addGap(100, 100, 100)
                                        .addComponent(btn_last))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(btn_back))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_moviecast, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_director, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(497, 497, 497)
                                .addComponent(btn_insert)
                                .addGap(111, 111, 111)
                                .addComponent(btn_update)
                                .addGap(120, 120, 120)
                                .addComponent(btn_delete)))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_new)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_movieid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txt_movietitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_back))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_releasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_runningtime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_director, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_moviecast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel9)
                                .addGap(55, 55, 55)
                                .addComponent(Btn_Choose_Image))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first)
                            .addComponent(btn_next)
                            .addComponent(btn_previous)
                            .addComponent(btn_last))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insert)
                            .addComponent(btn_update)
                            .addComponent(btn_delete))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            lbl_image.setIcon(ResizeImage(path, null)); 
            ImgPath = path;
        }
        else
        {
            System.out.println("No File has been Selected");
        }
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

// Button Insert Data Into JavaDB Database
// 1 - Check If the imgPath is Not NUll and the inouts are not empty
// 2- Insert the Data        
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        if(checkInputs()  &&  ImgPath !=null)
        {
            try{
                    Connection con = getConnection();      
                    String sqlInsert = "INSERT INTO mv_movies" 
                            + "(movietitle, releasedate, rating, category, runningtime, director, moviecast, poster)" 
                        + "values(?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                    
                    ps.setString(1, txt_movietitle.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_releasedate.getDate());
                    ps.setString(2, addDate);
                   
                    ps.setString(3, txt_rating.getText());
                    ps.setString(4, txt_runningtime.getText());
                    ps.setString(5, txt_category.getText());
                    ps.setString(6, txt_director.getText());
                    ps.setString(7, txt_moviecast.getText());
                    
                    InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob (8, img);
                                    
                    ps.executeUpdate();
                    Show_Movies_In_JTable();
                    Show_MoviesTotals_In_JTable();
         
                    JOptionPane.showMessageDialog(null,"New Movie has been Created");
                    
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
            System.out.println("Movie ID =>" + txt_movieid.getText());
            System.out.println("Movie Title => " + txt_movietitle.getText());
            System.out.println("Release Date => " + txt_releasedate.getDate());
            System.out.println("Rating => " + txt_rating.getText());
            System.out.println("Running Time => " + txt_runningtime.getText());
            System.out.println("Category => " + txt_category.getText());
            System.out.println("Director => " + txt_director.getText());
            System.out.println("Movie Cast => " + txt_moviecast.getText());
            System.out.println("Poster => " + ImgPath);
        
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data From JavaDB database    
// 1 - Check if inputs are not null
// if the imgPath is not null UPDATE also the image
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_movieid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            // Update without image
            if(ImgPath == null)
                    {
                        try {
                            sqlUpdate = "UPDATE mv_movies SET movietitle = ?, releasedate = ?, rating = ?, runningtime = ?, category = ?, director =?, moviecast = ?"
                                            + "WHERE movieid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_movietitle.getText());
                            
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String addDate = dateFormat.format(txt_releasedate.getDate());
                            ps.setString(2, addDate);
                            
                            ps.setString(3, txt_rating.getText());
                            ps.setString(4, txt_runningtime.getText());
                            ps.setString(5, txt_category.getText());
                            ps.setString(6, txt_director.getText());
                            ps.setString(7, txt_moviecast.getText());
                            
                            ps.setInt(8, Integer.parseInt(txt_movieid.getText()));
                            
                            ps.executeUpdate();
                            Show_Movies_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "Movie has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
            
                    // Update with Image
            else{
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    
                            sqlUpdate = "UPDATE mv_movies SET movietitle = ?, releasedate = ?, rating = ?, runningtime = ?, category = ?, director =?, moviecast = ?"
                                            + ", poster = ? WHERE movieid = ?";
                            
                            ps=con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_movietitle.getText());
                            
                            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                            String addDate = dateFormat.format(txt_releasedate.getDate());
                            ps.setString(2, addDate);
                           
                            ps.setString(3, txt_rating.getText());
                            ps.setString(4, txt_runningtime.getText());
                            ps.setString(5, txt_category.getText());
                            ps.setString(6, txt_director.getText());
                            ps.setString(7, txt_moviecast.getText());
                            ps.setBlob(8, img);
                            
                            ps.setInt(9, Integer.parseInt(txt_movieid.getText()));
                            ps.executeUpdate();
                            Show_Movies_In_JTable();
                            
                            JOptionPane.showMessageDialog(null,"Movie has been updated");
                            
  
                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null,ex.getMessage());
                                Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_movieid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_movies WHERE movieid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int movieid = Integer.parseInt(txt_movieid.getText());
                ps.setInt(1, movieid);
                ps.executeUpdate();
                Show_Movies_In_JTable();
                Show_MoviesTotals_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Movie_Total.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Product Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"Product Not Deleted: No Movie ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_MoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_MoviesMouseClicked
  
        int index = JTable_Movies.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_MoviesMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getMoviesList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getMoviesList().size())
        {
            pos = getMoviesList().size()-1;
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
        txt_movieid.setText(null);
        txt_movietitle.setText(null);
        txt_releasedate.setDate(null);
        txt_rating.setText(null);
        txt_runningtime.setText(null);
        txt_category.setText(null);
        txt_director.setText(null);
        txt_moviecast.setText(null);
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
            java.util.logging.Logger.getLogger(Movie_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movie_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movie_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movie_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Movie_Total().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Movies;
    private javax.swing.JTable JTable_MoviesTotals;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_category;
    private javax.swing.JTextField txt_director;
    private javax.swing.JTextField txt_moviecast;
    private javax.swing.JTextField txt_movieid;
    private javax.swing.JTextField txt_movietitle;
    private javax.swing.JTextField txt_rating;
    private com.toedter.calendar.JDateChooser txt_releasedate;
    private javax.swing.JTextField txt_runningtime;
    // End of variables declaration//GEN-END:variables
}
