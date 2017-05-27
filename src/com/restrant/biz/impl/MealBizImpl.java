package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.MealBiz;
import com.restrant.dao.MealDAO;
import com.restrant.entity.Meal;
import com.restrant.entity.Pager;

public class MealBizImpl implements MealBiz {

	MealDAO mealDAO;

	public void setMealDAO(MealDAO mealDAO) {
		this.mealDAO = mealDAO;
	}

	// 获得指定页显示的餐品列表
	@Override
	public List getAllMeal(int page) {

		return mealDAO.getAllMeal(page);
	}

	// 获得所有餐品数量，初始化分页类Pager对象，设置其perPageRows和rowCount属性
	@Override
	public Pager getPagerOfAllMeal() {
		int count = mealDAO.getCountOfAllMeal();
		// 使用分页类Pager定义对象
		Pager pager = new Pager();
		// 设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		// 设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
		// 返回pager对象
		return pager;
	}

	// 根据查询条件，获取指定页显示的餐品列表
	@Override
	public List getMealByCondition(Meal condition, int page) {

		return mealDAO.getMealByCondition(condition, page);
	}

	// 统计符合查询条件的餐品总数，初始化分页类Pager对象，设置perPageRows和rowCount属性
	@Override
	public Pager getPagerOfMeal(Meal condition) {
		int count = mealDAO.getCountOfMeal(condition);
		Pager pager = new Pager();
		pager.setPerPageRows(6);
		pager.setRowCount(count);
		return pager;
	}

	// 根据菜品编号获取菜品
	@Override
	public Meal getMealById(int mealId) {
		return mealDAO.getMealById(mealId);
	}

	// 添加餐品
	@Override
	public void addMeal(Meal meal) {
		mealDAO.addMeal(meal);

	}

	// 修改餐品
	@Override
	public void updateMeal(Meal meal) {
		mealDAO.updateMeal(meal);

	}

	// 删除指定编号的餐品
	@Override
	public void deleteMeal(int mealId) {
		// 根据前端页面传递来的mealId获取meal对象
		Meal meal = mealDAO.getMealById(mealId);
		// 删除meal对象
		mealDAO.daleteMeal(meal);
	}

}
