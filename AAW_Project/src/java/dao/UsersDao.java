/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Nathanael Villemin
 */
public interface UsersDao {
    public void save(UsersEntity user);
    public void update(UsersEntity user);
    public void delete(UsersEntity user);
    public UsersEntity find(Long id);
    public UsersEntity findByName(String name);
}
