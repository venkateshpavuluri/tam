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

import com.mnt.tam.bean.Section;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.SectionService;
import com.mnt.tam.service.XmlLabelsService;

@Controller
public class SectionController {
    private static Logger logger = Logger.getLogger(SectionController.class);
    @Autowired
    XmlLabelsService xmlService;
    @Autowired
    private PopulateService populateService;
    @Autowired
    SectionService SectionService;
    String message = null;

    @RequestMapping(value = "/Section", method = RequestMethod.GET)
    public ModelAndView getSection(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setCharacterEncoding("UTF-8");

	return new ModelAndView("sectionView", "Section", new Section());
    }

    @RequestMapping(value = "/checkSectionDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSectionDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("sectionName") String sectionName) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = SectionService.duplicateCheck(sectionName, null);
	if (checkName != 0) {
	    message = "Warning ! Section name is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/checkSectionUpdateDuplicate", method = RequestMethod.POST)
    public @ResponseBody
    String checkSectionUpdateDuplicate(HttpServletRequest request,
	    HttpServletResponse response,
	    @RequestParam("sectionName") String sectionName,
	    @RequestParam("sectionId") String sectionId) {
	response.setCharacterEncoding("UTF-8");
	Long checkName = SectionService.duplicateCheck(sectionName, sectionId);
	if (checkName != 0) {
	    message = "Warning !Section is Already exists. Please try some other name";
	} else {
	    message = "";
	}
	return message;
    }

    @RequestMapping(value = "/SectionSaveDetails", method = RequestMethod.POST)
    public String SectionSave(@ModelAttribute("Section") Section Section,
	    HttpServletRequest request, HttpServletResponse response) {
	String succMsg = null;
	try {
	    String msg = SectionService.saveSectionDetails(Section);
	    if (msg.equals("S")) {
		succMsg = "redirect:Section.mnt?list=S";
	    } else {
		succMsg = "redirect:Section.mnt?listWar=F";
	    }
	} catch (Exception e) {
	    succMsg = "redirect:Section.mnt?listWar=F";
	    logger.error(e.getMessage());

	}
	return succMsg;

    }

    @RequestMapping(value = "/SectionSearch", method = RequestMethod.GET)
    public String SectionSearch(@ModelAttribute("Section") Section Section,
	    HttpServletRequest request, HttpServletResponse response) {
	List<Section> listOfSections = null;

	try {
	    listOfSections = SectionService.searchSectionDetails(Section);
	    logger.error("the list size" + listOfSections.size());
	    request.setAttribute("listOfSections", listOfSections);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "sectionView";
    }

    @RequestMapping(value = "/SectionEdit", method = RequestMethod.GET)
    public String SectionEdit(@RequestParam("SectionId") int SectionId,
	    @ModelAttribute("Section") Section Section,
	    HttpServletRequest request, HttpServletResponse response) {

	try {

	    Section Sections = SectionService.editSectionDetails(SectionId);

	    BeanUtils.copyProperties(Section, Sections);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	return "sectionView";
    }

    @RequestMapping(value = "/SectionUpdate", method = RequestMethod.POST)
    public String SectionUpdate(@ModelAttribute("Section") Section Section,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = SectionService.updateSectionDetails(Section);
	    if (msg.equals("S")) {
		request.setAttribute("SectionUpdate", "Success");
	    } else {
		request.setAttribute("SectionUpdateFail", "Success");
	    }
	    model.addAttribute("Section", new Section());
	} catch (Exception e) {
	    request.setAttribute("SectionUpdateFail", "Success");
	    logger.error(e.getMessage());
	}
	return "sectionView";
    }

    @RequestMapping(value = "/SectionDelete", method = RequestMethod.GET)
    public String SectionDelete(@RequestParam("SectionId") int SectionId,
	    @ModelAttribute("Section") Section Section,
	    HttpServletRequest request, HttpServletResponse response,
	    Model model) {
	try {
	    String msg = SectionService.deleteSectionDetails(SectionId);
	    if (msg.equals("S")) {
		request.setAttribute("SectionDeleted", "S");
	    } else {
		request.setAttribute("SectionDeleteFail", "F");
	    }
	} catch (Exception e) {
	    request.setAttribute("SectionDeleteFail", "F");
	    logger.error(e.getMessage());
	}
	return "sectionView";
    }

    @ModelAttribute("xmlItems")
    public Map<String, String> populatLabelDetails() {
	String name = "SectionId";

	Map<String, String> map = null;

	try {
	    map = xmlService.populateXmlLabels(name);

	} catch (Exception e) {
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
		    .populateSelectBox("select c.classId,c.className from com.mnt.tam.bean.Classes c");

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	return map;
    }
}
