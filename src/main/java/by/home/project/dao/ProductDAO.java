
package by.home.project.dao;

import java.util.List;

import by.home.project.bean.Category;
import by.home.project.bean.Parameter;
/*import by.home.project.bean.GoodsInfo;
import by.home.project.bean.Parameter;*/
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;

public interface ProductDAO {

	List<Product> allGoodsList(String genderValue) throws DAOException;

	List<Product> allGoodsList(Parameter parameter) throws DAOException;

	List<Category> allCategoryList() throws DAOException;

	List<Subcategory> allSubcategoryList(int categoryId) throws DAOException;

	List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws DAOException;

	Product goodsInfo(int goodsId) throws DAOException;
	 
}
