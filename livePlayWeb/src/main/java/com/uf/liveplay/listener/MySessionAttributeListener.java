package com.uf.liveplay.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.uf.liveplay.entity.User;
import com.uf.liveplay.unit.SessionCache;

/**
 * Application Lifecycle Listener implementation class MySessionAttributeListener
 *
 */
public class MySessionAttributeListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MySessionAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
        if(arg0.getName().equals("user")){
        	String sessionId=arg0.getSession().getId();
        	User value=(User)arg0.getValue();
        	SessionCache.addUser(sessionId,value);
        }
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
