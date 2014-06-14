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

import com.mnt.tam.bean.TeacherType;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.TeacherTypeService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class TeacherTypeController {
    private static Logger logger = Logger
	    .getLogger(TeacherTypeController.class);
    @Autowired
    XmlLabelsService xmlService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    TeacherTypeService TeacherTypeService;
    String message= null;

    @RequestMapping(value = "/TeacherType", method = RequestMethod.GET)
    public ModelAndView getTeacherType(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	// return "teacherTypeView";
	return new ModelAndView("teacherTypeView", "TeacherType",
		new TeacherType());
    }

    @RequestMapping(value = "/checkTeacherTypeDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTeacherTypeDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("teacherType") String teacherType) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = TeacherTypeService.duplicateCheck(teacherType, null);
	if (checkName != 0) {
	    message = "Warning ! Teacher type name is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/checkTeacherTypeUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkTeacherTypeUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("teacherType") String teacherType,
	    @RequestParam("teacherTypeId") String teacherTypeId) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = TeacherTypeService.duplicateCheck(teacherType,
		teacherTypeId);
	if (checkName != 0) {
	    message = "Warning !Class Name is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/TeacherTypeSaveDetails", method = RequestMethod.POST)
    public String TeacherTypeSave(
	    @ModelAttribute("TeacherType") TeacherType TeacherType,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = TeacherTypeService.saveTeacherTypeDetails(TeacherType);
	    if (msg.equals("S")) {
		succMsg = "redirect:TeacherType.mnt?list=S";
	    } else {
		succMsg = "redirect:TeacherType.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:TeacherType.mnt?listWar=F";
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return succMsg;

    }

    @RequestMapping(value = "/TeacherTypeSearch", method = RequestMethod.GET)
    public String TeacherTypeSearch(
	    @ModelAttribute("TeacherType") TeacherType TeacherType,
	    HttpServletRequest request, HttpServletResponse response) {
	List<TeacherType> listOfTeacherTypes = null;

	try {
	    listOfTeacherTypes = TeacherTypeService
		    .searchTeacherTypeDetails(TeacherType);
	    logger.error("the list size" + listOfTeacherTypes.size());
	    request.setAttribute("listOfTeacherTypes", listOfTeacherTypes);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "teacherTypeView";
    }

    @RequestMapping(value = "/TeacherTypeEdit", method = RequestMethod.GET)
    public String TeacherTypeEdit(
	    @RequestParam("TeacherTypeId") int TeacherTypeId,
	    @ModelAttribute("TeacherType") TeacherType TeacherType,
	    HttpServletRequest request, HttpServletResponse response) {

	try {
	    logger.error("TeacherTypeid" + TeacherTypeId);
	    TeacherType TeacherTypes = TeacherTypeService
		    .editTeacherTypeDetails(TeacherTypeId);
	    logger.error("TeacherTypeName" + TeacherTypes.getTeacherType());
	    BeanUtils.copyProperties(TeacherType, TeacherTypes);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "teacherTypeView";
    }

    @RequestMapping(value = "/TeacherTypeUpdate", method = RequestMethod.POST)
    public String TeacherTypeUpdate(
	    @ModelAttribute("TeacherType") TeacherType TeacherType,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = TeacherTypeService
		    .updateTeacherTypeDetails(TeacherType);
	    if (msg.equals("S")) {
		request.setAttribute("TeacherTypeUpdate", "Success");
	    } else {
		request.setAttribute("TeacherTypeUpdateFail", "Success");
	    }
	    model.addAttribute("TeacherType", new TeacherType());
	} catch (Exception e) {
	    request.setAttribute("TeacherTypeUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "teacherTypeView";
    }

    @RequestMapping(value = "/TeacherTypeDelete", method = RequestMethod.GET)
    public String TeacherTypeDelete(
	    @RequestParam("TeacherTypeId") int TeacherTypeId,
	    @ModelAttribute("TeacherType") TeacherType TeacherType,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = TeacherTypeService
		    .deleteTeacherTypeDetails(TeacherTypeId);
	    if (msg.equals("S")) {
		request.setAttribute("TeacherTypeDeleted", "S");
	    } else {
		request.setAttribute("TeacherTypeDeleteFail", "F");
	    }
	} catch (Exception e) {
	    request.setAttribute("TeacherTypeDeleteFail", "F");
	    logger.error(e.getMessage());
	}
	return "teacherTypeView";
    }

    @ModelAttribute("xmlItems")
    public Map<String, String> populatLabelDetails() {
	String name = "TeacherTypeId";

	Map<String, String> map = null;

	try {
	    map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return map;
    }
}
