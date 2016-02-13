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
 * @author Jerome Bardot
 */
@Repository
public class FilesDaoImpl implements FilesDao {

    @PersistenceContext(unitName = "AAW_ProjectPU")
    private EntityManager em;

    @Transactional
    @Override
    public void save(FilesEntity file) {
        file = this.em.merge(file);
        this.em.persist(file);
    }

    @Transactional
    @Override
    public void update(FilesEntity file) {
        file = this.em.merge(file);
    }

    @Transactional
    @Override
    public void delete(FilesEntity file) {
        file = this.em.merge(file);
        this.em.remove(file);
    }

    @Transactional
    @Override
    public FilesEntity find(Long id) {
        return (FilesEntity) this.em.find(FilesEntity.class, id);
    }

    @Transactional
    @Override
    public ArrayList<FilesEntity> findByOwner(UsersEntity owner) {
            try {
            return (ArrayList<FilesEntity>) this.em.createQuery(
                "SELECT file "
                + "FROM FilesEntity file "
                + "WHERE file.target = :target")
                .setParameter("target", owner).getResultList();
        } catch (NoResultException e) {
            return null;
        }}



}
