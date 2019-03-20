package kr.co.konghr.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.to.DetailCodeTO;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;

public class DetailCodeDAOImpl implements DetailCodeDAO {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	private static DetailCodeDAO instance;
	private DetailCodeDAOImpl() {}
	public static DetailCodeDAO getInstance() {
		if (instance == null)
			instance = new DetailCodeDAOImpl();
		return instance;
	}

	@Override
	public ArrayList<DetailCodeTO> selectDetailCodeList(String codetype) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectDetailCodeList 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("select * from detail_code where code_number = ?");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, codetype);
			rs = pstmt.executeQuery();

			ArrayList<DetailCodeTO> detailCodeList=new ArrayList<DetailCodeTO>();
			DetailCodeTO detailCode=null;
			while(rs.next()){
				detailCode=new DetailCodeTO();
				detailCode.setCodeNumber(rs.getString("code_number"));
				detailCode.setDetailCodeNumber(rs.getString("detail_code_number"));
				detailCode.setDetailCodeName(rs.getString("detail_code_name"));
				detailCode.setDetailCodeNameusing(rs.getString("detail_code_nameusing"));
				detailCodeList.add(detailCode);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" selectDetailCodeList 종료 ");
			}
			return detailCodeList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void updateDetailCode(DetailCodeTO detailCodeto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteDetailCode 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("UPDATE DETAIL_CODE SET ");
			query.append("CODE_NUMBER = ?, DETAIL_CODE_NAME = ?, DETAIL_CODE_NAMEUSING = ? ");
			query.append("WHERE DETAIL_CODE_NUMBER = ? ");
		
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, detailCodeto.getCodeNumber());
			pstmt.setString(2, detailCodeto.getDetailCodeName());
			pstmt.setString(3, detailCodeto.getDetailCodeNameusing());
			pstmt.setString(4, detailCodeto.getDetailCodeNumber());
			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug(" deleteDetailCode 종료 ");
			}
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
		
	}
	
	@Override
	public void registDetailCode(DetailCodeTO detailCodeto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registDetailCode 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("insert into detail_code values(?,?,?,?)");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, detailCodeto.getDetailCodeNumber());
			pstmt.setString(2, detailCodeto.getCodeNumber());
			pstmt.setString(3, detailCodeto.getDetailCodeName());
			pstmt.setString(4, detailCodeto.getDetailCodeNameusing());
			
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug(" registDetailCode 종료 ");
			}		
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}		
	}
	
	@Override
	public void deleteDetailCode(DetailCodeTO detailCodeto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteDetailCode 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM DETAIL_CODE WHERE DETAIL_CODE_NUMBER = ? AND DETAIL_CODE_NAME = ? ");
		
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, detailCodeto.getDetailCodeNumber());
			pstmt.setString(2, detailCodeto.getDetailCodeName());
			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug(" deleteDetailCode 종료 ");
			}
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
		
	}
	
	@Override
	public ArrayList<DetailCodeTO> selectDetailCodeListRest(String code1, String code2, String code3) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectDetailCodeListRest 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("select * from detail_code where DETAIL_CODE_NUMBER = ? OR DETAIL_CODE_NUMBER = ? OR DETAIL_CODE_NUMBER = ?");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code1);
			pstmt.setString(2, code2);
			pstmt.setString(3, code3);
			rs = pstmt.executeQuery();

			ArrayList<DetailCodeTO> detailCodeList=new ArrayList<DetailCodeTO>();
			DetailCodeTO detailCode=null;
			while(rs.next()){
				detailCode=new DetailCodeTO();
				detailCode.setCodeNumber(rs.getString("code_number"));
				detailCode.setDetailCodeNumber(rs.getString("detail_code_number"));
				detailCode.setDetailCodeName(rs.getString("detail_code_name"));
				detailCode.setDetailCodeNameusing(rs.getString("detail_code_nameusing"));
				detailCodeList.add(detailCode);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" selectDetailCodeListRest 종료 ");
			}
			return detailCodeList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	
}
