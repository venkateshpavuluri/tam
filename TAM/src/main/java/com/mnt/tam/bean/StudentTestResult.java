/**
 * 
 */
package com.mnt.tam.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author venkateshp
 *
 */
@Entity
@NamedNativeQueries({
 @NamedNativeQuery(name="getStudentTestResultDetails", query="CALL getStudentTestResults()", resultSetMapping="mapping"), @NamedNativeQuery(name="getBasicSearchForStudentTestResult", query="CALL getBasicSearchForStudentTestResult(:dbField,:operation,:id)", resultSetMapping="basicSearch")})
@SqlResultSetMappings({ @SqlResultSetMapping(name="mapping",columns={@ColumnResult(name="TestId"),@ColumnResult(name="Test"),@ColumnResult(name="Class_Id"),@ColumnResult(name="Class_Name"),@ColumnResult(name="TermTestId"),@ColumnResult(name="TermTest"),@ColumnResult(name="Subject"),@ColumnResult(name="Subject_Id")}),
	@SqlResultSetMapping(name="basicSearch",columns={@ColumnResult(name="TestId"),@ColumnResult(name="Test"),@ColumnResult(name="Class_Id"),@ColumnResult(name="Class_Name"),@ColumnResult(name="TermTestId"),@ColumnResult(name="TermTest"),@ColumnResult(name="Subject"),@ColumnResult(name="Subject_Id")})
})




