package com.jdx.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.LocaleResolver;

import com.jdx.common.utils.MessageConstants;

public class AjaxTimeoutRedirectFilter implements Filter
{
 
    private static final Logger logger = LoggerFactory.getLogger(AjaxTimeoutRedirectFilter.class);
 
 
    private int customSessionExpiredErrorCode;
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {

    	HttpServletRequest req = (HttpServletRequest) request;
    	
    	if(req.getSession(false) == null)
    	{
    		 String msg = getMessage(MessageConstants.SESSION_EXPIRED, (HttpServletRequest)request);
    		 SecurityContextHolder.getContext().getAuthentication();
    	}
    	
        chain.doFilter(request, response);
        
        logger.debug("Chain processed normally");
    }
 
    public void setCustomSessionExpiredErrorCode(int customSessionExpiredErrorCode)
    {
        this.customSessionExpiredErrorCode = customSessionExpiredErrorCode;
    }
    
    private String getMessage(String message, HttpServletRequest req)
	{
		return messageSource.getMessage(message, null, localeResolver.resolveLocale(req));
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}