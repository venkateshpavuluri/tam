/**
 * 
 */
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


import com.mnt.tam.bean.TermTest;
import com.mnt.tam.bean.Users;
import com.mnt.tam.service.PopulateService;

import com.mnt.tam.service.TermTestService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author devi
 *
 */
@Controller
public class TermTestController {
	private static Logger logger = Logger.getLogger(TermTestController.class);
	@Autowired
	private TermTestService termTestService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	private XmlLabelsService xmlService;
	String msg;
	@RequestMapping(value = "/termTestHome", method = RequestMethod.GET)
	public String termTestHome(@ModelAttribute("termTest") TermTest term) {
		return "termTestHome";
		
	}
	@RequestMapping(value = "/checkTermTestDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTermTestDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("termtest") String tname) {
	response.setCharacterEncoding("UTF-8");
	
	Long checkName = termTestService.checkTermTestDup(tname, null);
	if (checkName != 0) {
	    msg = "Warning ! TermTest  is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
    @RequestMapping(value = "/checkTermTestUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTermTestUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("termtest") String ttestName, @RequestParam("termtest_Id") String ttid) {
	response.setCharacterEncoding("UTF-8");
		Long checkName = termTestService.checkTermTestDup(ttestName, ttid);
	if (checkName != 0) {
	    msg = "Warning ! TermTest is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }

	@RequestMapping(value = "/termTestSaveDetails", method = RequestMethod.POST)
	public String termTestSave(@ModelAttribute("termTest") TermTest ttest,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = termTestService.saveTermTestDetails(ttest);
			if(msg.equals("S"))
			{
				succMsg="redirect:termTestHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:termTestHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:termTestHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}
	@RequestMapping(value = "/termTestSearch", method = RequestMethod.GET)
	public String termTestSearch(@ModelAttribute("termTest") TermTest tTest,
			HttpServletRequest request, HttpServletResponse response) {
		List<TermTest> listOfTermTests = null;
		try {
			listOfTermTests = termTestService.searchTermTestDetails(tTest);
			request.setAttribute("listOfTermTests", listOfTermTests);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "termTestHome";
	}
	@RequestMapping(value = "/termTestEdit", method = RequestMethod.GET)
	public String termTestEdit(@RequestParam("termTestId") int termTestId,
			@ModelAttribute("termTest") TermTest ttest,
			HttpServletRequest request, HttpServletResponse response) {

		try {
		TermTest termTest1 = termTestService.editTermTestDetails(termTestId);
			BeanUtils.copyProperties(ttest, termTest1);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "termTestHome";
	}
	@RequestMapping(value = "/termTestUpdate", method = RequestMethod.POST)
	public String termTestUpdate(@ModelAttribute("termTest") TermTest tTest,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = termTestService.updateTermTestDetails(tTest);
			if (msg.equals("S")) {
				request.setAttribute("termTestUpdate", "Success");
			} else {
				request.setAttribute("termTestUpdateFail", "Success");
			}
			model.addAttribute("termTest", new TermTest());
		} catch (Exception e) {
			request.setAttribute("termTestUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "termTestHome";
	}

	@RequestMapping(value = "/termTestDelete", method = RequestMethod.GET)
	public String termTestDelete(@RequestParam("termtestId") int termTestId,
			@ModelAttribute("termTest") TermTest termtest,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = termTestService.deleteTermTestDetails(termTestId);
			if (msg.equals("S")) {
				request.setAttribute("termTestDeleted", "S");
			} else {
				request.setAttribute("termTestDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("termTestDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "termTestHome";
	}

	
	@ModelAttribute("termDetails")
	public Map<Integer, String> populateTermDetails() {
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select t.term_Id,t.term from com.mnt.tam.bean.TermBean t");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}

	
	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "termTest";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


}
