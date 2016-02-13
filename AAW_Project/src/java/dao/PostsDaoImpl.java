/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nathanael Villemin
 */
@Repository
public class PostsDaoImpl implements PostsDao {
    // Le contexte de persistance est décrit dans le fichier hibernate persistence.xml
    @PersistenceContext(unitName="AAW_ProjectPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(PostsEntity post) {
        post = this.em.merge(post);
        this.em.persist(post);
    }

    @Transactional
    @Override
    public void update(PostsEntity post) {
        this.em.merge(post);
    }

    @Transactional
    @Override
    public void delete(PostsEntity post) {
        post = this.em.merge(post);
        this.em.remove(post);
    }

    @Transactional
    @Override
    public PostsEntity find(Long id) {
        return (PostsEntity) this.em.find(PostsEntity.class, id);
    }
    
    @Transactional
    @Override
    public ArrayList<PostsEntity> searchByTarget(UsersEntity target) {
        try {
            return (ArrayList<PostsEntity>) this.em.createQuery(
                "SELECT post "
                + "FROM PostsEntity post "
                + "WHERE post.target = :target")
                .setParameter("target", target).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Transactional
    @Override
    public ArrayList<PostsEntity> searchBySender(UsersEntity sender) {
        try {
            return (ArrayList<PostsEntity>) this.em.createQuery(
                "SELECT post "
                + "FROM PostsEntity post "
                + "WHERE post.sender = :sender")
                .setParameter("sender", sender).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public EntityManager getEm() { return em; }
    public void setEm(EntityManager em) { this.em = em; }
}
