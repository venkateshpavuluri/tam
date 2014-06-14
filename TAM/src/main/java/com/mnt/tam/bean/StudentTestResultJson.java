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
public class StudentTestResultJson  implements Serializable, JSONStreamAware {
	private String subjectId;
	private String subject;
	private int testId;
	private String test;
	private List<StudentTestResultJson> termTests;
	private Map<String,List<StudentTestResultJson>> map;
	
	/**
	 * @return the map
	 */
	public Map<String, List<StudentTestResultJson>> getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<StudentTestResultJson>> map) {
		this.map = map;
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
	/**
	 * @return the termTests
	 */
	public List<StudentTestResultJson> getTermTests() {
		return termTests;
	}
	/**
	 * @param termTests the termTests to set
	 */
	public void setTermTests(List<StudentTestResultJson> termTests) {
		this.termTests = termTests;
	}
	/**
	 * @return the subjectId
	 */
	public String getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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
	@SuppressWarnings("unchecked")
	public void writeJSONString(Writer out) throws IOException {
		@SuppressWarnings("rawtypes")
		LinkedHashMap obj = new LinkedHashMap();
		obj.put("subjectId",subjectId);
		obj.put("subject",subject);
		obj.put("testId",testId);
		obj.put("test",test);
		obj.put("termTests",termTests);
		obj.put("map",map);
		JSONValue.writeJSONString(obj, out);
	}

}
