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
import com.mnt.tam.bean.Test;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.TestService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author venkateshp
 * 
 */
@Controller
public class TestController {
	private static Logger logger = Logger.getLogger(TestController.class);
	@Autowired
	private TestService testService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	private XmlLabelsService xmlService;
	String message= null;

	@RequestMapping(value = "/testHome", method = RequestMethod.GET)
	public String studentHome(@ModelAttribute("test") Test test) {
		return "testView";
		// return new ModelAndView("login", "command", new Login());
	}
	
	@RequestMapping(value = "/checkTestNameDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkTestDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("testName") String testName) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = testService.duplicateCheck(testName, null);
		if (checkName != 0) {
		    message = "Warning ! Test name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkTestNameUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkTestUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("testName") String testName, @RequestParam("testId") String testId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = testService.duplicateCheck(testName, testId);
		if (checkName != 0) {
		    message = "Warning !Test Name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	
	@RequestMapping(value = "/saveTestDetails", method = RequestMethod.POST)
	public String studentSave(@ModelAttribute("test") Test test,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = testService.saveTestDetails(test);
			if(msg.equals("S"))
			{
				succMsg="redirect:testHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:testHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:testHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}

	@RequestMapping(value = "/testSearch", method = RequestMethod.GET)
	public String studentSearch(@ModelAttribute("test")Test test,
			HttpServletRequest request, HttpServletResponse response) {
		List<Test> listOfTests= null;
		try {
			listOfTests = testService.searchTestDetails(test);
			request.setAttribute("listOfTests", listOfTests);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "testView";
	}

	@RequestMapping(value = "/testEdit", method = RequestMethod.GET)
	public String studentEdit(@RequestParam("testId") int testId,
			@ModelAttribute("test") Test test,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			
     	Test tests = testService.editTestDetails(testId);
			BeanUtils.copyProperties(test, tests);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "testView";
	}

	@RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute("test") Test test,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = testService.updateTestDetails(test);
			if (msg.equals("S")) {
				request.setAttribute("testUpdate", "Success");
			} else {
				request.setAttribute("testUpdateFail", "Success");
			}
			model.addAttribute("test", new Test());
		} catch (Exception e) {
			request.setAttribute("testUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "testView";
	}

	@RequestMapping(value = "/testDelete", method = RequestMethod.GET)
	public String studentDelete(@RequestParam("testId") int testId,
			@ModelAttribute("test") Test test,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg =testService.deleteTestDetails(testId);
			if (msg.equals("S")) {
				request.setAttribute("testDeleted", "S");
			} else {
				request.setAttribute("testDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("testDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "testView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "testId";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ModelAttribute("termTestDetails")
	public Map<Integer, String> populateStudentDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select t.termtest_Id,t.termtest from com.mnt.tam.bean.TermTest t");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
	
	
	@ModelAttribute("subjectDetails")
	public Map<Integer, String> populateSectionDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select s.subjectId,s.subject from com.mnt.tam.bean.SubjectBean s");
			logger.info("section size iss==="+map.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
	
	@ModelAttribute("testTypeDetails")
	public Map<Integer, String> populateClassDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select t.testTypeId,t.testType from com.mnt.tam.bean.TestType t");
logger.info("classes size iss==="+map.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
}
