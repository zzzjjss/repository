package com.uf.liveplay.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TeacherLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpRequest=(HttpServletRequest)request;
			Object teacher=httpRequest.getSession().getAttribute("teacher");
			if(teacher==null){
				HttpServletResponse httpResponse=(HttpServletResponse)response;
				String context=httpRequest.getServletContext().getContextPath();
				//System.out.println(context);
				httpResponse.sendRedirect(context+"/teacherLogin.html");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

