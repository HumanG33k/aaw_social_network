/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NotificationsEntity;
import dao.UsersEntity;
import java.util.ArrayList;

/**
 *
 * @author Nathanael Villemin
 */
public interface NotificationsService {
    public void add(UsersEntity sender, UsersEntity target);
    public boolean remove(NotificationsEntity notif);
    public NotificationsEntity find(Long id);
    public ArrayList<NotificationsEntity> searchByTarget(UsersEntity target);
    public NotificationsEntity searchBySenderTarget(UsersEntity sender, UsersEntity target);
}
