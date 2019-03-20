package kr.co.konghr.base.sf;

import java.util.ArrayList;
import java.util.List;

import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.to.DetailCodeTO;
import kr.co.konghr.base.to.HolidayTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;

public interface BaseServiceFacade {
	public boolean login(String name, String empCode);
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype);
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1,String code2,String code3);
	public ArrayList<HolidayTO> findHolidayList();
	public String findWeekDayCount(String startDate, String endDate);
	public void registEmpImg(String empCode, String imgExtend);
	public void batchDeptProcess(ArrayList<DeptTO> deptto);
	public ArrayList<BaseSalaryTO> findPositionList();
	public void modifyPosition(ArrayList<BaseSalaryTO> positionList);
	public ArrayList<CodeTO> findCodeList();
	void registCodeList(List<HolidayTO> holyday);
}
