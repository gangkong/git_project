package kr.co.konghr.common.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class CountSsesionListener
 *
 */
public class CountSsesionListener implements HttpSessionListener {
	
    /**
     * Default constructor. 
     */
    public CountSsesionListener() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         System.out.println("세션 +++++++++++");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("세션 ------------");
    }
	
}
