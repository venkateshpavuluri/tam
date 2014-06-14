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
 * @author devi
 *
 */
@Entity
@Table(name="termtest")
public class TermTest {
	@Id
	@Column(name="TermTest_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int termtest_Id;
	@Column(name="TermTest")
	private String termtest;
	@Column(name="TermTestCode")
	private String termTestCode;
	@Column(name="Term_Id")
	private String termId;
	@ManyToOne
    @JoinColumn(name="Term_Id",referencedColumnName="Term_Id",insertable=false,updatable=false)
	private TermBean termBean;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
@Transient
private String operations;
@Transient
private String term;
@Transient
private String editMsg;
@Transient
private int termIdEdit;
@Transient
private int atId;
public int getTermtest_Id() {
	return termtest_Id;
}
public void setTermtest_Id(int termtest_Id) {
	this.termtest_Id = termtest_Id;
}
public String getTermtest() {
	return termtest;
}
public void setTermtest(String termtest) {
	this.termtest = termtest;
}
public String getTermTestCode() {
	return termTestCode;
}
public void setTermTestCode(String termTestCode) {
	this.termTestCode = termTestCode;
}
public String getTermId() {
	return termId;
}
public void setTermId(String termId) {
	this.termId = termId;
}
public TermBean getTermBean() {
	return termBean;
}
public void setTermBean(TermBean termBean) {
	this.termBean = termBean;
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
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}
public String getEditMsg() {
	return editMsg;
}
public void setEditMsg(String editMsg) {
	this.editMsg = editMsg;
}
public int getTermIdEdit() {
	return termIdEdit;
}
public void setTermIdEdit(int termIdEdit) {
	this.termIdEdit = termIdEdit;
}
public int getAtId() {
	return atId;
}
public void setAtId(int atId) {
	this.atId = atId;
}



}
