/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PostsEntity;
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

    // Method used to handle the creation of a new post
    @RequestMapping(value="home", method=RequestMethod.POST, params="postContent")
    protected ModelAndView handleAddPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String content = request.getParameter("postContent");
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Long wallId = 0L; // TODO

        this.postsService.add(content, userId, wallId);
        
        return new ModelAndView("home");
    }
}
