/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.NotificationsEntity;
import dao.UsersEntity;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.NotificationsService;
import service.UsersService;

/**
 *
 * @author Nathanael Villemin
 */
@Controller
public class NotificationsController {
    @Autowired
    NotificationsService notifsService;
    @Autowired
    UsersService usersService;
    
    // Method used to show all the posts related to the user
    @RequestMapping(value="notifications", method=RequestMethod.GET)
    public ModelAndView handleNotifs(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }

        session.setAttribute("currentPage", "/notifications.htm");
        
        // Get all the notifications sent to this user
        UsersEntity user = (UsersEntity)session.getAttribute("user");
        ArrayList<NotificationsEntity> notifs = this.notifsService.searchByTarget(user);
        ModelAndView mv = new ModelAndView("notifications");
        mv.addObject("currentUser", user);
        mv.addObject("notifs", notifs);
        mv.addObject("nbNotifs", notifs.size());
        
        return mv;
    }
    
    // Method used to send a friend request
    @RequestMapping(value="{userId}/sendRequest", method=RequestMethod.GET)
    public ModelAndView handleSendRequest(HttpServletRequest request, @PathVariable Long userId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        UsersEntity targetUser = this.usersService.find(userId);
        
        this.notifsService.add(user, targetUser);

        return new ModelAndView("redirect:profile.htm");
    }
    
    // Method used to accept a friend request
    @RequestMapping(value="{notifId}/acceptFriend", method=RequestMethod.GET)
    public ModelAndView handleAcceptFriend(HttpServletRequest request, @PathVariable Long notifId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }
        
        UsersEntity user = (UsersEntity) session.getAttribute("user");
        NotificationsEntity notif = this.notifsService.find(notifId);
        if(user.equals(notif.getTarget()) && this.usersService.addFriendship(notif.getSender(), user)) {
            session.setAttribute("user", user);
        }
        this.notifsService.remove(notif);

        ModelAndView mv = new ModelAndView("redirect:" + session.getAttribute("currentPage"));
        mv.addObject("currentUser", (UsersEntity) session.getAttribute("user"));
        
        return mv;
    }
    
    // Method used to deny a friend request
    @RequestMapping(value="{notifId}/denyFriend", method=RequestMethod.GET)
    public ModelAndView handleDenyFriend(HttpServletRequest request, @PathVariable Long notifId) {
        HttpSession session = request.getSession();
        if(session == null || !request.isRequestedSessionIdValid()) {
            return new ModelAndView("index");
        }

        NotificationsEntity notif = this.notifsService.find(notifId);
        if(notif != null) {
            this.notifsService.remove(notif);
        }
        
        return new ModelAndView("redirect:/notifications.htm");
    }
}