@Table(name="studenttestresult")
public class StudentTestResult {
	@Id
	@Column(name="StudentTestResult_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int studentResultId;
	@Column(name="RefTest_Id")	
	private String testId;
	@Column(name="Student_Id")
	private String studentId;
	@Column(name="TermTest_Id")
	private String termTestId;
	@Column(name="MarksGained")
	private String marksGained; 
	@ManyToOne
	@JoinColumn(name="TermTest_Id",referencedColumnName="TermTest_Id",insertable=false,updatable=false)
	private TermTest termTest;
	@ManyToOne
	@JoinColumn(name="RefTest_Id",referencedColumnName="Test_Id",insertable=false,updatable=false)
	private Test test;
	@ManyToOne
	@JoinColumn(name="Student_Id",referencedColumnName="Student_Id",insertable=false,updatable=false)
	private Student student;
	@ManyToOne
	@JoinColumn(name="Subject_Id",referencedColumnName="Subject_Id",insertable=false,updatable=false)
	private SubjectBean subjectBean;
	@Column(name="ParentSubject_Id")
	private String parentSubjectId; 
	@Transient
	private String editMsg;
	@Transient
	private String studentName;
	@Transient
	private String testName;
	@Transient
	private String termTestName;
	@Column(name="Class_Id")
	private String classId;
	@Column(name="Subject_Id")
	private String subjectId;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String marks;
	@Transient
	private String testIds;
	@Transient
	private String className;
	@Transient
	private String subjectName;
	@ManyToOne
	@JoinColumn(name="ParentSubject_Id",referencedColumnName="Subject_Id",insertable=false,updatable=false)
	private SubjectBean parentSubjectBean;
	
	@ManyToOne
	@JoinColumn(name="Class_Id",referencedColumnName="Class_Id",insertable=false,updatable=false)
	private Classes classes;
	@Transient
	private List<Student> mapOfStudents;
	@Transient
	private String maxMarks;
	@Transient
	private String studentIdEdit;
	@Transient
	private String marksEdit;
	@Transient
	private String classIdEdit;
	@Transient
	private String subjectIdEdit;
	@Transient
	private String termTestIdEdit;
	@Transient
	private String testsEdit;
	/**
	 * @return the testsEdit
	 */
	public String getTestsEdit() {
		return testsEdit;
	}
	/**
	 * @param testsEdit the testsEdit to set
	 */
	public void setTestsEdit(String testsEdit) {
		this.testsEdit = testsEdit;
	}
	/**
	 * @return the classIdEdit
	 */
	public String getClassIdEdit() {
		return classIdEdit;
	}
	/**
	 * @param classIdEdit the classIdEdit to set
	 */
	public void setClassIdEdit(String classIdEdit) {
		this.classIdEdit = classIdEdit;
	}
	/**
	 * @return the subjectIdEdit
	 */
	public String getSubjectIdEdit() {
		return subjectIdEdit;
	}
	/**
	 * @param subjectIdEdit the subjectIdEdit to set
	 */
	public void setSubjectIdEdit(String subjectIdEdit) {
		this.subjectIdEdit = subjectIdEdit;
	}
	/**
	 * @return the termTestIdEdit
	 */
	public String getTermTestIdEdit() {
		return termTestIdEdit;
	}
	/**
	 * @param termTestIdEdit the termTestIdEdit to set
	 */
	public void setTermTestIdEdit(String termTestIdEdit) {
		this.termTestIdEdit = termTestIdEdit;
	}
	/**
	 * @return the marksEdit
	 */
	public String getMarksEdit() {
		return marksEdit;
	}
	/**
	 * @param marksEdit the marksEdit to set
	 */
	public void setMarksEdit(String marksEdit) {
		this.marksEdit = marksEdit;
	}
	/**
	 * @return the studentIdEdit
	 */
	public String getStudentIdEdit() {
		return studentIdEdit;
	}
	/**
	 * @param studentIdEdit the studentIdEdit to set
	 */
	public void setStudentIdEdit(String studentIdEdit) {
		this.studentIdEdit = studentIdEdit;
	}
	/**
	 * @return the maxMarks
	 */
	public String getMaxMarks() {
		return maxMarks;
	}
	/**
	 * @param maxMarks the maxMarks to set
	 */
	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}
	/**
	 * @return the mapOfStudents
	 */
	public List<Student> getMapOfStudents() {
		return mapOfStudents;
	}
	/**
	 * @param mapOfStudents the mapOfStudents to set
	 */
	public void setMapOfStudents(List<Student> mapOfStudents) {
		this.mapOfStudents = mapOfStudents;
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
	 * @return the parentSubjectBean
	 */
	public SubjectBean getParentSubjectBean() {
		return parentSubjectBean;
	}
	/**
	 * @param parentSubjectBean the parentSubjectBean to set
	 */
	public void setParentSubjectBean(SubjectBean parentSubjectBean) {
		this.parentSubjectBean = parentSubjectBean;
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
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the parentSubjectId
	 */
	public String getParentSubjectId() {
		return parentSubjectId;
	}
	/**
	 * @param parentSubjectId the parentSubjectId to set
	 */
	public void setParentSubjectId(String parentSubjectId) {
		this.parentSubjectId = parentSubjectId;
	}
	/**
	 * @return the testIds
	 */
	public String getTestIds() {
		return testIds;
	}
	/**
	 * @param testIds the testIds to set
	 */
	public void setTestIds(String testIds) {
		this.testIds = testIds;
	}
	/**
	 * @return the marks
	 */
	public String getMarks() {
		return marks;
	}
	/**
	 * @param marks the marks to set
	 */
	public void setMarks(String marks) {
		this.marks = marks;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
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
	
	
	
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	
	
	
	
	/**
	 * @return the studentResultId
	 */
	public int getStudentResultId() {
		return studentResultId;
	}
	/**
	 * @param studentResultId the studentResultId to set
	 */
	public void setStudentResultId(int studentResultId) {
		this.studentResultId = studentResultId;
	}
	/**
	 * @return the testId
	 */
	public String getTestId() {
		return testId;
	}
	/**
	 * @param testId the testId to set
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	 * @return the marksGained
	 */
	public String getMarksGained() {
		return marksGained;
	}
	/**
	 * @param marksGained the marksGained to set
	 */
	public void setMarksGained(String marksGained) {
		this.marksGained = marksGained;
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
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	
}
