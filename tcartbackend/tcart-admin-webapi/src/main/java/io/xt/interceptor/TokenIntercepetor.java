package io.xt.interceptor;

import com.alibaba.fastjson.JSON;
import io.xt.dto.LoginIofo;
import io.xt.exception.BackendUnauthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenIntercepetor implements HandlerInterceptor  {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException, BackendUnauthenticationException {
        String authorizationStr = request.getHeader("Authorization");
        String[] s = authorizationStr.split(" ");
        String token = s[1];
        byte[] decode = Base64.getDecoder().decode(token);
        String loginJsonStr = new String(decode, "UTF-8");
        LoginIofo loginIofo = JSON.parseObject(loginJsonStr, LoginIofo.class);
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

        return true;
    }
}
