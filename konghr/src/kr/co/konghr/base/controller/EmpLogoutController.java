package kr.co.konghr.base.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.AbstractController;

public class EmpLogoutController extends AbstractController {


	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		String viewName="redirect:home.html";
		try{
			request.getSession().invalidate();
		}catch(Exception e){
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			viewName="error";
		}
		ModelAndView modelAndView=new ModelAndView(viewName,map);
		return modelAndView;
	}
}

