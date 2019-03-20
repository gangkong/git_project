package kr.co.konghr.hr.salary.sf;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationService;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationServiceImpl;
import kr.co.konghr.hr.salary.sf.SalaryServiceFacade;
import kr.co.konghr.hr.salary.sf.SalaryServiceFacadeImpl;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.hr.salary.to.BaseDeductionTO;
import kr.co.konghr.hr.salary.to.BaseExtSalTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;
import kr.co.konghr.hr.salary.to.MonthSalaryTO;                                            

public class SalaryServiceFacadeImpl implements SalaryServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	private SalaryApplicationService salaryApplicationService = SalaryApplicationServiceImpl.getInstance();

	private static SalaryServiceFacade instance;
	private SalaryServiceFacadeImpl(){}
	public static SalaryServiceFacade getInstance(){
		if(instance==null) instance=new SalaryServiceFacadeImpl();
		return instance;
	}
	
	@Override
	public ArrayList<BaseSalaryTO> findBaseSalaryList() {
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseSalaryList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<BaseSalaryTO> baseSalaryList=salaryApplicationService.findBaseSalaryList();
			if (logger.isDebugEnabled()) {
				logger.debug(" findBaseSalaryList 종료 ");
			}
			return baseSalaryList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void modifyBaseSalaryList(ArrayList<BaseSalaryTO> baseSalaryList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseSalaryList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			salaryApplicationService.modifyBaseSalaryList(baseSalaryList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" modifyBaseSalaryList 종료 ");
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
	public ArrayList<BaseDeductionTO> findBaseDeductionList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseDeductionList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<BaseDeductionTO> baseDeductionList=salaryApplicationService.findBaseDeductionList();
			if (logger.isDebugEnabled()) {
				logger.debug(" findBaseDeductionList 종료 ");
			}
			return baseDeductionList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void batchBaseDeductionProcess(ArrayList<BaseDeductionTO> baseDeductionList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" batchBaseDeductionProcess 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			salaryApplicationService.batchBaseDeductionProcess(baseDeductionList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" batchBaseDeductionProcess 종료 ");
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
	public ArrayList<BaseExtSalTO> findBaseExtSalList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseExtSalList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<BaseExtSalTO> baseExtSalList=salaryApplicationService.findBaseExtSalList();
			if (logger.isDebugEnabled()) {
				logger.debug(" findBaseExtSalList 종료 ");
			}
			return baseExtSalList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void modifyBaseExtSalList(ArrayList<BaseExtSalTO> baseExtSalList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseExtSalList 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			salaryApplicationService.modifyBaseExtSalList(baseExtSalList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" modifyBaseExtSalList 종료 ");
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
	public MonthSalaryTO findMonthSalary(String ApplyYearMonth, String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findMonthSalary 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			MonthSalaryTO monthSalary=salaryApplicationService.findMonthSalary(ApplyYearMonth, empCode);
			if (logger.isDebugEnabled()) {
				logger.debug(" findMonthSalary 종료 ");
			}
			return monthSalary;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public ArrayList<MonthSalaryTO> findYearSalary(String applyYear, String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findYearSalary 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<MonthSalaryTO> monthSalary=salaryApplicationService.findYearSalary(applyYear, empCode);
			if (logger.isDebugEnabled()) {
				logger.debug(" findYearSalary 종료 ");
			}
			return monthSalary;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void modifyMonthSalary(MonthSalaryTO monthSalary) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyMonthSalary 시작 ");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			salaryApplicationService.modifyMonthSalary(monthSalary);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug(" modifyMonthSalary 종료 ");
			}
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}		
	}
	
}
