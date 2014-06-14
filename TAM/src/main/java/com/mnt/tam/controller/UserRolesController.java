package com.mnt.tam.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.mnt.tam.bean.UserRoles;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.UserRoleService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author tparvathi
 * 
 */
@Controller
public class UserRolesController {
    private static Logger logger = Logger.getLogger(UserRolesController.class);
    @Autowired
    XmlLabelsService xmlService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    UserRoleService UserRolesService;
    String msg;

    @RequestMapping(value = "/UserRoles", method = RequestMethod.GET)
    public ModelAndView getUserRoles(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	return new ModelAndView("userRolesView", "UserRoles", new UserRoles());
    }

    @RequestMapping(value = "/checkUserRolesDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkUserRoleDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("roleName") String roleName,@RequestParam("userName") String userName) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = UserRolesService.roleDup(roleName, userName, null);
	if (checkName != 0) {
	    msg = "Warning ! UserRole  is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }

    @RequestMapping(value = "/checkUserRoleUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkUserRoleUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response, @RequestParam("userId") String userId,
	    @RequestParam("roleId") String roleId,
	    @RequestParam("userRoleId") String userRoleId) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = UserRolesService.roleDup(roleId, userId, userRoleId);
	if (checkName != 0) {
	    msg = "Warning ! UserRole is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }

    @RequestMapping(value = "/UserRolesSaveDetails", method = RequestMethod.POST)
    public String UserRolesSave(
	    @ModelAttribute("UserRoles") UserRoles UserRoles,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = UserRolesService.saveUserRolesDetails(UserRoles);
	    if (msg.equals("S")) {
		succMsg = "redirect:UserRoles.mnt?list=S";
	    } else {
		succMsg = "redirect:UserRoles.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:UserRoles.mnt?listWar=F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return succMsg;

    }

    @RequestMapping(value = "/UserRolesSearch", method = RequestMethod.GET)
    public String UserRolesSearch(
	    @ModelAttribute("UserRoles") UserRoles UserRoles,
	    HttpServletRequest request, HttpServletResponse response) {
	List<UserRoles> listOfUserRoless = null;

	try {
	    listOfUserRoless = UserRolesService
		    .searchUserRolesDetails(UserRoles);
	    logger.error("the list size" + listOfUserRoless.size());
	    request.setAttribute("listOfUserRoless", listOfUserRoless);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "userRolesView";
    }

    @RequestMapping(value = "/UserRolesEdit", method = RequestMethod.GET)
    public String UserRolesEdit(@RequestParam("UserRolesId") int UserRolesId,
	    @ModelAttribute("UserRoles") UserRoles UserRoles,
	    HttpServletRequest request, HttpServletResponse response) {

	try {
	    logger.error("UserRolesid" + UserRolesId);
	    UserRoles UserRoless = UserRolesService
		    .editUserRolesDetails(UserRolesId);

	    BeanUtils.copyProperties(UserRoles, UserRoless);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "userRolesView";
    }

    @RequestMapping(value = "/UserRolesUpdate", method = RequestMethod.POST)
    public String UserRolesUpdate(
	    @ModelAttribute("UserRoles") UserRoles UserRoles,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = UserRolesService.updateUserRolesDetails(UserRoles);
	    if (msg.equals("S")) {
		request.setAttribute("UserRolesUpdate", "Success");
	    } else {
		request.setAttribute("UserRolesUpdateFail", "Success");
	    }
	    model.addAttribute("UserRoles", new UserRoles());
	} catch (Exception e) {
	    request.setAttribute("UserRolesUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "userRolesView";
    }

    @RequestMapping(value = "/UserRolesDelete", method = RequestMethod.GET)
    public String UserRolesDelete(@RequestParam("UserRolesId") int UserRolesId,
	    @ModelAttribute("UserRoles") UserRoles UserRoles,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = UserRolesService.deleteUserRolesDetails(UserRolesId);
	    if (msg.equals("S")) {
		request.setAttribute("UserRolesDeleted", "S");
	    } else {
		request.setAttribute("UserRolesDeleteFail", "F");
	    }
	} catch (Exception e) {
	    request.setAttribute("UserRolesDeleteFail", "F");
	    logger.error(e.getMessage());
	}
	return "userRolesView";
    }

    @ModelAttribute("xmlItems")
    public Map<String, String> populatLabelDetails() {
	String name = "UserRolesId";

	Map<String, String> map = null;

	try {
	    map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return map;
    }

    @ModelAttribute("userDetails")
    public Map<Integer, String> populateUserDetails() {

	Map<Integer, String> map = null;
	try {

	    map = populateService
		    .populateSelectBox("select v.userId,v.userName from com.mnt.tam.bean.Users v");

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	return map;
    }

    @ModelAttribute("roleDetails")
    public Map<Integer, String> populateRoleDetails() {

	Map<Integer, String> map = null;
	try {

	    map = populateService
		    .populateSelectBox("select r.role_Id,r.role_Name from com.mnt.tam.bean.Roles r");

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	return map;
    }
}
