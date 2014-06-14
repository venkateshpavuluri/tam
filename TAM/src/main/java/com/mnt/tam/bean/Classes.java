/**
 * 
 */
package com.mnt.tam.bean;


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
@Table(name="classes")
public class Classes {

	@Id
	@Column(name="Class_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int classId;
	@Column(name="Class_Name")
	private String className;
	@Column(name="School_Id")
	private String schoolId;
	@ManyToOne
	@JoinColumn(name="School_Id",referencedColumnName="School_Id",insertable=false,updatable=false)
	private School school;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String editMsg;
	@Transient
	private String schoolName;
	/**
	 * @return the classId
	 */
	public int getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(int classId) {
		this.classId = classId;
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
	 * @return the schoolId
	 */
	public String getSchoolId() {
		return schoolId;
	}
	/**
	 * @param schoolId the schoolId to set
	 */
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * @return the school
	 */
	public School getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(School school) {
		this.school = school;
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
	public String getEditMsg() {
		return editMsg;
	}
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	
}
