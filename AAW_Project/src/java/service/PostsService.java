/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostsEntity;
import dao.UsersEntity;
import java.util.ArrayList;

/**
 *
 * @author Nathanael Villemin
 */
public interface PostsService {
    public void add(String content, Long senderId, Long wallId);
    public ArrayList<PostsEntity> searchByTargetId(Long targetId);
}
