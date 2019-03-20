package kr.co.konghr.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.context.ApplicationContext;
import kr.co.konghr.common.servlet.mapper.SimpleUrlHandlerMapping;
import kr.co.konghr.common.servlet.mvc.Controller;
import kr.co.konghr.common.servlet.view.InternalResourceViewResolver;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet{
	private ApplicationContext applicationContext;
	private SimpleUrlHandlerMapping simpleUrlHandlerMapping;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		applicationContext=new ApplicationContext(this.getServletContext(),this.getServletConfig());
		simpleUrlHandlerMapping=SimpleUrlHandlerMapping.getInstance(this.getServletContext());
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		Controller controller=simpleUrlHandlerMapping.getController(applicationContext, request); 
		ModelAndView modelAndView=controller.handleRequest(request, response);
		
		System.out.println("MAV:"+modelAndView);
		if(modelAndView!=null)
			InternalResourceViewResolver.getInstance(this.getServletContext()).resolveView(modelAndView, request, response);
	}
}
