package controller;

import common.Enums.SignInResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UsersService;

/**
 *
 * @author Nathanael Villemin
 */
@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    // Method used to handle the sign up of a new user on the index page
    @RequestMapping(value="index", method=RequestMethod.POST)
    protected ModelAndView handleSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    @RequestMapping(value="home", method=RequestMethod.POST, params={"nameSignIn", "passwordSignIn"})
    protected ModelAndView handleSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            mv = new ModelAndView("home");
            
            // Creating the session of the user
            HttpSession session = request.getSession(true);
            session.setAttribute("name", name);
            session.setAttribute("userId", this.usersService.findByName(name).getId());
            session.setMaxInactiveInterval(600); // Inactive after 10 minutes
        }
        
        return mv;
    }
    
    // Method used to handle the sign up of a new user on the index page
    @RequestMapping(value="index", method=RequestMethod.GET)
    protected ModelAndView handleSignOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("indexMessage", "Please sign up or sign in if you already have an account.");
        return mv;
    }
}