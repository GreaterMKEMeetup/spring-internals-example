package com.mkejug.spring.webargumentresolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class PersonalizationTokenMethodArgumentResolver implements HandlerMethodArgumentResolver {

    static final String KENTICO_USER_ID_COOKIE_PREFIX = "k_e_id";
    static final String KENTICO_SESSION_ID_COOKIE_PREFIX = "k_e_ses";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(PersonalizationToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userCookieValue = null;
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().startsWith(KENTICO_SESSION_ID_COOKIE_PREFIX)) {
                return getTokenFromSessionCookie(cookie.getValue());
            } else if (cookie.getName().startsWith(KENTICO_USER_ID_COOKIE_PREFIX)) {
                // Deferring processing on this in case we have the session cookie
                userCookieValue = cookie.getValue();
            }
        }
        if (userCookieValue == null) {
            return null;
        } else {
            return getTokenFromUserCookie(userCookieValue);
        }
    }

    private PersonalizationToken getTokenFromUserCookie(String value) {
        return new PersonalizationToken(StringUtils.substringBefore(value, "."));
    }

    private PersonalizationToken getTokenFromSessionCookie(String value) {
        String[] parts = StringUtils.split(value, ".");
        if (parts.length <= 2) {
            return new PersonalizationToken(parts[0], parts[1]);
        }
        return null;
    }
}
