package by.home.project.service;

import by.home.project.bean.Order;

public interface OrderService {

	int saveOrder(Order order) throws ServiceException;

}
