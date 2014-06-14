/**
 * 
 */
package com.mnt.tam.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
/**
 * @author venkateshp
 * 
 */
@Entity
@Table(name = "studentclass")
public class StudentClass implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "StudentClass_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int studentClassId;
	@Column(name = "Year")
	private String year;
	@Column(name ="Student_Id")
	private String studentId;
	@Column(name = "Class_Id")
	private String classId;
	@Column(name = "Section_Id")
	private String sectionId;
	@ManyToOne
	@JoinColumn(name ="Student_Id", referencedColumnName ="Student_Id", insertable = false, updatable = false)
	private Student student;
	@ManyToOne
	@JoinColumn(name = "Section_Id", referencedColumnName = "Section_Id", insertable = false, updatable = false)
	private Section section;
	@ManyToOne
	@JoinColumn(name = "Class_Id", referencedColumnName = "Class_Id", insertable = false, updatable = false)
	private Classes classStudent;
	@Transient
	private String editMsg;
	@Transient
	private String studentName;
	@Transient
	private String className;
	@Transient
	private String sectionName;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
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
	 * @return the studentClassId
	 */
	public int getStudentClassId() {
		return studentClassId;
	}

	/**
	 * @param studentClassId
	 *            the studentClassId to set
	 */
	public void setStudentClassId(int studentClassId) {
		this.studentClassId = studentClassId;
	}



	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the classId
	 */
	public String getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}

	/**
	 * @return the sectionId
	 */
	public String getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId
	 *            the sectionId to set
	 */
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the section
	 */
	public Section getSection() {
		return section;
	}

	/**
	 * @param section
	 *            the section to set
	 */
	public void setSection(Section section) {
		this.section = section;
	}

	/**
	 * @return the classStudent
	 */
	public Classes getClassStudent() {
		return classStudent;
	}

	/**
	 * @param classStudent
	 *            the classStudent to set
	 */
	public void setClassStudent(Classes classStudent) {
		this.classStudent = classStudent;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName
	 *            the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * @param sectionName
	 *            the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}
