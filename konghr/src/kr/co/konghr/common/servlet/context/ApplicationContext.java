package kr.co.konghr.common.servlet.context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import kr.co.konghr.common.servlet.mvc.Controller;
public class ApplicationContext {
	private HashMap<String,Object> beans;
	public ApplicationContext(ServletContext application, ServletConfig config){
		beans=new HashMap<String,Object>();
		String filename=application.getRealPath(config.getInitParameter("configFile"));
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
			String classname=properties.getProperty((String)key);
			Object controller=null;
			try {
				controller = Class.forName(classname).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			beans.put((String)key,controller);
		}
	}
	public Controller getBean(String beanName){
		return (Controller)beans.get(beanName);
	}
}
