/**
 * 
 */
package com.mnt.tam.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnt.tam.bean.ChildSubjectsJson;
import com.mnt.tam.bean.Classes;
import com.mnt.tam.bean.Student;
import com.mnt.tam.bean.StudentTestResult;
import com.mnt.tam.bean.StudentTestResultJson;
import com.mnt.tam.bean.SubjectBean;
import com.mnt.tam.bean.TermTest;
import com.mnt.tam.bean.Test;
import com.mnt.tam.bean.TestResult;
import com.mnt.tam.bean.TestResultForJson;
import com.mnt.tam.dao.StudentTestResultDao;


/**
 * @author devi
 *
 */
@Service("strService")
public class StudentTestResultServiceImpl implements StudentTestResultService{
	private static Logger logger = Logger.getLogger(StudentTestResultServiceImpl.class);
    private String msg = null;
    @Autowired
    private StudentTestResultDao strDao;
	public String saveStudentTestDetails(StudentTestResult student,HttpServletRequest request) {
		List<StudentTestResult> listOfTestResults=null;
		List<ChildSubjectsJson> listOfSubjects=null;
		Object[] objects=null;
	try {
		String studentId=student.getStudentId();
		String marks=student.getMarks();
		String testIds=student.getTestIds();
		if(marks!=null){
		if(studentId!=null);
		String[] studentIds=studentId.split(",");
		if(testIds!=null);
		String[] testId=testIds.split(",");
		
		String[] studentMarks=marks.split(",");
		listOfTestResults=new ArrayList<StudentTestResult>();
		logger.info("studentIds length iss====="+studentIds.length+"==marks length iss=="+studentMarks.length);
		for(int i=0;i<studentIds.length;i++)
		{
			StudentTestResult testResults=new StudentTestResult();
			testResults.setTestId(testId[i]);
			testResults.setStudentId(studentIds[i]);
			testResults.setMarksGained(studentMarks[i]);
			testResults.setClassId(student.getClassId());
			testResults.setTermTestId(student.getTermTestId());
			testResults.setSubjectId(student.getSubjectId());
			listOfTestResults.add(testResults);
		}
		}
		
		else
		{
		listOfTestResults=new ArrayList<StudentTestResult>();
		Map<String, List<ChildSubjectsJson>> map=getChildObjects(Integer.parseInt(student.getSubjectId()));
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
List<Object[]>	listOfStudents = strDao.getStudents(Integer.parseInt(student.getClassId()));
Iterator<Object[]>	iterators=listOfStudents.iterator();
while(iterators.hasNext())
{
	 objects=(Object[])iterators.next();
	 Student students=(Student)objects[1];
		if(childSubjectsJson.getChildSpans()==0)
		{
			String subjname=request.getParameter(childSubjectsJson.getChildSubject()+students.getStudentId());
			logger.info("marks aree==="+subjname);
			StudentTestResult testResults=new StudentTestResult();
			System.out.println("testId iss==="+childSubjectsJson.getChildSubject()+students.getStudentId()+"select");
			testResults.setTestId(request.getParameter(childSubjectsJson.getChildSubject()+students.getStudentId()+"select"));
			testResults.setStudentId(String.valueOf(students.getStudentId()));
			testResults.setMarksGained(request.getParameter(String.valueOf(childSubjectsJson.getChildSubject()+students.getStudentId())));
			testResults.setClassId(student.getClassId());
			testResults.setTermTestId(student.getTermTestId());
			testResults.setParentSubjectId(student.getSubjectId());
			testResults.setSubjectId(String.valueOf(childSubjectsJson.getChildSubjectId()));
			listOfTestResults.add(testResults);
		}
}
		 }
		 logger.info("msg iss=="+msg);
	}
	    msg =strDao.saveStudentTestDetails(listOfTestResults);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }

