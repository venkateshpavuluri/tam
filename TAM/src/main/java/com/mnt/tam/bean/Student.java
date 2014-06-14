/**
 * 
 */
package com.mnt.tam.bean;
/**venkateshp
 *
 */
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
@Table(name = "student")
public class Student{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Student_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int studentId;
	@Column(name = "FirstName")
	private String fName;
	@Column(name = "LastName")
	private String lName;
	@Column(name = "MiddleName")
	private String midName;
	@Column(name = "AdmissionNo")
	private String admissionNo;
	@Column(name = "DOB")
	private String dob;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
	@Column(name = "Country")
	private String country;
	@Column(name = "Father_Name")
	private String fatehrName;
	@Column(name = "Mother_Name")
	private String mName;
	@Column(name = "Father_Occupation")
	private String fOccupation;
	@Column(name = "Mother_Occupation")
	private String mOccupation;
	@Column(name = "Mobile")
	private String mobileNo;
	@Column(name = "Phone")
	private String phoneNo;
	@Column(name = "School_Id")
	private String schoolId;
	@Column(name="Address")
	private String address;
	@Transient
	private String subjectId;
	@Transient
	private String marks;
	@Transient
	private String subjectName;
	
	@ManyToOne
	@JoinColumn(name ="Country", referencedColumnName ="Country_Id", insertable = false, updatable = false)
	private Country countries;
	@Transient
	private String testId;
	@Transient
	private String testName;
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
	 * @return the countries
	 */
	public Country getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Country countries) {
		this.countries = countries;
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

	@ManyToOne
	@JoinColumn(name = "School_Id", referencedColumnName = "School_Id", insertable = false, updatable = false)
	private School school;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String schoolName;
	@Transient
	private String editMsg;
	@Transient
	private String fNameEdit;
	
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
	 * @return the fNameEdit
	 */
	public String getfNameEdit() {
		return fNameEdit;
	}

	/**
	 * @param fNameEdit the fNameEdit to set
	 */
	public void setfNameEdit(String fNameEdit) {
		this.fNameEdit = fNameEdit;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName
	 *            the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the midName
	 */
	public String getMidName() {
		return midName;
	}

	/**
	 * @param midName
	 *            the midName to set
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}

	/**
	 * @return the admissionNo
	 */
	public String getAdmissionNo() {
		return admissionNo;
	}

	/**
	 * @param admissionNo
	 *            the admissionNo to set
	 */
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the fatehrName
	 */
	public String getFatehrName() {
		return fatehrName;
	}

	/**
	 * @param fatehrName
	 *            the fatehrName to set
	 */
	public void setFatehrName(String fatehrName) {
		this.fatehrName = fatehrName;
	}

	/**
	 * @return the mName
	 */
	public String getmName() {
		return mName;
	}

	/**
	 * @param mName
	 *            the mName to set
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the fOccupation
	 */
	public String getfOccupation() {
		return fOccupation;
	}

	/**
	 * @param fOccupation
	 *            the fOccupation to set
	 */
	public void setfOccupation(String fOccupation) {
		this.fOccupation = fOccupation;
	}

	/**
	 * @return the mOccupation
	 */
	public String getmOccupation() {
		return mOccupation;
	}

	/**
	 * @param mOccupation
	 *            the mOccupation to set
	 */
	public void setmOccupation(String mOccupation) {
		this.mOccupation = mOccupation;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
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
}





