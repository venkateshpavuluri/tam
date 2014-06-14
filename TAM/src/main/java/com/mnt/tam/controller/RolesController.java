/**
 * 
 */
package com.mnt.tam.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
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
import org.springframework.web.bind.support.SessionStatus;

import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.TermBean;

import com.mnt.tam.service.RoleService;
import com.mnt.tam.service.XmlLabelsServiceImpl;

/**
 * @author devi
 *
 */
@Controller
public class RolesController {
	@Autowired
	RoleService roleService;
	
	@Autowired
	XmlLabelsServiceImpl xmlService;
	
	String msg, res=null;
	
	private static Logger logger = Logger.getLogger(TermController.class);
	@RequestMapping(value = "/roleHome", method = RequestMethod.GET)
	@RequestScoped
	public String getRoleHome(@ModelAttribute Roles role,
			SessionStatus status, HttpServletResponse response, Model model,HttpServletRequest request) {
				model.addAttribute("roleCommand", role);
		return "roleHome";
	}
	
	@RequestMapping(value = "/checkRoleDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkRoleDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("role_Name") String roleName) {
	response.setCharacterEncoding("UTF-8");
	
	Long checkName = roleService.roleDup(roleName, null);
	if (checkName != 0) {
	    msg = "Warning ! Role  is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
    @RequestMapping(value = "/checkRoleUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkRoleUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("role_Name") String rName, @RequestParam("role_Id") String roleId) {
	response.setCharacterEncoding("UTF-8");
		Long checkName = roleService.roleDup(rName, roleId);
	if (checkName != 0) {
	    msg = "Warning ! Role is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
	
	
	@RequestMapping(value="/roleAdd" , method= RequestMethod.POST)
	public String AddRole(@ModelAttribute("roleCommand") Roles rBean,
			HttpServletRequest request, SessionStatus status,
			HttpServletResponse response)
{ 
				
	try{
		
		msg=roleService.saveRoleDetails(rBean);
		
		if(msg.equals("S"))
			res = "redirect:roleHome.mnt?list=" + "success" + "";
		else
			res = "redirect:roleHome.mnt?listwar=" + "fail" + "";
							
      }catch (Exception ex) { 
    	  res = "redirect:roleHome.mnt?listwar=" + "fail" + "";
          
      }
	return res;
}

@RequestMapping(value = "/roleSearch", method = RequestMethod.POST)
public String searchRole(
		@ModelAttribute("roleCommand") Roles rbean,
		Model model, HttpServletRequest request,
		HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	List<Object[]> list = null;
	List<Roles> roleBean = new ArrayList<Roles>();
    
	try {
		list=roleService.searchRoleDetails(rbean);
		Iterator<Object[]> iterator = list.iterator();
		while (iterator.hasNext()) {
			Roles rb = new Roles();
			Object[] obj = (Object[]) iterator.next();
			rb.setRole_Id((Integer)obj[0]);
			rb.setRole_Name((String) obj[1]);
			roleBean.add(rb);
		}
		request.setAttribute("roleView", roleBean);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return "roleHome";

}
@RequestMapping(value = "/roleEdit", method = RequestMethod.GET)
public String roleEdit(@RequestParam("roleEditId") int roleEditId,
		@ModelAttribute("roleCommand") Roles role,
		HttpServletRequest request, HttpServletResponse response) {

	try {
	Roles rbean = roleService.editRoleDetails(roleEditId);
		BeanUtils.copyProperties(role, rbean);
	} catch (Exception e) {
		logger.error(e.getMessage());
	}
	return "roleHome";
}

@RequestMapping(value = "/roleUpdate", method = RequestMethod.POST)
public String roleUpdate(@ModelAttribute("roleCommand") Roles role,
		HttpServletRequest request, HttpServletResponse response,
		Model model) {
	try {
		String msg = roleService.updateRoleDetails(role);
		if (msg.equals("S")) {
			request.setAttribute("roleUpdate", "Success");
		} else {
			request.setAttribute("roleUpdateFail", "Success");
		}
		model.addAttribute("roleCommand", new Roles());
	} catch (Exception e) {
		request.setAttribute("roleUpdateFail", "Success");
		logger.error(e.getMessage());
	}
	return "roleHome";
}

@RequestMapping(value = "/roleDelete", method = RequestMethod.GET)
public String DeleteRole(
		@ModelAttribute("roleCommand") Roles rdelete,
		HttpServletRequest request, HttpServletResponse response,Model model) {
	response.setCharacterEncoding("UTF-8");
	
	int id = Integer.parseInt(request.getParameter("roleDelId"));
	try {

		String msg = roleService.deleteRoleDetails(id);
		if (msg == "S") {
				request.setAttribute("roleDel",  "role Data Deleted Successfully");
			
		} else {

			request.setAttribute("roleDelErr",  "role Data Doesn't deleted properly");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

	model.addAttribute("roleCommand", new Roles());
	return "roleHome";
}

@ModelAttribute("xmlItems")
public Map<String, String> populatLabelDetails() {
	String name = "role";

	Map<String, String> map =null;

	try {
		map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
}

	


}
