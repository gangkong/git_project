package kr.co.konghr.hr.emp.applicationService;

import java.util.ArrayList;

import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;

public interface EmpApplicationService {
	public EmpTO selectEmp(String name);
	public String findLastEmpCode();
	public void registEmployee(EmpTO emp);
	public ArrayList<EmpTO> findEmployeeListByDept(String deptName);
	public EmpTO findAllEmployeeInfo(String empCode);
	public void modifyEmployee(EmpTO emp);
	public void deleteEmpList(ArrayList<EmpTO> empList);
	public ArrayList<DeptTO> findDeptList();

	public ArrayList<BaseSalaryTO> selectPositionList();
}
