
package by.home.project.service;

import java.util.List;

import by.home.project.bean.Category;/*
										import by.home.project.bean.GoodsInfo;*/
import by.home.project.bean.Parameter;
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;

public interface ProductService {

	List<Product> allGoodsList(String genderValue) throws ServiceException;

	List<Product> allGoodsList(Parameter parameter) throws ServiceException;

	List<Category> allCategoryList() throws ServiceException;

	List<Subcategory> allSubcategoryList(int categoryId) throws ServiceException;

	List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws ServiceException;

	Product goodsInfo(int goodsId) throws ServiceException;
	 
}
