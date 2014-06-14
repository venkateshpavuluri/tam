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
import com.mnt.tam.bean.Student;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.StudentService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author venkateshp
 * 
 */
@Controller
public class StudentController {
	private static Logger logger = Logger.getLogger(StudentController.class);
	@Autowired
	private StudentService studentService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	private XmlLabelsService xmlService;
	String message = null;

	@RequestMapping(value = "/studentHome", method = RequestMethod.GET)
	public String studentHome(@ModelAttribute("student") Student student) {
		return "studentView";
		// return new ModelAndView("login", "command", new Login());
	}

	@RequestMapping(value = "/checkStudentDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkStudentDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("fName") String fName) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = studentService.duplicateCheck(fName,null);
		if (checkName != 0) {
		    message = "Warning ! Student name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	    @RequestMapping(value = "/checkStudentUpdateDuplicate", method = RequestMethod.POST)
	    public @ResponseBody
	    String checkStudentUpdateDuplicate(HttpServletRequest request,
		    HttpServletResponse response,
		    @RequestParam("fName") String fName, @RequestParam("studentId") String studentId) {
		response.setCharacterEncoding("UTF-8");
		Long checkName = studentService.duplicateCheck(fName,studentId);
		if (checkName != 0) {
		    message = "Warning ! Student Name is Already exists. Please try some other name";
		} else {
		    message = "";
		}
		return message;
	    }
	@RequestMapping(value = "/studentSaveDetails", method = RequestMethod.POST)
	public String studentSave(@ModelAttribute("student") Student student,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = studentService.saveStudentDetails(student);
			if(msg.equals("S"))
			{
				succMsg="redirect:studentHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:studentHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:studentHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;

	}

	@RequestMapping(value = "/studentSearch", method = RequestMethod.GET)
	public String studentSearch(@ModelAttribute("student") Student student,
			HttpServletRequest request, HttpServletResponse response) {
		List<Student> listOfStudents = null;
		try {
			listOfStudents = studentService.searchStudentDetails(student);
			request.setAttribute("listOfStudents", listOfStudents);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "studentView";
	}

	@RequestMapping(value = "/studentEdit", method = RequestMethod.GET)
	public String studentEdit(@RequestParam("studentId") int studentId,
			@ModelAttribute("student") Student student,
			HttpServletRequest request, HttpServletResponse response) {

		try {
		Student	students = studentService.editStudentDetails(studentId);
			BeanUtils.copyProperties(student, students);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "studentView";
	}

	@RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
	public String studentUpdate(@ModelAttribute("student") Student student,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = studentService.updateStudentDetails(student);
			if (msg.equals("S")) {
				request.setAttribute("studentUpdate", "Success");
			} else {
				request.setAttribute("studentUpdateFail", "Success");
			}
			model.addAttribute("student", new Student());
		} catch (Exception e) {
			request.setAttribute("studentUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "studentView";
	}

	@RequestMapping(value = "/studentDelete", method = RequestMethod.GET)
	public String studentDelete(@RequestParam("studentId") int studentId,
			@ModelAttribute("student") Student student,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = studentService.deleteStudentDetails(studentId);
			if (msg.equals("S")) {
				request.setAttribute("studentDeleted", "S");
			} else {
				request.setAttribute("studentDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("studentDeleteFail", "F");
			logger.error(e.getMessage());
		}
		return "studentView";
	}

	@ModelAttribute("schoolDetails")
	public Map<Integer, String> populateSchoolDetails() {
		
		Map<Integer, String> map = null;
		try {
			/*
			 * listvalues = categoryService .selectMaterialCategoryDetails();
			 */
			map = populateService
					.populateSelectBox("select v.schoolId,v.schoolName from com.mnt.tam.bean.School v");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
	@ModelAttribute("countryDetails")
	public Map<Integer, String> populateCountryDetails() {
		
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select c.countryId,c.country from com.mnt.tam.bean.Country c");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}

	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "studentId";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
