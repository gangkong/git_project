package kr.co.konghr.hr.salary.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.konghr.hr.salary.to.MonthSalaryTO;

public interface MonthSalaryDAO {
	public ArrayList<MonthSalaryTO> selectYearSalary(String applyYear, String empCode); 
	public HashMap<String, Object> batchMonthSalaryProcess(String applyYearMonth, String empCode);
	public void updateMonthSalary(MonthSalaryTO monthSalary); 
}
