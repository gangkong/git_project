package kr.co.konghr.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.CareerInfoTO;
import kr.co.konghr.hr.emp.to.EducationInfoTO;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.emp.to.FamilyInfoTO;
import kr.co.konghr.hr.emp.to.LicenseInfoTO;
import kr.co.konghr.hr.emp.to.WorkInfoTO;
import net.sf.json.JSONObject;

public class EmpDetailController extends MultiActionController {
	private static EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;
	
	
	
	public ModelAndView findAllEmployeeInfo(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String empCode = request.getParameter("empCode");
		
		response.setContentType("application/json; charset=UTF-8");
		try{
			
			out=response.getWriter();
			EmpTO empTO=empServiceFacade.findAllEmpInfo(empCode);
			model.put("empBean", empTO);
			FamilyInfoTO familyInfoTO = new FamilyInfoTO();
			CareerInfoTO careerInfoTO = new CareerInfoTO();
			EducationInfoTO educationInfoTO = new EducationInfoTO();
			LicenseInfoTO licenseInfoTO = new LicenseInfoTO();
			WorkInfoTO workInfoTO = new WorkInfoTO();
			model.put("emptyFamilyInfoBean",familyInfoTO );
			model.put("emptyCareerInfoBean", careerInfoTO);
			model.put("emptyEducationInfoBean", educationInfoTO);
			model.put("emptyLicenseInfoBean", licenseInfoTO);
			model.put("emptyWorkInfoBean", workInfoTO);
			model.put("errorMsg","success");
			model.put("errorCode", 0);

			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);

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
		return null;
	}
	
	
	public ModelAndView modifyEmployee(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try{
			out=response.getWriter();
			Gson gson = new Gson();
			EmpTO emp = gson.fromJson(sendData, EmpTO.class);
			empServiceFacade.modifyEmployee(emp);
			model.put("errorMsg","success");
			model.put("errorCode", 0);

			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);

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
	
	public ModelAndView removeEmployeeList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try{
			out=response.getWriter();
			/* 간편하고 성능좋은 gson으로 변경*/ 			
			Gson gson = new Gson();
			ArrayList<EmpTO> empList = gson.fromJson(sendData, new TypeToken<ArrayList<EmpTO>>(){}.getType());
			empServiceFacade.deleteEmpList(empList);
			model.put("errorMsg","success");
			model.put("errorCode", 0);

			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);

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
