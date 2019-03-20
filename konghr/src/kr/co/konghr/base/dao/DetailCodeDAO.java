package kr.co.konghr.base.dao;

import java.util.*;

import kr.co.konghr.base.to.DetailCodeTO;

public interface DetailCodeDAO {
	public ArrayList<DetailCodeTO> selectDetailCodeList(String codetype);
	public ArrayList<DetailCodeTO> selectDetailCodeListRest(String code1,String code2, String code3);

	public void updateDetailCode(DetailCodeTO detailCodeto);
	public void registDetailCode(DetailCodeTO detailCodeto);
	public void deleteDetailCode(DetailCodeTO detailCodeto);
	
}