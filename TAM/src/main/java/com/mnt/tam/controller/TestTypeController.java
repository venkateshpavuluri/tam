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

import com.mnt.tam.bean.TestType;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.TestTypeService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class TestTypeController {
	private static Logger logger = Logger.getLogger(TestTypeController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	TestTypeService testTypeService;
	String message = null;
	@RequestMapping(value = "/TestType", method = RequestMethod.GET)
	public ModelAndView getTestType(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
//return "testTypeView";
		return new ModelAndView("testTypeView", "TestType",
				new TestType());
	}
	
	@RequestMapping(value = "/checkTestDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkTestDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("testTypeName") String testType) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = testTypeService.duplicateCheck(testType, null);
		if (checkName != 0) {
		    message = "Warning ! Test Type is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkTestUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkTestUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("testTypeName") String testType, @RequestParam("testTypeId") String testTypeId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = testTypeService.duplicateCheck(testType, testTypeId);
		if (checkName != 0) {
		    message = "Warning ! Test Type is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }

	@RequestMapping(value = "/TestTypeSaveDetails", method = RequestMethod.POST)
	public String TestTypeSave(@ModelAttribute("TestType") TestType TestType,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = testTypeService.saveTestTypeDetails(TestType);
			if(msg.equals("S"))
			{
				succMsg="redirect:TestType.mnt?list=S";
			}
			else
			{
				succMsg="redirect:TestType.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:TestType.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}
	
	@RequestMapping(value = "/TestTypeSearch", method = RequestMethod.GET)
	public String TestTypeSearch(@ModelAttribute("TestType") TestType TestType,
			HttpServletRequest request, HttpServletResponse response) {
		List<TestType> listOfTestTypes = null;
		
		try {
			listOfTestTypes = testTypeService.searchTestTypeDetails(TestType);
			logger.error("the list size"+listOfTestTypes.size());
			request.setAttribute("listOfTestTypes", listOfTestTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "testTypeView";
	}
	@RequestMapping(value = "/TestTypeEdit", method = RequestMethod.GET)
	public String TestTypeEdit(@RequestParam("TestTypeId") int TestTypeId,
			@ModelAttribute("TestType") TestType TestType,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.error("TestTypeid"+TestTypeId);
		TestType TestTypes = testTypeService.editTestTypeDetails(TestTypeId);
		logger.error("TestTypeName"+TestTypes.getTestType());
			BeanUtils.copyProperties(TestType, TestTypes);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "testTypeView";
	}
	@RequestMapping(value = "/TestTypeUpdate", method = RequestMethod.POST)
	public String TestTypeUpdate(@ModelAttribute("TestType") TestType TestType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = testTypeService.updateTestTypeDetails(TestType);
			if (msg.equals("S")) {
				request.setAttribute("TestTypeUpdate", "Success");
			} else {
				request.setAttribute("TestTypeUpdateFail", "Success");
			}
			model.addAttribute("TestType", new TestType());
		} catch (Exception e) {
			request.setAttribute("TestTypeUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "testTypeView";
	}
	@RequestMapping(value = "/TestTypeDelete", method = RequestMethod.GET)
	public String TestTypeDelete(@RequestParam("TestTypeId") int TestTypeId,
			@ModelAttribute("TestType") TestType TestType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = testTypeService.deleteTestTypeDetails(TestTypeId);
			if (msg.equals("S")) {
				request.setAttribute("TestTypeDeleted", "S");
			} else {
				request.setAttribute("TestTypeDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("TestTypeDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "testTypeView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "TestTypeId";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
