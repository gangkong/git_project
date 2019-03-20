package kr.co.konghr.base.applicationService;

import java.util.ArrayList;
import java.util.List;

import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;
import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.base.to.DeptTO;
/*import kr.co.konghr.base.to.AddressTO;*/
import kr.co.konghr.base.to.DetailCodeTO;
/*import kr.co.konghr.base.to.HolidayTO;*/
import kr.co.konghr.base.to.HolidayTO;



public interface BaseApplicationService {
   public boolean loginEmployee(String name, String empCode) throws IdNotFoundException, PwMissMatchException; 

   public ArrayList<DetailCodeTO> findDetailCodeList(String codetype);
   public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3);

   public ArrayList<HolidayTO> findHolidayList();
   public String findWeekDayCount(String startDate, String endDate);

   public void registEmpImg(String empCode, String imgExtend);

   public void batchDeptProcess(ArrayList<DeptTO> deptto);
   public void modifyPosition(ArrayList<BaseSalaryTO> positionList);
   
   public void registEmpCode(EmpTO emp);
   public void deleteEmpCode(EmpTO emp);

   public ArrayList<CodeTO> findCodeList();

   public void registCodeList(List<HolidayTO> holyday);
}