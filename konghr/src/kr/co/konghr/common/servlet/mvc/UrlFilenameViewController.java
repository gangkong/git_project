package kr.co.konghr.common.servlet.mvc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;

public class UrlFilenameViewController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response){
		String uri=request.getRequestURI();	
		String contextPath=request.getContextPath();	
		int beginIndex=contextPath.length();
		int endIndex=uri.lastIndexOf(".");
		String viewname=uri.substring(beginIndex,endIndex);
		HashMap<String, Object> modelObject=null;
		ModelAndView modelAndView=new ModelAndView(viewname,modelObject);
		return modelAndView;
	}
}
