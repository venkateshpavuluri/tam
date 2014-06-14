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

@Entity
@Table(name = "subjects")
public class SubjectBean  {

    @Id
    @Column(name = "Subject_Id")
    @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
    private int subjectId;
    @Column(name = "Subject")
    private String subject;
    @Column(name = "SubjectType_Id")
    private String subjectTypeId;
    @ManyToOne
    @JoinColumn(name = "SubjectType_Id", referencedColumnName = "SubjectType_Id", insertable = false, updatable = false)
    private SubjectType subjectType;
    
    @Column(name = "Class_Id")
    private String classId;
    @ManyToOne
    @JoinColumn(name = "Class_Id", referencedColumnName = "Class_Id", insertable = false, updatable = false)
    private Classes classes;
    @Column(name = "MaxMarks")
    private int maxMarks;
    @Column(name = "ParentSubject_Id",nullable=true)
    private String parentSubjectId;
    @Transient
    private String xmlLabel;
    @Transient
    private String basicSearchId;
    @Transient
    private String operations;
    @Transient
    private String subjectTypeName;
    @Transient
    private String className;
    @Transient
    private String editMsg;
    @Transient
    private int subjectIdEdit;

    public String getParentSubjectId() {
	return parentSubjectId;
    }

    public void setParentSubjectId(String parentSubjectId) {
	this.parentSubjectId = parentSubjectId;
    }

    public int getSubjectId() {
	return subjectId;
    }

    public void setSubjectId(int subjectId) {
	this.subjectId = subjectId;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getSubjectTypeId() {
	return subjectTypeId;
    }

    public void setSubjectTypeId(String subjectTypeId) {
	this.subjectTypeId = subjectTypeId;
    }

    public SubjectType getSubjectType() {
	return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
	this.subjectType = subjectType;
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

    public String getSubjectTypeName() {
	return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
	this.subjectTypeName = subjectTypeName;
    }

    public String getEditMsg() {
	return editMsg;
    }

    public void setEditMsg(String editMsg) {
	this.editMsg = editMsg;
    }

    public int getSubjectIdEdit() {
	return subjectIdEdit;
    }

    public void setSubjectIdEdit(int subjectIdEdit) {
	this.subjectIdEdit = subjectIdEdit;
    }

    public int getMaxMarks() {
	return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
	this.maxMarks = maxMarks;
    }

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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
