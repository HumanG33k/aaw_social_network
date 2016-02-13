/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostsDao;
import dao.PostsEntity;
import dao.UsersEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nathanael Villemin
 */
@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    PostsDao postsDao;

    @Override
    public void add(String content, UsersEntity sender, UsersEntity target) {
        PostsEntity post = new PostsEntity(content, sender, target);
        this.postsDao.save(post);
    }
    
    @Override
    public void remove(PostsEntity post) {
        this.postsDao.delete(post);
    }
    
    @Override
    public PostsEntity find(Long id) {
        return this.postsDao.find(id);
    }
    
    @Override
    public ArrayList<PostsEntity> searchByTarget(UsersEntity target) {
        return this.postsDao.searchByTarget(target);
    }
    
    @Override
    public ArrayList<PostsEntity> searchBySender(UsersEntity sender) {
        return this.postsDao.searchBySender(sender);
    }
}
