package kr.co.konghr.hr.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.co.konghr.common.exception.DataAccessException;
import kr.co.konghr.common.transaction.DataSourceTransactionManager;
import kr.co.konghr.hr.emp.to.EmpTO;

public class EmpDAOImpl implements EmpDAO{
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	private static EmpDAOImpl instance=new EmpDAOImpl();
	
	private EmpDAOImpl(){}
	public static EmpDAO getInstance(){
		if(instance==null) instance=new EmpDAOImpl();
		return instance;
	}

	
	public EmpTO selectEmp(String name){
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmp 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("select * from emp where emp_name=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			EmpTO emp=null;
			if(rs.next()){
				emp=new EmpTO();
				emp.setEmpCode(rs.getString("emp_code"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setDeptName(rs.getString("dept_name"));
				emp.setPosition(rs.getString("position"));
				emp.setGender(rs.getString("gender"));
				emp.setMobileNumber(rs.getString("mobile_number"));
			}
			
			if (logger.isDebugEnabled()) {
				logger.debug(" selectEmp 종료 ");
			}
			return emp;
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public String selectLastEmpCode() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectLastEmpCode 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("select emp_code from emp order by emp_code desc");

			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();

			if (logger.isDebugEnabled()) {
				logger.debug(" selectLastEmpCode 종료 ");
			}
			return rs.getString("emp_code");
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public void registEmployee(EmpTO empto) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" registEmployee 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("insert into emp values(?,?,TO_DATE(?,'YYYY/MM/DD'),?,?,?,?,?,?,?,?,?,?)");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, empto.getEmpCode());
			pstmt.setString(2, empto.getEmpName());
			pstmt.setString(3, empto.getBirthdate());
			pstmt.setString(4, empto.getGender() );
			pstmt.setString(5, empto.getMobileNumber());
			pstmt.setString(6, empto.getAddress());
			pstmt.setString(7, empto.getDetailAddress());
			pstmt.setString(8, empto.getPostNumber());
			pstmt.setString(9, empto.getEmail());
			pstmt.setString(10, empto.getLastSchool());
			pstmt.setString(11, empto.getImgExtend());
			pstmt.setString(12, empto.getPosition());
			pstmt.setString(13, empto.getDeptName());
			
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug(" registEmployee 종료 ");
			}		
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}		
	}
	
	@Override
	public ArrayList<EmpTO> selectEmpList() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmpList 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("select * from emp");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			rs=pstmt.executeQuery();
			ArrayList<EmpTO> list=new ArrayList<EmpTO>(); 
			while(rs.next()){
				EmpTO emp=new EmpTO();
				emp.setEmpName(rs.getString("emp_name"));
				emp.setDeptName(rs.getString("dept_name"));
				emp.setPosition(rs.getString("position"));
				emp.setGender(rs.getString("gender"));
				emp.setMobileNumber(rs.getString("mobile_number"));
				emp.setEmpCode(rs.getString("emp_code"));
				emp.setAddress(rs.getString("address"));
				emp.setDetailAddress(rs.getString("detail_address"));
				emp.setBirthdate(rs.getString("birthdate"));
				emp.setPostNumber(rs.getString("post_number"));
				emp.setImgExtend(rs.getString("img_extend"));
				emp.setLastSchool(rs.getString("last_school"));
				emp.setEmail(rs.getString("email"));
				list.add(emp);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" selectEmpList 종료 ");
			}
			
			
			return list;
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public ArrayList<EmpTO> selectEmpListD(String dept) {
		// TODO Auto-generated method stub
		System.out.println(dept+"DAO");
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmpListD 시작 ");
		}
		ArrayList<EmpTO> list=new ArrayList<EmpTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select emp_name,dept_name,position,gender,mobile_number,emp_code, ");
			query.append("address, detail_address, to_CHAR(birthdate) birthdate, post_number, ");
			query.append("img_extend,last_school,email ");
			query.append("from emp where dept_name=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dept);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				EmpTO emp=new EmpTO();
				emp.setEmpName(rs.getString("emp_name"));
				emp.setDeptName(rs.getString("dept_name"));
				emp.setPosition(rs.getString("position"));
				emp.setGender(rs.getString("gender"));
				emp.setMobileNumber(rs.getString("mobile_number"));
				emp.setEmpCode(rs.getString("emp_code"));
				emp.setAddress(rs.getString("address"));
				emp.setDetailAddress(rs.getString("detail_address"));
				emp.setBirthdate(rs.getString("birthdate"));
				emp.setPostNumber(rs.getString("post_number"));
				emp.setImgExtend(rs.getString("img_extend"));
				emp.setLastSchool(rs.getString("last_school"));
				emp.setEmail(rs.getString("email"));
				list.add(emp);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" selectEmpListD 종료 ");
			}
			return list;
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public ArrayList<EmpTO> selectEmpListN(String name) {
		// TODO Auto-generated method stub
		System.out.println(name+"DAO");
		
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmp 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ArrayList<EmpTO> list=new ArrayList<EmpTO>();
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("select * from emp where emp_name=? ");
			/*insertQuery.append("select emp_code,emp_name,TO_CHAR(birthdate) birthdate,gender,mobile_number, ");
			insertQuery.append("address,detail_address,post_number,email,last_school,img_extend, ");
			insertQuery.append("position,dept_name ");
			insertQuery.append("from emp where emp_name=?");*/
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			while(rs.next()){
				
					EmpTO emp=new EmpTO();
					emp.setEmpCode(rs.getString("emp_code"));
					emp.setEmpName(rs.getString("emp_name"));
					emp.setBirthdate(rs.getString("birthdate"));
					emp.setGender(rs.getString("gender"));
					emp.setMobileNumber(rs.getString("mobile_number"));
					emp.setAddress(rs.getString("address"));
					emp.setDetailAddress(rs.getString("detail_address"));
					emp.setPostNumber(rs.getString("post_number"));
					emp.setEmail(rs.getString("email"));
					emp.setLastSchool(rs.getString("last_school"));
					emp.setImgExtend(rs.getString("img_extend"));
					emp.setPosition(rs.getString("position"));
					emp.setDeptName(rs.getString("dept_name"));
					list.add(emp);
				
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" selectEmp 종료 ");
			}
			return list;
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public String getEmpCode(String name) {
		// TODO Auto-generated method stub
		String empCode=null;
		if (logger.isDebugEnabled()) {
			logger.debug(" getEmpCode 시작 ");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		ArrayList<EmpTO> list=new ArrayList<EmpTO>();
		try {
			StringBuffer insertQuery = new StringBuffer();
			insertQuery.append("select emp_code from emp where emp_name=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			while(rs.next()){
					empCode = rs.getString("emp_code");
			}
			if (logger.isDebugEnabled()) {
				logger.debug(" getEmpCode 종료 ");
			}
			return empCode;
		} catch(Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());			
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public EmpTO selectEmployee(String empCode) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" selectEmployee 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSourceTransactionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("select emp_name,emp.dept_name,position,gender,mobile_number,emp_code, ");
			query.append("address,detail_address,TO_CHAR(birthdate,'YYYY/MM/DD') birthdate,post_number, ");
			query.append("img_extend,last_school,email from emp, dept ");
			query.append("where emp.dept_name=dept.dept_name ");
			query.append("and emp.emp_code=?");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, empCode);
			rs = pstmt.executeQuery();
			EmpTO emp = new EmpTO();
			if (rs.next()) {
				emp.setEmpName(rs.getString("emp_name"));
				emp.setDeptName(rs.getString("dept_name"));
				emp.setPosition(rs.getString("position"));
				emp.setGender(rs.getString("gender"));
				emp.setMobileNumber(rs.getString("mobile_number"));
				emp.setEmpCode(rs.getString("emp_code"));
				emp.setAddress(rs.getString("address"));
				emp.setDetailAddress(rs.getString("detail_address"));
				emp.setBirthdate(rs.getString("birthdate"));
				emp.setPostNumber(rs.getString("post_number"));
				emp.setImgExtend(rs.getString("img_extend"));
				emp.setLastSchool(rs.getString("last_school"));
				emp.setEmail(rs.getString("email"));
			}

			if (logger.isDebugEnabled()) {
				logger.debug(" selectEmployee 종료 ");
			}
			return emp;
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public void updateEmployee(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" updateEmployee 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		try {
			con = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("update emp set ");
			query.append("EMP_NAME = ?, BIRTHDATE = to_date(?,'YYYY/MM/DD'),GENDER= ?, MOBILE_NUMBER=?,  ");
			query.append("ADDRESS = ?, DETAIL_ADDRESS = ?, POST_NUMBER = ?, EMAIL= ?, LAST_SCHOOL=?, IMG_EXTEND=?,  ");
			query.append("POSITION=?, DEPT_NAME=? where emp_code = ? ");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getBirthdate());
			pstmt.setString(3, emp.getGender());
			pstmt.setString(4, emp.getMobileNumber());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getDetailAddress());
			pstmt.setString(7, emp.getPostNumber());
			pstmt.setString(8, emp.getEmail());
			pstmt.setString(9, emp.getLastSchool());
			pstmt.setString(10, emp.getImgExtend());
			pstmt.setString(11, emp.getPosition());
			pstmt.setString(12, emp.getDeptName());
			pstmt.setString(13, emp.getEmpCode());
			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug(" updateEmployee 종료 ");
			}
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
		
	}
	@Override
	public void deleteEmployee(EmpTO emp) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug(" deleteEmployee 시작 ");
		}

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = dataSourceTransactionManager.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("delete from emp where emp_code = ?");

			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, emp.getEmpCode());
			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) {
				logger.debug(" deleteEmployee 종료 ");
			}
		} catch (Exception sqle) {
			logger.fatal(sqle.getMessage());
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
		
	}
	
}
