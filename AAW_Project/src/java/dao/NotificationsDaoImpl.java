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
public class NotificationsDaoImpl implements NotificationsDao {
    // Le contexte de persistance est décrit dans le fichier hibernate persistence.xml
    @PersistenceContext(unitName="AAW_ProjectPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(NotificationsEntity notif) {
        notif = this.em.merge(notif);
        this.em.persist(notif);
    }

    @Transactional
    @Override
    public void update(NotificationsEntity notif) {
        this.em.merge(notif);
    }

    @Transactional
    @Override
    public void delete(NotificationsEntity notif) {
        notif = this.em.merge(notif);
        this.em.remove(notif);
    }

    @Transactional
    @Override
    public NotificationsEntity find(Long id) {
        return (NotificationsEntity) this.em.find(NotificationsEntity.class, id);
    }
    
    @Transactional
    @Override
    public ArrayList<NotificationsEntity> searchByTarget(UsersEntity target) {
        try {
            return (ArrayList<NotificationsEntity>) this.em.createQuery(
                "SELECT notif "
                + "FROM NotificationsEntity notif "
                + "WHERE notif.target = :target")
                .setParameter("target", target).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Transactional
    @Override
    public NotificationsEntity searchBySenderTarget(UsersEntity sender, UsersEntity target) {
        try {
            return (NotificationsEntity) this.em.createQuery(
                "SELECT notif "
                + "FROM NotificationsEntity notif "
                + "WHERE notif.sender = :sender "
                + "AND notif.target = :target")
                .setParameter("sender", sender)
                .setParameter("target", target).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
