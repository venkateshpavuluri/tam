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

import com.mnt.tam.bean.SubjectType;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.SubjectTypeService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class SubjectTypeController {

    private static Logger logger = Logger
	    .getLogger(SubjectTypeController.class);
    @Autowired
    private SubjectTypeService subjectTypeService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    private XmlLabelsService xmlService;
    String message = null;

    @RequestMapping(value = "/subjectType", method = RequestMethod.GET)
    public String subjectTypeView(@ModelAttribute("subTypeCmd") SubjectType type) {
	return "subTypeView";
	// return new ModelAndView("login", "command", new Login());
    }

    @RequestMapping(value = "/saveSubjectType", method = RequestMethod.POST)
    public String subjectTypeSave(
	    @ModelAttribute("subTypeCmd") SubjectType type,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = subjectTypeService.saveSubjectTypeDetails(type);
	    if (msg.equals("S")) {
		succMsg = "redirect:subjectType.mnt?list=S";
	    } else {
		succMsg = "redirect:subjectType.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:subjectType.mnt?listWar=F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return succMsg;

    }

    @RequestMapping(value = "/checkSubjectTypeDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSubjectTypeDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("subjectType") String subjectType) {
	response.setCharacterEncoding("UTF-8");
	Long checkEventName = subjectTypeService.duplicateCheck(subjectType,null);
	if (checkEventName != 0) {
	    message = "Warning ! Subject Type is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }
    @RequestMapping(value = "/checkSubjectTypeUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSubjectTypeUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("subjectType") String subjectType, @RequestParam("subjectTypeId") String subjectTypeId) {
	response.setCharacterEncoding("UTF-8");
	Long checkEventName = subjectTypeService.duplicateCheck(subjectType,subjectTypeId);
	if (checkEventName != 0) {
	    message = "Warning ! Subject Type is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/searchSubjectType", method = RequestMethod.GET)
    public String subjectTypeSearch(
	    @ModelAttribute("subTypeCmd") SubjectType type,
	    HttpServletRequest request, HttpServletResponse response) {
	List<SubjectType> listOfSubTypes = null;
	try {
	    listOfSubTypes = subjectTypeService.searchSubjectTypeDetails(type);
	    request.setAttribute("listOfSubTypes", listOfSubTypes);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "subTypeView";
    }

    @RequestMapping(value = "/subjectTypeEdit", method = RequestMethod.GET)
    public String subjectTypeEdit(
	    @RequestParam("subjectTypeEdit") int subjectTypeId,
	    @ModelAttribute("subTypeCmd") SubjectType type,
	    HttpServletRequest request, HttpServletResponse response) {

	try {
	    SubjectType subjtype = subjectTypeService
		    .editSubjectTypeDetails(subjectTypeId);
	    BeanUtils.copyProperties(type, subjtype);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "subTypeView";
    }

    @RequestMapping(value = "/subjectTypeUpdate", method = RequestMethod.POST)
    public String subjectTypeUpdate(
	    @ModelAttribute("subTypeCmd") SubjectType type,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = subjectTypeService.updateSubjectTypeDetails(type);
	    if (msg.equals("S")) {
		request.setAttribute("subjectTypeUpdate", "Success");
	    } else {
		request.setAttribute("subjectTypeUpdateFail", "Success");
	    }
	    model.addAttribute("subTypeCmd", new SubjectType());
	} catch (Exception e) {
	    request.setAttribute("subjectTypeUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "subTypeView";
    }

    @RequestMapping(value = "/subjectTypelete", method = RequestMethod.GET)
    public String subjectTypelete(
	    @RequestParam("subjectTypelete") int subjectTypeId,
	    @ModelAttribute("subTypeCmd") SubjectType type,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = subjectTypeService
		    .deleteSubjectTypeDetails(subjectTypeId);
	    if (msg.equals("S")) {
		request.setAttribute("subjectTypeDeleted", "S");
	    } else {
		request.setAttribute("subjectTypeDeleteFail", "F");
	    }
	} catch (Exception e) {
	    request.setAttribute("subjectTypeDeleteFail", "F");
	    logger.error(e.getMessage());
	}
	return "subTypeView";
    }

    @ModelAttribute("xmlItems")
    public Map<String, String> populatLabelDetails() {
	String name = "subjectType";

	Map<String, String> map = null;

	try {
	    map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return map;
    }
}
