package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (!StringUtils.isEmpty(email) && "123456".equals(password)) {
            session.setAttribute("loginUser", email);
            return "dashboard";
//            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "incorrect email or password");
            return "index";
        }

    }

}
