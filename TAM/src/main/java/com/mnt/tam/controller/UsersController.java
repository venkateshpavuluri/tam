/**
 * 
 */
package com.mnt.tam.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mnt.tam.service.DateConversionService;
import com.mnt.tam.bean.Roles;
import com.mnt.tam.bean.Users;
import com.mnt.tam.service.UserService;
import com.mnt.tam.service.XmlLabelsService;
/**
 * @author devi
 *
 */
@Controller
public class UsersController {
	@Autowired
	UserService userService;
	@Autowired
	private XmlLabelsService xmlService;
	@Autowired
	DateConversionService dateService;
	String msg;

	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(@ModelAttribute("users") Users user) {
		return "userHome";
		
	}
	@RequestMapping(value = "/checkUserDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkUserDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("userName") String uname) {
	response.setCharacterEncoding("UTF-8");
	Long checkEventName = userService.checkUserDup(uname, null);
	if (checkEventName != 0) {
	    msg = "Warning ! User is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
    @RequestMapping(value = "/checkUserUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSubjectTypeUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("userName") String uname, @RequestParam("userId") String userid) {
	response.setCharacterEncoding("UTF-8");
	Long checkEventName = userService.checkUserDup(uname, userid);
	if (checkEventName != 0) {
	    msg = "Warning ! User is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }

	@RequestMapping(value = "/saveUsersDetails", method = RequestMethod.POST)
	public String saveUsers(@ModelAttribute("users") Users user,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			
          user.setValidFrom(dateService.dateFormat(dateService.dateParse(user.getValidFrom(),"au"),"au"));
          user.setValidTo(dateService.dateFormat(dateService.dateParse(user.getValidTo(),"au"),"au"));
          
			String msg = userService.saveUsersDetails(user);
			if(msg.equals("S"))
			{
				succMsg="redirect:userHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:userHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:userHome.mnt?listWar=F";
			e.printStackTrace();
		}
		return succMsg;

	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
	public String searchUsers(@ModelAttribute("users") Users user,
			HttpServletRequest request, HttpServletResponse response) {
		List<Users> listOfUsers = null;
		try {
			listOfUsers = userService.searchUsersDetails(user);
			
			request.setAttribute("listOfUsers", listOfUsers);
		} catch (Exception e) {
			
		}
		return "userHome";
	}

	@RequestMapping(value = "/usersEdit", method = RequestMethod.GET)
	public String userEdit(@RequestParam("userEditId") int userId,
			@ModelAttribute("users") Users user,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			
		Users users = userService.editUsersDetails(userId);
			BeanUtils.copyProperties(user, users);
		} catch (Exception e) {
			
		}
		return "userHome";
	}

	@RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
	public String userUpdate(@ModelAttribute("users") Users user,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = userService.updateUsersDetails(user);
			if (msg.equals("S")) {
				request.setAttribute("userUpdate", "Success");
			} else {
				request.setAttribute("userUpdateFail", "Success");
			}
			model.addAttribute("user", new Users());
		} catch (Exception e) {
			request.setAttribute("userUpdateFail", "Success");
		
		}
		return "userHome";
	}
	@RequestMapping(value = "/usersDelete", method = RequestMethod.GET)
	public String userDelete(@RequestParam("userDelId") int userId,
			@ModelAttribute("users") Users user,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg =userService.deleteUsersDetails(userId);
			if (msg.equals("S")) {
				request.setAttribute("userDeleted", "S");
			} else {
				request.setAttribute("userDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("userDeleteFail", "F");
			
		}
		return "userHome";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "username";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
