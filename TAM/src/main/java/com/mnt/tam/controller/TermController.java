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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnt.tam.bean.TermBean;
import com.mnt.tam.service.XmlLabelsServiceImpl;
import com.mnt.tam.service.TermService;


@Controller
public class TermController {
	@Autowired
	TermService tService;
	@Autowired
	XmlLabelsServiceImpl xmlService;
	String msg, res=null;
	private static Logger logger = Logger.getLogger(TermController.class);
	@RequestMapping(value = "/termHome", method = RequestMethod.GET)
	@RequestScoped
	public String getTermHome(@ModelAttribute TermBean sambean,
			SessionStatus status, HttpServletResponse response, Model model,HttpServletRequest request) {
				model.addAttribute("termCommand", sambean);
		return "termHome";
	}

	
	@RequestMapping(value = "/checkTermDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTermDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("term") String tName) {
	response.setCharacterEncoding("UTF-8");
	
	Long checkName = tService.checkTermCout(tName, null);
	if (checkName != 0) {
	    msg = "Warning ! Term is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
    @RequestMapping(value = "/checkTermUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTermUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("term") String tName, @RequestParam("term_Id") String tId) {
	response.setCharacterEncoding("UTF-8");
		Long checkName = tService.checkTermCout(tName, tId);
	if (checkName != 0) {
	    msg = "Warning ! Term is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
	
	@RequestMapping(value="/termAdd" , method= RequestMethod.POST)
		public String AddTerm(@ModelAttribute("termCommand") TermBean tBean,
				HttpServletRequest request, SessionStatus status,
				HttpServletResponse response)
	{ 
		
		
		try{
			
			msg=tService.saveTermDetails(tBean);
			
			if(msg.equals("S"))
				res = "redirect:termHome.mnt?list=" + "success" + "";
			else
				res = "redirect:termHome.mnt?listwar=" + "fail" + "";
								
	      }catch (Exception ex) { 
	    	  res = "redirect:termHome.mnt?listwar=" + "fail" + "";
	          
	      }
		
		return res;
	}
	
	@RequestMapping(value = "/termSearch", method = RequestMethod.POST)
	public String searchTerm(
			@ModelAttribute("termCommand") TermBean tbean,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		List<Object[]> list = null;
		List<TermBean> termBean = new ArrayList<TermBean>();
        
		try {
			list=tService.searchTermDetails(tbean);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				TermBean tb = new TermBean();
				Object[] obj = (Object[]) iterator.next();
				tb.setTerm_Id((Integer) obj[0]);
				tb.setTerm((String) obj[1]);
				termBean.add(tb);
			}

			request.setAttribute("termBeanView", termBean);

			


		} catch (Exception e) {
		
		}
		return "termHome";

	}
	@RequestMapping(value = "/termEdit", method = RequestMethod.GET)
	public String termEdit(@RequestParam("termEditId") int termEditId,
			@ModelAttribute("termCommand") TermBean term,
			HttpServletRequest request, HttpServletResponse response) {

		try {
		TermBean tbean = tService.editTermDetails(termEditId);
			BeanUtils.copyProperties(term, tbean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "termHome";
	}
	@RequestMapping(value = "/termUpdate", method = RequestMethod.POST)
	public String termUpdate(@ModelAttribute("termCommand") TermBean term,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = tService.updateTermDetails(term);
			if (msg.equals("S")) {
				request.setAttribute("termUpdate", "Success");
			} else {
				request.setAttribute("termUpdateFail", "Success");
			}
			model.addAttribute("termCommand", new TermBean());
		} catch (Exception e) {
			request.setAttribute("termUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "termHome";
	}
	@RequestMapping(value = "/termDelete", method = RequestMethod.GET)
	public String DeleteTerm(
			@ModelAttribute("termCommand") TermBean tdelete,
			HttpServletRequest request, HttpServletResponse response,Model model) {
		response.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("termDelId"));
		try {

			String msg = tService.deleteTermDetails(id);
			if (msg == "S") {
					request.setAttribute("termDel",  "Term Data Deleted Successfully");
				
			} else {

				request.setAttribute("termDelErr",  "Term Data Doesn't deleted properly");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("termCommand", new TermBean());
		return "termHome";
	}
	
	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "term";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
		
}
