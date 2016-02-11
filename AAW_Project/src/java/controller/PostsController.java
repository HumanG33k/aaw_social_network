/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PostsEntity;
import dao.UsersEntity;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.PostsService;
import service.UsersService;

/**
 *
 * @author Nathanael Villemin
 */
@Controller
public class PostsController {
    @Autowired
    PostsService postsService;
    @Autowired
    UsersService usersService;

    // Method used to handle the creation of a new post
    @RequestMapping(value="home", method=RequestMethod.GET)
    protected ModelAndView handleHome(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Long userId = ((UsersEntity)session.getAttribute("user")).getId();
        
        // Get all the posts sent by / to this user
        ArrayList<PostsEntity> posts = this.postsService.searchByTargetId(userId);
        HashMap<PostsEntity, UsersEntity> postsSenders = new HashMap<>();
        for(PostsEntity post : posts) {
            UsersEntity sender = this.usersService.find(post.getSenderId());
            postsSenders.put(post, sender);
        }
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("postsSenders", postsSenders);
        
        return mv;
    }
    
    // Method used to handle the creation of a new post
    @RequestMapping(value="home", method=RequestMethod.POST, params="postContent")
    protected ModelAndView handleAddPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String content = request.getParameter("postContent");
        
        if(!content.isEmpty()) {
            HttpSession session = request.getSession();
            Long userId = ((UsersEntity)session.getAttribute("user")).getId();
            this.postsService.add(content, userId, userId);
        }
        
        return handleHome(request, response);
    }
}
