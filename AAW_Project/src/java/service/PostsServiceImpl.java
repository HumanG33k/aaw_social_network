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
    public void add(String content, Long senderId, Long wallId) {
        PostsEntity post = new PostsEntity(content, senderId, wallId);
        this.postsDao.save(post);
    }
    
    @Override
    public ArrayList<PostsEntity> searchByTargetId(Long targetId) {
        return this.postsDao.searchByTargetId(targetId);
    }
}
