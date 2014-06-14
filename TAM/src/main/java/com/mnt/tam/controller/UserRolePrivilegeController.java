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
import org.springframework.web.servlet.ModelAndView;
import com.mnt.tam.bean.UserRolePrivilege;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.UserRolePrivilegeService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class UserRolePrivilegeController {
	private static Logger logger = Logger.getLogger(UserRolePrivilegeController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	UserRolePrivilegeService userRolePrivilegeService;
	@RequestMapping(value = "/UserRolePrivilege", method = RequestMethod.GET)
	public ModelAndView getUserRolePrivilege(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		return new ModelAndView("userRolesPrivilegesView", "UserRolePrivilege",
				new UserRolePrivilege());
	}
	@RequestMapping(value = "/UserRolePrivilegeSaveDetails", method = RequestMethod.POST)
	public String UserRolePrivilegeSave(@ModelAttribute("UserRolePrivilege") UserRolePrivilege UserRolePrivilege,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = userRolePrivilegeService.saveUserRolePrivilegeDetails(UserRolePrivilege);
			if(msg.equals("S"))
			{
				succMsg="redirect:UserRolePrivilege.mnt?list=S";
			}
			else
			{
				succMsg="redirect:UserRolePrivilege.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:UserRolePrivilege.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}
	
	@RequestMapping(value = "/UserRolePrivilegeSearch", method = RequestMethod.GET)
	public String UserRolePrivilegeSearch(@ModelAttribute("UserRolePrivilege") UserRolePrivilege UserRolePrivilege,
			HttpServletRequest request, HttpServletResponse response) {
		List<UserRolePrivilege> listOfUserRolePrivileges = null;
		
		try {
			listOfUserRolePrivileges = userRolePrivilegeService.searchUserRolePrivilegeDetails(UserRolePrivilege);
			logger.error("the list size"+listOfUserRolePrivileges.size());
			request.setAttribute("listOfUserRolePrivileges", listOfUserRolePrivileges);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "userRolesPrivilegesView";
	}
	@RequestMapping(value = "/UserRolePrivilegeEdit", method = RequestMethod.GET)
	public String UserRolePrivilegeEdit(@RequestParam("UserRolePrivilegeId") int UserRolePrivilegeId,
			@ModelAttribute("UserRolePrivilege") UserRolePrivilege UserRolePrivilege,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.error("UserRolePrivilegeid"+UserRolePrivilegeId);
		UserRolePrivilege UserRolePrivileges = userRolePrivilegeService.editUserRolePrivilegeDetails(UserRolePrivilegeId);
		
			BeanUtils.copyProperties(UserRolePrivilege, UserRolePrivileges);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "userRolesPrivilegesView";
	}
	@RequestMapping(value = "/UserRolePrivilegeUpdate", method = RequestMethod.POST)
	public String UserRolePrivilegeUpdate(@ModelAttribute("UserRolePrivilege") UserRolePrivilege UserRolePrivilege,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = userRolePrivilegeService.updateUserRolePrivilegeDetails(UserRolePrivilege);
			if (msg.equals("S")) {
				request.setAttribute("UserRolePrivilegeUpdate", "Success");
			} else {
				request.setAttribute("UserRolePrivilegeUpdateFail", "Success");
			}
			model.addAttribute("UserRolePrivilege", new UserRolePrivilege());
		} catch (Exception e) {
			request.setAttribute("UserRolePrivilegeUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "userRolesPrivilegesView";
	}
	@RequestMapping(value = "/UserRolePrivilegeDelete", method = RequestMethod.GET)
	public String UserRolePrivilegeDelete(@RequestParam("UserRolePrivilegeId") int UserRolePrivilegeId,
			@ModelAttribute("UserRolePrivilege") UserRolePrivilege UserRolePrivilege,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = userRolePrivilegeService.deleteUserRolePrivilegeDetails(UserRolePrivilegeId);
			if (msg.equals("S")) {
				request.setAttribute("UserRolePrivilegeDeleted", "S");
			} else {
				request.setAttribute("UserRolePrivilegeDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("UserRolePrivilegeDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "userRolesPrivilegesView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "UserRolePrivilegeId";

		Map<String, String> map =null;

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
	@ModelAttribute("privilegeDetails")
	public Map<Integer, String> populatePrivilegeDetails() {
		
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select r.privilegeId,r.privilege from com.mnt.tam.bean.Privilege r");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
}
