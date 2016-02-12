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

    // Method used to show all the posts related to the user
    @RequestMapping(value="home", method=RequestMethod.GET)
    public ModelAndView handleHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }

        // Get all the posts sent by / to this user
        UsersEntity user = (UsersEntity)session.getAttribute("user");
        ArrayList<PostsEntity> posts = this.postsService.searchByTarget(user);
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("posts", posts);
        
        return mv;
    }
    
    // Method used to handle the creation of a new post
    @RequestMapping(value="createPost", method=RequestMethod.POST, params="postContent")
    public ModelAndView handleAddPost(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        String content = request.getParameter("postContent");
        
        if(!content.isEmpty()) {
            UsersEntity sender = (UsersEntity)session.getAttribute("user");
            this.postsService.add(content, sender, sender);
        }
        
        return handleHome(request);
    }
}
