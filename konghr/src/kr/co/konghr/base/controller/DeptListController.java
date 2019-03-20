package kr.co.konghr.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.konghr.base.sf.BaseServiceFacade;
import kr.co.konghr.base.sf.BaseServiceFacadeImpl;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.to.HolidayTO;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationService;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationServiceImpl;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import net.sf.json.JSONObject;

public class DeptListController extends MultiActionController {	
	
	public ModelAndView batchDeptProcess(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String sendData = request.getParameter("sendData");
		PrintWriter out=null;
		Gson gson = new Gson();
		ArrayList<DeptTO> deptto = gson.fromJson(sendData, new TypeToken<ArrayList<DeptTO>>(){}.getType());
		
		String viewName = "";
		
		try {
			BaseServiceFacade baseServiceFacadeImpl = BaseServiceFacadeImpl.getInstance();
			out = response.getWriter();
			baseServiceFacadeImpl.batchDeptProcess(deptto);
			
			map.put("errorCode", 0);
			map.put("errorMsg", request.getParameter("deptName")+" 부서가 등록/삭제가 완료되었습니다.");
			JSONObject jsonobject = JSONObject.fromObject(map);
			out.println(jsonobject);
			
			
		} catch (Exception e) {
			viewName = "error";
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
			
			JSONObject jsonobject = JSONObject.fromObject(map);
			out.println(jsonobject);
			
		}
		ModelAndView modelAndView = new ModelAndView(viewName, map);
		return null;
		
		
	}
	
	public ModelAndView findDeptList(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String viewName = "/base/deptList";
		try {
			EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
			
			List<DeptTO> list = empServiceFacade.findDeptList();
			DeptTO emptyBean = new DeptTO();
			map.put("emptyBean", emptyBean);
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
		ModelAndView modelAndView = new ModelAndView(viewName, map);
		return null;
	}
		
}
