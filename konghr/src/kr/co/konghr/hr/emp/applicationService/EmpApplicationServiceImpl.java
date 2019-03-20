package kr.co.konghr.hr.emp.applicationService;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.applicationService.BaseApplicationServiceImpl;
import kr.co.konghr.base.dao.DeptDAO;
import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.hr.emp.dao.CareerInfoDAO;
import kr.co.konghr.hr.emp.dao.CareerInfoDAOImpl;
import kr.co.konghr.hr.emp.dao.EducationInfoDAO;
import kr.co.konghr.hr.emp.dao.EducationInfoDAOImpl;
import kr.co.konghr.hr.emp.dao.EmpDAO;
import kr.co.konghr.hr.emp.dao.EmpDAOImpl;
import kr.co.konghr.hr.emp.dao.FamilyInfoDAO;
import kr.co.konghr.hr.emp.dao.FamilyInfoDAOImpl;
import kr.co.konghr.hr.emp.dao.LicenseInfoDAO;
import kr.co.konghr.hr.emp.dao.LicenseInfoDAOImpl;
import kr.co.konghr.hr.emp.dao.WorkInfoDAO;
import kr.co.konghr.hr.emp.dao.WorkInfoDAOImpl;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.CareerInfoTO;
import kr.co.konghr.hr.emp.to.EducationInfoTO;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.emp.to.FamilyInfoTO;
import kr.co.konghr.hr.emp.to.LicenseInfoTO;
import kr.co.konghr.hr.emp.to.WorkInfoTO;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationService;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationServiceImpl;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;
import kr.co.konghr.base.dao.DeptDAOImpl;
import net.sf.json.JSONObject;

public class EmpApplicationServiceImpl implements EmpApplicationService {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/*BaseApplicationService baseApplication = BaseApplicationServiceImpl.getInstance();*/

	private EmpDAO empDAO = EmpDAOImpl.getInstance();
	private WorkInfoDAO workInfoDAO = WorkInfoDAOImpl.getInstance();
	private CareerInfoDAO careerInfoDAO = CareerInfoDAOImpl.getInstance();
	private EducationInfoDAO educationInfoDAO = EducationInfoDAOImpl.getInstance();
	private LicenseInfoDAO licenseInfoDAO = LicenseInfoDAOImpl.getInstance();
	private FamilyInfoDAO familyInfoDAO = FamilyInfoDAOImpl.getInstance();
	EmpServiceFacade empServiceFasade = EmpServiceFacadeImpl.getInstance();
	DeptDAO deptDAO = DeptDAOImpl.getInstance();
	SalaryApplicationService salaryApplication = SalaryApplicationServiceImpl.getInstance();
	
	private static EmpApplicationService instance;

	private EmpApplicationServiceImpl() {
	}

	public static EmpApplicationService getInstance() {
		if (instance == null)
			instance = new EmpApplicationServiceImpl();
		return instance;
	}

	public String getEmpCode(String name) {
		if (logger.isDebugEnabled()) {
			logger.debug(" getEmpCode 시작 ");
		}
		String empCode = empDAO.getEmpCode(name);

		if (logger.isDebugEnabled()) {
			logger.debug(" getEmpCode 종료 ");
		}
		return empCode;
	}
	
