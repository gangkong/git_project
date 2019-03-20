package kr.co.konghr.hr.emp.sf;

import java.util.ArrayList;
import java.util.List;

import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.hr.emp.to.EmpTO;

public interface EmpServiceFacade {
	public EmpTO getEmp(String name);
	public String findLastEmpCode();
	public void registEmployee(EmpTO empto);
	public List<EmpTO> findEmpList(String dept);
	public EmpTO findAllEmpInfo(String empCode);
	public void modifyEmployee(EmpTO emp);
	public void deleteEmpList(ArrayList<EmpTO> empList);
	public ArrayList<DeptTO> findDeptList();
}
