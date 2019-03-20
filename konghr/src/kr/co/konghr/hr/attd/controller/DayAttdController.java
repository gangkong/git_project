package kr.co.konghr.hr.attd.controller;

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
import kr.co.konghr.hr.attd.sf.AttdServiceFacade;
import kr.co.konghr.hr.attd.sf.AttdServiceFacadeImpl;
import kr.co.konghr.hr.attd.to.DayAttdTO;
import net.sf.json.JSONObject;

public class DayAttdController extends MultiActionController{
	private static AttdServiceFacade attdServiceFacade = AttdServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;

	public ModelAndView findDayAttdList(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HashMap<String, Object> model = new HashMap<String, Object>();
		String applyDay = request.getParameter("applyDay");
		String empCode = request.getParameter("empCode");
		response.setContentType("application/json; charset=UTF-8");
		try {
			out=response.getWriter();

			ArrayList<DayAttdTO> dayAttdList = attdServiceFacade.findDayAttdList(empCode, applyDay);
			model.put("dayAttdList", dayAttdList);
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

	public ModelAndView registDayAttd(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		System.out.println(sendData+"sendData  값");
		response.setContentType("application/json; charset=UTF-8");

		try {
			out=response.getWriter();
			/* 간편하고 성능좋은 gson으로 변경 */			
			Gson gson = new Gson();
			DayAttdTO dayAttd = gson.fromJson(sendData, DayAttdTO.class);
			attdServiceFacade.registDayAttd(dayAttd);
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

	public ModelAndView removeDayAttdList(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HashMap<String, Object> model = new HashMap<String, Object>();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");

		try {
			out=response.getWriter();		
			Gson gson = new Gson();
			ArrayList<DayAttdTO> dayAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<DayAttdTO>>(){}.getType());
			attdServiceFacade.removeDayAttdList(dayAttdList);
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
