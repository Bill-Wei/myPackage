package com.restrant.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.enterprise.inject.New;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.MealBiz;
import com.restrant.biz.MealSeriesBiz;
import com.restrant.entity.Meal;
import com.restrant.entity.Mealseries;
import com.restrant.entity.Pager;
//git 测试  --》第二次修改
public class MealAction extends ActionSupport implements RequestAware {

	private Pager pager;
	private Meal meal;
	MealBiz mealBiz;
	MealSeriesBiz mealSeriesBiz;

	public void setMealSeriesBiz(MealSeriesBiz mealSeriesBiz) {
		this.mealSeriesBiz = mealSeriesBiz;
	}

	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	private File doc;// 封装上传文件的属性
	private String docFileName;// 封装上传文件的名称属性
	private String docContenttypeString;// 封装上传文件的类型属性

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getDocContenttypeString() {
		return docContenttypeString;
	}

	public void setDocContenttypeString(String docContenttypeString) {
		this.docContenttypeString = docContenttypeString;
	}

	Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;

	}

	// 获取指定页码、符合查询条件的的餐品列表，再转到餐品显示页 show.jsp
	public String toShowMeal() throws Exception {
		int curPage = 1;// 初始化待显示页为第 1 页
		if (pager != null) {
			pager.setCurPage(curPage);
		}
		List mealList = null;
		if (meal != null) {// meal不为空，表示表单中输入了查询条件
		// 此时将获取指定页码、查询条件的餐品列表
			mealList = mealBiz.getMealByCondition(meal, curPage);
			// 统计符合查询条件的餐品总数，初始化分页类Pager对象，设置perPageRows和rowCount属性
			pager = mealBiz.getPagerOfMeal(meal);
			// 将查询条件存入request范围，将作为分页超链接中的参数值
			if ((meal.getMealseries() != null)
					&& (meal.getMealseries().getSeriesId() != null)) {
				request.put("seriesId", new Integer(meal.getMealseries()
						.getSeriesId()));
			}
			if ((meal.getMealName() != null)
					&& (!meal.getMealName().equals(""))) {
				request.put("mealName", meal.getMealName());
			}

		} else {// meal为空，表示没有输入查询条件，获取指定页码的餐品列表
			mealList = mealBiz.getAllMeal(curPage);
			// 获取所有餐品总数，初始化分页类Pager对象，设置perPageRows和rowCount属性
			pager = mealBiz.getPagerOfAllMeal();

		}
		// 设置pager对象中的待显示页页码
		pager.setCurPage(curPage);
		request.put("mealList", mealList);// 将查询获得的列表存入request范围
		// 获取菜系列表，存入request范围
		List mealSeriesList = mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);
		return "toShowMeal";
	}

	// 获取菜系列表，再转到添加餐品页“addmeal.jsp”
	public String toAddMeal() throws Exception {
		List mealSeriesList = mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);
		return "addMeal";
	}

	// 上传餐品图、添加餐品信息，再转到toShowMeal
	public String doAddMeal() throws Exception {
		if (docFileName != null) {// 判断是否选择了上传图片
		// 得到当前web工程下的upload目录在本机的绝对 路径，如果没有这个文件夹则会创建
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/mealimages");
			// 重命名上传文件
			String targetFileName = generateFileName(docFileName);
			// 在指定目录创建文件
			File target = new File(targetDirectory, targetFileName);
			// 把要上传的文件copy过去
			FileUtils.copyFile(doc, target);
			meal.setMealImage(targetFileName);
			mealBiz.addMeal(meal);
		}
		return "toShowMeal";
	}

	// 重命名上传文件
	public String generateFileName(String fileName) {
		// 设置时间格式以及带随机数的名字
		String formatDate = new SimpleDateFormat("yyMMddhhmmss")
				.format(new Date());
		int random = new Random().nextInt(1000);
		// 获得文件的后缀名（“.”以后的部分）
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	// 获取指定菜系指定页码及菜系的菜品列表，再转到 managemeal.jsp页面
	public String toManageMeal() throws Exception {
		int curPage = 1;// 定义待显示页
		if (pager != null) {
			pager.setCurPage(curPage);// 如果分页对象不为空，则显示待显示页
		}
		List mealList = null;// 声明一个列表，用于存放获取到的一个或对个meal对象
		if (meal != null) {// meal不为空，则表示查询输入框输入了查询条件
			mealList = mealBiz.getMealByCondition(meal, curPage);// 根据查询条件获取菜品
			pager = mealBiz.getPagerOfMeal(meal);// 获得符合条件的菜品页并赋予pager对象
			// 将查询条件存入request范围,将作为分页超链接中的参数值
			if ((meal.getMealseries() != null)
					&& (meal.getMealseries().getSeriesId() != null)) {
				request.put("seriesId", new Integer(meal.getMealseries()
						.getSeriesId()));

			}
			if ((meal.getMealName() != null)
					&& (!meal.getMealName().equals(""))) {
				request.put("mealName", meal.getMealName());
			}
		} else {// meal为空，表示没有输入查询条件，获取指定页码的餐品列表
			mealList = mealBiz.getAllMeal(curPage);
			pager = mealBiz.getPagerOfAllMeal();
		}
		// 设置pager对象中的待显示页页码
		pager.setCurPage(curPage);
		request.put("mealList", mealList);// 将查询获得的列表存入request范围
		// 获取菜系列表，存入request范围
		List mealSeriesList = mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);
		return "managemeal";
	}

	// 获取要修改的餐品对象，存入request范围，再转到餐品修改页
	public String toUpdateMeal() throws Exception {
		// 获取需要修改的额餐品对象，存入request范围中
		Meal updateMeal = mealBiz.getMealById(meal.getMealId());
		request.put("updateMeal", updateMeal);
		// 获取菜系列表，存入request范围中,用于修改是选择
		List mealSeriesList = mealSeriesBiz.getMealSeries();
		request.put("mealSeriesList", mealSeriesList);
		return "updateMeal";
	}

	// 执行餐品修改操作
	public String doUpdateMeal() throws Exception {
		if (docFileName != null) {
			// 得到当前web工程下的upload目录的在本机的绝对路径，如果没有这个文件夹则会创建
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath("/mealimages");
			// 重命名上传文件
			String targetfileName = generateFileName(docFileName);
			// 在指定目录创建文件
			File target = new File(targetDirectory, targetfileName);
			// 把要上传的文件copy过去
			FileUtils.copyFile(doc, target);
			meal.setMealImage(targetfileName);// 修改餐品图片
		}
		// 更新餐品对象
		mealBiz.updateMeal(meal);
		return "toShowMeal";
	}

	// 删除餐品对象，再转到toManageMeal
	public String deleteMeal() throws Exception {
		mealBiz.deleteMeal(meal.getMealId());
		return "toManageMeal";
	}
//	查看商品详情
	public String toShowDetails() throws Exception{
		Meal aMeal = mealBiz.getMealById(meal.getMealId());
		request.put("aMeal", aMeal);
		return "toShowDetails";
	}
	
}
