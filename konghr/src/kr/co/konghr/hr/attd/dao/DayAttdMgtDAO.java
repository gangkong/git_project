package kr.co.konghr.hr.attd.dao;

import java.util.HashMap;

import kr.co.konghr.hr.attd.to.DayAttdMgtTO;

public interface DayAttdMgtDAO {
	public HashMap<String, Object> batchDayAttdMgtProcess(String applyDay , String dept);
	public void updateDayAttdMgtList(DayAttdMgtTO dayAttdMgt);
}
