/**
 * 
 */
package com.mnt.tam.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yogi
 * 
 */
@Entity
@Table(name = "subjecttype")
public class SubjectType {

    private int subjectTypeId;
    private String subjectType;
    private String xmlLabel;
    private String operations;
    private String editMsg;

    @Transient
    public String getEditMsg() {
	return editMsg;
    }

    public void setEditMsg(String editMsg) {
	this.editMsg = editMsg;
    }

    @Transient
    public String getBasicSearchId() {
	return basicSearchId;
    }

    public void setBasicSearchId(String basicSearchId) {
	this.basicSearchId = basicSearchId;
    }

    private String basicSearchId;

    @Transient
    public String getXmlLabel() {
	return xmlLabel;
    }

    public void setXmlLabel(String xmlLabel) {
	this.xmlLabel = xmlLabel;
    }

    @Transient
    public String getOperations() {
	return operations;
    }

    public void setOperations(String operations) {
	this.operations = operations;
    }

    @Id
    @Column(name = "SubjectType_Id", unique = true, nullable = false)
    @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
    public int getSubjectTypeId() {
	return subjectTypeId;
    }

    public void setSubjectTypeId(int subjectTypeId) {
	this.subjectTypeId = subjectTypeId;
    }

    @Column(name = "SubjectType")
    public String getSubjectType() {
	return subjectType;
    }

    public void setSubjectType(String subjectType) {
	this.subjectType = subjectType;
    }

}
