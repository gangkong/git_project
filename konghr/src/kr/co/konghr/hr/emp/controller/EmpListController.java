package kr.co.konghr.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import kr.co.konghr.common.to.ListForm;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.EmpTO;
import net.sf.json.JSONObject;

public class EmpListController extends MultiActionController {
	private static EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
	PrintWriter out = null;

	public ModelAndView emplist(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		String viewName = null;
		try {
			
			String value = "전체부서";

			if (request.getParameter("value") != null)
				value = (String) request.getParameter("value");
			
			
			ArrayList<EmpTO> list = (ArrayList<EmpTO>) empServiceFacade.findEmpList(value);
			
			
			map.put("list", list);

			JSONObject jsonobject = JSONObject.fromObject(map);
			PrintWriter out = response.getWriter();
			out.println(jsonobject);
			System.out.println(jsonobject);

		} catch (Exception e) {
			viewName = "error";
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			JSONObject jsonobject = JSONObject.fromObject(map);
			try {
				PrintWriter out = response.getWriter();
				out.println(jsonobject);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		ModelAndView modelAndView = null;
		return modelAndView;
	}
	
	/*public ModelAndView workInfoList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		String viewName = null;
		try {
			String code = request.getParameter("code");

			EmpServiceFacadeImpl sf = EmpServiceFacadeImpl.getInstance();

			ListForm listForm = new ListForm();

			
			ArrayList<EmpTO> list = sf.workInfoList(code);
			map.put("list", list);

			JSONObject jsonobject = JSONObject.fromObject(map);
			PrintWriter out = response.getWriter();
			out.println(jsonobject);
			System.out.println(jsonobject);
		} catch (Exception e) {
			viewName = "error";
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			JSONObject jsonobject = JSONObject.fromObject(map);
			try {
				PrintWriter out = response.getWriter();
				out.println(jsonobject);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		ModelAndView modelAndView = null;
		return null;
	} */
	
}