/*
 * Login Window
 * Authenticate user from DATABASE
 */

/**
 * @author ROBERTO GOMEZ
 */
package mv_login;

import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
//import com.sun.jndi.ldap.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mvlogin extends javax.swing.JFrame {
java.sql.Connection conn = null;
ResultSet rs = null;
Statement st;

private JFrame frame;
    /**
     * Creates new form Login
     */
    public mvlogin() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtUsername = new javax.swing.JTextField();
        jtxtPassword = new javax.swing.JPasswordField();
        jtxtLogin = new javax.swing.JButton();
        jtxtReset = new javax.swing.JButton();
        jtxtExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1380, 800));
        setPreferredSize(new java.awt.Dimension(1500, 800));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("Password");
        jLabel3.setToolTipText("");

        jtxtUsername.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jtxtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jtxtPassword.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jtxtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        jtxtLogin.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jtxtLogin.setText("Login");
        jtxtLogin.setToolTipText("");
        jtxtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtLoginActionPerformed(evt);
            }
        });

        jtxtReset.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jtxtReset.setText("Reset");
        jtxtReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtResetActionPerformed(evt);
            }
        });

        jtxtExit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jtxtExit.setText("Exit");
        jtxtExit.setToolTipText("");
        jtxtExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtLogin)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtxtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(jtxtUsername)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jtxtReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jtxtExit)
                        .addGap(117, 117, 117))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtLogin)
                    .addComponent(jtxtReset)
                    .addComponent(jtxtExit))
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 172, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("CINEMA MANAGEMENT SYSTEM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 96, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jtxtExitActionPerformed

    private void jtxtResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtResetActionPerformed
        jtxtUsername.setText(null);
        jtxtPassword.setText(null);
    }//GEN-LAST:event_jtxtResetActionPerformed

    private void jtxtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtLoginActionPerformed
        
        String password = jtxtPassword.getText();
        String username = jtxtUsername.getText();
        
        try {
            int log = 1;
                String usernameDB = "cinema";
                String passwordDB = "cinemalogin";
                String databaseName = "jdbc:derby://localhost:1527/cinema";
                conn = DriverManager.getConnection(databaseName, usernameDB, passwordDB);
                st = (Statement)conn.createStatement();
                rs = st.executeQuery("select * from mv_staff");
 
            
            while (rs.next())
            {
                          
                if (rs.getString(11).equals(username) && rs.getString(12).equals(password))
                {
                    log = 0;
                        break;
                }
            }
            if(log == 0)
        
            {
                 JOptionPane.showMessageDialog(null,"ACCESS GRANTED");
                   CloseMe(); // Create class 
                    welcome_mv Info = new welcome_mv();
                    Info.setVisible(true);
                    
             }
        else
            {
                JOptionPane.showMessageDialog(null,"Invalid Login Details", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
                jtxtPassword.setText(null);
                jtxtUsername.setText(null);
                jtxtUsername.grabFocus();
            }
        }
        
        catch (SQLException ex)
                {
                Logger.getLogger(mvlogin.class.getName()).log(Level.SEVERE,null,ex);
                }
    }//GEN-LAST:event_jtxtLoginActionPerformed
    private void CloseMe(){
        WindowEvent meClose = new WindowEvent (this, WindowEvent.WINDOW_CLOSED);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(meClose);
    }
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
            java.util.logging.Logger.getLogger(mvlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mvlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mvlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mvlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mvlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jtxtExit;
    private javax.swing.JButton jtxtLogin;
    private javax.swing.JPasswordField jtxtPassword;
    private javax.swing.JButton jtxtReset;
    private javax.swing.JTextField jtxtUsername;
    // End of variables declaration//GEN-END:variables

    private void systemExit()
    {
    WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    }
}
