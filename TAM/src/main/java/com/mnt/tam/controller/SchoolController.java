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
import com.mnt.tam.bean.School;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.SchoolService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class SchoolController {
    private static Logger logger = Logger.getLogger(SchoolController.class);
    @Autowired
    XmlLabelsService xmlService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    SchoolService schoolService;
    String message = null;

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public ModelAndView getSchool(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");
	// return "schoolView";
	return new ModelAndView("schoolView", "school", new School());
    }

    @RequestMapping(value = "/checkSchoolDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSchoolDuplicate(HttpServletRequest request,
	    HttpServletResponse response, @RequestParam("school") String school, @RequestParam("branchCode") String branchCode ) {
	response.setCharacterEncoding("UTF-8");
	Long checkEventName = schoolService.duplicateCheck(school, null,branchCode);
	if (checkEventName != 0) {
	    message = "Warning ! School is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/checkSchoolUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSchoolUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("school") String school,
	    @RequestParam("schoolId") String schoolId,@RequestParam("branchCode") String branchCode) {
	response.setCharacterEncoding("UTF-8");
	Long checkSchoolName = schoolService.duplicateCheck(school, schoolId,branchCode);
	if (checkSchoolName != 0) {
	    message = "Warning ! School is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/schoolSaveDetails", method = RequestMethod.POST)
    public String SchoolSave(@ModelAttribute("school") School school,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = schoolService.saveSchoolDetails(school);
	    if (msg.equals("S")) {
		succMsg = "redirect:school.mnt?list=S";
	    } else {
		succMsg = "redirect:school.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:school.mnt?listWar=F";
	    logger.error(e.getMessage());
	}
	return succMsg;

    }

    @RequestMapping(value = "/schoolSearch", method = RequestMethod.GET)
    public String SchoolSearch(@ModelAttribute("school") School school,
	    HttpServletRequest request, HttpServletResponse response) {
	List<School> listOfSchools = null;

	try {
	    listOfSchools = schoolService.searchSchoolDetails(school);
	    logger.error("the list size" + listOfSchools.size());
	    request.setAttribute("listOfSchools", listOfSchools);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "schoolView";
    }

    @RequestMapping(value = "/schoolEdit", method = RequestMethod.GET)
    public String SchoolEdit(@RequestParam("schoolId") int schoolId,
	    @ModelAttribute("school") School school,
	    HttpServletRequest request, HttpServletResponse response) {

	try {
	    logger.error("schoolid" + schoolId);
	    School schools = schoolService.editSchoolDetails(schoolId);
	    logger.error("schoolName" + schools.getSchoolName());
	    BeanUtils.copyProperties(school, schools);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "schoolView";
    }

    @RequestMapping(value = "/schoolUpdate", method = RequestMethod.POST)
    public String SchoolUpdate(@ModelAttribute("school") School school,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = schoolService.updateSchoolDetails(school);
	    if (msg.equals("S")) {
		request.setAttribute("schoolUpdate", "Success");
	    } else {
		request.setAttribute("schoolUpdateFail", "Success");
	    }
	    model.addAttribute("school", new School());
	} catch (Exception e) {
	    request.setAttribute("schoolUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "schoolView";
    }

    @RequestMapping(value = "/schoolDelete", method = RequestMethod.GET)
    public String SchoolDelete(@RequestParam("schoolId") int schoolId,
	    @ModelAttribute("school") School School,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = schoolService.deleteSchoolDetails(schoolId);
	    if (msg.equals("S")) {
		request.setAttribute("schoolDeleted", "S");
	    } else {
		request.setAttribute("schoolDeleteFail", "F");
	    }
	} catch (Exception e) {
	    request.setAttribute("schoolDeleteFail", "F");
	    logger.error(e.getMessage());
	}
	return "schoolView";
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
	String name = "schoolId";

	Map<String, String> map = null;

	try {
	    map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return map;
    }

}
