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
@Table(name="users")
public class Users {
	@Id
	@Column(name = "User_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int userId;
	@Column(name = "UserName")
	private String userName;
	@Column(name = "Password")
	private String password;
	@Column(name = "IsActive")
	private Boolean isActive;
	@Column(name = "ValidFrom")
	private String validFrom;
	@Column(name = "ValidTo")
	private String validTo;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String editMsg;
	@Transient
	private int atId;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
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
	public int getAtId() {
		return atId;
	}
	public void setAtId(int atId) {
		this.atId = atId;
	}

  

}
