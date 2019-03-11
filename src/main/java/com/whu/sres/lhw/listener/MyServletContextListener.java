package com.whu.sres.lhw.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Description:
 * Created by lvhw on 2019/3/10 19:45.
 */
public class MyServletContextListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(MyServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("init servlet context.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("destroy servlet context.");

    }
}
