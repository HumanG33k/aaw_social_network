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
public interface PostsDao {
    public void save(PostsEntity post);
    public void update(PostsEntity post);
    public void delete(PostsEntity post);
    public PostsEntity find(Long id);
    public ArrayList<PostsEntity> searchByTarget(UsersEntity target);
    public ArrayList<PostsEntity> searchBySender(UsersEntity sender);
}
