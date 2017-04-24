package org.muks.wimd.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by 300000511 on 25/04/17.
 */

@WebListener
public class TasInitialise implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("========== Starting up the services ==========");
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("==========  Shutting down! ==========");
    }

}