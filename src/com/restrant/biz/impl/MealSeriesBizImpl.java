package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.MealSeriesBiz;
import com.restrant.dao.MealSeriesDAO;

public class MealSeriesBizImpl implements MealSeriesBiz {
	MealSeriesDAO mealSeriesDAO;

	public void setMealSeriesDAO(MealSeriesDAO mealSeriesDAO) {
		this.mealSeriesDAO = mealSeriesDAO;
	}

	// 获取菜系列表
	@Override
	public List getMealSeries() {

		return mealSeriesDAO.getMealSeries();
	}

}
