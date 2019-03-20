package kr.co.konghr.base.sf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.applicationService.BaseApplicationService;
import kr.co.konghr.base.applicationService.BaseApplicationServiceImpl;
import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.to.DetailCodeTO;
import kr.co.konghr.base.to.HolidayTO;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationService;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationServiceImpl;
import kr.co.konghr.hr.emp.to.EmpTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;

public class BaseServiceFacadeImpl implements BaseServiceFacade {
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	BaseApplicationService baseApplicationService = BaseApplicationServiceImpl.getInstance();
	EmpApplicationService empApplicationService = EmpApplicationServiceImpl.getInstance();
	private static BaseServiceFacade instance;

	private BaseServiceFacadeImpl() {
	}

	public static BaseServiceFacade getInstance() {
		if (instance == null)
			instance = new BaseServiceFacadeImpl();
		return instance;
	}

	@Override
	public boolean login(String name, String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug(" login 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {

			boolean check = baseApplicationService.loginEmployee(name, empCode);

			if (logger.isDebugEnabled()) {
				logger.debug(" login 종료 ");
			}
			dataSourceTransactionManager.commitTransaction();

			return check;

		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} catch (IdNotFoundException e) {
			throw new DataAccessException("아이디를 확인해주세요");
		} catch (PwMissMatchException e) {
			throw new DataAccessException("비밀번호를 확인해주세요");
		} finally {
			dataSourceTransactionManager.closeConnection();
		}

	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeList(String codetype) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeList 시작 ");
		}
		ArrayList<DetailCodeTO> detailCodeto = baseApplicationService.findDetailCodeList(codetype);
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeList 종료 ");
		}
		return detailCodeto;
	}

	@Override
	public ArrayList<DetailCodeTO> findDetailCodeListRest(String code1, String code2, String code3) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeListRest 시작 ");
		}
		ArrayList<DetailCodeTO> detailCodeto = baseApplicationService.findDetailCodeListRest(code1, code2, code3);
		if (logger.isDebugEnabled()) {
			logger.debug(" findDetailCodeListRest 종료 ");
		}
		return detailCodeto;
	}

	@Override
	public ArrayList<HolidayTO> findHolidayList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<HolidayTO> holidayList = baseApplicationService.findHolidayList();
			if (logger.isDebugEnabled()) {
				logger.debug(" findHolidayList 종료 ");
			}
			return holidayList;
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public String findWeekDayCount(String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findWeekdayList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {

			String weekdayCount = baseApplicationService.findWeekDayCount(startDate, endDate);
			if (logger.isDebugEnabled()) {
				logger.debug(" findWeekdayList 종료 ");
			}
			return weekdayCount;
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public void registEmpImg(String empCode, String imgExtend) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpImg 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			baseApplicationService.registEmpImg(empCode, imgExtend);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" registEmpImg 종료 ");
		}
	}

	@Override
	public void batchDeptProcess(ArrayList<DeptTO> deptto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" batchDeptProcess 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			baseApplicationService.batchDeptProcess(deptto);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" batchDeptProcess 종료 ");
		}

	}

	@Override
	public ArrayList<BaseSalaryTO> findPositionList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 시작 ");
		}

		ArrayList<BaseSalaryTO> positionList = empApplicationService.selectPositionList();

		if (logger.isDebugEnabled()) {
			logger.debug(" findHolidayList 종료 ");
		}
		return positionList;
	}

	@Override
	public void modifyPosition(ArrayList<BaseSalaryTO> positionList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyPosition 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			baseApplicationService.modifyPosition(positionList);

			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyPosition 종료 ");
		}
	}

	@Override
	public ArrayList<CodeTO> findCodeList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findCodeList 시작 ");
		}
		ArrayList<CodeTO> codeto = baseApplicationService.findCodeList();
		if (logger.isDebugEnabled()) {
			logger.debug(" findCodeList 종료 ");
		}
		return codeto;
	}

	@Override
	public void registCodeList(List<HolidayTO> holyday) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findWeekdayList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();

		try {
			baseApplicationService.registCodeList(holyday);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" findWeekdayList 종료 ");
			}
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

}
