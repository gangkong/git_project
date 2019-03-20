package kr.co.konghr.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;



import kr.co.konghr.base.sf.BaseServiceFacade;
import kr.co.konghr.base.sf.BaseServiceFacadeImpl;
import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.AbstractController;
import kr.co.konghr.common.util.FileUploadUtil;
import net.sf.json.JSONObject;

public class EmpImgController extends AbstractController {
	private static BaseServiceFacade baseServiceFacade = BaseServiceFacadeImpl.getInstance();
	private ModelAndView modelAndView = null;
	PrintWriter out = null;
	

	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        RequestContext rc = new ServletRequestContext(request);
        
        String empCode = null;
        String empImgUrl = null;
        String check = (String)request.getSession().getAttribute("newcheck");
        int newCheck = 0;
       

        try {
        	out = response.getWriter();

	        @SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(rc);
	        for (FileItem fileItem : items){
	        	if(fileItem.isFormField()){
	        		if("empCode".equals(fileItem.getFieldName())){
	        			empCode = fileItem.getString();
	        		}
	        		if("newcheck".equals(fileItem.getFieldName())){
	        			check = fileItem.getString();
	        		}
	        	} else {
	        		if((fileItem.getName() != null) && (fileItem.getSize() > 0)){
	        			empImgUrl = FileUploadUtil.doFileUpload(request, fileItem, empCode);
	        		}
	        	}
	        }
	        
	        if("1".equals(check)) {
	        	newCheck = 1;
	        }

	        if(newCheck == 0) {
	        	baseServiceFacade.registEmpImg(empCode, empImgUrl.substring(empImgUrl.lastIndexOf(".")+1));
	        }
	        
	        
            json.put("empImgUrl", empImgUrl);
            json.put("errorCode", 0);
            json.put("errorMsg", "사진 저장에 성공했습니다");
        } catch (FileUploadException e){
        	logger.fatal(e.getMessage());
            json.put("errorCode", -1);
            json.put("errorMsg", "사진 저장에 실패했습니다");
        } catch (IOException e){
        	logger.fatal(e.getMessage());
        	json.put("errorCode", -1);
            json.put("errorMsg", "사진 저장에 실패했습니다");
        }

        out.println(json);
        out.close();
		return modelAndView;
	}

}