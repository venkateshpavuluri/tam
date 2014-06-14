/**
 * 
 */
package com.mnt.tam.bean;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
@NamedNativeQuery(name="getTestResultDetails", query="CALL getTestResultDetails()", resultSetMapping="joinMapping"),@NamedNativeQuery(name="basicSearchForTestResult", query="CALL basicSearchForTestResult(:dbField,:operation,:id)",resultSetMapping="testResultBasicSearch")})
@SqlResultSetMappings({@SqlResultSetMapping(name="joinMapping",columns={@ColumnResult(name="Test_Id"),@ColumnResult(name="Test"),@ColumnResult(name="Class_Id"),@ColumnResult(name="Class_Name"),@ColumnResult(name="Subject"),@ColumnResult(name="Subject_Id")}),@SqlResultSetMapping(name="testResultBasicSearch",columns={@ColumnResult(name="Test_Id"),@ColumnResult(name="Test"),@ColumnResult(name="Class_Id"),@ColumnResult(name="Class_Name"),@ColumnResult(name="Subject"),@ColumnResult(name="Subject_Id")})})
@Table(name = "testresult")
public class TestResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "TestResult_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int testResultId;
	@Column(name = "Subject_Id")
	private String subjectId;
	@Column(name="ParentSubject_Id")
	private String parentSubjectId;
	@Column(name = "Marks")
	private String marks;
	@Column(name = "Test_Id")
	private String testId;
	@Column(name = "Student_Id")
	private String studentId;
	@ManyToOne
	@JoinColumn(name ="Test_Id", referencedColumnName ="Test_Id", insertable = false, updatable = false)
	private Test tests;
	@ManyToOne
	@JoinColumn(name ="Student_Id", referencedColumnName ="Student_Id", insertable = false, updatable = false)
	private Student student;
	@ManyToOne
	@JoinColumn(name ="Subject_Id", referencedColumnName ="Subject_Id", insertable = false, updatable = false)
	private SubjectBean subjectBean;
	@Column(name = "Class_Id")
    private String classId;
	@ManyToOne
	@JoinColumn(name ="Class_Id", referencedColumnName ="Class_Id", insertable = false, updatable = false)
	private Classes classes;
	@ManyToOne
	@JoinColumn(name ="ParentSubject_Id", referencedColumnName ="Subject_Id", insertable = false, updatable = false)
	private SubjectBean parentSubject;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String editMsg;
	@Transient
	private String testName;
	@Transient
	private String studentName;
	@Transient
	private String className;
	@Transient
	private String subjectName;
	@Transient
	private List<Student> mapOfStudents;
	@Transient
	private String studentIdEdit;
	@Transient
	private String marksEdit;
	@Transient 
	private String maxMarks;
	@Transient
	private String classIdEdit;
	@Transient
	private String testIdEdit;
	@Transient
	private String subjectIdEdit;
	/**
	 * @return the classes
	 */
	public Classes getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	/**
	 * @return the parentSubject
	 */
	public SubjectBean getParentSubject() {
		return parentSubject;
	}

	/**
	 * @param parentSubject the parentSubject to set
	 */
	public void setParentSubject(SubjectBean parentSubject) {
		this.parentSubject = parentSubject;
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
	 * @return the testIdEdit
	 */
	public String getTestIdEdit() {
		return testIdEdit;
	}

	/**
	 * @param testIdEdit the testIdEdit to set
	 */
	public void setTestIdEdit(String testIdEdit) {
		this.testIdEdit = testIdEdit;
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
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the subjectId
	 */
	public String getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the testId
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * @param testId
	 *            the testId to set
	 */
	public void setTestId(String testId) {
		this.testId = testId;
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
	 * @return the tests
	 */
	public Test getTests() {
		return tests;
	}

	/**
	 * @param tests
	 *            the tests to set
	 */
	public void setTests(Test tests) {
		this.tests = tests;
	}

	/**
	 * @return the testResultId
	 */
	public int getTestResultId() {
		return testResultId;
	}

	/**
	 * @param testResultId
	 *            the testResultId to set
	 */
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}

	/**
	 * @return the marks
	 */
	public String getMarks() {
		return marks;
	}

	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(String marks) {
		this.marks = marks;
	}

	/**
	 * @return the xmlLabel
	 */
	public String getXmlLabel() {
		return xmlLabel;
	}

	/**
	 * @param xmlLabel
	 *            the xmlLabel to set
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
	 * @param basicSearchId
	 *            the basicSearchId to set
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
	 * @param operations
	 *            the operations to set
	 */
	public void setOperations(String operations) {
		this.operations = operations;
	}

	/**
	 * @return the editMsg
	 */
	public String getEditMsg() {
		return editMsg;
	}

	/**
	 * @param editMsg
	 *            the editMsg to set
	 */
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}

}
