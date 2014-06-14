/**
 * 
 */
package com.mnt.tam.controller;

import java.util.Iterator;
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

import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentClass;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.StudentClassService;
import com.mnt.tam.service.StudentService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author venkateshp
 * 
 */
@Controller
public class StudentClassController {
	private static Logger logger = Logger.getLogger(StudentClassController.class);
	@Autowired
	private StudentClassService studentClassService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	private XmlLabelsService xmlService;
	String message= null;
	@RequestMapping(value = "/studentClassHome", method = RequestMethod.GET)
	public String studentHome(@ModelAttribute("studentClass") StudentClass studentClass) {
		return "studentClassView";
		// return new ModelAndView("login", "command", new Login());
	}
	
	@RequestMapping(value = "/checkStudentClassDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkStudentClassDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("stdClass") String stdClass) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = studentClassService.duplicateCheck(stdClass, null);
		if (checkName != 0) {
		    message = "Warning ! Student Class is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkStudentClassUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkStudentClassUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("stdClass") String stdClass, @RequestParam("stdClassId") String stdClassId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = studentClassService.duplicateCheck(stdClass, stdClassId);
		if (checkName != 0) {
		    message = "Warning !Student Class Name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	@RequestMapping(value = "/studentClassSaveDetails", method = RequestMethod.POST)
	public String studentSave(@ModelAttribute("studentClass") StudentClass studentClass,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = studentClassService.saveStudentClassDetails(studentClass);
			if(msg.equals("S"))
			{
				succMsg="redirect:studentClassHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:studentClassHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:studentClassHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}

	@RequestMapping(value = "/studentClassSearch", method = RequestMethod.GET)
	public String studentSearch(@ModelAttribute("studentClass")StudentClass studentClass,
			HttpServletRequest request, HttpServletResponse response) {
		List<StudentClass> listOfStudentClasses= null;
		try {
			listOfStudentClasses = studentClassService.searchStudentClassDetails(studentClass);
			request.setAttribute("listOfStudentClasses", listOfStudentClasses);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "studentClassView";
	}

@RequestMapping(value = "/studentClassEdit", method = RequestMethod.GET)
	public String studentEdit(@RequestParam("studentClassId") int studentClassId,
			@ModelAttribute("studentClass") StudentClass studentClass,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			
		StudentClass	students = studentClassService.editStudentClassDetails(studentClassId);
			BeanUtils.copyProperties(studentClass, students);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "studentClassView";
	}

@RequestMapping(value = "/studentClassUpdate", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute("studentClass") StudentClass studentClass,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = studentClassService.updateStudentClasssDetails(studentClass);
			if (msg.equals("S")) {
				request.setAttribute("studentClassUpdate", "Success");
			} else {
				request.setAttribute("studentClassUpdateFail", "Success");
			}
			model.addAttribute("studentClass", new StudentClass());
		} catch (Exception e) {
			request.setAttribute("studentClassUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "studentClassView";
	}

	@RequestMapping(value = "/studentClassDelete", method = RequestMethod.GET)
	public String studentDelete(@RequestParam("studentClassId") int studentClassId,
			@ModelAttribute("studentClass") StudentClass studentClass,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg =studentClassService.deleteStudentClassDetails(studentClassId);
			if (msg.equals("S")) {
				request.setAttribute("studentClassDeleted", "S");
			} else {
				request.setAttribute("studentClassDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("studentClassDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "studentClassView";
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "studentClassId";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ModelAttribute("studentDetails")
	public Map<Integer, String> populateStudentDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select s.studentId,s.fName from com.mnt.tam.bean.Student s");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
	
	
	@ModelAttribute("sectionDetails")
	public Map<Integer, String> populateSectionDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select s.sectionId,s.section from com.mnt.tam.bean.Section s");
			logger.info("section size iss==="+map.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
	
	@ModelAttribute("classDetails")
	public Map<Integer, String> populateClassDetails() {
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select s.classId,s.className from com.mnt.tam.bean.Classes s");
logger.info("classes size iss==="+map.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
}
