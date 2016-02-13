/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jerome Bardot
 */
@Entity
@Table(name = "files")
public class FilesEntity implements Serializable, Comparable<FilesEntity> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private byte[] content;
    
    @ManyToOne
    @JoinColumn(name = "owner")
    private UsersEntity owner;
    
    /*
    @JoinTable(
            name="file_post",
            joinColumns = @JoinColumn(name="id_file"),
            inverseJoinColumns = @JoinColumn(name="id_post")
    )
    @ManyToMany
    private List<PostsEntity> posts = new ArrayList<>();*/

    
    
    public FilesEntity() {
    }

    public FilesEntity(String name, String type, byte[] content, UsersEntity owner) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.owner = owner;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
    /*
    public UsersEntity getOwner() {
        return owner;
    }

    public void setOwner(UsersEntity owner) {
        this.owner = owner;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }
    
    public void addPost(PostsEntity post){
        this.posts.add(post);
    }
    
    public void removePost(PostsEntity post){
        this.posts.remove(this.posts.indexOf(post));
    }
    */
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }    

    @Override
    public int compareTo(FilesEntity o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
