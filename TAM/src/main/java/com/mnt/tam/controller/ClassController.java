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

import com.mnt.tam.bean.Classes;
import com.mnt.tam.service.ClassService;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class ClassController {
	private static Logger logger = Logger.getLogger(ClassController.class);
	@Autowired
	XmlLabelsService xmlService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	ClassService classService;
	String message = null;
	@RequestMapping(value = "/Classes", method = RequestMethod.GET)
	public ModelAndView getClasses(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		return new ModelAndView("classView", "Classes",
				new Classes());
	}
	@RequestMapping(value = "/checkClassDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkClassDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("className") String className) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = classService.duplicateCheck(className, null);
		if (checkName != 0) {
		    message = "Warning ! Class name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkClassUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkClassUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("classNameId") String className, @RequestParam("classId") String classId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = classService.duplicateCheck(className, classId);
		if (checkName != 0) {
		    message = "Warning !Class Name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	@RequestMapping(value = "/ClassesSaveDetails", method = RequestMethod.POST)
	public String ClassesSave(@ModelAttribute("Classes") Classes Classes,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = classService.saveClassesDetails(Classes);
			if(msg.equals("S"))
			{
				succMsg="redirect:Classes.mnt?list=S";
			}
			else
			{
				succMsg="redirect:Classes.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:Classes.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}
	
	@RequestMapping(value = "/ClassesSearch", method = RequestMethod.GET)
	public String ClassesSearch(@ModelAttribute("Classes") Classes Classes,
			HttpServletRequest request, HttpServletResponse response) {
		List<Classes> listOfClassess = null;
		
		try {
			listOfClassess = classService.searchClassesDetails(Classes);
			logger.error("the list size"+listOfClassess.size());
			request.setAttribute("listOfClassess", listOfClassess);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "classView";
	}
	@RequestMapping(value = "/ClassesEdit", method = RequestMethod.GET)
	public String ClassesEdit(@RequestParam("ClassesId") int ClassesId,
			@ModelAttribute("Classes") Classes Classes,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.error("Classesid"+ClassesId);
		Classes Classess = classService.editClassesDetails(ClassesId);
		
			BeanUtils.copyProperties(Classes, Classess);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "classView";
	}
	@RequestMapping(value = "/ClassesUpdate", method = RequestMethod.POST)
	public String ClassesUpdate(@ModelAttribute("Classes") Classes Classes,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = classService.updateClassesDetails(Classes);
			if (msg.equals("S")) {
				request.setAttribute("ClassesUpdate", "Success");
			} else {
				request.setAttribute("ClassesUpdateFail", "Success");
			}
			model.addAttribute("Classes", new Classes());
		} catch (Exception e) {
			request.setAttribute("ClassesUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "classView";
	}
	@RequestMapping(value = "/ClassesDelete", method = RequestMethod.GET)
	public String ClassesDelete(@RequestParam("ClassesId") int ClassesId,
			@ModelAttribute("Classes") Classes Classes,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = classService.deleteClassesDetails(ClassesId);
			if (msg.equals("S")) {
				request.setAttribute("ClassesDeleted", "S");
			} else {
				request.setAttribute("ClassesDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("ClassesDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "classView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "ClassesId";

		Map<String, String> map =null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@ModelAttribute("schoolDetails")
	public Map<Integer, String> populateClassDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select c.schoolId,c.schoolName from com.mnt.tam.bean.School c");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
}
