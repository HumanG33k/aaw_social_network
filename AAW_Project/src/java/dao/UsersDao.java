/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author nvillemi
 */
public interface UsersDao {
    public void save(UsersEntity user);
    public void update(UsersEntity user);
    public void delete(UsersEntity user);
    public UsersEntity find(String name);
}