    public List<StudentTestResult> searchStudentTestDetails(StudentTestResult student) {
	List<Object[]> listOfObjects = null;
	List<StudentTestResult> listOfStudents = null;
	try {

	    String dbField = student.getXmlLabel();
	    String operation = student.getOperations();
	    String basicSearchId = student.getBasicSearchId();
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
	    if (student.getBasicSearchId().equals("")) {
		listOfObjects = strDao.searchStudentTestDetails();
		 
	    } else {
		listOfObjects = strDao.basicSearchForStudentTest(dbField, operation, basicSearchId);
	    }
	    if(listOfObjects!=null)
	    {
	    listOfStudents = new ArrayList<StudentTestResult>();
	    Iterator<Object[]> iterator = listOfObjects.iterator();
	    while (iterator.hasNext()) {
		Object[] objects = (Object[]) iterator.next();
		StudentTestResult listStudent = new StudentTestResult();
		listStudent.setTestId(String.valueOf((Integer)objects[0]));
		listStudent.setTestName((String)objects[1]);
		listStudent.setClassId(String.valueOf((Integer)objects[2]));
		listStudent.setClassName((String)objects[3]);
		listStudent.setTermTestId(String.valueOf((Integer)objects[4]));
		listStudent.setTermTestName((String)objects[5]);
		listStudent.setSubjectId(String.valueOf((BigInteger)objects[7]));
		listStudent.setSubjectName((String)objects[6]);
		listOfStudents.add(listStudent);
	    }
	    }
	   
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return listOfStudents;
    }

    public StudentTestResult editStudentTestDetails(int testId,int termTestId,int subjectId,int classId) {
	List<Object[]> listOfStudents = null;
	Object[] objects=null;
	StudentTestResult testResult=null;
	List<Student> students=null;
	try {
		listOfStudents = strDao.editStudentTestDetails(testId,termTestId,subjectId,classId);
		logger.info("studenttestresult size iss=="+listOfStudents.size());
		//hql = "select s.testId,s.termTestId,s.marksGained,s.classId,s.studentId,s.subjectId from com.mnt.tam.bean.StudentTestResult s where s.testId="+testId+" and s.termTestId="+termTestId+"and classId="+classId+" and s.parentSubjectId="+subjectId;
	    if (listOfStudents != null) {
		Iterator<Object[]> iterator = listOfStudents.iterator();
		students=new ArrayList<Student>();
		while (iterator.hasNext()) {
			objects = (Object[]) iterator.next();
			 testResult=new StudentTestResult();
			 testResult.setTestId((String)objects[0]);
			// testResult.setStudentId((String)objects[1]);
			 testResult.setTermTestIdEdit((String)objects[1]);
			 testResult.setMarksGained((String)objects[2]);
			 testResult.setClassIdEdit((String)objects[3]);
			 //testResult.setSubjectId((String)objects[6]);
			 SubjectBean subjectBean=(SubjectBean)objects[5];
			 testResult.setSubjectIdEdit((String)objects[6]);
			 Test test=(Test)objects[7];
			 testResult.setEditMsg("Edit");
			 Student student=(Student)objects[4];
			 testResult.setStudentId(String.valueOf(student.getStudentId()));
			 testResult.setMaxMarks(String.valueOf(subjectBean.getMaxMarks()));
				student.setSubjectName(subjectBean.getSubject());
				student.setMarks((String)objects[2]);
				Student studentRsults=new Student();
				student.setSubjectId(String.valueOf(subjectBean.getSubjectId()));
				logger.debug("testId is=="+test.getTestId()+"==testName=="+test.getTest());
				student.setTestId(String.valueOf(test.getTestId()));
				student.setTestName((String)test.getTest());
				student.setfNameEdit(student.getfName()+" "+student.getMidName()+" "+student.getlName());
				BeanUtils.copyProperties(studentRsults, student);
				students.add(studentRsults);
				testResult.setMapOfStudents(students);
		}
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return testResult;
    }

    public String updateStudentTestDetails(StudentTestResult student,HttpServletRequest request) {
    	String classId=null;
		String subjectId=null;
		String testId=null;
		String studentId=null;
		String termTestId=null;
		String marks=null;
	List<StudentTestResult> listOfTestResults=null;
	List<ChildSubjectsJson> listOfSubjects=null;
	Object[]	 objects=null;
	String[] studentIds=null;
		try
		{
			 classId=student.getClassIdEdit();
			 subjectId=student.getSubjectIdEdit();
			 termTestId=student.getTermTestIdEdit();
			 logger.info("class==="+classId+"==subj=="+subjectId+"==test=="+testId+"==termTestId=="+termTestId);
			 studentId=student.getStudentIdEdit();
			 logger.info("studentId is=="+studentId);
			 marks=student.getMarksEdit();
			 testId=student.getTestsEdit();
			 if(studentId!=null){
			 studentIds=studentId.split(",");
			 }
			 if(marks!=null){
				 msg=strDao.deleteStudentTestResultDetails(Integer.parseInt(classId),Integer.parseInt(subjectId),Integer.parseInt(termTestId),"child");
				if(testId!=null);
				String[] testIds=testId.split(",");
				 
				 if(msg.equals("S"))
				{
				 listOfTestResults=new ArrayList<StudentTestResult>();
				 String[] studentMarks=marks.split(",");
			 for(int i=0;i<studentIds.length;i++)
			 {
			logger.debug("test ids are===="+testIds[i]);
			StudentTestResult testResults=new StudentTestResult();
				 testResults.setClassId(classId);
				 testResults.setTestId(testIds[i]);
				 testResults.setStudentId(studentIds[i]);
				 testResults.setMarksGained(studentMarks[i]);
				 testResults.setSubjectId(student.getSubjectIdEdit());
				 testResults.setTermTestId(termTestId);
				 listOfTestResults.add(testResults);
			 }
				}
			 }
			 else
			 {
				 msg=strDao.deleteStudentTestResultDetails(Integer.parseInt(classId),Integer.parseInt(subjectId),Integer.parseInt(termTestId),"parent");
					if(msg.equals("S"))
					{
				 listOfTestResults=new ArrayList<StudentTestResult>();
				 System.out.println("subject id iss=s=="+student.getSubjectId());
					Map<String, List<ChildSubjectsJson>> map=getChildObjects(Integer.parseInt(student.getSubjectIdEdit()));
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
			List<Object[]>	listOfStudents = strDao.getStudents(Integer.parseInt(student.getClassIdEdit()));
		 Iterator<Object[]>	iterators=listOfStudents.iterator();
			while(iterators.hasNext())
			{
			 objects=(Object[])iterators.next();
				 Student students=(Student)objects[1];
					if(childSubjectsJson.getChildSpans()==0)
					{
						StudentTestResult testResults=new StudentTestResult();
						String marksUp=request.getParameter(childSubjectsJson.getChildSubject()+students.getStudentId()); 
						logger.debug("student id iss==="+students.getStudentId());
						testResults.setStudentId(String.valueOf(students.getStudentId()));
						testResults.setMarksGained(marksUp);
						testResults.setTermTestId(termTestId);
						testResults.setClassId(classId);
						testResults.setSubjectId(String.valueOf(childSubjectsJson.getChildSubjectId()));
						testResults.setParentSubjectId(subjectId);
						logger.debug("testId iss=="+request.getParameter(childSubjectsJson.getChildSubject()+students.getStudentId()+"test"));
						testResults.setTestId(request.getParameter(childSubjectsJson.getChildSubject()+students.getStudentId()+"test"));
						listOfTestResults.add(testResults);
			 }
			}
					 }
					 }
			 }
			
			 msg= strDao.saveStudentTestDetails(listOfTestResults);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			msg="F";
		}
		return msg;
	}

    public String deleteStudentTestDetails(int termTestId,int subjectId,int classId) {
	try {
	    msg = strDao.deleteStudentTestDetails(termTestId, subjectId, classId);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	    e.printStackTrace();
	}
	return msg;
    }
	public JSONArray getSubjects(int termTestId) {
		// TODO Auto-generated method stub
		List<SubjectBean> subjectBeans=null;
		JSONArray jsonArray=null;
		Iterator<SubjectBean> iterator=null;
		List<StudentTestResultJson> listTest=null;
	
		StudentTestResultJson json=null;
		try {
			 subjectBeans = strDao.getSubjects(termTestId);
			if(subjectBeans!=null)
			{
				 jsonArray=new JSONArray();
				iterator=subjectBeans.iterator();
				while(iterator.hasNext())
				{
					SubjectBean subjectBean=(SubjectBean)iterator.next();
					 json=new StudentTestResultJson();
					json.setSubject(subjectBean.getSubject());
					json.setSubjectId(String.valueOf(subjectBean.getSubjectId()));
					jsonArray.add(json);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
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
			listOfStudents = strDao.getStudents(classId);
			
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
			list=strDao.getChildObjects(subjectId);
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
		 listOfSubChilds=strDao.getChildObjects((Integer)objects[0]);
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
			listOfStudents = strDao.getStudents(classId);
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
	
	
	
	

	public String getEachTestMarks(int subjectId, int classId, int studentId,
			int testId) {
		// TODO Auto-generated method stub
		String testMarks=null;
		try
		{
			testMarks=strDao.getEachTestMarks(subjectId, classId, studentId, testId);
		}
		catch(Exception e )
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return testMarks;
	}

	public JSONArray getAllTests(int subjectId, int termTestId) {
		// TODO Auto-generated method stub
		JSONArray jsonArray=null;
		List<Object[]> list=null;
		Object[] objects=null;
		try
		{
			list=strDao.getAllTests(subjectId, termTestId);
			if(list!=null)
			{
				jsonArray=new JSONArray();
		Iterator<Object[]> iterator=list.iterator();
		while(iterator.hasNext())
		{
			objects=(Object[])iterator.next();
			TestResultForJson forJson=new TestResultForJson();
			forJson.setTestId((Integer)objects[0]);
			forJson.setTest((String)objects[1]);
			jsonArray.add(forJson);
		}
			}
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return jsonArray;
	}

	public String getMaxMarks(int subjectId) {
		// TODO Auto-generated method stub
		String maxMarks=null;
		try {
			maxMarks = strDao.getMaxMarks(subjectId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return maxMarks;
	}

	public List<StudentTestResult> getAllTestsForEdit(int subjectId,
			int termTestId) {
		// TODO Auto-generated method stub
		List<StudentTestResult> listOfTests=null;
		try
		{
			List<Object[]> list=strDao.getAllTests(subjectId, termTestId);
			if(list!=null)
			{
			Iterator<Object[]> iterator=list.iterator();
			listOfTests=new ArrayList<StudentTestResult>();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				StudentTestResult result=new StudentTestResult();
				result.setTestId(String.valueOf((Integer)objects[0]));
				result.setTestName((String)objects[1]);
				listOfTests.add(result);
			}
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return listOfTests;
	}

	public String getTestMarks(int termtestId, int testId, int classId,
			int subjectId,int studentId) {
		// TODO Auto-generated method stub
		
		return strDao.getTestMarks(termtestId, testId, classId, subjectId,studentId);
	}

	public Long duplicateCheck(int termtestId, int testId, int classId,
			int subjectId, int studentId) {
		// TODO Auto-generated method stub
		return strDao.duplicateCheck(termtestId, testId, classId, subjectId, studentId);
	}
	
}

