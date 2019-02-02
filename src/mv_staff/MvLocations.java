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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Rob77
 */
@Entity
@Table(name = "MV_LOCATIONS", catalog = "", schema = "CINEMA")
@NamedQueries({
    @NamedQuery(name = "MvLocations.findAll", query = "SELECT m FROM MvLocations m")
    , @NamedQuery(name = "MvLocations.findByLocationid", query = "SELECT m FROM MvLocations m WHERE m.locationid = :locationid")
    , @NamedQuery(name = "MvLocations.findByLocationname", query = "SELECT m FROM MvLocations m WHERE m.locationname = :locationname")
    , @NamedQuery(name = "MvLocations.findByAddress", query = "SELECT m FROM MvLocations m WHERE m.address = :address")
    , @NamedQuery(name = "MvLocations.findByLocationphone", query = "SELECT m FROM MvLocations m WHERE m.locationphone = :locationphone")})
public class MvLocations implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LOCATIONID")
    private Integer locationid;
    @Basic(optional = false)
    @Column(name = "LOCATIONNAME")
    private String locationname;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "LOCATIONPHONE")
    private String locationphone;

    public MvLocations() {
    }

    public MvLocations(Integer locationid) {
        this.locationid = locationid;
    }

    public MvLocations(Integer locationid, String locationname) {
        this.locationid = locationid;
        this.locationname = locationname;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        Integer oldLocationid = this.locationid;
        this.locationid = locationid;
        changeSupport.firePropertyChange("locationid", oldLocationid, locationid);
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        String oldLocationname = this.locationname;
        this.locationname = locationname;
        changeSupport.firePropertyChange("locationname", oldLocationname, locationname);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldAddress = this.address;
        this.address = address;
        changeSupport.firePropertyChange("address", oldAddress, address);
    }

    public String getLocationphone() {
        return locationphone;
    }

    public void setLocationphone(String locationphone) {
        String oldLocationphone = this.locationphone;
        this.locationphone = locationphone;
        changeSupport.firePropertyChange("locationphone", oldLocationphone, locationphone);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationid != null ? locationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MvLocations)) {
            return false;
        }
        MvLocations other = (MvLocations) object;
        if ((this.locationid == null && other.locationid != null) || (this.locationid != null && !this.locationid.equals(other.locationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mv_staff.MvLocations[ locationid=" + locationid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
