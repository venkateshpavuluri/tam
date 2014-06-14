package com.mnt.tam.service;

import java.util.List;

import com.mnt.tam.bean.UserRolePrivilege;

public interface UserRolePrivilegeService {
	public String saveUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege);
	public List<UserRolePrivilege> searchUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege);
	public UserRolePrivilege editUserRolePrivilegeDetails(int UserRolePrivilegeId);
	public String updateUserRolePrivilegeDetails(UserRolePrivilege UserRolePrivilege); 
	public String deleteUserRolePrivilegeDetails(int id);
}
