package controller;

import common.Enums.CheckResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    
    @RequestMapping(value="index", method=RequestMethod.GET)
    String initIndex() {
        return "index";
    }
    
    // Method used to handle the sign up of a new user on the index page
    @RequestMapping(value="index", method=RequestMethod.POST)
    protected ModelAndView handleSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get the values that the user sent
        String name = request.getParameter("nameSignUp");
        String email = request.getParameter("emailSignUp");
        String password = request.getParameter("passwordSignUp");
        
        ModelAndView mv = new ModelAndView("index");
        
        // Check that the password is at least 8 characters long
        if(password.length() < 8) {
            mv.addObject("indexMessage", "Error: You password must have at least 8 characters.");
        // Register user in database
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
    @RequestMapping(value="home", method=RequestMethod.POST)
    protected ModelAndView handleSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get the values that the user sent
        String name = request.getParameter("nameSignIn");
        String password = request.getParameter("passwordSignIn");
        
        ModelAndView mv;

        CheckResult result = this.usersService.checkSignIn(name, password);
        if(result != CheckResult.SUCCESS) {
            mv = new ModelAndView("index");
            if(result == CheckResult.WRONG_USER) {
                mv.addObject("indexMessage", "Error: This user doesn't exist.");
            } else if(result == CheckResult.WRONG_PASSWORD) {
                mv.addObject("indexMessage", "Error: Incorrect password.");
            }
        } else {
            mv = new ModelAndView("home");
        }
        
        return mv;
    }
}