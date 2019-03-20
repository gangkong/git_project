package kr.co.konghr.common.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;

public class MultiActionController extends AbstractController {

	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		String methodName = request.getParameter("method");
		Class<?>[] parameters = new Class<?>[]{HttpServletRequest.class,HttpServletResponse.class};
		Class<?> cl = this.getClass();

		try{
			if (logger.isDebugEnabled()) {
				logger.debug(methodName + " 시작 ");
			}
			ModelAndView modelAndView=(ModelAndView)cl.getMethod(methodName, parameters).invoke(cl.newInstance(),request,response);

			if (logger.isDebugEnabled()) {
				logger.debug(methodName + " 종료 ");
			}
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
