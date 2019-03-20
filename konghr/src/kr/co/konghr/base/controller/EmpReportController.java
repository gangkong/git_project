package kr.co.konghr.base.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.konghr.common.servlet.ModelAndView;
import kr.co.konghr.common.servlet.mvc.MultiActionController;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class EmpReportController extends MultiActionController {
	private ModelAndView modelAndView;
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	
	public ModelAndView requestEmployment(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("empCode", request.getParameter("empCode"));
		parameters.put("usage", request.getParameter("usage"));
		parameters.put("date", request.getParameter("requestDay"));
		parameters.put("end", request.getParameter("useDay"));
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(request.getServletContext().getRealPath("/report/employment.jrxml"));
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

			dataSourceTransactionManager.beginTransaction();
			Connection con = dataSourceTransactionManager.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
			dataSourceTransactionManager.closeConnection();

			outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			outputStream.flush();

		} catch (Exception e) {
			logger.fatal(e.getMessage());
		} finally {
			try {
				if(inputStream != null){
					inputStream.close();
					inputStream = null;
				}
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
			} catch (Exception e) {
				logger.fatal(e.getMessage());
			}
		}

		return modelAndView;
	}
	
	public ModelAndView requestMonthSalary(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("empCode", request.getParameter("empCode"));
		parameters.put("applyMonth", request.getParameter("applyMonth"));
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(request.getServletContext().getRealPath("/report/SalaryStatement.jrxml"));
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

			dataSourceTransactionManager.beginTransaction();
			Connection con = dataSourceTransactionManager.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
			dataSourceTransactionManager.closeConnection();

			outputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			outputStream.flush();

		} catch (Exception e) {
			logger.fatal(e.getMessage());
		} finally {
			try {
				if(inputStream != null){
					inputStream.close();
					inputStream = null;
				}
				if(outputStream != null){
					outputStream.close();
					outputStream = null;
				}
			} catch (Exception e) {
				logger.fatal(e.getMessage());
			}
		}

		return modelAndView;
	}

}