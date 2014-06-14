/**
 * 
 */
package com.mnt.tam.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

/**
 * @author venkateshp
 *
 */
@Entity
@Table(name="test")
public class Test implements Serializable, JSONStreamAware  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Test_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int testId;
	@Column(name="Test")
	private String test;
	@Column(name="TestType_Id")
	private String testTypeId;
	@Column(name="Subject_Id")
	private String subjectId;
	@Column(name="TermTest_Id")
	private String termTestId;
	
	@ManyToOne
	@JoinColumn(name="TermTest_Id",referencedColumnName="TermTest_Id",insertable=false,updatable=false)
	private TermTest termTest;
	@ManyToOne
	@JoinColumn(name="TestType_Id",referencedColumnName="TestType_Id",insertable=false,updatable=false)
	private TestType testType;
	@ManyToOne
	@JoinColumn(name="Subject_Id",referencedColumnName="Subject_Id",insertable=false,updatable=false)
	private SubjectBean subjectBean;
	@Transient
	private String editMsg;
	@Transient
	private String subjectName;
	@Transient
	private String testTypeName;
	@Transient
	private String termTestName;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String student;
	@Transient
	private String testName;

	@Transient
	private String testsId;
	@Transient
	private String subject;
	

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
	 * @return the testsId
	 */
	public String getTestsId() {
		return testsId;
	}
	/**
	 * @param testsId the testsId to set
	 */
	public void setTestsId(String testsId) {
		this.testsId = testsId;
	}
	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the student
	 */
	public String getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(String student) {
		this.student = student;
	}
	/**
	 * @return the xmlLabel
	 */
	public String getXmlLabel() {
		return xmlLabel;
	}
	/**
	 * @param xmlLabel the xmlLabel to set
	 */
	public void setXmlLabel(String xmlLabel) {
		this.xmlLabel = xmlLabel;
	}
	/**
	 * @return the basicSearchId
	 */
	public String getBasicSearchId() {
		return basicSearchId;
	}
	/**
	 * @param basicSearchId the basicSearchId to set
	 */
	public void setBasicSearchId(String basicSearchId) {
		this.basicSearchId = basicSearchId;
	}
	/**
	 * @return the operations
	 */
	public String getOperations() {
		return operations;
	}
	/**
	 * @param operations the operations to set
	 */
	public void setOperations(String operations) {
		this.operations = operations;
	}
	/**
	 * @return the termTestId
	 */
	public String getTermTestId() {
		return termTestId;
	}
	/**
	 * @param termTestId the termTestId to set
	 */
	public void setTermTestId(String termTestId) {
		this.termTestId = termTestId;
	}
	/**
	 * @return the termTest
	 */
	public TermTest getTermTest() {
		return termTest;
	}
	/**
	 * @param termTest the termTest to set
	 */
	public void setTermTest(TermTest termTest) {
		this.termTest = termTest;
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
	 * @return the testTypeId
	 */
	public String getTestTypeId() {
		return testTypeId;
	}
	/**
	 * @param testTypeId the testTypeId to set
	 */
	public void setTestTypeId(String testTypeId) {
		this.testTypeId = testTypeId;
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
	 * @return the testType
	 */
	public TestType getTestType() {
		return testType;
	}
	/**
	 * @param testType the testType to set
	 */
	public void setTestType(TestType testType) {
		this.testType = testType;
	}
	/**
	 * @return the subjectBean
	 */
	public SubjectBean getSubjectBean() {
		return subjectBean;
	}
	/**
	 * @param subjectBean the subjectBean to set
	 */
	public void setSubjectBean(SubjectBean subjectBean) {
		this.subjectBean = subjectBean;
	}
	/**
	 * @return the editMsg
	 */
	public String getEditMsg() {
		return editMsg;
	}
	/**
	 * @param editMsg the editMsg to set
	 */
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return the testTypeName
	 */
	public String getTestTypeName() {
		return testTypeName;
	}
	/**
	 * @param testTypeName the testTypeName to set
	 */
	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}
	/**
	 * @return the termTestName
	 */
	public String getTermTestName() {
		return termTestName;
	}
	/**
	 * @param termTestName the termTestName to set
	 */
	public void setTermTestName(String termTestName) {
		this.termTestName = termTestName;
	}
	@SuppressWarnings("unchecked")
	public void writeJSONString(Writer out) throws IOException {
		@SuppressWarnings("rawtypes")
		LinkedHashMap obj = new LinkedHashMap();
		obj.put("subjectId",subjectId);
		obj.put("subject",subject);
	
		JSONValue.writeJSONString(obj, out);

	}
	
	
	
	
}
