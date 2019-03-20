package kr.co.konghr.common.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;

public interface Controller {
	public ModelAndView handleRequest(
			HttpServletRequest request,
			HttpServletResponse response);
}
