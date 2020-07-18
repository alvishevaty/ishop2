package by.home.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.home.project.bean.Category;
import by.home.project.bean.Parameter;
/*import by.home.project.bean.GoodsInfo;*/
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;
import by.home.project.dao.DAOException;
import by.home.project.dao.ProductDAO;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}

	@Override
	@Transactional
	public List<Product> allGoodsList(String gender) throws ServiceException {

		try {

			List<Product> productList = productDAO.allGoodsList(gender);

			return productList;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

	@Override
	@Transactional
	public List<Category> allCategoryList() throws ServiceException {

		try {
			List<Category> categoryList = productDAO.allCategoryList();

			return categoryList;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

	@Override
	@Transactional
	public List<Subcategory> allSubcategoryList(int categoryId) throws ServiceException {

		try {
			List<Subcategory> subcategoryList = productDAO.allSubcategoryList(categoryId);

			return subcategoryList;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

	@Override
	@Transactional
	public List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws ServiceException {

		try {
			List<Product> productsList = productDAO.basketGoodsList(basketGoodsIDList);

			return productsList;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}
	}

	@Override
	@Transactional
	public List<Product> allGoodsList(Parameter parameter) throws ServiceException {

		try {
			List<Product> productsList = productDAO.allGoodsList(parameter);

			return productsList;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

	@Override
	@Transactional
	public Product goodsInfo(int goodsId) throws ServiceException {

		try {
			Product product = productDAO.goodsInfo(goodsId);

			return product;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

}
