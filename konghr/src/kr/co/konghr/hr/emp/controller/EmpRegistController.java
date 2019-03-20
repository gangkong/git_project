package kr.co.konghr.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.EmpTO;
import net.sf.json.JSONObject;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;

public class EmpRegistController extends MultiActionController{
	private static EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;
	
	public ModelAndView registEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			EmpTO emp=new EmpTO();
			emp.setEmpName(request.getParameter("emp_name"));
			emp.setDeptName(request.getParameter("dept_name"));
			emp.setPosition(request.getParameter("position"));
			emp.setGender(request.getParameter("gender"));
			emp.setMobileNumber(request.getParameter("mobile_number"));
			emp.setEmpCode(request.getParameter("emp_code"));
			emp.setAddress(request.getParameter("address"));
			emp.setDetailAddress(request.getParameter("detail_address"));
			emp.setBirthdate(request.getParameter("birthday"));
			emp.setPostNumber(request.getParameter("post_number"));
			emp.setImgExtend(request.getParameter("img_extend"));
			emp.setLastSchool(request.getParameter("last_school"));
			emp.setEmail(request.getParameter("email"));
			
			empServiceFacade.registEmployee(emp);
			
			
			

			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("errorMsg", request.getParameter("emp_name")+" 사원이 등록되었습니다.");
			modelAndView=new ModelAndView("/emp/empRegist",model);
			
			
		} catch (DataAccessException dae){
			logger.fatal(dae.getMessage());
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("errorMsg", "사원 등록에 실패했습니다 : "+dae.getMessage());
			modelAndView=new ModelAndView("/emp/empRegist",model);
			
		}

		return modelAndView;
	}
	
	public ModelAndView findLastEmpCode(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			String empCode = empServiceFacade.findLastEmpCode();
			model.put("lastEmpCode", empCode);
			model.put("errorMsg","success");
			model.put("errorCode", 0);

			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println(jsonObject);
		} catch (IOException ioe) {
			logger.fatal(ioe.getMessage());
			String viewname = "redirect:welcome.html";
			model.clear();
			model.put("errorMsg", ioe.getMessage());
			modelAndView = new ModelAndView(viewname, model);
		} catch (DataAccessException dae){
			logger.fatal(dae.getMessage());
			model.clear();
			model.put("errorCode", -1);
			model.put("errorMsg", dae.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
		} finally {
			out.close();
		}
		return modelAndView;
	}
	
}
