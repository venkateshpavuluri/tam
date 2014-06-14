/**
 * 
 */
package com.mnt.tam.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestResult;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.TestResultService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author venkateshp
 * 
 */
@Controller
public class TestResultController {
	@Autowired
private	 PopulateService populateService;
private	static Logger logger = Logger.getLogger(TestResultController.class);
	@Autowired
 private TestResultService testResultService;
	@Autowired
 private XmlLabelsService xmlService;
	@RequestMapping(value = "/testResultHome", method = RequestMethod.GET)
	public String studentHome(@ModelAttribute("testResult") TestResult testResult,HttpServletRequest request,HttpServletResponse response) {
		return "testResultView";
	}
	@RequestMapping(value = "/saveTestResultDetails", method = RequestMethod.POST)
	public String testResultSave(@ModelAttribute("testResult")TestResult testResult,
			HttpServletRequest request, HttpServletResponse response) {
		String succMsg=null;
		try {
			String msg = testResultService.saveTestResultDetails(testResult,request,response);
			if(msg.equals("S"))
			{
				succMsg="redirect:testResultHome.mnt?list=S";
			}
			else
			{
				succMsg="redirect:testResultHome.mnt?listWar=F";
			}
		} catch (Exception e) {
			succMsg="redirect:testResultHome.mnt?listWar=F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return succMsg;
	}
	@RequestMapping(value = "/testResultSearch", method = RequestMethod.GET)
	public String testResultSearch(@ModelAttribute("testResult")TestResult testResult,
			HttpServletRequest request, HttpServletResponse response) {
		List<TestResult> listOfTestResults = null;
		try {
			listOfTestResults=testResultService.searchTestResultDetails(testResult);
			request.setAttribute("listOfTestResults", listOfTestResults);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "testResultView";
	}
	@RequestMapping(value = "/testResultEdit", method = RequestMethod.GET)
	public String testResultEdit(@ModelAttribute("testResult")TestResult testResult,Model model,
			HttpServletRequest request, HttpServletResponse response,@RequestParam("testId") int testId,@RequestParam("classId")int classId,@RequestParam("subjectId") int subjectId) {
		TestResult testResults=null;
		Map<String, List<ChildSubjectsJson>> map=null;
		TestResult result=null;
		List<ChildSubjectsJson> childSubjectsJsons=null;
		Iterator<List<ChildSubjectsJson>> iterator=null;
		try {
			testResults=testResultService.editTestResultDetails(testId, classId, subjectId);
			if(testResults!=null)
			{
			map=testResultService.getChildObjects(subjectId);
			Set<String> keys=map.keySet();
			Collection<List<ChildSubjectsJson>> collection=map.values();
		logger.debug("total subjects size iss=="+collection.size()+"==total key size=="+keys.size());
			request.setAttribute("totalSubjects",(keys.size()+collection.size())-1);
			testResults.setEditMsg("Edit");
			logger.info("listOfSubjects in edit iss=="+map);
			request.setAttribute("listStudentsMarks",testResults.getMapOfStudents());
			request.setAttribute("maxMarks",testResults.getMaxMarks());
			}
			result=new TestResult();
			BeanUtils.copyProperties(result,testResults);
			request.setAttribute("listOfSubjects",map);
			request.setAttribute("listOfStudentsForEdit",testResultService.getStudentForEdit(classId));
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("testResult",result);
		return "testResultView";
	}
	@RequestMapping(value = "/testResultUpdate", method = RequestMethod.POST)
	public String testResultUpdate(@ModelAttribute("testResult")TestResult testResult,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String msg=testResultService.updateTestResultDetails(testResult,request,response);
			if(msg.equals("S"))
			{
				request.setAttribute("testResultUpdated","S");
			}
			else
			{
				request.setAttribute("testResultUpdateFail","F");
			}
			model.addAttribute("testResult",new TestResult());
		}
		catch(Exception e)
		{
			request.setAttribute("testResultUpdateFail","F");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "testResultView";
		}
	@RequestMapping(value = "/testResultDelete", method = RequestMethod.GET)
	public String testResultDelete(@ModelAttribute("testResult")TestResult testResult,Model model,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId,@RequestParam("testId") int testId
			,HttpServletRequest request, HttpServletResponse response) {
		try {
			String msg=testResultService.deleteTestResultDetails(classId, subjectId, testId);
			if(msg.equals("S"))
			{
				request.setAttribute("testResultDeleted","S");
			}
			else
			{
				request.setAttribute("testResultDeleteFail","F");
			}
			model.addAttribute("testResult",new TestResult());
		}
		catch(Exception e)
		{
			request.setAttribute("testResultDeleteFail","F");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return "testResultView";
		}
	
	
	@RequestMapping(value = "/forSubjects", method = RequestMethod.POST)
	public @ResponseBody
	String forSubjects(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("classId") int classId) {
	List<Test> tests=null;
	StringWriter out = new StringWriter();
    JSONArray jsonArray = null;
		try{
			jsonArray=testResultService.getSubjects(classId);
			if(jsonArray!=null)
			{
				jsonArray.writeJSONString(out);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return out.toString();
	}
	@RequestMapping(value = "/forTests", method = RequestMethod.POST)
	public @ResponseBody
	String forTests(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("subjectId") int subjectId) {

	List<Test> tests=null;
	StringWriter out = new StringWriter();
    JSONArray jsonArray = null;
		try{
			jsonArray=testResultService.getTests(subjectId);
			if(jsonArray!=null)
			{
				jsonArray.writeJSONString(out);
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		return out.toString();
	}
	@RequestMapping(value = "/forStudents", method = RequestMethod.POST)
	public @ResponseBody String forStudents(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId) {
		response.setCharacterEncoding("UTF-8");
		StringWriter out = new StringWriter();
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray =null;
	    Map<String,List<ChildSubjectsJson>> map=null;
		try
		{
			jsonArray=testResultService.getStudents(classId,subjectId);
				jsonArray.writeJSONString(out);
		}
		catch(Exception  e)
		{
		logger.error(e.getMessage());
		}
				return  out.toString();
	}
	
	@RequestMapping(value = "/duplicateCheck", method = RequestMethod.POST)
	public @ResponseBody String duplicateCheck(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId,@RequestParam("testId") int testId) {
		response.setCharacterEncoding("UTF-8");
		StringWriter out = new StringWriter();
		//JSONObject responseDetailsJson = new JSONObject();
String msg="";
Long count=0l;
		try
		{
			count=testResultService.duplicateCheck(classId, subjectId, testId);

			if(count==0)
			{
				msg="";
			}
			else
			{
				msg="Already Exists";
			}
		}
		catch(Exception  e)
		{
		logger.error(e.getMessage());
		}
				return  msg;
	}
	
	
	
	@ModelAttribute("subjectDetails")
	public Map<Integer, String> populateStudentDetails() {
		Map<Integer, String> map = null;
		try {
			map = populateService.populateSelectBox("select s.subjectId,s.subject from com.mnt.tam.bean.SubjectBean s");
		} catch (Exception e) {
			//logger.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	@ModelAttribute("classDetails")
	public Map<Integer, String> populateClassDetails() {
		Map<Integer, String> map = null;
		try {
			map = populateService.populateSelectBox("select c.classId,c.className from com.mnt.tam.bean.Classes c");
		} catch (Exception e) {
			//logger.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	@ModelAttribute("testDetails")
	public Map<Integer, String> populateTestDetails() {
		Map<Integer, String> map = null;
		try {
			map = populateService.populateSelectBox("select c.testId,c.test from com.mnt.tam.bean.Test c");
		} catch (Exception e) {
			//logger.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	@ModelAttribute("xmlItems")
	public Map<String, String> populatLabelDetails() {
		String name = "testResultId";
		Map<String, String> map = null;
		try {
			map = xmlService.populateXmlLabels(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody List<String> getTags(@RequestParam String tagName) {
 
		return null;
 
	}
	
}
