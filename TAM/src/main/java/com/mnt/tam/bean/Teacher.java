/**
 * 
 */
package com.mnt.tam.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author devi
 * 
 */
@Entity
@Table(name = "Teacher")
public class Teacher {
	@Id
	@Column(name = "Teacher_Id")
	 @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int teacherId;
	@Column(name = "First_Name")
	private String fName;
	@Column(name = "Last_Name")
	private String lName;
	@Column(name = "Middle_Name")
	private String midName;
	@Column(name = "DOB")
	private String dob;
	@Column(name = "Gender")
	private String gender;
	@Column(name = "Address")
	private String address;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
	@Column(name = "Country")
	private String country;
	@Column(name = "Zip")
	private String zip;
	@Column(name = "Email")
	private String email;
	@Column(name = "Mobile")
	private String mobileNo;
	@Column(name = "Phone")
	private String phoneNo;
	@Column(name = "TeacherType_Id")
	private String teacherTypeId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TeacherType_Id", referencedColumnName = "TeacherType_Id", insertable = false, updatable = false)
	private TeacherType teacherTypeBean;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "Teacher_Id", referencedColumnName = "Teacher_Id", insertable = true, updatable = false)
	private Set<TeacherSubject> teacherSubjects;
	@Transient
	private List<TeacherSubject> teachers;
	@Transient
	private String classId;
	@Transient
	private String className;
	@Transient
	private String subjectId;
	@Transient
	private String subject;

	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String teacherType;
	@Transient
	private String editMsg;
	@Transient
	private int atId;
	@ManyToOne
	@JoinColumn(name ="Country", referencedColumnName ="Country_Id", insertable = false, updatable = false)
	private Country countries;
	
	public Set<TeacherSubject> getTeacherSubjects() {
		return teacherSubjects;
	}

	public void setTeacherSubjects(Set<TeacherSubject> teacherSubjects) {
		this.teacherSubjects = teacherSubjects;
	}

	public TeacherType getTeacherTypeBean() {
		return teacherTypeBean;
	}

	public void setTeacherTypeBean(TeacherType teacherTypeBean) {
		this.teacherTypeBean = teacherTypeBean;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTeacherTypeId() {
		return teacherTypeId;
	}

	public void setTeacherTypeId(String teacherTypeId) {
		this.teacherTypeId = teacherTypeId;
	}

	public String getXmlLabel() {
		return xmlLabel;
	}

	public void setXmlLabel(String xmlLabel) {
		this.xmlLabel = xmlLabel;
	}

	public String getBasicSearchId() {
		return basicSearchId;
	}

	public void setBasicSearchId(String basicSearchId) {
		this.basicSearchId = basicSearchId;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}

	public String getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}

	public String getEditMsg() {
		return editMsg;
	}

	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}

	public List<TeacherSubject> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherSubject> teachers) {
		this.teachers = teachers;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getAtId() {
		return atId;
	}

	public void setAtId(int atId) {
		this.atId = atId;
	}
	
	

}
