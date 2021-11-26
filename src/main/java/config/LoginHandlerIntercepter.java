package config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //should have user's session after login successfully
        Object loginUser =  request.getSession().getAttribute("loginUser");
        if (loginUser == null){
            request.setAttribute("msg", "Permission deny, please login first");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        } else{
            return true;
        }


    }


}
