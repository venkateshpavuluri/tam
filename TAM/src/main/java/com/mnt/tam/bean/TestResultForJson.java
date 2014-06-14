/**
 * 
 */
package com.mnt.tam.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

/**
 * @author venkateshp
 *
 */
public class TestResultForJson implements Serializable, JSONStreamAware{
	private int studentId;
	private String studentName;
	private int subjectId;
	private int parentSubjectId;
	private String subject;
	private int maxMarks;
	private int testId;
	private String test;
	private Map<String,List<ChildSubjectsJson>> map;
	private List<TestResultForJson> lisOfStudents;
		private String totalSubjsCount;
	
	
	
	
	/**
		 * @return the totalSubjsCount
		 */
		public String getTotalSubjsCount() {
			return totalSubjsCount;
		}
		/**
		 * @param totalSubjsCount the totalSubjsCount to set
		 */
		public void setTotalSubjsCount(String totalSubjsCount) {
			this.totalSubjsCount = totalSubjsCount;
		}
	/**
	 * @return the lisOfStudents
	 */
	public List<TestResultForJson> getLisOfStudents() {
		return lisOfStudents;
	}
	/**
	 * @param lisOfStudents the lisOfStudents to set
	 */
	public void setLisOfStudents(List<TestResultForJson> lisOfStudents) {
		this.lisOfStudents = lisOfStudents;
	}
	/**
	 * @return the map
	 */
	public Map<String, List<ChildSubjectsJson>> getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<ChildSubjectsJson>> map) {
		this.map = map;
	}
	/**
	 * 
	 * 
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @return the maxMarks
	 */
	public int getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the parentSubjectId
	 */
	public int getParentSubjectId() {
		return parentSubjectId;
	}
	/**
	 * @param parentSubjectId the parentSubjectId to set
	 */
	public void setParentSubjectId(int parentSubjectId) {
		this.parentSubjectId = parentSubjectId;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the testId
	 */
	public int getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(int testId) {
		this.testId = testId;
	}

	/**
	 * @return the test
	 */
	public String getTest() {
		return test;
	}
	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		this.test = test;
	}
	@SuppressWarnings("unchecked")
	public void writeJSONString(Writer out) throws IOException {
		@SuppressWarnings("rawtypes")
		LinkedHashMap obj = new LinkedHashMap();
		obj.put("studentId", studentId);
		obj.put("studentName", studentName);
		obj.put("subjectId", subjectId);
		obj.put("parentSubjectId", parentSubjectId);
		obj.put("subject", subject);
		obj.put("maxMarks", maxMarks);
		obj.put("testId", testId);
		obj.put("test",test);
		obj.put("map",map);
		obj.put("listOfStudents",lisOfStudents);
		obj.put("totalSubjsCount",totalSubjsCount);
		JSONValue.writeJSONString(obj, out);
	}

}
