package by.home.project.service;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.UserInfo;

public interface UserService {
	
	boolean registration(RegistrationUser regUser) throws ServiceException;

	UserInfo takeUserInfo(String username) throws ServiceException; 
}
