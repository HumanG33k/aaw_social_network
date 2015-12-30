/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UsersEntity;

/**
 *
 * @author Jardinier
 */
public interface UsersService {
    public boolean add(String name, String email, String password);
    public boolean remove(String name);
    public UsersEntity find(String name);
}
