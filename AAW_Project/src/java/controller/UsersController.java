package controller;

import common.Enums.SignInResult;
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
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    NotificationsService notifsService;
    @Autowired
    PostsService postsService;

    // Method used to display the index page
    @RequestMapping(value="index", method=RequestMethod.GET)
    public ModelAndView handleIndex(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("indexMessage", "Please sign up or sign in if you already have an account.");
        return mv;
    }
    
    // Method used to handle the sign up of a new user on the index page
    @RequestMapping(value="signUp", method=RequestMethod.POST)
    public ModelAndView handleSignUp(HttpServletRequest request) {
        // Get the values that the user sent
        String name = request.getParameter("nameSignUp");
        String email = request.getParameter("emailSignUp");
        String password = request.getParameter("passwordSignUp");
        
        ModelAndView mv = new ModelAndView("index");
        
        if(name.isEmpty()) {
            mv.addObject("indexMessage", "Error: Your name cannot be empty.");
        } else if(password.length() < 8) {
            mv.addObject("indexMessage", "Error: You password must have at least 8 characters.");
        } else {
            boolean success = this.usersService.add(name, email, password);
            if(success) {
                mv.addObject("indexMessage", "You have successfully signed up to the social network. You can now sign in.");
            } else {
                mv.addObject("indexMessage", "Error: This name is already used.");
            }
        }
        
        return mv;
    }
    
    // Method used to handle the sign in of an existing user from the index page
    @RequestMapping(value="signIn", method=RequestMethod.POST, params={"nameSignIn", "passwordSignIn"})
    public ModelAndView handleSignIn(HttpServletRequest request) {
        // Get the values that the user sent
        String name = request.getParameter("nameSignIn");
        String password = request.getParameter("passwordSignIn");
        
        ModelAndView mv;

        SignInResult result = this.usersService.checkSignIn(name, password);
        if(result != SignInResult.SUCCESS) {
            mv = new ModelAndView("index");
            if(result == SignInResult.WRONG_USER) {
                mv.addObject("indexMessage", "Error: This user doesn't exist.");
            } else if(result == SignInResult.WRONG_PASSWORD) {
                mv.addObject("indexMessage", "Error: Incorrect password.");
            }
        } else {
            mv = new ModelAndView("redirect:home.htm");
            
            // Creating the session of the user
            HttpSession session = request.getSession(true);
            session.setAttribute("user", this.usersService.findByName(name));
            session.setMaxInactiveInterval(600); // Inactive after 10 minutes
        }
        
        return mv;
    }
    
    // Method used to handle the sign out of a user
    @RequestMapping(value="signOut", method=RequestMethod.GET)
    public ModelAndView handleSignOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return this.handleIndex(request);
    }
    
    // Method used to show the user friends
    @RequestMapping(value="friends", method=RequestMethod.GET)
    public ModelAndView handleFriends(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        session.setAttribute("currentPage", "/friends.htm");
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("friends");
        mv.addObject("currentUser", user);
        mv.addObject("friends", user.getFriends());
        mv.addObject("nbNotifs", this.notifsService.searchByTarget(user).size());
        
        return mv;
    }
    
    // Method used to handle the search of users
    @RequestMapping(value="search", method=RequestMethod.POST)
    public ModelAndView handleSearch(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        session.setAttribute("currentPage", "/search.htm");
        
        String searchName = request.getParameter("searchName");
        ArrayList<UsersEntity> users = this.usersService.searchByName(searchName);
        ModelAndView mv = new ModelAndView("search");
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        mv.addObject("currentUser", user);
        mv.addObject("users", users);
        mv.addObject("nbNotifs", this.notifsService.searchByTarget(user).size());

        return mv;
    }
    
    // Method used to show the profile of a user
    @RequestMapping(value="{userId}/profile", method=RequestMethod.GET)
    public ModelAndView handleProfile(HttpServletRequest request, @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        session.setAttribute("currentPage", "/" + userId.toString() + "/profile.htm");
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        UsersEntity targetUser = this.usersService.find(userId);
        
        ModelAndView mv = new ModelAndView("profile");
        mv.addObject("currentUser", user);
        mv.addObject("user", targetUser);
        mv.addObject("nbNotifs", this.notifsService.searchByTarget(user).size());
        boolean isMyProfile = user.equals(targetUser);
        mv.addObject("myProfile", isMyProfile);  
        boolean isMyFriend = this.usersService.checkFriendship(user, targetUser);
        mv.addObject("myFriend", isMyFriend);
        
        if(!isMyProfile && !isMyFriend) {
            NotificationsEntity sentRequest = this.notifsService.searchBySenderTarget(user, targetUser);
            boolean requestSent = (sentRequest != null);
            mv.addObject("requestSent", requestSent);
            if(!requestSent) {
                NotificationsEntity receivedRequest = this.notifsService.searchBySenderTarget(targetUser, user);
                boolean requestReceived = (receivedRequest != null);
                mv.addObject("requestReceived", requestReceived);
                if(requestReceived) {
                    mv.addObject("notifId", receivedRequest.getId());
                }
            }
        }

        ArrayList<PostsEntity> posts = this.postsService.searchByTarget(targetUser);
        
        // Add the posts sent by this user
        for(PostsEntity post : this.postsService.searchBySender(targetUser)) {
            if(!posts.contains(post)) {
                posts.add(post);
            }
        }
        
        Collections.sort(posts, Collections.reverseOrder());
        mv.addObject("posts", posts);
        
        return mv;
    }
    
    // Method used to change the user info
    @RequestMapping(value="{userId}/userInfo", method=RequestMethod.POST)
    public ModelAndView handleUserInfo(HttpServletRequest request, @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        String newInfo = request.getParameter("infoInput");
        if(!newInfo.isEmpty()) {
            this.usersService.updateInfo(user, newInfo);
        }

        return new ModelAndView("redirect:profile.htm");
    }
    
    // Method used to change the user info
    @RequestMapping(value="{userId}/removeFriend", method=RequestMethod.GET)
    public ModelAndView handleRemoveFriend(HttpServletRequest request, @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        UsersEntity targetUser = this.usersService.find(userId);
        
        if(this.usersService.checkFriendship(user, targetUser)) {
            this.usersService.removeFriendship(user, targetUser);
        }

        return new ModelAndView("redirect:profile.htm");
    }
}