package com.restrant.dao;

import java.util.List;

import com.restrant.entity.Meal;

public interface MealDAO {
	// 获得指定页显示的餐品列表
	public List getAllMeal(int page);

	// 统计所有餐品总数
	public Integer getCountOfAllMeal();

	// 根据查询条件，获取指定页显示的餐品列表
	public List getMealByCondition(Meal condition, int page);

	// 统计符合查询条件的餐品总数
	public Integer getCountOfMeal(Meal condition);

	// 根据菜品编号加载菜单
	public Meal getMealById(int mealId);

	// 添加餐品
	public void addMeal(Meal meal);

	// 修改餐品
	public void updateMeal(Meal meal);

	// 删除餐品
	public void daleteMeal(Meal meal);
}
