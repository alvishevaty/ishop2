package by.home.project.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import by.home.project.bean.Address;
import by.home.project.bean.Category;
import by.home.project.bean.Delivery;
import by.home.project.bean.Order;
import by.home.project.bean.Parameter;
import by.home.project.bean.Payment;
import by.home.project.bean.Product;
import by.home.project.bean.RegistrationUser;
import by.home.project.bean.Subcategory;
import by.home.project.bean.UserInfo;
import by.home.project.service.OrderService;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;

@Controller
public class WebController {

	private static final String REGISTRATION_USER_PARAMETER = "regUser";
	private static final String GOODS_BASKET_LIST_ATTR = "goodsBasketList";
	private static final String SUBCATEGORY_LIST_ATTR = "subcategoryList";
	private static final String PAYMENT_METHOD_ATTR = "paymentMethod";
	private static final String DELIVERY_TYPE_ATTR = "deliveryType";
	private static final String DELIVERY_DATE_ATTR = "deliveryDate";
	private static final String PRODUCTS_LIST_ATTR = "productsList";
	private static final String ORDER_NUMBER_ATTR = "orderNumber";
	private static final String BASKET_SIZE_ATTR = "basketSize";
	private static final String SUBCATEGORY_ATTR = "subcategory";
	private static final String PRODUCT_ID_ATTR = "productId";
	private static final String POST_CODE_ATTR = "postCode";
	private static final String USER_INFO_ATTR = "userInfo";
	private static final String CATEGORY_ATTR = "category";
	private static final String GOODS_ID_ATTR = "goodsId";
	private static final String PRODUCT_ATTR = "product";
	private static final String COUNTRY_ATTR = "country";
	private static final String AMOUNT_ATTR = "amount";
	private static final String GENDER_ATTR = "gender";
	private static final String STREET_ATTR = "street";
	private static final String HOUSE_ATTR = "house";
	private static final String CITY_ATTR = "city";
	private static final String FLAT_ATTR = "flat";
	
	private static final Logger logger = Logger.getLogger(Class.class.getName());

	private List<Integer> goodsBasketList = new ArrayList<Integer>();

	private UserService userService;
	private ProductService productService;
	private OrderService orderService;

	@Autowired
	public WebController(UserService userService, ProductService productService, OrderService orderService) {
		super();

		this.userService = userService;
		this.productService = productService;
		this.orderService = orderService;

	}

	@GetMapping(value = "/registrationpage")
	public String goToRegistrationPage(Model theModel) {
		theModel.addAttribute(REGISTRATION_USER_PARAMETER, new RegistrationUser());
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute(REGISTRATION_USER_PARAMETER) RegistrationUser registrationUser) {
		try {
			
			boolean registrationStatus = userService.registration(registrationUser);
			
			if (registrationStatus == true) {
				return "redirect:/mainpage?registration=success";
				
			} else {
				return "registrationpage?registration=error";
			}

		} catch (ServiceException e) {
			logger.error("Error while registration");
			throw new WebControllerRuntimeException(e);
		}

	}

	@RequestMapping(value = "/")
	public String goToMainPage() {
		return "mainpage";
	}

	@RequestMapping(value = "/mainpage")
	public String goToHomePage() {
		return "mainpage";
	}

	@RequestMapping(value = "/userinfopage", method = RequestMethod.GET)
	public String goToUserInfoPage(Principal principal, HttpServletRequest request) {

		String username = principal.getName();
		UserInfo user;

		try {
			HttpSession session = request.getSession(true);
			user = userService.takeUserInfo(username);
			session.setAttribute(USER_INFO_ATTR, user);

		} catch (ServiceException e) {
			logger.error("Error while execute redirect to Userinfopage");
			throw new WebControllerRuntimeException(e);
		}

		return "userprofilepage";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String goToSignInPage() {
		return "signin";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/basketpage", method = RequestMethod.GET)
	public String goToBasketPage(HttpServletRequest request, Model theModel) {

		HttpSession session = request.getSession(true);
		goodsBasketList = (List<Integer>) session.getAttribute(GOODS_BASKET_LIST_ATTR);

		try {

			if (goodsBasketList != null) {

				List<Product> productsList = productService.basketGoodsList(goodsBasketList);
				session.setAttribute(PRODUCTS_LIST_ATTR, productsList);
				return "basketpage";

			} else {
				session.setAttribute(PRODUCTS_LIST_ATTR, null);
				return "basketpage";

			}

		} catch (ServiceException e) {
			logger.error("Error while execute redirect to Basketpage");
			throw new WebControllerRuntimeException(e);
		}

	}

	@GetMapping(value = "/errors")
	public String goToErrorPage() {
		return "error";
	}

	@RequestMapping(value = "/signout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/mainpage";
	}

	@RequestMapping(value = "/catalog")
	public String goToMenCatalog(HttpServletRequest request, Model theModel, RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession(true);
		Parameter parameter = new Parameter();
		List<Product> productList;

		String genderValue = request.getParameter(GENDER_ATTR);
		session.setAttribute(GENDER_ATTR, genderValue);

		try {
			
			parameter.add(GENDER_ATTR, genderValue);

			List<Category> categoryList = productService.allCategoryList();
			theModel.addAttribute(categoryList);

			if (request.getParameter(CATEGORY_ATTR) != null) {

				int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ATTR));
				parameter.add(CATEGORY_ATTR, Integer.toString(categoryId));

				List<Subcategory> subcategoryList = productService.allSubcategoryList(categoryId);

				theModel.addAttribute(categoryId + SUBCATEGORY_LIST_ATTR, subcategoryList);
			}

			if (request.getParameter(SUBCATEGORY_ATTR) != null) {
				
				int subcategoryId = Integer.parseInt(request.getParameter(SUBCATEGORY_ATTR));

				parameter.add(SUBCATEGORY_ATTR, Integer.toString(subcategoryId));
			}

			productList = productService.allGoodsList(parameter);
			theModel.addAttribute(productList);

		} catch (ServiceException e) {
			logger.error("Error while execute redirect to Catalog");
			throw new WebControllerRuntimeException(e);
		}
		
		return "catalog";
	}

