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
 * @author devi
 *
 */
@Entity
@Table(name="roles")
public class Roles {
	@Id
	@Column(name="Role_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int role_Id;
	@Column(name=" Role_Name")
	private String role_Name;
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
	private int roleIdEdit;
	@Transient
	private int atId;
    //getter & setter
	public int getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(int role_Id) {
		this.role_Id = role_Id;
	}
	public String getRole_Name() {
		return role_Name;
	}
	public void setRole_Name(String role_Name) {
		this.role_Name = role_Name;
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
	public String getEditMsg() {
		return editMsg;
	}
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}
	public int getRoleIdEdit() {
		return roleIdEdit;
	}
	public void setRoleIdEdit(int roleIdEdit) {
		this.roleIdEdit = roleIdEdit;
	}
	public int getAtId() {
		return atId;
	}
	public void setAtId(int atId) {
		this.atId = atId;
	}
	
    
	

}
