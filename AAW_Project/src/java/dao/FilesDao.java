/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author Nathanael Villemin
 */
public interface FilesDao {

    public void save(FilesEntity file);

    public void update(FilesEntity file);

    public void delete(FilesEntity file);
    
    public FilesEntity find(Long id);
    
    public ArrayList<FilesEntity> findByOwner(UsersEntity owner);
}
