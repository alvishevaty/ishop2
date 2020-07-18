package by.home.project.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.Role;
import by.home.project.bean.UserInfo;
import by.home.project.dao.DAOException;
import by.home.project.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final String SELECT_USER_WITH_EMAIL = "FROM RegistrationUser WHERE email = :userEmail";
	private static final String SELECT_USER_WITH_USERNAME = "FROM RegistrationUser WHERE username = :userUsername";
	private static final String SELECT_USERINFO_WITH_USERNAME = "FROM UserInfo WHERE username = :userUsername";

	private static final String USER_EMAIL_ATTR = "userEmail";
	private static final String USER_USERNAME_ATTR = "userUsername";

	private SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public synchronized boolean registration(RegistrationUser regUser) throws DAOException {

		if (!checkEmailAccessibility(regUser.getEmail())) {
			return false;
		}

		if (!checkUsernameAccessibility(regUser.getUsername())) {
			return false;
		}

		Session currentSession = sessionFactory.getCurrentSession();

		Role userRole = currentSession.get(Role.class, 1);

		regUser.setRole(userRole);

		regUser.setIsEnabled(1);

		regUser.setPassword("{noop}" + regUser.getPassword());

		currentSession.save(regUser);

		return true;
	}

	@Override
	public boolean checkEmailAccessibility(String email) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		List<RegistrationUser> userList = currentSession.createQuery(SELECT_USER_WITH_EMAIL, RegistrationUser.class)
				.setParameter(USER_EMAIL_ATTR, email).getResultList();

		if (!userList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkUsernameAccessibility(String username) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		List<RegistrationUser> userList = currentSession.createQuery(SELECT_USER_WITH_USERNAME, RegistrationUser.class)
				.setParameter(USER_USERNAME_ATTR, username).getResultList();

		if (!userList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public UserInfo takeUserInfo(String username) throws DAOException {

		Session currentSession = sessionFactory.getCurrentSession();

		UserInfo user = currentSession.createQuery(SELECT_USERINFO_WITH_USERNAME, UserInfo.class)
				.setParameter(USER_USERNAME_ATTR, username).getSingleResult();

		return user;
	}

}
