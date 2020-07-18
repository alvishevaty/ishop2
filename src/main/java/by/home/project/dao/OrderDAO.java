package by.home.project.dao;

import by.home.project.bean.Order;

public interface OrderDAO {

	int saveOrder(Order order) throws DAOException;

}
