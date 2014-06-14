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

import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.SubjectService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class SubjectController {

    private static Logger logger = Logger.getLogger(SubjectController.class);
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    private XmlLabelsService xmlService;
    String message= null;

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public String subjectHome(
	    @ModelAttribute("subjectCmd") SubjectBean subjectBean) {
	return "subjectView";
	// return new ModelAndView("login", "command", new Login());
    }
    @RequestMapping(value = "/checkSubjectDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSubjectDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("subject") String subject) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = subjectService.duplicateChek(subject, null);
	if (checkName != 0) {
	    message = "Warning ! Subject name is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/checkSubjectUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSubjectUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("subject") String subject,
	    @RequestParam("subjectId") String subjectId) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = subjectService.duplicateChek(subject, subjectId);
	if (checkName != 0) {
	    message = "Warning !Subject name is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }
    @RequestMapping(value = "/subjectSearch", method = RequestMethod.GET)
    public String subjectSearch(
	    @ModelAttribute("subjectCmd") SubjectBean subjectBean,
	    HttpServletRequest request, HttpServletResponse response) {
	List<SubjectBean> listOfSubjects = null;
	try {
	    listOfSubjects = subjectService.searchSubjectDetails(subjectBean);
	    request.setAttribute("listOfSubjects", listOfSubjects);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "subjectView";
    }

    @RequestMapping(value = "/saveSubjectDetails", method = RequestMethod.POST)
    public String saveSubjectDetails(
	    @ModelAttribute("subjectCmd") SubjectBean subjectBean,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = subjectService.saveSubjectDetails(subjectBean);
	    if (msg.equals("S")) {
		succMsg = "redirect:subject.mnt?list=S";
	    } else {
		succMsg = "redirect:subject.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:subject.mnt?listWar=F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return succMsg;

    }

    @ModelAttribute("subjectDetails")
    public Map<Integer, String> populateSubjectDetails() {
	Map<Integer, String> map = null;
	try {
	    /*
	     * listvalues = categoryService .selectMaterialCategoryDetails();
	     */
	    map = populateService
		    .populateSelectBox("select s.subjectTypeId, s.subjectType from com.mnt.tam.bean.SubjectType s");

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
					.populateSelectBox("select s.classId,s.className from com.mnt.tam.bean.Classes s");
logger.info("classes size iss==="+map.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return map;
	}
    @ModelAttribute("parenDetails")
    public Map<Integer, String> populateParentSubjectDetails() {
	Map<Integer, String> map = null;
	try {
	    /*
	     * listvalues = categoryService .selectMaterialCategoryDetails();
	     */
	    map = populateService
		    .populateSelectBox("select s.subjectId, s.subject  from com.mnt.tam.bean.SubjectBean s");

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	return map;
    }
    @ModelAttribute("xmlItems")
    public Map<String, String> populatLabelDetails() {
	String name = "subjectId";
	Map<String, String> map = null;
	try {
	    map = xmlService.populateXmlLabels(name);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return map;
    }
    @RequestMapping(value = "/subjectEdit", method = RequestMethod.GET)
    public String subjectEdit(@RequestParam("subjectId") int subjectId,
	    @ModelAttribute("subjectCmd") SubjectBean subjectBean,
	    HttpServletRequest request, HttpServletResponse response) {
	try {
	    SubjectBean subjectBeans = subjectService
		    .editSubjectDetails(subjectId);
	    BeanUtils.copyProperties(subjectBean, subjectBeans);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "subjectView";
    }
    @RequestMapping(value = "/subjectUpdate", method = RequestMethod.POST)
    public String subjectUpdate(
	    @ModelAttribute("subjectCmd") SubjectBean subjectBean,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = subjectService.updateSubjectDetails(subjectBean);
	    if (msg.equals("S")) {
		request.setAttribute("subjectUpdate", "Success");
	    } else {
		request.setAttribute("subjectUpdateFail", "Success");
	    }
	    model.addAttribute("subjectCmd", new SubjectBean());
	} catch (Exception e) {
	    request.setAttribute("subjectUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "subjectView";
    }
    @RequestMapping(value = "/subjectDelete", method = RequestMethod.GET)
	public String subjectDelete(@RequestParam("subjectId") int subjectId,
			@ModelAttribute("subjectCmd") SubjectBean subjectBean,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			String msg = subjectService.deleteSubjectDetails(subjectId);
			if (msg.equals("S")) {
				request.setAttribute("subjectDeleted", "S");
			} else {
				request.setAttribute("subjectDeleteFail", "F");
			}
		} catch (Exception e) {
			request.setAttribute("subjectDeleteFail", "F");
			logger.error(e.getMessage());
		}
	return "subjectView";
	}
}
