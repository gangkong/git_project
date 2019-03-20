package kr.co.konghr.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.base.to.CodeTO;
import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;

public class CodeDAOImpl implements CodeDAO {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	private static CodeDAO instance;
	private CodeDAOImpl() {}
	public static CodeDAO getInstance() {
		if (instance == null)
			instance = new CodeDAOImpl();
		return instance;
	}

	public ArrayList<CodeTO> selectCode() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectCode 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM DIVISION_CODE ORDER BY CODE_NUMBER");

			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			ArrayList<CodeTO> codeList=new ArrayList<CodeTO>();
			CodeTO code= null;
			while(rs.next()){
				code=new CodeTO();
				code.setCodeName(rs.getString("CODE_NAME"));
				code.setCodeNumber(rs.getString("CODE_NUMBER"));
				code.setModifiable(rs.getString("MODIFIABLE"));
				codeList.add(code);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(" selectCode 종료 ");
			}
			return codeList;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

}
