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
 * @author tparvathi
 *
 */
@Entity
@Table(name="Privilege")
public class Privilege {
	 @Id  
	 @Column(name = "Privilege_Id")  
	 @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	 private int privilegeId;  
     
    @Column(name="Privilege")
    private String privilege;
    @Transient
    private String editMsg;
  
    //basic search properties
    @Transient
    private String xmlLabel;
    @Transient
	private String operations;
    @Transient
	private String basicSearchId;
	public int getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
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
