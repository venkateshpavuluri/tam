package com.mnt.tam.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
/**
 * @author parvathi
 *
 */
@Entity
@Table(name="section")
public class Section {
	@Id
	@Column(name="Section_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int sectionId;
	@Column(name="Section_Name")
	private String  section;
	@Column(name="Class_Id")
	private String classId;
	@ManyToOne
    @JoinColumn(name="Class_Id",referencedColumnName="Class_Id",insertable=false,updatable=false)
	private Classes classes;
	
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;

	@Transient
	private String className;
	@Transient
	private String editMsg;

	/**
	 * @return the sectionId
	 */
	public int getSectionId() {
		return sectionId;
	}

	/**
	 * @param sectionId the sectionId to set
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
