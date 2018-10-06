/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mv_staff;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author rob
 */
@Entity
@Table(name = "MV_ACCOUNTS", catalog = "", schema = "CINEMA")
@NamedQueries({
    @NamedQuery(name = "MvAccounts.findAll", query = "SELECT m FROM MvAccounts m")
    , @NamedQuery(name = "MvAccounts.findByAccountid", query = "SELECT m FROM MvAccounts m WHERE m.accountid = :accountid")
    , @NamedQuery(name = "MvAccounts.findByUsername", query = "SELECT m FROM MvAccounts m WHERE m.username = :username")
    , @NamedQuery(name = "MvAccounts.findByPassword", query = "SELECT m FROM MvAccounts m WHERE m.password = :password")
    , @NamedQuery(name = "MvAccounts.findByFirstname", query = "SELECT m FROM MvAccounts m WHERE m.firstname = :firstname")
    , @NamedQuery(name = "MvAccounts.findByLastname", query = "SELECT m FROM MvAccounts m WHERE m.lastname = :lastname")})
public class MvAccounts implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ACCOUNTID")
    private Integer accountid;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastname;

    public MvAccounts() {
    }

    public MvAccounts(Integer accountid) {
        this.accountid = accountid;
    }

    public MvAccounts(Integer accountid, String username, String password, String firstname, String lastname) {
        this.accountid = accountid;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        Integer oldAccountid = this.accountid;
        this.accountid = accountid;
        changeSupport.firePropertyChange("accountid", oldAccountid, accountid);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        String oldUsername = this.username;
        this.username = username;
        changeSupport.firePropertyChange("username", oldUsername, username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        changeSupport.firePropertyChange("firstname", oldFirstname, firstname);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        String oldLastname = this.lastname;
        this.lastname = lastname;
        changeSupport.firePropertyChange("lastname", oldLastname, lastname);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountid != null ? accountid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvAccounts)) {
            return false;
        }
        MvAccounts other = (MvAccounts) object;
        if ((this.accountid == null && other.accountid != null) || (this.accountid != null && !this.accountid.equals(other.accountid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_staff.MvAccounts[ accountid=" + accountid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
