package eu.ark.creditark.services.creditarkservices.api;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/")));
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return "redirect:/";
    }


}