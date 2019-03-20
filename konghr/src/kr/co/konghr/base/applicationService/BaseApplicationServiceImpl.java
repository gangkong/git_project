package kr.co.konghr.base.applicationService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.dao.CodeDAO;
import kr.co.konghr.base.dao.CodeDAOImpl;
import kr.co.konghr.base.dao.DeptDAO;
import kr.co.konghr.base.dao.DeptDAOImpl;
import kr.co.konghr.base.dao.DetailCodeDAO;
import kr.co.konghr.base.dao.DetailCodeDAOImpl;
import kr.co.konghr.base.dao.HolidayDAO;
import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.to.DetailCodeTO;
import kr.co.konghr.base.to.HolidayTO;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationService;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationServiceImpl;
import kr.co.konghr.hr.emp.sf.EmpServiceFacade;
import kr.co.konghr.hr.emp.sf.EmpServiceFacadeImpl;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.salary.dao.BaseSalaryDAO;
import kr.co.konghr.hr.salary.dao.BaseSalaryDAOImpl;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;
import kr.co.konghr.base.dao.HolidayDAOImpl;

public class BaseApplicationServiceImpl implements BaseApplicationService {
	protected final Log logger = LogFactory.getLog(this.getClass());

	EmpApplicationService empApplicationService = EmpApplicationServiceImpl.getInstance();

	EmpServiceFacade empServiceFasade = EmpServiceFacadeImpl.getInstance();
	private DetailCodeDAO detailCodeDAO = DetailCodeDAOImpl.getInstance();
	private HolidayDAO holidayDAO = HolidayDAOImpl.getInstance();
	private DeptDAO deptDAO = DeptDAOImpl.getInstance();
	private BaseSalaryDAO baseSalaryDAO = BaseSalaryDAOImpl.getInstance();
	private CodeDAO codeDAO = CodeDAOImpl.getInstance();

	private static BaseApplicationService instance;

	private BaseApplicationServiceImpl() {
	}

	public static BaseApplicationService getInstance() {
		if (instance == null)
			instance = new BaseApplicationServiceImpl();
		return instance;
	}

