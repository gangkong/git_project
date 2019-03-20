package kr.co.konghr.hr.attd.dao;

import java.util.ArrayList;

import kr.co.konghr.hr.attd.to.DayAttdTO;

public interface DayAttdDAO {
	public ArrayList<DayAttdTO> selectDayAttdList(String empCode, String applyDay);

	public void insertDayAttd(DayAttdTO dayAttd);
	public void deleteDayAttd(DayAttdTO dayAttd);
}