	@Override
	public EmpTO selectEmp(String name) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmp 사용 ");
		}
		EmpTO empto = EmpDAOImpl.getInstance().selectEmp(name);
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmp 종료 ");
		}
		return empto;
	}

	@Override
	public String findLastEmpCode() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findLastEmpCode 시작 ");
		}
		String empCode = empDAO.selectLastEmpCode();
		if (logger.isDebugEnabled()) {
			logger.debug(" findLastEmpCode 종료 ");
		}
		return empCode;
	}

	@Override
	public void registEmployee(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmployee 시작 ");
		}
		
			empDAO.registEmployee(emp);
			BaseApplicationServiceImpl.getInstance().registEmpCode(emp);
	
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmployee 종료 ");
		}		
	}

	@Override
	public ArrayList<EmpTO> findEmployeeListByDept(String deptName) {
		// TODO Auto-generated method stub
		ArrayList<EmpTO> empList = null;
		String empCode = null;
		if (logger.isDebugEnabled()) {
			logger.debug(" findEmployeeListByDept 시작 ");
		}

		if (deptName.equals("전체부서")) {
			empList = empDAO.selectEmpList();
		} else if (deptName.substring(deptName.length()-1, deptName.length()).equals("팀")) {
			empList = empDAO.selectEmpListD(deptName);
		} else {
			empList = empDAO.selectEmpListN(deptName);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" findEmployeeListByDept 종료 ");
		}
		return empList;
	}

	@Override
	public EmpTO findAllEmployeeInfo(String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findAllEmployeeInfo 시작 ");
		}
		EmpTO empTO = empDAO.selectEmployee(empCode);

		ArrayList<WorkInfoTO> workInfoList = workInfoDAO.selectWorkList(empCode);
		ArrayList<CareerInfoTO> careerInfoList = careerInfoDAO.selectCareerList(empCode);
		ArrayList<EducationInfoTO> educationInfoList = educationInfoDAO.selectEducationList(empCode);
		ArrayList<LicenseInfoTO> licenseInfoList = licenseInfoDAO.selectLicenseList(empCode);
		ArrayList<FamilyInfoTO> familyInfoList = familyInfoDAO.selectFamilyList(empCode);
		empTO.setWorkInfoList(workInfoList);
		empTO.setCareerInfoList(careerInfoList);
		empTO.setEducationInfoList(educationInfoList);
		empTO.setLicenseInfoList(licenseInfoList);
		empTO.setFamilyInfoList(familyInfoList);

		if (logger.isDebugEnabled()) {
			logger.debug(" findAllEmployeeInfo 종료 ");
		}
		return empTO;
	}

	@Override
	public void modifyEmployee(EmpTO emp) {
		// TODO Auto-generated method stub

		if (logger.isDebugEnabled()) {
			logger.debug(" modifyEmployee 시작 ");
		}
		if (emp.getStatus().equals("update")) {
			empDAO.updateEmployee(emp);
		}
		
		if (emp.getWorkInfoList() != null) {
			ArrayList<WorkInfoTO> workInfoList = emp.getWorkInfoList();
			for (WorkInfoTO workInfo : workInfoList) {
				
				switch (workInfo.getStatus()) {
				case "insert":
					workInfoDAO.insertWorkInfo(workInfo);
					break;
				case "update":
					workInfoDAO.updateWorkInfo(workInfo);
					break;
				case "delete":
					workInfoDAO.deleteWorkInfo(workInfo);
					break;
				}
			}
		}
		
		
		if (emp.getCareerInfoList() != null && emp.getCareerInfoList().size() > 0) {
			ArrayList<CareerInfoTO> careerInfoList = emp.getCareerInfoList();
			for (CareerInfoTO careerInfo : careerInfoList) {
				switch (careerInfo.getStatus()) {
				case "insert":
					careerInfoDAO.insertCareerInfo(careerInfo);
					break;
				case "update":
					careerInfoDAO.updateCareerInfo(careerInfo);
					break;
				case "delete":
					careerInfoDAO.deleteCareerInfo(careerInfo);
					break;
				}
			}
		}
		
		
		if (emp.getEducationInfoList() != null && emp.getEducationInfoList().size() > 0) {
			ArrayList<EducationInfoTO> educationInfoList = emp.getEducationInfoList();
			for (EducationInfoTO educationInfo : educationInfoList) {
				switch (educationInfo.getStatus()) {
				case "insert":
					educationInfoDAO.insertEducationInfo(educationInfo);
					break;
				case "update":
					educationInfoDAO.updateEducationInfo(educationInfo);
					break;
				case "delete":
					educationInfoDAO.deleteEducationInfo(educationInfo);
					break;
				}
			}
		}
		
		
		if (emp.getLicenseInfoList() != null && emp.getLicenseInfoList().size() > 0) {
			ArrayList<LicenseInfoTO> licenseInfoList = emp.getLicenseInfoList();
			for (LicenseInfoTO licenseInfo : licenseInfoList) {
				switch (licenseInfo.getStatus()) {
				case "insert":
					licenseInfoDAO.insertLicenseInfo(licenseInfo);
					break;
				case "update":
					licenseInfoDAO.updateLicenseInfo(licenseInfo);
					break;
				case "delete":
					licenseInfoDAO.deleteLicenseInfo(licenseInfo);
					break;
				}
			}
		}
		
		
		if (emp.getFamilyInfoList() != null && emp.getFamilyInfoList().size() > 0) {
			ArrayList<FamilyInfoTO> familyInfoList = emp.getFamilyInfoList();
			for (FamilyInfoTO familyInfo : familyInfoList) {
				switch (familyInfo.getStatus()) {
				case "insert":
					familyInfoDAO.insertFamilyInfo(familyInfo);
					break;
				case "update":
					familyInfoDAO.updateFamilyInfo(familyInfo);
					break;
				case "delete":
					familyInfoDAO.deleteFamilyInfo(familyInfo);
					break;
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" modifyEmployee 종료 ");
		}
	}

	@Override
	public void deleteEmpList(ArrayList<EmpTO> empList) {
		// TODO Auto-generated method stub
		  if (logger.isDebugEnabled()) {
		  logger.debug(" deleteEmpList 시작 "); } 
		  for(EmpTO emp : empList){
			  empDAO.deleteEmployee(emp);
			  BaseApplicationServiceImpl.getInstance().deleteEmpCode(emp);
		  } 
		  if (logger.isDebugEnabled()) {
		  logger.debug(" deleteEmpList 종료 "); }		
	}

	@Override
	public ArrayList<DeptTO> findDeptList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDeptList 시작 ");
		}
		ArrayList<DeptTO> deptList = deptDAO.selectDeptList();
		if (logger.isDebugEnabled()) {
			logger.debug(" findDeptList 종료 ");
		}
		return deptList;
	}

	@Override
	public ArrayList<BaseSalaryTO> selectPositionList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteDept 시작 ");
		}
		ArrayList<BaseSalaryTO> positionList = salaryApplication.findBaseSalaryList();
		
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteDept 종료 ");
		}
		
		return positionList;
	}

}
