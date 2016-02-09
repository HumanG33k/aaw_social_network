/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Nathanael Villemin
 * 
 */
@Entity
@Table(name = "users")
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @JoinTable(
        name="users_users",
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns=@JoinColumn(name="user_friend_id")
    )
    @ManyToMany
    private List<UsersEntity> friends = new ArrayList<>();

    public UsersEntity() {}
    
    public UsersEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsersEntity)) {
            return false;
        }
        UsersEntity other = (UsersEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "dao.UsersEntity[ id=" + id + " ]";
    }

    // Getters ands setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<UsersEntity> getFriends() { return friends; }
    public void setFriends(List<UsersEntity> friends) { this.friends = friends; }
}