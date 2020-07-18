package by.home.project.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.home.project.bean.Order;
import by.home.project.dao.DAOException;
import by.home.project.dao.OrderDAO;
import by.home.project.service.OrderService;
import by.home.project.service.ServiceException;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderDAO orderDAO;

	@Autowired
	public OrderServiceImpl(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	@Override
	@Transactional
	public int saveOrder(Order order) throws ServiceException {

		try {

			int id = orderDAO.saveOrder(order);

			return id;

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

	}

}