	@RequestMapping(value = "/goodsinfopage")
	public String goToGoodsPage(Model theModel, @RequestParam(GOODS_ID_ATTR) int goodsId) {

		try {
			Product product = productService.goodsInfo(goodsId);
			theModel.addAttribute(PRODUCT_ATTR, product);

		} catch (ServiceException e) {
			logger.error("Error while execute redirect to Goodsinfopage");
			throw new WebControllerRuntimeException(e);
		}

		return "goodsinfo";
	}

	@PostMapping(value = "/putintobasket")
	public String putIntoBasket(HttpServletRequest request) {

		HttpSession session = request.getSession(true);

		String productId = request.getParameter(PRODUCT_ID_ATTR);

		int product = Integer.parseInt(productId);
		goodsBasketList.add(product);

		session.setAttribute(GOODS_BASKET_LIST_ATTR, goodsBasketList);
		session.setAttribute(BASKET_SIZE_ATTR, goodsBasketList.size());

		return "redirect:/goodsinfopage?goodsId=" + request.getParameter(PRODUCT_ID_ATTR);

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/deletefrombasket")
	public String deleteFromBasket(HttpServletRequest request, Model themodel) {

		HttpSession session = request.getSession(true);

		goodsBasketList = (List<Integer>) session.getAttribute(GOODS_BASKET_LIST_ATTR);

		String productId = request.getParameter(PRODUCT_ID_ATTR);
		int index = goodsBasketList.indexOf(Integer.parseInt(productId));
		goodsBasketList.remove(index);

		session.setAttribute(GOODS_BASKET_LIST_ATTR, goodsBasketList);
		session.setAttribute(BASKET_SIZE_ATTR, goodsBasketList.size());

		return "redirect:/basketpage";

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/order")
	public String order(Principal principal, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		goodsBasketList = (List<Integer>) session.getAttribute(GOODS_BASKET_LIST_ATTR);
		UserInfo user;

		try {

			String username = principal.getName();

			user = userService.takeUserInfo(username);

			session.setAttribute(USER_INFO_ATTR, user);

			String country = request.getParameter(COUNTRY_ATTR);
			String city = request.getParameter(CITY_ATTR);
			String street = request.getParameter(STREET_ATTR);
			String house = request.getParameter(HOUSE_ATTR);
			String flat = request.getParameter(FLAT_ATTR);
			String postCode = request.getParameter(POST_CODE_ATTR);

			String deliveryType = request.getParameter(DELIVERY_TYPE_ATTR);
			String deliveryDate = request.getParameter(DELIVERY_DATE_ATTR);

			String paymentMethod = request.getParameter(PAYMENT_METHOD_ATTR);
			String amount = request.getParameter(AMOUNT_ATTR);

			Address address = new Address(country, city, street, house, flat, postCode, user);
			Delivery delivery = new Delivery(deliveryType, deliveryDate, address);
			Payment payment = new Payment(paymentMethod, Integer.parseInt(amount));
			Order order = new Order(delivery, payment, user);

			int orderNumber = orderService.saveOrder(order);
			request.setAttribute(ORDER_NUMBER_ATTR, orderNumber);
			goodsBasketList.clear();

			session.setAttribute(GOODS_BASKET_LIST_ATTR, goodsBasketList);
			session.setAttribute(BASKET_SIZE_ATTR, 0);
			session.setAttribute(ORDER_NUMBER_ATTR, orderNumber);

		} catch (ServiceException e) {
			logger.error("Error while execute Order confirmation");
			throw new WebControllerRuntimeException(e);
		}
		
		return "redirect:/orderConfirm?order=success";

	}

	@GetMapping(value = "/orderConfirm")
	public String goOrderConfirmPage() {
		return "orderConfirm";
	}

}
