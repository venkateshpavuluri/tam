package com.mnt.tam.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="term")
public class TermBean {
	@Id
	@Column(name="Term_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int term_Id;
	@Column(name="Term")
	private String term;
	//xml operations
	@Transient
	private String xmlLabel;
	@Transient
	private String operations;
	@Transient
	private String basicSearchId;
	
	@Transient
	private String editMsg;
	@Transient
	private int termIdEdit;
	
	@Transient
	private int atId;
	//getters & setters
	
	
	
	public String getXmlLabel() {
		return xmlLabel;
	}
	public int getAtId() {
		return atId;
	}
	public void setAtId(int atId) {
		this.atId = atId;
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
	public int getTerm_Id() {
		return term_Id;
	}
	public void setTerm_Id(int term_Id) {
		this.term_Id = term_Id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setXmlLabel(String xmlLabel) {
		this.xmlLabel = xmlLabel;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public String getBasicSearchId() {
		return basicSearchId;
	}
	public void setBasicSearchId(String basicSearchId) {
		this.basicSearchId = basicSearchId;
	}
	
	
	
	
	
	
	

}
