package by.home.project.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Order;
import by.home.project.bean.Payment;
import by.home.project.dao.DAOException;
import by.home.project.dao.OrderDAO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
private SessionFactory sessionFactory;

	private static final String STATUS = "Done";
	
	@Autowired
	public OrderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int saveOrder(Order order) throws DAOException {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Delivery delivery = order.getDelivery();
		Payment payment = order.getPayment();
		Address address = delivery.getAddress();
		payment.setStatus(STATUS);
		
		currentSession.save(address);
		currentSession.save(delivery);
		currentSession.save(payment);
		currentSession.save(order);
		
		int id = order.getId();

		return id;

	}

}