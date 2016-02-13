package controller;

import dao.UsersEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.FilesService;
import service.UsersService;

/**
 *
 * @author Nathanael Villemin
 */
@Controller
public class FilesController {

    @Autowired
    FilesService filesService;
    @Autowired
    UsersService usersService;

    // Method used to display the file page
    @RequestMapping(value = "{userId}/files", method = RequestMethod.GET)
    public ModelAndView handleFile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView mv;
        if (session == null || !request.isRequestedSessionIdValid()) {
            mv = new ModelAndView("index");
        } else {
            mv = new ModelAndView("files");
        }
        return mv;
    }

    @RequestMapping(value = "{userId}/files", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletRequest request, @RequestParam("fileToUpload") MultipartFile file) {
        HttpSession session = request.getSession();
        ModelAndView mv;
        if (session == null || !request.isRequestedSessionIdValid()) {
            mv = new ModelAndView("/index");
        } else{
            mv = new ModelAndView("{userId}/files");

                if (!file.isEmpty()) {
                    String name = file.getName();
                    try {

                        this.filesService.add(file, (UsersEntity) session.getAttribute("user"));

                        mv.addObject("uploadMessage", "Your file" + name + " is succesfully upload.");

                    } catch (Exception e) {

                        mv.addObject("uploadMessage", e.getMessage());
                    }
                } else {
                    mv.addObject("uploadMessage", "Your file is empty.");

                }
            
        }
        return mv;
    }
    //    MultipartFile multipartFile = new MockMultipartFile("file",
    //        file.getName(), "text/plain", IOUtils.toByteArray(input));

}