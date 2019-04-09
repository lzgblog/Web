package com.spring.servlet.mobile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mobile.MobileCodeWSSoap;
import com.spring.cxf.server.mobile.MobileInterface;

/**
 * Servlet implementation class MobileServlet
 */

//@WebServlet("/mobile")
public class MobileServlet extends HttpServlet {
	
	private MobileInterface mobileInterface;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//手机号码归属地查询服务
		//获取表单数据
		String phone = request.getParameter("phone");
		if(phone!=null && !"".equals(phone)) {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			mobileInterface = (MobileInterface) context.getBean("MobileInterfaceImpl");
			//调用查询手机号业务
			String queryMobile = mobileInterface.queryMobile(phone);
			request.setAttribute("result", queryMobile);
		}
		//跳转
		request.getRequestDispatcher("mobile.jsp").forward(request, response);
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
