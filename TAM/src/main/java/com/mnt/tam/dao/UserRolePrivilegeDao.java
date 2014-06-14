package com.mnt.tam.dao;

import java.util.List;

import com.mnt.tam.bean.UserRolePrivilege;

/**
 * @author tparvathi
 *
 */
public interface UserRolePrivilegeDao {
	public String saveUserRolePrivilegeDetails(UserRolePrivilege object);
	public List<Object[]> searchUserRolePrivilegeDetails(); 
	public List<Object[]> basicSearchForUserRolePrivilege(String dbField,String operations,String basicSearchId);
    public List<UserRolePrivilege> editUserRolePrivilegeDetails(int UserRolePrivilegeId);
    public String updateUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege);
	public String deleteUserRolePrivilegeDetails(int id);
}
