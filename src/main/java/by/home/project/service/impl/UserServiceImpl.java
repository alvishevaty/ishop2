package by.home.project.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.UserInfo;
import by.home.project.dao.DAOException;
import by.home.project.dao.UserDAO;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	@Override
	@Transactional
	public boolean registration(RegistrationUser regUser) throws ServiceException {

		boolean registrationStatus;

		if (!isCorrectRegistrationData(regUser)) {
			return false;
		}

		try {

			registrationStatus = userDAO.registration(regUser);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return registrationStatus;
	}

	@Transactional
	public boolean isValidEmail(String email) {

		final String correctEmailRegex = EMAIL_REGEX;

		Pattern pattern = Pattern.compile(correctEmailRegex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	@Transactional
	public boolean isCorrectRegistrationData(RegistrationUser regUser) {

		String email = regUser.getEmail();
		String password = regUser.getPassword();
		String name = regUser.getName();
		String surname = regUser.getSurname();
		String phoneNumber = regUser.getPhoneNumber();
		int minLength = 6;

		if (email == null || email.isEmpty() || !isValidEmail(email)) {
			return false;
		} else if (password == null || password.isEmpty() || password.length() < minLength) {
			return false;
		} else if (name == null || name.isEmpty()) {
			return false;
		} else if (surname == null || surname.isEmpty()) {
			return false;
		} else if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	
	@Override
	@Transactional
	public UserInfo takeUserInfo(String username) throws ServiceException {

		try {
			
			UserInfo userInfo = userDAO.takeUserInfo(username);
			
			return userInfo;
			
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		
	}

}
