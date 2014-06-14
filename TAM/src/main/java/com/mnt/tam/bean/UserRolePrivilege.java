
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
 * @author tparvathi
 *
 */
@Entity
@Table(name="UserRolePrivileges")
public class UserRolePrivilege {
	@Id
	@Column(name="UserRolePrivilege_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int userRolePrivilegeId;
	
	@Column(name="Role_Id")
	private String roleId;
	@ManyToOne
    @JoinColumn(name="Role_Id",referencedColumnName="Role_Id",insertable=false,updatable=false)
	private Roles roles;
	
	@Column(name="User_Id")
	private String userId;
	@ManyToOne
    @JoinColumn(name="User_Id",referencedColumnName="User_Id",insertable=false,updatable=false)
	private Users users;
	
	@Column(name="Privilege_Id")
	private String privilegeId;
	@ManyToOne
    @JoinColumn(name="Privilege_Id",referencedColumnName="Privilege_Id",insertable=false,updatable=false)
	private Privilege privilege;
	@Transient
	private String xmlLabel;
	@Transient
	private String basicSearchId;
	@Transient
	private String operations;
	@Transient
	private String roleName;
	@Transient
	private String userName;
	@Transient
	private String privilegeName;
	@Transient
	private String editMsg;
	public int getUserRolePrivilegeId() {
		return userRolePrivilegeId;
	}
	public void setUserRolePrivilegeId(int userRolePrivilegeId) {
		this.userRolePrivilegeId = userRolePrivilegeId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public String getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPrivilegeName() {
		return privilegeName;
	}
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	public String getEditMsg() {
		return editMsg;
	}
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}
	
}
