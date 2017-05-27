package com.restrant.entity;

/**
 * Orderdts entity. @author MyEclipse Persistence Tools
 */

public class Orderdts implements java.io.Serializable {

	// Fields

	private Integer odId;
	private Meal meal;
	private Orders orders;
	private Double mealPrice;
	private Integer mealcount;

	// Constructors

	/** default constructor */
	public Orderdts() {
	}

	/** full constructor */
	public Orderdts(Meal meal, Orders orders, Double mealPrice,
			Integer mealcount) {
		this.meal = meal;
		this.orders = orders;
		this.mealPrice = mealPrice;
		this.mealcount = mealcount;
	}

	// Property accessors

	public Integer getOdId() {
		return this.odId;
	}

	public void setOdId(Integer odId) {
		this.odId = odId;
	}

	public Meal getMeal() {
		return this.meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Orders getOrders() {
		return this.orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Double getMealPrice() {
		return this.mealPrice;
	}

	public void setMealPrice(Double mealPrice) {
		this.mealPrice = mealPrice;
	}

	public Integer getMealcount() {
		return this.mealcount;
	}

	public void setMealcount(Integer mealcount) {
		this.mealcount = mealcount;
	}

}