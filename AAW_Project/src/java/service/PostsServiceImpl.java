/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.PostsDao;
import dao.PostsEntity;
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
    public void add(String content, Long userId, Long wallId) {
        PostsEntity post = new PostsEntity(content, userId, wallId);
        this.postsDao.save(post);
    }
}
