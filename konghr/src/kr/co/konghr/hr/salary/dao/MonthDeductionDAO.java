package kr.co.konghr.hr.salary.dao;

import java.util.ArrayList;

import kr.co.konghr.hr.salary.to.MonthDeductionTO;

public interface MonthDeductionDAO {
	public ArrayList<MonthDeductionTO> selectMonthDeductionList(String applyYearMonth, String empCode);
}
