package com.uf.fanfan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAllAgent
 */
@WebServlet("/admin/GetAllAgent")
public class GetAllAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllAgent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String res="{\"page\":1,\"total\":239,\"rows\":[{\"id\":\"1\",\"cell\":[\"jason zhang\",\"金蝶软件园A栋801\",\"￥100.00\",\"￥2000.00\",\"wang wu\",\"716\"]},{\"id\":\"2\",\"cell\":[\"jason li\",\"金蝶软件园A栋802\",\"￥100.00\",\"￥2000.00\",\" zhangShang\",\"716\"]}]}";
		response.setContentType("text/json");
		response.setCharacterEncoding("gb2312");
		response.getOutputStream().write(res.getBytes());
		
	}

}
