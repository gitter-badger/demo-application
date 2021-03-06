package com.github.kazuki43zoo.web.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component("defaultCsrfRequiresMethodMatcher")
public final class DefaultCsrfRequiresMethodMatcherImpl implements RequestMatcher {

    private Pattern allowedMethods;

    @Value("${DefaultCsrfRequiresMethodMatcherImpl.allowedMethods:^(GET|HEAD|TRACE|OPTIONS)$}")
    public void setAllowedMethods(Pattern allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public boolean matches(HttpServletRequest request) {
        return !allowedMethods.matcher(request.getMethod()).matches();
    }

}
