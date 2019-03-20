package kr.co.konghr.common.servlet.mapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import kr.co.konghr.common.servlet.context.ApplicationContext;
import kr.co.konghr.common.servlet.mvc.Controller;

public class SimpleUrlHandlerMapping {
	private HashMap<String,String> beanNames;
	private static SimpleUrlHandlerMapping instance;
	private SimpleUrlHandlerMapping(ServletContext application){
		beanNames=new HashMap<String,String>();
		String filename=application.getRealPath(application.getInitParameter("urlmappingFile"));
		Properties properties=new Properties();
				try {
					properties.load(new FileInputStream(filename));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		for(Object key: properties.keySet()){
			String value=properties.getProperty((String)key);
			beanNames.put((String)key,value);	
		}
	}
	public static SimpleUrlHandlerMapping getInstance(ServletContext application){
		if(instance==null) instance=new SimpleUrlHandlerMapping(application);
		return instance;
	}
	public Controller getController(ApplicationContext applicationContext, HttpServletRequest request){
		String uri=request.getRequestURI();	// uri="/spring2/menu.html"
		String contextPath=request.getContextPath();	// contextPath="/spring2" 
		String key=uri.substring(contextPath.length());	// key="/menu.html"
		String beanName=beanNames.get(key);				// beanName="urlFilenameViewController"
		System.out.println("beanName:"+beanName);
		if(beanName==null) beanName="unknownURLController";
		return applicationContext.getBean(beanName);
	}
}

