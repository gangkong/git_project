package kr.co.konghr.hr.attd.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.to.ResultTO;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.attd.to.DayAttdMgtTO;


public class DayAttdMgtDAOImpl implements DayAttdMgtDAO {

	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	private static DayAttdMgtDAO instance;
	private DayAttdMgtDAOImpl(){}
	public static DayAttdMgtDAO getInstance(){
		if(instance==null) instance=new DayAttdMgtDAOImpl();
		return instance;
	}

	@Override
	public HashMap<String, Object> batchDayAttdMgtProcess(String applyDay, String dept) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" batchDayAttdMgtProcess 시작 ");
		}

		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ResultTO resultTO = null;
		ArrayList<DayAttdMgtTO> dayAttdMgtList = new ArrayList<DayAttdMgtTO>();
		HashMap<String, Object> resultMap = new HashMap<>();
		try {
			con = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("{call P_HR_ATTENDANCE.P_CREATE_DAY_ATTD_MANAGE(TO_DATE(?,'YYYY-MM-DD'),?,?,?,?)}");
			cstmt = con.prepareCall(query.toString());
			cstmt.setString(1, applyDay);
			cstmt.setString(2, dept);
			cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.execute();

			resultTO = new ResultTO();
			resultTO.setErrorCode(cstmt.getString(4));
			resultTO.setErrorMsg(cstmt.getString(5));
			
			rs = (ResultSet)cstmt.getObject(3);
			DayAttdMgtTO datAttdMgt = null;
			while (rs.next()) {
				datAttdMgt = new DayAttdMgtTO();
				datAttdMgt.setEmpCode(rs.getString("EMP_CODE"));
				datAttdMgt.setEmpName(rs.getString("EMP_NAME"));
				datAttdMgt.setApplyDays(rs.getString("APPLY_DAYS"));
				datAttdMgt.setDayAttdCode(rs.getString("DAY_ATTD_CODE"));
				datAttdMgt.setDayAttdName(rs.getString("DAY_ATTD_NAME"));
				datAttdMgt.setAttendTime(rs.getString("ATTEND_TIME"));
				datAttdMgt.setQuitTime(rs.getString("QUIT_TIME"));
				datAttdMgt.setLateWhether(rs.getString("LATE_WHETHER"));
				datAttdMgt.setLeaveHour(rs.getString("LEAVE_HOUR"));
				datAttdMgt.setWorkHour(rs.getString("WORK_HOUR"));
				datAttdMgt.setOverWorkHour(rs.getString("OVER_WORK_HOUR"));
				datAttdMgt.setNightWorkHour(rs.getString("NIGHT_WORK_HOUR"));
				datAttdMgt.setFinalizeStatus(rs.getString("FINALIZE_STATUS"));
				dayAttdMgtList.add(datAttdMgt);
			}
			resultMap.put("dayAttdMgtList", dayAttdMgtList);
			resultMap.put("resultTO", resultTO);
			
			if (logger.isDebugEnabled()) {
				logger.debug(" batchDayAttdMgtProcess 종료 ");
			}
			return resultMap;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(cstmt,rs);
		}
	}
	
	@Override
	public void updateDayAttdMgtList(DayAttdMgtTO dayAttdMgt) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" updateDayAttdMgtList 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("UPDATE DAY_ATTD_MANAGE SET ");
			query.append("FINALIZE_STATUS = ? ");
			query.append("WHERE EMP_CODE = ? AND APPLY_DAYS = ? ");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dayAttdMgt.getFinalizeStatus());
			pstmt.setString(2, dayAttdMgt.getEmpCode());
			pstmt.setString(3, dayAttdMgt.getApplyDays());

			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug(" updateDayAttdMgtList 종료 ");
			}
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

}
