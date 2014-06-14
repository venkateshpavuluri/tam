package com.mnt.tam.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="teachertype")
public class TeacherType {
	 @Id  
	 @Column(name = "TeacherType_Id")  
	 @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	 private int teacherTypeId;  
    
   @Column(name="TeacherType")
   private String teacherType;
   @Transient
   private String editMsg;
 
   //basic search properties
   @Transient
   private String xmlLabel;
   @Transient
	private String operations;
   @Transient
	private String basicSearchId;
public int getTeacherTypeId() {
	return teacherTypeId;
}
public void setTeacherTypeId(int teacherTypeId) {
	this.teacherTypeId = teacherTypeId;
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
public String getXmlLabel() {
	return xmlLabel;
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
