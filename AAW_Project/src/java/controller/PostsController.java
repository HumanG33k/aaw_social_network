/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.NotificationsEntity;
import dao.PostsEntity;
import dao.UsersEntity;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.NotificationsService;
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
    @Autowired
    NotificationsService notifsService;

    // Method used to show all the posts related to the user
    @RequestMapping(value="home", method=RequestMethod.GET)
    public ModelAndView handleHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }

        session.setAttribute("currentPage", "/home.htm");
        
        // Get all the posts sent to this user
        UsersEntity user = (UsersEntity)session.getAttribute("user");
        ArrayList<PostsEntity> posts = this.postsService.searchByTarget(user);
        
        // Add the posts sent by this user
        for(PostsEntity post : this.postsService.searchBySender(user)) {
            if(!posts.contains(post)) {
                posts.add(post);
            }
        }
        
        // Add the posts sent by friends
        for(UsersEntity friend : user.getFriends()) {
            for(PostsEntity post : this.postsService.searchBySender(friend)) {
                if(!posts.contains(post)) {
                    posts.add(post);
                }
            }
        }
        
        Collections.sort(posts, Collections.reverseOrder());
        
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("currentUser", user);
        mv.addObject("posts", posts);
        mv.addObject("nbNotifs", this.notifsService.searchByTarget(user).size());
        
        return mv;
    }
    
    // Method used to handle the creation of a new post
    @RequestMapping(value="{userId}/createPost", method=RequestMethod.POST, params="postContent")
    public ModelAndView handleAddPost(HttpServletRequest request, @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        String content = request.getParameter("postContent");
        
        if(!content.isEmpty()) {
            UsersEntity sender = (UsersEntity)session.getAttribute("user");
            UsersEntity target = this.usersService.find(userId);
            this.postsService.add(content, sender, target);
        }
        
        return new ModelAndView("redirect:" + session.getAttribute("currentPage"));
    }
    
    // Method used to handle the removal of new post
    @RequestMapping(value="{postId}/removePost", method=RequestMethod.GET)
    public ModelAndView handleRemovePost(HttpServletRequest request, @PathVariable Long postId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        PostsEntity post = this.postsService.find(postId);
        if(post != null) {
            this.postsService.remove(post);
        }

        return new ModelAndView("redirect:" + session.getAttribute("currentPage"));
    }
}
