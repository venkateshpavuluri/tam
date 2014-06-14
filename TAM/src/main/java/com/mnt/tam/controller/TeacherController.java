/**
 * 
 */
package com.mnt.tam.controller;




import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import com.mnt.tam.bean.Teacher;
import com.mnt.tam.bean.TeacherSubject;
import com.mnt.tam.bean.TermBean;

import com.mnt.tam.service.PopulateService;

import com.mnt.tam.service.TeacherService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author devi
 *
 */
@Controller
@Transactional
public class TeacherController {
	private static Logger logger = Logger.getLogger(TeacherController.class);
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private PopulateService populateService;
	@Autowired
	private XmlLabelsService xmlService;
	String msg;
	@RequestMapping(value = "/teacherHome", method = RequestMethod.GET)
	public String teacherHome(@ModelAttribute("teacher") Teacher teacher) {
		return "teacherHome";
			}
	

	@RequestMapping(value = "/checkTeacherDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTeacherDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("fName") String fName) {
	response.setCharacterEncoding("UTF-8");
	
	Long checkName = teacherService.checkTeacherCount(fName, null);
	if (checkName != 0) {
	    msg = "Warning ! Teacher is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
    @RequestMapping(value = "/checkTeacherUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTeacherUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("fName") String fName, @RequestParam("teacherId") String tid) {
	response.setCharacterEncoding("UTF-8");
		Long checkName = teacherService.checkTeacherCount(fName, tid);
	if (checkName != 0) {
	    msg = "Warning ! Teacher is Already exists. Please try some other name";
	} else {
	    msg = "";
	}
	return msg;
    }
	@RequestMapping(value = "/saveTeacherDetails", method = RequestMethod.POST)
	public String teacherSave(@ModelAttribute("teacher") Teacher teach,
			HttpServletRequest request, HttpServletResponse response) {
			
	    String succMsg = null;
	   
	    try {
		
		
			String msg = teacherService.saveTeacherDetails(teach);
	
			if(msg.equals("S"))
			{
				succMsg="redirect:teacherHome.mnt?list=S";
				
			}
			else
			{
				succMsg="redirect:teacherHome.mnt?listWar=F";
				
			}
		} catch (Exception e) {
			succMsg="redirect:teacherHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
			
		}
	   return succMsg;

	}
	@RequestMapping(value = "/searchTeacherDetails", method = RequestMethod.GET)
	public String teacherSearch(@ModelAttribute("teacher") Teacher teacher,
			HttpServletRequest request, HttpServletResponse response) {
		List<Teacher> listOfTeachers = null;
		try {
			listOfTeachers = teacherService.searchTeacherDetails(teacher);
			request.setAttribute("listOfTeachers", listOfTeachers);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "teacherHome";
	}
	
	
	@RequestMapping(value = "/teacherEdit", method = RequestMethod.GET)
	public String teacherEdit(@RequestParam("teacherEditId") int teacherId,
			@ModelAttribute("teacher") Teacher teach,
			HttpServletRequest request, HttpServletResponse response,Model model) {
	Teacher teachers=null;
		try {
			 teachers=teacherService.editTeacherDetails(teacherId);
			 
		Set<TeacherSubject> teacherSubjects=teachers.getTeacherSubjects();
		List<TeacherSubject> subjects=new LinkedList<TeacherSubject>();
		for(TeacherSubject t:teacherSubjects){
			subjects.add(t);
		}
		teachers.setTeachers(subjects);
			teachers.setEditMsg("Edit");
			 model.addAttribute("teacher",teachers);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "teacherHome";
	}
	@RequestMapping(value = "/updateTeacherDetails", method = RequestMethod.POST)
	public String updateTeacher(@ModelAttribute("teacher") Teacher teach,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg=teacherService.updateTeacherDetails(teach);
			if (msg.equals("S")) {
				request.setAttribute("teacherUpdate", "Success");
			} else {
				request.setAttribute("teacherUpdateFail", "fail");
			}
			model.addAttribute("teacher", new Teacher());
		} catch (Exception e) {
			request.setAttribute("teacherUpdateFail", "Success");
			logger.error(e.getMessage());
		}
		return "teacherHome";
	}
	@RequestMapping(value = "/teacherDelete", method = RequestMethod.GET)
	public String teacherDelete(@RequestParam("teacherDelId") int teacherId,
			@ModelAttribute("teacher") Teacher teach,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = teacherService.deleteTeacherDetails(teacherId);
			if (msg.equals("S")) {
				request.setAttribute("teacherDeleted", "S");
			} else {
				request.setAttribute("teacherDeleteFail", "F");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("teacherDeleteFail", "F");
			
		}
		return "teacherHome";
	}
	
	@ModelAttribute("teacherTypeDetails")
	public Map<Integer, String> populateTeacherTypeDetails() {
		
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select t.teacherTypeId,t.teacherType from com.mnt.tam.bean.TeacherType t");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
	@ModelAttribute("subjectDetails")
	public Map<Integer, String> populateSubjectDetails() {
		
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select s.subjectId,s.subject from com.mnt.tam.bean.SubjectBean s");

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}
	
	@ModelAttribute("classDetails")
	public Map<Integer, String> populateClassDetails() {
		
		Map<Integer, String> map = null;
		try {
			
			map = populateService
					.populateSelectBox("select c.classId,c.className from com.mnt.tam.bean.Classes c");
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
		String name = "teacher";

		Map<String, String> map = null;

		try {
			map = xmlService.populateXmlLabels(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
