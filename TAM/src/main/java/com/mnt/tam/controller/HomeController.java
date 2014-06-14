/**
 * @Copyright MNTSOFT

 * 
 */

package com.mnt.tam.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mnt.tam.service.LoginService;

/**
 * @author pvenkateswarlu
 * @version 1.0 15-09-2013
 */
@Controller
public class HomeController {
    @Autowired
    LoginService loginServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginHome() {
	return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String gotoWelcomePage(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	String view = null;
	String dbUsername = null;
	String dbpassword = null;
	int dbUserId = 0;
	try {
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    List<Object[]> list = loginServiceImpl.getCredentials(userName,
		    password);
	    Iterator<Object[]> iterator = list.iterator();
	    while (iterator.hasNext()) {
		Object[] objects = (Object[]) iterator.next();
		dbUsername = (String) objects[1];
		dbpassword = (String) objects[2];
		dbUserId = (Integer) objects[0];
	    }
	    if (userName.equals(dbUsername) & password.equals(dbpassword)) {
		view = "loginHomeView";
		session.setAttribute("userId", dbUserId);
		session.setAttribute("role", dbUsername);
		session.setAttribute("userName", userName);
	    } else {

		view = "login";
		request.setAttribute("invalid", "Invalid UserName Password");
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request,
	    HttpServletResponse response) throws IOException {
	response.setCharacterEncoding("UTF-8");

	HttpSession session = request.getSession(false);
	if (session != null) {
	    session.removeAttribute("role");
	}
	session.invalidate();
	return "redirect:login.mnt";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgotPassword(HttpServletRequest request,
	    HttpServletResponse response, Model model) {
	response.setCharacterEncoding("UTF-8");
	String dbUsername = null;
	String dbpassword = null;
	int dbUserId = 0;
	String dbmailId = null;
	try {
	    String userName = request.getParameter("userNamefp");
	    String mailid = request.getParameter("mailId");
	    List<Object[]> list = loginServiceImpl.getCredentials(userName,
		    null);
	    Iterator<Object[]> iterator = list.iterator();
	    while (iterator.hasNext()) {
		Object[] objects = (Object[]) iterator.next();
		dbUsername = (String) objects[1];
		dbpassword = (String) objects[2];
		// dbmailId=(String) objects[3];
	    }
	    if (userName.equals(dbUsername)/* & mailid.equals(dbmailId) */) {
		// ForgotPasswordMail fpm=new ForgotPasswordMail();
		// fpm.forgotpassMail(userName,dbpassword);
		request.setAttribute("validUsername",
			"Password has been sent to your mail");
		// purchaseReqs=prservice.getPurchaseReqDetails();
		// request.setAttribute("purchaseRequisition",purchaseReqs);
	    } else {
		request.setAttribute("invalidUserName", "Invalid UserName or Mail Id");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "login";
    }
}
