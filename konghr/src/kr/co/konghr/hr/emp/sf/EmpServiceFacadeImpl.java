package kr.co.konghr.hr.emp.sf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.exception.PwMissMatchException;
import kr.co.konghr.base.to.DeptTO;
import kr.co.konghr.base.exception.IdNotFoundException;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationService;
import kr.co.konghr.hr.emp.applicationService.EmpApplicationServiceImpl;
import kr.co.konghr.hr.emp.to.EmpTO;

public class EmpServiceFacadeImpl implements EmpServiceFacade {
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	EmpApplicationService empApplicationService = EmpApplicationServiceImpl.getInstance();
	private static EmpServiceFacadeImpl instance = new EmpServiceFacadeImpl();

	private EmpServiceFacadeImpl() {
	}

	public static EmpServiceFacadeImpl getInstance() {
		return instance;
	}

	@Override
	public EmpTO getEmp(String name) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" getEmp 시작 ");
		}
		EmpTO empto = null;
		dataSourceTransactionManager.beginTransaction();
		try {
			empto = empApplicationService.selectEmp(name);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" getEmp 종료 ");
		}
		return empto;
	}

	@Override
	public String findLastEmpCode() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findLastEmpCode 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			String empCode = empApplicationService.findLastEmpCode();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" findLastEmpCode 종료 ");
			}
			return empCode;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public void registEmployee(EmpTO empto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmployee 시작 ");
		}
		
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.registEmployee(empto);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" registEmployee 종료 ");
			}
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}		
	}

	@Override
	public List<EmpTO> findEmpList(String dept) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findEmployeeListByDept 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<EmpTO> empList = empApplicationService.findEmployeeListByDept(dept);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" findEmployeeListByDept 종료 ");
			}
			return empList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public EmpTO findAllEmpInfo(String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findAllEmpInfo 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			
			EmpTO empTO = empApplicationService.findAllEmployeeInfo(empCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" findAllEmpInfo 종료 ");
			}
			
			return empTO;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public void modifyEmployee(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyEmployee 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.modifyEmployee(emp);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyEmployee 종료 ");
		}		
	}

	@Override
	public void deleteEmpList(ArrayList<EmpTO> empList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteEmpList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.deleteEmpList(empList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteEmpList 종료 ");
		}		
	}

	@Override
	public ArrayList<DeptTO> findDeptList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDeptList 시작 ");
		}
		ArrayList<DeptTO> deptList = empApplicationService.findDeptList();
		dataSourceTransactionManager.commitTransaction();
		if (logger.isDebugEnabled()) {
			logger.debug(" findDeptList 종료 ");
		}
		return deptList;
	}


}
