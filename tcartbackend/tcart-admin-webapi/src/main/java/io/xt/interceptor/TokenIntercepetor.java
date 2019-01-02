package io.xt.interceptor;

import com.alibaba.fastjson.JSON;
import io.xt.dto.LoginIofo;
import io.xt.exception.BackendUnauthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenIntercepetor implements HandlerInterceptor  {
    private String [] urls={"/user/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean contains = Arrays.asList(urls).contains(request.getRequestURI());
        if (contains){
            return true;
        }
        String authorizationStr = request.getHeader("Authorization");
        if (authorizationStr == null){
            throw new BackendUnauthenticationException("Unauthentication");
        }
        String[] s = authorizationStr.split(" ");
        String token = s[1];
        LoginIofo loginIofo;
        try {
            //todo decrypted token with aes or rsa
            byte[] decode = Base64.getDecoder().decode(token);
            String loginJsonStr = new String(decode, "UTF-8");
            loginIofo = JSON.parseObject(loginJsonStr, LoginIofo.class);
        }catch (Exception ex){
            throw new BackendUnauthenticationException("auth invalid caused by some issues");
        }
        Long userId = loginIofo.getUserId();
        String username = loginIofo.getUsername();
        if (username==null){
            throw new BackendUnauthenticationException("Unauthentication");
        }
        long expireTimestamp = loginIofo.getExpirationTime().getTime();
        Date currentTime = new Date();
        Long currentTimestamp = currentTime.getTime();
        if (username==null&&username.isEmpty()&&(currentTimestamp>expireTimestamp)) {
            throw new BackendUnauthenticationException("Unauthentication");
        }
        request.setAttribute("userId",userId);
        request.setAttribute("username",username);

        return true;
    }
}
