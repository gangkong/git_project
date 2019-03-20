package kr.co.konghr.base.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.base.sf.BaseServiceFacade;
import kr.co.konghr.base.sf.BaseServiceFacadeImpl;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.AbstractController;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.EmpTO;

public class EmpLoginController extends AbstractController {
	
	BaseServiceFacade baseServiceFacade = BaseServiceFacadeImpl.getInstance();
	EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
	
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		String viewName="loginform";
		try{
			// TODO Auto-generated method stub
			String name=request.getParameter("name");
			String empCode=request.getParameter("empCode");
			
			if(baseServiceFacade.login(name, empCode)){
				
				EmpTO empto = empServiceFacade.getEmp(name);
				
				request.getSession().setAttribute("id", name);
				request.getSession().setAttribute("dept", empto.getDeptName());
				request.getSession().setAttribute("position", empto.getPosition());
				request.getSession().setAttribute("code", empto.getEmpCode());
				
				viewName="redirect:home.html";
			}
			map=null;
			
		}catch(Exception e){
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			viewName="error";
		}
		
		ModelAndView modelAndView=new ModelAndView(viewName,map);
		System.out.println(viewName);
		return modelAndView;
	}
}
