/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import common.Enums.CheckResult;
import dao.UsersEntity;

/**
 *
 * @author Nathanael Villemin
 */
public interface UsersService {
    public boolean add(String name, String email, String password);
    public boolean remove(String name);
    public UsersEntity findByName(String name);
    public CheckResult checkSignIn(String name, String password);
}
