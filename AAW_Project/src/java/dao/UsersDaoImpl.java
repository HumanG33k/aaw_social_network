/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nvillemi
 */
@Repository
public class UsersDaoImpl implements UsersDao {
    // Le contexte de persistance est décrit dans le fichier hibernate persistence.xml
    @PersistenceContext(unitName="AAW_ProjectPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(UsersEntity user) {
        this.em.merge(user);
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
    public UsersEntity find(String name) {
        return this.em.find(UsersEntity.class, name);
    }
}
