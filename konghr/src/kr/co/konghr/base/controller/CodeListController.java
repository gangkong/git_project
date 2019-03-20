package kr.co.konghr.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.base.sf.BaseServiceFacade;
import kr.co.konghr.base.sf.BaseServiceFacadeImpl;
import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.base.to.DetailCodeTO;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import net.sf.json.JSONObject;



public class CodeListController extends MultiActionController {
	private static BaseServiceFacade baseServiceFacade = BaseServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;
	
	public void detailCodelist(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> model = new HashMap<String, Object>();	
		String code = request.getParameter("code");
		try {
			out = response.getWriter();
			ArrayList<DetailCodeTO> detailCodeList=baseServiceFacade.findDetailCodeList(code);
			model.put("detailCodeList", detailCodeList);
			model.put("errorMsg","success");
			model.put("errorCode", 0);
			
			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println("!"+jsonObject);
		}catch (DataAccessException dae){
			model.clear();
			model.put("errorCode", -1);
			model.put("errorMsg", dae.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println("de"+jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
	
	
	public void detailCodelistRest(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> model = new HashMap<String, Object>();	
		String code1 = request.getParameter("code1");
		String code2 = request.getParameter("code2");
		String code3 = request.getParameter("code3");
		try {
			out = response.getWriter();
			ArrayList<DetailCodeTO> detailCodeList=baseServiceFacade.findDetailCodeListRest(code1,code2,code3);
			model.put("detailCodeList", detailCodeList);
			model.put("errorMsg","success");
			model.put("errorCode", 0);
			
			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println("!"+jsonObject);
			
		}catch (DataAccessException dae){
			model.clear();
			model.put("errorCode", -1);
			model.put("errorMsg", dae.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(model);
			out.println(jsonObject);
			System.out.println("de"+jsonObject);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
	
	public void codelist(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json; charset=UTF-8");
		HashMap<String, Object> model = new HashMap<String, Object>();	
	
	try {
		out = response.getWriter();
		ArrayList<CodeTO> codeList=baseServiceFacade.findCodeList();
		model.put("codeList", codeList);
		model.put("errorMsg","success");
		model.put("errorCode", 0);
		
		JSONObject jsonObject = JSONObject.fromObject(model);
		out.println(jsonObject);
		System.out.println(jsonObject+"!");
		
	}catch (DataAccessException dae){
		model.clear();
		model.put("errorCode", -1);
		model.put("errorMsg", dae.getMessage());
		JSONObject jsonObject = JSONObject.fromObject(model);
		out.println(jsonObject);
	} catch (IOException e) {
		e.printStackTrace();
	}finally{
		out.close();
	}
	} 

}
