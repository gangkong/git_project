package kr.co.konghr.hr.attd.sf;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.attd.applicationService.AttdApplicationService;
import kr.co.konghr.hr.attd.applicationService.AttdApplicationServiceImpl;
import kr.co.konghr.hr.attd.sf.AttdServiceFacade;
import kr.co.konghr.hr.attd.sf.AttdServiceFacadeImpl;
import kr.co.konghr.hr.attd.to.DayAttdMgtTO;
import kr.co.konghr.hr.attd.to.DayAttdTO;
import kr.co.konghr.hr.attd.to.MonthAttdMgtTO;
import kr.co.konghr.hr.attd.to.RestAttdTO;

public class AttdServiceFacadeImpl implements AttdServiceFacade{
	protected final Log logger = LogFactory.getLog(this.getClass());

	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	private AttdApplicationService attdApplicationService=AttdApplicationServiceImpl.getInstance();
	/*private AnnualLeaveApplicationService annualLeaveApplicationService=AnnualLeaveApplicationServiceImpl.getInstance();*/
	
	private static AttdServiceFacade instance;
	private AttdServiceFacadeImpl(){}
	public static AttdServiceFacade getInstance(){
		if(instance==null) instance=new AttdServiceFacadeImpl();
		return instance;
	}
	
	@Override
	public ArrayList<DayAttdTO> findDayAttdList(String empCode, String applyDay) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDayAttdList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<DayAttdTO> dayAttdList=attdApplicationService.findDayAttdList(empCode, applyDay);
			if (logger.isDebugEnabled()) {
				logger.debug(" findDayAttdList 종료 ");
			}
			return dayAttdList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void registDayAttd(DayAttdTO dayAttd) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registDayAttd 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.registDayAttd(dayAttd);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" registDayAttd 종료 ");
		}		
	}

	@Override
	public void removeDayAttdList(ArrayList<DayAttdTO> dayAttdList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" removeDayAttdList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.removeDayAttdList(dayAttdList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" removeDayAttdList 종료 ");
		}
	}
	
	@Override
	public ArrayList<RestAttdTO> findRestAttdList(String empCode, String startDate, String endDate, String code) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findRestAttdList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<RestAttdTO> restAttdList = attdApplicationService.findRestAttdList(empCode, startDate, endDate, code);
			if (logger.isDebugEnabled()) {
				logger.debug(" findRestAttdList 종료 ");
			}
			return restAttdList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public ArrayList<RestAttdTO> findRestAttdListByDept(String deptName, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findRestAttdListByDept 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<RestAttdTO> restAttdList = attdApplicationService.findRestAttdListByDept(deptName, startDate, endDate);
			if (logger.isDebugEnabled()) {
				logger.debug(" findRestAttdListByDept 종료 ");
			}
			return restAttdList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public RestAttdTO findRestAttdListByToday(String empCode, String toDay) {
		if (logger.isDebugEnabled()) {
			logger.debug(" findRestAttdListByToday 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			RestAttdTO restAttdList = attdApplicationService.findRestAttdListByToday(empCode,toDay);
			if (logger.isDebugEnabled()) {
				logger.debug(" findRestAttdListByToday 종료 ");
			}
			return restAttdList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public void registRestAttd(RestAttdTO restAttd) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registRestAttd 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.registRestAttd(restAttd);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" registRestAttd 종료 ");
		}		
	}

	@Override
	public void modifyRestAttdList(ArrayList<RestAttdTO> restAttdList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyRestAttdList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.modifyRestAttdList(restAttdList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyRestAttdList 종료 ");
		}
				
	}
	
	@Override
	public void removeRestAttdList(ArrayList<RestAttdTO> restAttdList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" removeRestAttdList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.removeRestAttdList(restAttdList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" removeRestAttdList 종료 ");
		}		
	}
	
	@Override
	public ArrayList<DayAttdMgtTO> findDayAttdMgtList(String applyDay, String dept) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findDayAttdMgtList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<DayAttdMgtTO> dayAttdMgtList = attdApplicationService.findDayAttdMgtList(applyDay, dept);
			if (logger.isDebugEnabled()) {
				logger.debug(" findDayAttdMgtList 종료 ");
			}
			return dayAttdMgtList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}

	@Override
	public void modifyDayAttdMgtList(ArrayList<DayAttdMgtTO> dayAttdMgtList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyDayAttdMgtList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.modifyDayAttdMgtList(dayAttdMgtList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyDayAttdMgtList 종료 ");
		}		
	}
	
	@Override
	public ArrayList<MonthAttdMgtTO> findMonthAttdMgtList(String applyYearMonth) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findMonthAttdMgtList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			ArrayList<MonthAttdMgtTO> monthAttdMgtList = attdApplicationService.findMonthAttdMgtList(applyYearMonth);
			if (logger.isDebugEnabled()) {
				logger.debug(" findMonthAttdMgtList 종료 ");
			}
			return monthAttdMgtList;
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
	}
	
	@Override
	public void modifyMonthAttdMgtList(ArrayList<MonthAttdMgtTO> monthAttdMgtList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyMonthAttdMgtList 시작 ");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			attdApplicationService.modifyMonthAttdMgtList(monthAttdMgtList);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e){
			dataSourceTransactionManager.rollbackTransaction();
			logger.fatal(e.getMessage());
			throw e;
		} finally {
			dataSourceTransactionManager.closeConnection();
		}
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyMonthAttdMgtList 종료 ");
		}		
	}

}
