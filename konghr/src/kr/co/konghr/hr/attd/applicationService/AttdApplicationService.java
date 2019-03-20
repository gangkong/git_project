package kr.co.konghr.hr.attd.applicationService;

import java.util.ArrayList;

import kr.co.konghr.hr.attd.to.DayAttdMgtTO;
import kr.co.konghr.hr.attd.to.DayAttdTO;
import kr.co.konghr.hr.attd.to.MonthAttdMgtTO;
import kr.co.konghr.hr.attd.to.RestAttdTO;

public interface AttdApplicationService {
	public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay);
	public void registDayAttd(DayAttdTO dayAttd);
	public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList);

	public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay, String dept);
	public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList);
	public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth);
	public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList);
	
	public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code);
	public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate);
	public RestAttdTO findRestAttdListByToday(String empCode, String toDay);
	public void registRestAttd(RestAttdTO restAttd);
	public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList);
	public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList);
}
