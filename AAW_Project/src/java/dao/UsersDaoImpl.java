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
public class UsersDaoImpl implements UsersDao {
    // Le contexte de persistance est décrit dans le fichier hibernate persistence.xml
    @PersistenceContext(unitName="AAW_ProjectPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(UsersEntity user) {
        user = this.em.merge(user);
        this.em.persist(user);
    }

    @Transactional
    @Override
    public void update(UsersEntity user) {
        this.em.merge(user);
    }

    @Transactional
    @Override
    public void delete(UsersEntity user) {
        user = this.em.merge(user);
        this.em.remove(user);
    }

    @Transactional
    @Override
    public UsersEntity find(Long id) {
        return (UsersEntity) this.em.find(UsersEntity.class, id);
    }
    
    @Transactional
    @Override
    public UsersEntity findByName(String name) {
        try {
            return (UsersEntity) this.em.createQuery(
                "SELECT user "
                + "FROM UsersEntity user "
                + "WHERE user.name = :name")
                .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public ArrayList<UsersEntity> searchByName(String name) {
        try {
            String sql = "WHERE user.name LIKE '%"+name+"%'" ;
            
            return (ArrayList<UsersEntity>) this.em.createQuery(
                "SELECT user "
                + "FROM UsersEntity user "
                + sql)
                .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Transactional
    @Override
    public boolean checkFriendship(UsersEntity user, UsersEntity friend) {
        return user.getFriends().contains(friend);
    }
    
    @Transactional
    @Override
    public boolean addFriendship(UsersEntity user, UsersEntity friend) {
        if(user.addFriend(friend) && friend.addFriend(user)) {
            this.update(user);
            this.update(friend);
            return true;
        }
        return false;
    }
    
    @Transactional
    @Override
    public boolean removeFriendship(UsersEntity user, UsersEntity friend) {
        if(user.removeFriend(friend) && friend.removeFriend(user)) {
            this.update(user);
            this.update(friend);
            return true;
        }
        return false;
    }
    
    public EntityManager getEm() { return em; }
    public void setEm(EntityManager em) { this.em = em; }
}
