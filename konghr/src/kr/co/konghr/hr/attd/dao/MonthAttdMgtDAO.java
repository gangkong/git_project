package kr.co.konghr.hr.attd.dao;

import java.util.HashMap;

import kr.co.konghr.hr.attd.to.MonthAttdMgtTO;

public interface MonthAttdMgtDAO {
	public HashMap<String, Object> batchMonthAttdMgtProcess(String applyYearMonth);
	public void updateMonthAttdMgtList(MonthAttdMgtTO monthAttdMgt);
}
