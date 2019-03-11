package com.whu.sres.lhw.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Description:
 * Created by lvhw on 2019/3/6 8:47.
 */
//@WebFilter(filterName = "userFilter", urlPatterns = "/*") + @ServletComponentScan
public class UserFilter implements Filter{
    private static final Logger LOG = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("init UserFiler");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("do UserFiler.doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.info("destroy UserFiler");
    }
}
