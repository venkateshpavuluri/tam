/**
 * 
 */
package com.mnt.tam.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.BeanUtil;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Classes;
import com.mnt.tam.bean.School;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestResult;
import com.mnt.tam.bean.TestResultForJson;
import com.mnt.tam.dao.TestResultDao;

/**
 * @author venkateshp
 * 
 */
@Service("testResultService")
public class TestResultServiceImpl implements TestResultService {
	private static Logger logger = Logger
			.getLogger(TestResultServiceImpl.class);
	private String msg = null;
	private List<Object[]> listOfObjects = null;
	private String hql = null;
	@Autowired
	private TestResultDao testResultDao;
	public String saveTestResultDetails(TestResult testResult,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<TestResult> listOfTestResults=null;
		 List<ChildSubjectsJson> listOfSubjects=null;
		 Object[] objects=null;
		try {
			String studentId=testResult.getStudentId();
			String marks=testResult.getMarks();
			if(marks!=null){
			if(studentId!=null);
			String[] studentIds=studentId.split(",");
			
			String[] studentMarks=marks.split(",");
			listOfTestResults=new ArrayList<TestResult>();
			logger.info("studentIds length iss====="+studentIds.length+"==marks length iss=="+studentMarks.length);
			for(int i=0;i<studentIds.length;i++)
			{
				TestResult testResults=new TestResult();
				testResults.setTestId(testResult.getTestId());
				testResults.setStudentId(studentIds[i]);
				testResults.setMarks(studentMarks[i]);
				testResults.setClassId(testResult.getClassId());
				testResults.setSubjectId(testResult.getSubjectId());
			
				listOfTestResults.add(testResults);
			}
			}
			else
			{
			listOfTestResults=new ArrayList<TestResult>();
			Map<String, List<ChildSubjectsJson>> map=getChildObjects(Integer.parseInt(testResult.getSubjectId()));
			logger.info("classId iss=="+map.size());
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
			    Map.Entry<String, List<ChildSubjectsJson>> entry = (Map.Entry<String, List<ChildSubjectsJson>>) it.next();
			String subName=(String)entry.getKey();
		 listOfSubjects=(List<ChildSubjectsJson>)entry.getValue();
			 logger.info("subject name iss=="+subName+"==childSubjects=="+listOfSubjects.size());
			}
			 Iterator<ChildSubjectsJson> iterator=listOfSubjects.iterator();
			 while(iterator.hasNext())
			 {
				 ChildSubjectsJson childSubjectsJson=(ChildSubjectsJson)iterator.next();
	List<Object[]>	listOfStudents = testResultDao.getStudents(Integer.parseInt(testResult.getClassId()));
 Iterator<Object[]>	iterators=listOfStudents.iterator();
	while(iterators.hasNext())
	{
		 objects=(Object[])iterators.next();
		 Student student=(Student)objects[1];
			if(childSubjectsJson.getChildSpans()==0)
			{
				String subjname=request.getParameter(childSubjectsJson.getChildSubject()+student.getStudentId());
				logger.info("marks aree==="+subjname);
				TestResult testResults=new TestResult();
				testResults.setTestId(testResult.getTestId());
				testResults.setStudentId(String.valueOf(student.getStudentId()));
				testResults.setMarks(subjname);
				testResults.setClassId(testResult.getClassId());
				testResults.setSubjectId(String.valueOf(childSubjectsJson.getChildSubjectId()));
				testResults.setParentSubjectId(testResult.getSubjectId());
				listOfTestResults.add(testResults);
			}
	}
			 }
			 logger.info("msg iss=="+msg);
		}
			msg = testResultDao.saveTestResultDetails(listOfTestResults);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public List<TestResult> searchTestResultDetails(TestResult testResult) {
		// TODO Auto-generated method stub
		List<Object[]> listOfObjects=null;
		List<TestResult> listOfTestResults=null;
		try
		{
			String dbField = testResult.getXmlLabel();
			String operation = testResult.getOperations();
			String basicSearchId = testResult.getBasicSearchId();
			if (operation.equals("_%")) {
				operation = " like ";
				basicSearchId = basicSearchId + "%";
			} else if (operation.equals("%_")) {
				operation = " like ";
				basicSearchId = "%" + basicSearchId;
			} else if (operation.equals("%_%")) {
				operation = " like ";
				basicSearchId = "%" + basicSearchId + "%";
			}
		//sql="select s.fName,s.lName,s.midName,s.dob,s.city,s.state,s.country,s.fatehrName,s.mName,s.fOccupation,s.mOccupation,s.mobileNo,s.phoneNo,s.school,s.admissionNo from com.mnt.tam.bean.Student s ";
			if(basicSearchId.equals(""))
			{
			 listOfObjects=testResultDao.searchTestResultDetails();
			}
			else
			{
				listOfObjects=testResultDao.basicSearchForTestResult(dbField, operation, basicSearchId);
			}
	
			if(listOfObjects!=null)
			{
				listOfTestResults=new ArrayList<TestResult>();
			Iterator<Object[]> iterator=listOfObjects.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				TestResult testResults=new TestResult();
				testResults.setTestName((String)objects[1]);
				testResults.setTestId(String.valueOf((Integer)objects[0]));
				testResults.setSubjectId(String.valueOf((BigInteger)objects[5]));
				testResults.setSubjectName((String)objects[4]);
				testResults.setClassId(String.valueOf((Integer)objects[2]));
				testResults.setClassName((String)objects[3]);
				listOfTestResults.add(testResults);
			}
			}
		}
			catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		return listOfTestResults;
	}
	public TestResult editTestResultDetails(int testId,int classId,int subjectId) {
		// TODO Auto-generated method stub
		Map<List<Student>,String> map=null;
		TestResult testResult=null;
		List<Student> students=null;
		String marks=null;
		try
		{
		//	hql="select t.testResultId,t.classId,t.marks,t.tests,t.studentId from com.mnt.tam.bean.TestResult
			listOfObjects=testResultDao.editTestResultDetails(testId,classId,subjectId);
			if(listOfObjects!=null)
			{
				students=new ArrayList<Student>();
			map=new LinkedHashMap<List<Student>, String>();
			Iterator<Object[]> iterator=listOfObjects.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				testResult=new TestResult();
				testResult.setTestResultId((Integer)objects[0]);
			 marks=(String)objects[1];
			Test test=(Test)objects[2];
			testResult.setTestIdEdit(String.valueOf(test.getTestId()));
			testResult.setSubjectIdEdit(test.getSubjectId());
			SubjectBean bean=(SubjectBean)objects[6];
			testResult.setMaxMarks(String.valueOf(bean.getMaxMarks()));
			testResult.setClassIdEdit(bean.getClassId());
		Student student=(Student)objects[3];
		student.setSubjectName(bean.getSubject());
		student.setMarks(marks);
		Student studentRsults=new Student();
		student.setSubjectId((String)objects[4]);
		BeanUtils.copyProperties(studentRsults, student);
		student.setfNameEdit(student.getfName()+" "+student.getMidName()+" "+student.getlName());
	
		students.add(studentRsults);
			testResult.setSubjectIdEdit((String)objects[4]);
			testResult.setClassIdEdit((String)objects[5]);
			testResult.setMapOfStudents(students);
			
				}
		
			}
		
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return testResult;
	}
	public String updateTestResultDetails(TestResult testResult,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		String classId=null;
		String subjectId=null;
		String testId=null;
		String studentId=null;
		String marks=null;
	List<TestResult> listOfTestResults=null;
	List<ChildSubjectsJson> listOfSubjects=null;
	Object[]	 objects=null;
	String[] studentIds=null;
		try
		{
			 classId=testResult.getClassIdEdit();
			 subjectId=testResult.getSubjectIdEdit();
			 testId=testResult.getTestIdEdit();
		
			 studentId=testResult.getStudentIdEdit();
			 logger.info("classis=="+classId+"==subjectId=="+subjectId+"==testId=="+testId);
			 marks=testResult.getMarksEdit();
			 if(studentId!=null){
			 studentIds=studentId.split(",");
			 }
			 if(marks!=null){
				 msg=testResultDao.deleteTestResultDetails(Integer.parseInt(testId), Integer.parseInt(classId), Integer.parseInt(subjectId),"child");
				if(msg.equals("S"))
				{
				 listOfTestResults=new ArrayList<TestResult>();
				 String[] studentMarks=marks.split(",");
			 for(int i=0;i<studentIds.length;i++)
			 {
			logger.debug("with info==");
				 TestResult testResults=new TestResult();
				 testResults.setClassId(classId);
				 testResults.setTestId(testId);
				 testResults.setStudentId(studentIds[i]);
				 testResults.setMarks(studentMarks[i]);
				 testResults.setSubjectId(subjectId);
				 listOfTestResults.add(testResults);
			 }
				}
			 }
			 else
			 {
				 msg=testResultDao.deleteTestResultDetails(Integer.parseInt(testId), Integer.parseInt(classId), Integer.parseInt(subjectId),"parent");
					if(msg.equals("S"))
					{
				 listOfTestResults=new ArrayList<TestResult>();
					Map<String, List<ChildSubjectsJson>> map=getChildObjects(Integer.parseInt(subjectId));
					logger.info("classId iss=="+map.size());
					Iterator it = map.entrySet().iterator();
					while (it.hasNext()) {
					    Map.Entry<String, List<ChildSubjectsJson>> entry = (Map.Entry<String, List<ChildSubjectsJson>>) it.next();
					String subName=(String)entry.getKey();
				 listOfSubjects=(List<ChildSubjectsJson>)entry.getValue();
					 logger.info("subject name iss=="+subName+"==childSubjects=="+listOfSubjects.size());
					}
					 Iterator<ChildSubjectsJson> iterator=listOfSubjects.iterator();
					 while(iterator.hasNext())
					 {
						 ChildSubjectsJson childSubjectsJson=(ChildSubjectsJson)iterator.next();
			List<Object[]>	listOfStudents = testResultDao.getStudents(Integer.parseInt(classId));
		 Iterator<Object[]>	iterators=listOfStudents.iterator();
			while(iterators.hasNext())
			{
			 objects=(Object[])iterators.next();
				 Student student=(Student)objects[1];
					if(childSubjectsJson.getChildSpans()==0)
					{
						TestResult testResults=new TestResult();
						String marksUp=request.getParameter(childSubjectsJson.getChildSubject()+student.getStudentId()); 
						logger.info("marks in update iss==="+marksUp);
						testResults.setTestId(testId);
						testResults.setStudentId(String.valueOf(student.getStudentId()));
						testResults.setMarks(marksUp);
						testResults.setClassId(classId);
						testResults.setSubjectId(String.valueOf(childSubjectsJson.getChildSubjectId()));
						testResults.setParentSubjectId(subjectId);
						listOfTestResults.add(testResults);
			 }
				
			}
					 }
					 }
			 }
			
			 msg= testResultDao.saveTestResultDetails(listOfTestResults);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			msg="F";
		}
		return msg;
	}
	public String deleteTestResultDetails(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public JSONArray getSubjects(int subjectId) {
		// TODO Auto-generated method stub
JSONArray jsonArray=new JSONArray();
		try {
			listOfObjects = testResultDao.listOfSubjects(subjectId);
			if (listOfObjects != null) {
				Iterator<Object[]> iterator = listOfObjects.iterator();
				while (iterator.hasNext()) {
					Object[] objects = (Object[]) iterator.next();
					Test test = new Test();
					test.setSubjectId(String.valueOf((Integer)objects[0]));
					test.setSubject((String)objects[1]);
					jsonArray.add(test);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return jsonArray;
	}
	public JSONArray getStudents(int classId,int subjectId) {
		// TODO Auto-generated method stub
		List<Object[]> listOfStudents = null;
		Iterator<Object[]> iterator=null;
		Object objects[]=null;
		TestResultForJson resultForJson=null;
		JSONArray jsonArray=null;
				Map<String,List<ChildSubjectsJson>> map=null;
				List<TestResultForJson> listOfStu=null;
		try {
			listOfStudents = testResultDao.getStudents(classId);
			
			logger.info("size iss=="+listOfStudents.size());
			listOfStu=new ArrayList<TestResultForJson>();
			if(listOfStudents!=null)
			{
				jsonArray=new JSONArray();
				map=getChildObjects(subjectId);
				Set<String> keys=map.keySet();
				Collection<List<ChildSubjectsJson>> childSubjectsJsons=map.values();
				int count=keys.size()+childSubjectsJsons.size();
				TestResultForJson forJson=new TestResultForJson();
				if(map!=null)
				{
				forJson.setMap(map);
				forJson.setTotalSubjsCount(String.valueOf(count));
				}
				logger.info("map size iss=="+map.size());
			iterator=listOfStudents.iterator();
			while(iterator.hasNext())
			{
				 objects=(Object[])iterator.next();
				 resultForJson=new  TestResultForJson();
				 Student student=(Student)objects[1];
				 resultForJson.setStudentName(student.getfName()+" "+student.getMidName()+" "+student.getlName());
				 resultForJson.setStudentId(student.getStudentId());
				 listOfStu.add(resultForJson);	 
			}
			forJson.setLisOfStudents(listOfStu);
			 jsonArray.add(forJson);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return jsonArray;
	}
	public JSONArray getTests(int subjectId) {
		// TODO Auto-generated method stub
		Iterator<Object[]> iterator=null;
		TestResultForJson resultForJson=null;
		Object[]  objects=null;
		JSONArray jsonArray=null;
		try
		{
			listOfObjects=testResultDao.getTests(subjectId);
			Set<String> set=new HashSet<String>();
			iterator=listOfObjects.iterator();
			jsonArray=new JSONArray();
			while(iterator.hasNext())
			{
				 objects=(Object[])iterator.next();
				 resultForJson=new TestResultForJson();
				 resultForJson.setTestId((Integer)objects[0]);
				 resultForJson.setTest((String)objects[1]);
				 resultForJson.setMaxMarks((Integer)objects[2]);
				 jsonArray.add(resultForJson);
			}		
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return jsonArray;
	}
	public Map<String, List<ChildSubjectsJson>> getChildObjects(int subjectId) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		Object[] objects=null;
		Map<String, List<ChildSubjectsJson>> map=null;
		List<Object[]> listOfSubChilds=null;
		Iterator<Object[]> iteratorForSub=null;
		Object[] objectsForSub=null;
		List<ChildSubjectsJson> subjectsJsonsList=null;
		int childCount=0;
		int totalSubjects=0;
		try
		{
			list=testResultDao.getChildObjects(subjectId);
			if(list!=null)
			{
	 subjectsJsonsList=new ArrayList<ChildSubjectsJson>();
				map=new LinkedHashMap<String, List<ChildSubjectsJson>>();
			Iterator<Object[]> iterator=list.iterator();
			while(iterator.hasNext())
			{
				objects=(Object[])iterator.next();
				ChildSubjectsJson childSubjectsJsong=new ChildSubjectsJson();
				 childSubjectsJsong.setChildSubjectId((Integer)objects[0]);
				 childSubjectsJsong.setChildSubject((String)objects[1]);
				 childSubjectsJsong.setMarks(String.valueOf((Integer)objects[2]));
				 logger.info("maxMarks aree==="+String.valueOf((Integer)objects[2])+"==parent=="+(String)objects[1]);
				 childSubjectsJsong.setStage("1");
		 listOfSubChilds=testResultDao.getChildObjects((Integer)objects[0]);
		 childSubjectsJsong.setChildSpans(listOfSubChilds.size());
		 if(listOfSubChilds.size()!=0)
		 {
			 totalSubjects+=1;
		 }
		 childSubjectsJsong.setTotalSubjects(totalSubjects);
		 childCount=listOfSubChilds.size();
		 subjectsJsonsList.add(childSubjectsJsong);
		 map.put((String)objects[1],subjectsJsonsList);
		 if(listOfSubChilds!=null)
		 {
		 iteratorForSub=listOfSubChilds.iterator();
			while(iteratorForSub.hasNext())
			{
			 objectsForSub=(Object[])iteratorForSub.next();
			 ChildSubjectsJson childSubjectsJson=new ChildSubjectsJson();
			 childSubjectsJson.setChildSubjectId((Integer)objectsForSub[0]);
			 childSubjectsJson.setChildSubject((String)objectsForSub[1]);
			 childSubjectsJson.setStage("2");
			 childSubjectsJson.setChildSubjs(String.valueOf(childCount));
			 totalSubjects+=1;
			 childSubjectsJson.setTotalSubjects(totalSubjects);
			 logger.debug("total subjects=="+childSubjectsJson.getTotalSubjects());
			 childSubjectsJson.setMarks(String.valueOf((Integer)objectsForSub[2]));
			 logger.info("maxMarks aree==="+String.valueOf((Integer)objectsForSub[2])+"==subchild=="+(String)objectsForSub[1]);
			 subjectsJsonsList.add(childSubjectsJson);
			}
			map.put((String)objects[1],subjectsJsonsList);
		 }
			}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return map;
	}
	public List<Student> getStudentForEdit(int classId) {
		// TODO Auto-generated method stub
		List<Student> students=null;
		List<Object[]> listOfStudents=null;
		Object[]	 objects=null;
		try
		{
			listOfStudents = testResultDao.getStudents(classId);
			if(listOfStudents!=null)
			{
				students=new ArrayList<Student>();
		Iterator<Object[]>	iterator=listOfStudents.iterator();
			while(iterator.hasNext())
			{
		 objects=(Object[])iterator.next();
				 Student student=(Student)objects[1];
				 student.setfNameEdit(student.getfName()+" "+student.getMidName()+" "+student.getlName());
				 students.add(student);
			}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return students;
	}
	public String deleteTestResultDetails(int classId, int subjectId, int testId) {
		// TODO Auto-generated method stub
		try
		{
			msg=testResultDao.deleteAllTestResultDetails(classId, subjectId, testId);
		}
		catch(Exception e)
		{
			msg="F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	public Long duplicateCheck(int classId, int subjectId, int testId) {
		// TODO Auto-generated method stub
		Long count=0l;
		try
		{
			count=testResultDao.duplicateCheck(classId, subjectId, testId);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return count;
	}
}
