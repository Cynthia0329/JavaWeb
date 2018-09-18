package com.yzgs.service;

import java.util.List;

import com.yzgs.domain.MonthGoodGuoQi;
import com.yzgs.domain.MonthGoodMoneyTotal;
import com.yzgs.domain.MonthMoneyTotal;

public interface TotalService {

	List<MonthMoneyTotal> getMonthMoneyTotal(List<String> months);

	List<MonthGoodMoneyTotal> getMonthGoodMoneyTotal(List<String> months,
			String goodId);

	List<MonthGoodGuoQi> getMonthGoodGuoQi(List<String> months, String goodId);

}