	public boolean loginEmployee(String name, String empCode) throws IdNotFoundException, PwMissMatchException {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" loginEmployee 시작 ");
		}

		EmpTO emp = empServiceFasade.getEmp(name); // empName으로 사원의 정보를 찾는다

		if (emp == null) {
			if (logger.isDebugEnabled()) {
				logger.debug(" loginEmployee 종료 (MissName)");
			}
			throw new DataAccessException("아이디가 존재하지 않습니다");
		} else {
			if (emp.getEmpCode().equals(empCode)) {
				if (logger.isDebugEnabled()) {
					logger.debug(" loginEmployee 종료 ");
				}
				return true;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(" loginEmployee 종료 (MissEmpCode)");
				}
				throw new DataAccessException("비밀번호를 확인해주세요");

			}
		}

	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeList 시작 ");
		}
		ArrayList<DetailCodeTO> detailCodeList = null;
		detailCodeList = detailCodeDAO.selectDetailCodeList(codetype);

		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeList 종료 ");
		}
		return detailCodeList;
	}

	@Override
	public void registEmpCode(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpCode 시작 ");
		}
		DetailCodeTO detailCodeto = new DetailCodeTO();
		detailCodeto.setDetailCodeNumber(emp.getEmpCode());
		detailCodeto.setDetailCodeName(emp.getEmpName());
		detailCodeto.setCodeNumber("CO-17");
		detailCodeto.setDetailCodeNameusing("N");
		detailCodeDAO.registDetailCode(detailCodeto);

		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpCode 종료 ");
		}
	}

	@Override
	public void deleteEmpCode(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteEmpCode 시작 ");
		}
		DetailCodeTO detailCodeto = new DetailCodeTO();
		detailCodeto.setDetailCodeNumber(emp.getEmpCode());
		detailCodeto.setDetailCodeName(emp.getEmpName());
		detailCodeDAO.deleteDetailCode(detailCodeto);

		if (logger.isDebugEnabled()) {
			logger.debug(" deleteEmpCode 종료 ");
		}

	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeListRest 시작 ");
		}
		ArrayList<DetailCodeTO> detailCodeList = null;
		detailCodeList = detailCodeDAO.selectDetailCodeListRest(code1, code2, code3);

		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeListRest 종료 ");
		}
		return detailCodeList;
	}

	@Override
	public ArrayList<HolidayTO> findHolidayList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 시작 ");
		}

		ArrayList<HolidayTO> holidayList = holidayDAO.selectHolidayList();

		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 종료 ");
		}
		return holidayList;
	}

	@Override
	public void batchDeptProcess(ArrayList<DeptTO> deptto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {

			logger.debug(" batchDeptProcess 시작 ");

		}
		DetailCodeTO detailCodeto = new DetailCodeTO();

		for (DeptTO dept : deptto) {
			switch (dept.getStatus()) {

			case "update":
				deptDAO.updateDept(dept);
				detailCodeto.setDetailCodeNumber(dept.getDeptCode());
				detailCodeto.setDetailCodeName(dept.getDeptName());
				detailCodeto.setCodeNumber("CO-07");
				detailCodeto.setDetailCodeNameusing("Y");
				detailCodeDAO.updateDetailCode(detailCodeto);
				break;

			case "insert":
				deptDAO.registDept(dept);
				detailCodeto.setDetailCodeNumber(dept.getDeptCode());
				detailCodeto.setDetailCodeName(dept.getDeptName());
				detailCodeto.setCodeNumber("CO-07");
				detailCodeto.setDetailCodeNameusing("Y");
				detailCodeDAO.registDetailCode(detailCodeto);
				break;

			case "delete":
				deptDAO.deleteDept(dept);
				detailCodeto.setDetailCodeNumber(dept.getDeptCode());
				detailCodeto.setDetailCodeName(dept.getDeptName());
				detailCodeDAO.deleteDetailCode(detailCodeto);
				break;

			case "normal":
				break;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" batchDeptProcess 종료 ");
		}

	}

	@Override
	public void modifyPosition(ArrayList<BaseSalaryTO> positionList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyPosition 시작 ");
		}

		if (positionList != null && positionList.size() > 0) {
			for (BaseSalaryTO position : positionList) {
				DetailCodeTO detailCodeto = new DetailCodeTO();
				switch (position.getStatus()) {

				case "update":
					baseSalaryDAO.updatePosition(position);
					detailCodeto.setDetailCodeNumber(position.getPositionCode());
					detailCodeto.setDetailCodeName(position.getPosition());
					detailCodeto.setCodeNumber("CO-04");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeDAO.updateDetailCode(detailCodeto);
					break;

				case "insert":
					baseSalaryDAO.insertPosition(position);
					detailCodeto.setDetailCodeNumber(position.getPositionCode());
					detailCodeto.setDetailCodeName(position.getPosition());
					detailCodeto.setCodeNumber("CO-04");
					detailCodeto.setDetailCodeNameusing("Y");
					detailCodeDAO.registDetailCode(detailCodeto);
					break;

				case "delete":
					baseSalaryDAO.deletePosition(position);
					detailCodeto.setDetailCodeNumber(position.getPositionCode());
					detailCodeto.setDetailCodeName(position.getPosition());
					detailCodeDAO.deleteDetailCode(detailCodeto);
					break;
				}
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyPosition 종료 ");
		}
	}

	@Override
	public String findWeekDayCount(String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 시작 ");
		}

		String weekdayCount = holidayDAO.selectWeekDayCount(startDate, endDate);

		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 종료 ");
		}
		return weekdayCount;
	}

	@Override
	public void registEmpImg(String empCode, String imgExtend) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpImg 시작 ");
		}

		EmpTO emp = empApplicationService.findAllEmployeeInfo(empCode);
		if (emp == null) {
			emp = new EmpTO();
			emp.setEmpCode(empCode);
			emp.setStatus("insert");
		} else {
			emp.setStatus("update");
		}
		emp.setImgExtend(imgExtend);
		empApplicationService.modifyEmployee(emp);

		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpImg 종료 ");
		}
	}

	@Override
	public ArrayList<CodeTO> findCodeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findCodeList 시작 ");
		}

		ArrayList<CodeTO> codeList = codeDAO.selectCode();

		if (logger.isDebugEnabled()) {
			logger.debug(" findCodeList 종료 ");
		}
		return codeList;
	}

	@Override
	public void registCodeList(List<HolidayTO> holyday) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" 어플리 ");
		}
		for (HolidayTO holiday : holyday) {
			switch (holiday.getStatus()) {

			case "update":
				holidayDAO.updateCodeList(holiday);
				break;
			
			case "insert":
				holidayDAO.insertCodeList(holiday);
				break;

			case "delete":
				holidayDAO.deleteCodeList(holiday);
				break;

			}
		}
	}

}