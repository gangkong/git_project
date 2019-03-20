package kr.co.konghr.hr.attd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.co.konghr.hr.attd.sf.AttdServiceFacade;
import kr.co.konghr.hr.attd.sf.AttdServiceFacadeImpl;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import kr.co.konghr.hr.attd.to.RestAttdTO;
import net.sf.json.JSONObject;

public class RestAttdController extends MultiActionController{
	private static AttdServiceFacade attdServiceFacade = AttdServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;

	
/*	public ModelAndView findRejectRestAttdList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String empCode = request.getParameter("empCode");
		String applyYear = request.getParameter("applyYear");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			ArrayList<RestAttdTO> rejectRestAttdList = attdServiceFacade.findRejectRestAttdList(empCode, applyYear);
			model.put("errorMsg","success");
			model.put("errorCode", 0);
			model.put("rejectRestAttdList", rejectRestAttdList);

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
	}*/

	public ModelAndView findRestAttdListByToday(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String empCode = request.getParameter("empCode");
		String toDay = request.getParameter("toDay");
		response.setContentType("application/json; charset=UTF-8");

		try {
			out=response.getWriter();

			RestAttdTO restAttdList = attdServiceFacade.findRestAttdListByToday(empCode,toDay);
			model.put("errorMsg","success");
			model.put("errorCode", 0);
			model.put("restAttdList", restAttdList);

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
	
/*	public ModelAndView findUseAnnualLeaveList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String empCode = request.getParameter("empCode");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		response.setContentType("application/json; charset=UTF-8");
		
		try {
			out=response.getWriter();

			ArrayList<RestAttdTO> useAnnualLeaveList = attdServiceFacade.findUseAnnualLeaveList(empCode,startDate, endDate);
			model.put("errorMsg","success");
			model.put("errorCode", 0);
			model.put("useAnnualLeaveList", useAnnualLeaveList);

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
	}*/

	public ModelAndView registRestAttd(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			
			Gson gson = new Gson();
			RestAttdTO restAttd = gson.fromJson(sendData, RestAttdTO.class);
			attdServiceFacade.registRestAttd(restAttd);
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
	
	public ModelAndView findRestAttdList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String empCode = request.getParameter("empCode");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String code = request.getParameter("code");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			ArrayList<RestAttdTO> restAttdList = attdServiceFacade.findRestAttdList(empCode, startDate, endDate,code);
			model.put("restAttdList", restAttdList);
			model.put("errorMsg","success");
			model.put("errorCode", 0);

			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println("ghkrxlds"+jsonObject);
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

	public ModelAndView removeRestAttdList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			 //간편하고 성능좋은 gson으로 변경 			
			Gson gson = new Gson();
			ArrayList<RestAttdTO> restAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<RestAttdTO>>(){}.getType());
			attdServiceFacade.removeRestAttdList(restAttdList);
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
	
/*	public ModelAndView removeRejectRestAttdList(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();
			// 간편하고 성능좋은 gson으로 변경 			
			Gson gson = new Gson();
			ArrayList<RestAttdTO> rejectRestAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<RestAttdTO>>(){}.getType());
			attdServiceFacade.removeRejectRestAttdList(rejectRestAttdList);
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
	}*/
}
