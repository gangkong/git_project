package kr.co.konghr.hr.salary.applicationService;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.to.ResultTO;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationService;
import kr.co.konghr.hr.salary.applicationService.SalaryApplicationServiceImpl;
import kr.co.konghr.hr.salary.dao.BaseDeductionDAO;
import kr.co.konghr.hr.salary.dao.BaseDeductionDAOImpl;
import kr.co.konghr.hr.salary.dao.BaseExtSalDAO;
import kr.co.konghr.hr.salary.dao.BaseExtSalDAOImpl;
import kr.co.konghr.hr.salary.dao.BaseSalaryDAO;
import kr.co.konghr.hr.salary.dao.BaseSalaryDAOImpl;
import kr.co.konghr.hr.salary.dao.MonthDeductionDAO;
import kr.co.konghr.hr.salary.dao.MonthDeductionDAOImpl;
import kr.co.konghr.hr.salary.dao.MonthSalaryDAO;
import kr.co.konghr.hr.salary.dao.MonthSalaryDAOImpl;
import kr.co.konghr.hr.salary.to.BaseDeductionTO;
import kr.co.konghr.hr.salary.to.BaseExtSalTO;
import kr.co.konghr.hr.salary.to.BaseSalaryTO;
import kr.co.konghr.hr.salary.to.MonthSalaryTO;
import kr.co.konghr.hr.salary.dao.MonthExtSalDAO;
import kr.co.konghr.hr.salary.dao.MonthExtSalDAOImpl;

public class SalaryApplicationServiceImpl implements SalaryApplicationService{
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private BaseSalaryDAO baseSalaryDAO = BaseSalaryDAOImpl.getInstance();
	private BaseDeductionDAO baseDeductionDAO = BaseDeductionDAOImpl.getInstance();
	private BaseExtSalDAO baseExtSalDAO = BaseExtSalDAOImpl.getInstance();
	private MonthSalaryDAO monthSalaryDAO = MonthSalaryDAOImpl.getInstance();
	private MonthDeductionDAO monthDeductionDAO = MonthDeductionDAOImpl.getInstance();
	private MonthExtSalDAO monthExtSalDAO = MonthExtSalDAOImpl.getInstance();

	private static SalaryApplicationService instance;
	private SalaryApplicationServiceImpl(){}
	public static SalaryApplicationService getInstance(){
		if(instance==null) instance=new SalaryApplicationServiceImpl();
		return instance;
	}

	@Override
	public ArrayList<BaseSalaryTO> findBaseSalaryList() {
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseSalaryList 시작 ");
		}

		ArrayList<BaseSalaryTO> baseSalaryList = baseSalaryDAO.selectBaseSalaryList();

		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseSalaryList 종료 ");
		}

		return baseSalaryList;
	}
	
	@Override
	public void modifyBaseSalaryList(ArrayList<BaseSalaryTO> baseSalaryList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseSalaryList 시작 ");
		}

		for(BaseSalaryTO baseSalary : baseSalaryList){
			if(baseSalary.getStatus().equals("update"))
				baseSalaryDAO.updateBaseSalary(baseSalary);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseSalaryList 종료 ");
		}		
	}
	
	@Override
	public ArrayList<BaseDeductionTO> findBaseDeductionList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseDeductionList 시작 ");
		}

		ArrayList<BaseDeductionTO> baseDeductionList = baseDeductionDAO.selectBaseDeductionList();

		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseDeductionList 종료 ");
		}
		return baseDeductionList;
	}
	
	@Override
	public void batchBaseDeductionProcess(ArrayList<BaseDeductionTO> baseDeductionList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" batchBaseDeductionProcess 시작 ");
		}
		for(BaseDeductionTO baseDeduction : baseDeductionList){
			switch(baseDeduction.getStatus()){
			/*	case "update" :
				baseDeductionDAO.updateBaseDeduction(baseDeduction);
				break;*/
				
				case "insert" :
					baseDeductionDAO.insertBaseDeduction(baseDeduction);
					break;
				
				case "delete" :
					baseDeductionDAO.deleteBaseDeduction(baseDeduction);
					break;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" batchBaseDeductionProcess 종료 ");
		}		
	}
	@Override
	public ArrayList<BaseExtSalTO> findBaseExtSalList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseExtSalList 시작 ");
		}

		ArrayList<BaseExtSalTO> baseExtSalList = baseExtSalDAO.selectBaseExtSalList();

		if (logger.isDebugEnabled()) {
			logger.debug(" findBaseExtSalList 종료 ");
		}
		return baseExtSalList;
	}
	
	@Override
	public void modifyBaseExtSalList(ArrayList<BaseExtSalTO> baseExtSalList) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseSalaryList 시작 ");
		}

		for(BaseExtSalTO baseExtSal : baseExtSalList){
			if(baseExtSal.getStatus().equals("update"))
				baseExtSalDAO.updateBaseExtSal(baseExtSal);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" modifyBaseSalaryList 종료 ");
		}		
		
	}

	@Override
	public MonthSalaryTO findMonthSalary(String applyYearMonth, String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findMonthSalary 시작 ");
		}

		HashMap<String, Object> resultMap = monthSalaryDAO.batchMonthSalaryProcess(applyYearMonth, empCode);
		ResultTO resultTO = (ResultTO) resultMap.get("resultTO");
		if(Integer.parseInt(resultTO.getErrorCode()) < 0){
			throw new DataAccessException(resultTO.getErrorMsg());
		}
		MonthSalaryTO monthSalary = (MonthSalaryTO) resultMap.get("monthSalary");
		monthSalary.setMonthDeductionList(monthDeductionDAO.selectMonthDeductionList(applyYearMonth, empCode));
		monthSalary.setMonthExtSalList(monthExtSalDAO.selectMonthExtSalList(applyYearMonth, empCode));

		if (logger.isDebugEnabled()) {
			logger.debug(" findMonthSalary 종료 ");
		}
		return monthSalary;
	}

	@Override
	public ArrayList<MonthSalaryTO> findYearSalary(String applyYear, String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" findYearSalary 시작 ");
		}

		ArrayList<MonthSalaryTO> yearSalary = monthSalaryDAO.selectYearSalary(applyYear, empCode);

		if (logger.isDebugEnabled()) {
			logger.debug(" findYearSalary 종료 ");
		}

		return yearSalary;
	}
	
	@Override
	public void modifyMonthSalary(MonthSalaryTO monthSalary) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" modifyMonthSalary 시작 ");
		}

		monthSalaryDAO.updateMonthSalary(monthSalary);

		if (logger.isDebugEnabled()) {
			logger.debug(" modifyMonthSalary 종료 ");
		}		
	}
	
}
