/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nathanael Villemin
 */
@Entity
@Table(name = "notifications")
public class NotificationsEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender")
    private UsersEntity sender;
    @ManyToOne
    @JoinColumn(name = "target")
    private UsersEntity target;

    public NotificationsEntity() {}
    
    public NotificationsEntity(UsersEntity sender, UsersEntity target) {
        this.sender = sender;
        this.target = target;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationsEntity)) {
            return false;
        }
        NotificationsEntity other = (NotificationsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.NotificationsEntity[ id=" + id + " ]";
    }
    
    // Getters ands setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UsersEntity getSender() { return sender; }
    public void setSender(UsersEntity sender) { this.sender = sender; }
    public UsersEntity getTarget() { return target; }
    public void setTarget(UsersEntity target) { this.target = target; }
}
