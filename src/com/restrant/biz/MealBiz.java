package com.restrant.biz;

import java.util.List;

import org.hibernate.Session;

import com.restrant.dao.MealDAO;
import com.restrant.entity.Meal;
import com.restrant.entity.Pager;

public interface MealBiz {

	// 获得指定页显示的餐品列表
	public List getAllMeal(int page);

	// 获得所有餐品数量，初始化分页类Pager对象，设置其perPageRows和rowCount属性
	public Pager getPagerOfAllMeal();

	// 根据查询条件，获取指定页显示的餐品列表
	public List getMealByCondition(Meal condition, int page);

	// 统计符合查询条件的餐品总数，初始化分页类Pager对象，设置perPageRows和rowCount属性
	public Pager getPagerOfMeal(Meal condition);

	// 根据菜品编号获取菜品
	public Meal getMealById(int mealId);

	// 添加餐品
	public void addMeal(Meal meal);

	// 修改餐品
	public void updateMeal(Meal meal);

	// 删除指定编号的餐品
	public void deleteMeal(int mealId);
}
