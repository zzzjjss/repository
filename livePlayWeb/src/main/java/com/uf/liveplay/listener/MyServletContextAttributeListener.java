package com.uf.liveplay.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.uf.liveplay.entity.Teacher;
import com.uf.liveplay.socketio.SocketIoServer;

/**
 * Application Lifecycle Listener implementation class MyServletContextAttributeListener
 *
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public MyServletContextAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
//    	if(arg0.getName().equals("currentTeacher")&&arg0.getValue()!=null&&arg0.getValue() instanceof Teacher){
//    		Teacher teacher=(Teacher)arg0.getValue();
//    		//TODO TESS
//    		//WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());  
//    		
//    		SocketIoServer socketIoServer=springContext.getBean(SocketIoServer.class);
//    		socketIoServer.switchCurrentTeacher(teacher.getRealName());
//    	}
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
