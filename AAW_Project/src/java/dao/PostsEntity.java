/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Nathanael Villemin
 */
@Entity
@Table(name = "posts")
public class PostsEntity implements Serializable, Comparable<PostsEntity> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender")
    private UsersEntity sender;
    @ManyToOne
    @JoinColumn(name = "target")
    private UsersEntity target;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datePost;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<FilesEntity> files = new ArrayList<>();;

    public PostsEntity() {

    }

    public PostsEntity(String content, UsersEntity sender, UsersEntity target) {
        this.files = new ArrayList<>();
        this.content = content;
        this.sender = sender;
        this.target = target;
        this.datePost = new Date();
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
        if (!(object instanceof PostsEntity)) {
            return false;
        }
        PostsEntity other = (PostsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.PostsEntity[ id=" + id + " ]";
    }

    @Override
    public int compareTo(PostsEntity post) {
        return this.datePost.compareTo(post.getDate());
    }

    // Getters ands setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UsersEntity getSender() {
        return sender;
    }

    public void setSender(UsersEntity sender) {
        this.sender = sender;
    }

    public UsersEntity getTarget() {
        return target;
    }

    public void setTarget(UsersEntity target) {
        this.target = target;
    }

    public Date getDate() {
        return this.datePost;
    }

    public void setDate(Date datePost) {
        this.datePost = datePost;
    }
}
