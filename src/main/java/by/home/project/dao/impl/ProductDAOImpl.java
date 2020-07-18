package by.home.project.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.home.project.bean.Category;
import by.home.project.bean.Parameter;
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;
import by.home.project.dao.DAOException;
import by.home.project.dao.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final String SELECT_ALL_SUBCATEGORIES = "FROM Subcategory where category_id = :categoryId";
	private static final String SELECT_ALL_CATEGORIES = "FROM Category";
	private static final String SELECT_ALL_GOODS_WITH_GENDER = "FROM Product WHERE gender = :productGender";
	private static final String SELECT_ALL_GOODS_WITH_GENDER_CATEGORY = "FROM Product where gender = :productGender and category_id = :productCategory";
	private static final String SELECT_ALL_GOODS_WITH_GENDER_CATEGORY_SUBCATEGORY = "FROM Product where gender = :productGender and category_id = :productCategory and subcategory_id = :productSubcategory";
	private static final String SELECT_ALL_GOODS_WITH_ID = "FROM Product WHERE id = :productId";

	private static final String CATEGORY_ID_ATTR = "categoryId";
	private static final String PRODUCT_GENDER_ATTR = "productGender";
	private static final String PRODUCT_CATEGORY_ATTR = "productCategory";
	private static final String PRODUCT_SUBCATEGORY_ATTR = "productSubcategory";
	private static final String PRODUCT_ID_ATTR = "productId";

	private static final String CATEGORY = "category";
	private static final String SUBCATEGORY = "subcategory";
	private static final String GENDER = "gender";

	private SessionFactory sessionFactory;

	@Autowired
	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Product> allGoodsList(String genderValue) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		List<Product> goodsList = currentSession.createQuery(SELECT_ALL_GOODS_WITH_GENDER, Product.class)
				.setParameter(PRODUCT_GENDER_ATTR, genderValue).getResultList();

		return goodsList;
	}

	@Override
	public List<Category> allCategoryList() throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		List<Category> categoryList = currentSession.createQuery(SELECT_ALL_CATEGORIES, Category.class).getResultList();

		return categoryList;
	}

	@Override
	public List<Subcategory> allSubcategoryList(int categoryId) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		List<Subcategory> subcategoryList = currentSession.createQuery(SELECT_ALL_SUBCATEGORIES, Subcategory.class)
				.setParameter(CATEGORY_ID_ATTR, categoryId).getResultList();

		return subcategoryList;
	}

	public List<Product> executeRequest(int number, List<String> keyValue) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();
		List<Product> productsList = null;

		if (number == 1) {

			productsList = currentSession.createQuery(SELECT_ALL_GOODS_WITH_GENDER, Product.class)
					.setParameter(PRODUCT_GENDER_ATTR, keyValue.get(0)).getResultList();

		} else if (number == 2) {

			productsList = currentSession.createQuery(SELECT_ALL_GOODS_WITH_GENDER_CATEGORY, Product.class)
					.setParameter(PRODUCT_GENDER_ATTR, keyValue.get(0))
					.setParameter(PRODUCT_CATEGORY_ATTR, Integer.parseInt(keyValue.get(1))).getResultList();

		} else if (number == 3) {

			productsList = currentSession.createQuery(SELECT_ALL_GOODS_WITH_GENDER_CATEGORY_SUBCATEGORY, Product.class)
					.setParameter(PRODUCT_GENDER_ATTR, keyValue.get(0))
					.setParameter(PRODUCT_CATEGORY_ATTR, Integer.parseInt(keyValue.get(1)))
					.setParameter(PRODUCT_SUBCATEGORY_ATTR, keyValue.get(2)).getResultList();
		}

		return productsList;
	}

	@Override
	public List<Product> allGoodsList(Parameter parameter) throws DAOException {

		String value;
		List<Product> allProducts = new ArrayList<Product>();
		List<String> keyValue = new ArrayList<String>();

		Set<String> keys = parameter.getParameter().keySet();

		if (!keys.contains(SUBCATEGORY) & !keys.contains(CATEGORY)) {
			for (String key : keys) {
				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(1, keyValue);

		} else if (!keys.contains(SUBCATEGORY)) {
			for (String key : keys) {
				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(CATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(2, keyValue);

		} else {
			for (String key : keys) {

				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(CATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(SUBCATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(3, keyValue);
		}

		return allProducts;
	}

	@Override
	public List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		List<Product> basketGoodsList = new ArrayList<Product>();

		for (int productId : basketGoodsIDList) {

			Product product = currentSession.createQuery(SELECT_ALL_GOODS_WITH_ID, Product.class)
					.setParameter(PRODUCT_ID_ATTR, productId).getSingleResult();
			basketGoodsList.add(product);

		}

		return basketGoodsList;
	}

	@Override
	public Product goodsInfo(int goodsId) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		Product product = currentSession.createQuery(SELECT_ALL_GOODS_WITH_ID, Product.class)
				.setParameter(PRODUCT_ID_ATTR, goodsId).getSingleResult();

		return product;

	}

}
