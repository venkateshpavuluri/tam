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

import com.mnt.tam.bean.Privilege;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.PrivilegeService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class PrivilegeController {
	private static Logger logger = Logger.getLogger(PrivilegeController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	PrivilegeService PrivilegeService;
	String message =null;
	@RequestMapping(value = "/Privilege", method = RequestMethod.GET)
	public ModelAndView getPrivilege(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
//return "privilegeView";
		return new ModelAndView("privilegeView", "Privilege",
				new Privilege());
	}
	 @RequestMapping(value = "/checkPrivilegeDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkPrivilegeDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("privilege") String privilege) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = PrivilegeService.duplicateCheck(privilege, null);
		if (checkName != 0) {
		    message = "Warning ! Privilege is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkPrivilegeUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkPrivilegeUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("privilege") String privilege, @RequestParam("privilegeId") String privilegeId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = PrivilegeService.duplicateCheck(privilege, privilegeId);
		if (checkName != 0) {
		    message = "Warning !Privilege is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	
	@RequestMapping(value = "/PrivilegeSaveDetails", method = RequestMethod.POST)
	public String PrivilegeSave(@ModelAttribute("Privilege") Privilege Privilege,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = PrivilegeService.savePrivilegeDetails(Privilege);
			if(msg.equals("S"))
			{
				succMsg="redirect:Privilege.mnt?list=S";
			}
			else
			{
				succMsg="redirect:Privilege.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:Privilege.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}
	
	@RequestMapping(value = "/PrivilegeSearch", method = RequestMethod.GET)
	public String PrivilegeSearch(@ModelAttribute("Privilege") Privilege Privilege,
			HttpServletRequest request, HttpServletResponse response) {
		List<Privilege> listOfPrivileges = null;
		
		try {
			listOfPrivileges = PrivilegeService.searchPrivilegeDetails(Privilege);
			logger.error("the list size"+listOfPrivileges.size());
			request.setAttribute("listOfPrivileges", listOfPrivileges);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "privilegeView";
	}
	@RequestMapping(value = "/PrivilegeEdit", method = RequestMethod.GET)
	public String PrivilegeEdit(@RequestParam("PrivilegeId") int PrivilegeId,
			@ModelAttribute("Privilege") Privilege Privilege,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.error("Privilegeid"+PrivilegeId);
		Privilege Privileges = PrivilegeService.editPrivilegeDetails(PrivilegeId);
		logger.error("PrivilegeName"+Privileges.getPrivilege());
			BeanUtils.copyProperties(Privilege, Privileges);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "privilegeView";
	}
	@RequestMapping(value = "/PrivilegeUpdate", method = RequestMethod.POST)
	public String PrivilegeUpdate(@ModelAttribute("Privilege") Privilege Privilege,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = PrivilegeService.updatePrivilegeDetails(Privilege);
			if (msg.equals("S")) {
				request.setAttribute("PrivilegeUpdate", "Success");
			} else {
				request.setAttribute("PrivilegeUpdateFail", "Success");
			}
			model.addAttribute("Privilege", new Privilege());
		} catch (Exception e) {
			request.setAttribute("PrivilegeUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "privilegeView";
	}
	@RequestMapping(value = "/PrivilegeDelete", method = RequestMethod.GET)
	public String PrivilegeDelete(@RequestParam("PrivilegeId") int PrivilegeId,
			@ModelAttribute("Privilege") Privilege Privilege,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = PrivilegeService.deletePrivilegeDetails(PrivilegeId);
			if (msg.equals("S")) {
				request.setAttribute("PrivilegeDeleted", "S");
			} else {
				request.setAttribute("PrivilegeDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("PrivilegeDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "privilegeView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "PrivilegeId";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
