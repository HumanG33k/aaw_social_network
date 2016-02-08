package controller;

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
    
    @RequestMapping(value="index", method=RequestMethod.POST)
    protected ModelAndView handleSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("nameSignUp");
        String email = request.getParameter("emailSignUp");
        String password = request.getParameter("passwordSignUp");
        
        ModelAndView mv = new ModelAndView("index");
        boolean success = this.usersService.add(name, email, password);
        if(success) {
            mv.addObject("indexMessage", "You have successfully signed up to the social network. You can now sign in.");
        } else {
            mv.addObject("indexMessage", "Error: This name is already used.");
        }
        return mv;
    }
}