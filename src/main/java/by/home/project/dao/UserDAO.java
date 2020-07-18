package by.home.project.dao;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.UserInfo;

public interface UserDAO {
	
	boolean registration(RegistrationUser regUser) throws DAOException;

	boolean checkEmailAccessibility(String email) throws DAOException;

	UserInfo takeUserInfo(String username) throws DAOException; 
}
