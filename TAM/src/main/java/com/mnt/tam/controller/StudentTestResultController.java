/**
 * 
 */
package com.mnt.tam.controller;

import java.io.StringWriter;
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
import com.mnt.tam.bean.StudentTestResult;
import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestResult;
import com.mnt.tam.service.PopulateService;
import com.mnt.tam.service.StudentTestResultService;
import com.mnt.tam.service.XmlLabelsService;

/**
 * @author devi
 *
 */
@Controller
public class StudentTestResultController {
	 private static Logger logger = Logger.getLogger(StudentTestResultController.class);
	    @Autowired
	    private StudentTestResultService strService;;
	    @Autowired
	    private PopulateService populateService;
	    @Autowired
	    private XmlLabelsService xmlService;

	    @RequestMapping(value = "/studentTestResult", method = RequestMethod.GET)
	    public String studentTestHome(
		    @ModelAttribute("studentTest") StudentTestResult student) {
		return "studentTestResult";
		
	    }
	    
	    @RequestMapping(value = "/saveStudentTestDetails", method = RequestMethod.POST)
	    public String saveStudentTestDetails(
		    @ModelAttribute("studentTest") StudentTestResult student,
		    HttpServletRequest request, HttpServletResponse response) {
		String succMsg = null;
		try {
		    String msg = strService.saveStudentTestDetails(student,request);
		    if (msg.equals("S")) {
			succMsg = "redirect:studentTestResult.mnt?list=S";
		    } else {
			succMsg = "redirect:studentTestResult.mnt?listWar=F";
		    }
		} catch (Exception e) {
		    succMsg = "redirect:studentTestResult.mnt?listWar=F";
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
		return succMsg;

	    }
	    
	    @RequestMapping(value = "/studentTestSearch", method = RequestMethod.GET)
	    public String SearchStudentTest(
		    @ModelAttribute("studentTest") StudentTestResult stBean,
		    HttpServletRequest request, HttpServletResponse response) {
		List<StudentTestResult> listOfStudents = null;
		try {
			listOfStudents = strService.searchStudentTestDetails(stBean);
		    request.setAttribute("listOfStudents", listOfStudents);
		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return "studentTestResult";
	    }
	    @RequestMapping(value = "/studentTestEdit", method = RequestMethod.GET)
	    public String studentTestEdit(@RequestParam("testId") int testId,@RequestParam("termTestId") int termTestId,@RequestParam("subjectId") int subjectId,
	    		@RequestParam("classId") int classId, @ModelAttribute("studentTest") StudentTestResult stBean,
		    HttpServletRequest request, HttpServletResponse response,Model model) {
	    	Map<String, List<ChildSubjectsJson>> map=null;
			
			List<ChildSubjectsJson> childSubjectsJsons=null;
			Iterator<List<ChildSubjectsJson>> iterator=null;
		try {
		StudentTestResult studentBeans = strService.editStudentTestDetails(testId,termTestId,subjectId,classId);
		if(studentBeans!=null)
		{
		map=strService.getChildObjects(subjectId);
		Set<String> keys=map.keySet();
		Collection<List<ChildSubjectsJson>> collection=map.values();
	logger.debug("total subjects size iss=="+collection.size()+"==total key size=="+keys.size());
		request.setAttribute("totalSubjects",(keys.size()+collection.size())-1);
		logger.info("listOfSubjects in edit iss=="+map);
		request.setAttribute("listStudentsMarks",studentBeans.getMapOfStudents());
		request.setAttribute("maxMarks",studentBeans.getMaxMarks());
		model.addAttribute("studentTest",studentBeans);
		logger.debug("list size iss===="+strService.getAllTestsForEdit(subjectId,termTestId).size());
		 request.setAttribute("listOfTests",strService.getAllTestsForEdit(subjectId,termTestId)
		    		);
		}
		
		request.setAttribute("listOfSubjects",map);
		request.setAttribute("listOfStudentsForEdit",strService.getStudentForEdit(classId));
		}
		 catch (Exception e) {
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
		return "studentTestResult";
	    }

	    @RequestMapping(value = "/studentTestUpdate", method = RequestMethod.POST)
	    public String studentTestUpdate(
		    @ModelAttribute("studentTest") StudentTestResult sBean,
		    HttpServletRequest request, HttpServletResponse response,
		    Model model) {
		try {
		    String msg = strService.updateStudentTestDetails(sBean,request);
		    logger.debug("classs Id saree=="+sBean.getClassIdEdit());
		    if (msg.equals("S")) {
			request.setAttribute("studentTestUpdate", "S");
		    } else {
			request.setAttribute("studentTestUpdateFail", "F");
		    }
		
		    model.addAttribute("studentTest", new StudentTestResult());
		} catch (Exception e) {
		    request.setAttribute("studentUpdateFail", "F");
		    logger.error(e.getMessage());
		}
		return "studentTestResult";
	    }
	    
	    @RequestMapping(value = "/studentTestDelete.mnt", method = RequestMethod.GET)
		public String studentTestDelete(@RequestParam("termTestId") int termTestId,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId,
				@ModelAttribute("studentTest") StudentTestResult stBean,
				HttpServletRequest request, HttpServletResponse response,
				Model model) {
			try {
				String msg = strService.deleteStudentTestDetails(termTestId, subjectId, classId);
				if (msg.equals("S")) {
					request.setAttribute("studentTestDeleted", "S");
				} else {
					request.setAttribute("studentTestDeleteFail", "F");
				}
			} catch (Exception e) {
				request.setAttribute("studentTestDeleteFail", "F");
				logger.error(e.getMessage());
			}
		return "studentTestResult";
		}
	    
	    
	    
	    @RequestMapping(value = "/getAllTests", method = RequestMethod.POST)
	  		public @ResponseBody
	  		String getAllTests(HttpServletRequest request,
	  				HttpServletResponse response,@RequestParam("subjectId") int subjectId,@RequestParam("termTestId") int termTestId) {
	  	logger.debug("termtest id iss==="+termTestId);
	  		StringWriter out = new StringWriter();
	  	    JSONArray jsonArray = null;
	  			try{
	  				jsonArray=strService.getAllTests(subjectId, termTestId);
	  				if(jsonArray!=null)
	  				{
	  					jsonArray.writeJSONString(out);
	  				}
	  			} catch (Exception e) {

	  				logger.error(e.getMessage());
	  				e.printStackTrace();
	  			}
	  			return out.toString();
	  		}
	    
	    
	    @RequestMapping(value = "/getMaxMarks", method = RequestMethod.POST)
  		public @ResponseBody
  		String getMaxMarks(HttpServletRequest request,
  				HttpServletResponse response,@RequestParam("subjectId") int subjectId) {
	    	String maxMarks=null;
  			try{
  				maxMarks=strService.getMaxMarks(subjectId);
  				logger.debug("maxMarks is=="+maxMarks);
  			} catch (Exception e) {

  				logger.error(e.getMessage());
  				e.printStackTrace();
  			}
  			return maxMarks;
  		}
	    
	    
	    
	    @RequestMapping(value = "/getTestsMarks", method = RequestMethod.POST)
	  		public @ResponseBody
	  		String getTestsMarks(HttpServletRequest request,
	  				HttpServletResponse response,@RequestParam("termTestId") int termTestId,@RequestParam("testId") int testId,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId, @RequestParam("studentId") int studentId ) {
		    	String marks=null;
	  			try{
	  				marks=strService.getTestMarks(termTestId, testId, classId, subjectId,studentId);
	  				logger.debug("particularTest marks is=="+marks);
	  			} catch (Exception e) {

	  				logger.error(e.getMessage());
	  				e.printStackTrace();
	  			}
	  			return marks;
	  		}
		    
	    
	    @RequestMapping(value = "/getSubjects", method = RequestMethod.POST)
		public @ResponseBody
		String getSubjects(HttpServletRequest request,
				HttpServletResponse response,@RequestParam("termTestId") int termTestId) {
	logger.debug("termtest id iss==="+termTestId);
		StringWriter out = new StringWriter();
	    JSONArray jsonArray = null;
			try{
				jsonArray=strService.getSubjects(termTestId);
				if(jsonArray!=null)
				{
					jsonArray.writeJSONString(out);
				}
			} catch (Exception e) {

				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return out.toString();
		}
	    @RequestMapping(value = "/getStudentsMarks", method = RequestMethod.POST)
		public @ResponseBody String forStudents(HttpServletRequest request,
				HttpServletResponse response,@RequestParam("classId") int classId,@RequestParam("subjectId") int subjectId,@RequestParam("termTestId") int termTestId ) {
			response.setCharacterEncoding("UTF-8");
			StringWriter out = new StringWriter();
			//JSONObject responseDetailsJson = new JSONObject();
		    JSONArray jsonArray =null;
		    Map<String,List<ChildSubjectsJson >> map=null;
			try
			{
				jsonArray=strService.getStudents(classId,subjectId);
					jsonArray.writeJSONString(out);
			}
			catch(Exception  e)
			{
			logger.error(e.getMessage());
			}
					return  out.toString();
		}
	    
	    @RequestMapping(value ="/getMarks", method=RequestMethod.POST)
		public @ResponseBody
		String getEachTestMarks(HttpServletRequest request,
				HttpServletResponse response) {
	String marks=null;
			try{
				//,@RequestParam("subjectId") int subjectId,@RequestParam("classId ") int classId,@RequestParam("studentId") int studentId,@RequestParam("testId") int testId
				
				marks= strService.getEachTestMarks(Integer.parseInt(request.getParameter("subjectId")),Integer.parseInt(request.getParameter("classId")),Integer.parseInt(request.getParameter("studentId")) ,Integer.parseInt(request.getParameter("testId")));
				logger.debug("marks aree==="+marks);
			} catch (Exception e) {

				logger.error(e.getMessage());
				e.printStackTrace();
			}
			return marks;
		}
	    @RequestMapping(value ="/studentTestduplicateCheck", method=RequestMethod.POST)
	  		public @ResponseBody
	  		String duplicateCheck(HttpServletRequest request,
	  				HttpServletResponse response,@RequestParam("subjectId") int subjectId,@RequestParam("classId") int classId,@RequestParam("studentId") int studentId,@RequestParam("testId") int testId,@RequestParam("termTestId") int termTestId) {
	  	Long count=null;
	  	String duplicatCount="";
	  			try{
	  				//,@RequestParam("subjectId") int subjectId,@RequestParam("classId ") int classId,@RequestParam("studentId") int studentId,@RequestParam("testId") int testId
	  				
	  				//marks= strService.duplicateCheck(Integer.parseInt(request.getParameter("subjectId")),Integer.parseInt(request.getParameter("classId")),Integer.parseInt(request.getParameter("studentId")) ,Integer.parseInt(request.getParameter("testId")),Integer.parseInt(request.getParameter("termTestId")));
	  				count=strService.duplicateCheck(termTestId, testId, classId, subjectId, studentId);
	logger.debug("duplicate count idss=="+count);
	  				if(count==0)
	  {
		  duplicatCount=""; 
	  }
	  else
	  {
		  duplicatCount="Already Existed";
	  }
	  			} catch (Exception e) {

	  				logger.error(e.getMessage());
	  				e.printStackTrace();
	  			}
	  			return duplicatCount;
	  		}
	    
	    @ModelAttribute("xmlItems")
	    public Map<String, String> populatLabelDetails() {
		String name = "studentTestResult";
		Map<String, String> map = null;
		try {
		    map = xmlService.populateXmlLabels(name);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return map;
	    }
	    @ModelAttribute("testDetails")
	    public Map<Integer, String> populateTestDetails() {
		Map<Integer, String> map = null;
		try {
		    
		    map = populateService
			    .populateSelectBox("select s.testId, s.test from com.mnt.tam.bean.Test s");

		} catch (Exception e) {
		    logger.error(e.getMessage());
		}

		return map;
	    }
	    
	    @ModelAttribute("studentDetails")
	    public Map<Integer, String> populateStudentDetails() {
		Map<Integer, String> map = null;
		try {
		    
		    map = populateService
			    .populateSelectBox("select s.studentId, s.fName from com.mnt.tam.bean.Student s");

		} catch (Exception e) {
		    logger.error(e.getMessage());
		}

		return map;
	    }
	    
	    @ModelAttribute("termtestDetails")
	    public Map<Integer, String> populateTermTestDetails() {
		Map<Integer, String> map = null;
		try {
		    
		    map = populateService
			    .populateSelectBox("select s.termtest_Id, s.termtest from com.mnt.tam.bean.TermTest s");

		} catch (Exception e) {
		    logger.error(e.getMessage());
		}

		return map;
	    }
	    
	    @ModelAttribute("classDetails")
	    public Map<Integer, String> populateClassDetails() {
		Map<Integer, String> map = null;
		try {
		    
		    map = populateService.populateSelectBox("select s.classId, s.className from com.mnt.tam.bean.Classes s");

		} catch (Exception e) {
		    logger.error(e.getMessage());
		}
		return map;
	    }
	    @ModelAttribute("subjectDetails")
		public Map<Integer, String> populateSubjectDetails() {
			Map<Integer, String> map = null;
			try {
				map = populateService.populateSelectBox("select s.subjectId,s.subject from com.mnt.tam.bean.SubjectBean s where s.parentSubjectId=0");
			} catch (Exception e) {
				//logger.error(e.getMessage());
				e.printStackTrace();
			}
			return map;
		}
	    
}
